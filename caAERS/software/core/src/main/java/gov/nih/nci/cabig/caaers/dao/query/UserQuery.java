/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import java.util.List;

/**
 * @author: Biju Joseph
 */
public class UserQuery extends AbstractQuery{

    public UserQuery(){
        this("select distinct u from User u");
    }
    public UserQuery(String queryString) {
        super(queryString);
    }

    public void filterByLoginNames(List<String> loginNames){
        andWhere("u.loginName in( :loginNames )");
        setParameterList("loginNames", loginNames);
    }
}
