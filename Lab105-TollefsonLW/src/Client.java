
import java.text.NumberFormat;
import java.util.Scanner;


/**
 * Testing out ArrayStack, LinkedStack, ArrayQueue, and LinkedQueue classes
 * @author Luke Tollefson
 */
public class Client {
    
    /**
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        System.out.println(
                asciiTable("ArrayStack Test", 
                        new String[]{"N","push\n(nsec)","pop\n(nsec)"},
                        arrayStackTimes()));
        
        System.out.println(
                asciiTable("LinkedStack Test", 
                        new String[]{"N","push\n(nsec)","pop\n(nsec)"},
                        linkedStackTimes()));

        System.out.println(
                asciiTable("ArrayQueue Test", 
                        new String[]{"N","enqueue\n(nsec)","dequeue\n(nsec)"},
                        arrayQueueTimes()));
       
        System.out.println(
                asciiTable("LinkedQueue Test", 
                        new String[]{"N","enqueue\n(nsec)","dequeue\n(nsec)"},
                        linkedQueueTimes()));
        
    }
    
    /**
     * 
     * @return a two dimensional array with the first column being N, second
     *  column is the is the time it took to push N elements onto the stack
     *  and the third column is how long it took to pop N elements off the stack
     */
    public static long[][] arrayStackTimes() {
        long[][] aStackTimes = new long[8][3]; // N, push time, pop time

        ArrayStack aStack;
        long start, end;
        int n;
        
        for (int p = 0; p < 8; p++) {
            n = (int) Math.pow(10, p+1);
            aStackTimes[p][0] = n;
            aStack = new ArrayStack<Integer>(n);
            
            // push items on
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                aStack.push(0);
            }
            end = System.nanoTime();
            aStackTimes[p][1] = end - start;
            
            // pop items off
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                aStack.pop();
            }
            end = System.nanoTime();
            aStackTimes[p][2] = end - start;
        }
        
        return aStackTimes;
    }
    
    /**
     * 
     * @return a two dimensional array with the first column being N, second
     *  column is the is the time it took to push N elements onto the stack
     *  and the third column is how long it took to pop N elements off the stack
     */
    public static long[][] linkedStackTimes() {
        long[][] lStackTimes = new long[8][3]; // N, push time, pop time

        LinkedStack lStack;
        long start, end;
        int n;
        
        for (int p = 0; p < 8; p++) {
            n = (int) Math.pow(10, p+1);
            lStackTimes[p][0] = n;
            lStack = new LinkedStack<Integer>();
            
            // push items on
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                lStack.push(0);
            }
            end = System.nanoTime();
            lStackTimes[p][1] = end - start;
            
            // pop items off
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                lStack.pop();
            }
            end = System.nanoTime();
            lStackTimes[p][2] = end - start;
        }
        
        return lStackTimes;
    }
     
    /**
     * 
     * @return a two dimensional array with the first column being N, second
     *  column is the is the time it took to enqueue N elements onto the queue
     *  and the third column is how long it took to dequeue N elements off the stack
     */
    public static long[][] arrayQueueTimes() {
        long[][] aQueueTimes = new long[8][3]; // N, enqueue time, dequeue time

        ArrayQueue aQueue;
        long start, end;
        int n;
        
        for (int p = 0; p < 8; p++) {
            n = (int) Math.pow(10, p+1);
            aQueueTimes[p][0] = n;
            aQueue = new ArrayQueue<Integer>(n);
            
            // enqueue items on
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                aQueue.enqueue(0);
            }
            end = System.nanoTime();
            aQueueTimes[p][1] = end - start;
            
            // dequeue items off
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                aQueue.dequeue();
            }
            end = System.nanoTime();
            aQueueTimes[p][2] = end - start;
        }
        
        return aQueueTimes;
    }
   
    /**
     * 
     * @return a two dimensional array with the first column being N, second
     *  column is the is the time it took to enqueue N elements onto the queue
     *  and the third column is how long it took to dequeue N elements off the stack
     */
    public static long[][] linkedQueueTimes() {
        long[][] lQueueTimes = new long[8][3]; // N, enqueue time, dequeue time

        LinkedQueue lQueue;
        long start, end;
        int n;
        
        for (int p = 0; p < 8; p++) {
            n = (int) Math.pow(10, p+1);
            lQueueTimes[p][0] = n;
            lQueue = new LinkedQueue<Integer>();
            
            // enqueue items on
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                lQueue.enqueue(0);
            }
            end = System.nanoTime();
            lQueueTimes[p][1] = end - start;
            
            // dequeue items off
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                lQueue.dequeue();
            }
            end = System.nanoTime();
            lQueueTimes[p][2] = end - start;
        }
        
        return lQueueTimes;
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
