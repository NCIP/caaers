package gov.nih.nci.cabig.caaers.domain.dto;

public class ReviewCommentsDTO {
	
	private String comment;
	
	public ReviewCommentsDTO(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
