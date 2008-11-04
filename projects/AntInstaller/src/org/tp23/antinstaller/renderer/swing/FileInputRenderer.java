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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.tp23.antinstaller.input.FileInput;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.renderer.MessageRenderer;
import org.tp23.gui.GBCF;

public class FileInputRenderer
	extends SwingOutputFieldRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");

	protected FileInput inputField;

	protected AILabel fieldLabel = new AILabel();
	protected AIShortTextField jTextField = new AIShortTextField();
	protected AIButton browseButton = new AIButton();
	protected JPanel browsePanel = new JPanel();
	private Color origFore = jTextField.getForeground();
	private JPanel parent;

	public FileInputRenderer() {
	}
	public void initComponent(JPanel parent){
		this.parent=parent;
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setOutputField(OutputField inputField) {
		this.inputField = (FileInput)inputField;
		this.inputField.setValue(this.inputField.getDefaultValue(true));
	}
	public void updateInputField(){
		if( !inputField.getDefaultValue(true).equals(jTextField.getText()) ){
			inputField.setEditted(true);			
		}
		inputField.setValue(jTextField.getText());
	}
	public void updateDefaultValue(){
		if(!inputField.isEditted())jTextField.setText(inputField.getDefaultValue(true));
	}

	private void jbInit() throws Exception {
		BorderLayout bl = new BorderLayout();
		//bl.setHgap(3);
		browsePanel.setLayout(bl);
		fieldLabel.setText(inputField.getDisplayText());
		jTextField.setText(inputField.getDefaultValue(true));
		browsePanel.add(jTextField, BorderLayout.CENTER);
		browsePanel.add(browseButton, BorderLayout.EAST);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File selectedFile = null;

				JFileChooser chooser = new JFileChooser();
				chooser.setFileHidingEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (jTextField.getText() != null) {
					chooser.setCurrentDirectory(new File(jTextField.getText()).getParentFile());
				}
				int returnVal = chooser.showDialog(parent, e.getActionCommand());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = chooser.getSelectedFile();
				}
				if (selectedFile != null) {
					jTextField.setText(selectedFile.getAbsolutePath());
					inputField.setValue(selectedFile.getAbsolutePath());
					inputField.setEditted(true);
				}
			}
		});
		browseButton.setText(res.getString("selectFile"));
		browseButton.setPreferredSize(new Dimension(150,FIELD_HEIGHT));

		jTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateInputField();
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
		content.add(browsePanel,cf.getCell(row,1));
		if(overflow){
			jTextField.setOverflow(SwingOutputFieldRenderer.OVERFLOW_SHORT_FIELD_SIZE);
		}
		return ++row;
	}


	/**
	 * renderError
	 */
	public void renderError() {
		MessageRenderer mr = ctx.getMessageRenderer();
		mr.printMessage(res.getString("fileNotExist"));
		this.jTextField.requestFocus();
		this.jTextField.setForeground(Color.red);
	}
}
