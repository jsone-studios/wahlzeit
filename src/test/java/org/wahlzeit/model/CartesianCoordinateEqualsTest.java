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
 * Test cases for the {@link CartesianCoordinate} class.
 * To use a parameterized test only the {@link CartesianCoordinate#isEqual(CartesianCoordinate)}
 * and {@link CartesianCoordinate#equals(Object)} method are tested in this test case.
 * All other methods are tested in {@link CartesianCoordinateTest}
 *
 * @see CartesianCoordinateTest
 */
@RunWith(Parameterized.class)
public class CartesianCoordinateEqualsTest {
	private Coordinate a;
	private Coordinate b;
	private boolean isEqual;

	public CartesianCoordinateEqualsTest(Coordinate a, Coordinate b, boolean isEqual) {
		this.a = a;
		this.b = b;
		this.isEqual = isEqual;
	}

	@Parameterized.Parameters(name = "{0} == {1} = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{new CartesianCoordinate(0, 0, 0), null, false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, 0, 0), true},
				{new CartesianCoordinate(0, 1, 0), new CartesianCoordinate(0, 1, 0), true},
				{new CartesianCoordinate(0, 0, 1), new CartesianCoordinate(0, 0, 1), true},
				{new CartesianCoordinate(1, 0, 0), new CartesianCoordinate(1, 0, 0), true},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(1, 0, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, 1, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, 0, 1), false},
				{new CartesianCoordinate(-0, -0, -0), new CartesianCoordinate(-0, -0, -0), true},
				{new CartesianCoordinate(-0, 0, -0), new CartesianCoordinate(0, -0, 0), true},
				{new CartesianCoordinate(0, -0, 0), new CartesianCoordinate(-0, 0, -0), true},

				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(NaN, 0, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, NaN, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, 0, NaN), false},
				{new CartesianCoordinate(NaN, NaN, NaN), new CartesianCoordinate(NaN, NaN, NaN), false},

				{new CartesianCoordinate(POSITIVE_INFINITY, NEGATIVE_INFINITY, POSITIVE_INFINITY), new CartesianCoordinate(POSITIVE_INFINITY, NEGATIVE_INFINITY, POSITIVE_INFINITY), false},
				{new CartesianCoordinate(NEGATIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY), new CartesianCoordinate(NEGATIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(POSITIVE_INFINITY, 0, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(NEGATIVE_INFINITY, 0, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, POSITIVE_INFINITY, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, NEGATIVE_INFINITY, 0), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, 0, POSITIVE_INFINITY), false},
				{new CartesianCoordinate(0, 0, 0), new CartesianCoordinate(0, 0, NEGATIVE_INFINITY), false},

				{new CartesianCoordinate(1.23456789, -9.87654321, 0.000001), new CartesianCoordinate(1.23456789, -9.87654321, 0.000001), true},
				{new CartesianCoordinate(2.64, 0, 0), new CartesianCoordinate(4.64 - 2.0, 0, 0), true},
				{new CartesianCoordinate(410, 0, 0), new CartesianCoordinate(4.10 * 100, 0, 0), true},
				{new CartesianCoordinate(0, 410, 0), new CartesianCoordinate(0, 4.10 * 100, 0), true},
				{new CartesianCoordinate(0, 0, 410), new CartesianCoordinate(0, 0, 4.10 * 100), true},
				{new CartesianCoordinate(0.123456789, 0, 0), new CartesianCoordinate(0.123456999, 0, 0), true},
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
