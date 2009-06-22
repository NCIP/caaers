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
package org.tp23.antinstaller.selfextract;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;



/**
 *
 * <p>Frame to indicate progress of the extraction of a SelfExctracting archive </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: ProgressIndicator.java,v 1.2 2006/11/10 17:04:39 kumarvi Exp $
 */
public class ProgressIndicator
	extends JFrame {

	public static final String IMAGE_RESOURCE = "/resources/extract-image.png";
	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");

	private JPanel jPanel1 = new JPanel();
	private JProgressBar jProgressBar1 = new JProgressBar();
	private JLabel textLabel = new JLabel();
	private Border border1;
	private int max = 0;
	private static int PAGE_WIDTH = 160;
	private static int PAGE_HEIGHT = 110; // 35 is text + bar
	private String title = res.getString("extracting");
	private JLabel imagePanel = new JLabel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	private boolean useIcon = true;

    public ProgressIndicator(){
    	
    }

	public ProgressIndicator(int max) {
		this.max = max;
		jbInit();
	}

	public ProgressIndicator(int max, String title) {
		this.max = max;
		this.title = title;
		jbInit();
	}

	private void setLocation() {
		GraphicsConfiguration config = getGraphicsConfiguration();
		int x = (int) config.getBounds().getCenterX() - (PAGE_WIDTH / 2);
		int y = (int) config.getBounds().getCenterY() - (PAGE_HEIGHT / 2);
		setLocation(x, y);
	}


	private void jbInit() {
		border1 = BorderFactory.createCompoundBorder(
			BorderFactory.createBevelBorder(BevelBorder.RAISED),
			BorderFactory.createEmptyBorder(4, 4, 4, 4));
		jPanel1.setLayout(gridBagLayout1);
		int row = 0;
		this.getContentPane().add(jPanel1, BorderLayout.CENTER);
		if (useIcon) {
			PAGE_HEIGHT = 110;
			setImage();
			jPanel1.add(imagePanel, new GridBagConstraints(0, row++, 1, 1, 0.1, 0.9
				, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			this.setSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		}
		else {
			PAGE_HEIGHT = 40;
			this.setSize(new Dimension(PAGE_WIDTH, 35));
		}
		jPanel1.setBorder(border1);
		jPanel1.setMaximumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		jPanel1.setMinimumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		jPanel1.setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		textLabel.setText(title);
		this.setTitle(title);
		jPanel1.add(textLabel, new GridBagConstraints(0, row++, 1, 1, 0.1, 0.1
			, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		jPanel1.add(jProgressBar1, new GridBagConstraints(0, row++, 1, 1, 0.1, 0.1
			, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		jProgressBar1.setMinimum(0);
		jProgressBar1.setMaximum(max);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.setSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		this.setResizable(false);
		this.setUndecorated(true);
		setLocation();
	}

	public void tick() {
		jProgressBar1.setValue(jProgressBar1.getValue() + 1);
	}

	private void setImage() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream in = this.getClass().getResourceAsStream(IMAGE_RESOURCE);
			byte[] buffer = new byte[2048];
			int read = -1;
			while ( (read = in.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
			}
			ImageIcon icon = new ImageIcon(baos.toByteArray());
			imagePanel.setHorizontalAlignment(JLabel.CENTER);
			imagePanel.setIcon(icon);
		}
		catch (Exception ex) {
		}
	}

	public static void main(String[] args) {
		try {
			ProgressIndicator indicator = null;
			indicator = new ProgressIndicator(200);
			indicator.show();
			UIManager.setLookAndFeel("org.tp23.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
		}
		catch (Exception ex) {
			// not concerned about Look and Feel
		}

	}
}
