
import java.util.ArrayList;
import java.util.List;


/**
 * A Client testing out the BinaryTree and it's traversals
 * @author Luke Tollefson
 * @version March 14, 2019
 */
public class Client {
    /* A lispy representation of the tree
    (* (+ (/ (* (+ 5 2)
                (- 2 1))
             (+ 2 9))
          (- (- 7 2)
             1))
       8)
    
    The names a..i
    (a (b (c (d (e 5 2)
                (f 2 1))
             (g 2 9))
          (h (i 7 2)
             1))
       8)
    */
    
    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LinkedBinaryTree expr = new LinkedBinaryTree<String>();
        // a is the root of the tree
        Position a, b, c, d, e, f, g, h, i;
        a = expr.addRoot("*");
        b = expr.addLeft(a, "+");
        c = expr.addLeft(b, "/");
        d = expr.addLeft(c, "*");
        e = expr.addLeft(d, "+");
        expr.addLeft(e, "5");
        expr.addRight(e, "2");
        f = expr.addRight(d, "-");
        expr.addLeft(f, "2");
        expr.addRight(f, "1");
        g = expr.addRight(c, "+");
        expr.addLeft(g, "2");
        expr.addRight(g, "9");
        h = expr.addRight(b, "-");
        i = expr.addLeft(h, "-");
        expr.addLeft(i, "7");
        expr.addRight(i, "2");
        expr.addRight(h, "1");
        expr.addRight(a, "8");
        
        System.out.println("The original expression:");
        System.out.println("(((5+2)*(2–1)/(2+9)+((7–2)–1))*8)\n");
        
        System.out.println("Preorder traversal:");
        Iterable<Position<String>> preIter = expr.preorder();
        for (Position<String> s : preIter) {
            System.out.print(s.getElement() + " ");
        }
        System.out.println("\n");
        
        System.out.println("Inorder traversal:");
        Iterable<Position<String>> inIter = expr.inorder();
        for (Position<String> s : inIter) {
            System.out.print(s.getElement() + " ");
        }
        System.out.println("\n");
        
        System.out.println("Postorder traversal:");
        Iterable<Position<String>> postIter = expr.postorder();
        for (Position<String> s : postIter) {
            System.out.print(s.getElement() + " ");
        }
        System.out.println("\n");
        
        System.out.println("Breadth traversal:");
        Iterable<Position<String>> breadthIter = expr.breadthfirst();
        for (Position<String> s : breadthIter) {
            System.out.print(s.getElement() + " ");
        }
        System.out.println("\n");
        
        System.out.println("Parenthesized representation:");        
        printParenthesize(expr, a);
        System.out.println();
    }
    
    /**
     * print a parenthesized representation of the tree. Suited for arithmetic trees.
     * @param <E>
     * @param T the tree you want to print
     * @param p the node which you want to print from
     */
    public static <E> void printParenthesize(LinkedBinaryTree<E> T, Position<E> p) {
        if (T.left(p) != null) {
            System.out.print("(");
            printParenthesize(T, T.left(p));
        }
        System.out.print(p.getElement());
        if (T.right(p) != null) {
            printParenthesize(T, T.right(p));
            System.out.print(")");            
        }
    }
}
