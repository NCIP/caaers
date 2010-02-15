package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.resolver.RemoteStudyResolver;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.semanticbits.coppa.domain.annotations.RemoteEntity;
import com.semanticbits.coppa.domain.annotations.RemoteProperty;
import com.semanticbits.coppa.domain.annotations.RemoteUniqueId;

@Entity
@RemoteEntity(entityResolver=RemoteStudyResolver.class)
@DiscriminatorValue("REMOTE")
public class RemoteStudy extends Study{
	
	private static final long serialVersionUID = 1L;

	@RemoteUniqueId
    @RemoteProperty
    public String getExternalId() {
		return externalId;
	}
	
	@RemoteProperty
    public String getShortTitle() {
        return shortTitle;
    }
	
	@RemoteProperty
    public String getLongTitle() {
        return longTitle;
    }
	
	@RemoteProperty
    public String getPhaseCode() {
        return phaseCode;
    }
	
	@RemoteProperty
    public String getDescription() {
        return description;
    }

	@RemoteProperty
    public String getStatus() {
        return status;
    }
	
	private List<InvestigationalNewDrug> investigationalNewDrugList = new ArrayList<InvestigationalNewDrug>();

	@Transient
	public List<InvestigationalNewDrug> getInvestigationalNewDrugList() {
		return investigationalNewDrugList;
	}

	public void setInvestigationalNewDrugList(
			List<InvestigationalNewDrug> investigationalNewDrugList) {
		this.investigationalNewDrugList = investigationalNewDrugList;
	}
	
	public void addInvestigationalNewDrug(InvestigationalNewDrug investigationalNewDrug){
		getInvestigationalNewDrugList().add(investigationalNewDrug);
	}
}
