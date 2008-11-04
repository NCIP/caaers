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
package org.tp23.antinstaller.runtime;

import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.Installer;
import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.ValidationException;
import org.tp23.antinstaller.antmod.FeedbackListener;
import org.tp23.antinstaller.input.OutputField;
import org.tp23.antinstaller.page.Page;
import org.tp23.antinstaller.page.SimpleInputPage;
import org.tp23.antinstaller.renderer.AntOutputRenderer;
import org.tp23.antinstaller.renderer.RendererFactory;
import org.tp23.antinstaller.renderer.swing.PageCompletionListener;
import org.tp23.antinstaller.renderer.swing.SwingInstallerContext;
import org.tp23.antinstaller.renderer.swing.SwingMessageRenderer;
import org.tp23.antinstaller.renderer.swing.SwingPageRenderer;
import org.tp23.antinstaller.renderer.swing.plaf.LookAndFeelFactory;

/**
 *
 * <p>Runs the installer in a JFrame window </p>
 * <p>This class uses the Installer object tree as its data source and renderers
 * from the org.tp23.antinstaller.renderer.swing package </p>
 * Runners must also create a MessageRenderer and make it available in the 
 * InstallerContext
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: SwingRunner.java,v 1.4 2006/11/28 23:30:08 kumarvi Exp $
 */
public class SwingRunner implements Runner,PageCompletionListener{

	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");

	
	private List targets = new ArrayList();
	private SwingInstallerContext swingCtx = null;
	private JFrame frame = new JFrame();
	private List pageRenderers;
	private volatile boolean doAnt = false;
	private Thread initialThread;
	private IfPropertyHelper ifHelper;
	// context local property refs
	private InstallerContext ctx;
	private Logger logger;
	private Installer installer;

	public SwingRunner(InstallerContext ctx) {
		/**
		 * Added by kumarvi
		 */
		frame.setUndecorated(true);
		/**
		 * End of change
		 */
		swingCtx = new SwingInstallerContext(ctx,frame);

		SwingMessageRenderer smr = new SwingMessageRenderer();
		smr.setOwner(frame);
		ctx.setMessageRenderer(smr);
		
		ctx.setBuildListener(new FeedbackListener(swingCtx));

		ifHelper = new IfPropertyHelper(ctx);
		logger = ctx.getLogger();
		installer = ctx.getInstaller();
		this.ctx=ctx;
	}

	/**
	 * Renders the installer in a Swing GUI, this method blocks until
	 * the UI has finished
	 * @throws InstallException
	 * @return boolean false implies user aborted
	 */
	public boolean runInstaller() throws InstallException{
		new LookAndFeelFactory(ctx).setLAF();
		try {
			frame.setTitle(installer.getName());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(SwingPageRenderer.PAGE_WIDTH,SwingPageRenderer.PAGE_HEIGHT);
			frame.getRootPane().setDoubleBuffered(true);
			setLocation(frame);
			setIcon(frame,installer);

			preparePages(installer.getPages(), ctx);
			showFirstPage(installer.getPages()[0]);
			// need to  block here until pages are complete
			initialThread = Thread.currentThread();
			try {
				Thread.sleep(Long.MAX_VALUE);
			}
			catch (InterruptedException ex1) {

			}
			return doAnt;
		}
		catch (Exception ex) {
			logger.log("Fatal exception:"+ex.getMessage());
			if(ctx.getInstaller().isVerbose()){
				logger.log(ex);
			}
			ctx.getMessageRenderer().printMessage("Fatal exception:"+ex.getMessage());
			throw new InstallException("",ex);
		}
	}

	public void pageBack(Page page){
		if (page.isAbort()) {
			abort();
			return;
		}
		Page[] pages = installer.getPages();
		for (int i = 0; i < pages.length; i++) {
			if (pages[i] == page) {
				// found current page
				if (i > 0) {

					//skip pages if the ifTarget or ifProperty attributes exist and fail
					int nextIdx = i - 1;
					for(boolean skip = true;skip==true;nextIdx--){
						if( !ifTarget(pages[nextIdx], pages) ||
							!ifHelper.ifProperty(pages[nextIdx]) ){
							 skip = true;
						 }
						 else break;
					 }

					//for(;ifTargetSkip(pages[nextIdx], pages);nextIdx--);

					SwingPageRenderer renderer = (SwingPageRenderer) pageRenderers.get(nextIdx);
					ctx.setCurrentPage(pages[nextIdx]);
					try {
						renderNext(renderer);
					}
					catch (InstallException ex) {
						logger.log("InstallExcepiton rendering page:"+page.getName());
						if(installer.isVerbose())logger.log(ex);
					}
					catch (ClassNotFoundException ex) {
						logger.log("ClassNotFoundException rendering page:"+page.getName());
						if(installer.isVerbose())logger.log(ex);
					}
					return;
				}
			}
		}
	}
	/**
	 * Called when a page is complete and the next button is pressed.
	 * This method is called by the event thread that looses exceptions so Throwable
	 * is caught
	 * @param page Page
	 */
	public void pageComplete(Page page){
		//System.out.println("Calling pageComplete!!");
		try {
			if(page.isAbort()){
				abort();
				return;
			}
			Page[] pages = installer.getPages();
			SwingPageRenderer currentRenderer;
			for (int i = 0; i < pages.length; i++) {
				if(pages[i]==page){ // found current page
					currentRenderer = (SwingPageRenderer)pageRenderers.get(i);
					// check validation
					boolean validationPassed = false;
					try{
						currentRenderer.updateInputFields();
						validationPassed = currentRenderer.validateFields();
					}catch (ValidationException ve){
						logger.log("ValidationException rendering page:"+page.getName());
						if(installer.isVerbose())logger.log(ve);
						return;
					}
					if(!validationPassed)return;


					if(i<pages.length-1){

						//more pages left

						// skip the page if the ifTarget or ifProperty dictate it
						int nextIdx = i + 1;
						
						for(boolean skip = true;skip==true;nextIdx++){
							if( !ifTarget(pages[nextIdx], pages) ||
								!ifHelper.ifProperty(pages[nextIdx]) ){
								 skip = true;
							 }
							 else break;
						 }


						SwingPageRenderer renderer = (SwingPageRenderer)pageRenderers.get(nextIdx);
						ctx.setCurrentPage(pages[nextIdx]);
						try {
							renderNext(renderer);
						}
						catch (InstallException ex) {
							logger.log("InstallException rendering page:"+page.getName());
							if(installer.isVerbose())logger.log(ex);
						}
						catch (ClassNotFoundException ex) {
							logger.log("ClassNotFoundException rendering page:"+page.getName());
							if(installer.isVerbose())logger.log(ex);
						}
						return;
					}
					if(i==pages.length-1){
						// all done
						currentRenderer.getBackButton().setEnabled(false);
						currentRenderer.getNextButton().setEnabled(false);
						currentRenderer.getFinishButton().setEnabled(false);
						doAnt=true;
						initialThread.interrupt();
						return;
					}
				}
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
			ctx.log("Throwable during page completion:"+e.getMessage());
			StackTraceElement[] ste = e.getStackTrace();
		       
	        int numLines = 5;
	        String msg = e.toString();
	        for (int i = 0; i < (ste.length >numLines ? numLines : 
	        	ste.length) ; i++) {
	            msg = msg + "\n\tat " + ste[i].toString();
	        }
	        ctx.log("Deatiled Message:"+msg);
			if(ctx.getInstaller().isVerbose())ctx.log(e);
		}
	}

	private void showFirstPage(Page page) throws Exception{
		ctx.setCurrentPage(page);
		renderNext((SwingPageRenderer)pageRenderers.get(0));
	}


	private void preparePages(Page[] pages, InstallerContext ctx) throws Exception{
		pageRenderers = new ArrayList();
		for (int i = 0; i < pages.length; i++) {
			SwingPageRenderer renderer = RendererFactory.getSwingPageRenderer(pages[i]);
			if(i==0)renderer.getBackButton().setEnabled(false);
			renderer.setContext(swingCtx);
			renderer.setPageCompletionListener(this);
			renderer.setPage(pages[i]);
			renderer.instanceInit();
			pageRenderers.add(renderer);
			if(renderer instanceof AntOutputRenderer){
				ctx.setAntOutputRenderer((AntOutputRenderer)renderer);
			}
		}
	}

	protected void renderNext(SwingPageRenderer renderer) throws ClassNotFoundException, InstallException{
		renderer.updateDefaultValues();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(renderer);
		frame.getContentPane().repaint();
		frame.show();
		if(renderer.getNextButton().isEnabled())renderer.getNextButton().requestFocus();
		else if(renderer.getFinishButton().isEnabled())renderer.getFinishButton().requestFocus();
	}

	private void setLocation(JFrame frame){
		GraphicsConfiguration config =frame.getGraphicsConfiguration();
		int x = (int)config.getBounds().getCenterX() - (SwingPageRenderer.PAGE_WIDTH/2);
		int y = (int)config.getBounds().getCenterY() - (SwingPageRenderer.PAGE_HEIGHT/2);
		frame.setLocation(x,y);
		frame.setResizable(false);
	}

	private void setIcon(JFrame frame,Installer installer){
		String iconResource = installer.getWindowIcon();
		try {
			if(iconResource==null)return;
			InputStream in = this.getClass().getResourceAsStream(iconResource);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[256];
			int read = 0; // The number of bytes read from the stream
			for (read = in.read(buffer); read != -1; read = in.read(buffer)) {
				baos.write(buffer, 0, read);
			}
			ImageIcon icon = new ImageIcon(baos.toByteArray());
			//Image icon = Toolkit.getDefaultToolkit().createImage(baos.toByteArray());
			frame.setIconImage(icon.getImage());
		}
		catch (Exception ex) {
			// we can live with out an icon
			logger.log("Can not load icon resource:"+iconResource);
			if(installer.isVerbose())logger.log(ex);
		}
	}

	public void antFinished(){
		SwingPageRenderer renderer = (SwingPageRenderer)pageRenderers.get(pageRenderers.size()-1);
		renderer.getBackButton().setEnabled(false);
		renderer.getNextButton().setEnabled(false);
		renderer.getCancelButton().setEnabled(false);
		renderer.getFinishButton().setText(res.getString("exit"));
		renderer.getFinishButton().setEnabled(true);
		renderer.getFinishButton().requestFocus();
		renderer.getTitleLabel().setText(res.getString("complete"));
		ctx.getAntOutputRenderer().getErr().flush();
		ctx.getAntOutputRenderer().getOut().flush();
		ctx.getMessageRenderer().printMessage(res.getString("finished"));
	}
	public void fatalError(){
		SwingPageRenderer renderer = (SwingPageRenderer)pageRenderers.get(pageRenderers.size()-1);
		renderer.getBackButton().setEnabled(false);
		renderer.getNextButton().setEnabled(false);
		renderer.getCancelButton().setEnabled(false);
		renderer.getFinishButton().setText(res.getString("exit"));
		renderer.getFinishButton().setEnabled(true);
		renderer.getFinishButton().requestFocus();
		renderer.getTitleLabel().setText(res.getString("failed"));
	}
	/**
	 * Returns a string representation of the object.
	 * @return a string representation of the object.
	 */
	public String toString() {
		return "SwingRunner";
	}

	private void abort(){
		this.doAnt=false;
		initialThread.interrupt();
	}

	/**
	 * Returns true by default or if the ifTarget is set 
	 * and the target present
	 * @return boolean
	 */
	private boolean ifTarget(Page next, Page[] pages){
		// skip iftarget specified and missing
		if(next instanceof SimpleInputPage){
			SimpleInputPage conditionalPage = (SimpleInputPage) next;
			String ifTarget = conditionalPage.getIfTarget();
			if (ifTarget != null) {
				boolean show = false;
				for (int p = 0; p < pages.length; p++) {
					show |= pages[p].isTarget(ifTarget);
				}
				return show;
			}
		}
		return true;
	}
	/**
	 * @return Returns the frame.
	 */
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * Thi smethod is only valid after the PageRenderers have been generated
	 * @return Returns the pageRenderers.
	 */
	public List getPageRenderers() {
		return pageRenderers;
	}
}
