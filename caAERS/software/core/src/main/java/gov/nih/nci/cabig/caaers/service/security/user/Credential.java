/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.security.user;

import gov.nih.nci.cabig.caaers.domain.User;
import org.acegisecurity.userdetails.UserDetails;

public class Credential {
    private String _userName;

    private String _password;
    
    private User user;
    private UserDetails userDetails;

    public Credential(String userName, String password) {
        _userName = userName;
        _password = password;
    }

    public String getPassword() {
        return _password;
    }

    public String getUserName() {
        return _userName;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
