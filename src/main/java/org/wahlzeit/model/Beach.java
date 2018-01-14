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

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.wahlzeit.services.DataObject;

@Entity
public class Beach extends DataObject {

	@Id
	private final String name;

	private boolean hasSharks;

	private final BeachType beachType;

	public Beach(String name, BeachType beachType) {
		this.name = name;
		this.beachType = beachType;
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype get
	 */
	public BeachType getBeachType() {
		return beachType;
	}

	/**
	 * @methodtype get
	 */
	public boolean hasSharks() {
		return hasSharks;
	}

	/**
	 * @methodtype set
	 */
	public void setHasSharks(boolean hasSharks) {
		this.hasSharks = hasSharks;
	}
}
