/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.security.UserProvisioningManager;

import java.sql.Timestamp;
import java.util.Date;

public class UserRepositoryTest extends AbstractTestCase {
	
	private UserRepositoryImpl repository;
	private UserDao userDao;
	private UserProvisioningManager userProvisioningManager;
	
    @Override
    protected void setUp() throws Exception {
    	repository = new UserRepositoryImpl();
    	userDao = registerDaoMockFor(UserDao.class);
    	userProvisioningManager = registerMockFor(UserProvisioningManager.class);
    	repository.setUserDao(userDao);
    	repository.setUserProvisioningManager(userProvisioningManager);
    }
    


    public void testGetUserByLoginName_caAERSAndCSMUserDoesNotExist() throws Exception{
    	String loginName = "monishd";
    	User _user = null;
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
    	User _user = new User();
    	_user.setLoginName("monishd");

    	gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
    	
    	expect(userDao.getByLoginName(loginName)).andReturn(_user).anyTimes();
    	expect(userProvisioningManager.getUser(loginName)).andReturn(csmUser).anyTimes();
    	replayMocks();
    	try{
    		_user = repository.getUserByLoginName(loginName);
    		fail("User repository should not have returned a User");
    	}catch(CaaersSystemException csEx){
    		/*good*/
    	}
    	verifyMocks();
    }
    
    public void testUnlockUser(){
    	User _user = new User();
    	_user.setLoginName("monishd");
    	_user.setFailedLoginAttempts(3);
    	_user.setLastFailedLoginAttemptTime(new Timestamp(new Date().getTime()));
    	userDao.save(_user);
    	replayMocks();
    	repository.unlockUserPassword(_user);
    	verifyMocks();
    	assertNull(_user.getLastFailedLoginAttemptTime());
    	assertEquals(0,_user.getFailedLoginAttempts());
    }


}
