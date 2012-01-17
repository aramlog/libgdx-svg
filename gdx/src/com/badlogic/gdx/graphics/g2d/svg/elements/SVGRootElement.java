package com.badlogic.gdx.graphics.g2d.svg.elements;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

//TODO verificar url e fazer um cache de elementos para a url
public class SVGRootElement extends SVGElement{
	
	public SVGRootElement() {
		super("svg");
	}

	public int width;
	public int height;
	public int format;
	
	public float min_x;
    public float min_y;
    public float max_x;
    public float max_y;

    public float scale;
	
	public long getLongData(){
		return width * height * format;
	}
}
