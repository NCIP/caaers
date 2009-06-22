package org.tp23.antinstaller.renderer.swing;

import gov.nih.nci.cagrid.antinstaller.utils.ClassPathModifier;
import gov.nih.nci.cagrid.antinstaller.utils.DataBaseConnectionTester;
import gov.nih.nci.cagrid.installer.certificate.CertificateValidator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.page.SimpleInputPage;
import org.tp23.antinstaller.renderer.RendererFactory;
import org.tp23.gui.GBCF;

public class DataBaseConfigurationPageRenderer extends SwingPageRenderer {
	

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

	public DataBaseConfigurationPageRenderer(){
		try{
		File installRoot = InstallerContext.getLatestInstallDir();
		File resources = new File(installRoot,"resources");
		
		File lib = new File(resources,"lib");
		File oracle_jar = new File(lib,"ojdbc14-10.2.0.2.0.jar");
		File postgres_jar = new File(lib,"postgresql-8.2-504.jdbc3.jar");
		
		
		ClassPathModifier.addFile(oracle_jar);
		ClassPathModifier.addFile(postgres_jar);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
		
	public boolean validateFields() throws ValidationException {
		StringBuffer stbr= new StringBuffer();
		OutputField[] fields = page.getOutputField();
		for (int i = 0; i < fields.length; i++) {
			
			if(!fields[i].validate(ctx)){
				SwingOutputFieldRenderer renderer = (SwingOutputFieldRenderer)renderers.get(i);
				renderer.renderError();
				return false;
			}
		}
		String result = validatePage();
		if(result.length()>1){
		ctx.getMessageRenderer().printMessage(result);
		return false;
		}
		return true;
	}
	
	public String validatePage(){
		String test = "";
		DataBaseConnectionTester dct = new DataBaseConnectionTester();
		
		String dataBaseVendor = ctx.getInstaller().getResultContainer().getDefaultValue("${database.vendor}");
		String dataBaseName =  ctx.getInstaller().getResultContainer().getDefaultValue("${database.name}");
		String hostName=  ctx.getInstaller().getResultContainer().getDefaultValue("${database.host.name}");
		String port = ctx.getInstaller().getResultContainer().getDefaultValue("${database.host.port}");
		String userId =  ctx.getInstaller().getResultContainer().getDefaultValue("${database.userid}");
		String password=  ctx.getInstaller().getResultContainer().getDefaultValue("${database.password}");
		
		boolean connected = dct.testConnection(dataBaseVendor, userId, password, hostName, port, dataBaseName);
		boolean csmPopulated=true;
		if(connected){
			try {
				csmPopulated = dct.csmPopulated(dataBaseVendor, userId, password, hostName, port, dataBaseName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				csmPopulated=true;
			}
		}
		if(csmPopulated){
			ctx.getInstaller().getResultContainer().setProperty("csm.populated", "true");
		}else{
			ctx.getInstaller().getResultContainer().setProperty("csm.populated", "false");
		}
		if(!connected){
			test="There is some problem with connecting to database";
		}
		
		return test;
	}
	public void updateInputFields(){
		for (int i = 0; i < renderers.size(); i++) {
			((SwingOutputFieldRenderer)renderers.get(i)).updateInputField();
		}
	}

	public void updateDefaultValues(){
		for (int i = 0; i < renderers.size(); i++) {
			((SwingOutputFieldRenderer)renderers.get(i)).updateDefaultValue();
		}
	}

	public void instanceInit() throws Exception {
		overflow = ((SimpleInputPage)page).isOverflow();
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

		OutputField[] fields = page.getOutputField();
		contentPanel.setDoubleBuffered(true);
		contentPanel.setLayout(gridLayout);
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
			renderer.initComponent(contentPanel);
			row = renderer.addSelf(contentPanel,cf,row,overflow);
			renderers.add(renderer);
		}
		JPanel filler = new JPanel();
		filler.setOpaque(false);
		//contentPanel.add(new JPanel(),cf.getVertGlue(row++));
		contentPanel.add(filler,cf.getVertGlue(row++));
	}

}
