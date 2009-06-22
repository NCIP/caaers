package gov.nih.nci.cabig.caaers.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 TODO: Everything that's represented here should be either an enum
 TODO: or a database LUT (or both)
 */
public class ConfigProperty {

    // Temporary lov values to hold the data pertaining to CADsr
    // TODO remove the lovMap once a CaDsr service is in place
    // TODO: remove it sooner than that
    private Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();

    public Map<String, List<Lov>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<Lov>> map) {
        this.map = map;
    }

}
