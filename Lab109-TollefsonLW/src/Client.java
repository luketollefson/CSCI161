
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Scanner;


/**
 * Testing out some hashing and compression algorithms 
 * @author Luke Tollefson
 * @version April 5, 2019
 */
public class Client {
    
    // there are 45402 words in words.txt
    // the next prime after that number is 45403
    // then test 45398, 45403, 45408
    
    /**
     * The method ran when the program starts
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
            
        // testing out the polynomial hash function
        long[][] aTests = new long[16][3];
        for (int i = 0; i <= 15; i++) {
            TotalMax cols = testHash(i + 30);
            // and the information to the table
            aTests[i][0] = i + 30;
            aTests[i][1] = cols.total;
            aTests[i][2] = cols.max;
        }
        System.out.println(
                asciiTable("Poly Collisions", new String[]{"Shift", "Total", "Max"}, aTests));
        
        // a hash table using open addressing
        // 90821 is the next prime that keeps the load factor < 0.5
        int N = 90804;  // twice as many words in the file to ensure low load factor
        int primeMinusFive = 90816;
        int prime = 90821;
        int primePlusFive = 90826;
        
        // testing the MAD compression algorithm and adding the entries to the table
        long[][] madTests = new long[3][3];
        madTests[0][0] = primeMinusFive;
        TotalMax pmf = testMAD(N, primeMinusFive);
        madTests[0][1] = pmf.total;
        madTests[0][2] = pmf.max;
        
        madTests[1][0] = prime;
        TotalMax p = testMAD(N, prime);
        madTests[1][1] = p.total;
        madTests[1][2] = p.max;
        
        madTests[2][0] = primePlusFive;
        TotalMax ppf = testMAD(N, primePlusFive);
        madTests[2][1] = ppf.total;
        madTests[2][2] = ppf.max;
        
        System.out.println(
                asciiTable("MAD Collisions", new String[]{"p", "Total", "Max"}, madTests));

    }
    
    /**
     * Tests out MAD compression on the text in words.txt
     * @param n the number of available indexes
     * @param p a (preferably prime greater than n) number
     * @return the total and maximum number of collisions
     * @throws FileNotFoundException 
     */
    public static TotalMax testMAD(int n, int p) throws FileNotFoundException {
        String[] table = new String[n];
        int[] cols = new int[n];
        File words = new File("words.txt");
        Scanner scan = new Scanner(words);
        int totalCollisions = 0;
        
        // deal with each word
        while (scan.hasNext()) {
            String word = scan.next();
            int hash = Hash.madCompress(Hash.positivePolynomialHashCode(word, 33), n, p, 51, 12540);
            // if there was a collision
            if (table[hash] != null) {
                cols[hash]++;
                totalCollisions++;
            }
            // find the next open index
            while (table[hash] != null) {
                hash = (hash + 1) % 90804;
            }
            table[hash] = word; 
        }
        
        // figure out how many collisions there are
        int maxCollisions = 0;
        for (int i = 0; i < cols.length; i++) {
            maxCollisions = Math.max(maxCollisions, cols[i]);
        }
        
        return new TotalMax(totalCollisions, maxCollisions);
    }
    
    /**
     * Testing out the hashing algorithm
     * @param a the a being used for polynomial hashing
     * @return the total number of collisions and maximum
     * @throws FileNotFoundException 
     */
    public static TotalMax testHash(int a) throws FileNotFoundException {
        HashNum[] hs = new HashNum[45402];
        File words = new File("words.txt");
        Scanner scan = new Scanner(words);
        
        // scan each word
        while (scan.hasNext()) {
            addHash(Hash.polynomialHashCode(scan.next(), a), hs);
        }

        // determine the number of collisions
        int collisions = 0;
        int maxCollisions = 0;
        for (int i = 0; i < hs.length && hs[i] != null; i++) {
            maxCollisions = Math.max(maxCollisions, hs[i].num - 1);
            collisions += hs[i].num - 1;
        }
        
        return new TotalMax(collisions, maxCollisions);
    }
    
    /**
     * Adds a hash to the hash table
     * (the hash table only contains the hashes and how many have appeared)
     * @param hash the hash number 
     * @param hs the hash table
     */
    public static void addHash(int hash, HashNum[] hs) {
        int i = 0;
        for (; i < hs.length && hs[i] != null; i++) {
            if (hash == hs[i].hash) {
                hs[i].num++;
                return;
            }
        }
        // if new hash if the hash is not present
        hs[i] = new HashNum(hash);
    }
    
     /**
     * Prints an aciiTable with a specific title, headers, and table of longs
     * @param title a table title, it cant be longer than the width of the table
     * @param headers the headers, you can used a "\n" to break the header
     * @param data the data in longs
     * @return an ascii table of the data
     */
    public static String asciiTable(String title, String[] headers, long[][] data) {
        // the number of rows of of the data
        int rows = data.length;
        // find number of columns of the table
        int columns = 0;
        columns = Math.max(headers.length, columns);
        for (int i = 0; i < data.length; i++) {
            columns = Math.max(data[i].length, columns);
        }
        
        // convert the table contents to Strings
        NumberFormat nf = NumberFormat.getIntegerInstance();
        String[][] numStrings = new String[rows][];
        for (int i = 0; i < rows; i++) {
            numStrings[i] = new String[columns];
            for (int j = 0; j < columns; j++) {
                if (j < data[i].length) {
                    numStrings[i][j] = nf.format(data[i][j]);
                } else {
                    numStrings[i][j] = "";
                }
            }
        }

        // find the most newlines in a header
        int headerRows = 0;
        Scanner scan;
        for (int i = 0; i < headers.length; i++) {
            int newlines = 0;
            scan = new Scanner(headers[i]).useDelimiter("\n");
            while (scan.hasNext()) {
                scan.next();
                newlines++;
            }
            headerRows = Math.max(headerRows, newlines);
        }
        
        // Create a multi-demential lists for headers
        String[][] breakedHeaders = new String[headerRows][columns];
        for (int i = 0; i < columns; i++) {
            if (i < headers.length) {
                scan = new Scanner(headers[i]).useDelimiter("\n");
                int j = 0;
                while (scan.hasNext()) {
                    breakedHeaders[j][i] = scan.next();
                    j++;
                }
                while (j < headerRows) {
                    breakedHeaders[j][i] = "";
                    j++;
                }
            } else {
                for (int j = 0; j < headerRows; j++) {
                    breakedHeaders[j][i] = ""; 
                }
            }
        }

        // find the column widths
        // search the headers
        int[] columnWidths = new int[columns];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < headerRows; j++) {
                columnWidths[i] = Math.max(breakedHeaders[j][i].length(), columnWidths[i]);
            }
        }
        // search the data
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                columnWidths[i] = Math.max(numStrings[j][i].length(), columnWidths[i]);
            }
        }
        
        // find the width of the entire table (excluding | +, etc)
        int tableWidth = 0;
        for (int i = 0; i < columnWidths.length; i++) {
            tableWidth += columnWidths[i];
        }
        
        // we have row, columns, numStrings, headerRows, breakedHeaders, and columnWidths
        String table = "";
        String row = "";
        
        // top line
        row += "+" + (new String(new char[tableWidth+5*columnWidths.length-1]).replace("\0", "-")) + "+";
        table += row + "\n";
        row = "";
        
        // add the able title
        title =  title + (new String(new char[(tableWidth+5*(columnWidths.length-1))/2-title.length()/2]).replace("\0", " "));
        table += String.format("|  %" + (tableWidth+5*(columnWidths.length-1)) + "s  |\n", title);
        
        
        // add a line
        for (int j = 0; j < columns; j++) {
            if (j == columns - 1) {
                row += "+" + (new String(new char[4+columnWidths[j]]).replace("\0", "-")) + "+";
            } else {
                row += "+" + (new String(new char[4+columnWidths[j]]).replace("\0", "-"));
            }
        }
        table += row + "\n";
        row = "";
        
        // center headers
        for (int i = 0; i < headerRows; i++) {
            for (int j = 0; j < columns; j++) {
                String head = breakedHeaders[i][j];
                String rightPadding = (new String(new char[columnWidths[j]/2-head.length()/2]).replace("\0", " "));
                breakedHeaders[i][j] = head + rightPadding;
            }
        }
        
        
        // add the headers
        for (int i = 0; i < headerRows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j == 0) {
                    row += String.format("|  %" + columnWidths[j] + "s", breakedHeaders[i][j]);
                } else {
                    row += String.format("  |  %" + columnWidths[j] + "s", breakedHeaders[i][j]);
                }
            }
            table += row + "  |\n";
            row = "";
        }
        row = "";
        
        
        // add a line
        for (int j = 0; j < columns; j++) {
            if (j == columns - 1) {
                row += "+" + (new String(new char[4+columnWidths[j]]).replace("\0", "-")) + "+";
            } else {
                row += "+" + (new String(new char[4+columnWidths[j]]).replace("\0", "-"));
            }
        }
        table += row + "\n";
        
        // add the data
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j == 0) {
                    row = String.format("|  %" + columnWidths[j] + "s", numStrings[i][j]);
                } else {
                    row += String.format("  |  %" + columnWidths[j] + "s", numStrings[i][j]);
                }
            }
            String line = "";
            for (int j = 0; j < columns; j++) {
                if (j == columns - 1) {
                    line += "+" + (new String(new char[4+columnWidths[j]]).replace("\0", "-")) + "+";
                } else {
                    line += "+" + (new String(new char[4+columnWidths[j]]).replace("\0", "-"));
                }
            }
            table += row + "  |\n" + line + "\n";
            
        }
        
        return table;
    }
}

/**
 * Essentially an immutable tuple that holds two ints
 * @author Luke Tollefson
 * @version April 5, 2019
 */
class TotalMax {
    public final int total;
    public final int max;
    
    /**
     * 
     * @param t the total
     * @param m the mamximum
     */
    public TotalMax(int t, int m) {
        total = t;
        max = m;
    }
}

/**
 * Essentially a tuple that holds two ints.
 * @author Luke Tollefson
 * @version April 5, 2019
 */
class HashNum {
    public final int hash;
    public int num;
    
    /**
     * 
     * @param h the hash
     */
    public HashNum(int h) {
        hash = h;
        num = 1;
    }
}