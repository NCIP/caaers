package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang.BooleanUtils;

/**
 * User: Ion C. Olaru
 * Date: Dec 22, 2008 - 10:44:55 AM
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
    private Boolean caaersXMLType = Boolean.FALSE;
    private Boolean adeersPDFType = Boolean.FALSE;
    private Boolean medwatchPDFType = Boolean.FALSE;
    private Boolean dcpSAEPDFType = Boolean.FALSE;
    private Boolean ciomsPDFType = Boolean.FALSE;
    private Boolean ciomsSaePDFType = Boolean.FALSE;
    private Integer loadStatus = LoadStatus.COMPLETE.getCode();

    // Used to facilitate import of a coordinating center / funding sponsor
    private FundingSponsor fundingSponsor;
    private CoordinatingCenter coordinatingCenter;

    // DCP specific properties
    private Design design;

    private List<Epoch> epochs=new ArrayList<Epoch>();
    
    private boolean workflowEnabled;
    
    private StudyDao studyDao;

    public StudyCommand(StudyDao studyDao) {
    	this.studyDao = studyDao;
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

    public void setSurgeryTherapyType(final Boolean surgeryTherapyType) {
        this.surgeryTherapyType = surgeryTherapyType;
    }

    @Transient
    public Boolean getBehavioralTherapyType() {
        return behavioralTherapyType;
    }

    public void setBehavioralTherapyType(final Boolean behavioralTherapyType) {
        this.behavioralTherapyType = behavioralTherapyType;
    }

    @Transient
    public CoordinatingCenter getCoordinatingCenter() {
        return coordinatingCenter;
    }

    @Transient
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
    
    /**
     * Will delete or mark as retired the study agent object mentioned.
     * @param index
     */
    public void deleteStudyAgentAtIndex(int index){
    	delete(study.getStudyAgents(), index);
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
    	delete(study.getCtepStudyDiseases(), index);
    }
    

    /**
     * Will delete or mark as retired the {@link MeddraStudyDisease} at specified index.
     * @param index
     */
    public void deleteMeddraStudyDiseaseAtIndex(int index){
    	delete(study.getMeddraStudyDiseases(), index);
    }

    /**
     * Will delete or mark as retired the {@link StudyCondition} at specified index.
     * @param index
     */
    public void deleteStudyConditionAtIndex(int index){
    	delete(study.getStudyConditions(), index);
    }
    
    /**
     * Will delete or mark as retired the {@link SiteInvestigator} at specified index.
     * @param index
     */
    public void deleteSiteInvestigatorAtIndex(int studyOrgIndex, int index){
    	study.getActiveStudyOrganizations().get(studyOrgIndex).getStudyInvestigators().get(index).retire();
    }
    
    /**
     * Will delete or mark as retired the {@link StudyPersonnel} at specified index.
     * @param studyOrgIndex
     * @param index
     */
    public void deleteStudyPersonAtIndex(int studyOrgIndex, int index){
    	study.getActiveStudyOrganizations().get(studyOrgIndex).getStudyPersonnels().get(index).retire();
    }
    
    /**
     * This base method will delete or mark retire a retire-able object. 
     * @param source
     * @param index
     */
    public void delete(List<? extends Retireable> source, int index){
    		source.get(index).retire();
    }
   
    /**
     * This method will save a study into the DB
     */
    public void save(){
    	
         Study mergedStudy = studyDao.merge(study);
         studyDao.initialize(mergedStudy);

         // now check for study therapies.
         mergedStudy.setDrugAdministrationTherapyType(study.getDrugAdministrationTherapyType());
         mergedStudy.setDeviceTherapyType(study.getDeviceTherapyType());
         mergedStudy.setRadiationTherapyType(study.getRadiationTherapyType());
         mergedStudy.setSurgeryTherapyType(study.getSurgeryTherapyType());

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
}
