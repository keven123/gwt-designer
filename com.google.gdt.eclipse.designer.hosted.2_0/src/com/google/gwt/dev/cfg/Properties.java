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
package com.google.gwt.dev.cfg;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A typed map of deferred binding properties.
 */
public class Properties implements Iterable<Property> {

  private final SortedSet<BindingProperty> bindingProps = new TreeSet<BindingProperty>();

  private final SortedSet<ConfigurationProperty> configProps = new TreeSet<ConfigurationProperty>();

  private final SortedMap<String, Property> map = new TreeMap<String, Property>();

  /**
   * Creates the specified deferred-binding property, or returns an existing one
   * by the specified name if present.
   */
  public BindingProperty createBinding(String name) {
    BindingProperty prop = create(name, BindingProperty.class);
    bindingProps.add(prop);
    return prop;
  }

  /**
   * Creates the specified configuration property, or returns an existing one by
   * the specified name if present.
   */
  public ConfigurationProperty createConfiguration(String name,
      boolean allowMultipleValues) {
    ConfigurationProperty prop = create(name, allowMultipleValues,
        ConfigurationProperty.class);
    configProps.add(prop);
    return prop;
  }

  public Property find(String name) {
    return map.get(name);
  }

  /**
   * Gets all deferred binding properties in sorted order.
   */
  public SortedSet<BindingProperty> getBindingProperties() {
    return bindingProps;
  }

  public SortedSet<ConfigurationProperty> getConfigurationProperties() {
    return configProps;
  }

  public Iterator<Property> iterator() {
    return map.values().iterator();
  }

  /**
   * Count the total number of permutations that this property set supports.
   * This method can be expensive because it always recalculates the answer
   * based on the current set of properties and values.
   */
  public int numPermutations() {
    return new PropertyPermutations(this).size();
  }

  private <T extends Property> T create(String name, boolean flag,
      boolean useFlagArgument, Class<T> clazz) {
    if (clazz == null) {
      throw new NullPointerException("clazz");
    } else if (name == null) {
      throw new NullPointerException("name");
    }

    Property property = find(name);
    if (property != null) {
      try {
        return clazz.cast(property);
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("Cannot create property " + name
            + " because one of another type ("
            + property.getClass().getSimpleName() + ") already exists.");
      }
    }

    Exception ex = null;
    try {
      T newInstance;
      if (useFlagArgument) {
        newInstance = clazz.getConstructor(String.class, boolean.class).newInstance(
            name, flag);
      } else {
        newInstance = clazz.getConstructor(String.class).newInstance(name);
      }
      map.put(name, newInstance);
      return newInstance;
    } catch (NoSuchMethodException e) {
      ex = e;
    } catch (InstantiationException e) {
      ex = e;
    } catch (IllegalAccessException e) {
      ex = e;
    } catch (InvocationTargetException e) {
      ex = e;
    }

    throw new RuntimeException("Unable to create Property instance", ex);
  }

  private <T extends Property> T create(String name, boolean flag,
      Class<T> clazz) {
    return create(name, flag, true, clazz);
  }

  private <T extends Property> T create(String name, Class<T> clazz) {
    return create(name, false, false, clazz);
  }
}
