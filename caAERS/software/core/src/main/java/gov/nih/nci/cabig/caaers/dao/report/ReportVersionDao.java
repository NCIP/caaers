package gov.nih.nci.cabig.caaers.dao.report;

import gov.nih.nci.cabig.caaers.dao.GridIdentifiableDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ReportVersionDao extends GridIdentifiableDao<ReportVersion> {
	
	/*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.dao.CaaersDao#domainClass()
     */
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ReportVersion> domainClass() {
        return ReportVersion.class;
    }
    
    /**
     * Get the list of reports due by given date.
     * 
     * @param dueDate
     *                The due date.
     * @return The list of reports.
     */
    public List<ReportVersion> getAllWithTracking() {
    	 String hsql = "from ReportVersion s order by s.id desc";//where s.reportTrackings is not null order by s.report.id";
    	 List<ReportVersion> fullList =  getHibernateTemplate().find(hsql);
    	 //filter
    	 List<ReportVersion> withTrackingInfo = new ArrayList<ReportVersion>();
    	 
    	 
    	 for (ReportVersion rv:fullList) {
    		 if (rv.getReportTrackings().size()>0) {
    			 withTrackingInfo.add(rv);
    		 }
    	 }
    	 return withTrackingInfo;
    	// return fullList;
    }
    public List<ReportVersion> getAllFailedReportsWithTracking() {
   	 String hsql = "from ReportVersion s where s.reportStatus = 5 order by s.id desc";//where s.reportTrackings is not null order by s.report.id";
   	 List<ReportVersion> fullList =  getHibernateTemplate().find(hsql);
   	 //filter
   	 List<ReportVersion> withTrackingInfo = new ArrayList<ReportVersion>();
   	 
   	 
   	 for (ReportVersion rv:fullList) {
   		 if (rv.getReportTrackings().size()>0) {
   			 withTrackingInfo.add(rv);
   		 }
   	 }
   	 return withTrackingInfo;
   	// return fullList;
   }    
    
    public List<ReportVersion> getAllSubmittedReportsInLastGivenNumberOfDays(int days) {
    	List<Object> params = new ArrayList<Object>();
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_YEAR, -days);
    	Date pastDate = cal.getTime();
    	String hsql = "from ReportVersion s where s.submittedOn >= ? order by s.id desc";
    	params.add(pastDate);
    	
    	
    	List<ReportVersion> fullList =   getHibernateTemplate().find(hsql, params.toArray());
//    	filter
      	 List<ReportVersion> withTrackingInfo = new ArrayList<ReportVersion>();
      	 
      	 
      	 for (ReportVersion rv:fullList) {
      		 if (rv.getReportTrackings().size()>0) {
      			 withTrackingInfo.add(rv);
      		 }
      	 }
      	return withTrackingInfo;
    	
    }

    public List<ReportVersion> getAllSubmittedReportsByDateRange(Date sDate, Date eDate) {
    	List<Object> params = new ArrayList<Object>();

    	String hsql = "from ReportVersion s where s.submittedOn >= ? and s.submittedOn <= ? order by s.id desc";
    	params.add(sDate);
    	params.add(eDate);
    	
    	List<ReportVersion> fullList =   getHibernateTemplate().find(hsql, params.toArray());
//    	filter
      	 List<ReportVersion> withTrackingInfo = new ArrayList<ReportVersion>();
      	 
      	 
      	 for (ReportVersion rv:fullList) {
      		 if (rv.getReportTrackings().size()>0) {
      			 withTrackingInfo.add(rv);
      		 }
      	 }
      	return withTrackingInfo;
    	
    }
    
    public List<ReportVersion> getAllInProcessReports() {
    	List<Object> params = new ArrayList<Object>();

    	String hsql = "from ReportVersion s where s.reportStatus = 4";
    	return getHibernateTemplate().find(hsql);
    }
  

}
