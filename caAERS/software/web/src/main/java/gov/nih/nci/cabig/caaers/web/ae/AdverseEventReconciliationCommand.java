package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationCommand {

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
    private Map<Integer, AdverseEventDTO> internalAeMap;
    
    private Map<AdverseEventDTO, AdverseEventDTO> matchedAeMapping;
    private Map<Integer, Integer> matchedAeIdMapping;

    private Map<String, AdverseEventDTO> mergeMap;

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
    
    
    public AdverseEventReconciliationCommand(List<AdverseEventDTO> internalAeList, List<AdverseEventDTO> externalAeList){
        this.externalAeList = externalAeList;
        this.internalAeList = internalAeList;
        
        internalAeMap = new HashMap<Integer, AdverseEventDTO>();
        externalAeMap = new HashMap<Integer, AdverseEventDTO>();
        mergeMap = new HashMap<String, AdverseEventDTO>();

        unMappedInternalAeList = new ArrayList<AdverseEventDTO>();
        unMappedExternalAeList = new ArrayList<AdverseEventDTO>();
        rejectedInternalAeList = new ArrayList<AdverseEventDTO>();
        rejectedExternalAeList = new ArrayList<AdverseEventDTO>();

        for(AdverseEventDTO ae : internalAeList){
            internalAeMap.put(ae.getId(), ae);
            if(ae.isRejected()) this.rejectedInternalAeList.add(ae);
            else this.unMappedInternalAeList.add(ae);
        }
        for(AdverseEventDTO ae : externalAeList){
            externalAeMap.put(ae.getId(), ae);
            if(ae.isRejected()) this.rejectedExternalAeList.add(ae);
            else this.unMappedExternalAeList.add(ae);
        }

        matchedAeMapping = new HashMap<AdverseEventDTO, AdverseEventDTO>();
        matchedAeIdMapping = new HashMap<Integer, Integer>();
        List<AdverseEventDTO> tmp = new ArrayList<AdverseEventDTO>( unMappedInternalAeList);
        for(AdverseEventDTO ae : tmp){
           AdverseEventDTO found = find(ae, unMappedExternalAeList);
           if(found != null){
               matchedAeMapping.put(ae, found);
               matchedAeIdMapping.put(ae.getId(), found.getId());
               unMappedExternalAeList.remove(found);
               unMappedInternalAeList.remove(ae);
           }
        }
        tmp = new ArrayList<AdverseEventDTO>( unMappedInternalAeList);
        for(AdverseEventDTO ae : tmp){
            if(StringUtils.isNotEmpty(ae.getExternalID())) unMappedInternalAeList.remove(ae);
        }

    }

    
    public void link(Integer internalAeId, Integer externalAeId){
        AdverseEventDTO externalAe = find(externalAeId, externalAeList);
        AdverseEventDTO internalAe = find(internalAeId, internalAeList);
        AdverseEventDTO oldExternalAe = null;
        if(externalAe != null) {
            unMappedExternalAeList.remove(externalAe);
        }
        if(internalAe != null) {
            unMappedInternalAeList.remove(internalAe);
            oldExternalAe = matchedAeMapping.get(internalAe);
        }
        if(oldExternalAe != null){
            unMappedExternalAeList.add(oldExternalAe);
        }
        if(internalAe != null && externalAe != null){
            matchedAeMapping.put(internalAe, externalAe);
        }

    }
    public void unlink(Integer internalAeId, Integer externalAeId){
        AdverseEventDTO internalAe = find(internalAeId, internalAeList);
        if(internalAe != null){
            AdverseEventDTO externalAe = matchedAeMapping.get(internalAe);
            if(externalAe != null) {
                unMappedExternalAeList.add(externalAe);
            }
            matchedAeMapping.remove(internalAe);
            unMappedInternalAeList.add(internalAe);
            internalAe.setExternalID(null);
        }
    }
    
    public void reject(Integer externalAeId){
        AdverseEventDTO externalAe = find(externalAeId, externalAeList);
        if(externalAe != null){
            rejectedExternalAeList.add(externalAe);
            unMappedExternalAeList.remove(externalAe);
            AdverseEventDTO internalAe = find(externalAe, matchedAeMapping);
            if(internalAe != null){
                internalAe.setExternalID(null);
                unMappedInternalAeList.add(internalAe);
                matchedAeMapping.remove(internalAe);
            }

            externalAe.setRejected(true);
        }
    }
    
    public void unreject(Integer externalAeId){
        AdverseEventDTO externalAe = find(externalAeId, rejectedExternalAeList);
        if(externalAe != null) {
            unMappedExternalAeList.add(externalAe);
            externalAe.setRejected(false);
            rejectedExternalAeList.remove(externalAe);
        }
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

    public void processExternalAeRejections(){
        String arr[] = StringUtils.split(rejectedExternalAeStr, ',');
        rejectedExternalAeList.clear();
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
        String arr[] = StringUtils.split(rejectedInternalAeStr, ',');
        rejectedInternalAeList.clear();
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
        String arr[] = StringUtils.split(unmappedExternalAeStr, ',');
        unMappedExternalAeList.clear();
        if(arr != null) {
            for(String s : arr){
                AdverseEventDTO ae = externalAeMap.get(Integer.parseInt(s));
                unMappedExternalAeList.add(ae);
            }
        }
    }



    public void processUnmappedInternalAes(){
        String arr[] = StringUtils.split(unmappedInternalAeStr, ',');
        unMappedInternalAeList.clear();
        if(arr != null) {
            for(String s : arr){
                AdverseEventDTO ae = internalAeMap.get(Integer.parseInt(s));
                unMappedInternalAeList.add(ae);
            }
        }
    }
    
    public void processAeMapping(){
        String arr[] = StringUtils.split(matchedAeMappingStr, '&') ;
        matchedAeMapping.clear();
        matchedAeIdMapping.clear();
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

    public void addToMergeMap(AdverseEventDTO iae, AdverseEventDTO eae){
        String key = iae.getId() + "_" + eae.getId();
        if(!mergeMap.containsKey(key)){
            AdverseEventDTO ae = iae.clone();
            List<String> diff = iae.diff(eae); 
            ae.clearFields(diff.toArray(new String[]{}));
            mergeMap.put(key, ae);
        }
    }


    public ReconciliationReport generateReconcilationReport(){
       ReconciliationReport report = new ReconciliationReport();
       report.setReviewedBy(SecurityUtils.getUserLoginName());
       report.setCreatedDate(new Date());

       return null;
    }
}
