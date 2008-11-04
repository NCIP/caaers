package org.tp23.antinstaller.renderer.swing.plaf;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.*;
import java.awt.*;

/**
 * @author Paul Hinds
 * @version $Id: ModMetalTheme.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class ModMetalTheme extends DefaultMetalTheme{

        private static final ColorUIResource primary1 = new ColorUIResource(
                                  102, 102, 102);
        private static final ColorUIResource primary2 = new ColorUIResource(
                				  153, 153, 153);
        private static final ColorUIResource primary3 = new ColorUIResource(
                                  204, 204, 204);
        
        
        private static final ColorUIResource secondary1 = new ColorUIResource(
                                  102, 102, 102);
        private static final ColorUIResource secondary2 = new ColorUIResource(
                                  153, 153, 153);
        private static final ColorUIResource secondary3 = new ColorUIResource(
                                  204, 204, 204);


        // these are blue in Metal Default Theme
        protected ColorUIResource getPrimary1() { return primary1; } 
        protected ColorUIResource getPrimary2() { return primary2; }
        protected ColorUIResource getPrimary3() { return primary3; }

        // these are gray in Metal Default Theme
        protected ColorUIResource getSecondary1() { return secondary1; }
        protected ColorUIResource getSecondary2() { return secondary2; }
        protected ColorUIResource getSecondary3() { return secondary3; }

}
