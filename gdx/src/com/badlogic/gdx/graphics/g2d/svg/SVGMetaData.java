package com.badlogic.gdx.graphics.g2d.svg;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElement;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGRootElement;

public class SVGMetaData {
	
	public SVGMetaData(SVGRootElement rootElement){
		parse(rootElement);
		end();
	}
	
	private void parse(SVGElement element){
		
		//Styles inLine
		String styleInLine = element.getStyleInLine();
		
		if ( styleInLine != null ){
				SVGStyle[] stylesInLine = ParseUtils.extractStyleProperty(styleInLine.toString());
				
				if ( stylesInLine.length > 0){
					
					if ( element.getStyle() != null ){
						
						element.setStyle(ParseUtils.concatenateStyle(stylesInLine, element.getStyle()));
						
					}else{
						
						element.setStyle(stylesInLine);
					
					}
				}
		}
		
		ArrayList<SVGElement> childs = element.getChild();
		
		if ( childs != null ){
			int lenght = childs.size();
			
			for ( int x=0; x<lenght; x++ ){
				SVGElement child = childs.get(x); 
				
				if ( element.getStyle() != null && element.getStyle().length > 0 ){
					child.setStyle(ParseUtils.concatenateStyle(element.getStyle(), child.getStyle()));
				}
				
				if ( element.getTransforms() != null && element.getTransforms().length > 0 ){
					child.setTransforms(concatenateTransform(element.getTransforms(), child.getTransforms()));
				}
				
				parse(child);
			}
		}
		
		next(element);
	}
	
	
	public void next(SVGElement element){
	}
	
	public void end(){
		
	}
	
	private SVGTransform[] concatenateTransform(SVGTransform[] transform1, SVGTransform[] transform2) {
		
		if ( transform1 == null || transform1.length == 0 ){
			return transform2;
		}else if ( transform2 == null || transform2.length == 0 ){
			return transform1;
		}
		
		int trans1Lenght = transform1.length;
		int trans2Lenght = transform2.length;
		
		SVGTransform[] ret = new SVGTransform[trans1Lenght + trans2Lenght];
		
		System.arraycopy(transform1, 0, ret, 0, trans1Lenght);
		System.arraycopy(transform2, 0, ret, trans1Lenght, trans2Lenght);
		
		return ret;
	}
	
}
