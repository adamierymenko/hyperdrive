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
 * Utility methods for dealing with coordinate arrays
 */
public class Coord
{
  /**
   * Lexicographically increment a coordinate array
   * 
   * @param coords Coordinate array
   * @param limits Limits of space (usually space's dimensions)
   * @return False if we've wrapped at the limits
   */
  public static final boolean advance(int[] coords,int[] limits)
  {
    for(int i=0;i<coords.length;++i) {
      if (++coords[i] < limits[i])
        return true;
      else coords[i] = 0;
    }
    return false;
  }

  /**
   * Get a comma-delimited representation of a coordinate array
   * 
   * @param coords Coordinate array
   * @return Comma-delimited representation
   */
  public static final String toString(int[] coords)
  {
    StringBuffer tmp = new StringBuffer(coords.length * 6);
    for(int i=0;i<coords.length;++i) {
      if (i > 0)
        tmp.append(',');
      tmp.append(Integer.toString(coords[i]));
    }
    return tmp.toString();
  }
}
