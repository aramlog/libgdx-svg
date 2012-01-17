package com.badlogic.gdx.graphics.g2d.svg.elements;

public class SVGElementCircle extends SVGElement{

	public double cx;
	public double cy;
	public double raio;
	
	public SVGElementCircle() {
		this(0d,0d,0d);
	}
	
	public SVGElementCircle(double cx, double cy, double raio) {
		this("circle", cx, cy, raio);
	}
	
	public SVGElementCircle(String name, double cx, double cy, double raio) {
		super(name);
		this.cx   = cx;
		this.cy   = cy;
		this.raio = raio;
	}
	
	@Override
	public void addAttribute(String attribute, double value){
		if ( "cx".equals(attribute) ){
			cx  = value;
		}else if ( "cy".equals(attribute) ){
			cy = value;
		}else if ( "cy".equals(attribute) ){
			raio = value;
		}
	}
}
