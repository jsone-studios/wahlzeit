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

import java.util.Objects;

/**
 * CartesianCoordinate represents cartesian coordinates on earth.
 */
public class CartesianCoordinate implements Coordinate {

	private static final double DELTA = 1E-6;

	private double x;
	private double y;
	private double z;

	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = Math.sqrt(x * x + y * y + z * z);
		if (radius == 0.0) {
			return new SphericCoordinate(0.0, 0.0, 0.0);
		}
		double latitude = Math.acos(z / radius);
		double longitude = Math.atan2(y, x);
		return new SphericCoordinate(latitude, longitude, radius);
	}

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 * Delegates to {@link #getCartesianDistance(Coordinate)}
	 *
	 * @param other Coordinate
	 * @return the direct distance
	 */
	@Override
	public double getDistance(Coordinate other) {
		return getCartesianDistance(other);
	}

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 * If the given coordinate is not a {@link CartesianCoordinate} it is transformed to one.
	 *
	 * @param other Coordinate
	 * @return the direct distance
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		CartesianCoordinate cartesianCoordinate = other.asCartesianCoordinate();
		double dx = this.getX() - cartesianCoordinate.getX();
		double dy = this.getY() - cartesianCoordinate.getY();
		double dz = this.getZ() - cartesianCoordinate.getZ();

		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}

	/**
	 * Calculates the spheric distance between this object and the given coordinate.
	 * This delegates the call to the {@link Coordinate#getSphericDistance(Coordinate)} implementation of the {@link SphericCoordinate}
	 *
	 * @param other Coordinate
	 * @return the spheric distance
	 */
	@Override
	public double getSphericDistance(Coordinate other) {
		SphericCoordinate thisAsSpheric = this.asSphericCoordinate();
		return thisAsSpheric.getSphericDistance(other);
	}

	/**
	 * This compares two double values on equality.
	 * It checks if the doubles are equal within {@link CartesianCoordinate#DELTA}.
	 *
	 * @param a first double value
	 * @param b second double value
	 * @return true if a and b are mathematical equal, false otherwise
	 */
	private boolean isDoubleEqual(double a, double b) {
		return Math.abs(a - b) <= DELTA;
	}

	private boolean isEqual(CartesianCoordinate other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		} else return isDoubleEqual(this.getX(), other.getX())
				&& isDoubleEqual(this.getY(), other.getY())
				&& isDoubleEqual(this.getZ(), other.getZ());
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		} else if (other instanceof CartesianCoordinate) {
			return isEqual((CartesianCoordinate) other);
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return isEqual(other);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public String toString() {
		return "CartesianCoordinate{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}
}
