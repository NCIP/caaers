package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExternalAdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.ReconciliationReportDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.query.ExternalAdverseEventQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AeMappingsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AeMergeDTO;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationCommand {

    private ReconciliationReportDao reconciliationReportDao;
    private ExternalAdverseEventDao externalAdverseEventDao;
    private CtcTermDao ctcTermDao;
    private LowLevelTermDao lowLevelTermDao;
    
    private String rejectedInternalAeStr;
    private String rejectedExternalAeStr;
    private String unmappedExternalAeStr;
    private String unmappedInternalAeStr;
    private String matchedAeMappingStr;

    private AdverseEventReportingPeriod reportingPeriod;


    private List<AdverseEventDTO> unMappedExternalAeList;
    private List<AdverseEventDTO> unMappedInternalAeList;
    private List<AdverseEventDTO> rejectedExternalAeList;
    private List<AdverseEventDTO> rejectedInternalAeList;


    private List<AdverseEventDTO> externalAeList;
    private List<AdverseEventDTO> internalAeList;
    private Map<Integer, AdverseEventDTO> externalAeMap;
    private Map<String, AdverseEventDTO> externalAeExternalIdMap;
    private Map<Integer, AdverseEventDTO> internalAeMap;
    
    private Map<AdverseEventDTO, AdverseEventDTO> matchedAeMapping;
    private Map<Integer, Integer> matchedAeIdMapping;

    private Map<String, AdverseEventDTO> mergeMap;

    private List<AdverseEventDTO> errorAeList;

    static AdverseEventDTO find(AdverseEventDTO value, Map<AdverseEventDTO, AdverseEventDTO> map){
        for(Map.Entry<AdverseEventDTO, AdverseEventDTO> e: map.entrySet()){
            if(e.getValue().equals(value)) return e.getKey();
        }
        return null;
    }
    static AdverseEventDTO find(AdverseEventDTO ae, List<AdverseEventDTO> list){
        AdverseEventDTO found = null;
        for(AdverseEventDTO dto : list) {
            if(ae.isSame(dto)) {
                found = dto ;
                break;
            }
        }
      
        return found;
    }
    static AdverseEventDTO find(int id, List<AdverseEventDTO> list){
        AdverseEventDTO found = null;
        for(AdverseEventDTO dto : list) {
            if(dto.getId() == id) {
                found = dto ;
                break;
            }
        }
      
        return found;
    }

    public AdverseEventReconciliationCommand(ReconciliationReportDao reconciliationReportDao,ExternalAdverseEventDao externalAdverseEventDao, 
                                             CtcTermDao ctcTermDao, LowLevelTermDao lowLevelTermDao){
        this.reconciliationReportDao = reconciliationReportDao;
        this.externalAdverseEventDao = externalAdverseEventDao;
        this.ctcTermDao = ctcTermDao;
        this.lowLevelTermDao = lowLevelTermDao;
        
        this.errorAeList = new ArrayList<AdverseEventDTO>();
        externalAeList = new ArrayList<AdverseEventDTO>();
        internalAeList = new ArrayList<AdverseEventDTO>();
       
        externalAeMap = new LinkedHashMap<Integer, AdverseEventDTO>();
        externalAeExternalIdMap = new LinkedHashMap<String, AdverseEventDTO>();
        internalAeMap = new LinkedHashMap<Integer, AdverseEventDTO>();
        mergeMap = new HashMap<String, AdverseEventDTO>();

        unMappedInternalAeList = new ArrayList<AdverseEventDTO>();
        unMappedExternalAeList = new ArrayList<AdverseEventDTO>();
        rejectedInternalAeList = new ArrayList<AdverseEventDTO>();
        rejectedExternalAeList = new ArrayList<AdverseEventDTO>();

        matchedAeMapping = new HashMap<AdverseEventDTO, AdverseEventDTO>();
        matchedAeIdMapping = new HashMap<Integer, Integer>();


    }
    private boolean isValidGrade(Grade g, List<? extends CodedGrade> grades){
        if(g == null) return false;
        if(grades == null || grades.isEmpty()) return false;
        for(CodedGrade cg : grades){
            if(cg.getCode() == g.getCode()) return true;
        }
        return false;
    } 
    public void loadExternalAdverseEvents(){
        ExternalAdverseEventQuery query = new ExternalAdverseEventQuery();
        query.filterByCreatedOnBefore(new Date());
        query.filterByStatus(ExternalAEReviewStatus.PENDING);
        query.filterByReportingPeriod(reportingPeriod.getId());
        List<ExternalAdverseEvent> eaeList = (List<ExternalAdverseEvent> )externalAdverseEventDao.search(query);
        AeTerminology terminology = reportingPeriod.getStudy().getAeTerminology();
        StringBuilder errorBuilder = new StringBuilder();
        for(ExternalAdverseEvent eae : eaeList){
            AdverseEventDTO dto = AdverseEventDTO.create(eae);
            errorBuilder.setLength(0);
            if(terminology.getTerm() == Term.CTC){
                CtcTerm term = ctcTermDao.getByCtepCodeandVersion(eae.getAdverseEventTermCode(), terminology.getCtcVersion());
                if(term == null){
                    errorBuilder.append(String.format("Invalid CTC term. Unable to find ('%s:%s') with in Ctc version '%s'.",
                            eae.getAdverseEventTermCode(),
                            eae.getAdverseEventTerm(),
                            terminology.getCtcVersion().getName()));
                }  else {
                    dto.getTerm().setId(term.getId());
                    dto.getTerm().setName(term.getFullName());
                    dto.getTerm().setCode(term.getCtepCode());
                    if(StringUtils.isNotEmpty(eae.getAdverseEventTermOtherValue())){
                        //load the other Other Meddra.
                        MeddraVersion meddraVersion = reportingPeriod.getStudy().getOtherMeddra();
                        if(meddraVersion != null){
                            List<LowLevelTerm> lowLevelTerms = lowLevelTermDao.getByMeddraCodeandVersion(eae.getAdverseEventTermCode(), terminology.getMeddraVersion().getId()) ;
                            LowLevelTerm otherTerm = (lowLevelTerms == null || lowLevelTerms.isEmpty()) ? null : lowLevelTerms.get(0);
                            if(otherTerm != null){
                                dto.getTerm().setOtherSpecify(otherTerm.getFullName());
                            }
                        }
                    }
                    if (!isValidGrade(eae.getGrade(), term.getGrades())){
                        errorBuilder.append(String.format("Grade '%s' is not valid for the term '%s:%s'. Valid options are '%s'",
                                eae.getGrade().getShortName() ,
                                eae.getAdverseEventTermCode(),
                                eae.getAdverseEventTerm(),
                                term.getGrades().toString()));
                    }
                }

            } else if(terminology.getTerm() == Term.MEDDRA){
                List<LowLevelTerm> lowLevelTerms = lowLevelTermDao.getByMeddraCodeandVersion(eae.getAdverseEventTermCode(), terminology.getMeddraVersion().getId()) ;
                LowLevelTerm term = (lowLevelTerms == null || lowLevelTerms.isEmpty()) ? null : lowLevelTerms.get(0);
                if(term == null){
                    errorBuilder.append(String.format("Invalid MedDRA term. Unable to find ('%s:%s') with in MedDRA version '%s'.",
                            eae.getAdverseEventTermCode(),
                            eae.getAdverseEventTerm(),
                            terminology.getMeddraVersion().getName()));
                }else{
                    dto.getTerm().setId(term.getId());
                    dto.getTerm().setCode(term.getMeddraCode());
                    dto.getTerm().setName(term.getFullName());
                }
            }

            if(errorBuilder.length() > 0){
                dto.setError(errorBuilder.toString());
                errorAeList.add(dto);
            } else {
                externalAeList.add(dto);
                externalAeMap.put(dto.getId(), dto);
                externalAeExternalIdMap.put(dto.getExternalID(), dto);
                unMappedExternalAeList.add(dto);
            }
        }



    }
    public void loadInternalAdverseEvents(){
        for(AdverseEvent ae : reportingPeriod.getAdverseEvents()){
            if(ae.isRetired()) continue;
            if(StringUtils.isEmpty(ae.getExternalId()) || externalAeExternalIdMap.containsKey(ae.getExternalId()) ){
                AdverseEventDTO dto = AdverseEventDTO.create(ae);
                internalAeList.add(dto);
                internalAeMap.put(dto.getId(), dto);
                if(StringUtils.isEmpty(ae.getExternalId())){
                    unMappedInternalAeList.add(dto);
                } else {
                    unMappedExternalAeList.remove(externalAeExternalIdMap.get(dto.getExternalID()));
                }
            }
        }
    }
    public  void doAutoMapping(){
        AeMappingsDTO oldMapping = StringUtils.isNotEmpty(reportingPeriod.getOldAeMapping()) ? AeMappingsDTO.deseralize(reportingPeriod.getOldAeMapping()) : null;
        if(oldMapping != null){
           //handle rejections
           if(oldMapping.getRejectedExternalAeIds() != null){
               for(Integer id : oldMapping.getRejectedExternalAeIds()){
                   AdverseEventDTO rejected = externalAeMap.get(id);
                   if(rejected != null){
                       unMappedExternalAeList.remove(rejected);
                       rejectedExternalAeList.add(rejected);
                   }
               }
           }
           if(oldMapping.getRejectedInternalAeIds() != null){
               for(Integer id : oldMapping.getRejectedInternalAeIds()){
                   AdverseEventDTO rejected = internalAeMap.get(id);
                   if(rejected != null){
                       unMappedInternalAeList.remove(rejected);
                       rejectedInternalAeList.add(rejected);
                   }
               }
           }

           //handle mappings
           if(oldMapping.getRelations() != null){
               for(AeMergeDTO relation : oldMapping.getRelations()){
                   AdverseEventDTO iae = internalAeMap.get(relation.getInteralAeId());
                   AdverseEventDTO eae = internalAeMap.get(relation.getExternalAeId());
                   if(iae == null || eae == null) continue;
                   matchedAeMapping.put(iae, eae);
                   matchedAeIdMapping.put(iae.getId(), eae.getId());
                   unMappedExternalAeList.remove(eae);
                   unMappedInternalAeList.remove(iae);
                   AdverseEventDTO merged = addToMergeMap(iae, eae);
                   relation.mergeChanges(iae, eae, merged);
               }
           }

        }

        List<AdverseEventDTO> unmapped = new ArrayList<AdverseEventDTO>( unMappedInternalAeList);
        for(AdverseEventDTO ae : unmapped){
            AdverseEventDTO found = find(ae, unMappedExternalAeList);
            if(found != null){
                matchedAeMapping.put(ae, found);
                matchedAeIdMapping.put(ae.getId(), found.getId());
                unMappedExternalAeList.remove(found);
                unMappedInternalAeList.remove(ae);
                addToMergeMap(ae, found);
            }
        }
        
    }
    public void seralizeMapping()  {
        AeMappingsDTO mappings = new AeMappingsDTO();
        List<Integer> rejectedExternal = new ArrayList<Integer>();
        for(AdverseEventDTO ae : getRejectedExternalAeList()) rejectedExternal.add(ae.getId());
        mappings.setRejectedExternalAeIds(ArrayUtils.toPrimitive(rejectedExternal.toArray(new Integer[]{})));
        
        List<Integer> rejectedInternal = new ArrayList<Integer>();
        for(AdverseEventDTO ae : getRejectedInternalAeList()) rejectedInternal.add(ae.getId());
        mappings.setRejectedInternalAeIds(ArrayUtils.toPrimitive(rejectedInternal.toArray(new Integer[]{})));
        List<AeMergeDTO> relations = new ArrayList<AeMergeDTO>();
        for(Map.Entry<AdverseEventDTO, AdverseEventDTO> entry: matchedAeMapping.entrySet()){
            String key = entry.getKey().getId() +  "_"  +  entry.getValue().getId();
            AeMergeDTO merge = new AeMergeDTO();
            merge.setInteralAeId(entry.getKey().getId());
            merge.setExternalAeId(entry.getValue().getId());
            relations.add(merge);
            AdverseEventDTO ae = mergeMap.get(key);
            if(ae != null) merge.copyChanges(entry.getKey(), entry.getValue(), ae);
        }
        mappings.setRelations(relations.toArray(new AeMergeDTO[]{}));

        reportingPeriod.setOldAeMapping(AeMappingsDTO.seralize(mappings));
    }

    public Study getStudy() {
        return reportingPeriod.getStudy();
    }
    public Participant getParticipant() {
        return  reportingPeriod.getParticipant();
    }
    public AdverseEventReportingPeriod getReportingPeriod() {
        return reportingPeriod;
    }

    public void setReportingPeriod(AdverseEventReportingPeriod reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }

    public StudyParticipantAssignment getAssignment(){
        return reportingPeriod.getAssignment();
    }

    public List<AdverseEventDTO> getUnMappedExternalAeList() {
        return unMappedExternalAeList;
    }

    public void setUnMappedExternalAeList(List<AdverseEventDTO> unMappedExternalAeList) {
        this.unMappedExternalAeList = unMappedExternalAeList;
    }

    public List<AdverseEventDTO> getUnMappedInternalAeList() {
        return unMappedInternalAeList;
    }

    public void setUnMappedInternalAeList(List<AdverseEventDTO> unMappedInternalAeList) {
        this.unMappedInternalAeList = unMappedInternalAeList;
    }

    public List<AdverseEventDTO> getRejectedExternalAeList() {
        return rejectedExternalAeList;
    }

    public void setRejectedExternalAeList(List<AdverseEventDTO> rejectedExternalAeList) {
        this.rejectedExternalAeList = rejectedExternalAeList;
    }

    public List<AdverseEventDTO> getRejectedInternalAeList() {
        return rejectedInternalAeList;
    }

    public void setRejectedInternalAeList(List<AdverseEventDTO> rejectedInternalAeList) {
        this.rejectedInternalAeList = rejectedInternalAeList;
    }

    public List<AdverseEventDTO> getExternalAeList() {
        return externalAeList;
    }

    public void setExternalAeList(List<AdverseEventDTO> externalAeList) {
        this.externalAeList = externalAeList;
    }

    public List<AdverseEventDTO> getInternalAeList() {
        return internalAeList;
    }

    public void setInternalAeList(List<AdverseEventDTO> internalAeList) {
        this.internalAeList = internalAeList;
    }

    public Map<AdverseEventDTO, AdverseEventDTO> getMatchedAeMapping() {
        return matchedAeMapping;
    }

    public void setMatchedAeMapping(Map<AdverseEventDTO, AdverseEventDTO> matchedAeMapping) {
        this.matchedAeMapping = matchedAeMapping;
    }

    public String getRejectedExternalAeStr() {
        return rejectedExternalAeStr;
    }

    public void setRejectedExternalAeStr(String rejectedExternalAeStr) {
        this.rejectedExternalAeStr = rejectedExternalAeStr;
    }

    public String getUnmappedExternalAeStr() {
        return unmappedExternalAeStr;
    }

    public void setUnmappedExternalAeStr(String unmappedExternalAeStr) {
        this.unmappedExternalAeStr = unmappedExternalAeStr;
    }

    public String getUnmappedInternalAeStr() {
        return unmappedInternalAeStr;
    }

    public void setUnmappedInternalAeStr(String unmappedInternalAeStr) {
        this.unmappedInternalAeStr = unmappedInternalAeStr;
    }

    public String getMatchedAeMappingStr() {
        return matchedAeMappingStr;
    }

    public void setMatchedAeMappingStr(String matchedAeMappingStr) {
        this.matchedAeMappingStr = matchedAeMappingStr;
    }

    public Map<Integer, AdverseEventDTO> getExternalAeMap() {
        return externalAeMap;
    }

    public void setExternalAeMap(Map<Integer, AdverseEventDTO> externalAeMap) {
        this.externalAeMap = externalAeMap;
    }

    public Map<Integer, AdverseEventDTO> getInternalAeMap() {
        return internalAeMap;
    }

    public void setInternalAeMap(Map<Integer, AdverseEventDTO> internalAeMap) {
        this.internalAeMap = internalAeMap;
    }

    public Map<String, AdverseEventDTO> getMergeMap() {
        return mergeMap;
    }

    public void setMergeMap(Map<String, AdverseEventDTO> mergeMap) {
        this.mergeMap = mergeMap;
    }

    public Map<Integer, Integer> getMatchedAeIdMapping() {
        return matchedAeIdMapping;
    }

    public void setMatchedAeIdMapping(Map<Integer, Integer> matchedAeIdMapping) {
        this.matchedAeIdMapping = matchedAeIdMapping;
    }

    public String getRejectedInternalAeStr() {
        return rejectedInternalAeStr;
    }

    public void setRejectedInternalAeStr(String rejectedInternalAeStr) {
        this.rejectedInternalAeStr = rejectedInternalAeStr;
    }

    public List<AdverseEventDTO> getErrorAeList() {
        return errorAeList;
    }

    public void setErrorAeList(List<AdverseEventDTO> errorAeList) {
        this.errorAeList = errorAeList;
    }


    public List<String> getAllReviewedExternalAeExternalIds(){
        List<String> ids = new ArrayList<String>();
        List<String> rejected = getRejectedExternalAeExternalIds();
        List<String> incorrect = getIncorrectExternalAeExternalIds();
        boolean hasRejected = rejected.isEmpty();
        boolean hasIncorrect = incorrect.isEmpty();
        for(AdverseEventDTO ae : externalAeList){
            if(hasRejected && rejected.contains(ae.getExternalID())) continue;
            if(hasIncorrect && incorrect.contains(ae.getExternalID())) continue;
            ids.add(ae.getExternalID());
        }
        return ids;
    }

    public List<String> getRejectedExternalAeExternalIds(){
        List<String> ids = new ArrayList<String>();
        for(AdverseEventDTO ae : rejectedInternalAeList){
            ids.add(ae.getExternalID());
        }
        return ids;
    }

    public List<String> getIncorrectExternalAeExternalIds(){
        List<String> ids = new ArrayList<String>();
        for(AdverseEventDTO ae : errorAeList){
            ids.add(ae.getExternalID());
        }
        return ids;
    }
    public void processExternalAeRejections(){
        rejectedExternalAeList.clear();
        if(StringUtils.isEmpty(rejectedExternalAeStr)) return;

        String arr[] = StringUtils.split(rejectedExternalAeStr, '_');
        Set<Integer> set = new HashSet<Integer>();
        if(arr != null) {
            for(String s : arr){
                if(StringUtils.isEmpty(s)) continue;
                Integer i = Integer.parseInt(s);
                if(set.add(i)) {
                    AdverseEventDTO ae = externalAeMap.get(i);
                    rejectedExternalAeList.add(ae);
                }

            }
        }
    }


    public void processInternalAeRejections(){
        rejectedInternalAeList.clear();
        if(StringUtils.isEmpty(rejectedInternalAeStr)) return;
        String arr[] = StringUtils.split(rejectedInternalAeStr, '_');

        Set<Integer> set = new HashSet<Integer>();
        if(arr != null) {
            for(String s : arr){
                if(StringUtils.isEmpty(s)) continue;
                Integer i = Integer.parseInt(s);
                if(set.add(i)) {
                    AdverseEventDTO ae = internalAeMap.get(i);
                    rejectedInternalAeList.add(ae);
                }
            }
        }
    }


    public void processUnmappedExternalAes(){
        unMappedExternalAeList.clear();
        if(StringUtils.isEmpty(unmappedExternalAeStr)) return;
        String arr[] = StringUtils.split(unmappedExternalAeStr, '_');

        if(arr != null) {
            for(String s : arr){
                AdverseEventDTO ae = externalAeMap.get(Integer.parseInt(s));
                unMappedExternalAeList.add(ae);
            }
        }
    }



    public void processUnmappedInternalAes(){
        unMappedInternalAeList.clear();
        String arr[] = StringUtils.split(unmappedInternalAeStr, '_');
        if(StringUtils.isEmpty(unmappedInternalAeStr)) return;

        if(arr != null) {
            for(String s : arr){
                AdverseEventDTO ae = internalAeMap.get(Integer.parseInt(s));
                unMappedInternalAeList.add(ae);
            }
        }
    }
    
    public void processAeMapping(){
        matchedAeMapping.clear();
        matchedAeIdMapping.clear();
        if(StringUtils.isEmpty(matchedAeMappingStr)) return;
        String arr[] = StringUtils.split(matchedAeMappingStr, '&') ;
        if(arr != null){
            for(String s : arr){
                String ids[] = StringUtils.split(s, '=');
                if(ids != null){
                    AdverseEventDTO iae = internalAeMap.get(Integer.parseInt(ids[0]));
                    AdverseEventDTO eae = externalAeMap.get(Integer.parseInt(ids[1]));
                    matchedAeMapping.put(iae, eae);
                    matchedAeIdMapping.put(iae.getId(), eae.getId());
                    addToMergeMap(iae,eae);
                }
            }
        }
    }

    public AdverseEventDTO addToMergeMap(AdverseEventDTO iae, AdverseEventDTO eae){
        String key = iae.getId() + "_" + eae.getId();
        if(!mergeMap.containsKey(key)){
            AdverseEventDTO ae = iae.clone();
            List<String> diff = iae.diff(eae); 
            ae.clearFields(diff.toArray(new String[]{}));
            return  mergeMap.put(key, ae);
        }
        return mergeMap.get(key);
    }


    public ReconciliationReport generateReconcilationReport(){
        ReconciliationReport report = new ReconciliationReport();
        report.setReviewedBy(SecurityUtils.getUserLoginName());
        report.setCreatedDate(new Date());
        for(AdverseEventDTO ae : rejectedExternalAeList){
           report.addReconciledAdverseEvent(ae.getReconciledAdverseEvent(ReconciliationAction.DELETE));
        }
        for(AdverseEventDTO ae : rejectedInternalAeList){
           report.addReconciledAdverseEvent(ae.getReconciledAdverseEvent(ReconciliationAction.DELETE));
        }
        for(AdverseEventDTO ae : unMappedExternalAeList){
            report.addReconciledAdverseEvent(ae.getReconciledAdverseEvent(ReconciliationAction.ADD));
        }
        for(AdverseEventDTO ae : unMappedInternalAeList){
            report.addReconciledAdverseEvent(ae.getReconciledAdverseEvent(ReconciliationAction.ADD));
        }
        for(AdverseEventDTO ae : errorAeList){
            report.addReconciledAdverseEvent(ae.getReconciledAdverseEvent(ReconciliationAction.ERROR));
        }
        
        for(Map.Entry<AdverseEventDTO, AdverseEventDTO> e : matchedAeMapping.entrySet()){
            AdverseEventDTO iae = e.getKey();
            AdverseEventDTO eae = e.getValue();
            String key = iae.getId() + "_" + eae.getId();
            
            AdverseEventDTO merged = mergeMap.get(key);
            if(merged == null) continue;

            boolean linked = true;

            if(!iae.diff(merged).isEmpty()){
                ReconciledAdverseEvent rae = merged.getReconciledAdverseEvent(ReconciliationAction.UPDATE);
                rae.setSystem(ReconciliationSystem.CAAERS);
                rae.setExternalId(eae.getExternalID());
                rae.setItemId(iae.getId());
                report.addReconciledAdverseEvent(rae);
                linked = false;
            }
            
            if(!eae.diff(merged).isEmpty()){
                ReconciledAdverseEvent rae = merged.getReconciledAdverseEvent(ReconciliationAction.UPDATE);
                rae.setSystem(ReconciliationSystem.FORCE);
                rae.setItemId(eae.getId());
                report.addReconciledAdverseEvent(rae);
                linked = false;
            }

            if(linked){
                ReconciledAdverseEvent rae = merged.getReconciledAdverseEvent(ReconciliationAction.LINKED);
                rae.setSystem(ReconciliationSystem.CAAERS);
                rae.setExternalId(eae.getExternalID());
            }

        }
        
       return report;
    }
}
