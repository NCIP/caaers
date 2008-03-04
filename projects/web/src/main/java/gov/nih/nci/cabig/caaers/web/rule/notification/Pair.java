package gov.nih.nci.cabig.caaers.web.rule.notification;

public class Pair {
    private String key;

    private String value;

    private String attribute1;

    private String attribute2;

    public Pair() {
        this(null, null);
    }

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *                the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *                the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the attribute1
     */
    public String getAttribute1() {
        return attribute1;
    }

    /**
     * @param attribute1
     *                the attribute1 to set
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * @return the attribute2
     */
    public String getAttribute2() {
        return attribute2;
    }

    /**
     * @param attribute2
     *                the attribute2 to set
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

}
