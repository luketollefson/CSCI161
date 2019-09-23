
import java.util.Random;


/**
 * Client class to test the Scores class
 * 
 * @author Luke Tollefson
 * @version January 26, 2019
 */
public class Client {

    /**
     * Testing out the Scores class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scores scores = new Scores(100);
        Random rand = new Random();
        
        for (int i = 0; i < 100; i++) {
            scores.add(rand.nextInt(201) - 100);
        }
        System.out.println("Scores:\n" + scores.toString());
        scores.add(86);
        System.out.println("Length of scores w/ 86 added: " + scores.getCurrentSize());
        scores.remove();
        int seventyFifth = scores.get(75);
        System.out.println("Freq of 75th index with a random index removed: " + scores.getFrequencyOf(seventyFifth));
        scores.remove(seventyFifth);
        System.out.println("Freq of 75th index with the old 75th index removed: " + scores.getFrequencyOf(seventyFifth));
        System.out.println("Freq of 86: " + scores.getFrequencyOf(86));
        System.out.println("Does scores contain 86? " + scores.contains(86));
    }
    
}
