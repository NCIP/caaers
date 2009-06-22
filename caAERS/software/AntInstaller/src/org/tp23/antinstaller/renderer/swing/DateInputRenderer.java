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
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.tp23.antinstaller.input.DateInput;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.renderer.MessageRenderer;
import org.tp23.gui.GBCF;

public class DateInputRenderer
	extends SwingOutputFieldRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");
	
	protected DateInput inputField;

    protected AILabel fieldLabel = new AILabel();
    protected AITextfield jTextField = new AITextfield();
	protected Color origFore;

	public DateInputRenderer() {
		origFore = jTextField.getForeground();
	}

	public void initComponent(JPanel parent){
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setOutputField(OutputField inputField) {
		this.inputField=(DateInput)inputField;
		this.inputField.setValue(this.inputField.getDefaultValue());
	}
	public void updateInputField(){
		inputField.setValue(jTextField.getText());
	}
	public void updateDefaultValue(){
		if(!inputField.isEditted())jTextField.setText(inputField.getDefaultValue());
	}

    private void jbInit() throws Exception {
		fieldLabel.setText(inputField.getDisplayText());
		jTextField.setText(inputField.getDefaultValue());
		jTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!='\t'){
					inputField.setEditted(true);
				}
			}
		});
		jTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent fe){
				jTextField.setForeground(origFore);
			}
		});
    }
	public int addSelf(JPanel content,GBCF cf,  int row,boolean overflow) {
		content.add(fieldLabel,cf.getCell(row,0));
		content.add(jTextField,cf.getCell(row,1));
		if(overflow){
			jTextField.setOverflow(SwingOutputFieldRenderer.OVERFLOW_FIELD_SIZE);
		}
		return ++row;
	}


	/**
	 * renderError
	 */
	public void renderError() {
		MessageRenderer mr = ctx.getMessageRenderer();
		mr.printMessage(res.getString("notCorrectFormat")+"\n\n "+inputField.getDateFormat());
		//fixed BUG1295944 mr.printMessage("The field is not of the correct format\n\n "+inputField.getDateFormat());
		this.jTextField.requestFocus();
		this.jTextField.setForeground(Color.red);
	}

}
