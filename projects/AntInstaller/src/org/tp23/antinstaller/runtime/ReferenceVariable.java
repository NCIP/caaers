package org.tp23.antinstaller.runtime;

public class ReferenceVariable {
	
	private String name;
	private String key;
	private String value;
	private String replaceableKey;
	
	public ReferenceVariable() {
		
	}
	public ReferenceVariable(String name, String key, String value, String replaceableKey) {
		super();
		this.name = name;
		this.key = key;
		this.value = value;
		this.replaceableKey = replaceableKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReplaceableKey() {
		return replaceableKey;
	}
	public void setReplaceableKey(String replaceableKey) {
		this.replaceableKey = replaceableKey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return "Name:"+name+"\n"
		       +"Key:"+ key+"\n"
		       +"ReplaceableKey:"+replaceableKey+"\n"
		       +"Value: "+value;
	}

}