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
import java.util.ResourceBundle;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.page.LicensePage;
import org.tp23.antinstaller.page.Page;


public class LicensePageRenderer
	extends TextPageRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.text.Res");
	private static final String nextChar = res.getString("nextChar");
	
	private boolean usePaging = false;

	public LicensePageRenderer() {
	}

	public boolean renderPage(Page page) throws InstallException {
		if (page instanceof LicensePage) {
			LicensePage lPage = (LicensePage) page;
			String strUsePaging = lPage.getUsePaging();
			usePaging = strUsePaging!=null && isTrue(strUsePaging);
			return renderLicensePage(lPage);
		}
		else {
			throw new InstallException("Wrong Renderer in LicensePageRenderer.renderPage");
		}
	}

	private boolean renderLicensePage(LicensePage page) throws InstallException {
		try {
			BufferedReader commandReader = new BufferedReader(new InputStreamReader(in));
			out.println();
			out.println(res.getString("clickViewLicense"));
			commandReader.readLine();

			String resource = page.getResource();
			InputStream licensein = this.getClass().getResourceAsStream(resource);
			BufferedReader reader = new BufferedReader(new InputStreamReader(licensein));
			printHeader(page);
			String lineread = null;
			StringBuffer sb = new StringBuffer();

			while ( (lineread = reader.readLine()) != null) {
				sb.append(lineread);
				sb.append('\n');
			}

			String command = null;
			Pager pager = new Pager(sb.toString());
			if (usePaging) {
				do {
					if (!pager.next(out)) {
						break;
					}
					out.println();
					out.println(getNextInstructions());
					command = commandReader.readLine();
				}
				while (command.toUpperCase().startsWith(nextChar));
				pager.rest(out);
			}
			else {
				out.println(pager.getText());
			}

			for (int i = 0; i < PAGE_DECO_WIDTH; i++) {
				out.print('~');
			}
			out.println();
			out.println(res.getString("licenseAccept"));
			command = commandReader.readLine();
			command = command.trim();
			if (isTrue(command)) {
				return true;
			}
			else {
				page.setAbort(true);
				return false;
			}
		}
		catch (IOException ex) {
			throw new InstallException("Not able to read license file", ex);
		}
	}

	private String getNextInstructions() {
		return res.getString("license_next");
	}
}

