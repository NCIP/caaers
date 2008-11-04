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
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.SelectInput;


public class SelectInputRenderer
	implements TextOutputFieldRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.text.Res");

	protected InstallerContext ctx;
	public SelectInputRenderer() {
	}

	public void setContext(InstallerContext ctx) {
		this.ctx = ctx;
	}

	public void renderOutput(OutputField field, InputStream in, PrintStream out) throws IOException {
		SelectInput iField = (SelectInput) field;
		printText(iField,out);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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
	private void printText(SelectInput iField, PrintStream out) throws IOException{
		out.println(iField.getDisplayText());
		SelectInput.Option[] options = iField.getOptions();
		out.print("  ");
		out.println(res.getString("enterNumber"));
		for (int i = 0; i < options.length; i++) {
			out.print("  ");
			out.print(i+1);
			out.print(") ");
			out.print(options[i].text);
			if(iField.getDefaultValue().equals(options[i].value)){
				out.print(" [");
				out.print(res.getString("_default_"));
				out.print("]");
			}
			out.println();
		}
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
}
