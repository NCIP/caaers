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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.input.CommentOutput;
import org.tp23.antinstaller.input.OutputField;


public class CommentOutputRenderer
	implements TextOutputFieldRenderer {
	protected InstallerContext ctx;
	public CommentOutputRenderer() {
	}

	public void setContext(InstallerContext ctx) {
		this.ctx = ctx;
	}

	public void renderOutput(OutputField field, InputStream in, PrintStream out) throws IOException {
		CommentOutput comField = (CommentOutput) field;
		String text = field.getDisplayText();
		if(comField.getBold()!=null && (
			  comField.getBold().equalsIgnoreCase("true")||
			  comField.getBold().equalsIgnoreCase("yes"))
			){
			text=text.toUpperCase();
		}
		else if(comField.getTitle()!=null && (
			  comField.getTitle().equalsIgnoreCase("true")||
			  comField.getTitle().equalsIgnoreCase("yes"))
			){
			text=text.toUpperCase();
		}

		out.println(text);
	}
	public boolean isAbort(){
		return false;
	}



	/**
	 * renderError
	 *
	 * @param field InputField
	 * @param in InputStream
	 * @param out PrintStream
	 */
	public void renderError(OutputField field, InputStream in, PrintStream out) {
	}
}
