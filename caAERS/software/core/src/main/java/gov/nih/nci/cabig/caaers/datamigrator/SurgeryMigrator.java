/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.datamigrator;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Does the migration of Surgery 
 * @author: Biju Joseph
 */
public class SurgeryMigrator extends CaaersDataMigratorTemplate {


    public CaaersDataMigrator.MigraorType migratorType() {
        return CaaersDataMigrator.MigraorType.SURGERY;
    }


    @Override
    public void migrate(CaaersDataMigratorTemplate.CaaersDataMigrationContext ctx) {
        String radiationQuery = new StringBuilder("select so.study_id , sd.id from ae_surgery_interventions sd ")
            .append("join ae_reports r on sd.report_id = r.id  ")
            .append("join ae_reporting_periods rp on rp.id = r.reporting_period_id   ")
            .append("join participant_assignments a on a.id = rp.assignment_id   ")
            .append("join study_organizations so on so.id = a.study_site_id    ")
            .append("order by so.study_id")
            .toString();

       final HashSet<String> studyIdSet = new HashSet<String>();
       final HashMap<String, String> surgeryIdMap = new HashMap<String, String>();
       getJdbcTemplate().query(radiationQuery, new ResultSetExtractor(){
           public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
               while(rs.next()){
                   String studyId = getString(rs, 1);
                   String surgeryId = getString(rs, 2);

                   if(studyId != null && surgeryId != null){
                       studyIdSet.add(studyId);
                       surgeryIdMap.put(surgeryId, studyId);
                   }
               }

               return null;
           }

           //some PostgresSQL driver will throw NPE when column is SQL NULL
           // so this workaround....
           public String getString(ResultSet rs , int index) throws SQLException{
                Object o = rs.getObject(index);
                if(o == null) return null;
                return String.valueOf(o);
           }
       });

       if(!studyIdSet.isEmpty())  {
           ArrayList<String> studySurgerySQLs = new ArrayList<String>();
           studySurgerySQLs.addAll(generateOtherStudyInterventionInsertSQL(ctx, studyIdSet));
           studySurgerySQLs.addAll(generateSurgeryInterventionUpdateSQL(surgeryIdMap));

           String[] sqls = studySurgerySQLs.toArray(new String[]{});

           if(log.isDebugEnabled()){
            log.debug("SQLs to run [Surgery Data Migrator] ..." );
            for(String sql : sqls) log.debug(" >>> " + sql);
           }

           getJdbcTemplate().batchUpdate(sqls);
       }


    }

    public ArrayList<String> generateOtherStudyInterventionInsertSQL(CaaersDataMigratorTemplate.CaaersDataMigrationContext ctx, HashSet<String> set){
        ArrayList<String> sqls = new ArrayList<String>();
        StringBuilder sb = new StringBuilder("insert into other_interventions (")
        .append(ctx.isOracle() ? "id, " : " ")
        .append("name, description, study_therapy_type, study_id ) values (")
        .append(ctx.isOracle() ? "seq_other_interventions_id.nextval, " : " ")
        .append("'Surgery-1', 'Surgery 1', 3, ")
        .append("%s")
        .append(")");

        String queryTemplate = sb.toString();

        for(String s : set) {
            sqls.add(String.format(queryTemplate, s));
        }
        return sqls;
    }


    public ArrayList<String> generateSurgeryInterventionUpdateSQL(HashMap<String, String> map){
        ArrayList<String> sqls = new ArrayList<String>();
        String queryTemplate = "update ae_surgery_interventions set study_intervention_id = ( " +
                "select id from other_interventions where study_id = %s and name='Surgery-1'" +
                ") where id = %s";
        for(Map.Entry<String,String> e: map.entrySet()){
            sqls.add(String.format(queryTemplate, e.getValue(), e.getKey()));
        }
        return sqls;
    }
}
