package gov.nih.nci.cabig.caaers.api;

public class SearchOptions {
	
	private boolean ignoreCase;
	private boolean enableLike;
	//private MatchMode enableLikeWithMatchMode;
	
	public boolean isEnableLike() {
		return enableLike;
	}
	public void setEnableLike(boolean enableLike) {
		this.enableLike = enableLike;
	}
	
	public boolean isIgnoreCase() {
		return ignoreCase;
	}
	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}
	
	
}
