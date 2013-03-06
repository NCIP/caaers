/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

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
	protected Object getKeyObject(ResultSet rs, String colname) throws SQLException {
        String s = rs.getString(colname);
        if (s == null) return null;
        else {
            if (NumberUtils.isNumber(s)) return Integer.parseInt(s);
            return s;
        }
    }
}
