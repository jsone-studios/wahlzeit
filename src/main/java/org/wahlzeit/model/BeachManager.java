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

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.utils.DesignPattern;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@DesignPattern(name = "Singleton", participants = {})
public class BeachManager extends ObjectManager {

	private static final Logger log = Logger.getLogger(BeachManager.class.getName());

	private static BeachManager instance = new BeachManager();

	private Map<String, BeachType> beachTypeMap = new HashMap<>();

	private Map<String, Beach> beachMap = new HashMap<>();

	protected BeachManager() {
	}

	public static BeachManager getInstance() {
		return instance;
	}

	public Beach getBeach(String beachName, String typeName) {
		assertIsValidBeachType(typeName);
		assertIsValidBeachName(beachName);

		if (beachMap.containsKey(beachName)) {
			return beachMap.get(beachName);
		}

		Beach beach = readObject(Beach.class, beachName);
		if (beach == null) {
			BeachType beachType = getBeachType(typeName);
			beach = beachType.createInstance(beachName);
		}

		beachMap.put(beachName, beach);
		return beach;
	}

	protected BeachType getBeachType(String type) {
		assertIsValidBeachType(type);

		if (beachTypeMap.containsKey(type)) {
			return beachTypeMap.get(type);
		}
		BeachType beachType = readObject(BeachType.class, type);
		if (beachType == null) {
			beachType = new BeachType(type);
			writeObject(beachType);
		}
		beachTypeMap.put(type, beachType);
		return beachType;
	}

	private static void assertIsValidBeachType(String type) {
		if (type == null || type.length() == 0) {
			throw new IllegalArgumentException("BeachType can not be empty");
		}
	}

	private static void assertIsValidBeachName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Beach name can not be empty");
		}
	}
}
