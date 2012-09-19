package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationCommand {

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
    private Map<AdverseEventDTO, AdverseEventDTO> matchedAeMapping;

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

        unMappedInternalAeList = new ArrayList<AdverseEventDTO>();
        unMappedExternalAeList = new ArrayList<AdverseEventDTO>();
        rejectedInternalAeList = new ArrayList<AdverseEventDTO>();
        rejectedExternalAeList = new ArrayList<AdverseEventDTO>();

        for(AdverseEventDTO ae : internalAeList){
            if(ae.isRejected()) this.rejectedInternalAeList.add(ae);
            else this.unMappedInternalAeList.add(ae);
        }
        for(AdverseEventDTO ae : externalAeList){
            if(ae.isRejected()) this.rejectedExternalAeList.add(ae);
            else this.unMappedExternalAeList.add(ae);
        }

        matchedAeMapping = new HashMap<AdverseEventDTO, AdverseEventDTO>();
        List<AdverseEventDTO> tmp = new ArrayList<AdverseEventDTO>( unMappedInternalAeList);
        for(AdverseEventDTO ae : tmp){
           AdverseEventDTO found = find(ae, unMappedExternalAeList);
           if(found != null){
               matchedAeMapping.put(ae, found);
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
}
