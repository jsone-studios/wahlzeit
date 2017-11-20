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

public interface Coordinate {
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 *
	 * @param other Coordinate
	 * @return the direct distance, or {@link Double#NaN} if other is <code>null</code
	 */
	double getCartesianDistance(Coordinate other);

	SphericCoordinate asSphericCoordinate();

	/**
	 * Calculates the spheric distance between this object and the given coordinate.
	 *
	 * @param other Coordinate
	 * @return the spheric distance, or {@link Double#NaN} if other is <code>null</code
	 */
	double getSphericDistance(Coordinate other);

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 * This should be the same as {@link Coordinate#getCartesianDistance(Coordinate)}
	 *
	 * @param other Coordinate
	 * @return the direct distance, or {@link Double#NaN} if other is <code>null</code
	 */
	double getDistance(Coordinate other);

	boolean isEqual(Coordinate coordinate);
}
