package gov.nih.nci.cabig.caaers.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The purpose of this class is to intercept calls to JUnit test methods which would fail unless an
 * appropriate Authentication object existed in the SecurityContext.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
@Aspect
public class TestAuthenticationAspect {

    private ApplicationContext ctx;

    public TestAuthenticationAspect() {
        try {
            this.ctx = new ClassPathXmlApplicationContext(
                            new String[] { "classpath:gov/nih/nci/cabig/caaers/applicationContext-test-security.xml" });
        } catch (Exception ex) {
            throw new RuntimeException("Error getting ApplicationContext: " + ex.getMessage(), ex);
        }
    }

    @Before("(execution(public void junit.framework.TestCase+.test*()) || "
                    + "execution(public static * gov.nih.nci.cabig.caaers.domain.Fixtures.*(..)) ) && "
                    + "!within(gov.nih.nci.cabig.caaers.security..*)")
    public void advise() {
        TestAuthenticationPopulator p = (TestAuthenticationPopulator) this.ctx
                        .getBean("testAuthenticationPopulator");
        p.populate();
    }

}
