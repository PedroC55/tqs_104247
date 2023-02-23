/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.euromillions.Dip;

/**
 * @author ico0
 */
public class DipTest {

    private Dip sampleInstance;


    @BeforeEach
    public void setUp() {
        sampleInstance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        sampleInstance = null;
    }

    @DisplayName("format as string show all elements")
    @Test
    public void testFormat() {
        String result = sampleInstance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @DisplayName("new Dip rejects wrong size ou negatives")
    @Test
    public void testConstructorFromBadArrays() {

        // insufficient args
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11}, new int[]{} ) );

        //negative numbers
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11, 12, 13, -1}, new int[]{1, 2} ) );

        // this test will reveal that the code was not yet checking ranges


    }

    @DisplayName("new Dip rejects out of range elements")
    @Test
    public void testConstructorFromBadRanges() {
        // creating Dip with numbers or starts outside the expected range
        // expects an exception
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11, 12, 13, Dip.NUMBERS_RANGE_MAX * 2}, new int[]{1,2} ) );
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{11, 12, 13, 14, 15}, new int[]{ Dip.STARS_RANGE_MAX*2 ,1} ) );

    }



}
