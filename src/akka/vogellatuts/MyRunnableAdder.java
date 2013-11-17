package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/16/13
 * Time: 11:28 PM
 */

public class MyRunnableAdder implements Runnable {

    private final long sumTo;

    public MyRunnableAdder(long sumTo) {
        this.sumTo = sumTo;
    }

    @Override
    public void run() {
        // sum of first n integers
        long sum = 0;
        for (int i = 1; i <= sumTo; i++)
            sum += i;
        System.out.println("Sum of first " + sumTo + " integers: " + sum);
    }
}
