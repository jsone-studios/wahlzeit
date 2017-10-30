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

/**
 * Test cases for the {@link Location} class.
 */
public class LocationTest {
	private static final double DELTA = 1E-6;

	@Test
	public void testGetCoordinate() {
		Coordinate coordinate = new Coordinate(1, 1, 1);
		Location location = new Location(coordinate);
		Coordinate result = location.getCoordinate();
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.getX(), DELTA);
		Assert.assertEquals(1, result.getY(), DELTA);
		Assert.assertEquals(1, result.getZ(), DELTA);
	}
}
