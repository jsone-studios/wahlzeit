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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link CartesianCoordinate} class.
 */
public class CartesianCoordinateTest {

	private static final double DELTA = 1E-6;

	@Test
	public void testGetCartesianDistance() {
		CartesianCoordinate base1 = getCartesianCoordinate(0, 0, 0);
		assertEquals(1, base1.getCartesianDistance(getCartesianCoordinate(1, 0, 0)), DELTA);
		assertEquals(2, base1.getCartesianDistance(getCartesianCoordinate(0, 2, 0)), DELTA);
		assertEquals(3.5, base1.getCartesianDistance(getCartesianCoordinate(0, 0, 3.5)), DELTA);

		CartesianCoordinate base2 = getCartesianCoordinate(1, 1, 1);
		assertEquals(1, base2.getCartesianDistance(getCartesianCoordinate(0, 1, 1)), DELTA);
		assertEquals(3.5, base2.getCartesianDistance(getCartesianCoordinate(1, -2.5, 1)), DELTA);
		assertEquals(4, base2.getCartesianDistance(getCartesianCoordinate(1, 1, -3)), DELTA);

		CartesianCoordinate base3 = getCartesianCoordinate(-2, 4, -3);
		assertEquals(5.830951, base3.getCartesianDistance(getCartesianCoordinate(1, 1, 1)), DELTA);
		assertEquals(10.816653, base3.getCartesianDistance(getCartesianCoordinate(7, 10, -3)), DELTA);
		assertEquals(13.425721, base3.getCartesianDistance(getCartesianCoordinate(4, -8, -3.5)), DELTA);
	}

	@Test
	public void testAsCartesianCoordinate() {
		CartesianCoordinate base1 = getCartesianCoordinate(1, 2, 3);
		assertEquals(getCartesianCoordinate(1, 2, 3), base1.asCartesianCoordinate());
	}

	@Test
	public void testAsSphericCoordinate() {
		CartesianCoordinate cartesianCoordinate = getCartesianCoordinate(1, 2, 3);
		assertEquals(getSphericCoordinate(-0.9302740141154721,1.1071487177940904,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());

		cartesianCoordinate = getCartesianCoordinate(1, 2, -3);
		assertEquals(getSphericCoordinate(0.9302740141154722,1.1071487177940904,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());

		cartesianCoordinate = getCartesianCoordinate(1, -2, 3);
		assertEquals(getSphericCoordinate(-0.9302740141154721,-1.1071487177940904,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());

		cartesianCoordinate = getCartesianCoordinate(1, -2, -3);
		assertEquals(getSphericCoordinate(0.9302740141154722,-1.1071487177940904,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());

		cartesianCoordinate = getCartesianCoordinate(-1, 2, 3);
		assertEquals(getSphericCoordinate(-0.9302740141154721,2.0344439357957027,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());

		cartesianCoordinate = getCartesianCoordinate(-1, 2, -3);
		assertEquals(getSphericCoordinate(0.9302740141154722,2.0344439357957027,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());

		cartesianCoordinate = getCartesianCoordinate(-1, -2, 3);
		assertEquals(getSphericCoordinate(-0.9302740141154721,-2.0344439357957027,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());

		cartesianCoordinate = getCartesianCoordinate(-1, -2, -3);
		assertEquals(getSphericCoordinate(0.9302740141154722,-2.0344439357957027,3.7416573867739413), cartesianCoordinate.asSphericCoordinate());
	}
	
	private CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		//return new CartesianCoordinate(x, y, z);
		return CartesianCoordinateHelper.getInstance().getCartesianCoordinate(x, y, z);
	}

	private SphericCoordinate getSphericCoordinate(double latitude, double longitude, double radius) {
		//return new SphericCoordinate(latitude, longitude, radius);
		return SphericCoordinateHelper.getInstance().getSphericCoordinate(latitude, longitude, radius);
	}
}
