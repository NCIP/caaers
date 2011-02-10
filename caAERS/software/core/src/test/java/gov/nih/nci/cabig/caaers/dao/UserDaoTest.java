package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Calendar;

public class UserDaoTest extends CaaersDbNoSecurityTestCase  {
	
	private UserDao _userDao;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		_userDao = (UserDao)getDeployedApplicationContext().getBean("_userDao");
	}

	public void testGetByLoginName(){
		User _user = _userDao.getByLoginName("monishd");
		assertNotNull(_user);
		assertEquals("monishd",_user.getLoginName());
		assertEquals("abcd",_user.getToken());
	}
	
	public void testSave(){
		User _newUser = new User();
		_newUser.setLoginName("monish123");
		_newUser.setToken("1234abc");
		_newUser.setFailedLoginAttempts(0);
		_newUser.setLastFailedLoginAttemptTime(DateUtils.today());
		
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();
		// get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();
		// a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		_newUser.setPasswordLastSet(currentTimestamp);
		_newUser.setTokenTime(currentTimestamp);
		
		_userDao.save(_newUser);
		interruptSession();
		User _user = _userDao.getByLoginName("monish123");
		assertNotNull(_user);
		
	}

}
