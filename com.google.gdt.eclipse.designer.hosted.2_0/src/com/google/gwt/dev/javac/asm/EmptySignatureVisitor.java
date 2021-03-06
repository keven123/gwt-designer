/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.google.gwt.dev.javac.asm;

import org.objectweb.asm.signature.SignatureVisitor;

/**
 * Signature visitor that does nothing.
 * 
 * Unlike the ASM-provided EmptyVisitor (which does not implement
 * SignatureVisitor), this class does not pass itself to unimplemented
 * sub-visitors, so that a subclass doesn't have to worry about calls for
 * something under a sub-visitor it doesn't care about.
 * 
 * There is no need to call any superclass methods from any subclass as they
 * do nothing.
 */
public class EmptySignatureVisitor implements SignatureVisitor {

  protected static EmptySignatureVisitor ignore = new EmptySignatureVisitor();

  /**
   * Treated as a visitEnd for this visitor.
   */
  public SignatureVisitor visitArrayType() {
    return ignore;
  }

  /**
   * Treated as a visitEnd for this visitor.
   */
  public void visitBaseType(char descriptor) {
  }

  public SignatureVisitor visitClassBound() {
    return ignore;
  }

  public void visitClassType(String name) {
  }

  public void visitEnd() {
  }

  public SignatureVisitor visitExceptionType() {
    return ignore;
  }

  public void visitFormalTypeParameter(String name) {
  }

  public void visitInnerClassType(String name) {
  }

  public SignatureVisitor visitInterface() {
    return ignore;
  }

  public SignatureVisitor visitInterfaceBound() {
    return ignore;
  }

  public SignatureVisitor visitParameterType() {
    return ignore;
  }

  public SignatureVisitor visitReturnType() {
    return ignore;
  }

  public SignatureVisitor visitSuperclass() {
    return ignore;
  }

  public void visitTypeArgument() {
  }

  public SignatureVisitor visitTypeArgument(char wildcard) {
    return ignore;
  }

  /**
   * Treated as a visitEnd for this visitor.
   */
  public void visitTypeVariable(String name) {
  }
}
