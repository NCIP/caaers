package org.tp23.antinstaller.renderer.swing;

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

import gov.nih.nci.cagrid.antinstaller.utils.ClassPathModifier;
import gov.nih.nci.cagrid.installer.certificate.CertificateValidator;

public class CaExistPageRenderer  extends SwingPageRenderer{

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

	public CaExistPageRenderer(){
		try{
		File installRoot = InstallerContext.getLatestInstallDir();
		File resources = new File(installRoot,"resources");
		
		File lib = new File(resources,"lib");
		File cert_task_jar = new File(lib,"certificate_tasks.jar");
		File ext = new File(lib,"ext");
		File core_jar = new File(ext,"caGrid-1.0-core.jar");
		File grid_ca_jar = new File(ext,"caGrid-1.0-gridca-1.0.jar");
		File jglobus_jar = new File(ext,"cog-jglobus.jar");
		File jce_jar = new File(ext,"jce-jdk13-125.jar");
		
		ClassPathModifier.addFile(cert_task_jar);
		ClassPathModifier.addFile(core_jar);
		ClassPathModifier.addFile(grid_ca_jar);
		ClassPathModifier.addFile(jglobus_jar);
		ClassPathModifier.addFile(jce_jar);
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
		CertificateValidator cv = new CertificateValidator();
		StringBuffer stbr = new StringBuffer();
		String caCertFile = ctx.getInstaller().getResultContainer().getDefaultValue("${ca.cert.file}");
		String cakeyFile =  ctx.getInstaller().getResultContainer().getDefaultValue("${ca.key.file}");
		String cakeyPwd =  ctx.getInstaller().getResultContainer().getDefaultValue("${ca.keypwd}");
		
		stbr.append(cv.validateCert(caCertFile));
		if(stbr.toString().length()>1){
			return stbr.toString();
		}
		
		stbr.append(cv.validateKey(cakeyFile,cakeyPwd));
		if(stbr.toString().length()>1){
			return stbr.toString();
		}
		String host_dv = ctx.getInstaller().getResultContainer().getDefaultValue("${host.dv}");
		Integer host_days= null;
		try{
			host_days = new Integer(host_dv);
		}catch(NumberFormatException nfe){
			stbr.append("The days valid for host can only be a number !");
			
		}
		if(stbr.toString().length()>1){
			return stbr.toString();
		}
		
		stbr.append(cv.validateHostCertificateExpiry(caCertFile, host_days.intValue()));
		
		
		
		return stbr.toString();
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
