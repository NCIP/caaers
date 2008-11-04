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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.SelectInput;
import org.tp23.gui.GBCF;

public class SelectInputRenderer
	extends SwingOutputFieldRenderer {
	
	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");

	protected SelectInput inputField;

	protected JLabel fieldLabel = new AILabel();
	protected ButtonGroup optionGroup = new ButtonGroup();

	private int numOfEntries = 2;

	public SelectInputRenderer() {
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
		this.inputField=(SelectInput)inputField;
		this.numOfEntries=this.inputField.getOptions().length;
	}
	public void updateInputField(){
		Enumeration enum_ = optionGroup.getElements();
		int i=0;
		for(;enum_.hasMoreElements();i++){
			JRadioButton o = (JRadioButton)enum_.nextElement();
			if(o.isSelected()){
				inputField.setValue(inputField.getOptions()[i].value);
				break;
			}
		}
		if(i>inputField.getOptions().length){
			inputField.setValue(inputField.getDefaultValue());
		}
	}
	public void updateDefaultValue(){
		if(!inputField.isEditted()){
			String newDefault = inputField.getDefaultValue();
			Enumeration enum_ = optionGroup.getElements();
			for(int i=0;enum_.hasMoreElements();i++){
				if(newDefault.equals(inputField.getOptions()[i].value)){
					JRadioButton jrb = (JRadioButton) enum_.nextElement();
					jrb.setSelected(true);
				}else {
					enum_.nextElement();
				}
			}
		}
	}

    private void jbInit() throws Exception {
		fieldLabel.setText(inputField.getDisplayText());
		SelectInput.Option[] options = inputField.getOptions();

		for (int i = 0; i < options.length; i++) {
			JRadioButton jrb = new AIRadioButton(options[i].text);
			optionGroup.add(jrb);
			if(options[i].value.equals(inputField.getDefaultValue())){
				jrb.setSelected(true);
			}
			jrb.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					inputField.setEditted(true);
				}
			});
		}
    }
	public int addSelf(JPanel content,GBCF cf,  int row,boolean overflow) {
		content.add(fieldLabel,cf.getCell(row,0));
		Enumeration enum_ = optionGroup.getElements();
		// there should be at least two
		enum_.hasMoreElements();
		AIRadioButton jrb = (AIRadioButton)enum_.nextElement();
		content.add(jrb,cf.getCell(row++,1));
		if(overflow){
			jrb.setOverflow(SwingOutputFieldRenderer.OVERFLOW_FIELD_SIZE);
		}
		JPanel empty = new JPanel();
		/**
		 * This solves the problem of the grey check box that appears
		 */
		empty.setOpaque(false);
		while(enum_.hasMoreElements()){
			jrb = (AIRadioButton)enum_.nextElement();
			
			content.add(empty,cf.getCell(row,0));
			content.add(jrb,cf.getCell(row++,1));
			if(overflow){
				jrb.setOverflow(SwingOutputFieldRenderer.OVERFLOW_FIELD_SIZE);
			}
		}
		
		return row;
	}

	/**
	 * renderError
	 */
	public void renderError() {
		ctx.getMessageRenderer().printMessage(res.getString("notValidSelection"));
		// fixed BUG:1295944  ctx.getMessageRenderer().printMessage("Not a valid selection");
		//optionGroup.requestFocus();
	}
}
