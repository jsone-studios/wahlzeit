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
 * Test cases for the {@link Coordinate} class.
 */
public class CoordinateTest {

	private static final double DELTA = 1E-6;

	@Test
	public void testGetDistance() {
		Coordinate base1 = new Coordinate(0, 0, 0);
		assertEquals(1, base1.getDistance(new Coordinate(1, 0, 0)), DELTA);
		assertEquals(2, base1.getDistance(new Coordinate(0, 2, 0)), DELTA);
		assertEquals(3.5, base1.getDistance(new Coordinate(0, 0, 3.5)), DELTA);

		Coordinate base2 = new Coordinate(1, 1, 1);
		assertEquals(1, base2.getDistance(new Coordinate(0, 1, 1)), DELTA);
		assertEquals(3.5, base2.getDistance(new Coordinate(1, -2.5, 1)), DELTA);
		assertEquals(4, base2.getDistance(new Coordinate(1, 1, -3)), DELTA);

		Coordinate base3 = new Coordinate(-2, 4, -3);
		assertEquals(5.830951, base3.getDistance(new Coordinate(1, 1, 1)), DELTA);
		assertEquals(10.816653, base3.getDistance(new Coordinate(7, 10, -3)), DELTA);
		assertEquals(13.425721, base3.getDistance(new Coordinate(4, -8, -3.5)), DELTA);
	}
}
