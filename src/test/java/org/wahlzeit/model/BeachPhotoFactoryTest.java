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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

/**
 * Test cases for the {@link BeachPhotoFactory} class.
 */
public class BeachPhotoFactoryTest {

	@Rule
	public TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider());

	private BeachPhotoFactory beachPhotoFactory;

	@Before
	public void setup() {
		this.beachPhotoFactory = new BeachPhotoFactory();
	}

	@Test
	public void testCreatePhoto() {
		BeachPhoto beachPhoto = beachPhotoFactory.createPhoto();
		Assert.assertNotNull(beachPhoto);
	}

	@Test
	public void testCreatePhotoWithId() {
		PhotoId photoId = new PhotoId(42);
		BeachPhoto beachPhoto = beachPhotoFactory.createPhoto(photoId);
		Assert.assertNotNull(beachPhoto);
		Assert.assertEquals(photoId, beachPhoto.getId());
	}
}
