package org.tp23.antinstaller.renderer.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.Target;
import org.tp23.antinstaller.InstallerContext;


/**
 * @author Paul Hinds
 * @version $Id: ProgressPanel.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ProgressPanel extends JPanel implements Scrollable{

	public static final int tHeight = 19;
	public static final int leftIndent = 15;
	public static final int DONE = 0;
	public static final int INPROGRESS = 1;
	public static final int TODO = 2;
	private static final Color progressColor = new Color(0,125,0);
	private static final Font mainFont = new Font("Dialog", Font.PLAIN,11);
	private static final Font subFont = new Font("Dialog", Font.PLAIN,10);
	List targets = null;
	private final InstallerContext ctx;
	private int mainTargetPos = 0;
	private ProgressModel currentPM = null;

	public ProgressPanel(InstallerContext ctx) {
		super(true);
		this.ctx=ctx;
	}

	public void prepareCalledTargets(){
		List targetStrings = ctx.getInstaller().getTargets();
		Iterator iter = targetStrings.iterator();
		targets = new ArrayList();
		while (iter.hasNext()) {
			String tgt = (String) iter.next();
			targets.add(new ProgressModel(tgt));
		}
		repaint();
	}
	/**
	 * This method assumes that we are send target started methods in order
	 * but that we do not have the information about "depends" targets and have to
	 * insert the information as it arrives.  If a TargetStarted event arrives that
	 * is not the expected target is is assumed to be a depends.
	 * @param buildEvent
	 */
	public void targetStarted(BuildEvent buildEvent){
		try {
			Target tgt = buildEvent.getTarget();
			ProgressModel pm = (ProgressModel)targets.get(mainTargetPos);
            pm.state=INPROGRESS;
			if(tgt.getName().equals(pm.name)){
				// main target
				currentPM=pm;
				mainTargetPos++;
				repaint();// this is inefficient and paints the whole component
				// it also seems to misout on extending the pannel in XP only
				// mainTargetPos could be used to paint only the extension to the view
			}
			else {
				//dependency
				ProgressModel dependency = new ProgressModel(tgt.getName());
				dependency.state=INPROGRESS;
				if(currentPM!=null)currentPM.state=DONE;// this to catch antcall strangenesses
				currentPM=dependency;
				pm.subTargets.add(dependency);
				this.getParent().doLayout();// this is inefficient and paints the whole component
				repaint();
			}
		}
		catch (Exception e) {
			ctx.log(e);
		}
	}
	public void targetFinished(BuildEvent buildEvent) {
		currentPM.state=DONE;
	}

	public void buildFinished(BuildEvent buildEvent) {
        // this is done because antcall sometimes results in targetFinished not being called
        setToDone(targets);
		repaint();
	}
    
    private void setToDone(List pModels){
        if(pModels==null || pModels.size()==0)return;
        Iterator iter = pModels.iterator();
        while (iter.hasNext()) {
            ProgressModel pm = (ProgressModel)iter.next();
            pm.state=DONE;
            setToDone(pm.subTargets);
        }
    }
	public void paint(Graphics g){
		super.paint(g);
		if(targets==null){
			return;
		}
		g.setColor(getBackground());
		g.fillRect(g.getClipBounds().x,
					g.getClipBounds().y,
					g.getClipBounds().width,
					g.getClipBounds().height);
		Iterator iter = targets.iterator();
		int offset = 0;
		for(int i = 1;iter.hasNext();i++){
			ProgressModel pmodel = (ProgressModel)iter.next();
			drawTarget(pmodel, (Graphics2D)g,offset,i<targets.size(),i>1);
			offset += tHeight;
			offset += pmodel.subTargets.size()*tHeight;
		}
	}

	public Dimension getPreferredSize(){
		return getSize();
	}
	/**
	 * @see java.awt.Component#getSize()
	 */
	public Dimension getSize() {
		if(targets==null)return new Dimension(500,tHeight*5);
		int count = targets.size();
		Iterator iter = targets.iterator();
		while(iter.hasNext()){
			ProgressModel pmodel = (ProgressModel)iter.next();
			count += pmodel.subTargets.size();
		}
		return new Dimension(500,count*tHeight);
	}
	/**
	 *
	 * @param target the main target to be rendered
	 * @param g
	 * @param yOffset the vertical offset where this target should be drawn
	 * @param hasMore if false this target is the last in the list
	 * @param hasPrev if false this target is the first in the list
	 */
	private void drawTarget(ProgressModel target, Graphics2D g,int yOffset,boolean hasMore,boolean hasPrev){
		g.setFont(mainFont);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.gray);
		//7 vertical line (up)
		if(hasPrev)g.drawLine(leftIndent+8,yOffset,leftIndent+8,yOffset+8);
		//7 vertical line (down)
		if(hasMore || target.subTargets.size()>0)g.drawLine(leftIndent+8,(tHeight/2)+yOffset,leftIndent+8,tHeight+yOffset);
		// sideways line
		int xOffset = 0;
		g.drawLine(leftIndent+8,8+yOffset,leftIndent+xOffset+20,8+yOffset);
		if(target.state==DONE){
			g.setColor(Color.darkGray);
		}
		if(target.state==INPROGRESS){
			g.setColor(progressColor);
		}
		if(target.state==TODO){
			g.setColor(Color.gray);
		}
		g.fillRoundRect(leftIndent+xOffset+3,yOffset+4,11,9,7,7);
		g.setColor(Color.black);
		g.drawString(target.name,leftIndent+xOffset+22,13+yOffset);
		if(target.subTargets.size()>0){
			Iterator iter = target.subTargets.iterator();
			for(int i = 1;iter.hasNext();i++){
				drawSubTarget((ProgressModel)iter.next(), g,yOffset+=tHeight,hasMore| i<target.subTargets.size());
			}
		}
	}
	private void drawSubTarget(ProgressModel target, Graphics2D g,int yOffset,boolean hasMore){
		g.setFont(subFont);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.gray);
		//7 vertical line (up)
		g.drawLine(leftIndent+8,yOffset,leftIndent+8,yOffset+8);
		//7 vertical line (down)
		if(hasMore)g.drawLine(leftIndent+8,(tHeight/2)+yOffset,leftIndent+8,tHeight+yOffset);
		int xOffset = 15;
		// sideways line
		g.drawLine(leftIndent+8,8+yOffset,leftIndent+xOffset+4,8+yOffset);
		if(target.state==DONE){
			g.setColor(Color.darkGray);
		}
		if(target.state==INPROGRESS){
			g.setColor(progressColor);
		}
		if(target.state==TODO){
			g.setColor(Color.gray);
		}
		g.fillRoundRect(leftIndent+xOffset+4,yOffset+5,9,7,7,7);
		g.setColor(Color.black);
		g.drawString(target.name,leftIndent+xOffset+15,12+yOffset);
	}

    public Dimension getPreferredScrollableViewportSize(){
    	return getPreferredSize();
    }
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction){
    	return tHeight;
    }
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction){
    	return tHeight*3;
    }
    public boolean getScrollableTracksViewportWidth(){
    	return true;
    }
    public boolean getScrollableTracksViewportHeight(){
    	return false;
    }
	public static class ProgressModel{
		int state = TODO;
		String name;
		List subTargets = new ArrayList();

		public ProgressModel(String name){
			this.name=name;
		}

		int getHeight(){
			return tHeight + subTargets.size()*tHeight;
		}
	}
}
