/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

/**
 * This is a pure value object, to help evaluate the significance value by rules
 * @author Biju Joseph
 * @date 3/28/12
 */
public class ObservedAdverseEventSignificanceLevel {

    private double significance;

    public ObservedAdverseEventSignificanceLevel(double significance) {
        this.significance = significance;
    }

    public double getSignificance() {
        return significance;
    }

    public void setSignificance(double significance) {
        this.significance = significance;
    }
}
