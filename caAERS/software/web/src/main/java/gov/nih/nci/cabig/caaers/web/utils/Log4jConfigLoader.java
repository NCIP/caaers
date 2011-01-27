package gov.nih.nci.cabig.caaers.web.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Log4jConfigLoader extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private Thread thread;
    
    @Override
    public void destroy() {
  			thread.interrupt();
  			super.destroy();
  	  }

    public void init() throws ServletException {
		super.init();
		try{
			String prefix =  getServletContext().getRealPath("/");
		    String file = getInitParameter("log4j-init-file");
			MonitorThread monitorThread = new MonitorThread();
			monitorThread.setCheckIntervalMillis(120000); //120 seconds // 2 minutes
			System.out.println("caAERS log4j.xml location --- " + prefix+file);
			monitorThread.setFileName(prefix+file);
			thread = new Thread(monitorThread);
			thread.start();
		}catch(Exception e){
			System.out.println("Could not start log4.xml monitor");
		}
    }

}
