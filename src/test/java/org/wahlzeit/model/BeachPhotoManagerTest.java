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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link BeachPhotoManager} class.
 */
public class BeachPhotoManagerTest {

	@Rule
	public TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	private BeachPhotoManager beachPhotoManager;

	private PhotoId photoId;
	private BeachPhoto beachPhoto;

	@Before
	public void setup() {
		this.beachPhotoManager = new BeachPhotoManager();
		this.photoId = new PhotoId(42);
		this.beachPhoto = new BeachPhoto(photoId);
	}

	@Test
	public void testGetPhotoFromNullId() {
		BeachPhoto beachPhoto = beachPhotoManager.getPhotoFromId(null);
		assertNull(beachPhoto);
	}

	@Test
	public void testGetPhotoFromId() {
		beachPhotoManager.photoCache.put(photoId, beachPhoto);

		BeachPhoto testBeachPhoto = beachPhotoManager.getPhotoFromId(photoId);
		assertNotNull(testBeachPhoto);
		assertEquals(beachPhoto, testBeachPhoto);
	}

	@Test
	public void testDoAddPhoto() {
		beachPhotoManager.doAddPhoto(beachPhoto);

		BeachPhoto testBeachPhoto = beachPhotoManager.photoCache.get(photoId);
		assertNotNull(testBeachPhoto);
		assertEquals(beachPhoto, testBeachPhoto);
	}

	@Test
	public void testDoHasPhoto() {
		assertFalse(beachPhotoManager.doHasPhoto(photoId));
		beachPhotoManager.photoCache.put(photoId, beachPhoto);
		assertTrue(beachPhotoManager.doHasPhoto(photoId));
	}


}
