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
	
	/**
	 * The Enum ActionType.
	 */
	public static enum ActionType{
		
		/** The AMEND. */
		AMEND, 
 /** The WITHDRAW. */
 WITHDRAW, 
 /** The EDIT. */
 EDIT, 
 /** The CREATE. */
 CREATE;
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return name().charAt(0) + name().toLowerCase().substring(1);
		}

        public String getDisplayName(){
            return  toString();
        }
	}
	
	/** The def. */
	private ReportDefinition def;
	
	/** The status. */
	private String status;
	
	/** The substitute. */
	private ReportDefinition substitute;
	
	/** The action. */
	private ActionType action;
	
	/** The due on. */
	private Date dueOn;
	
	/** The submitted on. */
	private Date submittedOn;
	
	/**
	 * Instantiates a new report definition wrapper.
	 *
	 * @param def the def
	 * @param substitute the substitute
	 * @param action the action
	 */
	public ReportDefinitionWrapper(ReportDefinition def, ReportDefinition substitute, ActionType action) {
		this.def = def;
		this.substitute = substitute;
		this.action = action;
	}
	
	/**
	 * Gets the readable message.
	 *
	 * @return the readable message
	 */
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
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public ActionType getAction() {
		return action;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return def.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return def.hashCode();
	}
	
	/**
	 * Gets the substitute.
	 *
	 * @return the substitute
	 */
	public ReportDefinition getSubstitute() {
		return substitute;
	}
	
	/**
	 * Gets the def.
	 *
	 * @return the def
	 */
	public ReportDefinition getDef() {
		return def;
	}
	
	/**
	 * Sets the due on.
	 *
	 * @param dueOn the new due on
	 */
	public void setDueOn(Date dueOn) {
		this.dueOn = dueOn;
	}
	
	/**
	 * Gets the due on.
	 *
	 * @return the due on
	 */
	public Date getDueOn() {
		return dueOn;
	}
	
	/**
	 * Sets the submitted on.
	 *
	 * @param submittedOn the new submitted on
	 */
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	
	/**
	 * Gets the submitted on.
	 *
	 * @return the submitted on
	 */
	public Date getSubmittedOn() {
		return submittedOn;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

