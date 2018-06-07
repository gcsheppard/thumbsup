package thumbsup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageManager imageManager = (ImageManager) getServletContext().getAttribute("imageManager");
        
        req.setAttribute("images", imageManager.allImages());
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
