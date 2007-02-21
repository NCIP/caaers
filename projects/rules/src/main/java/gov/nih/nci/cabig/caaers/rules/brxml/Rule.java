package gov.nih.nci.cabig.caaers.rules.brxml;

import java.util.ArrayList;


public class Rule {
  protected Condition condition;

  protected Action action;

  protected MetaData metaData;

  protected String id;

  protected String name;

  protected String category;

  protected String packageName;

  protected String description;

  protected String format;


  public void addRuleAttribute(RuleAttribute ruleAttribute) {
    ruleAttributeList.add(ruleAttribute);
  }

  public RuleAttribute getRuleAttribute(int index) {
    return (RuleAttribute)ruleAttributeList.get( index );
  }

  public int sizeRuleAttributeList() {
    return ruleAttributeList.size();
  }

  public Condition getCondition() {
    return this.condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  public Action getAction() {
    return this.action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public MetaData getMetaData() {
    return this.metaData;
  }

  public void setMetaData(MetaData metaData) {
    this.metaData = metaData;
  }

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

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getPackageName() {
    return this.packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFormat() {
    return this.format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  protected ArrayList ruleAttributeList = new ArrayList();

}
