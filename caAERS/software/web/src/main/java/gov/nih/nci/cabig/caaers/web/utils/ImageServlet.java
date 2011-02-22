package gov.nih.nci.cabig.caaers.web.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
public class ImageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ImageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportID = request.getParameter("report");
        String aeReportID = request.getParameter("aeReport");
        String pageNumber = request.getParameter("page");
        String imagePath = WebUtils.getFilePath(aeReportID, reportID, pageNumber);
        ServletOutputStream outPutStream = null;
        File f = new File(imagePath.trim());

        response.setContentType("image/png");
        response.setContentLength((int) (f.length()));
        outPutStream = response.getOutputStream();
        dumpFile(imagePath, outPutStream);

        outPutStream.flush();
        outPutStream.close();

    }

    private void dumpFile(String paramString, OutputStream paramOutputStream) {
        String str = paramString;
        byte[] arrayOfByte = new byte[4096];
        try {
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            int i;
            while ((i = localBufferedInputStream.read(arrayOfByte, 0, 4096)) != -1)
                paramOutputStream.write(arrayOfByte, 0, i);
            localBufferedInputStream.close();
        } catch (Exception localException) {
        }
    }

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
