package gov.nih.nci.cabig.caaers.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigProperty {
	
	// Temporary lov values to hold the data pertaining to CADsr
	// TODO remove the lovMap once a CaDsr service is in place
	private Map map  = new HashMap<String, List<Lov>>();
	
	// set the schema to be connected, although Oracle's username connects to corresponding
	// schema, this property can ensure that your database connection will only be for one schema
	// Currently used by Unit testing framework only
	private String schema;
	
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
}
