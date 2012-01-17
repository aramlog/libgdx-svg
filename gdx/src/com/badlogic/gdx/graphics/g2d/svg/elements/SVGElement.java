package com.badlogic.gdx.graphics.g2d.svg.elements;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.svg.SVGStyle;
import com.badlogic.gdx.graphics.g2d.svg.SVGTransform;
import com.badlogic.gdx.utils.Array;

public class SVGElement {
	
	private String 	 name;
	private String 	 id;
	private SVGStyle[] style; 
	private SVGTransform[] transforms; 
	private StringBuilder styleInline;
	
	private ArrayList<SVGElement> child;
	
	public SVGElement(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SVGStyle[] getStyle() {
		return style;
	}

	public void setStyle(SVGStyle[] style) {
		this.style = style;
	}

	public SVGTransform[] getTransforms() {
		return transforms;
	}

	public void setTransforms(SVGTransform[] transforms) {
		this.transforms = transforms;
	}

	public ArrayList<SVGElement> getChild() {
		return child;
	}
	
	public void addStyleInLine(String key, String value){
		if ( styleInline == null ){
			styleInline = new StringBuilder();
		}
		styleInline.append(key).append(":").append(value);
	}
	
	public String getStyleInLine(){
		return styleInline != null ? styleInline.toString() : null;
	}
	
	public void addChild(SVGElement element) {
		if ( child == null ){
			child = new ArrayList<SVGElement>();
		}
		child.add(element);
	}
	
	public void addAttribute(String attribute, double value){
		
	}
	
	@Override
	public boolean equals(Object value){
		return name.equals(value);
	}

}
