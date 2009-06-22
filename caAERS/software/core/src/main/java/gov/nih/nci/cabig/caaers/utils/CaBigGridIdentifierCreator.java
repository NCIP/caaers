package gov.nih.nci.cabig.caaers.utils;

/**
 * Class to fetch Grid Identifiers from CaBig grid Identifier Generator.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class CaBigGridIdentifierCreator implements GridIdentifierCreator {
    public String getGridIdentifier(String identification) {
        throw new UnsupportedOperationException(
                        "This class is currently unused.  Uncomment body and resolve dependencies later.");
    }

    //
    // private static final Log log = LogFactory.getLog(CaBigGridIdentifierCreator.class);
    //
    // private String handleRepositoryDir = "cabig";
    //
    // private String context = "urn://cabig";
    //
    // public CaBigGridIdentifierCreator(){
    // //setHandleRepositoryDir("cabig");
    // }
    //
    // /**
    // * Updates the grid identifier of the domain Object (given the unique id)
    // * @param identifier Unique Id of the object
    // * Typical implementation is (Class+ primary key)
    // * @return the Grid Identifier
    // */
    // public final String getGridIdentifier(String identifier)
    // {
    // IDSvcInterface idInterface = null;
    // URI gridId = null;
    // try {
    // idInterface = IDSvcInterfaceFactory.getInterface(handleRepositoryDir);
    // } catch (BadConfigurationFileException e) {
    // log.debug("Unable to find/load config.dct file. Pl update property file " +
    // "grid-security.properties");
    // throw new CaaersSystemException(e.getMessage(), e);
    // } catch (InvalidSubclassException e) {
    // log.debug("caught in InvalidSubclassException");
    // throw new CaaersSystemException(e.getMessage(), e);
    // }
    //
    // try {
    // ResourceIdInfo rid = new ResourceIdInfo(new URI(context), identifier);
    // gridId = idInterface.createOrGetGlobalID(rid);
    // } catch (URISyntaxException e) {
    // log.debug("caught in URISyntaxException");
    // throw new CaaersSystemException(e.getMessage(), e);
    // }
    //
    // return gridId.toString();
    // }
    //
    //
    // public String getHandleRepositoryDir() {
    // return handleRepositoryDir;
    // }
    //
    // public void setHandleRepositoryDir(String handleRepositoryDir) {
    // this.handleRepositoryDir = handleRepositoryDir;
    // File repositoryDir = new File(handleRepositoryDir);
    // if(!repositoryDir.exists()) {
    // createHandleRepositoryDir(handleRepositoryDir);
    // }
    // }
    //
    // private void createHandleRepositoryDir(String handleRepositoryDir) {
    // File repositoryDir = new File(handleRepositoryDir);
    // repositoryDir.mkdir();
    // copyFile("config.dct", handleRepositoryDir + File.separator +"config.dct");
    // }
    //
    // /**
    // * Creates a folder and copies the config.dct file to that folder so that the
    // * folder can be given as an input to the CaBig big id creator code.
    // * This code will fail if the application server is running with write permissions set to
    // false.
    // *
    // * TODO : Id the big id creator code is not going to change, we will have to read this from a
    // location
    // * specified in a property file.
    // * */
    // private void copyFile(String fromFileName, String toFileName) {
    // File toFile = new File(toFileName);
    //
    // InputStream from = CaBigGridIdentifierCreator.class.getResourceAsStream(fromFileName);
    // FileOutputStream to = null;
    // try {
    // to = new FileOutputStream(toFile);
    // byte[] buffer = new byte[4096];
    // int bytesRead;
    //
    // while ((bytesRead = from.read(buffer)) != -1)
    // to.write(buffer, 0, bytesRead); // write
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } finally {
    // if (from != null)
    // try {
    // from.close();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // if (to != null)
    // try {
    // to.close();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
    // }
    //
    // public String getContext() {
    // return context;
    // }
    //
    // public void setContext(String context) {
    // this.context = context;
    // }
    //
    //
}