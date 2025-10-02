package web.lunarsonic.core;
import web.lunarsonic.abstractions.Handler;
import web.lunarsonic.handlers.HistoryHandler;
import web.lunarsonic.handlers.ResultHandler;
import web.lunarsonic.handlers.ValidationHandler;

public class RequestChainExecutor {
    public Handler createChain() {
        Handler validationHandler = new ValidationHandler();
        Handler resultHandler = new ResultHandler();
        Handler historyHandler = new HistoryHandler();
        validationHandler.setNextHandler(resultHandler);
        resultHandler.setNextHandler(historyHandler);
        return validationHandler;
    }
}
