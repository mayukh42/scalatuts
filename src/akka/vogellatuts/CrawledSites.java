package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/16/13
 * Time: 3:22 PM
 *
 * lame DS for a web crawler. keeps track of visited sites and also a list of sites that are yet to be crawled.
 */

import java.util.ArrayList;
import java.util.List;

public class CrawledSites {

    private List<String> crawled = new ArrayList<>();
    private List<String> links = new ArrayList<>();

    public void add (String site) {
        synchronized (this) {
            if (!crawled.contains(site))
                links.add(site);
        }
    }

    public String next() {
        if (links.isEmpty())
            return null;
        synchronized (this) {
//          check again if there is a link, to keep logic independent of the non-synchronized part
            if (!links.isEmpty()) {
                String site = links.get(0);
                links.remove(0); // removing head is O(1) in list
                crawled.add(site);
                return site;
            }
            return null;
        }
    }

}
