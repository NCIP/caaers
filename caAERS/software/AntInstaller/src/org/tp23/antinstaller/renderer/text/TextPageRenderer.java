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
import java.util.ResourceBundle;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.page.Page;
import org.tp23.antinstaller.renderer.PageRenderer;

public abstract class TextPageRenderer
	implements PageRenderer {

	public static final int PAGE_BLANK_LINES = 20;
	public static final int PAGE_DECO_WIDTH = 80;

	protected InputStream in;
	protected PrintStream out;
	protected InstallerContext ctx;


	public TextPageRenderer() {
	}

	public void setContext(InstallerContext ctx){
		this.ctx=ctx;
	}
	public InstallerContext getContext(){
		return ctx;
	}

	public void init( InputStream in, PrintStream out){
		this.out=out;
		this.in=in;
	}
	/**
	 *
	 * @param page Page
	 * @throws IOException
	 * @return boolean false implys user aborted
	 */
	public abstract boolean renderPage(Page page) throws InstallException;

	protected void printHeader(Page page) throws IOException{
		for (int i = 0; i < PAGE_BLANK_LINES; i++) {
			out.println();
		}


		for (int i = 0; i < PAGE_DECO_WIDTH; i++) {
			out.print('~');
		}
		out.println();
		out.println("  "+page.getDisplayText());
		for (int i = 0; i < PAGE_DECO_WIDTH; i++) {
			out.print('~');
		}
		out.println();
		out.println();
		out.println();
	}

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.text.Res");
	private static final char[] affimativeChars = parseChars(res.getString("affirmativeChars"));
	private static char[] parseChars(String commaSeparated){
		char[] input = commaSeparated.toCharArray();
		char[] theChars = new char[input.length];
		int j = 0;
		for (int i = 0; i < input.length; i++) {
			if(Character.isWhitespace(input[i]))continue;
			if(','==input[i])continue;
			else theChars[j++]=input[i];
		}
		char[] toReturn = new char[j];
		System.arraycopy(theChars,0,toReturn,0,j);
		return toReturn;
	}

	/**
	 * does the string represent true default = true
	 * @param entered String
	 * @return boolean
	 */
	protected boolean isTrue(String entered){
		if(entered.length()==0)return true;
		char first = entered.charAt(0);
		boolean isTrue= false;
		for (int i = 0; i < affimativeChars.length; i++) {
			isTrue |= Character.toUpperCase(first) == affimativeChars[i];
		}
		return isTrue;
	}
}
