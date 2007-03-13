Following is list of modifications:

src/gov/nih/nci/logging/api/logger/hibernate/HibernateSessinFactoryHelper.java
 - I changed getAuditSession(SessionFactory) to just return the results of the
   call to SessionFactory.openSession().