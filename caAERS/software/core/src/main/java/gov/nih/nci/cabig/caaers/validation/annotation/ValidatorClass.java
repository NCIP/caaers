/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Link between an constraint annotation and it's validator implementation
 * 
 * @author Saurabh Agrawal, Created on August,11th, 2007
 * 
 */
@Documented
@Target( { ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidatorClass {
    Class<? extends Validator> value();
}
