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
package org.tp23.antinstaller.runtime;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.tp23.antinstaller.Installer;
import org.tp23.antinstaller.input.AppRootInput;
import org.tp23.antinstaller.input.CheckboxInput;
import org.tp23.antinstaller.input.CommentOutput;
import org.tp23.antinstaller.input.DateInput;
import org.tp23.antinstaller.input.DirectoryInput;
import org.tp23.antinstaller.input.ExtValidatedTextInput;
import org.tp23.antinstaller.input.FileInput;
import org.tp23.antinstaller.input.InputField;
import org.tp23.antinstaller.input.LargeSelectInput;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.PasswordTextInput;
import org.tp23.antinstaller.input.SelectInput;
import org.tp23.antinstaller.input.TargetInput;
import org.tp23.antinstaller.input.TargetSelectInput;
import org.tp23.antinstaller.input.UnvalidatedTextInput;
import org.tp23.antinstaller.input.ValidatedTextInput;
import org.tp23.antinstaller.page.LicensePage;
import org.tp23.antinstaller.page.Page;
import org.tp23.antinstaller.page.ProgressPage;
import org.tp23.antinstaller.page.SimpleInputPage;
import org.tp23.antinstaller.page.SplashPage;
import org.tp23.antinstaller.runtime.exe.LoadConfigFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
/**
 *
 * <p>Loads the configuration file into memory as an Installer object. </p>
 * <p>This class also contains a main() method to check the config files for common errors </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @todo this should be an interface not a class
 * @author Paul Hinds
 * @version $Id: ConfigurationLoader.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ConfigurationLoader extends LoadConfigFilter{
	/**
	 * Command line config checker
	 * @param args String[]
	 */
	public static void main(String[] args) {
		int ret = 1;
		try {
			ret = validate(args[0],INSTALLER_CONFIG_FILE);
		}
		catch (ConfigurationException ex) {
			ex.printStackTrace();
			System.exit(ret);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			System.exit(ret);
		}
	}
	
	public static int validate(String fileRoot,String configName) throws IOException, ConfigurationException{
		ConfigurationLoader loader = new ConfigurationLoader();
		Installer installer = loader.readConfig(new File(fileRoot),configName);
		Page[] pages = installer.getPages();
		boolean foundErrors = false;
		for (int p = 0; p < pages.length; p++) {
			OutputField[] fields = pages[p].getOutputField();
			for (int f = 0; f < fields.length; f++) {
				if(!fields[f].validateObject()){
					foundErrors = true;
					System.out.println("Error in page:"+pages[p].getName());
				}
			}
		}
		System.out.println("Finished checking config inputs");
		// check page structure
		if(!(pages[pages.length-1] instanceof ProgressPage)){
			foundErrors = true;
			System.out.println("Last Page should be a progress page");
		}
		// check for targets
		int numOfTargets = 0;
		for (int p = 0; p < pages.length; p++) {
			numOfTargets += pages[p].getTargets().size();
		}
		if(numOfTargets==0){
			System.out.println("Warning: No Page Targets (not a problem if there are target input types)");
		}
		//@todo check targets exist in build.xml

		// check ifTargets
		ArrayList targetsSoFar = new ArrayList();
		for (int p = 0; p < pages.length; p++) {
			if(pages[p] instanceof SimpleInputPage){
				SimpleInputPage simple = (SimpleInputPage)pages[p];
				String ifTarget = simple.getIfTarget();
				if(ifTarget != null && !targetsSoFar.contains(ifTarget)){
					System.out.println("ifTarget="+ifTarget);
					System.out.println("ifTarget will never test true, no prior target in page:"+pages[p].getName());
					foundErrors = true;
				}
			}
			// add after to ensure testing previous pages
			targetsSoFar.addAll(pages[p].getTargets());
			OutputField[] fields = pages[p].getOutputField();
			for (int f = 0; f < fields.length; f++) {
				if(fields[f] instanceof TargetInput){
					TargetInput ti = (TargetInput)fields[f];
					targetsSoFar.add(ti.getTarget());
				}
			}
		}
//		if(targetsSoFar.contains("default")){
//			System.out.println("Target:target can not be \"default\"");
//			foundErrors = true;
//		}


		System.out.println("Finished checking config");
		if(!foundErrors)return 0;
		return 1;
	}

}
