package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.persistence.Transient;

import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.webservice.*;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;

/**
 * @author Ion C. Olaru
 * Date: Dec 22, 2008 - 10:44:55 AM
 * @author Biju Joseph
 */

public class StudyCommand {

    private Study study;
    private int studySiteIndex = -1; 
    private String[] diseaseTermIds;
    private String diseaseCategoryAsText;

    private String diseaseLlt;
    private String condition;
    protected List<ConfigProperty> allPersonnelRoles;
    protected List<ConfigProperty> allInvestigatorRoles;
    protected Map<String, String> studyPersonnelRoles;
    protected Map<String, String> studyInvestigatorRoles;

    private StudyRepository studyRepository;

    private Integer primaryStudyDisease;
    
    // ----------------------------------------------------------------------------------------------------------------
    
    public static final String STATUS_ADMINISTRATIVELY_COMPLETE = "Administratively Complete";
    public static final String STATUS_ACTIVE = "Active - Trial is open to accrual";

    private String shortTitle;
    private String longTitle;
    private String description;
    private String precis;
    private String phaseCode;
    private AeTerminology aeTerminology;
    private DiseaseTerminology diseaseTerminology;
    private String status;



    private Boolean drugAdministrationTherapyType = Boolean.FALSE;
    private Boolean radiationTherapyType = Boolean.FALSE;
    private Boolean deviceTherapyType = Boolean.FALSE;
    private Boolean surgeryTherapyType = Boolean.FALSE;
    private Boolean behavioralTherapyType = Boolean.FALSE;
    private Boolean biologicalTherapyType = Boolean.FALSE;
    private Boolean geneticTherapyType = Boolean.FALSE;
    private Boolean diaterySupplementTherapyType = Boolean.FALSE;
    private Boolean otherTherapyType = Boolean.FALSE;
    private Integer loadStatus = LoadStatus.COMPLETE.getCode();

    // Used to facilitate import of a coordinating center / funding sponsor
    private FundingSponsor fundingSponsor;
    private CoordinatingCenter coordinatingCenter;

    // DCP specific properties
    private Design design;
    private List<Epoch> epochs=new ArrayList<Epoch>();
    private boolean workflowEnabled;
    private StudyDao studyDao;
    
    private InvestigationalNewDrugDao investigationalNewDrugDao;

    protected StudyCoordinatingCenter prevCC;
    protected StudyFundingSponsor prevFS;
    
    public StudyCommand(StudyDao studyDao, InvestigationalNewDrugDao investigationalNewDrugDao) {
    	this.studyDao = studyDao;
    	this.investigationalNewDrugDao = investigationalNewDrugDao;
        this.studyPersonnelRoles = new HashMap<String, String>();
        this.studyInvestigatorRoles = new HashMap<String, String>();
    }

    // TODO: this stuff should really, really not be in here. It's web-view/entry specific.

    public String getDescription() {
        return description;
    }

    public void setDescription(final String descriptionText) {
        description = descriptionText;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(final String longTitleText) {
        longTitle = longTitleText;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(final String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getPrecis() {
        return precis;
    }

    public void setPrecis(final String precisText) {
        precis = precisText;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(final String shortTitleText) {
        shortTitle = shortTitleText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Integer getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    public Boolean getDrugAdministrationTherapyType() {
        return drugAdministrationTherapyType;
    }

    public void setDrugAdministrationTherapyType(final Boolean drugAdministrationTherapyType) {
        this.drugAdministrationTherapyType = drugAdministrationTherapyType;
    }

    public Boolean getRadiationTherapyType() {
        return radiationTherapyType;
    }

    public void setRadiationTherapyType(final Boolean radiationTherapyType) {
        this.radiationTherapyType = radiationTherapyType;
    }

    public Boolean getDeviceTherapyType() {
        return deviceTherapyType;
    }

    public void setDeviceTherapyType(final Boolean deviceTherapyType) {
        this.deviceTherapyType = deviceTherapyType;
    }

    public Boolean getSurgeryTherapyType() {
        return surgeryTherapyType;
    }

    public void setSurgeryTherapyType(final Boolean surgeryTherapyType) {
        this.surgeryTherapyType = surgeryTherapyType;
    }

    public Boolean getBehavioralTherapyType() {
        return behavioralTherapyType;
    }

    public void setBehavioralTherapyType(final Boolean behavioralTherapyType) {
        this.behavioralTherapyType = behavioralTherapyType;
    }
    
    public Boolean getBiologicalTherapyType() {
		return biologicalTherapyType;
	}
    public void setBiologicalTherapyType(Boolean biologicalTherapyType) {
		this.biologicalTherapyType = biologicalTherapyType;
	}
    public Boolean getGeneticTherapyType() {
		return geneticTherapyType;
	}
    public void setGeneticTherapyType(Boolean geneticTherapyType) {
		this.geneticTherapyType = geneticTherapyType;
	}
    public Boolean getDiaterySupplementTherapyType() {
		return diaterySupplementTherapyType;
	}
    public void setDiaterySupplementTherapyType(Boolean diaterySupplementTherapyType) {
		this.diaterySupplementTherapyType = diaterySupplementTherapyType;
	}
    
    public Boolean getOtherTherapyType() {
		return otherTherapyType;
	}
    public void setOtherTherapyType(Boolean otherTherapyType) {
		this.otherTherapyType = otherTherapyType;
	}
    
    public boolean isTherapyTypeSelected(StudyTherapyType therapyType){
    	switch (therapyType) {
		case DRUG_ADMINISTRATION:
			return BooleanUtils.isTrue(drugAdministrationTherapyType);
		case BEHAVIORAL:
			return BooleanUtils.isTrue(behavioralTherapyType);
		case BIOLOGICAL_VACCINE:
			return BooleanUtils.isTrue(biologicalTherapyType);
		case DEVICE:
			return BooleanUtils.isTrue(deviceTherapyType);
		case DIETARY_SUPPLEMENT:
			return BooleanUtils.isTrue(diaterySupplementTherapyType);
		case GENETIC:
			return BooleanUtils.isTrue(geneticTherapyType);
		case OTHER:
			return BooleanUtils.isTrue(otherTherapyType);
		case RADIATION:
			return BooleanUtils.isTrue(radiationTherapyType);
		case SURGERY:
			return BooleanUtils.isTrue(surgeryTherapyType);
		default : 
			return false;
		}
    }
    
    /**
     * This method will update the study therapies.
     */
    public void updateStudyTherapies() {
    	for(StudyTherapyType therapyType : StudyTherapyType.values()){
    		if(this.isTherapyTypeSelected(therapyType)){
    			StudyTherapy therapy = this.getStudy().getStudyTherapy(therapyType);
    			if(therapy == null) this.getStudy().addStudyTherapy(therapyType);
    		}else{
    			this.getStudy().removeTherapiesOfType(therapyType);
    		}
    	}
    }
    
    public CoordinatingCenter getCoordinatingCenter() {
        return coordinatingCenter;
    }

    public FundingSponsor getFundingSponsor() {
        return fundingSponsor;
    }

    public void setCoordinatingCenter(CoordinatingCenter coordinatingCenter) {
        this.coordinatingCenter = coordinatingCenter;
    }

    public void setFundingSponsor(FundingSponsor fundingSponsor) {
        this.fundingSponsor = fundingSponsor;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
    	this.study= study;
    	//this.study=remoteObjectBinder.bindStudy(study);

    }

    @Transient
    public int getStudySiteIndex() {
        return studySiteIndex; 
    }

    public void setStudySiteIndex(final int studySiteIndex) {
        this.studySiteIndex = studySiteIndex;
    }

    @Transient
    public String[] getDiseaseTermIds() {
        return diseaseTermIds;
    }

    public void setDiseaseTermIds(final String[] diseaseTermIds) {
        this.diseaseTermIds = diseaseTermIds;
    }

    @Transient
    public String getDiseaseCategoryAsText() {
        return diseaseCategoryAsText;
    }

    public void setDiseaseCategoryAsText(final String diseaseCategoryAsText) {
        this.diseaseCategoryAsText = diseaseCategoryAsText;
    }

    @Transient
    public String getDiseaseLlt() {
        return diseaseLlt;
    }

    public void setDiseaseLlt(final String diseaseLlt) {
        this.diseaseLlt = diseaseLlt;
    }

    @Transient
    public String getCondition() {
        return condition;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }
    public boolean isWorkflowEnabled() {
		return workflowEnabled;
	}

    
    public void setWorkflowEnabled(boolean workflowEnabled) {
		this.workflowEnabled = workflowEnabled;
	}
    
    /**
     * This will mark datacomplete the study
     */
    public void openStudy(){
    	study.setDataEntryStatus(true);
    	save();
    }
    
    public boolean isDataEntryComplete(){
    	return BooleanUtils.isTrue(study.getDataEntryStatus()); 
    }
    
    public String getDataEntryStatus(){
    	if(isDataEntryComplete()) return "Complete";
    	return "Inprogress";
    }
    
    public StudyDao getStudyDao() {
		return studyDao;
	}
    public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
    
    public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
		return investigationalNewDrugDao;
	}
    
    public void setInvestigationalNewDrugDao(
			InvestigationalNewDrugDao investigationalNewDrugDao) {
		this.investigationalNewDrugDao = investigationalNewDrugDao;
	}
    
    //will fetch the default IND for CTEP 
    public InvestigationalNewDrug fetchDefaultInvestigationalNewDrugForCTEP(){
    	return investigationalNewDrugDao.fetchCtepInd();
    }
    
    //will fetch the default IND for DCP
    public InvestigationalNewDrug fetchDefaultInvestigationalNewDrugForDCP(){
    	return investigationalNewDrugDao.fetchDcpInd();
    }
    
    /**
     * Will delete or mark as retired the study agent object mentioned.
     * @param index
     */
    public StudyAgent deleteStudyAgentAtIndex(int index){
    	StudyAgent studyAgent = getStudy().getStudyAgents().get(index);
    	if(studyAgent.getId() == null) {
    		getStudy().getStudyAgents().remove(index);
    	}else{
    		studyAgent.retire();
    	}
    	return studyAgent;
    }
    
    /**
     * Will delete or mark as retired the treatment assignment object mentioned.
     * @param index
     */
    public void deleteTreatmentAssignmentAtIndex(int index){
    	delete(study.getTreatmentAssignments(), index);
    }
    
    /**
     * Will delete or mark as retired the study site at specified index.
     * @param index
     */
    public void deleteStudySiteAtIndex(int index){
    	delete(study.getStudySites(), index);
    }
    
    /**
     * Will delete or mark as retired the {@link CtepStudyDisease} at specified index.
     * @param index
     */
    public void deleteCtepStudyDiseaseAtIndex(int index){
    	delete(study.getActiveCtepStudyDiseases(), index);
    }
    

    /**
     * Will delete or mark as retired the {@link MeddraStudyDisease} at specified index.
     * @param index
     */
    public void deleteMeddraStudyDiseaseAtIndex(int index){
    	delete(study.getActiveMeddraStudyDiseases(), index);
    }

    /**
     * Will delete or mark as retired the {@link StudyCondition} at specified index.
     * @param index
     */
    public void deleteStudyConditionAtIndex(int index){
    	delete(study.getActiveStudyConditions(), index);
    }
    
    /**
     * Will delete or mark as retired the {@link SiteInvestigator} at specified index.
     * @param index
     */
    public void deleteSiteInvestigatorAtIndex(int studyOrgIndex, int index){
    	delete(study.getActiveStudyOrganizations().get(studyOrgIndex).getStudyInvestigators(), index);
    }
    
    /**
     * Will delete or mark as retired the {@link StudyPersonnel} at specified index.
     * @param studyOrgIndex
     * @param index
     */
    public void deleteStudyPersonAtIndex(int studyOrgIndex, int index){
    	delete(study.getActiveStudyOrganizations().get(studyOrgIndex).getStudyPersonnels(), index);
    }
    
    /**
     * This base method will delete or mark retire a retire-able object. 
     * @param source
     * @param index
     */
    public void delete(List<? extends Retireable> source, int index){
    	int size = source.size();
    	int actualIndex = index;
    	
    	for(int i = 0; i < size & actualIndex < size; i++){
    		Retireable o = source.get(i);
    		if(actualIndex == i){
    			if(o.getId() == null) {
    				source.remove(actualIndex);
    			}else{
    				o.retire();
    			}
    			return;
    		}else{
    			if(o.isRetired()) actualIndex++;
    		}
    		
    	}
    	
    }
   
   
    public void reloadStudy(){
    	Integer oldStudyId = study.getId();
    	Study loaded = studyDao.getStudyDesignById(oldStudyId.intValue());
    	setStudy(loaded);
    }
    
    /**
     * This method will save a study into the DB
     */
    public void save(){

         //synchronize research staff if study have a valid ID.
         if(study.getId() != null ){
             studyRepository.synchronizeStudyPersonnel(study);
         }

        //save the study by merging it.
         Study mergedStudy = studyRepository.merge(study);
         studyDao.initialize(mergedStudy);

         // poperly set the  report formats
         mergedStudy.setAdeersPDFType(study.getAdeersPDFType());
         mergedStudy.setCaaersXMLType(study.getCaaersXMLType());
         mergedStudy.setCiomsPDFType(study.getCiomsPDFType());
         mergedStudy.setCiomsSaePDFType(study.getCiomsSaePDFType());
         mergedStudy.setDcpSAEPDFType(study.getDcpSAEPDFType());
         mergedStudy.setMedwatchPDFType(study.getMedwatchPDFType());

         setStudy(mergedStudy);
    }

    public List<ConfigProperty> getAllPersonnelRoles() {
        return allPersonnelRoles;
    }

    public void setAllPersonnelRoles(List<ConfigProperty> allPersonnelRoles) {
        this.allPersonnelRoles = allPersonnelRoles;
    }

    public List<ConfigProperty> getAllInvestigatorRoles() {
        return allInvestigatorRoles;
    }

    public void setAllInvestigatorRoles(List<ConfigProperty> allInvestigatorRoles) {
        this.allInvestigatorRoles = allInvestigatorRoles;
    }

    public void populateRoleNamesMap() {
        for (ConfigProperty pr : getAllPersonnelRoles()) {
            this.studyPersonnelRoles.put(pr.getCode(), pr.getName());
        }
        for (ConfigProperty pr : getAllInvestigatorRoles()) {
            this.studyInvestigatorRoles.put(pr.getCode(), pr.getName());
        }
    }

    public Map<String, String> getStudyPersonnelRoles() {
        return studyPersonnelRoles;
    }

    public void setStudyPersonnelRoles(Map<String, String> studyPersonnelRoles) {
        this.studyPersonnelRoles = studyPersonnelRoles;
    }

    public Map<String, String> getStudyInvestigatorRoles() {
        return studyInvestigatorRoles;
    }

    public void setStudyInvestigatorRoles(Map<String, String> studyInvestigatorRoles) {
        this.studyInvestigatorRoles = studyInvestigatorRoles;
    }
    
    public Study checkForDuplicateStudyByIdentifier(Identifier identifier){
    	StudyQuery query = new StudyQuery();
        query.joinIdentifier();
        query.filterByIdentifier(identifier);
        
        if(study.getId() != null){
    	   query.ignoreStudyById(study.getId());
        }
        List<Study> studies = studyDao.find(query);
        
        if(CollectionUtils.isNotEmpty(studies)){
        	return studies.get(0);
        }
        return null;
    }

    /*
    * @author: Ion C. Olaru
    * Synchronize Agent Specific Terms with Study's Expecetd AEs
    * Study gets all the Expected AEs' associated with study's agent
    * If an agent is CTEP lead ind, then study gets only its terms, otherwise gets all terms of all its lagents.
    *
    * */
    public void synchronizeStudyWithAgentAEList(AgentSpecificAdverseEventListService service, Study s, StudyAgent sa, boolean deleted) {

/*
        boolean hasLeadCTEPInds = false;
        boolean isLeadCTEPInds = false;

        // for delete
        if (deleted) {
            service.synchronizeStudyWithAgent(s, sa.getAgent(), deleted);
            return;
        }

        // for add, find if has leadCTEP INDs 
        if (sa.getIndType().equals(INDType.CTEP_IND) && sa.getPartOfLeadIND()) {
                isLeadCTEPInds = true;
                hasLeadCTEPInds = true;
        } else {
            for (StudyAgent studyAgent : s.getStudyAgents()) {
                if (sa.getIndType().equals(INDType.CTEP_IND) && sa.getPartOfLeadIND())
                    hasLeadCTEPInds = true;
            }
        }


        if (hasLeadCTEPInds) {

            // delete all not needed
            for (StudyAgent studyAgent : s.getStudyAgents()) {
                if (!sa.getIndType().equals(INDType.CTEP_IND) || !sa.getPartOfLeadIND())
                    service.synchronizeStudyWithAgent(s, studyAgent.getAgent(), true);
            }

            // synchronzie the remaining agents
            for (StudyAgent studyAgent : s.getStudyAgents()) {
                if (sa.getIndType().equals(INDType.CTEP_IND) && sa.getPartOfLeadIND())
                    service.synchronizeStudyWithAgent(s, studyAgent.getAgent(), false);
            }

            // add the new one if it's CTEP Lead
            if (isLeadCTEPInds)
                service.synchronizeStudyWithAgent(s, sa.getAgent(), false);
        } else {
            service.synchronizeStudyWithAgent(s, sa.getAgent(), false);
        }
*/
    }

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Integer getPrimaryStudyDisease() {
        return primaryStudyDisease;
    }

    public void setPrimaryStudyDisease(Integer primaryStudyDisease) {
        this.primaryStudyDisease = primaryStudyDisease;
    }

    public StudyCoordinatingCenter getPrevCC() {
        return prevCC;
    }

    public void setPrevCC(StudyCoordinatingCenter prevCC) {
        this.prevCC = prevCC;
    }

    public StudyFundingSponsor getPrevFS() {
        return prevFS;
    }

    public void setPrevFS(StudyFundingSponsor prevFS) {
        this.prevFS = prevFS;
    }
}
