/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils.ranking;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Biju Joseph
 */
public class Ranker {

     private static final int WHOLE_SENTENCE_MATCH = 100000;
     private static final int BEGINING_OF_SENTENCE = 50000;
     private static final int WHOLE_WORD_MATCH = 10000;
     private static final int BEGINING_OF_WORD = 1000;
     private static final int PART_OF_SENTENCE = 500;


    private Pattern p;
    private String searchStr;
    private String escapedSearchStr;
    private int patternLength;
    
    public Ranker(String searchStr){
        this.searchStr = searchStr;
        patternLength = searchStr.length();
        escapedSearchStr = searchStr;
        //11 characters with special meanings: the opening square bracket [, the backslash \, the caret ^, the dollar sign $, the period or dot ., the vertical bar
        // or pipe symbol |, the question mark ?, the asterisk or star *, the plus sign +, the opening round bracket ( and the closing round bracket ).
        //  These special characters are often called "metacharacters".
        char[] metachars = new char[]{'\\','(',')','[',']','^','$','.','?','+','*','|'};
        if(StringUtils.containsAny(searchStr, metachars)){
           String[] metaStr = {"\\","(",")","[","]","^","$",".","?","+","*","|"};
           String[] metaEscapedStr = {"\\\\","\\(","\\)","\\[","\\]","\\^","\\$","\\.","\\?","\\+","\\*","\\|"};
           escapedSearchStr = StringUtils.replaceEach(searchStr, metaStr, metaEscapedStr);
        }
        p = Pattern.compile(escapedSearchStr, Pattern.CASE_INSENSITIVE);
    }
    
    /**
     * Will rank an object, based on the following rules :-
     *  1) If there is a full text match - Highest rank "WHOLE_SENTENCE_MATCH" is given.
     *  1) Starting of the sentence if match - 2nd Highest rank "BEGINING_OF_SENTENCE" is given.
     *  2) Begining of any word match, - 3rd Highest rank "BEGINING_OF_WORD" is given.
     *  3) Anywhere in the sentence match - The lowest rank PART_OF_SENTENCE is given.
     * 
     * @param obj
     * @return
     */
    public <T extends Object> RankedObject rank(T obj, Serializer<T> serializer){
        RankedObject<T> rankedObject = new RankedObject(obj);
        String str = serializer.serialize(obj);
        int l = str.length();

        //whole sentence
        if(StringUtils.equalsIgnoreCase(str, searchStr)){
            rankedObject.addToRank(WHOLE_SENTENCE_MATCH);
        }

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

                if(iChar == ' ' || iChar == '('){

                    int k = start + patternLength;
                    if(k == l ||  ( k < l && (str.charAt(k) == ' ' || str.charAt(k) == ')') )){
                       //whole word match
                        rankedObject.addToRank(WHOLE_WORD_MATCH);
                    }

                    //begining of word
                    rankedObject.addToRank(BEGINING_OF_WORD);
                }
            }


            //part of sentence
            rankedObject.addToRank(PART_OF_SENTENCE);

            rankedObject.substractFromRank(start);

        }

        return rankedObject;
    }

    
    
}
