package web.lunarsonic.models;
import jakarta.servlet.http.HttpServletRequest;

public class ResultContext {
    private final HttpServletRequest request;
    private final long startTime;
    private Point pointFromRequest;
    private Result result;

    public ResultContext(HttpServletRequest request) {
        this.request = request;
        startTime = System.nanoTime();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public long getStartTime() {
        return startTime;
    }

    public Result getResult() {
        return result;
    }

    public Point getPointFromRequest() {
        return pointFromRequest;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setPointFromRequest(Point pointFromRequest) {
        this.pointFromRequest = pointFromRequest;
    }
}
