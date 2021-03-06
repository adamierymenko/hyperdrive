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

public final class NArrayDouble extends NArray<Double> {
    public NArrayDouble(int[] dimensions) {
        super(dimensions);
        this.data = new double[super.size()];
    }

    @Override
    public final Double get(int idx) {
        return data[idx];
    }

    @Override
    public final void set(int idx, Double value) {
        data[idx] = value;
    }

    public final double getDouble(int idx) {
        return data[idx];
    }

    public final void setDouble(int idx, double value) {
        data[idx] = value;
    }

    public final double getDouble(int[] coords) {
        return data[super.indexOf(coords)];
    }

    public final void setDouble(int[] coords, double value) {
        data[super.indexOf(coords)] = value;
    }

    public final NArrayDouble getResized(int[] newDim) throws Exception {

        NArrayDouble output = new NArrayDouble(newDim);

        if (this.size() == output.size()) {
            int[] coordsOld = new int[dimensions().length];
            int[] coordsNew = new int[newDim.length];

            for (int i = 0, j = this.size(); i < j; ++i) {

                output.setDouble(coordsNew, this.get(coordsOld));

                Coord.advance(coordsOld, dimensions());
                Coord.advance(coordsNew, newDim);

            }
            return output;
        } else {
            throw new Exception("Can't resize to a matrix with a different number of values.");
        }
    }

    private final double[] data;
}
