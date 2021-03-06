/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cagrid.antinstaller.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * This class is used for modifying the classpath at runtime.There are some use cases
 * when the classpath has to be change at run time.Suppose somebody wants to add a jar file
 * at installation time and claims that a class exist in that jar, then this class will be 
 * used to check if that particular class can be loaded from that jar.
 * 
 * @author Vinay Kumar
 *
 */

public class ClassPathModifier {
	 
	private static final Class[] parameters = new Class[]{URL.class};
	 
	public static void addFile(String s) throws IOException {
		File f = new File(s);
		addFile(f);
	}//end method
	 
	public static void addFile(File f) throws IOException {
		addURL(f.toURL());
	}//end method
	 
	 
	public static void addURL(URL u) throws IOException {
			
		URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
		Class sysclass = URLClassLoader.class;
	 
		try {
			Method method = sysclass.getDeclaredMethod("addURL",parameters);
			method.setAccessible(true);
			method.invoke(sysloader,new Object[]{ u });
		} catch (Throwable t) {
			t.printStackTrace();
			throw new IOException("Error, could not add URL to system classloader");
		}//end try catch
			
	}//end method
	 
	}//end class

