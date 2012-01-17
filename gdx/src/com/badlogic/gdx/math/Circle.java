/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.math;

import java.io.Serializable;

/** A convenient 2D circle class.
 * @author mzechner */
public class Circle implements Serializable, IShape {
	public float x, y;
	private float radius;
	private float orgRadius;

	public Circle (float x, float y, float radius) {
		this.x 			= x;
		this.y 			= y;
		this.radius 	= radius;
		this.orgRadius	= radius;
	}

	public Circle (Vector2 position, float radius) {
		this.x 			= position.x;
		this.y 			= position.y;
		this.radius 	= radius;
		this.orgRadius	= radius;
	}
	
	public void setScale(float scale){
		radius = orgRadius * scale;
	}
	
	public boolean contains(float orgX, float orgY, float orgRad) {
		orgX 		= Math.abs((orgX+orgRad) - ( x + radius ) );
		orgY 		= Math.abs((orgY+orgRad) - ( y + radius ) );
		orgRad   	= radius + orgRad;
		
		return (orgX*orgX + orgY*orgY) <= ( orgRad * orgRad );
	}
	
	public boolean contains(Circle circle) {
		return contains(circle.x, circle.y, circle.radius);
	}
	
	@Override
	public float getRadius(){
		return radius;
	}
	
	public boolean pointInCircle (float x, float y) {
		return contains( x, y-1, 1);
	}
	
	@Override
	public boolean isPointInShape(float x, float y) {
		return pointInCircle(x, y);
	}
	
	public void set(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public float getWidth() {
		return radius*2;
	}

	@Override
	public float getHeight() {
		return radius*2;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setX(float x) {
		this.x=x;
	}

	@Override
	public void setY(float y) {
		this.y=y;
	}

}
