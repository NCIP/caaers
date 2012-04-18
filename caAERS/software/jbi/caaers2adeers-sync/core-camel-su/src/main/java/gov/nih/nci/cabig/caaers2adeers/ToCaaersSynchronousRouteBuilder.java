package gov.nih.nci.cabig.caaers2adeers;

/**
 * Created by IntelliJ IDEA.
 * User: BJW7
 * Date: 4/18/12
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToCaaersSynchronousRouteBuilder {


    private String xslBase = "xslt/caaers/response/";

    private Caaers2AdeersRouteBuilder routeBuilder;

    public ToCaaersSynchronousRouteBuilder(Caaers2AdeersRouteBuilder routeBuilder) {
        this.routeBuilder = routeBuilder;
    }

    private String xpathPredicate(String entity, String operation){

        return new StringBuilder("/payload/response/operation/data ")
                .append(" and ")
                .append("not(/payload/response/operation/errors)")
                .append(" and ")
                .append("/payload/response/operation/@name = '").append(operation).append("' ")
                .append("and ")
                .append("/payload/response/entity = '").append(entity).append("'").toString();
    }

    public void configure(){
        //content based router
        routeBuilder.from("direct:caAERSSynchronousRequestSink")
                .to("log:caaers-sync-request")
                .choice()
                .when().xpath(xpathPredicate("study", "searchStudy")).to("direct:caaers-study-search-sync")
                .otherwise().to("log:unknown-sync-caaers-request");

        //caAERS - searchStudy
        this.routeBuilder.configureTransformationRoute("direct:caaers-study-search-sync", xslBase + "study_search_sync.xsl");
    }
}
