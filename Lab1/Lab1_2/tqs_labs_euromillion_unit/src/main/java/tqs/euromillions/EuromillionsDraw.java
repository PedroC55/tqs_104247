package tqs.euromillions;

import java.util.ArrayList;

/**
 * Models an Euromillions draw. Contains the solution and allows to check for
 * matched numbers and stars.
 *
 * @author ico
 */
public class EuromillionsDraw {

    private final Dip drawResults;

    /**
     * Creates a new draw assuming the given solution.
     *
     * @param drawResults the given solution is used
     */
    public EuromillionsDraw(Dip drawResults) {
        this.drawResults = drawResults;
    }

    /**
     * Creates a new draw from a random solution.
     *
     * @return new instance
     */
    static public EuromillionsDraw generateRandomDraw() {
        return new EuromillionsDraw(Dip.generateRandomDip());
    }

    /**
     * Find the numbers and start matches.
     *
     * @param playCoupon the user sheet
     * @return an array of dips containing only the numbers and starts that
     * are also present in the draw
     */
    public ArrayList<Dip> findMatchesFor(CuponEuromillions playCoupon) {

        ArrayList<Dip> results = new ArrayList<Dip>();
        Dip workingDip;

        for (Dip dipUnderAnalysis : playCoupon) {
            workingDip = new Dip();
            for (int number : dipUnderAnalysis.getNumbersColl()) {
                if (drawResults.getNumbersColl().contains(number)) {
                    workingDip.getNumbersColl().add(number);
                }
            }
            for (int star : dipUnderAnalysis.getStarsColl()) {
                if (drawResults.getStarsColl().contains(star)) {
                    workingDip.getStarsColl().add(star);
                }
            }
            results.add(workingDip);
        }
        return results;
    }

    public Dip getDrawResults() {
        return drawResults;
    }
}
