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

import java.io.PrintStream;


/**
 *
 * <p>Used for the Text/Console input to show pages of text as opposed to displaying
* a long list of text that scrolls off the top of the page. </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: Pager.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class Pager {

	private char[] text;
	private int linesPerPage = 20;
	private int charsPerLine = 80;
	private int stringIndex = 0;

	public Pager(String text) {
		this.text = text.toCharArray();
	}
	public Pager(){
	}

	public String getText() {
		return new String(text);
	}

	public void setText(String text) {
		this.text = text.toCharArray();
	}
	/**
	 * Print the rest of the text
	 */
	public void rest(PrintStream out){
		while(next(out));
}
	/**
	 * Print the next page
	 * @param out PrintStream
	 * @return boolean true if there is more text
	 */
	public boolean next(PrintStream out){
		int lineChars = 0;
		int lastSpace = -1;
		// loop past charaters, increment with lines
		char[] lineBuffer = new char[charsPerLine+1];
		for (int lines = 0; lines < linesPerPage;) {
			if(stringIndex>=text.length)return false;
			lineBuffer[lineChars]=text[stringIndex];
			if(text[stringIndex]==' '){
				lastSpace=lineChars;
			}
			if(text[stringIndex]=='\n'){
				String tmp = new String(lineBuffer,0,lineChars+1);
				out.print(tmp);
				lines++;
				lineChars=0;
				lastSpace=-1;
			}
			else if(lineChars==charsPerLine){
				// handle lines ending with the last full word
				if(lastSpace!=-1){
					out.println(new String(lineBuffer,0,lastSpace));
					stringIndex=stringIndex-(charsPerLine-lastSpace);
				}
				else
				{
					out.println(new String(lineBuffer, 0, lineChars));
				}
				lines++;
				lineChars = 0;
				lastSpace=-1;
			}
			else{
				lineChars++;
			}
			stringIndex++;
		}
		return true;
	}
}
