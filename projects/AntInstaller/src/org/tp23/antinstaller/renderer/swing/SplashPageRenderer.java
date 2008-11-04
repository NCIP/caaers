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
package org.tp23.antinstaller.renderer.swing;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.page.SplashPage;
import org.tp23.antinstaller.runtime.ConfigurationException;

public class SplashPageRenderer extends SwingPageRenderer{

	JLabel imagePanel = new JLabel();

	public SplashPageRenderer() {
	}
	public boolean validateFields()throws ValidationException{
		return true; // @todo option to force accepting or tick box to accept
	}

	public void instanceInit() throws Exception {
		String resource = ((SplashPage)page).getSplashResource();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream in = this.getClass().getResourceAsStream(resource);
		if(in==null)throw new ConfigurationException("Splash page resource is missing:"+resource);
		byte[] buffer = new byte[2048];
		int read = -1;
		while ( (read = in.read(buffer)) != -1) {
			baos.write(buffer, 0, read);
		}
		ImageIcon icon = new ImageIcon(baos.toByteArray());
		imagePanel.setHorizontalAlignment(JLabel.CENTER);
		imagePanel.setIcon(icon);
		this.add(imagePanel, BorderLayout.CENTER);
	}

	public void updateInputFields(){
		;
	}



	/**
	 * updateDefaultValues
	 */
	public void updateDefaultValues() {
	}
}
