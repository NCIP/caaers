/* 
 * Copyright 2005 Paul Hinds
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tp23.antinstaller.renderer.swing;

import gov.nih.nci.cagrid.antinstaller.utils.InstallerToolTip;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;




/**
 * A JLabel with altered prefered size to facilitate fixing the width
 * but still using a GridBagLayout
 * @author Paul Hinds
 * @version $Id: AILabel.java,v 1.2 2006/10/27 18:23:16 kumarvi Exp $
 */
public class AILabel extends JLabel {

	public AILabel() {
		super();
	}
	public AILabel(String text) {
		super(text);
	}
	public AILabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
	}
	public AILabel(Icon image) {
		super(image);
	}
	public AILabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
	}
	public AILabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
	}
	private Dimension prefSize= new Dimension(SwingOutputFieldRenderer.LABEL_WIDTH,
											  SwingOutputFieldRenderer.FIELD_HEIGHT);
	public Dimension getMinimumSize() {
		return prefSize;
	}
	public Dimension getPreferredSize() {
		return prefSize;
	}
	public void setOverflow(Dimension prefSize) {
		this.prefSize = prefSize;
	}
	public Dimension getMaximumSize() {
		return prefSize;
	}
	
	/**
	 * Added by kumarvi
	 * This is to override the method for creating and using custom tooltip
	 */
	
	public JToolTip createToolTip() {
        //JToolTip tip = super.createToolTip();
    	InstallerToolTip jmp = new InstallerToolTip();
    	jmp.setFixedWidth(200);
    	JToolTip tip = jmp;
    	
        tip.setBackground(Color.YELLOW);
        //Rectangle r = new Rectangle(40,40);
        //tip.setBounds(r);
        
        
        return tip;
    }
	
}
