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
 * <p>Free text input type </p>
 * @author Paul Hinds
 * @version $Id: UnvalidatedTextInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class UnvalidatedTextInput
	extends InputField{

	public UnvalidatedTextInput() {
	}

	public void setValue(String dir){
		setInputResult(dir);
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
		if(getDisplayText()==null){
			System.out.println("Simple:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("Simple:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("Simple:defualtValue must be set");
			return false;
		}
		return true;
	}
}
