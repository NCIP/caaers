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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.page.LicensePage;
import org.tp23.antinstaller.renderer.RendererFactory;
import org.tp23.antinstaller.runtime.ConfigurationException;
import org.tp23.gui.GBCF;

/**
 *
 * <p>Renders the license page </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
* @todo this could be an input type and simple renderer
 * @author Paul Hinds
 * @version $Id: LicensePageRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class LicensePageRenderer
extends SwingPageRenderer {

private JTextArea licenseTextArea = new JTextArea();
private JScrollPane scrollPane = new JScrollPane();

private JPanel contentPanel = new JPanel(){
	/**
	 * added by kumarvi
	 */
	public void paintComponent(Graphics g)
	 {  super.paintComponent(g);
	   this.setForeground(Color.BLUE);
	    super.paintComponent(g);
	    ImageScaler is = new ImageScaler();
			ImageIcon icon = new ImageIcon(is.getScaledInstance("resources/mainbackground.jpg",600,400));
			Image image = icon.getImage();
	    if(image != null) g.drawImage(image, 0,0,600,400,this);
	    

	    
	 }
	/**
	 * end of addition
	 */
	
};

private GridBagLayout gridLayout = new GridBagLayout();
private GBCF cf = new GBCF(); // GridBagConstraintsFactory
private boolean overflow = false;
// used in overflow
JScrollPane scroller = null;


private ArrayList renderers = new ArrayList();;

public LicensePageRenderer(){
}

public boolean validateFields()throws ValidationException{
	boolean passed= false;
	String agreed =  ctx.getInstaller().getResultContainer().getDefaultValue("${agreed}");
    if(agreed.equalsIgnoreCase("true")){
    	passed=true;
    }
	return passed;
	 // @todo option to force accepting or tick box to accept
}

public void instanceInit() throws Exception {

	
	contentPanel.setMaximumSize(new Dimension(SwingPageRenderer.PAGE_WIDTH - 50,SwingPageRenderer.PAGE_HEIGHT));

		this.add(contentPanel,BorderLayout.CENTER);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(SwingOutputFieldRenderer.TOP_INDENT,4,4,4));
		
		displayLicense();
	//OutputField[] fields = page.getOutputField();
	contentPanel.setDoubleBuffered(true);
	//contentPanel.setLayout(new BorderLayout());
	contentPanel.add(scrollPane);
	
	JPanel licensePanel = new JPanel();
	licensePanel.setOpaque(false);
	OutputField[] fields = page.getOutputField();
	licensePanel.setDoubleBuffered(true);
	licensePanel.setLayout(gridLayout);
	int row = 0;
	for (int i = 0; i < fields.length; i++) {
		SwingOutputFieldRenderer renderer = RendererFactory.getSwingRenderer(fields[i]);
		/**
		 * Commented out following section by Kumarvi
		 */
		//String text = fields[i].getExplanatoryText();
		//if(fields[i].getExplanatoryText()!=null){
			//JTextArea area = new DisplayTextArea(contentPanel.getBackground(),contentPanel.getForeground());
			//area.setIgnoreRepaint(true);
			//area.setText(text);
			//contentPanel.add(area,cf.getSpan(row++));
		//}
		
		/**
		 * End of comments
		 */
		renderer.setOutputField(fields[i]);
		renderer.setInstallerContext(ctx);
		renderer.initComponent(licensePanel);
		row = renderer.addSelf(licensePanel,cf,row,overflow);
		renderers.add(renderer);
	}
	JPanel filler = new JPanel();
	filler.setOpaque(false);
	//contentPanel.add(new JPanel(),cf.getVertGlue(row++));
	licensePanel.add(filler,cf.getVertGlue(row++));
	
	contentPanel.add(licensePanel);
}



private void displayLicense() throws Exception{
	String resource = ((LicensePage)page).getResource();
	InputStream licensein = this.getClass().getResourceAsStream(resource);
	if(licensein==null)throw new ConfigurationException("License resource is missing");
	BufferedReader reader = new BufferedReader(new InputStreamReader(licensein));
	StringBuffer sb = new StringBuffer();
	String line;
	while((line=reader.readLine())!=null){
		sb.append(line);
		sb.append('\n');
	}

	licenseTextArea.setText(sb.toString());
	licenseTextArea.setTabSize(4);
	licenseTextArea.setAutoscrolls(true);
	licenseTextArea.setCaretPosition(0);
	licenseTextArea.setEditable(false);
	licenseTextArea.setLineWrap(true);
	licenseTextArea.setWrapStyleWord(true);
	licenseTextArea.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
	//licenseTextArea.setPreferredSize((new Dimension(SwingPageRenderer.PAGE_WIDTH - 5,SwingPageRenderer.PAGE_HEIGHT-175)));
	scrollPane.getViewport().add(licenseTextArea);
	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPane.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(4,4,4,4),
		            BorderFactory.createEtchedBorder()));
	scrollPane.setPreferredSize(new Dimension(SwingPageRenderer.PAGE_WIDTH - 5,SwingPageRenderer.PAGE_HEIGHT-175));
	
}

public void updateInputFields(){
	for (int i = 0; i < renderers.size(); i++) {
		((SwingOutputFieldRenderer)renderers.get(i)).updateInputField();
	}
}



/**
 * updateDefaultValues
 */
public void updateDefaultValues(){
	for (int i = 0; i < renderers.size(); i++) {
		((SwingOutputFieldRenderer)renderers.get(i)).updateDefaultValue();
	}
}
}