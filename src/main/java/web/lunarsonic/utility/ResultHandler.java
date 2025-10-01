package web.lunarsonic.utility;
import jakarta.servlet.http.HttpServletRequest;
import web.lunarsonic.core.HitCheck;
import web.lunarsonic.core.Validation;
import web.lunarsonic.models.*;
import web.lunarsonic.exceptions.ValidationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ResultHandler {
    private Point validate(HttpServletRequest request) {
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

    public Result handleResult(HttpServletRequest request) {
        long start = System.nanoTime();
        Point point = validate(request);
        HitCheck hitCheck = new HitCheck(point.x(), point.y(), point.r());
        boolean isHit = hitCheck.wasThereHit();
        long scriptTime = System.nanoTime() - start;
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        String serverTime = currentTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        return new Result(point.x(), point.y(), point.r(), isHit, scriptTime, serverTime);
    }
}
