package gov.nih.nci.cabig.caaers.tools.hibernate;

import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Biju
 * Date: 5/12/13
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class BitAndSqlFunction extends StandardSQLFunction implements SQLFunction {
    private String dialect;
    public BitAndSqlFunction(String name, Type registeredType, String dialect) {
        super(name, registeredType);
        this.dialect = dialect;
    }

    @Override
    public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor sessionFactory) {
        if(dialect.equals("postgresql")) return String.valueOf(arguments.get(0)) + " & " + String.valueOf(arguments.get(1));
        return super.render(firstArgumentType, arguments, sessionFactory);
    }
}
