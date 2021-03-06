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
package com.google.gwt.dev.resource;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Provides information about a file-like resource.
 */
public abstract class Resource {

  /**
   * Overridden to finalize; always returns object identity.
   */
  @Override
  public final boolean equals(Object obj) {
    return super.equals(obj);
  }

  /**
   * Returns the last modified time of the compilation unit.
   */
  public abstract long getLastModified();

  /**
   * Returns the URL-like location of the resource. The returned value should
   * generally reflect a unique resource in the system. The returned value will
   * only be a valid URL if the underlying object is accessible via a URL.
   */
  public abstract String getLocation();

  /**
   * Returns the full abstract path of the resource.
   */
  public abstract String getPath();

  /**
   * Returns a URL for this resource if the resource happens to be based off the
   * file system, otherwise returns <code>null</code>.
   * 
   * @deprecated with no replacement
   */
  @Deprecated
  public final URL getURL() {
    try {
      return new URL(getLocation());
    } catch (MalformedURLException e) {
      return null;
    }
  }
  /**
   * Overridden to finalize; always returns identity hash code.
   */
  @Override
  public final int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns the contents of the resource. May return <code>null</code> if this
   * {@link Resource} has been invalidated by its containing
   * {@link ResourceOracle}. The caller is responsible for closing the stream.
   */
  public abstract InputStream openContents();

  /**
   * Overridden to finalize; always returns {@link #getLocation()}.
   */
  @Override
  public final String toString() {
    return getLocation();
  }

  public abstract boolean wasRerooted();

}
