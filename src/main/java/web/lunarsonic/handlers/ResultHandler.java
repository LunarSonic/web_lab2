package web.lunarsonic.handlers;
import web.lunarsonic.abstractions.BaseHandler;
import web.lunarsonic.core.HitCheck;
import web.lunarsonic.models.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ResultHandler extends BaseHandler {
    @Override
    protected void process(ResultContext resultContext) {
        Point point = resultContext.getPointFromRequest();
        long start = resultContext.getStartTime();
        HitCheck hitCheck = new HitCheck(point.x(), point.y(), point.r());
        boolean isHit = hitCheck.wasThereHit();
        long scriptTime = System.nanoTime() - start;
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        String serverTime = currentTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Result result = new Result(point.x(), point.y(), point.r(), isHit, scriptTime, serverTime);
        resultContext.setResult(result);
    }
}
