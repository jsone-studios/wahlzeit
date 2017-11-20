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
 * To use first parameterized test only the {@link CartesianCoordinate#isEqual(CartesianCoordinate)}
 * and {@link CartesianCoordinate#equals(Object)} method are tested in this test case.
 * All other methods are tested in {@link CartesianCoordinateTest}
 *
 * @see CartesianCoordinateTest
 */
@RunWith(Parameterized.class)
public class CoordinateEqualsTest {
	private Coordinate[] first;
	private Coordinate[] second;
	private boolean isEqual;

	public CoordinateEqualsTest(double[] first, double[] second, boolean isEqual) {
		this.first = new Coordinate[]{getCartesianCoordinate(first), getSphericCoordinate(first)};
		this.second = new Coordinate[]{getCartesianCoordinate(second), getSphericCoordinate(second)};
		this.isEqual = isEqual;
	}

	private CartesianCoordinate getCartesianCoordinate(double[] values) {
		if (values == null) {
			return null;
		}
		return new CartesianCoordinate(values[0], values[1], values[2]);
	}

	private SphericCoordinate getSphericCoordinate(double[] values) {
		if (values == null) {
			return null;
		}
		return new SphericCoordinate(values[0], values[1], values[2]);
	}

	@Parameterized.Parameters(name = "{0} == {1} = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{new double[]{0, 0, 0}, null, false},
				{new double[]{0, 0, 0}, new double[]{0, 0, 0}, true},
				{new double[]{0, 1, 0}, new double[]{0, 1, 0}, true},
				{new double[]{0, 0, 1}, new double[]{0, 0, 1}, true},
				{new double[]{1, 0, 0}, new double[]{1, 0, 0}, true},
				{new double[]{0, 0, 0}, new double[]{1, 0, 0}, false},
				{new double[]{0, 0, 0}, new double[]{0, 1, 0}, false},
				{new double[]{0, 0, 0}, new double[]{0, 0, 1}, false},
				{new double[]{-0, -0, -0}, new double[]{-0, -0, -0}, true},
				{new double[]{-0, 0, -0}, new double[]{0, -0, 0}, true},
				{new double[]{0, -0, 0}, new double[]{-0, 0, -0}, true},

				{new double[]{0, 0, 0}, new double[]{NaN, 0, 0}, false},
				{new double[]{0, 0, 0}, new double[]{0, NaN, 0}, false},
				{new double[]{0, 0, 0}, new double[]{0, 0, NaN}, false},
				{new double[]{NaN, NaN, NaN}, new double[]{NaN, NaN, NaN}, false},

				{new double[]{POSITIVE_INFINITY, NEGATIVE_INFINITY, POSITIVE_INFINITY}, new double[]{POSITIVE_INFINITY, NEGATIVE_INFINITY, POSITIVE_INFINITY}, false},
				{new double[]{NEGATIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY}, new double[]{NEGATIVE_INFINITY, POSITIVE_INFINITY, NEGATIVE_INFINITY}, false},
				{new double[]{0, 0, 0}, new double[]{POSITIVE_INFINITY, 0, 0}, false},
				{new double[]{0, 0, 0}, new double[]{NEGATIVE_INFINITY, 0, 0}, false},
				{new double[]{0, 0, 0}, new double[]{0, POSITIVE_INFINITY, 0}, false},
				{new double[]{0, 0, 0}, new double[]{0, NEGATIVE_INFINITY, 0}, false},
				{new double[]{0, 0, 0}, new double[]{0, 0, POSITIVE_INFINITY}, false},
				{new double[]{0, 0, 0}, new double[]{0, 0, NEGATIVE_INFINITY}, false},

				{new double[]{1.23456789, -9.87654321, 0.000001}, new double[]{1.23456789, -9.87654321, 0.000001}, true},
				{new double[]{2.64, 0, 0}, new double[]{4.64 - 2.0, 0, 0}, true},
				{new double[]{410, 0, 0}, new double[]{4.10 * 100, 0, 0}, true},
				{new double[]{0, 410, 0}, new double[]{0, 4.10 * 100, 0}, true},
				{new double[]{0, 0, 410}, new double[]{0, 0, 4.10 * 100}, true},
				{new double[]{0.123456789, 0, 0}, new double[]{0.123456999, 0, 0}, true},
		});
	}

	@Test
	public void testIsEqual() {
		for (int i = 0; i < first.length; i++) {
			assertEquals(isEqual, first[i].isEqual(second[i]));
		}
	}

	@Test
	public void testEquals() {
		for (int i = 0; i < first.length; i++) {
			if (isEqual) {
				Assert.assertEquals(first[i], second[i]);
			} else {
				Assert.assertNotEquals(first[i], second[i]);
			}
		}

	}
}
