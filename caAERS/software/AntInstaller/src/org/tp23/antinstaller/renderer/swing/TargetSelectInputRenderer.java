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
package org.tp23.antinstaller.renderer.swing;

import java.util.Enumeration;

import javax.swing.JRadioButton;

import org.tp23.antinstaller.input.TargetSelectInput;
/**
 * 
 * @author Paul Hinds, Mark Anderson
 * @version $Id: TargetSelectInputRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class TargetSelectInputRenderer
	extends SelectInputRenderer {

	public TargetSelectInputRenderer() {
	}

	public void updateInputField(){
		Enumeration enum_ = optionGroup.getElements();
		int targetIdx = ((TargetSelectInput)inputField).getIdx();
		int i=0;
		for(;enum_.hasMoreElements();i++){
			JRadioButton o = (JRadioButton)enum_.nextElement();
			ctx.getCurrentPage().removeTarget(targetIdx,"");
			if(o.isSelected()){
				String target = inputField.getOptions()[i].value;        
				ctx.getCurrentPage().addTarget(targetIdx, target);
				inputField.setValue(target);
				break;
			}
		}
		if(i>inputField.getOptions().length){
			inputField.setValue(inputField.getDefaultValue());
		}
	}

}
