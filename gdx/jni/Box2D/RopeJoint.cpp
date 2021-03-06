/*
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com), Nathan Sweet (admin@esotericsoftware.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
#include "Box2D.h"
#include "RopeJoint.h"

/*
 * Class:     com_badlogic_gdx_physics_box2d_joints_RopeJoint
 * Method:    jniGetMaxLength
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_RopeJoint_jniGetMaxLength
  (JNIEnv *, jobject, jlong addr) {
  b2RopeJoint* rope = (b2RopeJoint*)addr;
  return rope->GetMaxLength();
}