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
package org.tp23.antinstaller.renderer;

import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.page.Page;
import org.tp23.antinstaller.renderer.swing.SwingOutputFieldRenderer;
import org.tp23.antinstaller.renderer.swing.SwingPageRenderer;
import org.tp23.antinstaller.renderer.text.TextOutputFieldRenderer;
import org.tp23.antinstaller.renderer.text.TextPageRenderer;



/**
 *
 * <p>Fetches an instance of a Renderers by using the class of the method parameter and
 * a naming convention </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: RendererFactory.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class RendererFactory {
	public RendererFactory() {
	}

	public static TextOutputFieldRenderer getTextRenderer(OutputField field) throws ClassNotFoundException {
		String fullyQualified = field.getClass().getName();
		int lastDot = fullyQualified.lastIndexOf('.');
		if (lastDot == -1) {
			throw new UnsupportedOperationException("InputField can not be a member of the default package");
		}
		int prevDot = fullyQualified.substring(0,lastDot-1).lastIndexOf('.',lastDot-1);
		if (prevDot == -1) {
			throw new UnsupportedOperationException("InputField can not be a member of a single level package");
		}
		StringBuffer rendererClassName = new StringBuffer();
		rendererClassName.append(fullyQualified.substring(0, prevDot));
		rendererClassName.append(".renderer.text.");
		rendererClassName.append(fullyQualified.substring(lastDot + 1));
		rendererClassName.append("Renderer");
		try {
			Class clazz = Class.forName(rendererClassName.toString());
			return (TextOutputFieldRenderer) clazz.newInstance();
		}
		catch (Exception ex) {
			throw new ClassNotFoundException("Class does not meet the contract for TextInputFieldRenderer:" + rendererClassName);
		}
	}
	public static TextPageRenderer getTextPageRenderer(Page page) throws ClassNotFoundException {
		String fullyQualified = page.getClass().getName();
		int lastDot = fullyQualified.lastIndexOf('.');
		if (lastDot == -1) {
			throw new UnsupportedOperationException("Pages can not be a member of the default package");
		}
		int prevDot = fullyQualified.substring(0,lastDot-1).lastIndexOf('.',lastDot-1);
		if (prevDot == -1) {
			throw new UnsupportedOperationException("Pages can not be a member of a single level package");
		}
		StringBuffer rendererClassName = new StringBuffer();
		rendererClassName.append(fullyQualified.substring(0, prevDot));
		rendererClassName.append(".renderer.text.");
		rendererClassName.append(fullyQualified.substring(lastDot + 1));
		rendererClassName.append("Renderer");
		try {
			Class clazz = Class.forName(rendererClassName.toString());
			return (TextPageRenderer) clazz.newInstance();
		}
		catch (Exception ex) {
			throw new ClassNotFoundException("Class does not meet the contract for TextPageRenderer:" + rendererClassName);
		}
	}
	public static SwingPageRenderer getSwingPageRenderer(Page page) throws ClassNotFoundException {
		String fullyQualified = page.getClass().getName();
		int lastDot = fullyQualified.lastIndexOf('.');
		if (lastDot == -1) {
			throw new UnsupportedOperationException("Pages can not be a member of the default package");
		}
		int prevDot = fullyQualified.substring(0,lastDot-1).lastIndexOf('.',lastDot-1);
		if (prevDot == -1) {
			throw new UnsupportedOperationException("Pages can not be a member of a single level package");
		}
		StringBuffer rendererClassName = new StringBuffer();
		rendererClassName.append(fullyQualified.substring(0, prevDot));
		rendererClassName.append(".renderer.swing.");
		rendererClassName.append(fullyQualified.substring(lastDot + 1));
		rendererClassName.append("Renderer");
		try {
			Class clazz = Class.forName(rendererClassName.toString());
			return (SwingPageRenderer) clazz.newInstance();
		}
		catch (Exception ex) {
			throw new ClassNotFoundException("Class does not meet the contract for SwingPageRenderer:" + rendererClassName);
		}
	}
	public static SwingOutputFieldRenderer getSwingRenderer(OutputField field) throws ClassNotFoundException {
		String fullyQualified = field.getClass().getName();
		int lastDot = fullyQualified.lastIndexOf('.');
		if (lastDot == -1) {
			throw new UnsupportedOperationException("OutputField can not be a member of the default package");
		}
		int prevDot = fullyQualified.substring(0,lastDot-1).lastIndexOf('.',lastDot-1);
		if (prevDot == -1) {
			throw new UnsupportedOperationException("OutputField can not be a member of a single level package");
		}
		StringBuffer rendererClassName = new StringBuffer();
		rendererClassName.append(fullyQualified.substring(0, prevDot));
		rendererClassName.append(".renderer.swing.");
		rendererClassName.append(fullyQualified.substring(lastDot + 1));
		rendererClassName.append("Renderer");
		try {
			Class clazz = Class.forName(rendererClassName.toString());
			return (SwingOutputFieldRenderer) clazz.newInstance();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ClassNotFoundException("Class does not meet the contract for SwingInputFieldRenderer:" + rendererClassName);
		}
	}
}
