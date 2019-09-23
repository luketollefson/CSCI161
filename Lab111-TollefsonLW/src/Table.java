
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class for printing out ascii tables
 * @author Luke Tollefson
 * @version May 1 2019
 */
public class Table {

    /**
     * Print out an adjacency matrix table of the graph g
     * @param title the name of the graph
     * @param g the graph being represented
     * @return 
     */
    public static String adjacencyMatrixTable(String title, Graph<String,Integer> g) {
        String[][] table = new String[g.numVertices()+1][g.numVertices()+1];
        
        // fill the table with blank spaces
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = "";
            }
        }
        
        // fill an array for the vertices
        Iterator<Vertex<String>> vertices = g.vertices().iterator();
        Vertex<String>[] verts = new Vertex[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++) {
            verts[i] = vertices.next();
        }
        
        // fill the column and header lables
        for (int i = 0; i < verts.length; i++) {
            table[0][i+1] = verts[i].getElement();
            table[i+1][0] = verts[i].getElement();
        }
        NumberFormat nf = NumberFormat.getIntegerInstance();
        for (int i = 0; i < verts.length; i++) {
            for (int j = 0; j < verts.length; j++) {
                Edge<Integer> edge = g.getEdge(verts[i], verts[j]);
                
                if (edge != null) {
                    table[i+1][j+1] = nf.format(edge.getElement());
                } else {
                    table[i+1][j+1] = "0";
                }
            }
        }
        
        return asciiTable2(title, table);
    }

    /**
     * Prints an aciiTable with a specific title, headers, and table of Strings
     *
     * @param title a table title, it cant be longer than the width of the table
     * @param headers the headers, you can used a "\n" to break the header
     * @param data the data in Strings
     * @return an ascii table of the data
     */
    public static String asciiTable2(String title, String[][] data) {
        String[] headers = new String[data[0].length];
        for (int i = 0; i < data[0].length; i++) {
            headers[i] = data[0][i];
        }
        
        String[][] newData = new String[data.length-1][];
        
        for (int i = 0; i < data.length-1; i++) {
            newData[i] = data[i+1];
        }
        data = newData;
        
        // the number of rows of of the data
        int rows = data.length;
        // find number of columns of the table
        int columns = 0;
        columns = Math.max(headers.length, columns);
        for (int i = 0; i < data.length; i++) {
            columns = Math.max(data[i].length, columns);
        }

        // convert the table contents to Strings
//        NumberFormat nf = NumberFormat.getIntegerInstance();
        String[][] numStrings = data;
//        for (int i = 0; i < rows; i++) {
//            numStrings[i] = new String[columns];
//            for (int j = 0; j < columns; j++) {
//                if (j < data[i].length) {
//                    numStrings[i][j] = nf.format(data[i][j]);
//                } else {
//                    numStrings[i][j] = "";
//                }
//            }
//        }

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
        row += "+" + (new String(new char[tableWidth + 5 * columnWidths.length - 1]).replace("\0", "-")) + "+";
        table += row + "\n";
        row = "";

        // add the able title
        title = title + (new String(new char[(tableWidth + 5 * (columnWidths.length - 1)) / 2 - title.length() / 2]).replace("\0", " "));
        table += String.format("|  %" + (tableWidth + 5 * (columnWidths.length - 1)) + "s  |\n", title);

        // add a line
        for (int j = 0; j < columns; j++) {
            if (j == columns - 1) {
                row += "+" + (new String(new char[4 + columnWidths[j]]).replace("\0", "-")) + "+";
            } else {
                row += "+" + (new String(new char[4 + columnWidths[j]]).replace("\0", "-"));
            }
        }
        table += row + "\n";
        row = "";

        // center headers
        for (int i = 0; i < headerRows; i++) {
            for (int j = 0; j < columns; j++) {
                String head = breakedHeaders[i][j];
                String rightPadding = (new String(new char[columnWidths[j] / 2 - head.length() / 2]).replace("\0", " "));
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
                row += "+" + (new String(new char[4 + columnWidths[j]]).replace("\0", "-")) + "+";
            } else {
                row += "+" + (new String(new char[4 + columnWidths[j]]).replace("\0", "-"));
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
                    line += "+" + (new String(new char[4 + columnWidths[j]]).replace("\0", "-")) + "+";
                } else {
                    line += "+" + (new String(new char[4 + columnWidths[j]]).replace("\0", "-"));
                }
            }
            table += row + "  |\n" + line + "\n";

        }

        return table;
    }
}
