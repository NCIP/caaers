package gov.nih.nci.cabig.caaers;

/**
 * @author Krikor Krumlian
 */
public abstract class JdbcDaoTestCase<D> extends CaaersDbTestCase {
    protected D getDao() {
        return (D) getApplicationContext().getBean(getDaoBeanName());
    }
    
    
	@Override
	public String[] getConfigLocations() {
		// TODO Auto-generated method stub
		  return new String[] {
		            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml",
		            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-service.xml",
		            "classpath*:applicationContext-test.xml"
		        };
	}
	
    /**
     * Defaults to the name of the class, less "Test", first letter in lowercase.
     */
    protected String getDaoBeanName() {
        StringBuilder name = new StringBuilder(getClass().getSimpleName());
        name.setLength(name.length() - 4); // trim off "Test"
        name.setCharAt(0, Character.toLowerCase(name.charAt(0)));
        return name.toString();
    }
}
