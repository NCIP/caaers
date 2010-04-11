package gov.nih.nci.cabig.caaers.testdata;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @author: Biju Joseph
 */
public class TestDataFileUtils {

    /**
     * A file representing the test folder is returned
     * @return
     */
    public static File getTestDataFolder(){
        String userHome = System.getProperty("user.home");
        File f = new File(userHome + "/testdatafiles");
        return createFolder(f);
    }

    /**
     * The path of test folder is returned.
     * @return
     */
    public static String getTestDataFolderPath(){
       return getTestDataFolder().getPath();
    }

    /**
     * A file representing the test data folder of study
     * @return
     */
    public static File getStudyTestDataFolder(){
        File f = new File(getTestDataFolderPath() +"/study");
        return createFolder(f);
    }

    /**
     * A file representing the test data folder for subject
     * @return
     */
    public static File getSubjectTestDataFolder(){
        File f = new File(getTestDataFolderPath() +"/subject");
        return createFolder(f);
    }

     /**
     * A file representing the test data folder of investigator
     * @return
     */
    public static File getInvestigatorTestDataFolder(){
        File f = new File(getTestDataFolderPath() +"/investigator");
        return createFolder(f);
    }

    /**
     * A file representing the test data folder for research staff
     * @return
     */
    public static File getResearchStaffTestDataFolder(){
        File f = new File(getTestDataFolderPath() +"/researchstaff");
        return createFolder(f);
    }
     /**
     * A file representing the test data folder for adverse events
     * @return
     */
    public static File getAdverseEventTestDataFolder(){
        File f = new File(getTestDataFolderPath() +"/ae");
        return createFolder(f);
    }

    /**
     * Return the content of the file
     * @param f
     * @return
     */
    public static String getContent(File f) throws Exception{
        return FileUtils.readFileToString(f);
    }

    /**
     * Will delete the file
     * @param folder
     * @param fileName
     * @throws Exception
     */
    public static void deleteFile(File folder, String fileName) throws Exception {
        FileUtils.deleteQuietly(getFileObject(folder, fileName));
    }

    /**
     * Returns a file object representation of the fileName
     * @param folder
     * @param fileName
     * @return
     */
    public static File getFileObject(File folder, String fileName){
        return new File(folder.getPath() +"/" +fileName);
    }

    public static File createFolder(File f){
        if(!f.exists()) f.mkdir();
        if(!f.isDirectory()) throw new RuntimeException("The folder "  + f.getPath() +", unable to create");
        return f;
    }


    
}
