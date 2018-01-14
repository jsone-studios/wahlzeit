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


import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link BeachManager} class.
 */
public class BeachManagerTest {

	private static final String BEACH_TYPE = "Test beach type";
	private static final String BEACH = "Test beach";

	@Rule
	public TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	private BeachManager beachManager;

	private Closeable ofyCloseable;

	@Before
	public void setup() {
		this.ofyCloseable = ObjectifyService.begin();
		this.beachManager = new BeachManager();
	}

	@After
	public void tearDown() {
		this.ofyCloseable.close();
	}

	@Test
	public void testGetBeachType() {
		BeachType beachType = beachManager.getBeachType(BEACH_TYPE);
		assertNotNull(beachType);
		assertEquals(BEACH_TYPE, beachType.getName());

		BeachType beachType1 = beachManager.getBeachType(BEACH_TYPE);
		assertNotNull(beachType);
		assertEquals(BEACH_TYPE, beachType1.getName());

		assertEquals(beachType, beachType1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetBeachTypeOfNull() {
		beachManager.getBeachType(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetBeachTypeOfEmpty() {
		beachManager.getBeachType("");
	}

	@Test
	public void testGetBeach() {
		Beach beach = beachManager.getBeach(BEACH, BEACH_TYPE);
		assertNotNull(beach);
		assertEquals(BEACH, beach.getName());
		assertEquals(BEACH_TYPE, beach.getBeachType().getName());

		Beach beach1 = beachManager.getBeach(BEACH, BEACH_TYPE);
		assertNotNull(beach1);
		assertEquals(BEACH, beach1.getName());
		assertEquals(BEACH_TYPE, beach1.getBeachType().getName());

		assertEquals(beach, beach1);

		Beach beach2 = beachManager.getBeach(BEACH + "2", BEACH_TYPE);
		assertNotNull(beach2);
		assertEquals(BEACH + "2", beach2.getName());
		assertEquals(BEACH_TYPE, beach2.getBeachType().getName());

		assertNotEquals(beach, beach2);
		assertNotEquals(beach1, beach2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetBeachOfNull() {
		beachManager.getBeach(null, BEACH_TYPE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetBeachOfEmpty() {
		beachManager.getBeach("", BEACH_TYPE);
	}
}
