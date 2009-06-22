package org.tp23.antinstaller.renderer.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.page.ServicePropertyPage;
import org.tp23.antinstaller.page.SimpleInputPage;
import org.tp23.antinstaller.renderer.RendererFactory;
import org.tp23.gui.GBCF;

public class ServicePropertyPageRenderer extends SwingPageRenderer {

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
	

	private ArrayList renderers = new ArrayList();
	
	private Hashtable displayProperties = new Hashtable();
	private Properties serviceProperties = new Properties();

	public ServicePropertyPageRenderer(){
	}
	
	
	
	private void loadProperties(){
		File servicePropFile = getServicePropertyFile();
		try {
			serviceProperties.load(new FileInputStream(servicePropFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private File getServicePropertyFile(){
		File servicePropFile = null;
		File installDir = InstallerContext.getLatestInstallDir();
		File resourcesDir = new File(installDir,"resources");
		File releaseDir = new File(resourcesDir,"release");
		servicePropFile = new File(releaseDir,"service.properties");
		return servicePropFile;
	}
	
	
		
	public boolean validateFields() throws ValidationException {
		Enumeration enum_ = displayProperties.keys();
		while(enum_.hasMoreElements()){
			String key =(String)enum_.nextElement();
			AITextfield aiField = (AITextfield)displayProperties.get(key);
			serviceProperties.put(key, aiField.getText());
		}
		try {
			serviceProperties.store(new FileOutputStream(this.getServicePropertyFile()), "Service Propeties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public void updateInputFields(){
		;;
	}

	public void updateDefaultValues(){
		;;
	}
	
	private int addSelf(JPanel content,GBCF cf,  int row,boolean overflow,AILabel fieldLabel,AITextfield jTextField ) {
		content.add(fieldLabel,cf.getCell(row,0));
		content.add(jTextField,cf.getCell(row,1));
		if(overflow){
			jTextField.setOverflow(SwingOutputFieldRenderer.OVERFLOW_FIELD_SIZE);
		}
		return ++row;
	}

	public void instanceInit() throws Exception {
		this.loadProperties();
		overflow = ((ServicePropertyPage)page).isOverflow();
		if(overflow){
			//WARNING this causes flickering in the UI 
			contentPanel.setMaximumSize(new Dimension(SwingPageRenderer.PAGE_WIDTH - 50,SwingPageRenderer.PAGE_HEIGHT));
			scroller = new JScrollPane();
			scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroller.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createEmptyBorder(4,4,4,4),
							BorderFactory.createEtchedBorder()				
							));
			add(scroller,BorderLayout.CENTER);
			scroller.getViewport().add(contentPanel);
			//contentPanel.setBackground(Color.red);
		}
		else {
			this.add(contentPanel,BorderLayout.CENTER);
			contentPanel.setBorder(BorderFactory.createEmptyBorder(SwingOutputFieldRenderer.TOP_INDENT,4,4,4));
		}

		
		contentPanel.setDoubleBuffered(true);
		contentPanel.setLayout(gridLayout);
		Enumeration enum_ = serviceProperties.keys();
		int row = 0;
		while(enum_.hasMoreElements()){
			String key = (String)enum_.nextElement();
			AILabel label = new AILabel();
			label.setText(key);
			AITextfield textField = new AITextfield();
			textField.setText((String)serviceProperties.getProperty(key));
			row = this.addSelf(contentPanel, cf, row, overflow, label, textField);
			displayProperties.put(key, textField);
		}
		
		
		JPanel filler = new JPanel();
		filler.setOpaque(false);
		//contentPanel.add(new JPanel(),cf.getVertGlue(row++));
		contentPanel.add(filler,cf.getVertGlue(row++));
	}
}


