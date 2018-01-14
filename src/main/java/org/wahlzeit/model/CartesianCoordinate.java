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

import org.wahlzeit.utils.DesignPattern;

import java.util.Objects;

/**
 * CartesianCoordinate represents cartesian coordinates on earth.
 */
@DesignPattern(name = "Template", participants = "Concrete class")
@DesignPattern(name = "Value Object", participants = {})
public class CartesianCoordinate extends AbstractCoordinate {

	private final double x;
	private final double y;
	private final double z;

	CartesianCoordinate(double x, double y, double z) {
		if (isNonValidCartesianCoordinates(x, y, z)) {
			throw new IllegalArgumentException("Given cartesian coordinate is not valid");
		}
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
	protected CartesianCoordinate doAsCartesianCoordinate() {
		return this;
	}

	@Override
	protected SphericCoordinate doAsSphericCoordinate() {
		return getSphericCoordinate(x, y, z);
	}

	@Override
	protected double doGetCartesianDistance(Coordinate other) {
		CartesianCoordinate cartesianCoordinate = other.asCartesianCoordinate();
		double dx = this.x - cartesianCoordinate.x;
		double dy = this.y - cartesianCoordinate.y;
		double dz = this.z - cartesianCoordinate.z;
		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}

	/**
	 * Calculates the spheric distance between this object and the given coordinate.
	 * This delegates the call to the {@link Coordinate#getSphericDistance(Coordinate)} implementation of the {@link SphericCoordinate}
	 *
	 * @param other Coordinate, should not be <code>null</code>
	 * @return the spheric distance
	 */
	@Override
	protected double doGetSphericDistance(Coordinate other) {
		SphericCoordinate thisAsSpheric = this.asSphericCoordinate();
		return thisAsSpheric.getSphericDistance(other);
	}

	@Override
	protected void assertClassInvariants() {
		if (isNonValidCartesianCoordinates(x, y, z))
		{
			throw new InvalidClassInvariantException("Cartesian coordinate does not fulfil the class invariant");
		}
	}

	private static boolean isNonValidCartesianCoordinates(double x, double y, double z) {
		return Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z);
	}

	private SphericCoordinate getSphericCoordinate(double x, double y, double z) {
		double radius = Math.sqrt(x * x + y * y + z * z);
		if (radius == 0.0) {
			return SphericCoordinateHelper.getInstance().getSphericCoordinate(0.0, 0.0, 0.0);
		}
		double latitude = Math.acos(z / radius);
		double longitude = Math.atan2(y, x);
		latitude -= Math.PI/2;
		return SphericCoordinateHelper.getInstance().getSphericCoordinate(latitude, longitude, radius);
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		}
		return this == other.asCartesianCoordinate();
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
