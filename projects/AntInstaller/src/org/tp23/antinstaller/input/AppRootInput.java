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

import java.io.File;
import java.util.ResourceBundle;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.renderer.MessageRenderer;

/**
 *
 * <p>Input type to select a directory and validate it by checking the existence of
 * files relative to the directory selected.  An Expected use for this is to find the
 * Application root of an exiting app on the clients machine.  for example to find
 * Tomcat, ask the user to select the tomcat root and check the existence of ./conf/tomcat-users.xml
 * and ./webapps </p>
 * @author Paul Hinds
 * @version $Id: AppRootInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class AppRootInput
	extends DirectoryInput {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");


    private String checkFile1;
    private String checkFile2;
	private String checkDir1;
	private String checkDir2;

	public AppRootInput() {
	}

	/**
	 * Called to validate the user input
	 */
	public boolean validate(InstallerContext cxt) throws ValidationException{
		if (getInputResult() == null)return false;
		MessageRenderer mr = cxt.getMessageRenderer();
		String directorySelected = getInputResult();
		File file = new File(directorySelected);
		// removed in response to BUG:1303230
//		if(!file.exists()){
//			if(mr.prompt(res.getString("dirNotExistCreate"))){
//				boolean ok = file.mkdirs();
//				if(!ok)mr.printMessage(res.getString("dirNotCreated"));
//			}
//		}
		if(!file.isDirectory()){
			mr.printMessage(res.getString("dirNotExist")+":"+file.getAbsolutePath());
			return false;
		}
		else if(checkFile1!=null && ! checkExists(mr,file,checkFile1)){
			return false ;
		}
		else if(checkFile2!=null && ! checkExists(mr,file,checkFile2)){
			return false ;
		}
		else if(checkDir1!=null && ! checkExists(mr,file,checkDir1)){
			return false ;
		}
		else if(checkDir2!=null && ! checkExists(mr,file,checkDir2)){
			return false ;
		}
		return true;
	}

	private boolean checkExists(MessageRenderer mr,File root,String check){
		File checkFile = new File(root,checkFile1);
		if(!checkFile.exists()){
			reportMissing(mr,checkFile);
			return false;
		}
		return true;
	}

	private void reportMissing(MessageRenderer mr,File missing){
		StringBuffer message = new StringBuffer();
		message.append(res.getString("appRootInvalid"));
		message.append(System.getProperty("line.separator"));
		if(missing.isDirectory()){
			message.append(res.getString("dirNotExist"));
		}
		else{
			message.append(res.getString("fileNotExist"));
		}
		message.append(":");
		message.append(missing.getAbsolutePath());
		mr.printMessage(message.toString());
	}



	public String getCheckDir1() {
		return checkDir1;
	}

	public String getCheckDir2() {
		return checkDir2;
	}

	public String getCheckFile1() {
		return checkFile1;
	}

	public String getCheckFile2() {
		return checkFile2;
	}

	public void setCheckFile2(String checkFile2) {
		this.checkFile2 = checkFile2;
	}

	public void setCheckFile1(String checkFile1) {
		this.checkFile1 = checkFile1;
	}

	public void setCheckDir2(String checkDir2) {
		this.checkDir2 = checkDir2;
	}

	public void setCheckDir1(String checkDir1) {
		this.checkDir1 = checkDir1;
	}
	
	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("AppRoot:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("AppRoot:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("AppRoot:defaultValue must be set");
			return false;
		}
		return true;
	}

}
