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
package org.tp23.antinstaller.page;

import org.tp23.antinstaller.input.OutputField;

public class ProgressPage extends Page{
	
	private boolean showTargets = true;
	
	
	public ProgressPage() {
	}
	/**
	 * @return Returns the showTargets.
	 */
	public boolean isShowTargets() {
		return showTargets;
	}
	/**
	 * @param showTargets indicates that the graphical display of
	 * progress should be used in the Swing renderer
	 */
	public void setShowTargets(boolean showTargets) {
		this.showTargets = showTargets;
	}
	public void setShowTargets(String strShowTargets) {
		this.showTargets = OutputField.isTrue(strShowTargets);
	}
}
