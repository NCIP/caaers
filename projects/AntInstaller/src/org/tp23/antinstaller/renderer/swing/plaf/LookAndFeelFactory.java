package org.tp23.antinstaller.renderer.swing.plaf;

import java.lang.reflect.Method;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.input.OutputField;


/**
 * @author Paul Hinds
 * @version $Id: LookAndFeelFactory.java,v 1.2 2006/11/28 23:22:56 kumarvi Exp $
 */
public class LookAndFeelFactory {

	//public static final String DEFAULT_LAF = "org.tp23.jgoodies.plaf.plastic.PlasticXPLookAndFeel";
	public static final String DEFAULT_LAF = "org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel";

	private final String specifiedLAF;
	private final InstallerContext ctx;
	/**
	 * 
	 */
	public LookAndFeelFactory(InstallerContext ctx) {
		this.ctx=ctx;
		this.specifiedLAF=ctx.getInstaller().getLookAndFeel();
	}

	public void setLAF(){
		String laf = null;
		try{
			if(specifiedLAF.equals("null")){
				return;
			}
			if(specifiedLAF.equals("native")){
				laf=UIManager.getSystemLookAndFeelClassName();
			}
			else if(specifiedLAF==null || specifiedLAF.equals("jgoodies")){
				laf=DEFAULT_LAF;
			}
			else if(specifiedLAF.equals("greymetal")){
				laf="org.tp23.antinstaller.renderer.swing.plaf.ModMetalLookAndFeel";
			}
			else {
				laf=specifiedLAF;
			}
			LookAndFeel look = (LookAndFeel)Class.forName(laf).newInstance();
			UIManager.setLookAndFeel(look);

			boolean antialias = OutputField.isTrue(ctx.getInstaller().getAntialiased());
			// Reflection used here to avoid dependencies on JGoodies
			if(antialias){
				Method setAntialiased = look.getClass().getMethod("setAntiAliased",new Class[]{boolean.class});
				if(setAntialiased!=null)setAntialiased.invoke(null,new Boolean[]{new Boolean(antialias)});
			}
		}catch(Exception ex ){
			ctx.getLogger().log("Can not correctly set Look And Feel:"+ex.getMessage());
			if(ctx.getInstaller().isVerbose())ctx.getLogger().log(ex);
		}
		
	}
}
