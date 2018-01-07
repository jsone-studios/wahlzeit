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

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.DesignPattern;

@DesignPattern(
		name = "Abstract Factory",
		participants = {"ConcreteProduct"}
)
@Subclass
public class BeachPhoto extends Photo {
	private String weather;
	private boolean hasSharks;

	/**
	 * @methodtype constructor
	 */
	public BeachPhoto() {
	}

	/**
	 * @methodtype constructor
	 */
	public BeachPhoto(PhotoId myId) {
		super(myId);
	}

	/**
	 * @methodtype get
	 */
	public String getWeather() {
		return weather;
	}

	/**
	 * @methodtype set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}

	public boolean hasSharks() {
		return hasSharks;
	}

	public void setHasSharks(boolean hasSharks) {
		this.hasSharks = hasSharks;
	}
}
