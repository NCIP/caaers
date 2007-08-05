package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.meddra.*;
import gov.nih.nci.cabig.caaers.dao.MedDRADao;
import gov.nih.nci.cabig.caaers.domain.meddra.*;


import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.AbstractFormController;
import javax.servlet.http.HttpServletRequest;
import org.directwebremoting.WebContextFactory;

/**
 * 
 * @author Krikor Krumlian
 */
public class MeddraAjaxFacade {
   
    private MedDRADao meddraDao;
    private LowLevelTermDao lltDao;
    private String[] files = {"llt.asc", "pt.asc", "hlt.asc", "hlgt.asc", "soc.asc", "hlt_pt.asc", "hlgt_hlt.asc" , "soc_hlgt.asc" };
    
       
    public String handleMedDRA(String path, int step)
	{
    	
    	// This appears to work only with Windows
    	File theFile = new File(path + "\\" + files[step]);
    	System.out.println(path);
    	List al = new ArrayList();
    	String message ="Done";
    	
    	//declared here only to make visible to finally clause
        BufferedReader input = null;
        try {
          //use buffering, reading one line at a time
          //FileReader always assumes default encoding is OK!
          input = new BufferedReader( new FileReader(theFile) );
          //ObjectInputStream in = xstream.createObjectInputStream(input);
          String line;

          while ((line = input.readLine()) != null ) {
        	  //al.add(line.split("\\$"));
              if ( line.length() > 0 ) al.add(line.split("\\$"));
          }
       
          int start= 0;
          int loopEnd = 0;
          int end = al.size() ;
          int increment = 5000;  
             
          while(true){
              loopEnd = start + increment < end ? start + increment : start + (end - start);
              if (step == 0 ) {int[] kk = meddraDao.insertLowLevelTerms(al.subList(start,loopEnd),start);}
              if (step == 1 ) {int[] kk = meddraDao.insertPreferredTerms(al.subList(start,loopEnd),start);}
              if (step == 2 ) {int[] kk = meddraDao.insertHighLevelTerms(al.subList(start,loopEnd),start);}
              if (step == 3 ) {int[] kk = meddraDao.insertHighLevelGroupTerms(al.subList(start,loopEnd),start);}
              if (step == 4 ) {int[] kk = meddraDao.insertSystemOrganClasses(al.subList(start,loopEnd),start);}
              if (step == 5 ) {int[] kk = meddraDao.insertHLTxPT(al.subList(start,loopEnd),start);}
              if (step == 6 ) {int[] kk = meddraDao.insertHLGTxHLT(al.subList(start,loopEnd),start);}
              if (step == 7 ) {int[] kk = meddraDao.insertSOCxHLGT(al.subList(start,loopEnd),start);}
              start = start + increment + 1;
           	  if (loopEnd  == end ) { break;}
             }
        }
        catch (EOFException ex){
            System.out.println("EOF Reached");
          }
        catch (FileNotFoundException ex) {
        	throw new RuntimeException("File Not found Exception", ex);
        }
        catch (IOException ex){
        	throw new RuntimeException("IO Exception", ex);
        }
        finally {
          try {
            if (input!= null) {
              //flush and close both "input" and its underlying FileReader
              input.close();
            }
          }
          catch (IOException ex) {
        	  throw new RuntimeException("IO Exception", ex);
          }
        }
        return(message);
	}
    ////// CONFIGURATION
    
    
    @Required
	public MedDRADao getMeddraDao() {
		return meddraDao;
	}


	public void setMeddraDao(MedDRADao meddraDao) {
		this.meddraDao = meddraDao;
	}

	public LowLevelTermDao getLltDao() {
		return lltDao;
	}

	public void setLltDao(LowLevelTermDao lltDao) {
		this.lltDao = lltDao;
	}
	
	
    
}
