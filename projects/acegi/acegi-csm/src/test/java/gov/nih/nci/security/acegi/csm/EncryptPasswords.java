package gov.nih.nci.security.acegi.csm;

import gov.nih.nci.security.util.StringEncrypter;

public class EncryptPasswords {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        StringEncrypter enc = new StringEncrypter();
        System.out.println(enc.encrypt("user_1"));
        System.out.println(enc.encrypt("participant_cd1"));
    }

}
