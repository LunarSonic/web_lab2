package web.lunarsonic.servlets;
import com.google.gson.Gson;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import web.lunarsonic.core.*;
import web.lunarsonic.models.*;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AreaCheckServlet.class.getName());
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long start = System.nanoTime();
        Map<String, String> coordinates = new HashMap<>();
        coordinates.put("x", request.getParameter("x"));
        coordinates.put("y", request.getParameter("y"));
        coordinates.put("r", request.getParameter("r"));
        Validation validation = new Validation();
        validation.validateXYR(coordinates);
        if (validation.hasErrors()) {
            logger.info("Ошибки: " + validation.getErrors());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        float x = validation.getX();
        int y = validation.getY();
        float r = validation.getR();
        HitCheck hitCheck = new HitCheck(x, y, r);
        boolean isHit = hitCheck.wasThereHit();
        long scriptTime = System.nanoTime() - start;
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        String serverTime = currentTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Result newResult = new Result(x, y, r, isHit, scriptTime, serverTime);
        ServletContext servletContext = request.getServletContext();
        ResultHistory history = (ResultHistory) servletContext.getAttribute("resultHistory");
        if (history == null) {
            history = ResultHistory.getInstance();
            servletContext.setAttribute("resultHistory", history);
        }
        history.addResultToHistory(newResult);
        Gson gson = new Gson();
        String historyJson = gson.toJson(history.getHistory());
        logger.info("История: "+ historyJson);
        request.setAttribute("historyJson", historyJson);
        request.setAttribute("newResult", newResult);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
