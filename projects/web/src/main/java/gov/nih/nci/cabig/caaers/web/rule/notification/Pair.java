package gov.nih.nci.cabig.caaers.web.rule.notification;

public class Pair {
	private String key;
	private String value;
	
	public Pair(){
		this(null, null);
	}
	public Pair(String key, String value){
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
	 * @param key the key to set
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
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
