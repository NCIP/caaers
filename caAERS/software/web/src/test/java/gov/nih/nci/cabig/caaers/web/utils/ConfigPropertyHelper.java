/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.utils;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;

/**
 * Helper class which provides ConfigPropery maps for testing purpose
 *
 * @author Biju Joseph
 */
public class ConfigPropertyHelper {

    public static ConfigProperty getConfigProperty() {
        ConfigProperty configProperty = new ConfigProperty();
        return configProperty;

    }

    public static ConfigProperty putParticipantIdentifiersType(final ConfigProperty configProperty) {

        configProperty.getMap().put("participantIdentifiersType", LovHelper.getParticipantIdentifiersType());
        return configProperty;

    }

    public static ConfigProperty putPhaseCodeRefData(final ConfigProperty configProperty) {

        configProperty.getMap().put("phaseCodeRefData", LovHelper.getPhaseCodeRefData());
        return configProperty;

    }

    public static ConfigProperty putStatusRefData(final ConfigProperty configProperty) {

        configProperty.getMap().put("statusRefData", LovHelper.getStatusRefData());
        return configProperty;

    }

}
