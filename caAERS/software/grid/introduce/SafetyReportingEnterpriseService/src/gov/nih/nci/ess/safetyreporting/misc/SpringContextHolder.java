/**
 * 
 */
package gov.nih.nci.ess.safetyreporting.misc;

import org.springframework.context.ApplicationContext;

/**
 * Assists in solving the problem where multiple grid services try to load the same Spring context multiple times. 
 * @author Denis G. Krylov
 * 
 */
public final class SpringContextHolder {

	private SpringContextHolder() {
	}

	private static final ThreadLocal<ApplicationContext> holder = new ThreadLocal<ApplicationContext>();

	public static ApplicationContext getApplicationContext() {
		return holder.get();
	}
	
	public static void setApplicationContext(ApplicationContext ctx) {
		holder.set(ctx);
	}	

}
