package gov.nih.nci.cabig.caaers.web.filters;

/**
 *  Copyright 2003-2006 Greg Luck
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


import net.sf.ehcache.constructs.web.GenericResponseWrapper;
import net.sf.ehcache.constructs.web.ResponseHeadersNotModifiableException;
import net.sf.ehcache.constructs.web.ResponseUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Provides GZIP compression of responses.
 * <p/>
 * See the filter-mappings.xml entry for the gzip filter for the URL patterns
 * which will be gzipped. At present this includes .jsp, .js and .css.
 * <p/>
 * @author <a href="mailto:gluck@thoughtworks.com">Greg Luck</a>
 * @author <a href="mailto:amurdoch@thoughtworks.com">Adam Murdoch</a>
 * @version $Id: GzipFilter.java 233 2006-11-18 03:11:18Z gregluck $
 */
public class GzipFilter extends net.sf.ehcache.constructs.web.filter.GzipFilter {

    private static final Log LOG = LogFactory.getLog(GzipFilter.class.getName());

    /**
     * Performs the filtering for a request.
     */
    protected void doFilter(final HttpServletRequest request, final HttpServletResponse response,
                            final FilterChain chain) throws Exception {
        if (!isIncluded(request) && acceptsEncoding(request, "gzip")) {
            // Client accepts zipped content
            if (LOG.isDebugEnabled()) {
                LOG.debug(request.getRequestURL() + ". Writing with gzip compression");
            }

            // Create a gzip stream
            final ByteArrayOutputStream compressed = new ByteArrayOutputStream();
            final GZIPOutputStream gzout = new GZIPOutputStream(compressed);

            // Handle the request
            final GenericResponseWrapper wrapper = new GenericResponseWrapper(response, gzout);
            chain.doFilter(request, wrapper);
            wrapper.flush();

            gzout.close();

            //return on error or redirect code, because response is already committed
            int statusCode = wrapper.getStatus();
            if (statusCode != HttpServletResponse.SC_OK) {
                return;
            }

            //Saneness checks
            byte[] compressedBytes = compressed.toByteArray();
            boolean shouldGzippedBodyBeZero = ResponseUtil.shouldGzippedBodyBeZero(compressedBytes, request);
            boolean shouldBodyBeZero = ResponseUtil.shouldBodyBeZero(request, wrapper.getStatus());
            if (shouldGzippedBodyBeZero || shouldBodyBeZero) {
                compressedBytes = new byte[0];
            }

            try {
				// Write the zipped body
				ResponseUtil.addGzipHeader(response);
				response.setContentLength(compressedBytes.length);
			} catch (ResponseHeadersNotModifiableException e) {
				return;
			}


            response.getOutputStream().write(compressedBytes);
        } else {
            // Client does not accept zipped content - don't bother zipping
            if (LOG.isDebugEnabled()) {
                LOG.debug(request.getRequestURL()
                        + ". Writing without gzip compression because the request does not accept gzip.");
            }
            chain.doFilter(request, response);
        }
    }


    /**
     * Checks if the request uri is an include.
     * These cannot be gzipped.
     */
    private boolean isIncluded(final HttpServletRequest request) {
        final String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        final boolean includeRequest = !(uri == null);

        if (includeRequest && LOG.isDebugEnabled()) {
            LOG.debug(request.getRequestURL() + " resulted in an include request. This is unusable, because" +
                    "the response will be assembled into the overrall response. Not gzipping.");
        }
        return includeRequest;
    }


}

