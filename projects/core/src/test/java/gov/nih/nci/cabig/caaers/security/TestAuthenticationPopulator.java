package gov.nih.nci.cabig.caaers.security;

import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestAuthenticationPopulator {

    private static final Log logger = LogFactory
                    .getLog(gov.nih.nci.cabig.caaers.security.TestAuthenticationPopulator.class);

    private String username;

    private String password;

    private String includePattern = ".*";

    private String excludePattern;

    private List<String> authorities;

    public void populate() {

        logger.debug("################# Populating Authentication #################");

        List<String> l = getAuthorities();
        GrantedAuthority[] auths = new GrantedAuthority[l.size()];
        int idx = 0;
        for (String auth : l) {
            auths[idx++] = new GrantedAuthorityImpl(auth);
        }
        Authentication auth = new TestingAuthenticationToken(getUsername(), getPassword(), auths);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExcludePattern() {
        return excludePattern;
    }

    public void setExcludePattern(String excludePattern) {
        this.excludePattern = excludePattern;
    }

    public String getIncludePattern() {
        return includePattern;
    }

    public void setIncludePattern(String includedPattern) {
        this.includePattern = includedPattern;
    }

}
