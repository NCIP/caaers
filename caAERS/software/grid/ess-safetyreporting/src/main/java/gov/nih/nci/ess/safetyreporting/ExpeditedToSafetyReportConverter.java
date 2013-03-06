/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.safetyreporting;

import ess.caaers.nci.nih.gov.AdditionalInformation;
import ess.caaers.nci.nih.gov.PerformedActivity;
import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.EventStatus;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.PriorTherapyAgent;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.ess.ae.DomainToGridObjectConverter;
import gov.nih.nci.ess.ae.ISO21090Helper;
import gov.nih.nci.ess.safetyreporting.types.AdverseEventOutcomeResult;
import gov.nih.nci.ess.safetyreporting.types.AdverseEventReportingPeriod;
import gov.nih.nci.ess.safetyreporting.types.AdverseEventResponseDescription;
import gov.nih.nci.ess.safetyreporting.types.ConcomitantAgent;
import gov.nih.nci.ess.safetyreporting.types.DefinedSubjectActivityGroup;
import gov.nih.nci.ess.safetyreporting.types.Device;
import gov.nih.nci.ess.safetyreporting.types.HealthCareProvider;
import gov.nih.nci.ess.safetyreporting.types.MaterialName;
import gov.nih.nci.ess.safetyreporting.types.OtherCause;
import gov.nih.nci.ess.safetyreporting.types.ParticipantHistory;
import gov.nih.nci.ess.safetyreporting.types.PerformedDiagnosis;
import gov.nih.nci.ess.safetyreporting.types.PerformedObservation;
import gov.nih.nci.ess.safetyreporting.types.PerformedObservationCategory;
import gov.nih.nci.ess.safetyreporting.types.PerformedObservationTerm;
import gov.nih.nci.ess.safetyreporting.types.PerformedObservationValue;
import gov.nih.nci.ess.safetyreporting.types.PerformedProcedure;
import gov.nih.nci.ess.safetyreporting.types.PerformedSubstanceAdministration;
import gov.nih.nci.ess.safetyreporting.types.Person;
import gov.nih.nci.ess.safetyreporting.types.PreExistingCondition;
import gov.nih.nci.ess.safetyreporting.types.PriorTherapy;
import gov.nih.nci.ess.safetyreporting.types.ReportSubmitter;
import gov.nih.nci.ess.safetyreporting.types.ResearchStaff;
import gov.nih.nci.ess.safetyreporting.types.SAEPriorTherapy;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;
import gov.nih.nci.ess.safetyreporting.types.StudySubjectProtocolVersionRelationship;
import gov.nih.nci.ess.safetyreporting.types.TreatmentInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import _21090.org.iso.CD;
import _21090.org.iso.IVL_TSDateTime;
import _21090.org.iso.NullFlavor;
import _21090.org.iso.PQ;
import _21090.org.iso.TSDateTime;

public class ExpeditedToSafetyReportConverter {
	private static final String TS_DATETIME_PATTERN = "yyyyMMddHHmmss";
	private static final ISO21090Helper h = null;
	public static String COD = "cause of death";
	public static String RFP = "remove from protocol";
	public static String EA = "event abated?";
	public static String SA = "single occurance";
	public static String ETT = "treatment time";
	public static String PS = "present status";
	public static String SDI = "study drug interrupted indicator";
	public static String ADD = "agent dose decrease";
	public static String BN = "brand name";
	public static String CN = "common name";
	
	public SafetyReportVersion convertExpeditedAdverseEventReport(ExpeditedAdverseEventReport expeditedAEReport) {
		SafetyReportVersion safetyReport= new SafetyReportVersion();
		safetyReport.setIdentifier(h.II(expeditedAEReport.getId()));
		safetyReport.setTimeCreated(convert(expeditedAEReport.getCreatedAt()));
		if (expeditedAEReport.getResponseDescription()!=null) {
			populateAEResponseDescription(safetyReport,expeditedAEReport);
		}
		populateAdverseEvents(safetyReport,expeditedAEReport);
		if (expeditedAEReport.getTreatmentInformation()!=null) {
			populateTreatmentInformation(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getAdditionalInformation()!=null) {
			populateAdditionalInformation(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getReporter()!=null) {
			populateReporter(safetyReport,expeditedAEReport);
		}
		populateReportingPeriod(safetyReport,expeditedAEReport);

		if (expeditedAEReport.getParticipantHistory()!=null) {
			populateParticipantHistory(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getDiseaseHistory()!=null) {
			populateDiseaseHistory(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getLabs()!=null) {
			populateLabs(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getMedicalDevices()!=null) {
			populateDevices(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getRadiationInterventions()!=null) {
			populateRadiationInterventions(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getSurgeryInterventions()!=null) {
			populateSurgeryInterventions(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getConcomitantMedications()!=null) {
			populateConcomitantMedications(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getOtherCauses()!=null) {
			populateOtherCauses(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getSaeReportPriorTherapies()!=null) {
			populatePriorTherapies(safetyReport,expeditedAEReport);
		}
		if (expeditedAEReport.getSaeReportPreExistingConditions()!=null) {
			populatePreExistingConditions(safetyReport,expeditedAEReport);
		}
		return safetyReport;
	}
	private void populatePreExistingConditions(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition> pcs = expeditedAEReport.getSaeReportPreExistingConditions();
		PreExistingCondition gpc = null;
		int ct = -1;
		for (gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition pc:pcs) {
			gpc = convertPreExistingCondition(pc);
			safetyReport.setPreExistingConditions(ct++, gpc);
		}
	}
	private PreExistingCondition convertPreExistingCondition(gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition pc) {
		PreExistingCondition gpc = new PreExistingCondition();

		if(pc.getPreExistingCondition().getText()!=null) {
			gpc.setText(h.ST(pc.getPreExistingCondition().getText()));
		}
		if(pc.getPreExistingCondition().getMeddraHlgt()!=null) {
			gpc.setText(h.ST(pc.getPreExistingCondition().getMeddraHlgt()));
		}
		if(pc.getPreExistingCondition().getMeddraLlt()!=null) {
			gpc.setText(h.ST(pc.getPreExistingCondition().getMeddraLlt()));
		}
		if(pc.getPreExistingCondition().getMeddraLltCode()!=null) {
			gpc.setText(h.ST(pc.getPreExistingCondition().getMeddraLltCode()));
		}
		if(pc.getOther()!=null) {
			gpc.setText(h.ST(pc.getOther()));
		}
		return gpc;
	}
	
	private void populatePriorTherapies(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy> pts = expeditedAEReport.getSaeReportPriorTherapies();
		SAEPriorTherapy gpt = null;
		int ct = -1;
		for (gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy pt:pts) {
			gpt = convertPriorTherapy(pt);
			safetyReport.setPriorTherapies(ct++, gpt);
		}
	}
	private SAEPriorTherapy convertPriorTherapy(gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy pt) {
		SAEPriorTherapy gspt = new SAEPriorTherapy();
		PriorTherapy gpt = new PriorTherapy();
		
		if(pt.getPriorTherapy().getText()!=null) {
			gpt.setText(h.ST(pt.getPriorTherapy().getText()));
		}
		if(pt.getPriorTherapy().getMeddraCode() !=null) {
			gpt.setMeddraCode(h.ST(pt.getPriorTherapy().getMeddraCode()));
		}
		if(pt.getPriorTherapy().getMeddraTerm() !=null) {
			gpt.setMeddraCode(h.ST(pt.getPriorTherapy().getMeddraTerm()));
		}
		gspt.setPriorTherapy(gpt);
		if(pt.getStartDate() !=null) {
			gspt.setStartDate(convert(pt.getStartDate().toDate()));
		}
		if(pt.getEndDate() !=null) {
			gspt.setEndDate(convert(pt.getEndDate().toDate()));
		}
		if(pt.getOther() !=null) {
			gspt.setOther(h.ST(pt.getOther()));
		}
		int ct = -1;
		for (PriorTherapyAgent pta:pt.getPriorTherapyAgents()) {
			if (pta.getName()!=null){
				gspt.setPriorTherapyAgents(ct++, h.ST(pta.getName()));
			}
		}
		return gspt;
	}
	
	private void populateOtherCauses(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<gov.nih.nci.cabig.caaers.domain.OtherCause> ocs = expeditedAEReport.getOtherCauses();
		OtherCause goc = null;
		int ct = -1;
		for (gov.nih.nci.cabig.caaers.domain.OtherCause oc:ocs) {
			goc = new OtherCause();
			if (oc.getText()!=null){
				goc.setText(h.ST(oc.getText()));
				safetyReport.setOtherCauses(ct++, goc);
			}
		}
	}

	private void populateConcomitantMedications(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<ConcomitantMedication> cms = expeditedAEReport.getConcomitantMedications();
		ConcomitantAgent ca = null;
		int ct = -1;
		for (ConcomitantMedication cm:cms) {
			ca = convertConcomitantMedication(cm);
			safetyReport.setConcomitantMedications(ct++, ca);
		}
	}
	private ConcomitantAgent convertConcomitantMedication(ConcomitantMedication cm){
		ConcomitantAgent ca = new ConcomitantAgent();
		ca.setIdentifier(h.II(cm.getId()));
		if(cm.getAgentName()!=null) {
			ca.setMaterialName(mn(cm.getAgentName(),""));
		}
		IVL_TSDateTime iv = new IVL_TSDateTime();
		if(cm.getStartDate()!=null) {
			iv.setLow(convert(cm.getStartDate().toDate()));
			ca.setActualDateRange(iv);
		}
		if(cm.getEndDate()!=null) {
			iv.setHigh(convert(cm.getEndDate().toDate()));
			ca.setActualDateRange(iv);
		}
		if(cm.getStillTakingMedications()!=null) {
			ca.setStillTakingMedications(h.BL(cm.getStillTakingMedications()));
		}
		return ca;
		
	}
	private void populateSurgeryInterventions(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<SurgeryIntervention> sis = expeditedAEReport.getSurgeryInterventions();
		PerformedProcedure pp = null;
		int ct = -1;
		for (SurgeryIntervention si:sis) {
			pp = convertSurgeryIntervention(si);
			safetyReport.setSurgeryInterventions(ct++, pp);
		}
	}
	private PerformedProcedure convertSurgeryIntervention(SurgeryIntervention si) {
		PerformedProcedure pp = new PerformedProcedure();
		pp.setIdentifier(h.II(si.getId()));
		if (si.getDescription()!=null) {
			pp.setDescription(h.ST(si.getDescription()));
		}
		if (si.getInterventionDate()!=null) {
			pp.setActualDateRange(h.IVL_TSDateTime(convert(si.getInterventionDate())));
		}
		if (si.getTreatmentArm()!=null) {
			pp.setTreatmentArm(h.ST(si.getTreatmentArm()));
		}
		if (si.getInterventionSite()!=null) {
			pp.setMethodCode(h.CD(si.getInterventionSite().getName()));
		}
		return pp;
		
	}
	private void populateRadiationInterventions(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<RadiationIntervention> ris = expeditedAEReport.getRadiationInterventions();;
		PerformedSubstanceAdministration psa = null;
		int ct = -1;
		for (RadiationIntervention ri:ris) {
			psa = convertRadiationIntervention(ri);
			safetyReport.setRadiationInterventions(ct++, psa);
		}
	}	
	private PerformedSubstanceAdministration convertRadiationIntervention(RadiationIntervention ri) {
		PerformedSubstanceAdministration psa = new PerformedSubstanceAdministration();
		psa.setIdentifier(h.II(ri.getId()));
		if (ri.getAdministration() !=null) {
			psa.setMethodCode(h.CD(ri.getAdministration().getDisplayName()));
		}
		if (ri.getAdjustment() !=null) {
			psa.setAdjustment(h.ST(ri.getAdjustment()));
		}
		if (ri.getDaysElapsed() !=null) {
			psa.setDaysElapsed(h.ST(ri.getDaysElapsed()));
		}
		if (ri.getDescription() !=null) {
			psa.setDescription(h.ST(ri.getDescription()));
		}
		if (ri.getDosage() !=null && ri.getDosageUnit()!=null) {
			psa.setDose(h.PQ(new Double(ri.getDosage()), ri.getDosageUnit()));
		}
		if (ri.getFractionNumber() !=null) {
			psa.setPeriodDoseTotal(h.PQ(ri.getFractionNumber()));
		}
		if (ri.getLastTreatmentDate() !=null) {
			psa.setActualDateRange(h.IVL_TSDateTime(convert(ri.getLastTreatmentDate())));
		}
		if (ri.getTreatmentArm() !=null) {
			psa.setTreatmentArm(h.ST(ri.getTreatmentArm()));
		}
		return psa;
	}
	private void populateDevices(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<MedicalDevice> mds = expeditedAEReport.getMedicalDevices();;
		Device d = null;
		int ct = -1;
		for (MedicalDevice md:mds) {
			d = convertMedicalDevice(md);
			safetyReport.setDevices(ct++, d);
		}
	}	
	private Device convertMedicalDevice(MedicalDevice md) {
		Device d = new Device();
		int ct = -1;
		if (md.getBrandName()!=null) {
			d.setMaterialName(ct++, mn(md.getBrandName(),BN));
		}
		if (md.getCommonName()!=null) {
			d.setMaterialName(ct++, mn(md.getCommonName(),CN));
		}
		if(md.getCatalogNumber()!=null){
			d.setCatalogNumber(h.II(md.getCatalogNumber()));
		}
		if(md.getModelNumber()!=null){
			d.setModelNumber(h.II(md.getModelNumber()));
		}
		if(md.getDeviceType()!=null){
			d.setTypeCode(h.CD(md.getDeviceType()));
		}
		if(md.getLotNumber()!=null){
			d.setLotNumberText(h.ST(md.getLotNumber()));
		}
		if(md.getManufacturerCity()!=null && md.getManufacturerState()!=null){
			d.setManufacturerCityState(h.AD(null, md.getManufacturerCity(), md.getManufacturerState(), null, null));
		}
		if(md.getManufacturerName()!=null){
			d.setManufacturerName(h.ST(md.getManufacturerName()));
		}
		if(md.getReprocessorName()!=null){
			d.setReprocessorName(h.ST(md.getReprocessorName()));
		}
		if(md.getReprocessorAddress()!=null){
			d.setReprocessorAddress(h.ST(md.getReprocessorAddress()));
		}
		if(md.getReturnedDate()!=null){
			d.setReturnedFromReprocessorDate(convert(md.getReturnedDate()));
		}
		if(md.getOtherDeviceOperator()!=null){
			d.setOtherDeviceOperator(h.ST(md.getOtherDeviceOperator()));
		}
		
		return d;
	}

	private MaterialName mn(String str,String typeCode) {
		MaterialName mn = new MaterialName();
		mn.setName(h.ST(str));
		mn.setTypeCode(h.CD(typeCode));
		return mn;
	}
	private void populateLabs(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<Lab> labs = expeditedAEReport.getLabs();
		PerformedObservation po = null;
		int ct = -1;
		for (Lab lab:labs) {
			po = convertLab(lab);
			safetyReport.setLabs(ct++, po);
		}
	}
	private PerformedObservation convertLab(Lab lab){
		PerformedObservation po = new PerformedObservation();
		if (lab.getUnits()!=null) {
			PQ pq = new PQ();
			pq.setUnit(lab.getUnits());
			po.setResult(pq);
		}
		if (lab.getInfectiousAgent()!=null) {
			po.setInfectiousAgent(h.ST(lab.getInfectiousAgent()));
		}
		if (lab.getSite()!=null) {
			po.setTargetAnatomicSiteCode(h.CD(lab.getSite()));
		}
		if (lab.getBaseline()!=null) {
			po.setBaseline(pov(lab.getBaseline()));
		}
		if (lab.getNadir()!=null) {
			po.setNadir(pov(lab.getNadir()));
		}
		if (lab.getRecovery()!=null) {
			po.setRecovery(pov(lab.getRecovery()));
		}
		if (lab.getLabTerm()!=null) {
			po.setPerformedObservationTerm(pt(lab.getLabTerm()));
		}
		
		return po;
	}
	private PerformedObservationValue pov(LabValue lv){
		PerformedObservationValue pv = new PerformedObservationValue();
		pv.setValue(h.ST(lv.getValue()));
		pv.setActualDateRange(convert(lv.getDate()));
		return pv;
	}
	private PerformedObservationTerm pt(LabTerm lt){
		PerformedObservationTerm pt = new PerformedObservationTerm();
		pt.setTerm(h.ST(lt.getTerm()));
		
		PerformedObservationCategory pc = new PerformedObservationCategory();
		pc.setName(h.ST(lt.getCategory().getName()));
		pc.setPerformedObservationVersion(h.ST(lt.getCategory().getLabVersion().getName()));
		pt.setPerformedObservationCategory(pc);
		return pt;
	}
	private void populateDiseaseHistory(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport){
		gov.nih.nci.cabig.caaers.domain.DiseaseHistory dh = expeditedAEReport.getDiseaseHistory();
		PerformedDiagnosis pd = new PerformedDiagnosis();
		pd.setIdentifier(h.II(dh.getId()));
		if (dh.getDiagnosisDate()!=null) {
			pd.setActualDateRange(convert(dh.getDiagnosisDate().toDate()));
		}
		if (dh.getOtherPrimaryDisease()!=null) {
			CD cd = new CD();
			cd.setOriginalText(h.EDText(dh.getOtherPrimaryDisease()));
			pd.setResult(cd);
		}
		if (dh.getOtherPrimaryDiseaseSite()!=null) {
			CD cd = new CD();
			cd.setOriginalText(h.EDText(dh.getOtherPrimaryDiseaseSite()));
			pd.setTargetAnatomicSiteCode(cd);
		}
		safetyReport.setDiseaseHistory(pd);
	}
	
	private void populateParticipantHistory(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport){
		gov.nih.nci.cabig.caaers.domain.ParticipantHistory ph = expeditedAEReport.getParticipantHistory();
		ParticipantHistory gph = new ParticipantHistory();
		gph.setIdentifier(h.II(ph.getId()));
		if (ph.getBsa()!=null) {
			gph.setBsa(h.ST(ph.getBsa()));
		}
		if (ph.getHeight()!=null) {
			gph.setHeight(h.PQ(ph.getHeight().getQuantity(),ph.getHeight().getUnit()));
		}
		if (ph.getWeight()!=null) {
			gph.setWeight(h.PQ(ph.getWeight().getQuantity(),ph.getWeight().getUnit()));
		}
		safetyReport.setParticipantHistory(gph);
	}
	
	private void populateReportingPeriod(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod reportingPeriod = expeditedAEReport.getReportingPeriod();
		AdverseEventReportingPeriod gridReportingPeriod = new AdverseEventReportingPeriod();
		gridReportingPeriod.setIdentifier(h.II(reportingPeriod.getId()));
		if (reportingPeriod.getDescription()!=null) {
			gridReportingPeriod.setDescription(h.ST(reportingPeriod.getDescription()));
		}
		gridReportingPeriod.setStartDate(convert(reportingPeriod.getStartDate()));
		gridReportingPeriod.setEndDate(convert(reportingPeriod.getEndDate()));
		gov.nih.nci.cabig.caaers.domain.TreatmentAssignment ta = reportingPeriod.getTreatmentAssignment();
		DefinedSubjectActivityGroup dsag = new DefinedSubjectActivityGroup();
		if (ta.getComments()!=null) {
			dsag.setComment(h.ST(ta.getComments()));
		}
		if (ta.getDescription()!=null) {
			dsag.setDescription(h.ST(ta.getDescription()));
		}
		if (ta.getDoseLevelOrder()!=null) {
			dsag.setDoseLevelOrder(h.ST(ta.getDoseLevelOrder()));
		}
		dsag.setIdentifier(h.II(ta.getId()));
		if (ta.getCode()!=null) {
			dsag.setNameCode(h.CD(ta.getCode()));
		}
		gridReportingPeriod.setTreatmentAssignment(dsag);

		if (reportingPeriod.getTreatmentAssignmentDescription()!=null) {
			gridReportingPeriod.setTreatmentAssignmentDescription(h.ST(reportingPeriod.getTreatmentAssignmentDescription()));
		}
		StudySubjectProtocolVersionRelationship spvr = new StudySubjectProtocolVersionRelationship();
		StudyParticipantAssignment spa = reportingPeriod.getAssignment();
		spvr.setStudySiteIdentifier(h.II(spa.getStudySite().getId()));
		spvr.setStudySubjectIdentifier(h.II(spa.getParticipant().getId()));
		gridReportingPeriod.setAssignment(spvr);
		
		safetyReport.setReportingPeriod(gridReportingPeriod);
	}
	
	private void populateReporter(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		ReportSubmitter gridR = new ReportSubmitter();
		gov.nih.nci.cabig.caaers.domain.Reporter r = expeditedAEReport.getReporter();
		Person gridP = new Person();
		if(r.getAddress()!=null) {
			Address addr = r.getAddress();
			gridP.setPostalAddress(h.AD(addr.getStreet(), addr.getCity(), addr.getState(), addr.getZip(), addr.getCountry()));
		}
		gridP.setTelecomAddress(h.BAG_TEL(r.getEmailAddress(), r.getPhoneNumber(), r.getFax()));
		//gridP.setName(arg0);
		
		if (r.getInvestigator() != null) {
			gridR.setHealthCareProvider((HealthCareProvider)gridP);
		}
		if (r.getResearchStaff() !=null) {
			gridR.setResearchStaff((ResearchStaff)gridP);
		}
		
		safetyReport.setReporter(gridR);
	}

	private void populateTreatmentInformation(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		TreatmentInformation gridTi = new TreatmentInformation();
		gov.nih.nci.cabig.caaers.domain.TreatmentInformation ti = expeditedAEReport.getTreatmentInformation();
		gridTi.setIdentifier(h.II(ti.getId()));
		if (ti.getFirstCourseDate()!=null) {
			gridTi.setActualDateRange((h.IVL_TSDateTime(convert(ti.getFirstCourseDate()))));
		}
		if (ti.getTotalCourses()!=null) {
			gridTi.setRepetitionNumber(h.INT(ti.getTotalCourses()));
		}
		if (ti.getTreatmentDescription()!=null) {
			gridTi.setDescription(h.ST(ti.getTreatmentDescription()));
		}
		
		safetyReport.setTreatmentInformation(gridTi);
	}
	private void populateAdditionalInformation(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		AdditionalInformation gridAi = new AdditionalInformation();
		gov.nih.nci.cabig.caaers.domain.AdditionalInformation ai = expeditedAEReport.getAdditionalInformation();
		gridAi.setIdentifier(h.II(ai.getId()));
		if(ai.getAutopsyReport()!=null) {
			gridAi.setAutopsyReport(h.BL(ai.getAutopsyReport()));
		}
		if(ai.getConsults()!=null) {
			gridAi.setConsults(h.BL(ai.getConsults()));
		}
		if(ai.getDischargeSummary()!=null) {
			gridAi.setDischargeSummary(h.BL(ai.getConsults()));
		}
		if(ai.getFlowCharts()!=null) {
			gridAi.setFlowCharts(h.BL(ai.getDischargeSummary()));
		}
		if(ai.getIrbReport()!=null) {
			gridAi.setIrbReport(h.BL(ai.getIrbReport()));
		}
		if(ai.getLabReports()!=null) {
			gridAi.setLabReports(h.BL(ai.getLabReports()));
		}
		if(ai.getObaForm()!=null) {
			gridAi.setOboForm(h.BL(ai.getObaForm()));
		}
		if(ai.getOther()!=null) {
			gridAi.setOther(h.BL(ai.getOther()));
		}
		if(ai.getOtherInformation()!=null) {
			gridAi.setOtherInformation(h.ST(ai.getOtherInformation()));
		}
		if(ai.getPathologyReport()!=null) {
			gridAi.setPathologyReport(h.BL(ai.getPathologyReport()));
		}
		if(ai.getProgressNotes()!=null) {
			gridAi.setProgressNotes(h.BL(ai.getProgressNotes()));
		}
		if(ai.getRadiologyReports()!=null) {
			gridAi.setRadiologyReports(h.BL(ai.getRadiologyReports()));
		}
		if(ai.getReferralLetters()!=null) {
			gridAi.setReferralLetters(h.BL(ai.getReferralLetters()));
		}

		safetyReport.setAdditionalInformation(gridAi);
	}
	private void populateAdverseEvents(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		List<AdverseEvent> aes = expeditedAEReport.getAdverseEvents();
		int ct =-1;
		for (AdverseEvent ae:aes) {
			DomainToGridObjectConverter dgoc = new DomainToGridObjectConverter();
			ess.caaers.nci.nih.gov.AdverseEvent gridAe = dgoc.convertAdverseEvent(ae);
			safetyReport.setAdverseEvents(ct++, gridAe);			
		}
	}
	
	private void populateAEResponseDescription(SafetyReportVersion safetyReport,ExpeditedAdverseEventReport expeditedAEReport) {
		gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription responseDesc = expeditedAEReport.getResponseDescription();
		
		AdverseEventResponseDescription gridResponseDescription = new AdverseEventResponseDescription();
		gridResponseDescription.setIdentifier(h.II(responseDesc.getId()));
		List<AdverseEventOutcomeResult> aeORList = new ArrayList<AdverseEventOutcomeResult>();
		List<PerformedActivity> paList = new ArrayList<PerformedActivity>();
		if (responseDesc.getCauseOfDeath() != null) {
			AdverseEventOutcomeResult aeOR = new AdverseEventOutcomeResult();
			aeOR.setResult(h.ST(responseDesc.getCauseOfDeath()));
			aeOR.setTypeCode(h.CD(COD));
			aeORList.add(aeOR);
		}		
		if (responseDesc.getDateRemovedFromProtocol() != null) {
			PerformedActivity pa = new PerformedActivity();
			pa.setNameCode(h.CD(RFP));
			pa.setActualDateRange(h.IVL_TSDateTime(convert(responseDesc.getDateRemovedFromProtocol())));
			paList.add(pa);
		}
		if (responseDesc.getDaysNotGiven() != null) {
			PerformedActivity pa = new PerformedActivity();
			pa.setDelayDuration(h.PQTime(new Double(responseDesc.getDaysNotGiven())));
			paList.add(pa);
		}
		if (responseDesc.getEventAbate() != null) {
			AdverseEventOutcomeResult aeOR = new AdverseEventOutcomeResult();
			EventStatus es = responseDesc.getEventAbate();
			aeOR.setTypeCode(h.CD(EA));
			if (es.equals(es.YES)) {
				aeOR.setResult(h.BL(true));
				aeORList.add(aeOR);
			}
			if (es.equals(es.NO)) {
				aeOR.setResult(h.BL(false));
				aeORList.add(aeOR);
			}
		}
		if (responseDesc.getEventDescription() != null) {
			gridResponseDescription.setSummary(h.ST(responseDesc.getEventDescription()));
		}
		if (responseDesc.getEventReappear() != null) {
			EventStatus es = responseDesc.getEventReappear();
			if (es.equals(es.NO)) {
				gridResponseDescription.setOccurrencePatternCode(h.CD(SA));
			}
			if (es.equals(es.YES)) {
				gridResponseDescription.setOccurrencePatternCode(h.CD("not " + SA));
			}
		}
		if (responseDesc.getPrimaryTreatmentApproximateTime() != null) {
			PerformedActivity pa = new PerformedActivity();
			pa.setNameCode(h.CD(ETT));
			pa.setActualDateRange(h.IVL_TSDateTime(convert(responseDesc.getPrimaryTreatmentApproximateTime())));
			paList.add(pa);
		}
		if (responseDesc.getPresentStatus() != null) {
			PostAdverseEventStatus pes = responseDesc.getPresentStatus();
			AdverseEventOutcomeResult aeOR = new AdverseEventOutcomeResult();
			aeOR.setResult(h.ST(pes.getDisplayName()));
			aeOR.setTypeCode(h.CD(PS));
			aeORList.add(aeOR);
		}
		if (responseDesc.getReducedDate() != null && responseDesc.getReducedDose()!=null) {
			PerformedSubstanceAdministration psa = new PerformedSubstanceAdministration();
			psa.setChangeTypeCode(h.CD(ADD));
			psa.setActualDateRange(h.IVL_TSDateTime(convert(responseDesc.getReducedDate())));
			psa.setDose(h.PQ(responseDesc.getReducedDose()));
		}
		if (responseDesc.getStudyDrugInterrupted() != null) {
			AdverseEventOutcomeResult aeOR = new AdverseEventOutcomeResult();
			aeOR.setResult(h.BL(responseDesc.getStudyDrugInterrupted()));
			aeOR.setTypeCode(h.CD(SDI));
			aeORList.add(aeOR);
		}
		
		gridResponseDescription.setAdverseEventOutcomeResult(aeORList.toArray(new AdverseEventOutcomeResult[aeORList.size()]));
		gridResponseDescription.setPerformedActivity(paList.toArray(new PerformedActivity[paList.size()]));
		safetyReport.setAdverseEventResponseDescription(gridResponseDescription);
		
	}
	
	private TSDateTime convert(Date date) {
		TSDateTime tsDateTime = new TSDateTime();
		if (date != null) {
			tsDateTime.setValue(DateFormatUtils.format(date,
					TS_DATETIME_PATTERN));
		} else {
			tsDateTime.setNullFlavor(NullFlavor.NI);
		}
		return tsDateTime;

	}
	
	private TSDateTime convert(TimeValue time) {
		// A day is selected arbitrarily, since it will be ignored anyway. Only
		// hours and minutes matter.
		return convert(new Date(0), time);
	}

	private TSDateTime convert(Date day, TimeValue time) {
		if (time == null || time.isBlank()) {
			return convert(day);
		}
		day = day == null ? new Date(0) : day;
		// My apologies for the less readable expression below.
		return convert(DateUtils.setMinutes(DateUtils.setHours(day,
				time.isAM() ? (time.getHour() == 12 ? 0 : time.getHour())
						: (time.getHour() == 12 ? 12 : time.getHour() + 12)),
				time.getMinute()));
	
		
	}

	
}
