package gov.nih.nci.cabig.caaers.rules.brxml;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;


public class FieldConstraint {
  protected String fieldName;


  public void addLiteralRestriction(LiteralRestriction literalRestriction) {
    literalRestrictionList.add(literalRestriction);
  }

  public LiteralRestriction getLiteralRestriction(int index) {
    return (LiteralRestriction)literalRestrictionList.get( index );
  }

  public int sizeLiteralRestrictionList() {
    return literalRestrictionList.size();
  }

  public void addVariableRestriction(VariableRestriction variableRestriction) {
    variableRestrictionList.add(variableRestriction);
  }

  public VariableRestriction getVariableRestriction(int index) {
    return (VariableRestriction)variableRestrictionList.get( index );
  }

  public int sizeVariableRestrictionList() {
    return variableRestrictionList.size();
  }

  public void addReturnValueRestriction(ReturnValueRestriction returnValueRestriction) {
    returnValueRestrictionList.add(returnValueRestriction);
  }

  public ReturnValueRestriction getReturnValueRestriction(int index) {
    return (ReturnValueRestriction)returnValueRestrictionList.get( index );
  }

  public int sizeReturnValueRestrictionList() {
    return returnValueRestrictionList.size();
  }

  public String getFieldName() {
    return this.fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  protected ArrayList literalRestrictionList = new ArrayList();

  protected ArrayList variableRestrictionList = new ArrayList();

  protected ArrayList returnValueRestrictionList = new ArrayList();

}
