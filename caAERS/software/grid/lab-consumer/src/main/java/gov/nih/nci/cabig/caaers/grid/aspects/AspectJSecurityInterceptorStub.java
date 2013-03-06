/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.grid.aspects;

import org.acegisecurity.intercept.method.aspectj.AspectJCallback;
import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.JoinPoint;

public class AspectJSecurityInterceptorStub extends AspectJSecurityInterceptor {
    @Override
    public Object invoke(JoinPoint jp, AspectJCallback advisorProceed) {
        // TODO Auto-generated method stub
        return null;
    }
}
