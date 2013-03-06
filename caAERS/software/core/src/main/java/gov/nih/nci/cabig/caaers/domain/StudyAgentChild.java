/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

 
/**
 * The Interface StudyAgentChild.
 */
public interface StudyAgentChild {
    
    /**
     * Sets the study agent.
     *
     * @param agent the new study agent
     */
    public void setStudyAgent(StudyAgent agent);

    /**
     * Gets the study agent.
     *
     * @return the study agent
     */
    public StudyAgent getStudyAgent();
}
