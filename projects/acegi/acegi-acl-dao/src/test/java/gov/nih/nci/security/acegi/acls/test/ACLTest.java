package gov.nih.nci.security.acegi.acls.test;

import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.acls.AccessControlEntry;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.MutableAclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.Permission;
import org.acegisecurity.acls.domain.BasePermission;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.objectidentity.ObjectIdentityImpl;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.acls.sid.Sid;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class ACLTest extends AbstractTransactionalDataSourceSpringContextTests {

    
    
    
    public ACLTest() {
        init();
    }

    public ACLTest(String name) {
        super(name);
        init();
    }

    public void init() {
        
        
        ApplicationContext appCtx = new ClassPathXmlApplicationContext(getConfigLocations());
        HibernateTransactionManager txMgr = (HibernateTransactionManager) appCtx
                        .getBean("transactionManager");

        Session session = SessionFactoryUtils.getSession(txMgr.getSessionFactory(), true);

        if (session != null) {
            session.clear();
        }

        LocalSessionFactoryBean sessionFactoryBean = (LocalSessionFactoryBean) appCtx
                        .getBean("&sessionFactory");

        sessionFactoryBean.createDatabaseSchema();
        
    }
    

    protected String[] getConfigLocations() {
        return new String[] { "classpath:/applicationContext-acls-dao-test.xml" };
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new ACLTest("testLifecycle"));
        return suite;
    }

    public void testLifecycle() {
        
        

        MutableAclService aclService = (MutableAclService) applicationContext
                        .getBean("mutableAclDao");

        Authentication auth = new TestingAuthenticationToken("joshua", "ignored",
                        new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_ADMINISTRATOR") });
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        ObjectIdentity topParentOid = new ObjectIdentityImpl(
                        "gov.nih.nci.security.acegi.acls.test.SomeBean", new Long(100));
        ObjectIdentity middleParentOid = new ObjectIdentityImpl(
                        "gov.nih.nci.security.acegi.acls.test.SomeBean", new Long(101));
        ObjectIdentity childOid = new ObjectIdentityImpl(
                        "gov.nih.nci.security.acegi.acls.test.SomeBean", new Long(102));

        assertNotNull("No ACLService", aclService);
        MutableAcl topParent = aclService.createAcl(topParentOid);
        MutableAcl middleParent = aclService.createAcl(middleParentOid);
        MutableAcl child = aclService.createAcl(childOid);

        // Specify the inheritence hierarchy
        middleParent.setParent(topParent);
        child.setParent(middleParent);

        // Now let's add a couple of permissions
        topParent.insertAce(null, BasePermission.READ, new PrincipalSid(auth), true);
        topParent.insertAce(null, BasePermission.WRITE, new PrincipalSid(auth), false);
        middleParent.insertAce(null, BasePermission.DELETE, new PrincipalSid(auth), true);
        child.insertAce(null, BasePermission.DELETE, new PrincipalSid(auth), false);

        // Explictly save the changed ACL
        aclService.updateAcl(topParent);
        aclService.updateAcl(middleParent);
        aclService.updateAcl(child);

        // Let's check if we can read them back correctly
        Map map = aclService.readAclsById(new ObjectIdentity[] { topParentOid, middleParentOid,
                childOid });
        assertEquals(3, map.size());

        // Replace our current objects with their retrieved versions
        topParent = (MutableAcl) map.get(topParentOid);
        middleParent = (MutableAcl) map.get(middleParentOid);
        child = (MutableAcl) map.get(childOid);

        // Check the retrieved versions has IDs
        assertNotNull(topParent.getId());
        assertNotNull(middleParent.getId());
        assertNotNull(child.getId());

        // Check their parents were correctly persisted
        assertNull(topParent.getParentAcl());
        assertEquals(topParentOid, middleParent.getParentAcl().getObjectIdentity());
        assertEquals(middleParentOid, child.getParentAcl().getObjectIdentity());

        // Check their ACEs were correctly persisted
        assertEquals(2, topParent.getEntries().length);
        assertEquals(1, middleParent.getEntries().length);
        assertEquals(1, child.getEntries().length);

        // Check the retrieved rights are correct
        assertTrue(topParent.isGranted(new Permission[] { BasePermission.READ },
                        new Sid[] { new PrincipalSid(auth) }, false));
        assertFalse(topParent.isGranted(new Permission[] { BasePermission.WRITE },
                        new Sid[] { new PrincipalSid(auth) }, false));
        assertTrue(middleParent.isGranted(new Permission[] { BasePermission.DELETE },
                        new Sid[] { new PrincipalSid(auth) }, false));
        assertFalse(child.isGranted(new Permission[] { BasePermission.DELETE },
                        new Sid[] { new PrincipalSid(auth) }, false));

        try {
            child.isGranted(new Permission[] { BasePermission.ADMINISTRATION },
                            new Sid[] { new PrincipalSid(auth) }, false);
            fail("Should have thrown NotFoundException");
        } catch (NotFoundException expected) {
            assertTrue(true);
        }

        // Now check the inherited rights (when not explicitly overridden) also
        // look OK
        assertTrue(child.isGranted(new Permission[] { BasePermission.READ },
                        new Sid[] { new PrincipalSid(auth) }, false));
        assertFalse(child.isGranted(new Permission[] { BasePermission.WRITE },
                        new Sid[] { new PrincipalSid(auth) }, false));
        assertFalse(child.isGranted(new Permission[] { BasePermission.DELETE },
                        new Sid[] { new PrincipalSid(auth) }, false));

        // Next change the child so it doesn't inherit permissions from above
        child.setEntriesInheriting(false);
        aclService.updateAcl(child);

        child = (MutableAcl) aclService.readAclById(childOid);

        assertFalse(child.isEntriesInheriting());

        // Check the child permissions no longer inherit
        assertFalse(child.isGranted(new Permission[] { BasePermission.DELETE },
                        new Sid[] { new PrincipalSid(auth) }, true));

        try {
            child.isGranted(new Permission[] { BasePermission.READ }, new Sid[] { new PrincipalSid(
                            auth) }, true);
            fail("Should have thrown NotFoundException");
        } catch (NotFoundException expected) {
            assertTrue(true);
        }

        try {
            child.isGranted(new Permission[] { BasePermission.WRITE },
                            new Sid[] { new PrincipalSid(auth) }, true);
            fail("Should have thrown NotFoundException");
        } catch (NotFoundException expected) {
            assertTrue(true);
        }

        // Let's add an identical permission to the child, but it'll appear
        // AFTER the current permission, so has no impact
        child.insertAce(null, BasePermission.DELETE, new PrincipalSid(auth), true);

        // Let's also add another permission to the child
        child.insertAce(null, BasePermission.CREATE, new PrincipalSid(auth), true);

        // Save the changed child
        aclService.updateAcl(child);
        child = (MutableAcl) aclService.readAclById(childOid);
        assertEquals(3, child.getEntries().length);

        // Output permissions
        for (int i = 0; i < child.getEntries().length; i++) {
            System.out.println(child.getEntries()[i]);
        }

        // Check the permissions are as they should be
        assertFalse(child.isGranted(new Permission[] { BasePermission.DELETE },
                        new Sid[] { new PrincipalSid(auth) }, true)); // as earlier
        // permission
        // overrode
        assertTrue(child.isGranted(new Permission[] { BasePermission.CREATE },
                        new Sid[] { new PrincipalSid(auth) }, true));

        // Now check the first ACE (index 0) really is DELETE for our Sid and is
        // non-granting
        AccessControlEntry entry = child.getEntries()[0];
        assertEquals(BasePermission.DELETE.getMask(), entry.getPermission().getMask());
        assertEquals(new PrincipalSid(auth), entry.getSid());
        assertFalse(entry.isGranting());
        assertNotNull(entry.getId());

        // Now delete that first ACE
        child.deleteAce(entry.getId());

        // Save and check it worked
        child = aclService.updateAcl(child);
        assertEquals(2, child.getEntries().length);
        assertTrue(child.isGranted(new Permission[] { BasePermission.DELETE },
                        new Sid[] { new PrincipalSid(auth) }, false));

        SecurityContextHolder.clearContext();

    }

}
