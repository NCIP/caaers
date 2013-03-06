/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * Will provide the password for AdEERSWebservice and caAERS Webservice
 * @author Biju Joseph
 */
public class CaaersWSPasswordCallback implements CallbackHandler{
    private String caaersWSUser;
    private String caaersWSPassword;
    private String adeersWSUser;
    private String adeersWSPassword;
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        org.apache.ws.security.WSPasswordCallback pc = (org.apache.ws.security.WSPasswordCallback) callbacks[0];
        if(equals(pc.getIdentifier(), caaersWSUser)) pc.setPassword(caaersWSPassword);
    }
    
    private boolean equals(String a, String b){
        if(a == null || b == null) return false;
        return a.equals(b);
    }

    public String getCaaersWSUser() {
        return caaersWSUser;
    }

    public void setCaaersWSUser(String caaersWSUser) {
        this.caaersWSUser = caaersWSUser;
    }

    public String getCaaersWSPassword() {
        return caaersWSPassword;
    }

    public void setCaaersWSPassword(String caaersWSPassword) {
        this.caaersWSPassword = caaersWSPassword;
    }

    public String getAdeersWSUser() {
        return adeersWSUser;
    }

    public void setAdeersWSUser(String adeersWSUser) {
        this.adeersWSUser = adeersWSUser;
    }

    public String getAdeersWSPassword() {
        return adeersWSPassword;
    }

    public void setAdeersWSPassword(String adeersWSPassword) {
        this.adeersWSPassword = adeersWSPassword;
    }
}
