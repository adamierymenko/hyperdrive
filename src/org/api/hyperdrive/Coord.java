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
