package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/17/13
 * Time: 2:09 AM
 */

import java.util.concurrent.Callable;

public class MyCallableAdder implements Callable<Long> {

    private final long sumTo;

    public MyCallableAdder(long sumTo) {
        this.sumTo = sumTo;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = 1; i <= sumTo; i++)
            sum += i;
        return sum;
    }
}
