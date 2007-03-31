package gov.nih.nci.cabig.caaers.web.ae;

import org.apache.commons.collections.Transformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @author Rhett Sutphin
*/
public class RepeatingFieldGroupTransformer implements Transformer {
    private static final Log log = LogFactory.getLog(RepeatingFieldGroupTransformer.class);
    private static final Pattern CREATED_FIELD_GROUP_PATTERN
        = Pattern.compile("([a-zA-Z_-]+)(\\d+)");

    private Map<String, RepeatingFieldGroupFactory> factories
        = new HashMap<String, RepeatingFieldGroupFactory>();

    public Object transform(Object input) {
        if (log.isDebugEnabled()) log.debug("Creating new field group for " + input);

        String in = input.toString();
        Matcher match = CREATED_FIELD_GROUP_PATTERN.matcher(in);
        if (match.matches()) {
            String basename = match.group(1);
            RepeatingFieldGroupFactory factory = factories.get(basename);
            if (factory == null) {
                throw new IllegalArgumentException(String.format(
                    "No RepeatingFieldGroupFactory with basename '%s' for '%s'", basename, in));
            } else {
                Integer index = new Integer(match.group(2));
                return factory.createGroup(index);
            }
        } else {
            throw new IllegalArgumentException(String.format(
                "'%s' is not a valid name for a repeating field group", in));
        }
    }

    public void addFactory(RepeatingFieldGroupFactory factory) {
        factories.put(factory.getBasename(), factory);
    }

}
