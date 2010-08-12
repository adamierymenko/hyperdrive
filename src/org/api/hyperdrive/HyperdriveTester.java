package org.api.hyperdrive;

import java.util.Random;

public class HyperdriveTester
{
  public static void runTest()
  {
    System.out.println("Testing with a 3D array...");

    Random prng = new Random(System.currentTimeMillis());
    int[][][] testReference = new int[991][997][7];
    int[] dimensions = new int[3];
    dimensions[0] = 991;
    dimensions[1] = 997;
    dimensions[2] = 7;
    int[] coords = new int[3];
    NArrayInt testArray = new NArrayInt(dimensions);
    System.out.println("Created array with dimensions ["+Coord.toString(dimensions)+"], size: "+testArray.size());
    coords[0] = 990;
    coords[1] = 996;
    coords[2] = 6;
    System.out.println("Index of max coords ["+Coord.toString(coords)+"]: "+testArray.indexOf(coords));
    for(int x=0;x<991;++x) {
      for(int y=0;y<997;++y) {
        for(int z=0;z<7;++z) {
          int foo = prng.nextInt();
          testReference[x][y][z] = foo;
          coords[0] = x;
          coords[1] = y;
          coords[2] = z;
          try {
            testArray.setInt(coords,foo);
          } catch (Exception e) {
            System.out.println("Failed setInt() at "+Coord.toString(coords));
            System.out.println("Size of array: "+testArray.size());
            e.printStackTrace();
            throw new RuntimeException("Test failed at "+Coord.toString(coords));
          }
        }
      }
    }
    for(int x=0;x<991;++x) {
      for(int y=0;y<997;++y) {
        for(int z=0;z<7;++z) {
          coords[0] = x;
          coords[1] = y;
          coords[2] = z;
          if (testArray.get(coords) != testReference[x][y][z])
            throw new RuntimeException("Test failed at "+Coord.toString(coords));
        }
      }
    }
    System.out.println("Passed.");
  }

  public static void runBenchmarks()
  {
    System.out.println("Benchmarking native Java arrays...");

    System.out.print("  1 D: "); System.out.flush();
    int[] ja1d = new int[1048576];
    int[] coords = new int[1];
    int[] limits = new int[coords.length];
    limits[0] = ja1d.length;
    long lookups = 0;
    long startTime = System.currentTimeMillis();
    for(int k=0;k<128;++k) {
      for(int i=0;i<1048576;++i) {
        ja1d[coords[0]] = i + k;
        Coord.advance(coords,limits);
        ++lookups;
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println(Double.toString(((double)lookups) / ((double)(endTime - startTime)))+" lookups/ms");
    ja1d = null;
    System.gc();

    System.out.print("  2 D: "); System.out.flush();
    int[][] ja2d = new int[1024][1024];
    coords = new int[2];
    limits = new int[coords.length];
    limits[0] = ja2d.length;
    limits[1] = ja2d[0].length;
    lookups = 0;
    startTime = System.currentTimeMillis();
    for(int k=0;k<128;++k) {
      for(int i=0;i<(1024*1024);++i) {
        ja2d[coords[0]][coords[1]] = i + k;
        Coord.advance(coords,limits);
        ++lookups;
      }
    }
    endTime = System.currentTimeMillis();
    System.out.println(Double.toString(((double)lookups) / ((double)(endTime - startTime)))+" lookups/ms");
    ja2d = null;
    System.gc();

    System.out.print("  3 D: "); System.out.flush();
    int[][][] ja3d = new int[96][96][96];
    coords = new int[3];
    limits = new int[coords.length];
    limits[0] = ja3d.length;
    limits[1] = ja3d[0].length;
    limits[2] = ja3d[0][0].length;
    lookups = 0;
    startTime = System.currentTimeMillis();
    for(int k=0;k<128;++k) {
      for(int i=0;i<(96*96*96);++i) {
        ja3d[coords[0]][coords[1]][coords[2]] = i + k;
        Coord.advance(coords,limits);
        ++lookups;
      }
    }
    endTime = System.currentTimeMillis();
    System.out.println(Double.toString(((double)lookups) / ((double)(endTime - startTime)))+" lookups/ms");
    ja3d = null;
    System.gc();

    System.out.print("  4 D: "); System.out.flush();
    int[][][][] ja4d = new int[32][32][32][32];
    coords = new int[4];
    limits = new int[coords.length];
    limits[0] = ja4d.length;
    limits[1] = ja4d[0].length;
    limits[2] = ja4d[0][0].length;
    limits[3] = ja4d[0][0][0].length;
    lookups = 0;
    startTime = System.currentTimeMillis();
    for(int k=0;k<128;++k) {
      for(int i=0;i<(32*32*32*32);++i) {
        ja4d[coords[0]][coords[1]][coords[2]][coords[3]] = i + k;
        Coord.advance(coords,limits);
        ++lookups;
      }
    }
    endTime = System.currentTimeMillis();
    System.out.println(Double.toString(((double)lookups) / ((double)(endTime - startTime)))+" lookups/ms");
    ja4d = null;
    System.gc();

    System.out.print("  5 D: "); System.out.flush();
    int[][][][][] ja5d = new int[16][16][16][16][16];
    coords = new int[5];
    limits = new int[coords.length];
    limits[0] = ja5d.length;
    limits[1] = ja5d[0].length;
    limits[2] = ja5d[0][0].length;
    limits[3] = ja5d[0][0][0].length;
    limits[4] = ja5d[0][0][0][0].length;
    lookups = 0;
    startTime = System.currentTimeMillis();
    for(int k=0;k<128;++k) {
      for(int i=0;i<(16*16*16*16*16);++i) {
        ja5d[coords[0]][coords[1]][coords[2]][coords[3]][coords[4]] = i + k;
        Coord.advance(coords,limits);
        ++lookups;
      }
    }
    endTime = System.currentTimeMillis();
    System.out.println(Double.toString(((double)lookups) / ((double)(endTime - startTime)))+" lookups/ms");
    ja5d = null;
    System.gc();

    System.out.println();

    System.out.println("Benchmarking integer NArrays of similar size...");
    for(int n=1;n<=5;++n) {
      System.out.print("  "+n+" D: "); System.out.flush();
      coords = new int[n];
      limits = new int[n];
      switch(n) {
        case 1:
          limits[0] = 1048576;
          break;
        case 2:
          limits[0] = 1024;
          limits[1] = 1024;
          break;
        case 3:
          limits[0] = 96;
          limits[1] = 96;
          limits[2] = 96;
          break;
        case 4:
          limits[0] = 32;
          limits[1] = 32;
          limits[2] = 32;
          limits[3] = 32;
          break;
        case 5:
          limits[0] = 16;
          limits[1] = 16;
          limits[2] = 16;
          limits[3] = 16;
          limits[4] = 16;
          break;
      }
      NArrayInt a = new NArrayInt(limits);
      lookups = 0;
      startTime = System.currentTimeMillis();
      for(int k=0;k<128;++k) {
        for(int i=0,j=a.size();i<j;++i) {
          a.setInt(coords,i + k);
          Coord.advance(coords,limits);
          ++lookups;
        }
      }
      endTime = System.currentTimeMillis();
      System.out.println(Double.toString(((double)lookups) / ((double)(endTime - startTime)))+" lookups/ms");
    }
  }

  public static void main(String[] argv)
  {
    runTest();
    System.gc();
    System.out.println();
    try {
      Thread.sleep(1000L);
    } catch (Exception e) {}
    runBenchmarks();
  }
}
