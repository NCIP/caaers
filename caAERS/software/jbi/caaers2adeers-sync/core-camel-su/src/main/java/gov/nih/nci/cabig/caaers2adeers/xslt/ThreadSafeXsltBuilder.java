package gov.nih.nci.cabig.caaers2adeers.xslt;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.xml.DefaultTransformErrorHandler;
import org.apache.camel.builder.xml.ResultHandler;
import org.apache.camel.builder.xml.StringResultHandler;
import org.apache.camel.builder.xml.XsltBuilder;
import org.apache.camel.impl.SynchronizationAdapter;
import org.apache.camel.util.ExchangeHelper;
import org.apache.camel.util.FileUtil;
import org.apache.camel.util.IOHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import java.io.File;
import java.io.InputStream;

import static org.apache.camel.util.ObjectHelper.notNull;


public class ThreadSafeXsltBuilder extends XsltBuilder {
    protected static final Log log = LogFactory.getLog(ThreadSafeXsltBuilder.class);

    public ThreadSafeXsltBuilder() {
        super();
    }

    public ThreadSafeXsltBuilder(Templates templates) {
        super(templates);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        notNull(getTemplate(), "template");

        if (isDeleteOutputFile()) {
            log.debug("Adding onCompletion handler");
            // add on completion so we can delete the file when the Exchange is done
            String fileName = ExchangeHelper.getMandatoryHeader(exchange, Exchange.XSLT_FILE_NAME, String.class);
            exchange.addOnCompletion(new XsltBuilderOnCompletion(fileName));
        }

        Transformer transformer = getTemplate().newTransformer();
        log.debug("Obtained transformer : " + transformer.toString() + " in thread : " + Thread.currentThread().getName());
        configureTransformer(transformer, exchange);
        transformer.setErrorListener(new DefaultTransformErrorHandler());
        ResultHandler resultHandler = new StringResultHandler();

        Result result = resultHandler.getResult();

        // let's copy the headers before we invoke the transform in case they modify them
        Message out = exchange.getOut();
        out.copyFrom(exchange.getIn());

        // the underlying input stream, which we need to close to avoid locking files or other resources
        InputStream is = null;
        try {
            Source source;
            // only convert to input stream if really needed
            if (isInputStreamNeeded(exchange)) {
                is = exchange.getIn().getBody(InputStream.class);
                source = getSource(exchange, is);
            } else {
                Object body = exchange.getIn().getBody();
                source = getSource(exchange, body);
            }
            log.debug("Input Source [" + Thread.currentThread() + "] : " + source);
            transformer.transform(source, result);
            log.debug("Output Result [" + Thread.currentThread() + "] : " + result);
            resultHandler.setBody(out);
            if(log.isDebugEnabled()){
                log.debug("Transformed XSLT [" + Thread.currentThread().getName() + "] \n=====================================================================================: " + out.getBody(String.class) + "\n******************************************************\n");
            }
        } finally {
            // IOHelper can handle if is is null
            IOHelper.close(is);
        }
    }


    private final class XsltBuilderOnCompletion extends SynchronizationAdapter {
        private final String fileName;

        private XsltBuilderOnCompletion(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void onDone(Exchange exchange) {
            FileUtil.deleteFile(new File(fileName));
        }

        @Override
        public String toString() {
            return "XsltBuilderOnCompletion";
        }
    }

}
