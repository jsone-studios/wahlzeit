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

public class SphericCoordinateTest {

	private static final double DELTA = 1E-6;

	@Test
	public void testGetSphericDistance() {
		SphericCoordinate base = getSphericCoordinate(0.23, 0.86, 4);
		assertEquals(0.0, base.getSphericDistance(getSphericCoordinate(0.23, 0.86, 4)), DELTA);
		assertEquals(0.0, base.getSphericDistance(getSphericCoordinate(0.23, 0.86, 6)), DELTA);

		assertEquals(2.96, base.getSphericDistance(getSphericCoordinate(-0.4, 0.12, 4)), DELTA);
		assertEquals(2.96, base.getSphericDistance(getSphericCoordinate(-0.4, 0.12, 9)), DELTA);
	}

	@Test
	public void testAsSphericCoordinate() {
		SphericCoordinate base1 = getSphericCoordinate(0.23, 0.84, 1);
		assertEquals(getSphericCoordinate(0.23, 0.84, 1), base1.asSphericCoordinate());
	}

	@Test
	public void testAsCartesianCoordinate() {
		SphericCoordinate spheric = getSphericCoordinate(0.28, 0.37, 5);
		assertEquals(getCartesianCoordinate(1.2882696413450714, 0.4996723361573461, 4.805277191553855), spheric.asCartesianCoordinate());

		spheric = getSphericCoordinate(0.28, -0.37, 5);
		assertEquals(getCartesianCoordinate(1.2882696413450714, -0.4996723361573461, 4.805277191553855), spheric.asCartesianCoordinate());

		spheric = getSphericCoordinate(-0.28, 0.37, 5);
		assertEquals(getCartesianCoordinate(-1.2882696413450714, -0.4996723361573461, 4.805277191553855), spheric.asCartesianCoordinate());

		spheric = getSphericCoordinate(-0.28, -0.37, 5);
		assertEquals(getCartesianCoordinate(-1.2882696413450714, 0.4996723361573461, 4.805277191553855), spheric.asCartesianCoordinate());
	}

	private CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		return CartesianCoordinateHelper.getInstance().getCartesianCoordinate(x, y, z);
	}

	private SphericCoordinate getSphericCoordinate(double latitude, double longitude, double radius) {
		return SphericCoordinateHelper.getInstance().getSphericCoordinate(latitude, longitude, radius);
	}
}
