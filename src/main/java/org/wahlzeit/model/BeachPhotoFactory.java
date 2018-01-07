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

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.DesignPattern;

import java.util.logging.Logger;

@DesignPattern(
		name = "Abstract Factory",
		participants = {"ConcreteFactory"}
)
@DesignPattern(name = "Singleton", participants = {})
public class BeachPhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(BeachPhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static BeachPhotoFactory instance = null;

	/**
	 * @methodtype constructor
	 */
	public BeachPhotoFactory() {
		super();
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized BeachPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic BeachPhotoFactory").toString());
			setInstance(new BeachPhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(BeachPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initialize BeachPhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public BeachPhoto createPhoto() {
		return new BeachPhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 * @methodtype factory
	 */
	@Override
	public BeachPhoto createPhoto(PhotoId id) {
		return new BeachPhoto(id);
	}
}
