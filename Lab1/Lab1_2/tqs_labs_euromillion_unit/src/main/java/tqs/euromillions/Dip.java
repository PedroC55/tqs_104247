package tqs.euromillions;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

import tqs.sets.BoundedSetOfNaturals;

import java.util.Random;

/**
 * A set of 5 numbers and 2 starts according to the Euromillions ranges.
 *
 * @author ico0
 */
public class Dip {
    static Random generator = new Random();
    public static final int NUMBERS_RANGE_MAX = 50;
    public static final int STARS_RANGE_MAX = 10;
    public static final int NUMBERS_REQUIRED = 5;
    public static final int STARS_REQUIRED = 2;
    private BoundedSetOfNaturals numbers;
    private BoundedSetOfNaturals stars;

    public Dip() {
        numbers = new BoundedSetOfNaturals(NUMBERS_REQUIRED);
        stars = new BoundedSetOfNaturals(STARS_REQUIRED);
    }

    public Dip(int[] arrayOfNumbers, int[] arrayOfStarts) {
        this();

       
       if( ! Arrays.stream(arrayOfNumbers).allMatch( nr -> nr >= 1 && nr <= NUMBERS_RANGE_MAX) ){
            throw new IllegalArgumentException("numbers set are outside the expected range!");
        }

        if( ! Arrays.stream(arrayOfStarts).allMatch(nr -> nr >= 1 && nr <= STARS_RANGE_MAX) ){
            throw new IllegalArgumentException("numbers set are outside the expected range!");
        }
        

        if (NUMBERS_REQUIRED == arrayOfNumbers.length && STARS_REQUIRED == arrayOfStarts.length) {
            numbers.add(arrayOfNumbers);
            stars.add(arrayOfStarts);
        } else {
            throw new IllegalArgumentException("wrong number of elements in numbers/stars");
        }

    }

    public BoundedSetOfNaturals getNumbersColl() {
        return numbers;
    }

    public BoundedSetOfNaturals getStarsColl() {
        return stars;
    }

    public static Dip generateRandomDip()  {


        Dip randomDip = new Dip();
        for (int i = 0; i < NUMBERS_REQUIRED; ) {
            int candidate = generator.nextInt(NUMBERS_RANGE_MAX) + 1;
            if (!randomDip.getNumbersColl().contains(candidate)) {
                randomDip.getNumbersColl().add(candidate);
                i++;
            }
        }
        for (int i = 0; i < STARS_REQUIRED; ) {
            int candidate = generator.nextInt(STARS_RANGE_MAX) + 1;
            if (!randomDip.getStarsColl().contains(candidate)) {
                randomDip.getStarsColl().add(candidate);
                i++;
            }
        }
        return randomDip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numbers);
        hash = 29 * hash + Objects.hashCode(this.stars);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dip other = (Dip) obj;
        if (!Objects.equals(this.numbers, other.numbers)) {
            return false;
        }
        return Objects.equals(this.stars, other.stars);
    }


    /**
     * prepares a string representation of the data structure, formated for
     * printing
     *
     * @return formatted string with data
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("N[");
        for (int number : getNumbersColl()) {
            sb.append(String.format("%3d", number));
        }
        sb.append("] S[");
        for (int star : getStarsColl()) {
            // the formating codes were missing
            sb.append(String.format("%3d", star));
        }
        sb.append("]");
        return sb.toString();
    }
}
