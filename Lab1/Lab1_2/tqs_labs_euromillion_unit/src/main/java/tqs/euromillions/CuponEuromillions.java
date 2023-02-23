package tqs.euromillions;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * models coupon, i.e., a set of several dips, as submitted by a player
 *
 * @author ico0
 */
public class CuponEuromillions implements Iterable<Dip> {

    private final ArrayList<Dip> dipsCollection = new ArrayList<>();

    public void appendDip(Dip dip) {
        dipsCollection.add(dip);
    }

    public int countDips() {
        return dipsCollection.size();

    }

    public Dip getDipByIndex(int order) {
        return dipsCollection.get(order);
    }

    /**
     * @return a formatted string with the Dips info
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        int dipIndex = 1;
        for (Dip dip : this) {
            sb.append(String.format("Dip #%d:", dipIndex++));
            sb.append(dip.format());
            sb.append("\n");
        }

        return sb.toString();

    }

    @Override
    public Iterator<Dip> iterator() {
        return dipsCollection.iterator();
    }
}
