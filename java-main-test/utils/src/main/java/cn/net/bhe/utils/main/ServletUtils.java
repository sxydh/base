package cn.net.bhe.utils.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ServletUtils {

    ;
    static final Logger LOGGER = LoggerFactory.getLogger(ServletUtils.class);

    public static void print(HttpServletResponse response, String content) {
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pw.flush();
            pw.close();
        }
    }

}
