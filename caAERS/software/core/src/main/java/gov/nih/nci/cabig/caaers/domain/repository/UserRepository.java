package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain._User;

public interface UserRepository {

	public _User getUserByLoginName(String loginName);
	public void createOrUpdateUser(_User user,String changeURL);
	public void userChangePassword(_User user, String password, int maxHistorySize);
	public void save(_User user);
	public void sendUserEmail(String emailAddress, String subject, String text);
}
