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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ResourceBundle;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.input.LargeSelectInput;
import org.tp23.antinstaller.input.OutputField;


public class LargeSelectInputRenderer
	implements TextOutputFieldRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.text.Res");
	private static final String nextChar = res.getString("nextChar");

	protected InstallerContext ctx;
	public LargeSelectInputRenderer() {
	}

	public void setContext(InstallerContext ctx) {
		this.ctx = ctx;
	}

	public void renderOutput(OutputField field, InputStream in, PrintStream out) throws IOException {
		LargeSelectInput iField = (LargeSelectInput) field;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		printText(iField,out,reader);
		String input = reader.readLine();
		out.println();
		if(input==null || input.equals(""))input=iField.getDefaultValue();
		else{
			try{
				int idx = Integer.parseInt(input.trim());
				input = iField.getOptions()[idx-1].value;
			}catch(Exception numFormatOrIndexOutOfBounds){
				return;
			}
		}
		iField.setInputResult(input);
	}
	
	public boolean isAbort(){
		return false;
	}
	private void printText(LargeSelectInput iField, PrintStream out,BufferedReader reader) throws IOException{
		out.println(iField.getDisplayText());
		LargeSelectInput.Option[] options = iField.getOptions();
		out.print("  ");
		out.println(res.getString("availableOptions"));
		StringBuffer optionsData = new StringBuffer();
		for (int i = 0; i < options.length; i++) {
			optionsData.append("  ");
			optionsData.append(i+1);
			optionsData.append(") ");
			optionsData.append(options[i].text);
			if(iField.getDefaultValue().equals(options[i].value)){
				optionsData.append(" [");
				optionsData.append(res.getString("_default_"));
				optionsData.append("]");
			}
			optionsData.append("\n");
		}
		optionsData.append("  ");
		optionsData.append(res.getString("enterNumber")).append("\n");
		Pager pager = new Pager(optionsData.toString());
		String command=null;
		do {
			if (!pager.next(out)) {
				break;
			}
			out.println();
			out.println(getNextInstructions());
			command = reader.readLine();
		}
		while (command.toUpperCase().startsWith( nextChar ));
		pager.rest(out);

	}



	/**
	 * renderError
	 *
	 * @param field InputField
	 * @param in InputStream
	 * @param out PrintStream
	 */
	public void renderError(OutputField field, InputStream in, PrintStream out) throws IOException {
		ctx.getMessageRenderer().printMessage("Not a valid selection");
		renderOutput(field, in, out);
	}
	private String getNextInstructions() {
		return res.getString("large_select_next");
	}
}
