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

/**
 * SphericCoordinate represents spheric coordinates on earth.
 */
public class SphericCoordinate extends AbstractCoordinate {

	private static final Logger log = Logger.getLogger(SphericCoordinate.class.getName());

	private final double latitude;
	private final double longitude;
	private final double radius;

	private final CartesianCoordinate cartesianCoordinate;

	public SphericCoordinate(double latitude, double longitude, double radius) {
		if (isNonValidSphericCoordinate(latitude, longitude, radius)) {
			throw new IllegalArgumentException("Given spheric coordinate is not valid");
		}
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.cartesianCoordinate = getCartesianCoordinate(latitude, longitude, radius);
	}

	protected SphericCoordinate(double latitude, double longitude, double radius, CartesianCoordinate cartesianCoordinate) {
		if (isNonValidSphericCoordinate(latitude, longitude, radius)) {
			throw new IllegalArgumentException("Given spheric coordinate is not valid");
		}
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.cartesianCoordinate = cartesianCoordinate;
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
	protected CartesianCoordinate doAsCartesianCoordinate() {
		return this.cartesianCoordinate;
	}

	@Override
	protected SphericCoordinate doAsSphericCoordinate() {
		return this;
	}

	/**
	 * Calculates the direct distance between this object and the given coordinate.
	 * This delegates the call to the {@link Coordinate#getCartesianDistance(Coordinate)} implementation of the {@link CartesianCoordinate}
	 *
	 * @param other Coordinate, should not be <code>null</code>
	 * @return the direct distance
	 */
	@Override
	protected double doGetCartesianDistance(Coordinate other) {
		CartesianCoordinate thisAsCartesian = this.asCartesianCoordinate();
		return thisAsCartesian.getCartesianDistance(other);
	}

	@Override
	protected double doGetSphericDistance(Coordinate other) {
		SphericCoordinate otherAsSpheric = other.asSphericCoordinate();
		double a = Math.cos(this.longitude) * Math.cos(otherAsSpheric.longitude) * Math.cos(this.latitude - this.latitude)
				+ Math.sin(this.longitude) * Math.sin(otherAsSpheric.longitude);
		double distance = Math.acos(a) * this.radius;
		if (Double.compare(this.radius, otherAsSpheric.radius) != 0) {
			log.warning("Can not calculate SphericDistance of two spheric coordinates with different radius!");
		}
		return distance;
	}

	@Override
	protected void assertClassInvariants() {
		if (isNonValidSphericCoordinate(latitude, longitude, radius))
		{
			throw new InvalidClassInvariantException("Cartesian coordinate does not fulfil the class invariant");
		}
	}

	private static boolean isNonValidSphericCoordinate(double latitude, double longitude, double radius) {
		boolean isValidLatitude = -(Math.PI/2) <= latitude && latitude <= Math.PI/2;
		boolean isValidLongitude = -Math.PI <= longitude && longitude <= Math.PI;
		boolean isValidRadius = radius >= 0;
		return !isValidLatitude || !isValidLongitude || !isValidRadius;
	}

	private CartesianCoordinate getCartesianCoordinate(double latitude, double longitude, double radius)
	{
		double x = radius * Math.sin(latitude) * Math.cos(longitude);
		double y = radius * Math.sin(latitude) * Math.sin(longitude);
		double z = radius * Math.cos(latitude);
		return new CartesianCoordinate(x, y, z, this);
	}

	private boolean isEqual(SphericCoordinate other) {
		return this == other
				|| (isDoubleEqual(this.latitude, other.latitude)
				&& isDoubleEqual(this.longitude, other.longitude)
				&& isDoubleEqual(this.radius, other.radius));
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if (this == other) {
			return true;
		} else if (other == null) {
			return false;
		}
		//else if (other instanceof SphericCoordinate) {
		//	return isEqual((SphericCoordinate) other);
		//}
		SphericCoordinate otherAsSpheric = other.asSphericCoordinate();
		return isEqual(otherAsSpheric);
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
