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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.GdxRuntimeException;

/** A list of string entries.
 * 
 * <h2>Functionality</h2> A list displays textual entries and highlights the current selection. A {@link SelectionListener} can be
 * registered with the list to listen to selection changes. Entries have an index in the list, the top entry having the index 0.
 * 
 * <h2>Layout</h2> The (preferred) width and height of a List are derrived from the bounding box around all list entries. Use
 * {@link #setPrefSize(int, int)} to programmatically change the size to your liking. In case the width and height you set are to
 * small for the contained text you will see artifacts. The patch highlighting the current selection will have the width of the
 * List, either determined as explained above or set programmatically.
 * 
 * <h2>Style</h2> A List is a {@link Widget} a text rendered for each list entry via a {@link BitmapFont} and {@link Color} as
 * well as a {@link NinePatch} highlighting the current selection and a second Color used for the text of the currently selected
 * entry. The highlighting NinePatch is rendered beneath the selected entry. The style is defined via an instance of
 * {@link ListStyle}, which can be done either programmatically or via a {@link Skin}.</p>
 * 
 * A List's style definition in an XML skin file should look like this:
 * 
 * <pre>
 * {@code 
 * <list name="styleName"
 *       font="fontName"
 *       fontColorUnselected="colorName"
 *       fontColorSelected="colorName" 
 *       selected="selectedPatch"/>
 * }
 * </pre>
 * 
 * <ul>
 * <li>The <code>name</code> attribute defines the name of the style which you can later use with
 * {@link Skin#newList(String, String[], String)}.</li>
 * <li>The <code>fontName</code> attribute references a {@link BitmapFont} by name, to be used for render the entries</li>
 * <li>The <code>fontColorUnselected</code> attribute references a {@link Color} by name, to be used for render unselected entries
 * </li>
 * <li>The <code>fontColorSelected</code> attribute references a {@link Color} by name, to be used to render the selected entry</li>
 * <li>The <code>selected</code> attribute references a {@link NinePatch} by name, to be used to render the highlight behind the
 * selected entry</li>
 * </ul>
 * 
 * @author mzechner */
public class List extends Widget {
	ListStyle style;
	String[] entries;
	boolean invalidated = false;
	float entryHeight = 0;
	float textOffsetX = 0;
	float textOffsetY = 0;
	int selected = 0;
	SelectionListener listener;

	public List (String[] entries, Skin skin) {
		this(null, entries, skin.getStyle(ListStyle.class));
	}

	public List (String[] entries, ListStyle style) {
		this(null, entries, style);
	}

	/** Creates a new List. The width and height is determined from the bounding box around all entries.
	 * @param name the name
	 * @param entries the entries
	 * @param style the {@link ListStyle} */
	public List (String name, String[] entries, ListStyle style) {
		super(name, 0, 0);
		setStyle(style);
		this.entries = entries;
		layout();
		this.width = prefWidth;
		this.height = prefHeight;
	}
	
	/**
	 * Sets the style of this widget. Calls {@link #invalidateHierarchy()} internally.
	 * @param style
	 */
	public void setStyle (ListStyle style) {
		this.style = style;
		invalidateHierarchy();
	}

	@Override
	public void layout () {
		final BitmapFont font = style.font;
		final NinePatch selectedPatch = style.selectedPatch;
		prefWidth = 0;
		prefHeight = 0;

		for (int i = 0; i < entries.length; i++) {
			String entry = entries[i];
			TextBounds bounds = font.getBounds(entry);
			prefWidth = Math.max(bounds.width, prefWidth);

		}

		entryHeight = font.getCapHeight() - font.getDescent() * 2;
		entryHeight += selectedPatch.getTopHeight() + selectedPatch.getBottomHeight();
		prefWidth += selectedPatch.getLeftWidth() + selectedPatch.getRightWidth();
		prefHeight = entries.length * entryHeight;
		textOffsetX = selectedPatch.getLeftWidth();
		textOffsetY = selectedPatch.getTopHeight() - font.getDescent();
		invalidated = false;
	}

	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		final BitmapFont font = style.font;
		final NinePatch selectedPatch = style.selectedPatch;
		final Color fontColorSelected = style.fontColorSelected;
		final Color fontColorUnselected = style.fontColorUnselected;

		if (invalidated) layout();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

		float posY = height;
		for (int i = 0; i < entries.length; i++) {
			if (selected == i) {
				selectedPatch.draw(batch, x, y + posY - entryHeight, Math.max(prefWidth, width), entryHeight);
				font.setColor(fontColorSelected.r, fontColorSelected.g, fontColorSelected.b, fontColorSelected.a * parentAlpha);
			} else {
				font.setColor(fontColorUnselected.r, fontColorUnselected.g, fontColorUnselected.b, fontColorUnselected.a
					* parentAlpha);
			}
			font.draw(batch, entries[i], x + textOffsetX, y + posY - textOffsetY);
			posY -= entryHeight;
		}
	}

	@Override
	public boolean touchDown (float x, float y, int pointer) {
		if (pointer != 0) return false;
		selected = (int)((height - y) / entryHeight);
		selected = Math.max(0, selected);
		selected = Math.min(entries.length - 1, selected);
		if (listener != null) listener.selected(this, selected, entries[selected]);
		return true;
	}

	@Override
	public void touchUp (float x, float y, int pointer) {
	}

	@Override
	public void touchDragged (float x, float y, int pointer) {
	}

	@Override
	public Actor hit (float x, float y) {
		return x >= 0 && x < width && y >= 0 && y < height ? this : null;
	}

	/** Defines a list style, see {@link List}
	 * @author mzechner */
	public static class ListStyle {
		public BitmapFont font;
		public Color fontColorSelected = new Color(1, 1, 1, 1);
		public Color fontColorUnselected = new Color(1, 1, 1, 1);
		public NinePatch selectedPatch;

		private ListStyle () {
		}

		public ListStyle (BitmapFont font, Color fontColorSelected, Color fontColorUnselected, NinePatch selectedPatch) {
			this.font = font;
			this.fontColorSelected.set(fontColorSelected);
			this.fontColorUnselected.set(this.fontColorUnselected);
			this.selectedPatch = selectedPatch;
		}
	}

	/** Interface for listening to selection changes.
	 * @author mzechner */
	public interface SelectionListener {
		public void selected (List list, int selectedIndex, String selection);
	}

	/** @return the index of the currently selected entry. The top entry has an index of 0. */
	public int getSelectedIndex () {
		return selected;
	}

	public void setSelectedIndex (int index) {
		selected = index;
	}

	/** @return the text of the currently selected entry or null if the list is empty*/
	public String getSelection () {
		if(entries.length == 0) return null;
		return entries[selected];
	}
	
	/** @param index sets the selected item */
	public void setSelection (int index) {
		if (index < 0 || index >= entries.length) throw new GdxRuntimeException("Index must be > 0 and < #entries");
		selected = index;
		invalidateHierarchy();
	}

	public int setSelection (String entry) {
		selected = -1;
		for (int i = 0, n = entries.length; i < n; i++) {
			if (entries[i].equals(entry)) {
				selected = i;
				break;
			}
		}
		return selected;
	}

	/** Sets the entries of this list. Invalidates all parents.
	 * @param entries the entries. */
	public void setEntries (String[] entries) {
		if (entries == null) throw new IllegalArgumentException("entries must not be null");
		this.entries = entries;
		selected = 0;
		invalidateHierarchy();
	}

	public String[] getEntries () {
		return entries;
	}

	/** Sets the {@link SelectionListener} of this list.
	 * @param listener the listener or null */
	public void setSelectionListener (SelectionListener listener) {
		this.listener = listener;
	}
}
