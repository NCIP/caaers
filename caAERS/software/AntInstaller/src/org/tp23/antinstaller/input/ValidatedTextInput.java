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


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.regexp.RegexpMatcher;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;
//import org.tp23.antinstaller.antmod.RegexpMatcherFactory;
import org.tp23.antinstaller.runtime.ConfigurationException;


/**
 *
 * <p>Free text input type with validation using regular expressions</p>
 * @author Paul Hinds
 * @version $Id: ValidatedTextInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ValidatedTextInput
	extends InputField{

    private String regex;
	private Pattern pattern;
	//private RegexpMatcher matcher; // use ant version in an attempt to support jdk1.3

	public ValidatedTextInput() {
	}

	public void setValue(String dir){
		setInputResult(dir);
	}
    public String getRegex() {
		return regex;
    }
    public void setRegex(String regex) {
		this.regex = regex;
		try {
			//matcher = new RegexpMatcherFactory().newRegexpMatcher();
			//matcher.setPattern(regex);
			pattern = Pattern.compile(regex);
		}
		catch (BuildException ex) {
			throw new InputException("Invalid regex in Validated text input");
		}
    }

	/**
	 * Called to validate the user input
	 */
	public boolean validate(InstallerContext cxt) throws ValidationException{
		try {
			if (getInputResult() == null)return false;
			String toTest = getInputResult();

			Matcher matcher = pattern.matcher(toTest);
			//boolean matches =  matcher.matches(toTest);
			boolean matches =  matcher.matches();

			return matches;
		}
		catch (Throwable e) {
			cxt.log(e);
			return false;
		}
	}

	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("Validated:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("Validated:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("Validated:defaultValue must be set");
			return false;
		}
		if(getRegex()==null){
			System.out.println("Validated:regex must be set");
			return false;
		}
		try{
			//matcher = new RegexpMatcherFactory().newRegexpMatcher();
			//matcher.setPattern(getRegex());
		}
		catch(Exception e){
			System.out.println("Validated:regex must compile");
			
		}
		return true;
	}
}
