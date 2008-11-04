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
 * This is the super class of all "Input types".  It is called OutputField since
 * it handles the base features of "Input types" for outputing text for
 * the user to read.  It also encapsulates some convenience methods for
 * interpreting boolean values from the command line and in configuration files.
 */
public abstract class OutputField {

	private String name;
	private String displayText;
	private String explanatoryText;

	public OutputField() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getExplanatoryText() {
		return explanatoryText;
	}

	public void setExplanatoryText(String explanatoryText) {
		this.explanatoryText = explanatoryText;
	}
	/**
	 * Validate the user input (or lack of it)
	 * This method should return false if the validation fails an throw an exception
	 * if it is not possible to validate or there is an error.
	 *
	 * @param cxt InstallerContext
	 * @throws ValidationException
	 * @return boolean
	 */
	public abstract boolean validate(InstallerContext cxt) throws ValidationException;

	/**
	 * Used to validate the configuration, this can be run prior to distributing the
	 * installer to check that the config is valid. Will not be used at runtime.
	 * @throws ValidationException
	 * @return boolean
	 */
	public abstract boolean validateObject();

	//////////////////////Static convenience methods

	    /** true if specified and true or yes.
	     *  N.B it is possible for X,  isTrue(X) == isFalse(X); 
	     *  This occurs if the value is null.
	     */
		public static boolean isTrue(String value){
			if(value==null)return false;
			return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes");
		}
		/** same as isTrue() but default is false if not specified */
		public static boolean isFalse(String value){
			if(value==null)return false;
			return value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no");
		}
		/**
		 * Return true if the value is set to true or false, returns false if the value is null
		 * @param value String
		 * @return boolean
		 */
		public static boolean requiredBoolean(String value){
			return isTrue(value) || isFalse(value);
		}
		/**
		 * Return true if the value is set to true or false, returns false if the value is null
		 * @param value String
		 * @return boolean
		 */
		public static boolean optionalBoolean(String value){
			return value == null || isTrue(value) || isFalse(value);
		}
}
