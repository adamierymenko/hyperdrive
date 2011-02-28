/*
 * Copyright (c) 2010-2011 Adam Ierymenko [adam.ierymenko@gmail.com]
 * All rights reserved. Licensed under the BSD license.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * - Neither the name of the author Adam Ierymenko nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.api.hyperdrive;

/**
 * Base class of all N-dimensional array implementations
 *
 * This is an abstract class. Use one of the NArray[Type] children for
 * an efficient implementation for each given type.
 * 
 * NArrayObject provides a templated object container for any arbitrary
 * type.
 * 
 * For primitive types such as int, double, etc. use NArrayInt, NArrayDouble,
 * etc. and use their typed accessors such as getInt(), setDouble(), etc.
 * These avoid Java's boxing/unboxing overhead with primitive types and will
 * yield greatly improved numerical performance over the generic get()/set()
 * interface.
 * 
 * Performance is approximately 34% slower for one-dimensional arrays compared
 * to direct use of a flat array. For 2d arrays, performance is similar to or
 * slightly better than [][] arrays. For 3d arrays, performance is slightly
 * better. For 4d and higher arrays performance is often orders of magnitude
 * better, measured up to 7X or more.
 * 
 * NOTE: The underlying flat array has Fortran-style ordering, which means
 * that incrementing the first dimension in the coordinate array moves by
 * single steps. This is also called column-major ordering. This is the
 * opposite of the in-memory ordering of a C multidimensional array. Be sure
 * to increment coordinates in lowest-dimension-first order or memory access
 * patterns will be very sub-optimal from a cache point of view. The included
 * Coord.advance() utility method does this for you.
 * 
 * @param <T> Type to be indexed
 */
public abstract class NArray<T>
{
  protected NArray(int[] dimensions)
  {
    this.strides = new int[dimensions.length];
    this.dimensions = new int[dimensions.length];

    this.strides[0] = 1;
    this.dimensions[0] = dimensions[0];
    int s = dimensions[0];
    int ns = 3;
    for(int i=1;i<dimensions.length;++i) {
      this.strides[i] = this.strides[i - 1] * dimensions[i - 1];
      this.dimensions[i] = dimensions[i];
      s *= dimensions[i];
      ns *= 3;
    }
    this.size = s;
    this.neighborhoodSize = ns;
  }

  /**
   * @return Number of dimensions (shortcut to dimensions().length)
   */
  public final int N()
  {
    return dimensions.length;
  }

  /**
   * @return Dimensions of array (do not modify returned array)
   */
  public final int[] dimensions()
  {
    return this.dimensions;
  }

  /**
   * @return Size of a Moore neighborhood in this array (3^N)
   */
  public final int neighborhoodSize()
  {
    return this.neighborhoodSize;
  }

  /**
   * @param coords Coordinates to look up
   * @return Index in raw underlying array corresponding to coordinates
   */
  public final int indexOf(int[] coords)
  {
    int idx = coords[0];
    for(int i=1;i<coords.length;++i)
      idx += coords[i] * strides[i];
    return idx;
  }

  /**
   * @return Raw size of array in elements
   */
  public final int size()
  {
    return this.size;
  }

  /**
   * @param idx Index of element in raw underlying array
   * @return Element value
   */
  public abstract T get(int idx);

  /**
   * @param idx Index of element in raw underlying array
   * @param value Element value to set
   */
  public abstract void set(int idx,T value);

  /**
   * @param coords Coordinates of element in n-dimensional array space
   * @return Element value
   */
  public final T get(int[] coords)
  {
    return get(indexOf(coords));
  }

  /**
   * @param coords Coordinates of element in n-dimensional array space
   * @param value Element value to set
   */
  public final void set(int[] coords,T value)
  {
    set(indexOf(coords),value);
  }

  /**
   * Fill every element with the specified value
   * 
   * @param value Value to fill
   */
  public final void fill(T value)
  {
    for(int i=0;i<this.size;++i)
      set(i,value);
  }

  private final int[] strides;
  private final int[] dimensions;
  private final int size;
  private final int neighborhoodSize;
}
