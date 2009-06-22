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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.TargetInput;
/**
 *
 * <p>Represents a page in the installer. </p>
 * <p>This object maintians an ordered list of targets that have been selected
* so that when ant is run the targets are run in the correct order.  If
* Targets exist in multiple pages they are run in the order they appear in the config file.  </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: Page.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */

public abstract class Page {

	//private static final int MAX_TARGETS = 10;
	private String name;
	private String displayText;
	private String imageResource;
	private OutputField[] outputField;
	private boolean abort;
	private Set targets = new TreeSet();

	public Page() {
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

	public String getImageResource() {
		return imageResource;
	}

	public void setImageResource(String imageResource) {
		this.imageResource = imageResource;
	}

	public OutputField[] getOutputField() {
		return outputField;
	}

	public void setOutputField(OutputField[] outputField) {
		this.outputField = outputField;
	}



/**
	 * These are the ant targets that will be run, this is decided after
	 * the Page has been displayed.  For example is the user chooses not
	 * to enter a field that may signify that a target should not be run
	 * @return A sorted List a list of Ant targets;
	 */
	public List getTargets() {
		List results = new ArrayList(targets.size());
		Iterator iter = targets.iterator();
		while(iter.hasNext()){
			IndexedTarget idxTarget = (IndexedTarget)iter.next();
			results.add(idxTarget.target);
		}
		return results;
	}
	/**
	 * Comma separated list of targets for this page, called when parsing the
	 * config file
	 * @param target String
	 */
	public void setTarget(String targetList) {
		StringTokenizer st = new StringTokenizer(targetList,",");
		while (st.hasMoreTokens()) {
			targets.add(new IndexedTarget(TargetInput.getGlobalIdx(),st.nextToken()));
		}
	}
	public void addTarget(int idx, String target) {
		this.targets.add(new IndexedTarget(idx,target));
	}
	public void removeTarget(int idx, String target) {
		this.targets.remove(new IndexedTarget(idx,target));
	}
	/**
	 * returns true if the page has the current target set
	 * @param target String
	 * @return boolean
	 */
	public boolean isTarget(String target) {
		if(target==null)return false;
		Iterator iter = targets.iterator();
		while(iter.hasNext()) {
			IndexedTarget idxTarget = (IndexedTarget)iter.next();
			if(idxTarget.target.equals(target))return true;
		}
		return false;
	}


    /**
	 * This is called after the page is displayed, a page can return false to indicate
	 * that the installation should abort.  Should be false if the cancel button is pressed.
	 * System.exit is not called to allow the installer to clean up temporary files.
	 * @return boolean
	 */
	public boolean isAbort() {
		return abort;
	}

	public void setAbort(boolean abort) {
		this.abort = abort;
	}
	class IndexedTarget implements Comparable{
		int idx;
		String target;
		IndexedTarget(int idx,String target){
			this.idx=idx;
			this.target=target;
		}
		public boolean equals(Object target){
			IndexedTarget test = (IndexedTarget)target;
			return test.idx==idx;
		}
		public int compareTo(Object o) {
			IndexedTarget test = (IndexedTarget)o;
			return idx-test.idx;
		}

	}
}
