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

public class SphericCoordinateHelper {
	private static SphericCoordinateHelper instance;

	public synchronized static SphericCoordinateHelper getInstance() {
		if (instance == null) {
			instance = new SphericCoordinateHelper();
		}
		return instance;
	}

	private Map<String, SphericCoordinate> coordinateMap;

	public SphericCoordinateHelper() {
		this.coordinateMap = new HashMap<>();
	}

	public synchronized SphericCoordinate getSphericCoordinate(double latitude, double longitude, double radius) {
		String key = computeKey(latitude, longitude, radius);
		if (coordinateMap.containsKey(key)) {
			return coordinateMap.get(key);
		}
		else {
			SphericCoordinate sphericCoordinate = new SphericCoordinate(latitude, longitude, radius);
			coordinateMap.put(key, sphericCoordinate);
			return sphericCoordinate;
		}
	}

	private String computeKey(double latitude, double longitude, double radius) {
		return Double.toString(latitude) + ',' + Double.toString(longitude) + ',' + Double.toString(radius);
	}
}
