/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

/**
 * This class contains the objects to be serialized by CaaersSerializerUtil.
 * CaaersSerializerUtil uses a custom XStream converter ObjectToSerializeConverter to marshal contents of this class. 
 * @author Monish
 *
 */
public class ObjectToSerialize {

	private HttpServletRequest httpRequest;
	private HttpSession httpSession;
	private Session hibernateSession;
	private Throwable exception;
    private Object[] methodParameters;
	
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}
	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	public HttpSession getHttpSession() {
		return httpSession;
	}
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	public Session getHibernateSession() {
		return hibernateSession;
	}
	public void setHibernateSession(Session hibernateSession) {
		this.hibernateSession = hibernateSession;
	}
	public Throwable getException() {                   
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}

    public Object[] getMethodParameters() {
        return methodParameters;
    }

    public void setMethodParameters(Object[] methodParameters) {
        this.methodParameters = methodParameters;
    }
}
