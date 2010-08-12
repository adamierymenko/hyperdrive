package org.api.hyperdrive;

public final class NArrayFloat extends NArray<Float>
{
  public NArrayFloat(int[] dimensions)
  {
    super(dimensions);
    this.data = new float[super.size()];
  }

  @Override
  public final Float get(int idx)
  {
    return data[idx];
  }

  @Override
  public final void set(int idx,Float value)
  {
    data[idx] = value;
  }

  public final float getFloat(int idx)
  {
    return data[idx];
  }

  public final void setFloat(int idx,float value)
  {
    data[idx] = value;
  }

  public final float getFloat(int[] coords)
  {
    return data[super.indexOf(coords)];
  }

  public final void setFloat(int[] coords,float value)
  {
    data[super.indexOf(coords)] = value;
  }

  private final float[] data;
}
