package org.tp23.antinstaller.selfextract;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.tp23.antinstaller.renderer.swing.ImageScaler;

public class ComponentPanel extends JPanel{
	private String componentName;
	public ComponentPanel(String componentName){
		this.componentName=componentName;
	}
	
	public void paintComponent(Graphics g)
	 {  super.paintComponent(g);
	   //this.setForeground(Color.BLUE);
	    super.paintComponent(g);
	    ImageScaler is = new ImageScaler();
	    System.out.println("Component Name:"+componentName);
			ImageIcon icon = new ImageIcon(is.getScaledInstance("resources/"+componentName+".gif",400,300));
			Image image = icon.getImage();
	    if(image != null) g.drawImage(image, 0,0,400,300,this);
	    

	    
	 }

}
