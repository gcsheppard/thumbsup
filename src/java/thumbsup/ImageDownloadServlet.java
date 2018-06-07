package thumbsup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageManager manager = (ImageManager) getServletContext().getAttribute("imageManager");
        Integer id = Integer.parseInt(req.getParameter("id"));
        ImageInfo image = manager.imageInfoBy(id);

        if (image != null) {
            resp.setStatus(200);
	    resp.setContentType(image.getContentType());
            manager.copyImageContent(id, resp.getOutputStream());
        } else {
            resp.sendError(404);
        }
        
    }

}
