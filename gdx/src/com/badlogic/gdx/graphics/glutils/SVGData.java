package com.badlogic.gdx.graphics.glutils;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.svg.ParseUtils;
import com.badlogic.gdx.graphics.g2d.svg.SVGConstants;
import com.badlogic.gdx.graphics.g2d.svg.SVGMetaData;
import com.badlogic.gdx.graphics.g2d.svg.SVGParse;
import com.badlogic.gdx.graphics.g2d.svg.SVGPath;
import com.badlogic.gdx.graphics.g2d.svg.SVGStyle;
import com.badlogic.gdx.graphics.g2d.svg.SVGTransform;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElement;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElementCircle;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElementEllipse;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElementLine;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElementPath;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElementPolyLine;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElementPolygon;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGElementRect;
import com.badlogic.gdx.graphics.g2d.svg.elements.SVGRootElement;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;

public class SVGData implements Disposable, SVGConstants {
	
	final long basePtr;
	final ByteBuffer svgData;
	static final long[] nativeData = new long[4];
	
	public SVGData(final SVGRootElement rootElement){
		
		svgData = newSvgPixmap(nativeData, rootElement.width, rootElement.height, rootElement.format);
		basePtr = nativeData[0];
		
		new SVGMetaData(rootElement){
			
			@Override
			public void next(final SVGElement element){
				
				if ( element.equals(TAG_SVG) || element.equals(TAG_GROUP) ){
					return;
				}
				
				long path    = startPath();
				
				//Styles
				if ( element.getStyle() != null ){
					int lenght = element.getStyle().length;
					for ( int x=0; x<lenght; x++){
						SVGStyle style = element.getStyle()[x];
						if ( style != null ){//TODO style não implementado
							attribute(path,style.name,style.strValue,style.numberValue);
						}
					}
				}
				
				//Transform
				if ( element.getTransforms() != null ){
					int lenght = element.getTransforms().length;
					
					for ( int x=0; x<lenght; x++ ){
						SVGTransform transform = element.getTransforms()[x];
						
						if ( ATTRIBUTE_TRANSFORM_VALUE_ROTATE.equals(transform.name ) ){
							rotate(path,transform.values);
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_SKEW_Y.equals(transform.name ) ){
							skewY(path,transform.values[0]);
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_SKEW_X.equals(transform.name ) ){
							skewX(path,transform.values[0]);
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_SCALE.equals(transform.name ) ){
							
							double scaleX = 0;
							double scaleY = 0;
							
							if ( transform.values.length == 1){
								scaleX = scaleY = transform.values[0];
							}else{
								scaleX = transform.values[0];
								scaleY = transform.values[1];
							}
							
							scale(path, scaleX, scaleY);
							
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_TRANSLATE.equals(transform.name ) ){
							translate(path,transform.values[0],transform.values[1]);
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_MATRIX.equals(transform.name ) ){
							matrix(path,transform.values[0],transform.values[1],transform.values[2],transform.values[3],transform.values[4],transform.values[5]);
						}
						
					}
				}
				
				if ( element.equals(TAG_CIRCLE) ){
					
					SVGElementCircle elementCircle = (SVGElementCircle)element;
					circle(path, elementCircle.cx, elementCircle.cy, elementCircle.raio);
				
				}else if ( element.equals(TAG_ELLIPSE) ){
					
					SVGElementEllipse elementEllipse = (SVGElementEllipse)element;
					circle(path, elementEllipse.cx, elementEllipse.cy, elementEllipse.raio);
				
				}else if ( element.equals(TAG_LINE) ){
					
					SVGElementLine elementLine = (SVGElementLine)element;
					line(path, elementLine.x1, elementLine.y1, elementLine.x2, elementLine.y2);
				
				}else if ( element.equals(TAG_RECTANGLE) ){
					
					SVGElementRect elementRect = (SVGElementRect)element;
					rect(path, elementRect.x, elementRect.y, elementRect.width, elementRect.height, elementRect.rx, elementRect.ry);
				
				}else if ( element.equals(TAG_POLYGON) ){
				
					SVGElementPolygon elementPolygon = (SVGElementPolygon)element;
					polygon(path, elementPolygon.paths, elementPolygon.paths.length);
				
				}else if ( element.equals(TAG_POLYLINE) ){

					SVGElementPolyLine elementPolyLine = (SVGElementPolyLine)element;
					polygon(path, elementPolyLine.paths, elementPolyLine.paths.length);
					
				}else if ( element.equals(TAG_PATH) ){
					int sizePath = ((SVGElementPath)element).path.length;
					
					for ( int x=0; x<sizePath; x++ ){
						SVGPath d = ((SVGElementPath)element).path[x];
						pathD(path,d.attribute,d.patch,d.patch.length);
					}
					
				}
				
				
				endPath(basePtr, path, rootElement.min_x, rootElement.min_y, rootElement.max_x, rootElement.max_y, rootElement.scale);
				
			}
			
			@Override
			public void end(){
				
			}
			
		};
		
	}
	
	@Override
	public void dispose() {
		free(basePtr);
	}
	
	private static native ByteBuffer newSvgPixmap(long[] nativeData, int width, int height, int format);
	private static native void free(long basePtr);
	
	static native long startPath();
	static native void endPath(long pixmap, long path, double min_x, double min_y, double max_x, double max_y, double scale);
	static native void circle( long path, double cx, double cy, double raio);
	static native void rect( long path, double x, double y, double width, double height, double rx, double ry);
	static native void line( long path, double x1, double y1, double x2, double y2);
	static native void polygon( long path, double[] paths, int lenght);
	static native void moveTo( long path, double x, double y, boolean rel);
	static native void lineTo( long path, double x,  double y, boolean rel);
	static native void hlineTo( long path, double x, boolean rel);
	static native void vlineTo( long path, double y, boolean rel);
	static native void curve3( long path, double arq[], boolean rel);
	static native void curve4( long path, double arq[], boolean rel);
	static native void attribute( long path, String name, String strValue, double numberValue);
	static native void matrix( long path, double val1, double val2, double val3, double val4, double val5, double val6);
	static native void translate( long path, double val1, double val2);
	static native void rotate( long path, double arq[]);
	static native void scale( long path, double val1, double val2);
	static native void skewX( long path, double value);
	static native void skewY( long path, double value);
	static native void pathD( long path, char key, double arg[], int length);


}
