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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.tp23.antinstaller.input.ExtValidatedTextInput;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.ValidatedTextInput;
import org.tp23.antinstaller.input.Validator;
import org.tp23.antinstaller.renderer.MessageRenderer;
import org.tp23.gui.GBCF;

public class ExtValidatedTextInputRenderer
	extends ValidatedTextInputRenderer {

	/**
	 * renderError
	 */
	public void renderError() {
		MessageRenderer mr = ctx.getMessageRenderer();
		ExtValidatedTextInput extVal = (ExtValidatedTextInput)inputField;
		Validator validator = extVal.getValidator();
		Throwable t = extVal.getThrowable();
		mr.printMessage(validator.getErrorMessage(t,null));
		this.jTextField.requestFocus();
		this.jTextField.setForeground(Color.red);
	}

}
