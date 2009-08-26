package gov.nih.nci.cabig.caaers.domain.security;

import gov.nih.nci.cabig.caaers.security.SystemUserVoter;
import junit.framework.TestCase;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
/**
 * 
 * @author Biju Joseph
 *
 */
public class SystemUserVoterTest extends TestCase {
	
	SystemUserVoter voter;
	protected void setUp() throws Exception {
		super.setUp();
		voter = new SystemUserVoter();
	}

	public void testSupportsClass() {
		assertTrue(voter.supports(String.class));
	}

	@SuppressWarnings("serial")
	public void testSupportsConfigAttribute() {
		boolean supports = voter.supports(new ConfigAttribute(){

			public String getAttribute() {
				return null;
			}
			
		});
		assertTrue(supports);
	}

	public void testVote() {
		Authentication token = new UsernamePasswordAuthenticationToken("efg", "abcd", new GrantedAuthority[0]);
		int result = voter.vote(token, null,null);
		assertEquals(SystemUserVoter.ACCESS_ABSTAIN, result);
		token = new UsernamePasswordAuthenticationToken("SYSTEM", "ignoreme", new GrantedAuthority[0]);
		result = voter.vote(token, null, null);
		assertEquals(SystemUserVoter.ACCESS_GRANTED, result);
	}

}
