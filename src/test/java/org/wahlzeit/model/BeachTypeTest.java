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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link BeachTypeTest} class.
 */
public class BeachTypeTest {

	private BeachType superType;
	private BeachType subType;

	@Before
	public void setUp() {
		this.superType = new BeachType("Super");
		this.subType = new BeachType("Sub");
	}

	@Test
	public void testAddSubType() {
		superType.addSubType(subType);

		assertNull(superType.getSuperType());
		assertEquals(superType, subType.getSuperType());

		assertTrue(superType.getSubTypeIterator().hasNext());
		assertEquals(subType, superType.getSubTypeIterator().next());
		assertFalse(subType.getSubTypeIterator().hasNext());
	}

	@Test
	public void testHasInstance1() {
		superType.addSubType(subType);

		Beach beach = new Beach("Test 1", superType);
		assertTrue(superType.hasInstance(beach));
		assertFalse(subType.hasInstance(beach));
	}

	@Test
	public void testHasInstance2() {
		superType.addSubType(subType);

		Beach beach = new Beach("Test 1", subType);
		assertTrue(superType.hasInstance(beach));
		assertTrue(subType.hasInstance(beach));
	}

	@Test
	public void testIsSubType() {
		superType.addSubType(subType);

		assertFalse(superType.isSubType(superType));
		assertTrue(superType.isSubType(subType));
		assertFalse(subType.isSubType(superType));
	}

	@Test
	public void testEquals() {
		BeachType beachType1 = new BeachType("Test");
		BeachType beachType2 = new BeachType("Test");

		assertTrue(beachType1.equals(beachType2));
		assertTrue(beachType2.equals(beachType1));

		assertFalse(superType.equals(subType));
		assertFalse(subType.equals(superType));
	}
}
