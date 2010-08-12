package org.api.hyperdrive;

public final class NArrayLong extends NArray<Long>
{
  public NArrayLong(int[] dimensions)
  {
    super(dimensions);
    this.data = new long[super.size()];
  }

  @Override
  public final Long get(int idx)
  {
    return data[idx];
  }

  @Override
  public final void set(int idx,Long value)
  {
    data[idx] = value;
  }

  public final long getLong(int idx)
  {
    return data[idx];
  }

  public final void setLong(int idx,long value)
  {
    data[idx] = value;
  }

  public final long getLong(int[] coords)
  {
    return data[super.indexOf(coords)];
  }

  public final void setLong(int[] coords,long value)
  {
    data[super.indexOf(coords)] = value;
  }

  private final long[] data;
}
