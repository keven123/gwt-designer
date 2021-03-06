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
package com.google.gdt.eclipse.designer.model.widgets.cell;

import com.google.gdt.eclipse.designer.model.widgets.support.DOMUtils;

import org.eclipse.wb.draw2d.geometry.Rectangle;
import org.eclipse.wb.internal.core.model.creation.CreationSupport;
import org.eclipse.wb.internal.core.model.description.ComponentDescription;
import org.eclipse.wb.internal.core.utils.ast.AstEditor;
import org.eclipse.wb.internal.core.utils.reflect.ReflectionUtils;

import java.util.List;

/**
 * Model for <code>com.google.gwt.user.cellview.client.CellTable</code>.
 * 
 * @author sablin_aa
 * @author scheglov_ke
 * @coverage gwt.model
 */
public class CellTableInfo extends AbstractCellTableInfo {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public CellTableInfo(AstEditor editor,
      ComponentDescription description,
      CreationSupport creationSupport) throws Exception {
    super(editor, description, creationSupport);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Refresh
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected List<Rectangle> getColumnBoundsByBody() throws Exception {
    DOMUtils dom = getDOMUtils();
    Object tbody = ReflectionUtils.getFieldObject(getObject(), "tbody");
    Object tr = dom.getChild(tbody, 0);
    List<Rectangle> rects = getRowCellsRects(tr);
    // translate bounds to header area
    int headerHeight = getHeaderHeight();
    for (Rectangle rect : rects) {
      rect.y -= headerHeight;
      rect.height = headerHeight;
    }
    // done
    return rects;
  }

  @Override
  public int getHeaderHeight() throws Exception {
    Object thead = ReflectionUtils.getFieldObject(getObject(), "thead");
    return getState().getAbsoluteBounds(thead).height;
  }
}
