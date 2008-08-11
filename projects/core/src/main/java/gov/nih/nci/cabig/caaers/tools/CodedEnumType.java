package gov.nih.nci.cabig.caaers.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CodedEnumType extends gov.nih.nci.cabig.ctms.tools.hibernate.CodedEnumType{
	 @Override
	protected int codeSqlType() {
		return Types.INTEGER;
	}

	@Override
	protected Class<Integer> codeJavaType() {
		return Integer.TYPE;
	}

	@Override
	protected Object getKeyObject(ResultSet rs, String colname)
			throws SQLException {
		return rs.getObject(colname);
	}
}
