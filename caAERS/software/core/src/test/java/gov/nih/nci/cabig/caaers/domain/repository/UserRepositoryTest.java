package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao._UserDao;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserRepositoryTest extends AbstractTestCase {
	
	private UserRepositoryImpl repository;
	private _UserDao userDao;
	private UserProvisioningManager userProvisioningManager;
	
    @Override
    protected void setUp() throws Exception {
    	repository = new UserRepositoryImpl();
    	userDao = registerDaoMockFor(_UserDao.class);
    	userProvisioningManager = registerMockFor(UserProvisioningManager.class);
    	repository.setUserDao(userDao);
    	repository.setUserProvisioningManager(userProvisioningManager);
    }
    
    public void testGetUserByLoginName_UserExists() throws Exception{
    	String userId = "100";
    	String loginName = "monishd";
    	_User _user = new _User();
    	_user.setLoginName("monishd");
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
    	csmUser.setUserId(new Long(userId));
    	csmUser.setLoginName(loginName);
    	
    	Set<Group> groups;
        Group group = new Group();
        group.setGroupId(-2L);
        group.setGroupName("data_analyst");
        groups = new HashSet<Group>();
        groups.add(group);
    	
    	expect(userDao.getByLoginName(loginName)).andReturn(_user).anyTimes();
    	expect(userProvisioningManager.getUser(loginName)).andReturn(csmUser).anyTimes();
    	expect(userProvisioningManager.getGroups(userId)).andReturn(groups).anyTimes();
    	replayMocks();
    	_user = repository.getUserByLoginName(loginName);
    	verifyMocks();
    	assertNotNull(_user);
    	assertEquals("monishd",_user.getLoginName());
    	assertNotNull(_user.getCsmUser());
    	assertEquals("100", _user.getCsmUser().getUserId().toString());
    	assertNotNull(_user.getUserGroupTypes());
    	assertEquals(1,_user.getUserGroupTypes().size());
    	assertEquals("data_analyst",_user.getUserGroupTypes().get(0).getCsmName());
    }
    
    
    public void testGetUserByLoginName_UserDoesNotExist() throws Exception{
    	String userId = "100";
    	String loginName = "monishd";
    	_User _user = null;
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
    	csmUser.setUserId(new Long(userId));
    	csmUser.setLoginName(loginName);
    	
    	Set<Group> groups;
        Group group = new Group();
        group.setGroupId(-2L);
        group.setGroupName("data_analyst");
        groups = new HashSet<Group>();
        groups.add(group);
    	
    	expect(userDao.getByLoginName(loginName)).andReturn(_user).anyTimes();
    	expect(userProvisioningManager.getUser(loginName)).andReturn(csmUser).anyTimes();
    	expect(userProvisioningManager.getGroups(userId)).andReturn(groups).anyTimes();
    	replayMocks();
    	_user = repository.getUserByLoginName(loginName);
    	verifyMocks();
    	assertNotNull(_user);
    	assertNotNull(_user.getCsmUser());
    	assertEquals("100", _user.getCsmUser().getUserId().toString());
    	assertNotNull(_user.getUserGroupTypes());
    	assertEquals(1,_user.getUserGroupTypes().size());
    	assertEquals("data_analyst",_user.getUserGroupTypes().get(0).getCsmName());
    }
    
    public void testGetUserByLoginName_caAERSAndCSMUserDoesNotExist() throws Exception{
    	String loginName = "monishd";
    	_User _user = null;
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
    	
    	expect(userDao.getByLoginName(loginName)).andReturn(_user).anyTimes();
    	expect(userProvisioningManager.getUser(loginName)).andReturn(csmUser).anyTimes();
    	replayMocks();
    	_user = repository.getUserByLoginName(loginName);
    	verifyMocks();
    	assertNull(_user);
    }
    
    public void testGetUserByLoginName_caAERSUserExists_CSMUserDoesNotExist() throws Exception{
    	String loginName = "monishd";
    	_User _user = new _User();
    	_user.setLoginName("monishd");

    	gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
    	
    	expect(userDao.getByLoginName(loginName)).andReturn(_user).anyTimes();
    	expect(userProvisioningManager.getUser(loginName)).andReturn(csmUser).anyTimes();
    	replayMocks();
    	try{
    		_user = repository.getUserByLoginName(loginName);
    		fail("User repository should not have returned a _User");
    	}catch(CaaersSystemException csEx){
    		/*good*/
    	}
    	verifyMocks();
    }
    
    public void testUnlockUser(){
    	_User _user = new _User();
    	_user.setLoginName("monishd");
    	_user.setFailedLoginAttempts(3);
    	_user.setLastFailedLoginAttemptTime(new Timestamp(new Date().getTime()));
    	userDao.save(_user);
    	replayMocks();
    	repository.unlockUser(_user);
    	verifyMocks();
    	assertNull(_user.getLastFailedLoginAttemptTime());
    	assertEquals(0,_user.getFailedLoginAttempts());
    }
}
