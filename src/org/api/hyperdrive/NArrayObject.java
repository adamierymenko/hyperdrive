package org.api.hyperdrive;

public class NArrayObject<T> extends NArray<T>
{
  public NArrayObject(int[] dimensions)
  {
    super(dimensions);
    this.data = new Object[super.size()];
  }

  @SuppressWarnings("unchecked")
  @Override
  public final T get(int idx)
  {
    return (T)data[idx];
  }

  @Override
  public final void set(int idx,T value)
  {
    data[idx] = value;
  }

  private final Object[] data;
}
