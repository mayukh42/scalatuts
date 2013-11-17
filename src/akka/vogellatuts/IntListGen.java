package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/17/13
 * Time: 8:29 PM
 */

import java.util.Random;

public class IntListGen {

    private final int[] list;

    public IntListGen() {
        this.list = new int[2000000];
        Random generator = new Random (19580427);
        for (int i = 0; i < list.length; i++) {
            list[i] = generator.nextInt(500000);
        }
    }

    public int[] getList() {
        return list;
    }
}
