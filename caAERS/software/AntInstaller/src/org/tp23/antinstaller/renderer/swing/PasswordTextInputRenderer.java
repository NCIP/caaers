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

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.tp23.antinstaller.renderer.MessageRenderer;
import org.tp23.gui.GBCF;

public class PasswordTextInputRenderer extends ValidatedTextInputRenderer{
	
	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");
	
    public PasswordTextInputRenderer() {
		this.jTextField = new AIPasswordField();
		origFore = jTextField.getForeground(); // this is lazy there must be a way to shift this line of code to the superclass (is it workth it)
    }


	public void renderError() {
		MessageRenderer mr = ctx.getMessageRenderer();
		mr.printMessage(res.getString("notCorrectPasswordFormat")+"\n\n e.g. "+inputField.getDefaultValue() );
		// fixed BUG:1295944 mr.printMessage("The password is not of the correct format\n\n e.g. "+inputField.getDefaultValue());
		this.jTextField.requestFocus();
		this.jTextField.setForeground(Color.red);
		jTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!='\t'){
					inputField.setEditted(true);
				}
			}
		});
	}
	public int addSelf(JPanel content,GBCF cf,  int row,boolean overflow) {
		content.add(fieldLabel,cf.getCell(row,0));
		content.add(jTextField,cf.getCell(row,1));
		if(overflow){
			((AIPasswordField)jTextField).setOverflow(SwingOutputFieldRenderer.OVERFLOW_FIELD_SIZE);
		}
		return ++row;
	}

}
