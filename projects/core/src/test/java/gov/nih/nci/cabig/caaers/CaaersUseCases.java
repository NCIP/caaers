package gov.nih.nci.cabig.caaers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Rhett Sutphin
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CaaersUseCases {
    CaaersUseCase[] value();
}
