package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.semanticbits.rules.objectgraph.FactResolver;
import com.semanticbits.rules.objectgraph.NavigationPath;
import com.semanticbits.rules.objectgraph.Node;
import com.semanticbits.rules.objectgraph.ObjectGraphFactory;

public class ObjectGraphTest extends TestCase {

    private ObjectGraphFactory objectGraphFactory;

    protected void setUp() throws Exception {
        objectGraphFactory = ObjectGraphFactory.getInstance();
    }

    public void atestLoadObjectGraph() throws Exception {
        NavigationPath path = objectGraphFactory.findNavigationPath(
                        "gov.nih.nci.cabig.caaers.domain.Study",
                        "gov.nih.nci.cabig.caaers.domain.TreatmentAssignment");

        for (Node node : path.getNode()) {
            System.out.println(node.getObjectType());
        }

    }

    public void testFactResolver_1() throws Exception {

        TreatmentAssignment ta = new TreatmentAssignment();
        ta.setCode("456");

        Study study = new LocalStudy();
        study.addTreatmentAssignment(ta);

        FactResolver factResolver = new FactResolver();

        boolean result = factResolver
                        .assertFact(study, "gov.nih.nci.cabig.caaers.domain.TreatmentAssignment",
                                        "code", "45g6", "==");
        System.out.println(result);

    }

    public void atestFactResolver_11() throws Exception {
        AdverseEvent ae = new AdverseEvent();
        ae.setGrade(Grade.DEATH);

        FactResolver factResolver = new FactResolver();

        boolean result = factResolver.assertFact(ae, "gov.nih.nci.cabig.caaers.domain.Grade",
                        "code", "5", "eq");
        System.out.println(result);

    }

    public void atestFactResolver_1MM1() throws Exception {
        Study study = new LocalStudy();
        study.setShortTitle("cgems");

        StudyAgent sa1 = new StudyAgent();
        sa1.setAgentAsString("agent1");

        InvestigationalNewDrug investigationalNewDrug = new InvestigationalNewDrug();
        investigationalNewDrug.setHolderName("NCI");

        StudyAgentINDAssociation studyAgentINDAssociation = new StudyAgentINDAssociation();
        studyAgentINDAssociation.setInvestigationalNewDrug(investigationalNewDrug);

        List<StudyAgentINDAssociation> studyAgentINDAssociations = new ArrayList<StudyAgentINDAssociation>();
        studyAgentINDAssociations.add(studyAgentINDAssociation);

        sa1.setStudyAgentINDAssociations(studyAgentINDAssociations);

        List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
        studyAgents.add(sa1);
        /*
         * StudyAgent sa2 = new StudyAgent(); sa2.setAgentAsString("agent2");
         * 
         * 
         * 
         * studyAgents.add(sa2);
         */
        study.setStudyAgents(studyAgents);

        FactResolver factResolver = new FactResolver();

        // boolean result = factResolver.assertFact(study,
        // "gov.nih.nci.cabig.caaers.domain.StudyAgent",
        // "agentAsString", "agent2");
        boolean result = factResolver.assertFact(study,
                        "gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug", "holderName",
                        "NCI", "eq");
        System.out.println(result);
    }
}
