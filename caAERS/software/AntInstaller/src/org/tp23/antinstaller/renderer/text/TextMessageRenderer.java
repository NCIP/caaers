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
import java.io.InputStreamReader;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.renderer.MessageRenderer;

/**
 *
 * <p>Render user messages to the console </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: TextMessageRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class TextMessageRenderer
	implements MessageRenderer {

	private InstallerContext ctx = null;

	public TextMessageRenderer() {
	}

	public void setInstallerContext(InstallerContext ctx){
		this.ctx=ctx;
	}
	public void printMessage(String message){
		System.out.println(message);
	}

	public boolean prompt(String message){
		try {
			System.out.println(message);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = reader.readLine();
			if (line != null && !line.equals("") && line.trim().length() > 0) {
				return Character.toUpperCase(line.trim().charAt(0)) == 'Y' ||
					Character.toUpperCase(line.trim().charAt(0)) == 'T' ;
			}
			return false;
		}
		catch (IOException ex) {
			throw new RuntimeException("IOException",ex);
		}
	}
}
