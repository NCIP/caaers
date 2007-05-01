package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;
import java.sql.SQLException;

import edu.nwu.bioinformatics.commons.CollectionUtils;

/**
 * @author Krikor Krumlian
 */
public class MedDRADao{
	protected final Log log = LogFactory.getLog(getClass());
	private JdbcTemplate jdbcTemplate;
	
	public int[] insertLowLevelTerms(final List llts, final int startIndex){
		
		String sql = "insert into meddra_llt (id,meddra_code,meddra_term,meddra_pt_id) " +
		 "values (?,?,?,?)";
		
		BatchPreparedStatementSetter setter = null;
		setter = new BatchPreparedStatementSetter(){
			
			int stIndex = startIndex;
			
			public int getBatchSize(){
				return llts.size();
			}
			
			public void setValues(PreparedStatement ps, int index) throws SQLException{
				String[] llt = (String[])llts.get(index);
				ps.setInt(1, new Integer(llt[0]).intValue());
				ps.setString(2,llt[0]);
				ps.setString(3,llt[1]);
				ps.setInt(4, new Integer(llt[2]).intValue());
			}
		};
		
		Object[] params = new Object[] {"test","test"};
		
		return jdbcTemplate.batchUpdate(sql,setter);
		// return jdbcTemplate.update(sql,params);
	}
	
public int[] insertPreferredTerms(final List llts, final int startIndex){
		
		String sql = "insert into meddra_pt (id,meddra_code,meddra_term,meddra_soc_id) " +
		 "values (?,?,?,?)";
		
		BatchPreparedStatementSetter setter = null;
		setter = new BatchPreparedStatementSetter(){
			
			int stIndex = startIndex;
			
			public int getBatchSize(){
				return llts.size();
			}
			
			public void setValues(PreparedStatement ps, int index) throws SQLException{
				String[] llt = (String[])llts.get(index);
				ps.setInt(1, new Integer(llt[0]).intValue());
				ps.setString(2,llt[0]);
				ps.setString(3,llt[1]);
				ps.setInt(4, new Integer(llt[3]).intValue());
			}
		};
		
		Object[] params = new Object[] {"test","test"};
		
		return jdbcTemplate.batchUpdate(sql,setter);
		// return jdbcTemplate.update(sql,params);
	}

public int[] insertHighLevelTerms(final List llts, final int startIndex){
	
	String sql = "insert into meddra_hlt (id,meddra_code,meddra_term) " +
	 "values (?,?,?)";
	
	BatchPreparedStatementSetter setter = null;
	setter = new BatchPreparedStatementSetter(){
		
		int stIndex = startIndex;
		
		public int getBatchSize(){
			return llts.size();
		}
		
		public void setValues(PreparedStatement ps, int index) throws SQLException{
			String[] llt = (String[])llts.get(index);
			ps.setInt(1, new Integer(llt[0]).intValue());
			ps.setString(2,llt[0]);
			ps.setString(3,llt[1]);
		}
	};
	Object[] params = new Object[] {"test","test"};
	
	return jdbcTemplate.batchUpdate(sql,setter);
	// return jdbcTemplate.update(sql,params);
}

	
	public int[] insertHighLevelGroupTerms(final List llts, final int startIndex){
		
		String sql = "insert into meddra_hlgt (id,meddra_code,meddra_term) " +
		 "values (?,?,?)";
		
		BatchPreparedStatementSetter setter = null;
		setter = new BatchPreparedStatementSetter(){
			
			int stIndex = startIndex;
			
			public int getBatchSize(){
				return llts.size();
			}
			
			public void setValues(PreparedStatement ps, int index) throws SQLException{
				String[] llt = (String[])llts.get(index);
				ps.setInt(1, new Integer(llt[0]).intValue());
				ps.setString(2,llt[0]);
				ps.setString(3,llt[1]);
			}
		};
		Object[] params = new Object[] {"test","test"};
		
		return jdbcTemplate.batchUpdate(sql,setter);
		// return jdbcTemplate.update(sql,params);
	}

		
	public int[] insertSystemOrganClasses(final List llts, final int startIndex){
			
		String sql = "insert into meddra_soc (id,meddra_code,meddra_term) " +
			"values (?,?,?)";
			
			BatchPreparedStatementSetter setter = null;
			setter = new BatchPreparedStatementSetter(){
				
				int stIndex = startIndex;
				
				public int getBatchSize(){
					return llts.size();
				}
				
				public void setValues(PreparedStatement ps, int index) throws SQLException{
					String[] llt = (String[])llts.get(index);
					ps.setInt(1, new Integer(llt[0]).intValue());
					ps.setString(2,llt[0]);
					ps.setString(3,llt[1]);
				}
			};
			Object[] params = new Object[] {"test","test"};
			
			return jdbcTemplate.batchUpdate(sql,setter);
			// return jdbcTemplate.update(sql,params);
		}
	
	public int[] insertHLTxPT(final List llts, final int startIndex){
		
		String sql = "insert into meddra_hlt_pt (id,meddra_hlt_id ,meddra_pt_id ) " +
			"values (?,?,?)";
			
			BatchPreparedStatementSetter setter = null;
			setter = new BatchPreparedStatementSetter(){
				
				int stIndex = startIndex;
				
				public int getBatchSize(){
					return llts.size();
				}
				
				public void setValues(PreparedStatement ps, int index) throws SQLException{
					String[] llt = (String[])llts.get(index);
					ps.setInt(1, stIndex++);
					ps.setInt(2,new Integer(llt[0]).intValue());
					ps.setInt(3,new Integer(llt[1]).intValue());
				}
			};
			Object[] params = new Object[] {"test","test"};
			
			return jdbcTemplate.batchUpdate(sql,setter);
			// return jdbcTemplate.update(sql,params);
		}
	
	public int[] insertHLGTxHLT(final List llts, final int startIndex){
		
		String sql = "insert into meddra_hlgt_hlt (id,meddra_hlgt_id ,meddra_hlt_id ) " +
			"values (?,?,?)";
			
			BatchPreparedStatementSetter setter = null;
			setter = new BatchPreparedStatementSetter(){
				
				int stIndex = startIndex;
				
				public int getBatchSize(){
					return llts.size();
				}
				
				public void setValues(PreparedStatement ps, int index) throws SQLException{
					String[] llt = (String[])llts.get(index);
					ps.setInt(1, stIndex++);
					ps.setInt(2,new Integer(llt[0]).intValue());
					ps.setInt(3,new Integer(llt[1]).intValue());
				}
			};
			Object[] params = new Object[] {"test","test"};
			
			return jdbcTemplate.batchUpdate(sql,setter);
			// return jdbcTemplate.update(sql,params);
		}
	
	public int[] insertSOCxHLGT(final List llts, final int startIndex){
		
		String sql = "insert into meddra_soc_hlgt (id,meddra_soc_id ,meddra_hlgt_id ) " +
			"values (?,?,?)";
			
			BatchPreparedStatementSetter setter = null;
			setter = new BatchPreparedStatementSetter(){
				
				int stIndex = startIndex;
				
				public int getBatchSize(){
					return llts.size();
				}
				
				public void setValues(PreparedStatement ps, int index) throws SQLException{
					String[] llt = (String[])llts.get(index);
					ps.setInt(1, stIndex++);
					ps.setInt(2,new Integer(llt[0]).intValue());
					ps.setInt(3,new Integer(llt[1]).intValue());
				}
			};
			Object[] params = new Object[] {"test","test"};
			
			return jdbcTemplate.batchUpdate(sql,setter);
			// return jdbcTemplate.update(sql,params);
		}



	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public class MedDRA{
		private String MedDRACode;
		private String MedDRATerm;
		private String COSTARTSym;
		private String HARTSCode;
		private String WHOARTCode;
		private String ICD10Code;
		private String ICD9Code;
		private String ICD9CMCode;
		private String JARTCode;
		
		
		public MedDRA(String medDRACode, String medDRATerm, String sym, String code, String code2, String code3, String code4, String code5, String code6) {
			super();
			MedDRACode = medDRACode;
			MedDRATerm = medDRATerm;
			COSTARTSym = sym;
			HARTSCode = code;
			WHOARTCode = code2;
			ICD10Code = code3;
			ICD9Code = code4;
			ICD9CMCode = code5;
			JARTCode = code6;
		}
		public String getCOSTARTSym() {
			return COSTARTSym;
		}
		public void setCOSTARTSym(String sym) {
			COSTARTSym = sym;
		}
		public String getHARTSCode() {
			return HARTSCode;
		}
		public void setHARTSCode(String code) {
			HARTSCode = code;
		}
		public String getICD10Code() {
			return ICD10Code;
		}
		public void setICD10Code(String code) {
			ICD10Code = code;
		}
		public String getICD9CMCode() {
			return ICD9CMCode;
		}
		public void setICD9CMCode(String code) {
			ICD9CMCode = code;
		}
		public String getICD9Code() {
			return ICD9Code;
		}
		public void setICD9Code(String code) {
			ICD9Code = code;
		}
		public String getJARTCode() {
			return JARTCode;
		}
		public void setJARTCode(String code) {
			JARTCode = code;
		}
		public String getMedDRACode() {
			return MedDRACode;
		}
		public void setMedDRACode(String medDRACode) {
			MedDRACode = medDRACode;
		}
		public String getMedDRATerm() {
			return MedDRATerm;
		}
		public void setMedDRATerm(String medDRATerm) {
			MedDRATerm = medDRATerm;
		}
		public String getWHOARTCode() {
			return WHOARTCode;
		}
		public void setWHOARTCode(String code) {
			WHOARTCode = code;
		}
		
		
	}
	

}
