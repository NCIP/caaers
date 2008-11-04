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



import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tp23.antinstaller.input.InputField;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.SecretPropertyField;
import org.tp23.antinstaller.page.Page;



/**
 *
 * <p>Outputs the completed Pages as a java Properties file. </p>
 * @author Paul Hinds
 * @version $Id: DefaultPropertiesFileRenderer.java,v 1.4 2006/11/28 23:16:29 kumarvi Exp $
 */
public class DefaultPropertiesFileRenderer
	implements PropertiesFileRenderer {
	//private static Logger logger = Logger.getLogger(DefaultPropertiesFileRenderer.class.getName());

	public DefaultPropertiesFileRenderer() {
		
		
	}

	public void renderProperties(Installer installer, File baseDir){
		//logger.info("Calling renderProperties");
		Page[] completedPages = installer.getPages();
		Properties props = new Properties();
		props.put(FILE_ROOT_PROPERTY,baseDir.getAbsolutePath());
		//logger.info("Passed stage 1");
		//logger.info("compted pages length"+completedPages.length );
		  try{
				for (int i = 0; i < completedPages.length; i++) {
					//logger.info("Inside big loop");
					OutputField[] fields = completedPages[i].getOutputField();
					//logger.info("Just before small loop");
					for (int f = 0; f < fields.length; f++) {
						//logger.info("Got inside");
						if (fields[f] instanceof SecretPropertyField) {
							InputField field = (InputField)fields[f];
							props.put(field.getProperty(), "XXXXXXXX");
						}
						else if (fields[f] instanceof InputField) {
							InputField field = (InputField)fields[f];
							String result = field.getInputResult();
							props.put(field.getProperty(), result);
						}
					}
				}
		  }catch(Exception e){
			  //logger.info("something got messed up here"+e.getMessage());
		  }
		
		//logger.info("Passed stage 2");
		 this.storeCustomProperties(props);
		 this.storeGridEnvProperties(props);
		try {
			File antInstallProperties = new File(baseDir.getAbsolutePath(), PROPERTIES_FILE_NAME);
			props.store(new FileOutputStream(antInstallProperties),
						"Ant Installer - AutoGenerated properties");
		}
		catch (Throwable ex) {
			//swallow Exceptions as in contract for this method
		}
	}
	
	private void storeCustomProperties(Properties props){
		/**
		 * First check if the custom property file is there
		 */
		//logger.info("Passed stage 2.1");
		String fileName = InstallerContext.getCustomPropertyFileName();
		//logger.info("Passed stage 2.2");
		if(fileName.equalsIgnoreCase(InstallerContext.DOES_NOT_EXIST)){
			return;
		}
		//logger.info("Passed stage 2.2");
		Properties toBeStoredProperties = new Properties();
		Properties customProperties = InstallerContext.getCustomProperties();
		//System.out.println("Size before storing:"+customProperties.keySet().size());
		//logger.info("Size of custom properties before storing:"+customProperties.keySet().size());
		Iterator iter = customProperties.keySet().iterator();
		while (iter.hasNext()) {
			Object key = (Object)iter.next();
			String keyName = this.getCustomKey((String)key);
			if(props.keySet().contains(keyName)){
				String value = props.getProperty(keyName);
				toBeStoredProperties.put(keyName, value);
			}else{
				toBeStoredProperties.put(keyName, customProperties.getProperty((String)key));
			}
			
		}
		
		try {
			File customPropertiesFile = new File(System.getProperty("user.home"), fileName);
			toBeStoredProperties.store(new FileOutputStream(customPropertiesFile),"caGrid Installer - AutoGenerated properties");
		}
		catch (Throwable ex) {
			//swallow Exceptions as in contract for this method
			//logger.info("Something went wrong with custom properties:"+ex.getMessage());
		}
	}
	
	private String getCustomKey(String key){
		String str = key;
		if(key.startsWith(InstallerContext.CUSTOM_PREFIX)){
			str = key.substring(7);
		}
		return str;
	}
	
	private void storeGridEnvProperties(Properties props){
		//logger.info("Calling storeGridEnvProperties");
		Properties toBeStoredProperties = new Properties();
		Properties gridEnvProperties = InstallerContext.getGridEnviornementProperties();
		//logger.info("Size of the  grid env properties before storing:"+gridEnvProperties.keySet().size());
		Iterator iter = gridEnvProperties.keySet().iterator();
		while (iter.hasNext()) {
			Object key = (Object)iter.next();
			String keyName = this.getGridKey((String)key);
			if(props.keySet().contains(keyName)){
				String value = props.getProperty(keyName);
				//logger.info(" block 1 Key:"+keyName+", value:"+value);
				toBeStoredProperties.put(keyName, value);
			}else{
				toBeStoredProperties.put(keyName, gridEnvProperties.getProperty((String)key));
				//logger.info(" block 2 Key:"+keyName+", value:"+gridEnvProperties.getProperty((String)key));
			}
			
		}
		
		try {
			File gridPropertiesFile = new File(System.getProperty("user.home"), InstallerContext.GRID_ENV_PROPERTIES_FILE_NAME);
			toBeStoredProperties.store(new FileOutputStream(gridPropertiesFile),"caGrid Installer - AutoGenerated properties");
		}
		catch (Throwable ex) {
			//logger.info("Something went wrong with grid env properties:"+ex.getMessage());
		}
	}
	
	private String getGridKey(String key){
		String str = key;
		if(key.startsWith(InstallerContext.GRID_ENV_PREFIX)){
			str = key.substring(8);
		}
		return str;
	}

}
