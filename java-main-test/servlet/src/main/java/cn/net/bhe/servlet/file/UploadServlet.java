package cn.net.bhe.servlet.file;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/picture/post")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 8409517717394774771L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * You can use the request.getPart() method to get the upload
         * attachment, or use request.getParts() to get all the uploaded
         * attachments (multi-file upload), and then process each uploaded file
         * separately by loop.
         */
        Part part = request.getPart("sample");
        if (part != null && part.getSubmittedFileName().length() > 0) {
            /*
             * Use the getRealPath() method of the ServletContext object to get
             * the absolute path of the uploaded file.
             */
            String savePath = request.getServletContext().getRealPath("/upload");
            File directory = new File(savePath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            /*
             * In the Servlet 3.1 specification, you can use the
             * getSubmittedFileName() method of the Part object to get the
             * uploaded file name. It is better to rename the uploaded file (to
             * avoid overlapping each other with the same name).
             */
            part.write(savePath + "/" + part.getSubmittedFileName());
            request.setAttribute("hint", "Upload Successfully!");
        } else {
            request.setAttribute("hint", "Upload failed!");
        }
        // Jump to the upload page
        request.getRequestDispatcher("/file.jsp").forward(request, response);
    }

}
