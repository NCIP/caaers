/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.datamigrator;

import gov.nih.nci.cabig.caaers.domain.StudyDevice;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Will take care of migrating existing Medical Devices to refer StudyDevice. 
 * @author: Biju Joseph
 */
public class MedicalDeviceDataMigrator extends CaaersDataMigratorTemplate{

    public MigraorType migratorType() {
        return MigraorType.MEDICAL_DEVICE;
    }

    /**
     * Will associate the medical devices to Study device after creating equivalent
     * StudyDevices (of type other).  
     * Step 1: Find the study, and medical device projection.
     * Step 2: Create unique study device per-study
     * Step 3: Associate Medical Device to Study Device
     * @param context
     */
    
    //NOTE : BJ : Assumed that the number of records to migrate will be less than 100. 
    @Override
    public void migrate(CaaersDataMigrationContext context){
        String query = "select so.study_id, md.id, md.brand_name, md.common_name, md.device_type,md.manufacturer_name, " +
                "md.manufacturer_city, md.manufacturer_state, md.model_number, md.catalog_number " +
                "from ae_medical_devices  md " +
                "join ae_reports r on r.id = md.report_id " +
                "join ae_reporting_periods rp on rp.id = r.reporting_period_id " +
                "join participant_assignments a on rp.assignment_id=a.id " +
                "join study_organizations so on so.id = a.study_site_id " +
                "order by so.study_id";
        

        HashMap<String, ArrayList<StudyDeviceWrapper>> studyDeviceMap =
        (HashMap<String, ArrayList<StudyDeviceWrapper>>)getJdbcTemplate().query(query, new ResultSetExtractor(){
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                 //a data structure to hold the merged study device -to- medical device mapping.
                final HashMap<String, ArrayList<StudyDeviceWrapper>> studyDeviceMap = new HashMap<String, ArrayList<StudyDeviceWrapper>>();
                String studyId = null;
                ArrayList<StudyDeviceWrapper> studyDeviceWrapperList = null;
                while(rs.next()){

                    studyId = getString(rs, 1);
                    String mdId = getString(rs, 2);

                    StudyDevice sd = new StudyDevice();
                    sd.setOtherBrandName(getString(rs, 3));
                    sd.setOtherCommonName(getString(rs, 4));
                    sd.setOtherDeviceType(getString(rs, 5));
                    sd.setManufacturerName(getString(rs, 6) );
                    sd.setManufacturerCity(getString(rs, 7));
                    sd.setManufacturerState(getString(rs, 8) );
                    sd.setModelNumber(getString(rs, 9));
                    sd.setCatalogNumber(getString(rs, 10) );

                    studyDeviceWrapperList = studyDeviceMap.get(studyId);
                    if(studyDeviceWrapperList == null){
                      //new study 
                      studyDeviceWrapperList = new ArrayList<StudyDeviceWrapper>();
                      studyDeviceMap.put(studyId, studyDeviceWrapperList);
                    }
                    StudyDeviceWrapper wrapper = StudyDeviceWrapper.findStudyDeviceWrapper(studyDeviceWrapperList, sd);
                    if(wrapper == null){
                       //new study device
                       wrapper = new StudyDeviceWrapper(studyId, sd, mdId);
                       studyDeviceWrapperList.add(wrapper);
                    }else{
                       //merge to existing study device
                       wrapper.addMedicalDeviceId(mdId);
                    }

                    
                }
                return studyDeviceMap; 
            }

            //some PostgresSQL driver will throw NPE when column is SQL NULL
            // so this workaround....
            public String getString(ResultSet rs , int index) throws SQLException{                                              
                Object o = rs.getObject(index);
                if(o == null) return null;
                return String.valueOf(o);
            }
        });

        //now generate the batch statements.
        ArrayList<String> sqls = new ArrayList<String>();
        for(String key : studyDeviceMap.keySet()){
            ArrayList<StudyDeviceWrapper> wrapperList = studyDeviceMap.get(key);
            for(StudyDeviceWrapper wrapper : wrapperList){
                sqls.add(generateInsertStudyDeviceSQL(context, wrapper)); //insert study device.
                sqls.addAll(generateUpdateMedicalDeviceSQL(wrapper)); // scripts to udpate the medical device
            }
        }

        if(log.isDebugEnabled()){
            log.debug("SQLs to run [MedicalDeviceDataMigrator] ..." );
            for(String sql : sqls) log.debug(" >>> " + sql);
        }
        if(!sqls.isEmpty())
            getJdbcTemplate().batchUpdate(sqls.toArray(new String[]{}));
    }

    private String generateInsertStudyDeviceSQL(CaaersDataMigrationContext ctx, StudyDeviceWrapper wrapper){
        StudyDevice sd = wrapper.sd;
        StringBuilder sb = new StringBuilder("INSERT INTO study_devices(");
        sb.append(ctx.isOracle() ? "id, " : " " )
          .append("brand_name, common_name, catalog_number, manufacturer_name,manufacturer_city, manufacturer_state,")
          .append("model_number, device_type, study_id) VALUES (")
          .append(ctx.isOracle() ? "seq_study_devices_id.nextval," : " ")
          .append(_sqlInsert(sd.getBrandName())).append(",")
          .append(_sqlInsert(sd.getCommonName())).append(",")
          .append(_sqlInsert(sd.getCatalogNumber())).append(",")
          .append(_sqlInsert(sd.getManufacturerName())).append(",")
          .append(_sqlInsert(sd.getManufacturerCity())).append(",")
          .append(_sqlInsert(sd.getManufacturerState())).append(",")
          .append(_sqlInsert(sd.getModelNumber())).append(",")
          .append(_sqlInsert(sd.getDeviceType())).append(",")
          .append(wrapper.studyId)
          .append(")");

        return sb.toString();
    }

    private List<String> generateUpdateMedicalDeviceSQL(StudyDeviceWrapper wrapper){
        StudyDevice sd = wrapper.sd;
        StringBuilder sb = new StringBuilder("update ae_medical_devices set study_device_id = (select id from study_devices where ")
        .append("study_id = ").append(wrapper.studyId).append(" and ")
        .append("brand_name ").append(_sqlSelect(sd.getBrandName())).append(" and ")
        .append("common_name ").append(_sqlSelect(sd.getCommonName())).append(" and ")
        .append("catalog_number ").append(_sqlSelect(sd.getCatalogNumber())).append(" and ")
        .append("manufacturer_name ").append(_sqlSelect(sd.getManufacturerName())).append(" and ")
        .append("manufacturer_city ").append(_sqlSelect(sd.getManufacturerCity())).append(" and ")
        .append("manufacturer_state ").append(_sqlSelect(sd.getManufacturerState())).append(" and ")
        .append("model_number ").append(_sqlSelect(sd.getModelNumber())).append(" and ")
        .append("device_type ").append(_sqlSelect(sd.getDeviceType()))
        .append(") where id = ");

        List<String> updateSQLs = new ArrayList<String>();
        for(String medicalDeviceId : wrapper.medicalDeviceIds){
           updateSQLs.add(sb.toString() + medicalDeviceId);
        }

        return updateSQLs;
    }


    private String _sqlInsert(String s){
       if(s == null) return "null";
       return "'" + s + "'";
    }

    private String _sqlSelect(String s){
        if(s == null) return " is null ";
        return  " = '" + s + "'";
    }

    private static class StudyDeviceWrapper{
        public String studyId;
        public StudyDevice sd;
        public List<String> medicalDeviceIds;
        public StudyDeviceWrapper(String studyId, StudyDevice sd, String medicalDeviceId){
            this.studyId = studyId;
            this.sd = sd;
            this.medicalDeviceIds = new ArrayList<String>();
            this.medicalDeviceIds.add(medicalDeviceId);
        }
        public void addMedicalDeviceId(String id){
            medicalDeviceIds.add(id);
        }

        public boolean hasSameStudyDevice(StudyDevice sd){
            if(this.sd == null) return false;
            return this.sd.equals(sd);
        }

        public static StudyDeviceWrapper findStudyDeviceWrapper(ArrayList<StudyDeviceWrapper> list, StudyDevice sd){
            for(StudyDeviceWrapper wrapper : list){
                if(wrapper.hasSameStudyDevice(sd)) return wrapper;
            }
            return null;
        }
    }
    

}
