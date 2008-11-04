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
 * <p>Input type to select targets to install </p>
 * If the osSpecific flag is set the OS of the current system will
 * be appended to the name of the target actually by ant run so that different
 * Targets can be run according to the target platform.
 * This feature goes against the principles of
 * building cross platform installers, but is provided so that common installer
 * tasks such as creating icons and shortcuts can be run on Windows for
 * all those useless users who can't run a command script ;)
 * <br>
 * Currently there are two modes strict and not strict (lax).</p>
 * <p>Strict target will return the target name plus the exact String in the
 * System Property "os.name" this means you will have to provide targets for
 * every possible OS version. See
 * <a href="http://lopica.sourceforge.net/os.html">this page</a> for a list of possible values
 * There are a great many but you may not want to consider some of the options.</p>
 * <p>Lax target will return one of the following strings only
 * <ul>
 * <li>"[target-name]-linux" - Linux </li>
 * <li>"[target-name]-mac" - Mac OS and Mac OS X</li>
 * <li>"[target-name]-sun" - SunOS and Solaris</li>
 * <li>"[target-name]-win" - Windows *</li>
 * <li>"[target-name]-other" - any thing else</li>
 * </ul></p> so you only have to create 5 ant targets to support all the cases.
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: TargetInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class TargetInput
	extends InputField{


	private String target;
	private String defaultValue;
	private String force;
	private String osSpecific;
	private String strict;
	//targets are ordered
	private int idx;

	private static int globalIdx = 1;

	public TargetInput() {
		idx=getGlobalIdx();
	}

	public String getTarget() {
		if(isTrue(osSpecific)){
			return getOSSpecificTarget();
		} else {
			return target;
		}
	}

	public void setTarget(String target) {
		this.target = target;
		setProperty("target"+idx);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = force;
	}


	public String getStrict() {
		return strict;
	}

	public void setStrict(String strict) {
		this.strict = strict;
	}

	public String getOsSpecific() {
		return osSpecific;
	}

	public void setOsSpecific(String osSpecific) {
		this.osSpecific = osSpecific;
	}

	/**
	 * Called to validate the user input
	 */
	public boolean validate(InstallerContext cxt) throws ValidationException {
		setInputResult(target);
		return true;
	}



	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("Target:displayText must be set");
			return false;
		}
		if(getTarget()==null){
			System.out.println("Target:target must be set");
			return false;
		}
//		if(getTarget().equals("default")){
//			System.out.println("Target:target can not be \"default\"");
//			return false;
//		}
		if(!InputField.optionalBoolean(getForce())){
			System.out.println("Target:force must be true or false or null");
			return false;
		}
		if(!InputField.optionalBoolean(getStrict())){
			System.out.println("Target:strict must be true or false or null");
			return false;
		}
		if(!InputField.optionalBoolean(getOsSpecific())){
			System.out.println("Target:osSpecific must be true or false or null");
			return false;
		}
		if(!InputField.requiredBoolean(getDefaultValue())){
			System.out.println("Target:defaultValue must be true or false");
			return false;
		}
		return true;
	}
    public int getIdx() {
		return idx;
    }
	public static int getGlobalIdx() {
		return globalIdx++;
	}

	public String getOSSpecificTarget() {
		if(isTrue(strict))return getStrictTarget();
		else return getLaxTarget();
	}

	private String getStrictTarget(){
		return target+System.getProperty("os.name");
	}

	private String getLaxTarget(){
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.indexOf("linux") != -1){
			return target +"-linux";
		}
		if(osName.indexOf("mac") != -1){
			return target +"-mac";
		}
		if(osName.indexOf("windows") != -1){
			return target +"-win";
		}
		if(osName.indexOf("solaris") != -1 || osName.indexOf("sunos") != -1){
			return target +"-sun";
		}
		return target+"-other";
	}

}
