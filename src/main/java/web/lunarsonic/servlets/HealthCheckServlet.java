package web.lunarsonic.servlets;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@WebServlet("/healthy")
public class HealthCheckServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(HealthCheckServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info("Health Check: сервер работоспособен");
    }
}
