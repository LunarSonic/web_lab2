package web.lunarsonic.handlers;
import jakarta.servlet.http.HttpServletRequest;
import web.lunarsonic.abstractions.BaseHandler;
import web.lunarsonic.core.Validation;
import web.lunarsonic.exceptions.ValidationException;
import web.lunarsonic.models.Point;
import web.lunarsonic.models.ResultContext;
import java.util.HashMap;
import java.util.Map;

public class ValidationHandler extends BaseHandler {
    private Point validateAndCreatePoint(HttpServletRequest request) {
        Map<String, String> coordinates = new HashMap<>();
        coordinates.put("x", request.getParameter("x"));
        coordinates.put("y", request.getParameter("y"));
        coordinates.put("yCanvasValue", request.getParameter("yCanvasValue"));
        coordinates.put("r", request.getParameter("r"));
        Validation validation = new Validation();
        validation.validateXYR(coordinates);
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getErrors().toString());
        }
        float x = validation.getX();
        float y = validation.getY();
        float r = validation.getR();
        return new Point(x, y, r);
    }

    @Override
    protected void process(ResultContext resultContext) {
        HttpServletRequest request = resultContext.getRequest();
        Point point = validateAndCreatePoint(request);
        resultContext.setPointFromRequest(point);
    }
}
