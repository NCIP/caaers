package gov.nih.nci.cabig.caaers.web.fields;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public class RepeatingFieldGroupTransformer implements Transformer<String, InputFieldGroup> {
    private static final Log log = LogFactory.getLog(RepeatingFieldGroupTransformer.class);

    private static final Pattern CREATED_FIELD_GROUP_PATTERN = Pattern
                    .compile("([a-zA-Z_-]+)(\\d+)");

    private Map<String, RepeatingFieldGroupFactory> factories = new HashMap<String, RepeatingFieldGroupFactory>();

    public InputFieldGroup transform(String in) {
        if (log.isDebugEnabled()) log.debug("Creating new field group for " + in);

        Matcher match = CREATED_FIELD_GROUP_PATTERN.matcher(in);
        if (match.matches()) {
            String basename = match.group(1);
            RepeatingFieldGroupFactory factory = factories.get(basename);
            if (factory == null) {
                log.warn(String.format("No RepeatingFieldGroupFactory with basename '%s' for '%s'",
                                basename, in));
                return null;
            } else {
                Integer index = new Integer(match.group(2));
                return factory.createGroup(index);
            }
        } else {
            log.warn(String.format("'%s' is not a valid name for a repeating field group", in));
            return null;
        }
    }

    public void addFactory(RepeatingFieldGroupFactory factory) {
        factories.put(factory.getBasename(), factory);
    }

}
