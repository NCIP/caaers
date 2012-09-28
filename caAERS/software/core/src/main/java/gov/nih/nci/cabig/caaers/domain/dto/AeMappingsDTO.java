package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.utils.JSONUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AeMappingsDTO {
    private AeMergeDTO[] relations;
    private int[] rejectedExternalAeIds;
    private int[] rejectedInternalAeIds;

    public AeMergeDTO[] getRelations() {
        return relations;
    }

    public void setRelations(AeMergeDTO[] relations) {
        this.relations = relations;
    }

    public int[] getRejectedExternalAeIds() {
        return rejectedExternalAeIds;
    }

    public void setRejectedExternalAeIds(int[] rejectedExternalAeIds) {
        this.rejectedExternalAeIds = rejectedExternalAeIds;
    }

    public int[] getRejectedInternalAeIds() {
        return rejectedInternalAeIds;
    }

    public void setRejectedInternalAeIds(int[] rejectedInternalAeIds) {
        this.rejectedInternalAeIds = rejectedInternalAeIds;
    }
    
    public static AeMappingsDTO deseralize(String s){
        AeMappingsDTO m = new AeMappingsDTO();
        int p1 = s.indexOf("relations:[") + 11;
        int p2 = s.indexOf("],eRejected:");
        int p3 = s.indexOf("eRejected:[") + 11;
        int p4 = s.indexOf("],iRejected:");
        int p5 = s.indexOf("iRejected:[") +11;
        int p6 = s.indexOf("]", p5);
        String relations = p2 - p1 > 1 ? s.substring(p1,p2) : null;
        String eRejections = p4 - p3 > 1 ? s.substring(p3, p4) : null;
        String iRejections = p6 - p5 > 1 ? s.substring(p5, p6) : null;
        if(relations != null){
            String[] rArr = StringUtils.split(relations, "}");
            List<AeMergeDTO> merges = new ArrayList<AeMergeDTO>();
            for(int i =0; i < rArr.length; i++){
                merges.add(AeMergeDTO.deseralize(rArr[i]));
            }
            m.setRelations(merges.toArray(new AeMergeDTO[]{}));
        }

        if(eRejections != null){
           m.setRejectedExternalAeIds(AeMergeDTO.parseCommaSeperatedIntegers(eRejections));
        }

        if(iRejections != null){
            m.setRejectedInternalAeIds(AeMergeDTO.parseCommaSeperatedIntegers(iRejections));
        }

        return m;
    }
    
    public static String seralize(AeMappingsDTO o){
        StringBuilder sb = new StringBuilder("{");
        sb.append("relations:[");
        if(o.getRelations() != null){
            for(int i =0; i < o.getRelations().length; i++){
                if(i > 0) sb.append(",");
                sb.append(o.getRelations()[i].seralize(o.getRelations()[i]));
            }
        }
        sb.append("],eRejected:").append(JSONUtils.toJSON(o.getRejectedExternalAeIds()));
        sb.append(",iRejected:").append(JSONUtils.toJSON(o.getRejectedInternalAeIds()));
        return sb.append("}").toString();
    }

}
