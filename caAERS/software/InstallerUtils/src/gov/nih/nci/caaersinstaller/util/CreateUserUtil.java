package gov.nih.nci.caaersinstaller.util;

import gov.nih.nci.security.util.StringEncrypter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class has main method.
 * This class is intented to to invoked by ant.
 * a user.properties file is generated as the outcome of the execution of this class.
 * 
 * @author Monish Dombla
 *
 */
public class CreateUserUtil {
	public static void main(String[] args) {
		
		Writer output = null;
		File userPropertiesFile = new File(args[2]+"user.properties");
		StringBuilder sb = new StringBuilder();
		String encryptedPassword;
		
		if("".equals(args[0])){
			args[0] = "ccts_user";
		}
		if("".equals(args[1])){
			args[1] = "ccts_user";
		}
		
		try {
			StringEncrypter stringEncrypter = new StringEncrypter();
			encryptedPassword = stringEncrypter.encrypt(args[1]);
			sb.append("user.username=").append(args[0]).append("\n");
			sb.append("user.password=").append(encryptedPassword);
			output = new BufferedWriter(new FileWriter(userPropertiesFile));
	        output.write( sb.toString() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(output != null){try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
		}
	}

}
