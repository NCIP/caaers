package gov.nih.nci.cabig.caaers.rules.brxml;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;


public class Column {
  protected String identifier;

  protected String objectType;


  public void addFieldBinding(FieldBinding fieldBinding) {
    fieldBindingList.add(fieldBinding);
  }

  public FieldBinding getFieldBinding(int index) {
    return (FieldBinding)fieldBindingList.get( index );
  }

  public int sizeFieldBindingList() {
    return fieldBindingList.size();
  }

  public void addFieldConstraint(FieldConstraint fieldConstraint) {
    fieldConstraintList.add(fieldConstraint);
  }

  public FieldConstraint getFieldConstraint(int index) {
    return (FieldConstraint)fieldConstraintList.get( index );
  }

  public int sizeFieldConstraintList() {
    return fieldConstraintList.size();
  }

  public void addPredicate(Predicate predicate) {
    predicateList.add(predicate);
  }

  public Predicate getPredicate(int index) {
    return (Predicate)predicateList.get( index );
  }

  public int sizePredicateList() {
    return predicateList.size();
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getObjectType() {
    return this.objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  protected ArrayList fieldBindingList = new ArrayList();

  protected ArrayList fieldConstraintList = new ArrayList();

  protected ArrayList predicateList = new ArrayList();

}
