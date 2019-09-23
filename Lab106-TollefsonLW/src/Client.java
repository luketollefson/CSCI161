
/**
 * A Client testing out LuckyNumberList
 * @author Luke Tollefson
 */
public class Client {

    /**
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        LuckyNumberList lnl = new LuckyNumberList();

        lnl.addLuckyNumber(new LuckyNumber("Luke"));
        lnl.addLuckyNumber(new LuckyNumber("Joe"));
        lnl.addLuckyNumber(new LuckyNumber("Steven"));
        lnl.addLuckyNumber(new LuckyNumber("Zach"));
        lnl.addLuckyNumber(new LuckyNumber("Adam"));
        lnl.addLuckyNumber(new LuckyNumber("CJ"));
        lnl.addLuckyNumber(new LuckyNumber("Ryan"));
        lnl.addLuckyNumber(new LuckyNumber("Brandon"));
        lnl.addLuckyNumber(new LuckyNumber("Nathan"));
        lnl.addLuckyNumber(new LuckyNumber("Wil"));

        //System.out.println(lnl);
        
        System.out.println("Table with all lucky numbers");
        Iterable<Position<LuckyNumber>> luckyNumberIterator = lnl.positions();
        printIterableTable(luckyNumberIterator);

        System.out.println("\nTable with all the even lucky numbers");
        Iterable<Position<LuckyNumber>> evenNumberIterator = lnl.evenNumbers();
        printIterableTable(evenNumberIterator);
        
        System.out.println("\nTable with all the prime lucky numbers");
        Iterable<Position<LuckyNumber>> primeNumberIterator = lnl.primeNumbers();
        printIterableTable(primeNumberIterator);


    }
    
    /**
     * Prints a simple table with all the lucky numbers
     * @param lni the iterable lucky numbers
     */
    public static void printIterableTable(Iterable<Position<LuckyNumber>> lni) {
        System.out.printf("%-7s %-12s %-6s %-9s\n", "Name", "Lucky Number", "Parity", "Primality");
        for (Position<LuckyNumber> pln : lni) {
            LuckyNumber ln = pln.getElement();
            String even = LuckyNumberList.isEven(ln.getLuckyNumber()) ? "Even" : "Odd";
            String prime = LuckyNumberList.isPrime(ln.getLuckyNumber()) ? "Prime" : "Not Prime";
            System.out.printf("%-7s %-12d %-6s %-9s\n", ln.getName(), ln.getLuckyNumber(), even, prime);  
        }
    }
}
