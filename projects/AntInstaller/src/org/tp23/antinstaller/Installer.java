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
package org.tp23.antinstaller;

import java.util.List;
import java.util.Vector;

import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.ResultContainer;
import org.tp23.antinstaller.page.Page;



/**
 *
 * <p>Object representation of the Installer element in the config. </p>
 * <p>This object holds the reference to all the Pages which in tern hold references
 * to the InputFields,  All the properties in the Installer element are held at this level
 * as is the ResultContainer</p>
 * @author Paul Hinds
 * @version $Id: Installer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class Installer {


	private String name;
	private String minJavaVersion = "1.4";
	private String ui;
	private boolean verbose;
	private boolean debug;
	private String lookAndFeel;
	private String windowIcon;
	private String defaultImageResource;
	private String finishButtonText = "Install";
    private String antialiased;
    
	private Page[] pages;
	private ResultContainer resultContainer = new ResultContainer();

	public Installer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getMinJavaVersion() {
		return minJavaVersion;
	}

	public Page[] getPages() {
		return pages;
	}

	public void setPages(Page[] pages) {
		this.pages = pages;
	}

	public String getUi() {
		return ui;
	}

	public void setUi(String ui) {
		this.ui = ui;
	}

	public boolean isVerbose() {
		return verbose;
	}
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	public void setVerbose(String strVerbose) {
		this.verbose = OutputField.isTrue(strVerbose);
	}

	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public void setDebug(String strDebug) {
		this.debug = OutputField.isTrue(strDebug);
	}


	public String getLookAndFeel() {
		return lookAndFeel;
	}

	public void setLookAndFeel(String lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}

	public String getWindowIcon() {
		return windowIcon;
	}

	public void setWindowIcon(String windowIcon) {
		this.windowIcon = windowIcon;
	}

	public String getDefaultImageResource() {
		return defaultImageResource;
	}

	public void setDefaultImageResource(String defaultImageResource) {
		this.defaultImageResource = defaultImageResource;
	}

	public String getFinishButtonText() {
		return finishButtonText;
	}

	public void setFinishButtonText(String finishButtonText) {
		this.finishButtonText = finishButtonText;
	}

	public ResultContainer getResultContainer() {
		return resultContainer;
	}

	public String getAntialiased() {
		return antialiased;
	}

	public void setAntialiased(String antialiased) {
		this.antialiased = antialiased;
	}

	/**
	 * Returns the list of selected targets from the installer obeying
	 * page order. This method is
	 * probably only usefull after the UI screens have finished.  Using prior to that
	 * bear in mind that the user in the Swing GUI can go back and reselect
	 * targets that were not selected previously 
	 * @return Returns a Vector since Ant requires this for the Project class (Should be a List)
	 */
	public Vector getTargets(){
		Vector argsList = new Vector();
		for (int i = 0; i < getPages().length; i++) {
			Page page = getPages()[i];
			List pageTargets = page.getTargets();
			for (int pt = 0; pt < pageTargets.size(); pt++) {
				String target = (String)pageTargets.get(pt);
				if(!argsList.contains(target))argsList.add(target);
			}
		}
		return argsList;
	}

}
