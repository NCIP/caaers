package org.tp23.antinstaller.runtime.exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;


/**
 * Loads FilterChains from resource files on the classpath with lists of class
 * names listed in order.  In the files lines starting with # and blank
 * lines are ignored
 * @author Paul Hinds
 * @version $Id: FilterFactory.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class FilterFactory {
	
	public static final String FILTER_RESOURCE = "/antinstall-config.fconfig";

	private FilterFactory() {
	}

	public static FilterChain factory(String configResource) throws InstallException{
		try {
			InputStream is = FilterFactory.class.getResourceAsStream(configResource);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String filterClass = null;
			final List filterChain = new ArrayList();
			while((filterClass = br.readLine())!=null){
				if(filterClass.startsWith("#"))continue;
				filterClass = filterClass.trim();
				if(filterClass.equals(""))continue;
				filterChain.add( Class.forName(filterClass).newInstance() );
			}	
			return new DynamicFilterChain(filterChain);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		throw new InstallException("Can not create FilterChain");
	}
		
	static class DynamicFilterChain implements FilterChain{
		private InstallerContext ctx = null;
		private ExecuteFilter[] filters;
		private DynamicFilterChain(List filterChain){
			filters = new ExecuteFilter[filterChain.size()];
			filterChain.toArray(filters);
		}
		public void init(InstallerContext ctx){
			this.ctx = ctx;
		}
		public ExecuteFilter[] getFilters(){
			return filters;
		}
	};
}
