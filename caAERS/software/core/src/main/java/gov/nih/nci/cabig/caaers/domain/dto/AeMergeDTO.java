package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.utils.JSONUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class AeMergeDTO {
    Integer interalAeId;
    Integer externalAeId;
    int merges[] = new int[7];

    public Integer getInteralAeId() {
        return interalAeId;
    }

    public void setInteralAeId(Integer interalAeId) {
        this.interalAeId = interalAeId;
    }

    public Integer getExternalAeId() {
        return externalAeId;
    }

    public void setExternalAeId(Integer externalAeId) {
        this.externalAeId = externalAeId;
    }

    public int[] getMerges() {
        return merges;
    }

    public void setMerges(int[] merges) {
        this.merges = merges;
    }
    
    public void copyChanges(AdverseEventDTO iae, AdverseEventDTO eae, AdverseEventDTO mergedAe){
        List<String> diff = iae.diff(eae);
        if(diff.contains("term")){
            if(mergedAe.getTerm().isSame(iae.getTerm())) merges[0] = 1; else merges[0] = 2;
        }
        if(diff.contains("grade")){
            if(StringUtils.equals(iae.getGrade(), mergedAe.getGrade())) merges[1] = 1; else merges[1] = 2;
        }
        if(diff.contains("startDate")){
            if(StringUtils.equals(iae.getStartDate(), mergedAe.getStartDate())) merges[2] = 1; else merges[2] = 2;
        }
        if(diff.contains("endDate")){
            if(StringUtils.equals(iae.getEndDate(), mergedAe.getEndDate())) merges[3] = 1; else merges[3] = 2;
        }
        if(diff.contains("verbatim")){
            if(StringUtils.equals(iae.getVerbatim(), mergedAe.getVerbatim())) merges[4] = 1; else merges[4] = 2;
        }
        if(diff.contains("whySerious")){
            if(StringUtils.equals(iae.getWhySerious(), mergedAe.getWhySerious())) merges[5] = 1; else merges[5] = 2;
        }
        if(diff.contains("attribution")){
            if(StringUtils.equals(iae.getAttribution(), mergedAe.getAttribution())) merges[6] = 1; else merges[6] = 2;
        }
    }
    public void mergeChanges(AdverseEventDTO iae, AdverseEventDTO eae, AdverseEventDTO mergedAe){
        if(merges != null){
            mergedAe.setExternalID(eae.getExternalID());

            if(merges[0] == 1){
               mergedAe.setTerm(iae.getTerm().clone());
            } else if (merges[0] == 2){
               mergedAe.setTerm(eae.getTerm().clone());
            }

            if(merges[1] == 1){
                mergedAe.setGrade(iae.getGrade());
            } else if (merges[1] == 2){
                mergedAe.setGrade(eae.getGrade());
            }

            if(merges[2] == 1){
                mergedAe.setStartDate(iae.getStartDate());
            } else if (merges[2] == 2){
                mergedAe.setStartDate(eae.getStartDate());
            }
            if(merges[3] == 1){
                mergedAe.setEndDate(iae.getEndDate());
            } else if (merges[3] == 2){
                mergedAe.setEndDate(eae.getEndDate());
            }

            if(merges[4] == 1){
                mergedAe.setVerbatim(iae.getVerbatim());
            } else if (merges[4] == 2){
                mergedAe.setVerbatim(eae.getVerbatim());
            }
            if(merges[5] == 1){
                mergedAe.setWhySerious(iae.getWhySerious());
            } else if (merges[5] == 2){
                mergedAe.setWhySerious(eae.getWhySerious());
            }

            if(merges[6] == 1){
                mergedAe.setAttribution(iae.getAttribution());
            } else if (merges[6] == 2){
                mergedAe.setAttribution(eae.getAttribution());
            }

        }
    }
    
    public static String seralize(AeMergeDTO m) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("i:").append(m.getInteralAeId().intValue());
        sb.append(",e:").append(m.getExternalAeId().intValue());
        sb.append(",m:").append(JSONUtils.toJSON(m.getMerges()));
        return sb.append("}").toString();
    }
    
    public static AeMergeDTO deseralize(String s){
        AeMergeDTO m = new AeMergeDTO();
        int p1 = s.indexOf("i:") + 2;
        int p2 = s.indexOf(',', p1);
        String ae1 = s.substring(p1, p2);
        int p3 = s.indexOf("e:") + 2;
        int p4 = s.indexOf(',', p3);
        String ae2 = s.substring(p3, p4);
        int p5 = s.indexOf("m:[") + 3;
        int p6 = s.indexOf(']', p5);


        m.setInteralAeId(Integer.parseInt(ae1));
        m.setExternalAeId(Integer.parseInt(ae2));

        String arrStr = p6-p5 > 1 ? s.substring(p5, p6) : null;
        m.setMerges(parseCommaSeperatedIntegers(arrStr));
        return m;
    }
    
    public static int[] parseCommaSeperatedIntegers(String s){
        String[] ids = StringUtils.split(s, ",");
        if(ids != null){
            int[] merges = new int[ids.length];
            for(int i = 0; i < merges.length; i++){
                merges[i] = Integer.parseInt(ids[i]);
            }
            return merges;
        }
        return new int[0];
    }
}
