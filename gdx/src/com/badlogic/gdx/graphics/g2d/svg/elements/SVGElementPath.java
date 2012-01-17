package com.badlogic.gdx.graphics.g2d.svg.elements;

import com.badlogic.gdx.graphics.g2d.svg.SVGPath;

public class SVGElementPath extends SVGElement{
	
	public SVGPath[] path;

	public SVGElementPath() {
		this(' ',null);
	}
	
	public SVGElementPath(char attribute, SVGPath[] path) {
		super("path");
		this.path	   = path;
	}
}
