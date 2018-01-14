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

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
public class BeachType extends DataObject {
	@Id
	private final String name;

	@Container
	protected BeachType superType = null;
	@Container
	protected Set<BeachType> subTypes = new HashSet<>();

	public BeachType(String name) {
		this.name = name;
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
	public BeachType getSuperType() {
		return superType;
	}

	public Iterator<BeachType> getSubTypeIterator() {
		return subTypes.iterator();
	}

	public void addSubType(BeachType beachType) {
		if (beachType == null) {
			throw new IllegalArgumentException("Beach sub typ can not be null");
		}
		beachType.superType = this;
		subTypes.add(beachType);
	}

	public boolean hasInstance(Beach beach) {
		if (beach == null) {
			throw new IllegalArgumentException("Beach can not be null");
		}
		if (this.equals(beach.getBeachType())) {
			return true;
		}
		for (BeachType type : subTypes) {
			if (type.hasInstance(beach)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSubType(BeachType beachType) {
		if (beachType == null) {
			throw new IllegalArgumentException("Beach type can not be null");
		}
		for (BeachType subTypes : subTypes) {
			if (beachType.equals(subTypes) || subTypes.isSubType(beachType)) {
				return true;
			}
		}
		return false;
	}

	public Beach createInstance(String name) {
		return new Beach(name, this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BeachType beachType = (BeachType) o;
		return Objects.equals(name, beachType.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
