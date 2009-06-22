package gov.nih.nci.cabig.caaers.domain;

/**
 * This enumeration represents the loading status. It is mainly used by classes that are loaded into
 * caaers system, via grid service.
 * 
 * @author Biju Joseph
 * 
 */
public enum LoadStatus {

    INPROGRESS(0), COMPLETE(1);
    int code;

    private LoadStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
