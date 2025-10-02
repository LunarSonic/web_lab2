package web.lunarsonic.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.lunarsonic.abstractions.Handler;
import web.lunarsonic.exceptions.ValidationException;
import web.lunarsonic.core.RequestChainExecutor;
import web.lunarsonic.models.ResultContext;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AreaCheckServlet.class.getName());
    private Handler chainStart;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultContext resultContext = new ResultContext(request);
        try {
            chainStart.handle(resultContext);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (ValidationException e) {
            logger.info("Ошибка валидации: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        RequestChainExecutor requestChainExecutor = new RequestChainExecutor();
        this.chainStart = requestChainExecutor.createChain();
    }
}
