package gov.nih.nci.cabig.caaers.domain.repository;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;

public interface UserRepository {

	public _User getUserByLoginName(String loginName);
	public void createOrUpdateUser(_User user,String changeURL);
	public void userChangePassword(_User user, String password, int maxHistorySize);
	public void save(_User user);
	public void sendUserEmail(String emailAddress, String subject, String text);
	public void unlockUser(_User user);
	public List searchCsmUser(String firstName,String lastName,String userName);
	public boolean loginIDInUse(String loginId);
	public List<UserGroupType> getUserGroups(String loginName);
}
