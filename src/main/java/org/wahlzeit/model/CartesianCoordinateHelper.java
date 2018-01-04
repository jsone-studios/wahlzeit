/*
 * Copyright (c) 2018
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

import java.util.HashMap;
import java.util.Map;

public class CartesianCoordinateHelper {

	private static CartesianCoordinateHelper instance;

	public synchronized static CartesianCoordinateHelper getInstance() {
		if (instance == null) {
			instance = new CartesianCoordinateHelper();
		}
		return instance;
	}

	private Map<String, CartesianCoordinate> coordinateMap;

	public CartesianCoordinateHelper() {
		this.coordinateMap = new HashMap<>();
	}

	public synchronized CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		String key = computeKey(x, y, z);
		if (coordinateMap.containsKey(key)) {
			return coordinateMap.get(key);
		}
		else {
			CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);
			coordinateMap.put(key, cartesianCoordinate);
			return cartesianCoordinate;
		}
	}

	private String computeKey(double x, double y, double z) {
		return Double.toString(x) + ',' + Double.toString(y) + ',' + Double.toString(z);
	}
}
