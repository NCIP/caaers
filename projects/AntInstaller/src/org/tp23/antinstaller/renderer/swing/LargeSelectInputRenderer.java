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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tp23.antinstaller.input.LargeSelectInput;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.gui.GBCF;

public class LargeSelectInputRenderer
	extends SwingOutputFieldRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");

	protected LargeSelectInput inputField;

	protected JLabel fieldLabel = new AILabel();
	protected JComboBox optionCombo = new JComboBox();

	//private int numOfEntries = 2;

	public LargeSelectInputRenderer() {
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
		this.inputField=(LargeSelectInput)inputField;
		//this.numOfEntries=this.inputField.getOptions().length;
	}
	public void updateInputField(){

		int selectedIdx = optionCombo.getSelectedIndex();
		if (selectedIdx != -1) {
			inputField.setValue(inputField.getOptions()[selectedIdx].value);

		}else{
			inputField.setValue(inputField.getDefaultValue());
		}
	}
	public void updateDefaultValue(){
		if(!inputField.isEditted()){

			String newDefault = inputField.getDefaultValue();

			for(int i=0;i<optionCombo.getItemCount();i++){
				if(newDefault.equals(inputField.getOptions()[i].value)){
					optionCombo.setSelectedIndex(i);
					break;
				}
			}
		}
	}

    private void jbInit() throws Exception {
		fieldLabel.setText(inputField.getDisplayText());
		LargeSelectInput.Option[] options = inputField.getOptions();


		for (int i = 0; i < options.length; i++) {
			optionCombo.addItem(options[i].text);
			if(options[i].value.equals(inputField.getDefaultValue())){
				optionCombo.setSelectedIndex(i);
			}
		}
		optionCombo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				inputField.setEditted(true);
			}
		});
    }
	public int addSelf(JPanel content,GBCF cf,  int row,boolean overflow) {
		content.add(fieldLabel,cf.getCell(row,0));
		content.add(optionCombo,cf.getCell(row,1));
		if(overflow){
			optionCombo.setPreferredSize(SwingOutputFieldRenderer.OVERFLOW_FIELD_SIZE);
		}
		return ++row;
	}

	/**
	 * renderError
	 */
	public void renderError() {
		ctx.getMessageRenderer().printMessage(res.getString("notValidSelection"));
		optionCombo.requestFocus();
	}
}
