package org.tp23.antinstaller.antmod;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.regexp.RegexpMatcher;


/**
 * This class exists because the default Regexp handler is not part of ant.jar
 * @author Paul Hinds
 * @version $Id: RegexpMatcherFactory.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class RegexpMatcherFactory {

	/**
	 * 
	 */
	public RegexpMatcherFactory() {
		super();
	}
    public RegexpMatcher newRegexpMatcher() throws BuildException {
    	try{
    		return new org.apache.tools.ant.util.regexp.RegexpMatcherFactory().newRegexpMatcher(null);
    	}
    	catch(BuildException be){
    		return new Jdk14RegexpMatcher();
    	}
    }
}
