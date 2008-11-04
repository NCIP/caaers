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


import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;



/**
 *
 * <p>Input type for boolean choices represented as a checkbox in swing and a yes/no option
 * on the command line </p>
 * @author Paul Hinds
 * @version $Id: CheckboxInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class CheckboxInput
	extends InputField{


	private String force;
	private static int idx = 1;

	public CheckboxInput() {
	}

	public String getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = force;
	}
	/**
	 * This is a bit odd but we print a property with the targets to run
	 * although they are probably not going to be used by Ant as properties
	 * @param runTarget String
	 */
	public void setValue(String trueOrFalse){
			setInputResult(trueOrFalse);
	}
	/**
	 * Called to validate the user input
	 */
	public boolean validate(InstallerContext cxt) throws ValidationException{
		return true;
	}


	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getProperty()==null){
			System.out.println("Checkbox:property must be set");
			return false;
		}
		if(getDisplayText()==null){
			System.out.println("Checkbox:displayText must be set");
			return false;
		}
		if(!InputField.requiredBoolean(getDefaultValue())){
			System.out.println("Checkbox:defaultValue must be true or false");
			return false;
		}
		if(!InputField.optionalBoolean(getForce())){
			System.out.println("Checkbox:defaultValue must be true or false or null");
			return false;
		}
		return true;
	}


}
