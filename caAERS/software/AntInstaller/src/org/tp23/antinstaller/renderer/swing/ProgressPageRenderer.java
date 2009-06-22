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
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;

import org.tp23.antinstaller.page.ProgressPage;
import org.tp23.antinstaller.renderer.AntOutputRenderer;
import org.tp23.gui.widget.SystemOutJTextArea;
/**
 *
*
 * <p> </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @todo change absolute to Border layout and panels
*  @author Paul Hinds
 * @version $Id: ProgressPageRenderer.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ProgressPageRenderer
	extends SwingPageRenderer
	implements AntOutputRenderer {

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");
	private static final int MESSAGE_PANEL_HEIGHT = 30;

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

	private JTabbedPane jTabbedPane = new JTabbedPane();
	private JPanel messagesPanel = new JPanel(){
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
	
	
	private BorderLayout borderLayout1 = new BorderLayout();
	private JToggleButton jToggleButton = new JToggleButton();
	private JLabel feedBackLabel = new JLabel(){
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

	private SystemOutJTextArea outPanel = new SystemOutJTextArea();
	private SystemOutJTextArea errPanel = new SystemOutJTextArea();
	
	
	private boolean showTargets = true;
	private JScrollPane progressScrollPane = new JScrollPane();;
	private ProgressPanel progressPanel;

	public ProgressPageRenderer() {
		errPanel.setAsSystemErr();
		outPanel.setAsSystemOut();
	}
    /**
	 * getErr
	 * @return PrintStream
	 */
	public PrintStream getErr() {
		return errPanel.getOut();
	}
    /**
	 * getOut
	 * @return PrintStream
	 */
	public PrintStream getOut() {
		return outPanel.getOut();
	}


	/**
	 * instanceInit
	 */
	public void instanceInit() {
		showTargets = ((ProgressPage)page).isShowTargets();
		contentPanel.setLayout(borderLayout1);
		this.add(contentPanel,BorderLayout.CENTER);
		
		//progressScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		messagesPanel.setLayout(null);
		Dimension messPanelSize = new Dimension(PAGE_WIDTH,MESSAGE_PANEL_HEIGHT);
		messagesPanel.setSize(messPanelSize);
		messagesPanel.setPreferredSize(messPanelSize);
		messagesPanel.setMaximumSize(messPanelSize);
		messagesPanel.setMinimumSize(messPanelSize);

		feedBackLabel.setText(res.getString("click")+" "+ctx.getInstaller().getFinishButtonText()+" "+res.getString("toContinue"));
		feedBackLabel.setBounds(new Rectangle(115, 7, 272, 22));
		jToggleButton.setText(res.getString("showDetails"));
		jToggleButton.setBounds(new Rectangle(5, 7, 104, 22));
		messagesPanel.add(jToggleButton, null);
		messagesPanel.add(feedBackLabel, null);
		jToggleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(jToggleButton.isSelected()){
					if(showTargets){
						contentPanel.remove(progressScrollPane);
					}
					contentPanel.add(jTabbedPane, BorderLayout.CENTER);
					contentPanel.doLayout();
					contentPanel.repaint();
				}
				else {
					contentPanel.remove(jTabbedPane);
					if(showTargets){
						contentPanel.add(progressScrollPane, BorderLayout.CENTER);
					}
					contentPanel.doLayout();
					contentPanel.repaint();
				}
			}
		});
		contentPanel.add(messagesPanel, BorderLayout.NORTH);

		if(showTargets){
			progressScrollPane.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));
			contentPanel.add(progressScrollPane, BorderLayout.CENTER);
		}
		jTabbedPane.add(res.getString("output"), outPanel);
		jTabbedPane.add(res.getString("errors"), errPanel);
//		if(OutputField.isTrue( ctx.getInstaller().getAntialiased())){
//			outPanel.setAntialiased(true);
//			errPanel.setAntialiased(true);
//		}

		//jTabbedPane.setVisible(false);
		jTabbedPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		contentPanel.setMaximumSize(new Dimension(PAGE_WIDTH, Integer.MAX_VALUE));
		contentPanel.setMinimumSize(new Dimension(PAGE_WIDTH, 100));
		contentPanel.setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT-100));
		//contentPanel.add(jTabbedPane, null);
		//jTabbedPane.setBounds(new Rectangle(4, MESSAGE_PANEL_HEIGHT, PAGE_WIDTH-12, 214));

		this.getNextButton().setEnabled(false);
		this.getFinishButton().setText(ctx.getInstaller().getFinishButtonText());
		this.getFinishButton().setEnabled(true);
	}

	public void setContext(SwingInstallerContext swingCtx){
		super.setContext(swingCtx);
		if(showTargets){
			progressPanel = new ProgressPanel(swingCtx.getInstallerContext());
			progressScrollPane.getViewport().add(progressPanel);
			this.swingCtx.setProgressPanel(progressPanel);
		}
		this.swingCtx.setFeedBackLabel(feedBackLabel);
	}


	/**
	 * updateInputFields
	 */
	public void updateInputFields() {

	}



	/**
	 * validateFields
	 *
	 * @return boolean
	 */
	public boolean validateFields() {
		return true;
	}



	/**
	 * updateDefaultValues
	 */
	public void updateDefaultValues() {
	}
}
