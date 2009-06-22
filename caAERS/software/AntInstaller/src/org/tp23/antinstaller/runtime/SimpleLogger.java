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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * A Logger class that does not report errors
 * @author Paul Hinds
 * @version $Id: SimpleLogger.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class SimpleLogger
	implements Logger {

	BufferedWriter fos;

	public SimpleLogger() {
	}

	/**
	 * This method initialises the logger
	 */
	public void setFileName(String fileName){
		try {
			fos = new BufferedWriter(new FileWriter(fileName,true));
			fos.write("Logger initialized");
			fos.newLine();
		}
		catch (IOException e) {
			fos=null;
		}
	}

	public void log(String message)  {
		if(fos==null)return;
		try {
			fos.write(message);
			fos.newLine();
			fos.flush();
		}
		catch (Exception ex) {
			throw new RuntimeException("Can not write to logs");
		}
	}

	public void log(Throwable exception){
		if(fos==null)return;
		try {
			StringWriter writer = new StringWriter();
			exception.printStackTrace(new PrintWriter(writer));
			fos.write(writer.getBuffer().toString());
			fos.newLine();
		}
		catch (IOException ex) {
			throw new RuntimeException("Can not write to logs");
		}
	}

	public void close(){
		try {
			if(fos!=null){
				fos.flush();
				fos.close();
				fos=null;
			}
		}
		catch (IOException e) {
			System.err.println("Can't close logger");
		}
	}

	/**
	 * Called by the garbage collector on an object when garbage collection
	 * determines that there are no more references to the object.
	 *
	 * @throws Throwable the <code>Exception</code> raised by this method
	 * @todo Implement this java.lang.Object method
	 */
	protected void finalize() throws Throwable {
		if(fos!=null){
			fos.flush();
			fos.close();
		}
	}

}
