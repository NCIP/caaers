/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;

import java.util.ArrayList;
import java.util.Set;

/**
 * Will retrieve RuleSet objects
 * @author Biju Joseph.
 */
public class RuleSetQuery extends AbstractQuery {

    public RuleSetQuery() {
        super("select r from RuleSet r");
    }

    public void joinOrganization(){
        join("r.organization as o");
    }
    public void joinStudy(){
        join("r.study as s");
    }
    public void filterByNCICode(String nciCode){
        joinOrganization();
        andWhere("o.nciInstituteCode = :nciCode");
        setParameter("nciCode", nciCode);
    }
    public void filterByOrganizationId(Integer orgId){
        joinOrganization();
        andWhere("o.id = :orgId");
        setParameter("orgId", orgId);
    }

    public void filterByOrganizationId(Set<Integer> organizationIds){
        joinOrganization();
        andWhere("o.id in(:oIds)");
        setParameterList("oIds", new ArrayList(organizationIds));
    }
    public void filterByStudyId(Integer studyId){
        joinStudy();
        andWhere("s.id = :sId");
        setParameter("sId", studyId);
    }

    public void filterByRuleType(RuleType t){
        andWhere("r.ruleTypeName = :rType");
        setParameter("rType", t.getName());
    }

    public void filterByRuleLevel(RuleLevel l){
        andWhere("r.ruleLevelName = :l");
        setParameter("l", l.getName());
    }

    public void filterByStatus(String status){
        andWhere("r.status = :st");
        setParameter("st", status);
    }


    public void filterByRuleBindURI(String uri){
        andWhere("r.ruleBindURI = :uri");
        setParameter("uri", uri);
    }
    
    public void ignoreRuleSetId(Integer id){
        andWhere("r.id <> :rid");
        setParameter("rid", id);
    }


}
