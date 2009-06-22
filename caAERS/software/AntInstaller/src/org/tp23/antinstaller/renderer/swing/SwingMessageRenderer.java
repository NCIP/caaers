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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.renderer.MessageRenderer;
/**
 *
 * <p>Render User messages in Popup windows </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: SwingMessageRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class SwingMessageRenderer
	implements MessageRenderer {

	private InstallerContext ctx = null;
	private JFrame owner = null;


	public SwingMessageRenderer() {
	}
	public SwingMessageRenderer(InstallerContext ctx) {
		this.ctx=ctx;
	}

	public void setInstallerContext(InstallerContext ctx){
		this.ctx=ctx;
	}
	public void printMessage(String message){
		JOptionPane.showMessageDialog(owner,message,"Message",JOptionPane.INFORMATION_MESSAGE );
	}

	public boolean prompt(String message){
		int ret = JOptionPane.showConfirmDialog(owner,
												message,
												"Question",
												JOptionPane.YES_NO_OPTION);
		if (ret == JOptionPane.YES_OPTION) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * @param owner The owner to set.
	 */
	public void setOwner(JFrame owner) {
		this.owner = owner;
	}
}
