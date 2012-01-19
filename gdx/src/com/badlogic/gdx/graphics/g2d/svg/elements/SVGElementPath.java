package com.badlogic.gdx.graphics.g2d.svg.elements;

import com.badlogic.gdx.graphics.g2d.svg.SVGPath;
import com.badlogic.gdx.graphics.g2d.svg.SVGStyle;

public class SVGElementPath extends SVGElement{
	
	public SVGPath[] path;

	public SVGElementPath() {
		this(null);
	}
	
	public SVGElementPath(SVGPath[] path) {
		super("path");
		this.path	   = path;
	}
	
	@Override
	public SVGElement deepCopy(){
		SVGElementPath element   = (SVGElementPath) super.deepCopy(new SVGElementPath());
		
		SVGPath[] pathCopy 		 = new SVGPath[path.length];
		System.arraycopy(path, 0, pathCopy, 0, path.length);
		
		element.path 		= pathCopy;
		
		return element;
	}
}
