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
 * <p>Object representation of an inputField XML Element </p>
 * <p>Also used to hold data of the results of the installer questions </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
* @todo insert super class called OutputField without ResultContainer
 * @author Paul Hinds
 * @version $Id: InputField.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public abstract class InputField
	extends OutputField {
	//protected String inputResult = null;
	protected ResultContainer resultContainer;
	private String property;
	protected String defaultValue;

	/**
	 * Flag to indicate that the user has already editted this field
	 */
	private boolean editted = false;

	public InputField() {
	}


	/**
	 * Returns the input result if there is one and if this is a PropertyField
	 * @return String
	 */
	public String getInputResult() {
		return resultContainer.getProperty(property);
	}

	public void setInputResult(String inputResult) {
		resultContainer.setProperty(property,inputResult);
	}
	public boolean isEditted() {
		return editted;
	}
	public void setEditted(boolean editted) {
		this.editted = editted;
	}
	public void setResultContainer(ResultContainer resultContainer) {
		this.resultContainer = resultContainer;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDefaultValue() {
		return resultContainer.getDefaultValue(defaultValue);
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}


}
