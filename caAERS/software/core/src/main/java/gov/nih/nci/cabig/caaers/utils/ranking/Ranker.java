package gov.nih.nci.cabig.caaers.utils.ranking;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Biju Joseph
 */
public class Ranker {

     private static final int WHOLE_MATCH = 100000;
     private static final int BEGINING_OF_SENTENCE = 50000;
     private static final int BEGINING_OF_WORD = 1000;
     private static final int PART_OF_SENTENCE = 500;
    
    
    /**
     * Will rank an object, based on the following rules :-
     *  1) If there is a full text match - Highest rank "WHOLE_MATCH" is given. 
     *  1) Starting of the sentence if match - 2nd Highest rank "BEGINING_OF_SENTENCE" is given.
     *  2) Begining of any word match, - 3rd Highest rank "BEGINING_OF_WORD" is given.
     *  3) Anywhere in the sentence match - The lowest rank PART_OF_SENTENCE is given.
     * 
     * @param obj
     * @return
     */
    public <T extends Object> RankedObject rank(T obj, String searchStr , Serializer<T> serializer){
        RankedObject<T> rankedObject = new RankedObject(obj);
        String str = serializer.serialize(obj);

        //whole sentence
        if(StringUtils.equalsIgnoreCase(str, searchStr)){
            rankedObject.addToRank(WHOLE_MATCH);
        }

        Pattern p = Pattern.compile(searchStr, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        if(m.find()){

            int start = m.start();
            if(start == 0){
                //begining of sentence
                rankedObject.addToRank(BEGINING_OF_SENTENCE);
            } else {
                int i = start - 1;
                int j = start - 2;
                char iChar = str.charAt(i);

                //begining of sentence.
                if((j == 0 && str.charAt(j) == '(') || (i == 0 && iChar == '(') ) rankedObject.addToRank(BEGINING_OF_SENTENCE);
                //begining of word
                if(iChar == ' ' || iChar == '(') rankedObject.addToRank(BEGINING_OF_WORD);
            }


            //part of sentence
            rankedObject.addToRank(PART_OF_SENTENCE);

            rankedObject.substractFromRank(start);

        }

        return rankedObject;
    }

    
    
}
