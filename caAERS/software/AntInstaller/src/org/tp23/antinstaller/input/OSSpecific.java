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
/**
 * Operating System Specific input fields have different default values for Unix and Windoze
 */
public abstract class OSSpecific extends InputField {

	private String defaultValueWin;

	public String getDefaultValue(boolean correctForOS) {
		if(InstallerContext.isUnix() || defaultValueWin == null){
			return resultContainer.getDefaultFileRef(defaultValue);
		}
		else return resultContainer.getDefaultFileRef(defaultValueWin);
	}

	public String getDefaultValueWin() {
		return defaultValueWin;
	}

	public void setDefaultValueWin(String defaultValueWin) {
		this.defaultValueWin = defaultValueWin;
	}
}
