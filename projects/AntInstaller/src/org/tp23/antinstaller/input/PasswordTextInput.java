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
package org.tp23.antinstaller.input;

/**
 *
 * <p>Free validated text input type that does not echo the value in the GUI.</p>
 * <p>This class implements SecretPropertyField so the
 * values are not printed in the properties file.  It is the responsibility of the renderer
 * not to show the password. Hiding is currently not supported on the console.</p>
 * @author Paul Hinds
 * @version $Id: PasswordTextInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class PasswordTextInput
	extends ValidatedTextInput implements SecretPropertyField
{

	private String textMask = "false";


	/**
	 * @return Returns true if text masking is requested.
	 */
	public String getTextMask() {
		return textMask;
	}
	/**
	 * @param textMask The textMask value true or false.
	 */
	public void setTextMask(String textMask) {
		this.textMask = textMask;
	}
	/**
	 * Used by checkConfig to validate the configuration file
	 * not at runtime
	 * @return boolean
	 */
	public boolean validateObject() {
		if(!InputField.optionalBoolean(getTextMask())){
			System.out.println("Comment:textMask must be true or false or null:"+getTextMask());
			return false;
		}
		if(getDisplayText()==null){
			System.out.println("Password:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("Password:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("Password:defaultValue must be set");
			return false;
		}
		if(getRegex()==null){
			System.out.println("Password:regex must be set");
			return false;
		}
		return true;
	}


}
