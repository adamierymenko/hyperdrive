package org.api.hyperdrive;

public final class NArrayByte extends NArray<Byte>
{
  public NArrayByte(int[] dimensions)
  {
    super(dimensions);
    this.data = new byte[super.size()];
  }

  @Override
  public final Byte get(int idx)
  {
    return data[idx];
  }

  @Override
  public final void set(int idx,Byte value)
  {
    data[idx] = value;
  }

  public final byte getByte(int idx)
  {
    return data[idx];
  }

  public final void setByte(int idx,byte value)
  {
    data[idx] = value;
  }

  public final byte getByte(int[] coords)
  {
    return data[super.indexOf(coords)];
  }

  public final void setByte(int[] coords,byte value)
  {
    data[super.indexOf(coords)] = value;
  }

  private final byte[] data;
}
