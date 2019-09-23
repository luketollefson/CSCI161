import java.io.File;
import java.io.FileNotFoundException;

/**
 * Some recursive algorithms
 * @author Luke Tollefson
 * @version February 13 2019
 */
public class Recursion {

    /**
     * Calculates the nthHarmonic
     * @param n the nth harmonic
     * @return the value of the nth harmonic
     */
    public static double nthHarmonic(int n) {
        if (n >= 1) {
            return harmonic(n);
        } else {
            throw new IllegalArgumentException("nthHarmoic(int n) only allows n >= 1");
        }
    }

    /**
     * Recursively defined function for calculating the nth harmonic
     * @param n the nth harmonic
     * @return the value of the nth harmonic
     */
    private static double harmonic(int n) {
        if (n == 1) {
            return 1;
        } else {
            return (double) 1 / n + harmonic(n - 1);
        }
    }

    /**
     * Recursively calculates the sum of an array using Isabel's technique
     * @param a an array, its length must be a power of 2
     * @return the first index is the sum
     */
    private static int[] isabelSummation(int[] a) {
        if (a.length == 1) {
            return a;
        } else {
            int[] b = new int[a.length / 2];
            for (int i = 0; i < b.length; i++) {
                b[i] = a[2*i] + a[2*i+1];
            }
            return isabelSummation(b);
        }
    }
    
    /**
     * Calculates the sum of an array using Isabel's technique
     * @param a an array, its length must be a power of 2
     * @return the sum of the array
     */
    public static int isabelSum(int[] a) {
        // if a.length is a power of 2, then there should only be one one-bit
        if (Integer.bitCount(a.length) == 1) {
            return isabelSummation(a)[0];
        } else {
            throw new IllegalArgumentException("isabelSum(int[] a) requires a.length to be a power of 2");
        }
    }
    
    /**
     * Basic usage of the UNIX find command
     * @param filename the file trying to be found
     * @param startPath the path we are looking in
     */
    public static void find(String filename, String startPath) {
        File dir = new File(startPath);
        File f = new File(startPath, filename);
        // if the file is found
        if (f.exists()) {
            System.out.println(f.getAbsolutePath());
        }
        if (dir.isDirectory()) {
            File[] subdirs = dir.listFiles();
            for (int i = 0; i < subdirs.length; i++) {
                // if the directory is unreaderable, then a NullPointerException
                // will be thrown
                if (subdirs[i].canRead() && subdirs[i].isDirectory()) {
                    find(filename, subdirs[i].getAbsolutePath());
                }
            }
        }
    }
}
