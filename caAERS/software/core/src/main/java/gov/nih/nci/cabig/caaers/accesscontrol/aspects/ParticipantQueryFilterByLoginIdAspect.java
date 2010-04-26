package gov.nih.nci.cabig.caaers.accesscontrol.aspects;



/**
 * This aspect is configured to invoke on ParticipantAjaxableDomainObjectRepository.findParticipants.
 * @author akkalas
 *
 */
public class ParticipantQueryFilterByLoginIdAspect extends AbstractFilterByLoginIdAspect {

	/*@Override
	public  String getInQuery() {
		return "participant.id IN (:ids)";
	}*/

	@Override
	public String getJoinQuery() {
		return null;
	}
}
