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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.page.Page;

/**
 *
 * <p>Abstract super class for page renderers.  setPage will always be called. </p>
 * <p>Subclasses should implement instanceInit for initialising swing components
 * on the page. </p>
 * @author Paul Hinds
 * @version $Id: SwingPageRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public abstract class SwingPageRenderer
	extends JPanel {
	
	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");

	//public static int PAGE_WIDTH = 472;
	public static int PAGE_WIDTH = 600;
	public static int PAGE_HEIGHT = 415;
	public static int TITLE_IMAGE_HEIGHT = 100;

	// gui components
	private BorderLayout borderLayout1 = new BorderLayout();
	
	private JPanel controlPanel = new JPanel(){
		/**
		 * added by kumarvi
		 */
		public void paintComponent(Graphics g)
		 {  super.paintComponent(g);
		   this.setForeground(Color.BLUE);
		    super.paintComponent(g);
		    ImageScaler is = new ImageScaler();
				ImageIcon icon = new ImageIcon(is.getScaledInstance("resources/control.jpg",800,90));
				Image image = icon.getImage();
		    if(image != null) g.drawImage(image, 0,0,800,90,this);
		    

		    
		 }
		/**
		 * end of addition
		 */
		
	};

	private JButton backButton = new JButton();
	private JButton cancelButton = new JButton();
	private JButton nextButton = new JButton();
	private JButton finishButton = new JButton();

	private JPanel titlePanel = new JPanel();
	private JLabel titleLabel = new JLabel();
	private JLabel imagePanel = new JLabel();
	private GridLayout titleLayout = new GridLayout();

	// app components
	protected Page page;
	protected SwingInstallerContext swingCtx;
	protected InstallerContext ctx;
	protected PageCompletionListener listener;
	private Border emptyBorder;
	private Border bevelBorder;
		

	private static Font titleFont;
	static{
		titleFont = new JLabel().getFont();
		try {
			titleFont = new Font(titleFont.getFamily(),Font.BOLD,14);
		}
		catch (Exception ex) {
			// lets not fail due to font errors
		}
	}

	public SwingPageRenderer(){
		super();
	}
	public void setPage(Page page){
		this.page=page;
		try {
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setContext(SwingInstallerContext swingCtx){
		this.ctx=swingCtx.getInstallerContext();
		this.swingCtx=swingCtx;
	}
		
	
	private void jbInit() throws Exception {
		/**
		 * Added by kumavi
		 */
		Border brd = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		this.setBorder(brd);
		/**
		 * End of addition
		 */
		
		this.setDoubleBuffered(true);
		this.setBackground(Color.BLUE);
		emptyBorder = BorderFactory.createEmptyBorder(2,5,2,2);
		//bevelBorder = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(116, 116, 112),new Color(166, 166, 161)),BorderFactory.createEmptyBorder(2,SwingInputFieldRenderer.LEFT_INDENT,2,2));
		bevelBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1),BorderFactory.createEmptyBorder(2,SwingOutputFieldRenderer.LEFT_INDENT,2,2));
		bevelBorder = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),BorderFactory.createEmptyBorder(2,SwingOutputFieldRenderer.LEFT_INDENT,2,2));
		Border tripleBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3,4,1,4),bevelBorder);
		this.setLayout(borderLayout1);
		titleLabel.setBorder(tripleBorder);
		//controlPanel.setBorder(tripleBorder); commented out by kumarvi
		
		Color clr = new Color(202,225,255);
		titlePanel.setBackground(clr);
		//controlPanel.setBackground(Color.BLUE); commented out by kumarvi
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(controlPanel, BorderLayout.SOUTH);

		// title panel
		titlePanel.add(imagePanel, null);
		titlePanel.add(titleLabel, null);

		titlePanel.setLayout(titleLayout);
		titleLayout.setColumns(1);
		titleLayout.setHgap(0);
		titleLayout.setRows(2);
		titleLayout.setVgap(2);
		titlePanel.setMinimumSize(new Dimension(PAGE_WIDTH,75));
		titlePanel.setMaximumSize(new Dimension(PAGE_WIDTH,75));
		titlePanel.setPreferredSize(new Dimension(PAGE_WIDTH,75));

		titleLabel.setText(page.getDisplayText());
		titleLabel.setFont(titleFont);
		setImage(page.getImageResource());
		imagePanel.setMinimumSize(new Dimension(PAGE_WIDTH,TITLE_IMAGE_HEIGHT));
		imagePanel.setMaximumSize(new Dimension(PAGE_WIDTH,TITLE_IMAGE_HEIGHT));
		imagePanel.setPreferredSize(new Dimension(PAGE_WIDTH,TITLE_IMAGE_HEIGHT));

		// Ctrl Panel
		controlPanel.setBackground(Color.BLUE);
		
		controlPanel.add(cancelButton, null);
		controlPanel.add(backButton, null);
		controlPanel.add(nextButton, null);
		controlPanel.add(finishButton, null);
		backButton.setText(res.getString("backButton"));// "<< Back");
		cancelButton.setText(res.getString("cancelButton"));// "Cancel");
		nextButton.setText(res.getString("nextButton"));// "Next >>");
		finishButton.setText(ctx.getInstaller().getFinishButtonText());
		finishButton.setEnabled(false);
		setEventListeners();
	}
	public abstract void instanceInit() throws Exception ;
	public abstract void updateInputFields();
	public abstract void updateDefaultValues();
	public abstract boolean validateFields()throws ValidationException;

	public void setPageCompletionListener(PageCompletionListener listener){
		this.listener=listener;
	}

	private void setImage(String resource) throws Exception{
		if(resource==null){
		   resource = ctx.getInstaller().getDefaultImageResource();
		}
		try {
			if (resource != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				InputStream in = this.getClass().getResourceAsStream(resource);
				byte[] buffer = new byte[2048];
				int read = -1;
				while ( (read = in.read(buffer)) != -1) {
					baos.write(buffer, 0, read);
				}
				ImageIcon icon = new ImageIcon(baos.toByteArray());
				imagePanel.setIcon(icon);
			}
		}
		catch (Exception ex) {
			ctx.log("Can't load image resource:"+resource);
			if(ctx.getInstaller().isVerbose())ctx.log(ex);
		}
	}
	
	
	
	private void setEventListeners(){
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				listener.pageBack(page);
			}
		});
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				page.setAbort(true);
				listener.pageComplete(page);
			}
		});
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				listener.pageComplete(page);

			}
		});
		finishButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(finishButton.getText().equals(res.getString("exit"))){
					System.exit(0);
				}
				listener.pageComplete(page);
				//((SwingInstallerContext)ctx).getSwingRunner().finish();
			}
		});
	}
	
	/**
	 * Added by kumarvi
	 * @return
	 */
	
	public void paintComponent(Graphics g)
	 {  super.paintComponent(g);
	   this.setForeground(Color.BLUE);

	    
	    
	    super.paintComponent(g);
	    ImageScaler is = new ImageScaler();
			ImageIcon icon = new ImageIcon(is.getScaledInstance("resources/mainbackground.jpg",600,500));
			Image image = icon.getImage();
	    if(image != null) g.drawImage(image, 0,0,600,500,this);
	    

	    
	 }
	
	/**
	 * End of addition
	 * @return
	 */

	public JButton getCancelButton() {
		return cancelButton;
	}

	public InstallerContext getCtx() {
		return ctx;
	}

	public JPanel getControlPanel() {
		return controlPanel;
	}

	public JLabel getImagePanel() {
		return imagePanel;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}
    public JButton getFinishButton() {
		return finishButton;
    }
    public JButton getBackButton() {
		return backButton;
    }

}
