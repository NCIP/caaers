package gov.nih.nci.cabig.caaers.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import java.sql.PreparedStatement;

import java.util.List;
import java.sql.SQLException;

/**
 * @author Krikor Krumlian
 */
public class MedDRADao {
    protected final Log log = LogFactory.getLog(getClass());

    private JdbcTemplate jdbcTemplate;

    /**
     * TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertLowLevelTerms(final List llts, final int startIndex) {

        String sql = "insert into meddra_llt (id,meddra_code,meddra_term,meddra_pt_id) "
                        + "values (?,?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, new Integer(llt[0]).intValue());
                ps.setString(2, llt[0]);
                ps.setString(3, llt[1]);
                ps.setInt(4, new Integer(llt[2]).intValue());
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);

    }

    /**
     * TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertPreferredTerms(final List llts, final int startIndex) {

        String sql = "insert into meddra_pt (id,meddra_code,meddra_term,meddra_soc_id) "
                        + "values (?,?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, new Integer(llt[0]).intValue());
                ps.setString(2, llt[0]);
                ps.setString(3, llt[1]);
                ps.setInt(4, new Integer(llt[3]).intValue());
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertHighLevelTerms(final List llts, final int startIndex) {

        String sql = "insert into meddra_hlt (id,meddra_code,meddra_term) " + "values (?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, new Integer(llt[0]).intValue());
                ps.setString(2, llt[0]);
                ps.setString(3, llt[1]);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * Insert meddra high level group terms. TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertHighLevelGroupTerms(final List llts, final int startIndex) {

        String sql = "insert into meddra_hlgt (id,meddra_code,meddra_term) " + "values (?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, new Integer(llt[0]).intValue());
                ps.setString(2, llt[0]);
                ps.setString(3, llt[1]);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertSystemOrganClasses(final List llts, final int startIndex) {

        String sql = "insert into meddra_soc (id,meddra_code,meddra_term) " + "values (?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, new Integer(llt[0]).intValue());
                ps.setString(2, llt[0]);
                ps.setString(3, llt[1]);
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertHLTxPT(final List llts, final int startIndex) {

        String sql = "insert into meddra_hlt_pt (id,meddra_hlt_id ,meddra_pt_id ) "
                        + "values (?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            int stIndex = startIndex;

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, stIndex++);
                ps.setInt(2, new Integer(llt[0]).intValue());
                ps.setInt(3, new Integer(llt[1]).intValue());
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertHLGTxHLT(final List llts, final int startIndex) {

        String sql = "insert into meddra_hlgt_hlt (id,meddra_hlgt_id ,meddra_hlt_id ) "
                        + "values (?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            int stIndex = startIndex;

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, stIndex++);
                ps.setInt(2, new Integer(llt[0]).intValue());
                ps.setInt(3, new Integer(llt[1]).intValue());
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * TODO kkk
     * 
     * @param llts
     * @param startIndex
     * @return
     */
    public int[] insertSOCxHLGT(final List llts, final int startIndex) {

        String sql = "insert into meddra_soc_hlgt (id,meddra_soc_id ,meddra_hlgt_id ) "
                        + "values (?,?,?)";

        BatchPreparedStatementSetter setter = null;
        setter = new BatchPreparedStatementSetter() {

            int stIndex = startIndex;

            public int getBatchSize() {
                return llts.size();
            }

            public void setValues(PreparedStatement ps, int index) throws SQLException {
                String[] llt = (String[]) llts.get(index);
                ps.setInt(1, stIndex++);
                ps.setInt(2, new Integer(llt[0]).intValue());
                ps.setInt(3, new Integer(llt[1]).intValue());
            }
        };

        return jdbcTemplate.batchUpdate(sql, setter);
    }

    /**
     * Getter for JDBC template.
     * 
     * @return The JDBC template.
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Setter for JDBC template.
     * 
     * @param jdbcTemplate
     *                The JDBC template.
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public class MedDRA {
        private String MedDRACode;

        private String MedDRATerm;

        private String COSTARTSym;

        private String HARTSCode;

        private String WHOARTCode;

        private String ICD10Code;

        private String ICD9Code;

        private String ICD9CMCode;

        private String JARTCode;

        /**
         * TODO kkk
         * 
         * @param medDRACode
         * @param medDRATerm
         * @param sym
         * @param code
         * @param code2
         * @param code3
         * @param code4
         * @param code5
         * @param code6
         */
        public MedDRA(String medDRACode, String medDRATerm, String sym, String code, String code2,
                        String code3, String code4, String code5, String code6) {
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

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getCOSTARTSym() {
            return COSTARTSym;
        }

        /**
         * TODO kkk
         * 
         * @param sym
         */
        public void setCOSTARTSym(String sym) {
            COSTARTSym = sym;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getHARTSCode() {
            return HARTSCode;
        }

        /**
         * TODO kkk
         * 
         * @param code
         */
        public void setHARTSCode(String code) {
            HARTSCode = code;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getICD10Code() {
            return ICD10Code;
        }

        /**
         * TODO kkk
         * 
         * @param code
         */
        public void setICD10Code(String code) {
            ICD10Code = code;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getICD9CMCode() {
            return ICD9CMCode;
        }

        /**
         * TODO kkk
         * 
         * @param code
         */
        public void setICD9CMCode(String code) {
            ICD9CMCode = code;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getICD9Code() {
            return ICD9Code;
        }

        /**
         * TODO kkk
         * 
         * @param code
         */
        public void setICD9Code(String code) {
            ICD9Code = code;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getJARTCode() {
            return JARTCode;
        }

        /**
         * TODO kkk
         * 
         * @param code
         */
        public void setJARTCode(String code) {
            JARTCode = code;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getMedDRACode() {
            return MedDRACode;
        }

        /**
         * TODO kkk
         * 
         * @param medDRACode
         */
        public void setMedDRACode(String medDRACode) {
            MedDRACode = medDRACode;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getMedDRATerm() {
            return MedDRATerm;
        }

        /**
         * TODO kkk
         * 
         * @param medDRATerm
         */
        public void setMedDRATerm(String medDRATerm) {
            MedDRATerm = medDRATerm;
        }

        /**
         * TODO kkk
         * 
         * @return
         */
        public String getWHOARTCode() {
            return WHOARTCode;
        }

        /**
         * TODO kkk
         * 
         * @param code
         */
        public void setWHOARTCode(String code) {
            WHOARTCode = code;
        }

    }

}
