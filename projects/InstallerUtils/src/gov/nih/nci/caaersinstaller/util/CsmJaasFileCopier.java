package gov.nih.nci.caaersinstaller.util;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 * This is used to either replace caaers context in existing csm_jaas.config file or create a new csm_jass.config file
 * if one does not exist. 
 * 
 * @author Monish
 *
 */
public class CsmJaasFileCopier {
	
	public static void main(String args[]) throws Exception{
		
//		File csmJaasTemplateFile = new File("/Users/Moni/temp/installer/postgres.csm_jaas.config");
//		File csmJassConfigFile = new File("/Users/Moni/temp/installer/csm_jaas.config");
		
		File csmJaasTemplateFile = new File(args[0]);
		File csmJassConfigFile = new File(args[1]);
		
		if(csmJassConfigFile.exists()){
			//append content of csmJaasTemplateFile to existing csmJaasConfigFile
			String csmJaasTemplateFileContent = FileUtils.readFileToString(csmJaasTemplateFile);
			StringBuilder stringBuilder = new StringBuilder(FileUtils.readFileToString(csmJassConfigFile));
			
			int start = stringBuilder.indexOf("caaers {");
			if(start != -1){
				//If caaers context exisits then replace it.
				int end = stringBuilder.indexOf("};",start);
				end = end+2;
				stringBuilder.replace(start, end, csmJaasTemplateFileContent);
			}else{
				//if caaers context does not exist then add it 
				stringBuilder.append("\n");
				stringBuilder.append("\n");
				stringBuilder.append(csmJaasTemplateFileContent);
			}
			
			FileUtils.writeStringToFile(csmJassConfigFile, stringBuilder.toString());
			System.out.println("Modified csm_jaas.config to add caaers context");
			
		}else{
			//Create a new File with Contents of csmJaasTemplateFile
			FileUtils.copyFile(csmJaasTemplateFile,csmJassConfigFile);
			System.out.println("Created csm_jaas.config");
			
		}
	}
	
}
