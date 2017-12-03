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

/**
 * AbstractCoordinate is an abstraction  between {@link Coordinate} interface and its implementation
 */
public abstract class AbstractCoordinate implements Coordinate {

	private static final double DELTA = 1E-6;

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariants();

		CartesianCoordinate coordinate = doAsCartesianCoordinate();

		assert coordinate != null;
		assertClassInvariants();
		return coordinate;
	}

	protected abstract CartesianCoordinate doAsCartesianCoordinate();

	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();

		SphericCoordinate coordinate = doAsSphericCoordinate();

		assert coordinate != null;
		assertClassInvariants();
		return coordinate;
	}

	protected abstract SphericCoordinate doAsSphericCoordinate();

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 * Delegates to {@link #doGetCartesianDistance(Coordinate)}
	 *
	 * @param other Coordinate
	 * @return the direct distance
	 */
	@Override
	public double getDistance(Coordinate other) {
		assertClassInvariants();
		assertArgumentNotNull(other);

		double distance = doGetCartesianDistance(other);

		assert distance >= 0;
		assertClassInvariants();
		return distance;
	}

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 *
	 * @param other Coordinate, should not be <code>null</code>
	 * @return the direct distance
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		assertClassInvariants();
		assertArgumentNotNull(other);

		double distance = this.doGetCartesianDistance(other);

		assert distance >= 0;
		assertClassInvariants();
		return distance;
	}

	protected abstract double doGetCartesianDistance(Coordinate other);

	/**
	 * Calculates the spheric distance between this object and the given coordinate.
	 *
	 * @param other Coordinate, should not be <code>null</code>
	 * @return the spheric distance
	 */
	@Override
	public double getSphericDistance(Coordinate other) {
		assertClassInvariants();
		assertArgumentNotNull(other);

		double distance = this.doGetSphericDistance(other);

		assert distance >= 0;
		assertClassInvariants();
		return distance;
	}

	protected abstract double doGetSphericDistance(Coordinate other);

	@Override
	public boolean isEqual(Coordinate coordinate) {
		return false;
	}

	/**
	 * This compares two double values on equality.
	 * It checks if the doubles are equal within {@link CartesianCoordinate#DELTA}.
	 *
	 * @param a first double value
	 * @param b second double value
	 * @return true if a and b are mathematical equal, false otherwise
	 */
	protected boolean isDoubleEqual(double a, double b) {
		return Math.abs(a - b) <= DELTA;
	}

	protected void assertArgumentNotNull(Object object)
	{
		if (object == null)
		{
			throw new IllegalArgumentException("null is not a valid method argument");
		}
	}

	protected void assertClassInvariants() {}

	@Override
	public abstract int hashCode();

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Coordinate) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}
}
