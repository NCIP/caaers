package gov.nih.nci.cabig.caaers.dao.query;

/**
 * Created with IntelliJ IDEA.
 * User: Janakiram_G
 * Date: 6/8/15
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class PasswordPolicyQuery  extends AbstractQuery {

    public PasswordPolicyQuery(){
        this("select distinct pp from PasswordPolicy pp");
    }
    public PasswordPolicyQuery(String queryString) {
        super(queryString);
    }
}
