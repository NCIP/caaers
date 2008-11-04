package org.tp23.antinstaller.renderer.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.input.InputField;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.page.SimpleInputPage;
import org.tp23.antinstaller.renderer.RendererFactory;
import org.tp23.gui.GBCF;

public class CaAbsentPageRenderer extends SwingPageRenderer{

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

	public CaAbsentPageRenderer(){
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
		StringBuffer stbr = new StringBuffer();
		String ca_dv = ctx.getInstaller().getResultContainer().getDefaultValue("${ca.dv}");
		String host_dv = ctx.getInstaller().getResultContainer().getDefaultValue("${host.dv}");
		Integer ca_days = null;
		try{
			ca_days = new Integer(ca_dv);
		}catch(NumberFormatException nfe){
			stbr.append("The days valid for CA can only be a number !");
		}
		 
		Integer host_days= null;
		try{
			host_days = new Integer(host_dv);
		}catch(NumberFormatException nfe){
			stbr.append("The days valid for host can only be a number !");
			
		}
		if(stbr.toString().length()>1){
			return stbr.toString();
		}
		int ca_days_int = ca_days.intValue();
		int host_days_int = host_days.intValue();
		if(host_days_int>=ca_days_int){
			stbr.append("Days valid for host certificate has to be less than the days valid for CA !");
		}
		
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
