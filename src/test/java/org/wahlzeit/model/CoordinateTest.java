package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testGetDistance() {
        Coordinate base1 = new Coordinate(0, 0, 0);
        Assert.assertEquals(1, base1.getDistance(new Coordinate(1, 0, 0)), 0.001);
        Assert.assertEquals(2, base1.getDistance(new Coordinate(0, 2, 0)), 0.001);
        Assert.assertEquals(3.5, base1.getDistance(new Coordinate(0, 0, 3.5)), 0.001);

        Coordinate base2 = new Coordinate(1, 1, 1);
        Assert.assertEquals(1, base2.getDistance(new Coordinate(0, 1, 1)), 0.001);
        Assert.assertEquals(3.5, base2.getDistance(new Coordinate(1, -2.5, 1)), 0.001);
        Assert.assertEquals(4, base2.getDistance(new Coordinate(1, 1, -3)), 0.001);

        Coordinate base3 = new Coordinate(-2, 4, -3);
        Assert.assertEquals(5.83095, base3.getDistance(new Coordinate(1, 1, 1)), 0.001);
        Assert.assertEquals(10.81665, base3.getDistance(new Coordinate(7, 10, -3)), 0.001);
        Assert.assertEquals(13.42572, base3.getDistance(new Coordinate(4, -8, -3.5)), 0.001);
    }

    @Test
    public void testIsEqual() {
        Coordinate base = new Coordinate(1, 2, 3);
        Assert.assertTrue(base.isEqual(new Coordinate(1, 2, 3)));
        Assert.assertTrue(base.isEqual(base));

        Assert.assertFalse(base.isEqual(null));
        Assert.assertFalse(base.isEqual(new Coordinate(1, 2, 0)));
        Assert.assertFalse(base.isEqual(new Coordinate(1, 0, 2)));
        Assert.assertFalse(base.isEqual(new Coordinate(0, 2, 2)));
        Assert.assertFalse(base.isEqual(new Coordinate(-1, -2, -3)));
    }

    @Test
    public void testEquals() throws Exception {
        Coordinate base = new Coordinate(-1, -2, -3);
        Assert.assertEquals(base, base);
        Assert.assertEquals(base, new Coordinate(-1, -2, -3));
        Assert.assertNotEquals(base, null);
        Assert.assertNotEquals(base, new Coordinate(1, -2, -3));
        Assert.assertNotEquals(base, new Coordinate(-1, 2, -3));
        Assert.assertNotEquals(base, new Coordinate(-1, -2, 3));
        Assert.assertNotEquals(base, new Coordinate(1, 2, 3));
    }
}
