import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Testing out the recursive methods using a GUI
 * @author Luke Tollefson
 * @version February 13 2019
 */
public class Client {

    /**
     * Ran at start
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        prompt();
    }
    
    /**
     * The main prompt for the user
     */
    private static void prompt() {
        String[] options = {"Harmonic","Isabel's Algorithem","UNIX find","Cancel"};
        int i = JOptionPane.showOptionDialog(null, "Which algorithem would you like to test?", 
                "Lab104", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, options, null);
        
        if (i == 0) {
            harmonicPrompt();
        } else if (i == 1) {
            isabelPrompt();
        } else if (i == 2) {
            findPrompt();
        } else if (i == 3) {
            return;
        }
    }
    
    /**
     * Prompt for the user to calculate the nth harmonic
     */
    private static void harmonicPrompt() {
        String input = JOptionPane.showInputDialog(null, "Calculate the nth harmonic", "Lab104", JOptionPane.DEFAULT_OPTION);
        // if they hit cancel
        if (input == null) {
            prompt();
            return;
        }
        // loop while input is invalid
        while (!input.matches("\\d+") || Integer.parseInt(input) < 0) {
            System.out.println("nthHarmonic: Invalid input for harmonic (" +input + "), n should be greater than 0");
            input = JOptionPane.showInputDialog(null, "Invalid input, enter n > 0", "Lab104", JOptionPane.DEFAULT_OPTION);
            // if the hit cancel
            if (input == null) {
                prompt();
                return;
            }
        }
        int n = Integer.parseInt(input);
        double h = Recursion.nthHarmonic(n);
        System.out.println("nthHarmonic: nthHarmonic(" + n + ") = " + h);
        JOptionPane.showMessageDialog(null, "Harmonic " + n + " is " + h, "Lab104", JOptionPane.INFORMATION_MESSAGE);
        prompt();
    }
    
    /**
     * Prompt to calculate the sum of a file using isabelSum
     */
    private static void isabelPrompt() {
        String inputFile = JOptionPane.showInputDialog(null, "Enter a file", "Lab104", JOptionPane.DEFAULT_OPTION);
        // if the user hits cancel
        if (inputFile == null) {
            prompt();
            return;
        }
        
        File f = new File(inputFile);
        int[] array = fileChecker(f);
        // loop while file is a directory, doesn't exist, or is invalid
        while (array == null) {
            if (!f.exists() || f.isDirectory()) {
                System.out.println("isabelSum: User tried Isabel's algorithem on non-existent file " + inputFile);                
                inputFile = JOptionPane.showInputDialog(null, "File not found, enter a different file", "Lab104", JOptionPane.DEFAULT_OPTION);
            } else {
                System.out.println("isabelSum: Unable to use Isabel's algorithem on " + inputFile);
                inputFile = JOptionPane.showInputDialog(null, "Unable to use Isabel's algorithm, enter a different file", "Lab104", JOptionPane.DEFAULT_OPTION);
            }
            if (inputFile == null) {
                // if the user hits cancel
                prompt();
                return;
            }
            f = new File(inputFile);
            array = fileChecker(f);
        }
        int sum = Recursion.isabelSum(array);
        System.out.println("isabelSum: The sum of " + inputFile + " is " + sum);
        JOptionPane.showMessageDialog(null, "The sum is " + sum, "Lab104", JOptionPane.INFORMATION_MESSAGE);
        prompt();
    }
    
    /**
     * Helper method for isabelPrompt()
     * @param f the file being summed
     * @return the array of integers within the file, or null if the
     *      file is not found or doesn't have 2^n number of numbers
     */
    private static int[] fileChecker(File f) {
        try {
            Scanner scan = new Scanner(f);
            ArrayBag<Integer> numbers = new ArrayBag<Integer>();
            System.out.println("isabelSum: Reading " + f.getAbsolutePath());
            // fetching the numbers from the file
            while (scan.hasNext()) {
                try {
                    int i = scan.nextInt();
                    System.out.println(i);
                    numbers.add(i);
                } catch (InputMismatchException ime) {
                    scan.next();
                }
            }
            // if the number of numbers is not a power of 2
            if (Integer.bitCount(numbers.getCurrentSize()) != 1) {
                return null;
            }
            // copying the numbers from ArrayBag to primative int array
            int[] array = new int[numbers.getCurrentSize()];
            for (int i = 0; i < numbers.getCurrentSize(); i++) {
                array[i] = numbers.elementAt(i);
            }

            return array;
        }
        catch (FileNotFoundException fnfe)
        {
            return null;
        }
        
    }
    
    /**
     * Prompt for the user to use find
     */
    private static void findPrompt() {
        String inputPath = JOptionPane.showInputDialog(null,"Enter a path", "Lab104", JOptionPane.DEFAULT_OPTION);
        // if the user hits cancel
        if (inputPath == null) {
            prompt();
            return;
        }
        File f = new File(inputPath);
        
        while (!f.isDirectory()) {
            System.out.println("find: path " + inputPath + " not found");
            inputPath = JOptionPane.showInputDialog(null,"Path not found, enter a path", "Lab104", JOptionPane.DEFAULT_OPTION);
            f = new File(inputPath);
        }
        
        String inputFilename = JOptionPane.showInputDialog(null,"Enter a filename", "Lab104", JOptionPane.DEFAULT_OPTION);
        // if the user hits cancel
        if (inputFilename == null) {
            prompt();
            return;
        }
        System.out.println("find: finding " + inputFilename + " in " + inputPath);
        Recursion.find(inputFilename, inputPath);
        JOptionPane.showMessageDialog(null, "Output shown in the console");
        prompt();
    }
}
