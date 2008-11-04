package org.tp23.antinstaller.renderer.swing;

import java.awt.*;

import java.awt.geom.*;
import java.awt.image.*;

import javax.imageio.*;


public class ImageScaler {

	
	public BufferedImage getScaledInstance(String imageName,int width,int height){
		BufferedImage resizedImage = null;
		try{
		BufferedImage img = ImageIO.read(this.getClass().getClassLoader().getResource(imageName));
		GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage image = toCompatibleImage(img, gc);
        resizedImage = getScaledInstance(image, width, height, gc);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resizedImage;
	}
	private  GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
	
	private  BufferedImage toCompatibleImage(BufferedImage image, GraphicsConfiguration gc) {
        if (gc == null)
            gc = getDefaultConfiguration();
        int w = image.getWidth();
        int h = image.getHeight();
        int transparency = image.getColorModel().getTransparency();
        BufferedImage result = gc.createCompatibleImage(w, h, transparency);
        Graphics2D g2 = result.createGraphics();
        g2.drawRenderedImage(image, null);
        g2.dispose();
        return result;
    }
	
	private  BufferedImage copy(BufferedImage source, BufferedImage target) {
        Graphics2D g2 = target.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        double scalex = (double) target.getWidth()/ source.getWidth();
        double scaley = (double) target.getHeight()/ source.getHeight();
        AffineTransform xform = AffineTransform.getScaleInstance(scalex, scaley);
        g2.drawRenderedImage(source, xform);
        g2.dispose();
        return target;
    }
	
	private  BufferedImage getScaledInstance(BufferedImage image, int width, int height, GraphicsConfiguration gc) {
        
		if (gc == null)
            gc = getDefaultConfiguration();
        int transparency = image.getColorModel().getTransparency();
        return copy(image, gc.createCompatibleImage(width, height, transparency));
    }
	
	
	
	

}
