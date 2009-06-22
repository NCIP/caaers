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
 * <p>Input type to select a directory </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: DirectoryInput.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class DirectoryInput
	extends OSSpecific {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");


	private boolean abort = false;
    private String create;
    private String checkExists;

	public DirectoryInput() {
	}

	/**
	 * Called to validate the user input
	 */
	public boolean validate(InstallerContext cxt) throws ValidationException{
		if (getInputResult() == null)return false;
		MessageRenderer mr = cxt.getMessageRenderer();
		File file = new File(getInputResult());
		if(InputField.isTrue(create)){
			if(!file.exists()){
				try {
					if(mr.prompt(res.getString("dirNotExistCreate") + "\n"+file.getAbsolutePath())){
						boolean ok = file.mkdirs();
						if(!ok)mr.printMessage(res.getString("dirNotCreated"));
					}
				}
				catch (Exception ex) {
					mr.printMessage(res.getString("canNotCreateFile")+"\n"+file.getAbsolutePath());
					throw new ValidationException(res.getString("canNotCreateFile"),ex);
				}
			}
		}
		if(InputField.isTrue(checkExists)){
			if(!file.exists() || !file.isDirectory()){
				mr.printMessage(res.getString("dirNotExist")+"\n"+file.getAbsolutePath());
				return false;
			}
		}
		return true;
	}

	public boolean isAbort() {
		return abort;
	}

	public void setAbort(boolean abort) {
		this.abort = abort;
	}

    public String getCreate() {
		return create;
    }
    public void setCreate(String create) {
		this.create = create;
    }
	public void setValue(String dir){
		setInputResult(dir);
	}

	public String getCheckExists() {
		return checkExists;
	}

	public void setCheckExists(String checkExists) {
		this.checkExists = checkExists;
	}

	/**
	 * Used by checkConfig to validate the configuration file.
	 * Not used at runtime.
	 * @return boolean
	 */
	public boolean validateObject() {
		if(getDisplayText()==null){
			System.out.println("Directory:displayText must be set");
			return false;
		}
		if(getProperty()==null){
			System.out.println("Directory:property must be set");
			return false;
		}
		if(getDefaultValue()==null){
			System.out.println("Directory:defaultValue must be set");
			return false;
		}
		if(!InputField.optionalBoolean(getCreate())){
			System.out.println("Directory:create must be true or false or null");
			return false;
		}
		if(!InputField.optionalBoolean(getCheckExists())){
			System.out.println("Directory:checkExists must be true or false or null");
			return false;
		}
		return true;
	}

}
