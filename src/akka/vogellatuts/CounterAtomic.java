package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/17/13
 * Time: 3:29 AM
 */

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic {

    private AtomicInteger value = new AtomicInteger();

    public int getValue() {
        return value.get();
    }

    public int increment() {
        return value.incrementAndGet();
    }

//  implementing increment operation explicitly
    public int incrementAliter() {
        int old = value.get();
        while (!value.compareAndSet(old, old + 1)) { // Atomically sets the value to the given updated value (arg1) if the current value == the expected value (arg0). false indicates actual value was not equal to expected value
            old = value.get(); // if CAS fails, do nothing, retrieve value and set old
        }
        return old + 1; // returns value + 1 in both cases of whether CAS fails (old = value) or succeds (old = old)
    }

}
