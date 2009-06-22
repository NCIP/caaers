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


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;


/**
 *
 * <p>Free text input type with validation a SimpleDataFormat instance. </p>
 * <p>By default the date format is <code>dd/MM/yyyy</code> unless it
 * is overriden in teh antinstall-config.xml file. </p>
 * @author Paul Hinds
 * @version $Id: DateInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class DateInput
	extends InputField{

    private String dateFormat = "dd/MM/yyyy";
	private DateFormat formatter = new SimpleDateFormat(dateFormat);

	public DateInput() {
		formatter.setLenient(false);
	}

	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		try {
			formatter = new SimpleDateFormat(dateFormat);
			formatter.setLenient(false);
			this.dateFormat = dateFormat;
		}
		catch (RuntimeException e) {
			throw new InputException("Invalid date format in DateInput");
		}
	}

	public void setValue(String dir){
		setInputResult(dir);
	}

	/**
	 * Called to validate the user input
	 */
	public boolean validate(InstallerContext cxt) throws ValidationException{
		if (getInputResult() == null)return false;
		String toTest = getInputResult();
		try {
			formatter.parse(toTest);
		}
		catch (ParseException ex) {
			return false;
		}
		return true;
	}

	public void setDefaultValue(String defaultValue) {
		if(defaultValue.equals("TODAY")){
			this.defaultValue = formatter.format(new Date());
		} else {
			this.defaultValue = defaultValue;
		}
	}
	
	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("Date:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("Date:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("Date:defaultValue must be set");
			return false;
		}
		return true;
	}
}
