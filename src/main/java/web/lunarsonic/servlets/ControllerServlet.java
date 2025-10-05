package web.lunarsonic.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/app")
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Путь запроса: " + request.getRequestURI());
        String x = request.getParameter("x");
        String yCheckbox = request.getParameter("y");
        String yCanvas = request.getParameter("yCanvasValue");
        String r = request.getParameter("r");
        logger.info("Обработка запроса: x=" + x + "y=" + (yCheckbox != null ? yCheckbox : yCanvas) + "r=" + r);
        if (x != null && (yCheckbox != null || yCanvas != null) && r != null) {
            request.getRequestDispatcher("/check").forward(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

