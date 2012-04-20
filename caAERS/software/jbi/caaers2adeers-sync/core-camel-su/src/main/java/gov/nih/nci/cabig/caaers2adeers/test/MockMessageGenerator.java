package gov.nih.nci.cabig.caaers2adeers.test;


public class MockMessageGenerator {


    public static String getAgentsRequest() {

        return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>agent</entity>" +
                "<operation mode=\"async\" name=\"getAgentsLOV\">" +
                "<criteria>" +
                "<criterion  name=\"createdDate\">04-10-2001 14:17:38</criterion>" +
                "<criterion name=\"lastUpdatedDate\">04-10-2001 14:17:38</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }

    public static String getStudySearchRequest() {

        return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>study</entity>" +
                "<operation mode=\"sync\" name=\"searchStudy\">" +
                "<criteria>" +
                "<criterion  name=\"documentTitle\">Adjuvant Chemotherapy</criterion>" +
                "<criterion  name=\"createdDate\">04-10-2001 14:17:38</criterion>" +
                "<criterion name=\"lastUpdatedDate\">04-10-2001 14:17:38</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }


    public static String getStudyDetails(String nciDocumentNumber) {

        return "<payload>" +
                "<system>adeers</system>" +
                "<request>" +
                "<entity>study</entity>" +
                "<operation mode=\"sync\" name=\"getStudyDetails\">" +
                "<criteria>" +
                "<criterion  name=\"nciDocumentNumber\">" +  nciDocumentNumber + "</criterion>" +
                "<criterion  name=\"createdDate\">04-10-2001 14:17:38</criterion>" +
                "<criterion name=\"lastUpdatedDate\">04-10-2001 14:17:38</criterion>" +
                "</criteria>" +
                "</operation>" +
                "</request>" +
                "</payload>";
    }
}
