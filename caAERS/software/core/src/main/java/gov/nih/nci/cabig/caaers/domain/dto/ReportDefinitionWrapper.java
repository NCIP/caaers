package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

/**
 * This class is a wrapper, to keep the ReportDefintion.
 * 
 * @author Biju Joseph
 *
 */
public class ReportDefinitionWrapper {
	public static enum ActionType{
		AMEND, WITHDRAW, EDIT, CREATE;
		@Override
		public String toString() {
			return name().charAt(0) + name().toLowerCase().substring(1);
		}
	}
	
	private ReportDefinition def;
	private String status;
	private ReportDefinition substitute;
	private ActionType action;
	private Date dueOn;
	private Date submittedOn;
	
	public ReportDefinitionWrapper(ReportDefinition def, ReportDefinition substitute, ActionType action) {
		this.def = def;
		this.substitute = substitute;
		this.action = action;
	}
	
	public String getReadableMessage(){
		StringBuilder sb= new StringBuilder();
		
		if(action == ActionType.AMEND){
			sb.append("Amend " ).append(def.getLabel());
			if(substitute != null){
				sb.append(" with ").append(substitute.getLabel());
			}
		}else if(action == ActionType.WITHDRAW){
			sb.append("Withdraw " ).append(def.getLabel());
			if(substitute != null){
				sb.append(" and replace with ").append(substitute.getLabel());
			}
		}else if(action == ActionType.EDIT){
			sb.append("Edit " ).append(def.getLabel());
		}else if(action == ActionType.CREATE){
			sb.append("Create " ).append(def.getLabel());
		}
		
		return sb.toString();
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public ActionType getAction() {
		return action;
	}
	
	@Override
	public boolean equals(Object obj) {
		return def.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return def.hashCode();
	}
	
	public ReportDefinition getSubstitute() {
		return substitute;
	}
	public ReportDefinition getDef() {
		return def;
	}
	
	public void setDueOn(Date dueOn) {
		this.dueOn = dueOn;
	}
	public Date getDueOn() {
		return dueOn;
	}
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	public Date getSubmittedOn() {
		return submittedOn;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("ReportDefintionWrapper [");
		sb.append(def.getName());
		if(substitute != null){
			sb.append(" => ").append(substitute.getName());
		}
		sb.append(", ").append(String.valueOf(action));
		sb.append("]");
		return sb.toString();
	}
	
}

