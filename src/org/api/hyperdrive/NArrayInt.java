package org.api.hyperdrive;

public final class NArrayInt extends NArray<Integer>
{
  public NArrayInt(int[] dimensions)
  {
    super(dimensions);
    this.data = new int[super.size()];
  }

  @Override
  public final Integer get(int idx)
  {
    return data[idx];
  }

  @Override
  public final void set(int idx,Integer value)
  {
    data[idx] = value;
  }

  public final int getInt(int idx)
  {
    return data[idx];
  }

  public final void setInt(int idx,int value)
  {
    data[idx] = value;
  }

  public final int getInt(int[] coords)
  {
    return data[super.indexOf(coords)];
  }

  public final void setInt(int[] coords,int value)
  {
    data[super.indexOf(coords)] = value;
  }

  private final int[] data;
}
