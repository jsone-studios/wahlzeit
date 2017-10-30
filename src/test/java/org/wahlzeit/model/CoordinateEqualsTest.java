/*
 * Copyright (c) 2017
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.Double.*;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link Coordinate} class.
 * To use a parameterized test only the {@link Coordinate#isEqual(Coordinate)}
 * and {@link Coordinate#equals(Object)} method are tested in this test case.
 * All other methods are tested in {@link CoordinateTest}
 *
 * @see CoordinateTest
 */
@RunWith(Parameterized.class)
public class CoordinateEqualsTest {
	private Coordinate a;
	private Coordinate b;
	private boolean isEqual;

	public CoordinateEqualsTest(Coordinate a, Coordinate b, boolean isEqual) {
		this.a = a;
		this.b = b;
		this.isEqual = isEqual;
	}

	@Parameterized.Parameters(name = "{0} == {1} = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{new Coordinate(0, 0, 0), null, false},
				{new Coordinate(0, 0, 0), new Coordinate(0, 0, 0), true},
				{new Coordinate(0, 1, 0), new Coordinate(0, 1, 0), true},
				{new Coordinate(0, 0, 1), new Coordinate(0, 0, 1), true},
				{new Coordinate(1, 0, 0), new Coordinate(1, 0, 0), true},
				{new Coordinate(0, 0, 0), new Coordinate(1, 0, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, 1, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, 0, 1), false},
				{new Coordinate(-0, -0, -0), new Coordinate(-0, -0, -0), true},
				{new Coordinate(-0, 0, -0), new Coordinate(0, -0, 0), true},
				{new Coordinate(0, -0, 0), new Coordinate(-0, 0, -0), true},

				{new Coordinate(0, 0, 0), new Coordinate(NaN, 0, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, NaN, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, 0, NaN), false},
				{new Coordinate(NaN, NaN, NaN), new Coordinate(NaN, NaN, NaN), false},

				{new Coordinate(POSITIVE_INFINITY, NEGATIVE_INFINITY, POSITIVE_INFINITY), new Coordinate(POSITIVE_INFINITY, NEGATIVE_INFINITY, POSITIVE_INFINITY), false},
				{new Coordinate(NEGATIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY), new Coordinate(NEGATIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY), false},
				{new Coordinate(0, 0, 0), new Coordinate(POSITIVE_INFINITY, 0, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(NEGATIVE_INFINITY, 0, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, POSITIVE_INFINITY, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, NEGATIVE_INFINITY, 0), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, 0, POSITIVE_INFINITY), false},
				{new Coordinate(0, 0, 0), new Coordinate(0, 0, NEGATIVE_INFINITY), false},

				{new Coordinate(1.23456789, -9.87654321, 0.000001), new Coordinate(1.23456789, -9.87654321, 0.000001), true},
				{new Coordinate(2.64, 0, 0), new Coordinate(4.64 - 2.0, 0, 0), true},
				{new Coordinate(410, 0, 0), new Coordinate(4.10 * 100, 0, 0), true},
				{new Coordinate(0, 410, 0), new Coordinate(0, 4.10 * 100, 0), true},
				{new Coordinate(0, 0, 410), new Coordinate(0, 0, 4.10 * 100), true},
				{new Coordinate(0.123456789, 0, 0), new Coordinate(0.123456999, 0, 0), true},
		});
	}

	@Test
	public void testIsEqual() {
		assertEquals(isEqual, a.isEqual(b));
	}

	@Test
	public void testEquals() {
		if (isEqual)
			Assert.assertEquals(a, b);
		else
			Assert.assertNotEquals(a, b);
	}
}
