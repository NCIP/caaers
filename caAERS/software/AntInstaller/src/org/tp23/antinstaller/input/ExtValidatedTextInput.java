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
 * <p>Free text input type with validation using a custon supplied class.</p>
 * This enable very coplext validation, for example, a class could be written
 * to validate a port number enterd by a user that tries to open a socket
 * on the port and returns false to the validate method if the socket is in use. 
 * @author Paul Hinds
 * @version $Id: ExtValidatedTextInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ExtValidatedTextInput
	extends ValidatedTextInput{

    private String validationClass;
    private Validator validator;
    private Throwable throwable ;

	public ExtValidatedTextInput() {
	}

	public void setValue(String dir){
		setInputResult(dir);
	}
    public String getValidationClass() {
		return validationClass;
    }
    public void setValidationClass(String validationClass) {
		this.validationClass = validationClass;
		try {
			validator = (Validator)Class.forName(validationClass).newInstance();
		}
		catch (Exception ex) {
			throw new InputException("Invalid Class in ExtValidated text input");
		}
    }

	/**
	 * Called to validate the user input
	 */
	public boolean validate(InstallerContext ctx) throws ValidationException{
		String result = getInputResult();
		try{
			validator.validate(result,ctx);
			throwable=null;
			return true;
		}
		catch(Throwable t){
			throwable=t;
			return false;
		}
		
	}
	/**
	 * @return Returns the validator.
	 */
	public Validator getValidator() {
		return validator;
	}

	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("ExtValidated:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("ExtValidated:property must be set");
			return false;
		}
		if(getValidationClass()==null){
			System.out.println("ExtValidated:validationClass must be set");
			return false;
		}
		return true;
	}
	/**
	 * @return Returns the throwable.
	 */
	public Throwable getThrowable() {
		return throwable;
	}
}
