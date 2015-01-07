package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/17/13
 * Time: 9:12 PM
 * Task to be computed using Fork/Join concurrency framework. Overrides compute() from RecursiveAction
 */

//import scala.actors.threadpool.Arrays;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ForkJoinSolver extends RecursiveAction {

    private int[] list;
    public long result;
    private from, to;

    public ForkJoinSolver(int[] list) {
        this.list = list;
    }

    private ForkJoinSolver(int[] list, int from, int to) {
        this.list = list;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void compute() {
        // Summing the list by recursively breaking them into two halves - CUDA prefix sum approach
        if (to - from == 1)
            result = list[to - from];
        else {
            int midpoint = list.length / 2;
            
            ForkJoinSolver fjs1 = new ForkJoinSolver(list, from, midpoint); // recursively create child objects of my type and call invokeAll() with them
            ForkJoinSolver fjs2 = new ForkJoinSolver(list, from + midpoint, to);
            invokeAll(fjs1, fjs2); // implements the fork/join paradigm
            result = fjs1.result + fjs2.result; // joins results at my level
        }
    }
}
