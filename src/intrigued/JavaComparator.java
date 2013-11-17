package intrigued;

/**
 * User: mayukh
 * Date: 11/13/13
 * Time: 10:52 PM
 */
import java.util.ArrayList;
import java.util.List;

public class JavaComparator {

    final static int[] ns = {1,2,3,4,5,6};

    public static void testCollections() {
        List<Integer> doubled = new ArrayList<>(); // wef JDK7, we dont need to put type in the cons (RHS)
        for (int n : ns) {
            doubled.add(n<<1);
        }
        System.out.println(doubled.toString());
    }

    public int testFnSig(int a, int b) {
        return a+b;
    }

//    public double testFnSig(int a, int b) { // wont compile, neither in scala or c++.
//        return Math.sqrt(a*b);
//    }

    public static void main(String[] args) {
//        testCollections();
//        System.out.println(testFnSig(5, 6));
    }
}
