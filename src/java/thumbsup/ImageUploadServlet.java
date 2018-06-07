package thumbsup;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("image");
        if (part != null) {
            ImageManager manager = (ImageManager) getServletContext().getAttribute("imageManager");

            InputStream imageStream = null;
            try {
                String fileName = part.getSubmittedFileName();
                String contentType = part.getContentType();

                imageStream = part.getInputStream();

                manager.saveImage(fileName, contentType, imageStream);

            } finally {
                if (imageStream != null) {
                    try {
                        imageStream.close();
                    } catch (IOException ignored) {
                    }
                }
            }

            resp.sendRedirect("/thumbsup/");
        }

    }
}
