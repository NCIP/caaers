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
import org.tp23.antinstaller.input.OutputField;



/**
 *
 * <p>Renders text OutputFields, TextOutputFieldRenderer should provide a no args constructor. </p>
 * <p>The package name for TextOutputFieldRenderer is critical</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: TextOutputFieldRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public interface TextOutputFieldRenderer {
	public void setContext(InstallerContext ctx);
	public void renderOutput(OutputField field, InputStream in, PrintStream out) throws IOException;
	/**
	 * Called when validation fails
	 * @param field InputField
	 * @param in InputStream
	 * @param out PrintStream
	 * @throws IOException
	 * @return boolean
	 */
	public void renderError(OutputField field, InputStream in, PrintStream out) throws IOException;
	/** fields have abort for text since each field has its own input line*/
	public boolean isAbort();
}
