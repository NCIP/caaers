package gov.nih.nci.cabig.caaers.rules.brxml;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;


public class Or {
  public void addAnd(And and) {
    andList.add(and);
  }

  public And getAnd(int index) {
    return (And)andList.get( index );
  }

  public int sizeAndList() {
    return andList.size();
  }

  public void addOr(Or or) {
    orList.add(or);
  }

  public Or getOr(int index) {
    return (Or)orList.get( index );
  }

  public int sizeOrList() {
    return orList.size();
  }

  public void addNot(Not not) {
    notList.add(not);
  }

  public Not getNot(int index) {
    return (Not)notList.get( index );
  }

  public int sizeNotList() {
    return notList.size();
  }

  public void addExists(Exists exists) {
    existsList.add(exists);
  }

  public Exists getExists(int index) {
    return (Exists)existsList.get( index );
  }

  public int sizeExistsList() {
    return existsList.size();
  }

  public void addEval(String eval) {
    evalList.add(eval);
  }

  public String getEval(int index) {
    return (String)evalList.get( index );
  }

  public int sizeEvalList() {
    return evalList.size();
  }

  public void addColumn(Column column) {
    columnList.add(column);
  }

  public Column getColumn(int index) {
    return (Column)columnList.get( index );
  }

  public int sizeColumnList() {
    return columnList.size();
  }

  protected ArrayList andList = new ArrayList();

  protected ArrayList orList = new ArrayList();

  protected ArrayList notList = new ArrayList();

  protected ArrayList existsList = new ArrayList();

  protected ArrayList evalList = new ArrayList();

  protected ArrayList columnList = new ArrayList();

}
