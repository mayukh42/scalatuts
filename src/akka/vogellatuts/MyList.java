package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/16/13
 * Time: 7:39 PM
 * defensive copy for performance in concurrent env
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MyList {

    List<String> list = new ArrayList<>();

    public void add( String s ) {
        list.add(s);
    }

//  create defensive copy
    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }
}
