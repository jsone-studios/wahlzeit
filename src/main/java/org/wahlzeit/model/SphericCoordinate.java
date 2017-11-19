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

import java.util.logging.Logger;

public class SphericCoordinate implements Coordinate {
	private static final double DELTA = 1E-6;
	private static final Logger log = Logger.getLogger(SphericCoordinate.class.getName());

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

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 * Delegates to {@link #getCartesianDistance(Coordinate)} (Coordinate)}
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
	 * This delegates the call to the {@link Coordinate#getCartesianDistance(Coordinate)} implementation of the {@link CartesianCoordinate}
	 *
	 * @param other Coordinate
	 * @return the direct distance
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		CartesianCoordinate thisAsCartesian = this.asCartesianCoordinate();
		return thisAsCartesian.getCartesianDistance(other);
	}

	/**
	 * Calculates the spheric distance between this object and the given coordinate.
	 * If the given coordinate is not a {@link SphericCoordinate} it is transformed to one.
	 *
	 * @param other Coordinate
	 * @return the spheric distance
	 */
	@Override
	public double getSphericDistance(Coordinate other) {
		SphericCoordinate otherAsSpheric = other.asSphericCoordinate();
		double a = Math.cos(this.longitude) * Math.cos(otherAsSpheric.longitude) * Math.cos(this.latitude - this.latitude)
				+ Math.sin(this.longitude) * Math.sin(otherAsSpheric.longitude);
		double distance = Math.acos(a);
		if (Double.compare(this.radius, otherAsSpheric.radius) != 0) {
			log.warning("Can not calculate SphericDistance of two spheric coordinates with different radius!");
		}
		
		return this.radius * distance;
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
