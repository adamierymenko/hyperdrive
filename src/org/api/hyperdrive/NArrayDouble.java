package org.api.hyperdrive;

public final class NArrayDouble extends NArray<Double>
{
  public NArrayDouble(int[] dimensions)
  {
    super(dimensions);
    this.data = new double[super.size()];
  }

  @Override
  public final Double get(int idx)
  {
    return data[idx];
  }

  @Override
  public final void set(int idx,Double value)
  {
    data[idx] = value;
  }

  public final double getDouble(int idx)
  {
    return data[idx];
  }

  public final void setDouble(int idx,double value)
  {
    data[idx] = value;
  }

  public final double getDouble(int[] coords)
  {
    return data[super.indexOf(coords)];
  }

  public final void setDouble(int[] coords,double value)
  {
    data[super.indexOf(coords)] = value;
  }

  private final double[] data;
}
