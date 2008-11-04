package org.tp23.antinstaller.renderer.swing;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.Document;

import org.tp23.gui.GBCF;


/**
 * @author Paul Hinds
 * @version $Id: AIPasswordField.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class AIPasswordField extends JPasswordField {

	public AIPasswordField() {
		super();
	}

	public AIPasswordField(int columns) {
		super(columns);
	}

	public AIPasswordField(String text) {
		super(text);
	}

	public AIPasswordField(String text, int columns) {
		super(text, columns);
	}

	public AIPasswordField(Document doc, String txt, int columns) {
		super(doc, txt, columns);
	}

	private Dimension prefSize = new Dimension(SwingOutputFieldRenderer.FIELD_WIDTH, SwingOutputFieldRenderer.FIELD_HEIGHT);

	public Dimension getMinimumSize() {
		return prefSize;
	}

	public Dimension getPreferredSize() {
		return prefSize;
	}
	public void setOverflow(Dimension prefSize) {
		this.prefSize = prefSize;
	}

	public Dimension getMaximumSize() {
		return prefSize;
	}
	
}
