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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import org.tp23.antinstaller.input.InputField;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.input.SecretPropertyField;
import org.tp23.antinstaller.page.Page;

/**
 * <p>Outputs the completed Pages as a Java Properties file. The file produced is compatible
 * with java.util.Properties. </p>
 * <p>Output is commented as it is printed to aid debugging</p>
 * @see http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#100850
 * @author Paul Hinds
 * @version $Id: ExplicitPropertiesFileRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ExplicitPropertiesFileRenderer
	implements PropertiesFileRenderer {

	private static String newLine = System.getProperty("line.separator");
	private static final char[] hexidecimals = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public ExplicitPropertiesFileRenderer() {
	}

	public void renderProperties(Installer installer, File baseDir){
		Page[] completedPages = installer.getPages();

		StringBuffer propertiesData = new StringBuffer();
		propertiesData.append("### Ant Installer - properties auto generated on ");
		propertiesData.append(new Date().toString());
		propertiesData.append(newLine);
		propertiesData.append(newLine);

		propertiesData.append(FILE_ROOT_PROPERTY);
		propertiesData.append(" = ");
		propertiesData.append(baseDir.getAbsolutePath());
		propertiesData.append(newLine);

		propertiesData.append(newLine);
		String property = null;
		String value = null;


		for (int i = 0; i < completedPages.length; i++) {
			OutputField[] fields = completedPages[i].getOutputField();

			propertiesData.append(newLine);
			propertiesData.append("## Properties from Page:" + completedPages[i].getName());
			propertiesData.append(newLine);

			for (int f = 0; f < fields.length; f++) {
				if (fields[f] instanceof SecretPropertyField) {
					InputField field = (InputField) fields[f];
					String result = field.getInputResult();
					propertiesData.append("# Property hidden " + printClass(fields[f].getClass()));
					propertiesData.append(newLine);
					property = convert(field.getProperty(), true);
					propertiesData.append("#" + property + "=XXXXXXXX");
					propertiesData.append(newLine);
				}
				else if (fields[f] instanceof InputField) {
					InputField field = (InputField) fields[f];
					String result = field.getInputResult();
					propertiesData.append("# " + printClass(fields[f].getClass()));
					propertiesData.append(newLine);

					property = convert(field.getProperty(), true);
					value = convert(result, false);
					propertiesData.append(property + " = " + value);
					propertiesData.append(newLine);
				}
			}
		}
		try {
			File antInstallProperties = new File(baseDir.getAbsolutePath(), PROPERTIES_FILE_NAME);
			FileWriter fos = new FileWriter(antInstallProperties);
			BufferedWriter writer = new BufferedWriter(fos);
			writer.write(propertiesData.toString());
			writer.flush();
			fos.close();
		}
		catch (Throwable ex) {
			//swallow Exceptions as in the contract for this method
		}
	}

	private String printClass(Class clazz) {
		String name = clazz.getName();
		int lastDot = name.lastIndexOf('.');
		return name.substring(lastDot, name.length());
	}

	private String convert(String input, boolean doSpaces) {
		if (input == null) {
			// this happens when a page is skipped in text mode
			return "";
		}
		int num = input.length();
		StringBuffer sb = new StringBuffer(num);

		for (int i = 0; i < num; i++) {
			char c = input.charAt(i);
			switch (c) {
				case ' ':
					if (i == 0 || doSpaces) {
						sb.append('\\');
					}
					sb.append(' ');
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '=':
					sb.append("\\=");
					break;
				case ':':
					sb.append("\\:");
					break;
				case '#':
					sb.append("\\#");
					break;
				case '!':
					sb.append("\\!");
					break;

				default:
					if ( (c < 0x0020) || (c > 0x007e) ) {
						sb.append("\\u")
							.append(hex( (c >> 12) & 0xF))
							.append(hex( (c >> 8) & 0xF))
							.append(hex( (c >> 4) & 0xF))
							.append(hex(c & 0xF));
					}
					else {
						sb.append(c);
					}
			}
		}
		return sb.toString();
	}

	private char hex(int val) {
		return hexidecimals[ (val & 0xF)];
	}

}
