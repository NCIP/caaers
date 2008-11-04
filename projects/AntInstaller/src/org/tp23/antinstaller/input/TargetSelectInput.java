/* 
 * Copyright 2005 Paul Hinds, Mark Anderson
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

import org.tp23.antinstaller.input.SelectInput.Option;

/**
 *
 * <p>Input type to choose a single value from a (numbered) list of options
 * which will be rendered as radio buttons in the Swing GUI </p>
 * REF: 1177206
 * @author Paul Hinds, Mark Anderson
 * @version $Id: TargetSelectInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class TargetSelectInput
	extends SelectInput{

	//targets are ordered
	private int idx;

	public TargetSelectInput() {
		idx=TargetInput.getGlobalIdx();
	}

	public int getIdx() {
		return idx;
	}

	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("TargetSelect:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("TargetSelect:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("TargetSelect:defaultValue must be set");
			return false;
		}
		if(getOptions()==null){
			System.out.println("TargetSelect:option must have at least two options");
			return false;
		}
		if(getOptions().length<2){
			System.out.println("TargetSelect:option must have at least two options");
			return false;
		}
		for (int i = 0; i < getOptions().length; i++) {
			Option o = getOptions()[i];
			if(o.text==null){
				System.out.println("TargetSelect:option:text must be set");
				return false;
			}
			if(o.value==null){
				System.out.println("TargetSelect:option:value must be set");
				return false;
			}
		}
		boolean defaultExists = false;
		for (int i = 0; i < getOptions().length; i++) {
			Option o = getOptions()[i];
			if(o.value.equals(getDefaultValue())){
				defaultExists=true;
			}
//			if(o.value.equals("default")){
//				System.out.println("Target:target can not be \"default\"");
//				return false;
//			}
		}
		if(!defaultExists){
			System.out.println("TargetSelect:option:Default must be one of the options");
			return false;
		}
		return true;
	}
}
