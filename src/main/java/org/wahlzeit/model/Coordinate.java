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
 * Coordinate represents cartesian coordinates on earth.
 */
public class Coordinate {

	private static final double DELTA = 1E-6;

	private double x;
	private double y;
	private double z;

	public Coordinate(double x, double y, double z) {
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

	public double getDistance(Coordinate other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		double dz = this.z - other.z;

		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}

	/**
	 * This compares two double values on equality.
	 * It checks if the doubles are equal within {@link Coordinate#DELTA}.
	 *
	 * @param a first double value
	 * @param b second double value
	 * @return true if a and b are mathematical equal, false otherwise
	 */
	private boolean isDoubleEqual(double a, double b) {
		return Math.abs(a - b) <= DELTA;
	}

	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		} else return isDoubleEqual(this.x, other.x)
				&& isDoubleEqual(this.y, other.y)
				&& isDoubleEqual(this.z, other.z);
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
		return "Coordinate{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}
}
