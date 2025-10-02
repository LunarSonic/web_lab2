package web.lunarsonic.handlers;
import com.google.gson.Gson;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import web.lunarsonic.abstractions.BaseHandler;
import web.lunarsonic.models.*;
import java.util.logging.Logger;

public class HistoryHandler extends BaseHandler {
    private static final Logger logger = Logger.getLogger(HistoryHandler.class.getName());

    @Override
    protected void process(ResultContext resultContext) {
        Result newResult = resultContext.getResult();
        HttpServletRequest request = resultContext.getRequest();
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
