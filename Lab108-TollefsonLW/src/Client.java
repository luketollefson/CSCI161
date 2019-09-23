
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Evaluating arithmetic expression trees
 * @author Luke Tollefson
 */
public class Client {

    /**
     * 
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        File file;
        if (true) { // true if user input, false if hardcoded
            System.out.print("Enter the path of a file: ");
            String path = scan.next();
            System.out.print("Enter the filename: ");
            String filename = scan.next();
            file = new File(path + filename);
        } else {
            file = new File("/home/luke/NetBeansProjects/Lab108-TollefsonLW/src/data.txt");
        }

        scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.equals("")) {
                continue;
            }
            System.out.println("As in file: " + line);
            try {
                evaluateExpression(line);
            } catch(IllegalArgumentException iae) {
                System.out.println("Bad expression: " + iae.getLocalizedMessage());
            }
            System.out.println("\n");
        }
    }

    /**
     * Does most everything
     * converts the expression in postfix
     * prints a pre, in, and post-order traversal
     * prints a parenthesized representation
     * evaluates the expression
     * @param line the expression
     * @throws IllegalArgumentException 
     */
    public static void evaluateExpression(String line) throws IllegalArgumentException {

        LinkedQueue<String> right = new LinkedQueue<String>();
        LinkedStack<String> bottom = new LinkedStack<String>();
        LinkedStack<String> left = new LinkedStack<String>();

        // populate the righthand queue
        Scanner e = new Scanner(line);
        while (e.hasNext()) {
            right.enqueue(e.next());
        }
        /*
        Conditions for valid expression
        Every operator is followed by a non-operator token/non-)
        Every non-operator token is followed by a operator token/)
        Every ( is followed by a non-operator/non-) token
        Every ) is followed by a operator token/nothing
         */
        // read the tokens to produce a postfix stack
        boolean flip3 = false;
        while (!right.isEmpty()) {
            String token = right.dequeue();
            // deal with token accordingly
            switch (token) {
                case "(":
                    bottom.push(token);
                    if (right.first() == null || right.first().equals("*")
                            || right.first().equals("/") || right.first().equals("+")
                            || right.first().equals("-") || right.first().equals(")")
                            || right.first().equals("]") || right.first().equals("}")) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    break;
                case ")":
                    if (bottom.top() == null) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    while (!bottom.top().equals("(")) {
                        left.push(bottom.pop());
                    }
                    bottom.pop();
                    // right already dequeued
                    if (right.first() != null && !right.first().equals("*")
                            && !right.first().equals("/") && !right.first().equals("+")
                            && !right.first().equals("-") && !right.first().equals(")")
                            && !right.first().equals("]") && !right.first().equals("}")) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    break;                
                case "[":
                    bottom.push(token);
                    if (right.first() == null || right.first().equals("*")
                            || right.first().equals("/") || right.first().equals("+")
                            || right.first().equals("-") || right.first().equals(")")
                            || right.first().equals("]") || right.first().equals("}")) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    break;
                case "]":
                    if (bottom.top() == null) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    while (!bottom.top().equals("[")) {
                        left.push(bottom.pop());
                    }
                    bottom.pop();
                    // right already dequeued
                    if (right.first() != null && !right.first().equals("*")
                            && !right.first().equals("/") && !right.first().equals("+")
                            && !right.first().equals("-") && !right.first().equals(")")
                            && !right.first().equals("]") && !right.first().equals("}")) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    break;
                case "{":
                    bottom.push(token);
                    if (right.first() == null || right.first().equals("*")
                            || right.first().equals("/") || right.first().equals("+")
                            || right.first().equals("-") || right.first().equals(")")
                            || right.first().equals("]") || right.first().equals("}")) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    break;
                case "}":
                    if (bottom.top() == null) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    while (!bottom.top().equals("{")) {
                        left.push(bottom.pop());
                    }
                    bottom.pop();
                    // right already dequeued
                    if (right.first() != null && !right.first().equals("*")
                            && !right.first().equals("/") && !right.first().equals("+")
                            && !right.first().equals("-") && !right.first().equals(")")
                            && !right.first().equals("]") && !right.first().equals("}")) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    break;
                case "*":
                case "/":
                case "+":
                case "-":
                    if (bottom.top() != null && (token.equals("+") || token.equals("-"))
                            && (bottom.top().equals("*") || bottom.top().equals("/"))) {
                        String temp = bottom.pop();
                        bottom.push(token);
                        bottom.push(temp);
                        flip3 = true;
                    } else {
                        bottom.push(token);
                    }
                    //bottom.push(token);
                    if (right.first() == null || right.first().equals("*")
                            || right.first().equals("/") || right.first().equals("+")
                            || right.first().equals("-") || right.first().equals(")")
                            || right.first().equals("]") || right.first().equals("}")) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    break;
                default:
                    left.push(token);
                    if (right.first() != null && (!right.first().equals("*") 
                            && !right.first().equals("/") && !right.first().equals("+") 
                            && !right.first().equals("-") && !right.first().equals(")")
                            && !right.first().equals("]") && !right.first().equals("}")
                            || right.first().equals("(") || right.first().equals("[")
                            || right.first().equals("{"))) {
                        throw new IllegalArgumentException("Bad token after " + token);
                    }
                    if (flip3) {
                        String s1 = left.pop();
                        String s2 = left.pop();
                        String s3 = left.pop();
                        left.push(s1);
                        left.push(s3);
                        left.push(s2);
                        flip3 = false;
                    }
                    break;
            }
        }
        // push the remaing bottom tokens to the left
        while (!bottom.isEmpty()) {
            left.push(bottom.pop());
        }

        if (!bottom.isEmpty() || !right.isEmpty()) {
            throw new IllegalArgumentException("Not enough parentheses");
        }

        LinkedStack<String> temp = new LinkedStack<String>();
        while (!left.isEmpty()) {
            temp.push(left.pop());
        }

        // print out the expression
        System.out.print("Postfix: ");
        while (!temp.isEmpty()) {
            System.out.print(temp.top() + " ");
            left.push(temp.pop());
        }
        temp = null;
        System.out.println();

        /*
        Now we wish to produce a tree representing the expression
         */
        // the tree to store the expression
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();

        // now we will bring everything to the right
        Stack<String> right2 = new LinkedStack<String>();
        while (!left.isEmpty()) {
            right2.push(left.pop());
        }

        // build the expression tree
        LinkedStack<LinkedBinaryTree<String>> bottom2 = new LinkedStack<LinkedBinaryTree<String>>();
        while (!right2.isEmpty()) {
            LinkedBinaryTree<String> tempTree = new LinkedBinaryTree<String>();
            LinkedBinaryTree<String> tempLeft = new LinkedBinaryTree<String>();
            LinkedBinaryTree<String> tempRight = new LinkedBinaryTree<String>();

            String token = right2.pop();

            switch (token) {
                case "*":
                case "/":
                case "+":
                case "-":
                    tempTree.addRoot(token);
                    tempRight = bottom2.pop();
                    tempLeft = bottom2.pop();
                    tempTree.attach(tempTree.root(), tempLeft, tempRight);
                    //tempTree.addLeft(tempTree.root(), bottom)
                    //bottom.push(token);
                    bottom2.push(tempTree);
                    break;
                default:
                    tempTree.addRoot(token);
                    bottom2.push(tempTree);
                    left.push(token);
                    break;
            }
        }

        tree = bottom2.pop();

        System.out.print("Preorder  traversal: ");
        Iterable<Position<String>> preIter = tree.preorder();
        for (Position<String> s : preIter) {
            System.out.print(s.getElement() + " ");
        }

        System.out.print("\nInorder   traversal: ");
        Iterable<Position<String>> inIter = tree.inorder();
        for (Position<String> s : inIter) {
            System.out.print(s.getElement() + " ");
        }

        System.out.print("\nPostorder traversal: ");
        Iterable<Position<String>> postIter = tree.postorder();
        for (Position<String> s : postIter) {
            System.out.print(s.getElement() + " ");
        }

        System.out.print("\nParenthesized: ");
        printParenthesize(tree, tree.root());

        System.out.print("\nEvaluates to: " + evaluate(tree, tree.root()));
    }

    public static double evaluate(LinkedBinaryTree<String> T, Position<String> p) {
        if (T.isExternal(p)) {
            return Double.parseDouble(p.getElement());
        }
        switch (p.getElement()) {
            case "*":
                return evaluate(T, T.left(p)) * evaluate(T, T.right(p));
            case "/":
                return evaluate(T, T.left(p)) / evaluate(T, T.right(p));
            case "+":
                return evaluate(T, T.left(p)) + evaluate(T, T.right(p));
            case "-":
                return evaluate(T, T.left(p)) - evaluate(T, T.right(p));
        }
        return Double.NaN; // should never be reached
    }

    /**
     * print a parenthesized representation of the tree. Suited for arithmetic
     * trees.
     *
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
