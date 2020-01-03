package cn.net.bhe.servlet.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/picture/get")
@MultipartConfig
public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 5241775775497866276L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        File file = new File("C:/Users/Work/Desktop/" + name);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            String filename = URLEncoder.encode(file.getName(), "utf-8");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            out.close();
        } else {
            request.setAttribute("hint", "File not exist!");
            // Jump to the upload page
            request.getRequestDispatcher("/file.jsp").forward(request, response);
        }
    }

}
