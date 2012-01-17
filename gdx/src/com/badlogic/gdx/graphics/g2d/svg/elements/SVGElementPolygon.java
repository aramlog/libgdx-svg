package com.badlogic.gdx.graphics.g2d.svg.elements;

/**
 * paths deve ser múltiplo de 2
 * 
 * @author Robson
 *
 */
public class SVGElementPolygon extends SVGElement{
	
	public double paths[];

	public SVGElementPolygon() {
		this(null);
	}
	
	public SVGElementPolygon(double paths[]) {
		super("polygon");
		this.paths = paths;
	}
}
