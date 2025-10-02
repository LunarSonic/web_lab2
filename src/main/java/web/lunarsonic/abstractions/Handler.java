package web.lunarsonic.abstractions;
import web.lunarsonic.models.ResultContext;

public interface Handler {
    void handle(ResultContext resultContext);
    void setNextHandler(Handler nextHandler);
}
