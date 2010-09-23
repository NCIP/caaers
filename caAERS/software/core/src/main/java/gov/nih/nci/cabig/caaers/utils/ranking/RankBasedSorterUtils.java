package gov.nih.nci.cabig.caaers.utils.ranking;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class RankBasedSorterUtils {
    private static final Log log = LogFactory.getLog(RankBasedSorterUtils.class);
    /**
     * Will sort the input list based on the rank.
     * 
     * @param orginalList - The list to sort
     * @param searchString - The search string, on which the sorting to be applied.
     * @param seralizer  - The serializer that knows how to get a string version of the object being sorted
     * @return  The rank based sorted list is returned. 
     */
    public static <T extends Object> List<T> sort(List<T> orginalList, String searchString , Serializer<T> seralizer){
        try{
            if(CollectionUtils.isEmpty(orginalList)) return orginalList;

            List<RankedObject<T>> rankedList = new ArrayList(orginalList.size());
            Ranker ranker = new Ranker(searchString);
            RankSorter rankSorter = new RankSorter();

            for(T o : orginalList){
                RankedObject<T> rankedObject =  ranker.rank(o , seralizer);
                rankedList.add(rankedObject);
            }
            rankSorter.sort(rankedList);
            return toList(rankedList);
        }catch(Exception e){
            log.warn("unable to compile the pattern", e);
            return orginalList;
        }
    }


    private static <T extends Object> List<T> toList(List<RankedObject<T>> tList){
        ArrayList<T> list = new ArrayList<T>();
        for(RankedObject<T> r : tList){
            list.add(r.getObject());
        }
        return list;
    }

}
