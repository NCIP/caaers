package gov.nih.nci.cabig.caaers.rules.brxml;

import java.sql.Date;
import java.util.ArrayList;


public class MetaData {
  protected String name;

  protected String description;

  protected String title;

  protected String state;

  protected Date lastModifiedDate;

  protected String lastContributor;

  protected String versionNumber;

  protected Date createdDate;

  protected String packageName;

  protected String format;

  protected String type;

  protected String creator;

  protected String externalSource;

  protected String subject;

  protected String externalRelation;

  protected String rights;

  protected String coverage;

  protected String publisher;

  protected String checkinComment;

  protected Date dateEffective;

  protected Date dateExpired;

  protected boolean dirty;


  public void addCategory(Category category) {
    categoryList.add(category);
  }

  public Category getCategory(int index) {
    return (Category)categoryList.get( index );
  }

  public int sizeCategoryList() {
    return categoryList.size();
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

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Date getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getLastContributor() {
    return this.lastContributor;
  }

  public void setLastContributor(String lastContributor) {
    this.lastContributor = lastContributor;
  }

  public String getVersionNumber() {
    return this.versionNumber;
  }

  public void setVersionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
  }

  public Date getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getPackageName() {
    return this.packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getFormat() {
    return this.format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCreator() {
    return this.creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getExternalSource() {
    return this.externalSource;
  }

  public void setExternalSource(String externalSource) {
    this.externalSource = externalSource;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getExternalRelation() {
    return this.externalRelation;
  }

  public void setExternalRelation(String externalRelation) {
    this.externalRelation = externalRelation;
  }

  public String getRights() {
    return this.rights;
  }

  public void setRights(String rights) {
    this.rights = rights;
  }

  public String getCoverage() {
    return this.coverage;
  }

  public void setCoverage(String coverage) {
    this.coverage = coverage;
  }

  public String getPublisher() {
    return this.publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getCheckinComment() {
    return this.checkinComment;
  }

  public void setCheckinComment(String checkinComment) {
    this.checkinComment = checkinComment;
  }

  public Date getDateEffective() {
    return this.dateEffective;
  }

  public void setDateEffective(Date dateEffective) {
    this.dateEffective = dateEffective;
  }

  public Date getDateExpired() {
    return this.dateExpired;
  }

  public void setDateExpired(Date dateExpired) {
    this.dateExpired = dateExpired;
  }

  public boolean getDirty() {
    return this.dirty;
  }

  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }

  protected ArrayList categoryList = new ArrayList();

}
