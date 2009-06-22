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
package org.tp23.antinstaller.renderer.text;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ResourceBundle;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.input.InputField;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.PasswordTextInput;
import org.tp23.antinstaller.input.ValidatedTextInput;


public class PasswordTextInputRenderer extends ValidatedTextInputRenderer

	implements TextOutputFieldRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.text.Res");

	protected InstallerContext ctx;
	public PasswordTextInputRenderer() {
	}

	public void setContext(InstallerContext ctx) {
		this.ctx = ctx;
	}

	public void renderOutput(OutputField field, InputStream in, PrintStream out) throws IOException {
		PasswordTextInput iField = (PasswordTextInput) field;
		StringBuffer displayText = new StringBuffer();
		displayText.append(field.getDisplayText());
		displayText.append("   [");
		displayText.append(res.getString("_default_"));
		displayText.append(":");
		displayText.append(iField.getDefaultValue());
		displayText.append("]");

		String input = null;
		if(OutputField.isTrue(iField.getTextMask())){
			input = new PasswordField().getPassword(displayText.toString());
			System.out.print("\r                                                      ");
		}
		else {
			out.println(displayText.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			input = reader.readLine();
		}

		out.println();
		out.println();
		if(input==null || input.equals(""))input=iField.getDefaultValue();
		iField.setInputResult(input);
	}
	public void renderError(OutputField field, InputStream in, PrintStream out) throws IOException{
		out.println("The password is not of the correct format");
		renderOutput(field, in, out);
	}
	public boolean isAbort(){
		return false;
	}
	
	// shame this does not work
	// does any one know a way to not echo passwords?
//	private String readInput(InputStreamReader reader, PrintStream out) throws IOException{
//		StringBuffer sb = new StringBuffer();
//		char c = 0;
//		while((c=(char)reader.read())!='\n'){
//			if(c==8)sb.setLength(sb.length()-1);
//			sb.append(c);
//			out.print((char)8);
//			out.flush();
//		}
//		return sb.toString();
//	}
	
	/*
	 * 
	 * Taken from the SUN website
	 * @author Paul Hinds
	 * @version $Id: PasswordTextInputRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
	 */
	class MaskingThread extends Thread {
		private boolean stop = false;
		private int index;
		private String prompt;

		public MaskingThread(String prompt) {
			this.prompt = prompt;
		}

		public void run() {
			while (!stop) {
				try {
					// attempt masking at this rate
					this.sleep(1);
				}
				catch (InterruptedException iex) {
					iex.printStackTrace();
				}
				if (!stop) {
					System.out.print("\r" + prompt + " \r" + prompt);
				}
				System.out.flush();
			}
		}

		public void stopMasking() {
			this.stop = true;
		}
	}
	
	public class PasswordField {

		/**
		 *@param prompt The prompt to display to the user.
		 *@return The password as entered by the user.
		 */
		String getPassword(String prompt) throws IOException {
			// password holder
			StringBuffer password = new StringBuffer();
			MaskingThread maskingthread = new MaskingThread(prompt);
			Thread thread = new Thread(maskingthread);
			thread.start();
			// block until enter is pressed
			while (true) {
				char c = (char) System.in.read();
				// assume enter pressed, stop masking
				maskingthread.stopMasking();
				if (c == '\r') {
					c = (char) System.in.read();
					if (c == '\n') {
						break;
					}
					else {
						continue;
					}
				}
				else if (c == '\n') {
					break;
				}
				else {
					// store the password
					password.append(c);
				}
			}
			return password.toString();
		}
	}
}
