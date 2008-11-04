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
 * <p>Input type to choose a single value from a (numbered) list of options </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: SelectInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class SelectInput
	extends InputField{

	private SelectInput.Option[] options;

	public SelectInput() {
	}


	public SelectInput.Option[] getOptions() {
		return options;
	}

	public void setOptions(SelectInput.Option[] options) {
		this.options = options;
	}
	public Option getNewOption(){
		return new Option();
	}
	public class Option {
		public String text;
		public String value;
	}
	public void setValue(String dir){
		setInputResult(dir);
	}
	public boolean validate(InstallerContext cxt) throws ValidationException{
		if(getInputResult()==null)return false;
		String value = getInputResult();
		boolean ok = false;
		for (int i = 0; i < options.length; i++) {
			ok |= options[i].value.equals(value);
		}
		return ok;
	}



	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("Select:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("Select:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("Select:defaultValue must be set");
			return false;
		}
		if(getOptions()==null){
			System.out.println("Select:option must have at least two options");
			return false;
		}
		if(getOptions().length<2){
			System.out.println("Select:option must have at least two options");
			return false;
		}
		for (int i = 0; i < getOptions().length; i++) {
			Option o = getOptions()[i];
			if(o.text==null){
				System.out.println("Select:option:text must be set");
				return false;
			}
			if(o.value==null){
				System.out.println("Select:option:value must be set");
				return false;
			}
		}
		boolean defaultExists = false;
		for (int i = 0; i < getOptions().length; i++) {
			Option o = getOptions()[i];
			if(o.value.equals(getDefaultValue())){
				defaultExists=true;
			}
		}
		if(!defaultExists){
			System.out.println("Select:option:Default must be one of the options");
			return false;
		}
		return true;
	}
}
