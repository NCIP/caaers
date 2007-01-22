package gov.nih.nci.cabig.caaers.domain;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public interface GridIdentifiable {

	/**
	 * @return the grid-scoped unique identifier for this object
	 */
	String getGridId();

	/**
	 * Specify the grid-scoped unique identifier for this object
	 * @param gridId
	 */
	void setGridId(String gridId);

}
