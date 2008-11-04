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

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tp23.antinstaller.input.CheckboxInput;
import org.tp23.antinstaller.input.InputField;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.gui.GBCF;

public class CheckboxInputRenderer
	extends SwingOutputFieldRenderer {

	protected CheckboxInput inputField;

    protected JLabel fieldLabel = new AILabel();
	protected AICheckBox checkBox = new AICheckBox();

	public CheckboxInputRenderer() {
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
		this.inputField=(CheckboxInput)inputField;
	}
	public void updateInputField(){
		boolean selected = checkBox.isSelected();
		if(selected)inputField.setValue("true");
		else inputField.setValue("false");
	}
	public void updateDefaultValue(){
		if(!inputField.isEditted()){
			String newDefault = inputField.getDefaultValue();
			checkBox.setSelected(InputField.isTrue(newDefault));
		}
	}

    private void jbInit() throws Exception {
		fieldLabel.setText(inputField.getDisplayText());
		checkBox.setSelected(OutputField.isTrue(inputField.getDefaultValue()));
		checkBox.setEnabled(!OutputField.isTrue(inputField.getForce()));
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateInputField();
				inputField.setEditted(true);
			}
		});
    }
	public int addSelf(JPanel content,GBCF cf,  int row,boolean overflow) {
		content.add(fieldLabel,cf.getCell(row,0));
		content.add(checkBox,cf.getCell(row,1));
		if(overflow){
			checkBox.setOverflow(SwingOutputFieldRenderer.OVERFLOW_FIELD_SIZE);
		}
		return ++row;
	}

	/**
	 * renderError
	 */
	public void renderError() {
	}
}
