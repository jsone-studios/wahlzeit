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

import com.google.common.base.Objects;

public class SphericCoordinate implements Coordinate {
	private static final double DELTA = 1E-6;

	private double latitude;
	private double longitude;
	private double radius;

	public SphericCoordinate(double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = radius * Math.sin(latitude) * Math.cos(longitude);
		double y = radius * Math.sin(latitude) * Math.sin(longitude);
		double z = radius * Math.cos(latitude);
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	@Override
	public double getDistance(Coordinate other) {
		CartesianCoordinate thisAsCartesian = this.asCartesianCoordinate();
		return thisAsCartesian.getDistance(other);
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

	private boolean isEqual(SphericCoordinate other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		} else return isDoubleEqual(this.getLatitude(), other.getLatitude())
				&& isDoubleEqual(this.getLongitude(), other.getLongitude())
				&& isDoubleEqual(this.getRadius(), other.getRadius());
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		} else if (other instanceof SphericCoordinate) {
			return isEqual((SphericCoordinate) other);
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
		return Objects.hashCode(latitude, longitude, radius);
	}

	@Override
	public String toString() {
		return "SphericCoordinate{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				", radius=" + radius +
				'}';
	}
}
