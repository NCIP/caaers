package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.utils.CaaersSerializerUtil;
import junit.framework.TestCase;

/**
 * 
 * @author Monish
 *
 */
public class CaaersSerializerUtilTest extends TestCase {
	
	private ObjectToSerialize objectToSerialize;
	
	@SuppressWarnings("null")
	public void testSerializeException(){
		try{
			Throwable eX = null;
			eX.getMessage();
		}catch(Exception e){
			objectToSerialize = new ObjectToSerialize();
			objectToSerialize.setException(e);
			String output = CaaersSerializerUtil.serialize(objectToSerialize);
			System.out.println(output);  
		}
	}
}
