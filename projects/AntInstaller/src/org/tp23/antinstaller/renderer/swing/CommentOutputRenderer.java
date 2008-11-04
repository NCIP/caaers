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
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tp23.antinstaller.input.CommentOutput;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.gui.GBCF;

public class CommentOutputRenderer
	extends SwingOutputFieldRenderer {

	protected CommentOutput outputField;

    protected AILabel fieldLabel = new AILabel();

	private static Font boldCommentFont;
	private static Font titleCommentFont;
	static{
		boldCommentFont = new JLabel().getFont();// reusing the variable
		try {
			boldCommentFont = new Font(boldCommentFont.getFamily(),Font.BOLD,boldCommentFont.getSize());
			titleCommentFont = new Font(boldCommentFont.getFamily(),Font.BOLD,16);
		}
		catch (Exception ex) {
			// lets not fail due to font errors
		}
	}

	public CommentOutputRenderer() {
	}

	public void initComponent(JPanel parent){
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setOutputField(OutputField outputField) {
		this.outputField=(CommentOutput)outputField;
	}
	public void updateInputField(){
	}
	public void updateDefaultValue(){
	}
    private void jbInit() throws Exception {
		fieldLabel.setText(outputField.getDisplayText());

		if(outputField.getBold()!=null && (
			  outputField.getBold().equalsIgnoreCase("true")||
			  outputField.getBold().equalsIgnoreCase("yes"))
			){
			fieldLabel.setFont(boldCommentFont);
		}
		if(outputField.getTitle()!=null && (
			  outputField.getTitle().equalsIgnoreCase("true")||
			  outputField.getTitle().equalsIgnoreCase("yes"))
			){
			fieldLabel.setFont(titleCommentFont);
		}
    }
	public int addSelf(JPanel content,GBCF cf,  int row,boolean overflow) {
		content.add(fieldLabel,cf.getSpan(row));
		if(overflow){
			fieldLabel.setOverflow(OVERFLOW_TOTAL_SIZE);
		} else {
			fieldLabel.setOverflow(new Dimension(FIELD_WIDTH + LABEL_WIDTH, FIELD_HEIGHT));
		}
		return ++row;
	}


	/**
	 * renderError
	 */
	public void renderError() {
	}
}
