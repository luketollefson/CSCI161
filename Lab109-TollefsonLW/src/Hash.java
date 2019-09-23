
/**
 * Some hashing algorithms and hash compression algorithms.
 * @author Luke Tollefson
 * @version April 5, 2019
 */
public class Hash {

    /**
     * Creates an int hash (positive or negative) using the polynomial method
     * @param s any string s
     * @param a the coefficient for the algorithm
     * @return a hash (positive or negative)
     */
    public static int polynomialHashCode(String s, int a) {
        if (s.isEmpty()) {
            return 0;
        } else {
            return (int) s.charAt(0) + a * polynomialHashCode(s.substring(1), a);
        }
    }

    /**
     * Uses the polynomialHashCode(..) method underneath, just ensures
     * the hash is a positive number
     * @param s any string s
     * @param a the coefficient for the algorithm 
     * @return a positive hash
     */
    public static int positivePolynomialHashCode(String s, int a) {
        int hash = polynomialHashCode(s, a);
        if (hash == Integer.MIN_VALUE) {
            return 0;
        } else {
            return Math.abs(hash);
        }
    }
    
    /**
     * Compresses a hash into the range [0..n]
     * the underling algorithm is [(a*hash + b) % p] % n
     * @param hash the hash to be compressed
     * @param n the largest possible hash
     * @param p preferably a prime number larger than n
     * @param a a number to multiply the hash
     * @param b a number to be added to the product of a and the hash
     * @return the compressed hash
     */
    public static int madCompress(int hash, int n, int p, int a, int b) {
        // it must be casted to a long because the hash can exceed boundries
        // before compression.
        if (b >= 0 && b < p && a > 0) {
            return (int) (((long) a*hash + b) % p) % n;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
