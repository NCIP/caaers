<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par"%>
<tags:noform>

    <par:parIdentifier  title="Participant Identifier ${index + 1}" enableDelete="${index > 0}"
			sectionClass="${type eq 1 ? 'system-section-row' : 'organization-section-row'}" 
			removeButtonAction="removeIdentifier" index="${index}"  identifier="${command.participant.identifiers[index]}"
			 style="display:none"  />
			
</tags:noform>

