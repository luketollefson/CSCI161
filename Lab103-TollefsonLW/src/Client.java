
import java.util.Random;
import java.util.Scanner;

/**
 * Client class testing the ArrayBag and LinkedBag classes
 * @author Luke Tollefson
 * @version February 1, 2019
 */

public class Client {

    /**
     * Method called upon program execution
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayBag<Player> footballTeam = new ArrayBag<Player>(2);
        
        // user input for football players
        System.out.println("Create a football team");
        Scanner scan = new Scanner(System.in);
        String name = "";
        String positionPlayed = "";
        String jerseyNumber = "";
        for (int i = 0; i < 6; i++) {
            System.out.print("Enter player" + (i+1) + "'s name: ");
            name = scan.nextLine();
            System.out.print("Enter player" + (i+1) + "'s position: ");
            positionPlayed = scan.nextLine();
            do {
                System.out.print("Enter player" + (i+1) + "'s jersey number: ");
                jerseyNumber = scan.nextLine();
            } while (!jerseyNumber.matches("\\d+")); // the regex only accepts numbers
            footballTeam.add(new Player(name, positionPlayed, Integer.parseInt(jerseyNumber)));
        }
        
//        // hardcoded football team for testing
//        footballTeam.add(new Player("Christian Watson", "WR", 1));
//        footballTeam.add(new Player("Tre Fort", "CB", 7));
//        footballTeam.add(new Player("Easton Stick", "QB", 12));
//        footballTeam.add(new Player("Jaylaan Wimbush", "FS", 23));
//        footballTeam.add(new Player("Brock Robbins", "FB", 34));
//        footballTeam.add(new Player("Cole Karcz", "DT", 53));
        
        System.out.println(footballTeam);
        
        footballTeam.remove();
        System.out.println(footballTeam);
        
        footballTeam.add(new Player("Jacob Kubas", "OG", 63));
        System.out.println(footballTeam);
        
        footballTeam.remove(new Player("Jacob Kubas", "OG", 63));
        System.out.println(footballTeam);

        
        // testing out ArrayBag on Strings
        System.out.println("\nShowing the ArrayBag class can work with Strings");
        ArrayBag<String> courses = new ArrayBag<String>();
        courses.add("CSCI 161");
        courses.add("ENGL 321");
        courses.add("MATH 129");
        courses.add("MATH 266");
        courses.add("MATH 270");  
        System.out.println(courses);
        
        courses.remove();
        System.out.println(courses);
        
        
        // Doing the same with LinkedBag
        System.out.println("\nCreate a basketball team");
        LinkedBag<Player> basketballTeam = new LinkedBag<Player>();
        for (int i = 0; i < 6; i++) {
            System.out.print("Enter player" + (i+1) + "'s name: ");
            name = scan.nextLine();
            System.out.print("Enter player" + (i+1) + "'s position: ");
            positionPlayed = scan.nextLine();
            do {
                System.out.print("Enter player" + (i+1) + "'s jersey number: ");
                jerseyNumber = scan.nextLine();
            } while (!jerseyNumber.matches("\\d+"));
            basketballTeam.add(new Player(name, positionPlayed, Integer.parseInt(jerseyNumber)));
        }
        
//        // hardcoded football team for testing
//        basketballTeam.add(new Player("Reagan Heun", "G", 2));
//        basketballTeam.add(new Player("Tyrah Spencer", "G", 24));
//        basketballTeam.add(new Player("Raquel Terrer van Gool", "G", 33));
//        basketballTeam.add(new Player("Macey Kvilvang", "F", 14));
//        basketballTeam.add(new Player("Danneka Voegeli", "C", 44));
//        basketballTeam.add(new Player("Rylee Nudell", "F", 21));
        
        System.out.println(basketballTeam);
        
        basketballTeam.remove();
        System.out.println(basketballTeam);

        basketballTeam.add(new Player("Sarah Jacobson", "G", 12));
        System.out.println(basketballTeam);

        basketballTeam.remove(new Player("Sarah Jacobson", "G", 12));
        System.out.println(basketballTeam);

    }
}