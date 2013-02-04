package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * User: Biju Joseph
 * Date: 1/29/13
 */
public class AdverseEventMigrator implements Migrator<AdverseEventReportingPeriod> {
    private static Log logger = LogFactory.getLog(AdverseEventMigrator.class);
    private CtcTermDao ctcTermDao;
    private LowLevelTermDao lowLevelTermDao;

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    public LowLevelTermDao getLowLevelTermDao() {
        return lowLevelTermDao;
    }

    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

    public void migrate(AdverseEventReportingPeriod src, AdverseEventReportingPeriod dest, DomainObjectImportOutcome<AdverseEventReportingPeriod> outcome) {
        Study study = dest.getStudy();
        AeTerminology aeTerminology = study.getAeTerminology();

        for(AdverseEvent aeSrc : src.getAdverseEvents()){
            AdverseEvent aeDest = new AdverseEvent();

            aeDest.setStartDate(aeSrc.getStartDate());
            aeDest.setEndDate(aeSrc.getEndDate());
            aeDest.setGradedDate(aeSrc.getGradedDate());

            aeDest.setAttributionSummary(aeSrc.getAttributionSummary());
            aeDest.setExpected(aeSrc.getExpected());
            aeDest.setGrade(aeSrc.getGrade());
            aeDest.setHospitalization(aeSrc.getHospitalization());

            aeDest.setExternalId(aeSrc.getExternalId());
            aeDest.setComments(aeSrc.getComments());

            aeDest.setSolicited(aeSrc.getSolicited());

            aeDest.setEventApproximateTime(aeSrc.getEventApproximateTime());
            aeDest.setEventLocation(aeSrc.getEventLocation());

            aeDest.setOutcomes(aeSrc.getOutcomes());

            aeDest.setDetailsForOther(aeSrc.getDetailsForOther());

            if(aeTerminology.getTerm() == Term.CTC){

                AdverseEventCtcTerm aeSrcTerm = aeSrc.getAdverseEventCtcTerm();
                if(aeSrcTerm == null || aeSrcTerm.getCtcTerm() == null || aeSrcTerm.getCtcTerm().getCtepCode() == null){
                    logger.error("The AE terminology on the study is CTC, but there is no CTC term (CtepCode) present in the adverse event");
                    outcome.addError("WS_AEMS_020", "CTC term/Ctep code not found");
                    return;
                }
                AdverseEventCtcTerm aeCtcTermDest = new AdverseEventCtcTerm();
                CtcTerm ctcTerm = fetchCtcTerm(aeSrcTerm.getCtcTerm().getCtepCode(), aeTerminology.getCtcVersion().getName());
                if(ctcTerm == null){
                    logger.error("The CTC term with ctep code " + aeSrcTerm.getCtcTerm().getCtepCode() + " is not found under version : " + aeTerminology.getCtcVersion().getName());
                    outcome.addError("WS_AEMS_020", "CTC term/Ctep code not found", new String[]{aeSrcTerm.getCtcTerm().getCtepCode()});
                    return;
                }
                aeCtcTermDest.setTerm(ctcTerm);
                aeDest.setAdverseEventCtcTerm(aeCtcTermDest);
                aeCtcTermDest.setAdverseEvent(aeDest);
                if(ctcTerm.isOtherRequired()){
                    if(dest.getStudy().getOtherMeddra() != null){
                        //must have other Meddra
                        if(aeSrc.getLowLevelTerm() == null){
                            logger.error("The Ctc term " + ctcTerm.getCtepCode() + ", needs Other specify (MedDRA) term, but not found");
                            outcome.addError("WS_AEMS_022", "Other MedDRA is missing");
                            return;
                        }
                        LowLevelTerm lowLevelTerm = fetchLowLevelTerm(aeSrc.getLowLevelTerm().getMeddraCode(), dest.getStudy().getOtherMeddra().getId());
                        if(lowLevelTerm == null){
                            logger.error("MedDRA Term is not found (meddra code : " + aeSrc.getLowLevelTerm().getMeddraCode() + ")");
                            outcome.addError("WS_AEMS_021", "Other MedDRA term is not found", new String[]{aeSrc.getLowLevelTerm().getMeddraCode()});
                            return;
                        }
                        aeDest.setLowLevelTerm(lowLevelTerm);

                    }else {
                        //must have other specify
                        if(aeSrc.getOtherSpecify() == null){
                            logger.error("The Ctc term " + ctcTerm.getCtepCode() + ", needs Other Specify (text), but not found");
                            outcome.addError("WS_AEMS_078", "Other specify text is missing", new String[]{ctcTerm.getCtepCode()});
                            return;
                        }
                        aeDest.setOtherSpecify(aeSrc.getOtherSpecify());
                    }
                }
            }else if(aeTerminology.getTerm() == Term.MEDDRA){
                AdverseEventMeddraLowLevelTerm aeSrcTerm = aeSrc.getAdverseEventMeddraLowLevelTerm();
                if(aeSrcTerm == null || aeSrcTerm.getLowLevelTerm() == null || aeSrcTerm.getLowLevelTerm().getMeddraCode() == null){
                    logger.error("The AE terminology on the study is MedDRA, but there is no MedDRA term (meddra code) present in the adverse event");
                    outcome.addError("WS_AEMS_021", "MedDRA term/MedDRA code not found");
                    return;
                }
                AdverseEventMeddraLowLevelTerm meddraTerm = new AdverseEventMeddraLowLevelTerm();
                LowLevelTerm lowLevelTerm = fetchLowLevelTerm(aeSrcTerm.getLowLevelTerm().getMeddraCode(), aeTerminology.getMeddraVersion().getId());
                if(lowLevelTerm == null){
                    logger.error("The AE terminology on the study is MedDRA, but there is no MedDRA term (meddra code) present in the adverse event");
                    outcome.addError("WS_AEMS_021", "MedDRA term/MedDRA code not found", aeSrcTerm.getLowLevelTerm().getMeddraCode());
                    return;
                }
                meddraTerm.setTerm(lowLevelTerm);
                meddraTerm.setAdverseEvent(aeDest);
                aeDest.setAdverseEventMeddraLowLevelTerm(meddraTerm);
            }

            dest.addAdverseEvent(aeDest);

        }
    }

    public CtcTerm fetchCtcTerm(String ctcCode, String ctcVersionName){
       List<CtcTerm> ctcTerms = ctcTermDao.getByCtepCodeandVersion(ctcCode, ctcVersionName);
        if(CollectionUtils.isEmpty(ctcTerms)) return null;
        return ctcTerms.get(0);
    }

    public LowLevelTerm fetchLowLevelTerm(String meddraCode, int meddraVersionId){
        List<LowLevelTerm> llts = lowLevelTermDao.getByMeddraCodeandVersion(meddraCode, meddraVersionId);
        if(CollectionUtils.isEmpty(llts)) return null;
        return llts.get(0);
    }
}
