package web.lunarsonic.servlets;
import com.google.gson.Gson;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.lunarsonic.exceptions.ValidationException;
import web.lunarsonic.models.*;
import web.lunarsonic.utility.ResultHandler;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AreaCheckServlet.class.getName());
    private final ResultHandler resultHandler = new ResultHandler();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Result newResult = resultHandler.handleResult(request);
            handleHistory(request, newResult);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (ValidationException e) {
            logger.info("Ошибка валидации: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private void handleHistory(HttpServletRequest request, Result newResult) {
        ServletContext servletContext = request.getServletContext();
        ResultHistory resultHistory = (ResultHistory) servletContext.getAttribute("resultHistory");
        if (resultHistory == null) {
            resultHistory = ResultHistory.getInstance();
            servletContext.setAttribute("resultHistory", resultHistory);
        }
        resultHistory.addResultToHistory(newResult);
        Gson gson = new Gson();
        String historyJson = gson.toJson(resultHistory.getHistory());
        logger.info("История: " + historyJson);
        request.setAttribute("newResult", newResult);
        request.setAttribute("historyJson", historyJson);
    }
}
