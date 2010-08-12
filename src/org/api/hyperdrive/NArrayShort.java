package org.api.hyperdrive;

public final class NArrayShort extends NArray<Short>
{
  public NArrayShort(int[] dimensions)
  {
    super(dimensions);
    this.data = new short[super.size()];
  }

  @Override
  public final Short get(int idx)
  {
    return data[idx];
  }

  @Override
  public final void set(int idx,Short value)
  {
    data[idx] = value;
  }

  public final short getShort(int idx)
  {
    return data[idx];
  }

  public final void setShort(int idx,short value)
  {
    data[idx] = value;
  }

  public final short getShort(int[] coords)
  {
    return data[super.indexOf(coords)];
  }

  public final void setShort(int[] coords,short value)
  {
    data[super.indexOf(coords)] = value;
  }

  private final short[] data;
}
