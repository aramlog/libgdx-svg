#include <jni.h>

#ifndef _Included_com_badlogic_gdx_graphics_glutils_SVGData
#define _Included_com_badlogic_gdx_graphics_glutils_SVGData
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jobject JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_newSvgPixmap
  (JNIEnv *, jclass, jlongArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_free
  (JNIEnv *, jclass, jlong);

JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_startPath
  (JNIEnv *, jclass);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_endPath
  (JNIEnv *, jclass, jlong, jlong, jdouble, jdouble, jdouble, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_circle
	(JNIEnv *, jclass, jlong, jdouble, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_rect
	(JNIEnv *, jclass, jlong, jdouble, jdouble, jdouble, jdouble, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_line
	(JNIEnv *, jclass, jlong, jdouble, jdouble, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_polygon
	(JNIEnv *, jclass, jlong, jdoubleArray, jint);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_moveTo
	(JNIEnv *, jclass, jlong, jdouble, jdouble, jboolean);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_lineTo
	(JNIEnv *, jclass, jlong, jdouble,  jdouble, jboolean);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_hlineTo
	(JNIEnv *, jclass, jlong, jdouble, jboolean);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_vlineTo
	(JNIEnv *, jclass, jlong, jdouble, jboolean);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_curve3
	(JNIEnv *, jclass, jlong, jdoubleArray, jboolean);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_curve4
	(JNIEnv *, jclass, jlong, jdoubleArray, jboolean);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_attribute
	(JNIEnv *, jclass, jlong, jstring, jstring, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_matrix
	(JNIEnv *, jclass, jlong, jdouble, jdouble, jdouble, jdouble, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_translate
	(JNIEnv *, jclass, jlong, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_rotate
	(JNIEnv *, jclass, jlong, jdoubleArray);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_scale
	(JNIEnv *, jclass, jlong, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_skewX
	(JNIEnv *, jclass, jlong, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_skewY
	(JNIEnv *, jclass, jlong, jdouble);

JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_glutils_SVGData_pathD
	(JNIEnv *, jclass, jlong, jchar, jdoubleArray, jint);

#ifdef __cplusplus
}
#endif
#endif
