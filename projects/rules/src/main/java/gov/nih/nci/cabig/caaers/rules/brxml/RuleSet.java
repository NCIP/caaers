package gov.nih.nci.cabig.caaers.rules.brxml;

import java.io.Serializable;
import java.util.ArrayList;

public class RuleSet implements Serializable {

	protected String id;

	protected String name;

	protected String description;

	protected String status;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void addImport(String _import) {
		_importList.add(_import);
	}

	public String getImport(int index) {
		return (String) _importList.get(index);
	}

	public int sizeImportList() {
		return _importList.size();
	}

	public void addGlobal(String global) {
		globalList.add(global);
	}

	public String getGlobal(int index) {
		return (String) globalList.get(index);
	}

	public int sizeGlobalList() {
		return globalList.size();
	}

	public void addRule(Rule rule) {
		ruleList.add(rule);
	}

	public Rule getRule(int index) {
		return (Rule) ruleList.get(index);
	}

	public int sizeRuleList() {
		return ruleList.size();
	}

	protected ArrayList _importList = new ArrayList();

	protected ArrayList globalList = new ArrayList();

	protected ArrayList ruleList = new ArrayList();

}
