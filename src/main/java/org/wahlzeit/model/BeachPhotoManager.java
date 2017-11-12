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

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.Persistent;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class BeachPhotoManager extends PhotoManager {

	private static final Logger log = Logger.getLogger(PhotoManager.class.getName());

	/**
	 * In-memory cache for photos
	 */
	protected Map<PhotoId, BeachPhoto> photoCache = new HashMap<>();

	/**
	 * @methodtype constructor
	 */
	public BeachPhotoManager() {
		super();
	}

	/**
	 *
	 */
	@Override
	public BeachPhoto getPhotoFromId(PhotoId id) {
		if (id == null) {
			return null;
		}

		BeachPhoto result = doGetPhotoFromId(id);

		//PhotoFactory#loadPhoto(PhotoId) always returns null, so we can ignore it
		//if (result == null) {
		//	result = BeachPhotoFactory.getInstance().loadPhoto(id);
		//	if (result != null) {
		//		doAddPhoto(result);
		//	}
		//}

		return result;
	}

	/**
	 * @methodtype get
	 * @methodproperties primitive
	 */
	@Override
	protected BeachPhoto doGetPhotoFromId(PhotoId id) {
		return photoCache.get(id);
	}

	/**
	 * @methodtype command
	 * @methodproperties primitive
	 */
	protected void doAddPhoto(BeachPhoto myPhoto) {
		photoCache.put(myPhoto.getId(), myPhoto);
	}

	/**
	 * @methodtype command
	 *
	 * Load all persisted photos. Executed when Wahlzeit is restarted.
	 */
	@Override
	public void loadPhotos() {
		Collection<BeachPhoto> existingPhotos = ObjectifyService.run(new Work<Collection<BeachPhoto>>() {
			@Override
			public Collection<BeachPhoto> run() {
				Collection<BeachPhoto> existingPhotos = new ArrayList<>();
				readObjects(existingPhotos, BeachPhoto.class);
				return existingPhotos;
			}
		});

		for (BeachPhoto photo : existingPhotos) {
			if (!doHasPhoto(photo.getId())) {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Load BeachPhoto with ID", photo.getIdAsString()).toString());
				loadScaledImages(photo);
				doAddPhoto(photo);
			} else {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Already loaded BeachPhoto", photo.getIdAsString()).toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All BeachPhotos loaded.").toString());
	}

	/**
	 * @methodtype boolean-query
	 * @methodproperty primitive
	 */
	protected boolean doHasPhoto(PhotoId id) {
		return photoCache.containsKey(id);
	}

	@Override
	protected void updateDependents(Persistent obj) {
		if (obj instanceof BeachPhoto) {
			BeachPhoto photo = (BeachPhoto) obj;
			saveScaledImages(photo);
			updateTags(photo);
			UserManager userManager = UserManager.getInstance();
			Client owner = userManager.getClientById(photo.getOwnerId());
			userManager.saveClient(owner);
		}
	}

	/**
	 *
	 */
	@Override
	public void savePhotos() throws IOException{
		updateObjects(photoCache.values());
	}

	/**
	 * @methodtype get
	 */
	@Override
	public Map<PhotoId, BeachPhoto> getPhotoCache() {
		return photoCache;
	}

	/**
	 *
	 */
	@Override
	public Set<BeachPhoto> findPhotosByOwner(String ownerName) {
		Set<BeachPhoto> result = new HashSet<>();
		readObjects(result, BeachPhoto.class, Photo.OWNER_ID, ownerName);

		for (BeachPhoto photo : result) {
			doAddPhoto(photo);
		}

		return result;
	}

	/**
	 *
	 */
	@Override
	public BeachPhoto getVisiblePhoto(PhotoFilter filter) {
		filter.generateDisplayablePhotoIds();
		return getPhotoFromId(filter.getRandomDisplayablePhotoId());
	}

	/**
	 *
	 */
	@Override
	public BeachPhoto createPhoto(String filename, Image uploadedImage) throws Exception {
		PhotoId id = PhotoId.getNextId();
		BeachPhoto result = PhotoUtil.createBeachPhoto(filename, id, uploadedImage);
		addPhoto(result);
		return result;
	}

	/**
	 * @methodtype command
	 */
	public void addPhoto(BeachPhoto photo) throws IOException {
		PhotoId id = photo.getId();
		assertIsNewPhoto(id);
		doAddPhoto(photo);

		GlobalsManager.getInstance().saveGlobals();
	}

}
