package org.tp23.antinstaller.selfextract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;


import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;





public class ComponentProgressIndicator extends ProgressIndicator{



	

   
	private JPanel jPanel1;


	private JProgressBar jProgressBar1 = new JProgressBar();
	private JLabel textLabel = new JLabel();
	private Border border1;
	private int max = 0;
	private static int PAGE_WIDTH = 400;
	private static int PAGE_HEIGHT = 300; // 35 is text + bar
	private String title = "Extracting";
	
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	
	private String componentName;

    

	public ComponentProgressIndicator(int max,String componentName) {
		this.max = max;
		this.componentName=componentName;
		jPanel1 = new ComponentPanel(componentName);
		
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
		
		JLabel filler = new JLabel();
		filler.setText("");
		//Font f = new Font("Book Antiqua", Font.ITALIC, 2);
		//developers.setOpaque(false);
		//developers.setForeground(Color.yellow);
		//developers.setText("Scott Oster, Joshua Phillips, Ram Chilukuri, Vinay Kumar, Shanon Hastings");
		//Font f = new Font("Book Antiqua", Font.ITALIC+Font.BOLD, 12);
		/**
		ArrayList lines = this.getDevelopers();
		
		row=4;
		for(int k=0;k<lines.size();k++){
			
			
			JLabel line = new JLabel();
			
			line.setFont(f);
			line.setOpaque(false);
			line.setForeground(Color.yellow);
			String text = (String)lines.get(k);
			System.out.println("Line:"+text);
			line.setText(text);
			jPanel1.add(line, new GridBagConstraints(0, row++, 1, 1, 0.1, 0.1
					, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
            
		}
		*/
		jPanel1.add(filler, new GridBagConstraints(0, row++, 1, 1, 0.1, 2.4
				, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		
		jPanel1.setBorder(border1);
		jPanel1.setMaximumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		jPanel1.setMinimumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		jPanel1.setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		
		textLabel.setText(title);
		this.setTitle(title);
		
		textLabel.setForeground(Color.white);
		
		jPanel1.add(textLabel, new GridBagConstraints(0, row++, 1, 1, 0.1, 0.1
			, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		jPanel1.add(jProgressBar1, new GridBagConstraints(0, row++, 1, 1, 0.1, 0.1
			, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		jProgressBar1.setMinimum(0);
		jProgressBar1.setMaximum(max);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.setSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		this.setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		this.setResizable(false);
		this.setUndecorated(true);
		setLocation();
	}

	public void tick() {
		jProgressBar1.setValue(jProgressBar1.getValue() + 1);
	}

	private ArrayList getDevelopers(){
		ArrayList al = new ArrayList();
		Properties p = new Properties();
		try {
			p.load(this.getClass().getClassLoader().getResource("resources/"+componentName+".properties").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String developers =p.getProperty("developers");
		
		StringTokenizer str = new StringTokenizer(developers,",");
		while(str.hasMoreElements()){
		    String el = (String)str.nextElement();
		   //System.out.println(el.trim());
		   al.add(el.trim());
		   
		}
		
		ArrayList lines = new ArrayList();
		int k=0;
		String line="";
		boolean added= false;
		for(int i=0;i<al.size();i++){
			
			String name = (String)al.get(i);
			
			if(k+name.length()<62){
				line=line+name+",";
				k=k+line.length();
				  added=false;
			}else{
				lines.add(line);
				added=true;
				line=name;
				k=name.length();
				added=false;
				
			}
			
			
		}
		if(!added){
			lines.add(line);
		}
		
		return lines;
	}

	
}
