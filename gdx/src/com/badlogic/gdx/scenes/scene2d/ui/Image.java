/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Image extends Widget {
	private final TextureRegion region;
	private final Scaling scaling;
	private float imageX, imageY, imageWidth, imageHeight;

	public Image (TextureRegion region) {
		this(null, region, Scaling.fit);
	}

	public Image (TextureRegion region, Scaling scaling) {
		this(null, region, scaling);
	}

	public Image (String name, TextureRegion region, Scaling scaling) {
		super(name, region.getRegionWidth(), region.getRegionHeight());
		this.region = region;
		this.scaling = scaling;
	}

	public void layout () {
		if (!invalidated) return;
		invalidated = false;
		float regionWidth = region.getRegionWidth();
		float regionHeight = region.getRegionHeight();
		switch (scaling) {
		case fill: {
			float widgetRatio = height / width;
			float regionRatio = regionHeight / regionWidth;
			float scale = regionRatio > widgetRatio ? width / regionWidth : height / regionHeight;
			imageWidth = regionWidth * scale;
			imageHeight = regionHeight * scale;
			imageX = width / 2 - imageWidth / 2;
			imageY = height / 2 - imageHeight / 2;
			break;
		}
		case fit: {
			float widgetRatio = height / width;
			float regionRatio = regionHeight / regionWidth;
			float scale = regionRatio < widgetRatio ? width / regionWidth : height / regionHeight;
			imageWidth = regionWidth * scale;
			imageHeight = regionHeight * scale;
			imageX = width / 2 - imageWidth / 2;
			imageY = height / 2 - imageHeight / 2;
			break;
		}
		case stretch:
			imageX = 0;
			imageY = 0;
			imageWidth = width;
			imageHeight = height;
			break;
		case stretchX:
			imageWidth = width;
			imageHeight = regionHeight;
			imageX = 0;
			imageY = height / 2 - imageHeight / 2;
			break;
		case stretchY:
			imageWidth = regionWidth;
			imageHeight = height;
			imageX = width / 2 - imageWidth / 2;
			imageY = 0;
			break;
		case none:
			imageWidth = regionWidth;
			imageHeight = regionHeight;
			imageX = width / 2 - imageWidth / 2;
			imageY = height / 2 - imageHeight / 2;
			break;
		}
	}

	public void draw (SpriteBatch batch, float parentAlpha) {
		if (invalidated) layout();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, x + imageX, y + imageY, imageWidth, imageHeight);
	}

	public boolean touchDown (float x, float y, int pointer) {
		return false;
	}

	public void touchUp (float x, float y, int pointer) {
	}

	public void touchDragged (float x, float y, int pointer) {
	}

	static public enum Scaling {
		fill, fit, stretch, stretchX, stretchY, none
	}
}