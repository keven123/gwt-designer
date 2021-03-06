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
package com.google.gdt.eclipse.designer.gef.policy;

import com.google.gdt.eclipse.designer.model.widgets.IWidgetInfo;
import com.google.gdt.eclipse.designer.model.widgets.panels.IVerticalSplitPanelInfo;

import org.eclipse.wb.draw2d.geometry.Insets;
import org.eclipse.wb.gef.graphical.policies.LayoutEditPolicy;

/**
 * {@link LayoutEditPolicy} for {@link IVerticalSplitPanelInfo}.
 * 
 * @author scheglov_ke
 * @coverage gwt.gef.policy
 */
public final class VerticalSplitPanelLayoutEditPolicy<T extends IWidgetInfo>
    extends
      SplitPanelLayoutEditPolicy<T> {
  private final IVerticalSplitPanelInfo<T> m_panel;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  private VerticalSplitPanelLayoutEditPolicy(IVerticalSplitPanelInfo<T> panel) {
    super(panel);
    m_panel = panel;
  }

  public static <T extends IWidgetInfo> VerticalSplitPanelLayoutEditPolicy<T> create(IVerticalSplitPanelInfo<T> panel) {
    return new VerticalSplitPanelLayoutEditPolicy<T>(panel);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Feedbacks
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void addFeedbacks() throws Exception {
    if (m_panel.getTopWidget() == null) {
      addFeedback0(0, 0, 1, 0.5, new Insets(0, 0, 5, 0), "top");
    }
    if (m_panel.getBottomWidget() == null) {
      addFeedback0(0, 0.5, 1, 1, new Insets(5, 0, 0, 0), "bottom");
    }
  }
}
