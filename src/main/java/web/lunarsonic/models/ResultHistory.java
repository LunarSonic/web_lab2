package web.lunarsonic.models;
import java.util.ArrayList;
import java.util.List;

public class ResultHistory {
    private final List<Result> history = new ArrayList<>();
    private static final ResultHistory instance = new ResultHistory();

    private ResultHistory() {}

    public static ResultHistory getInstance() {
        return instance;
    }

    public List<Result> getHistory() {
        return history;
    }

    public void addResultToHistory(Result result) {
        history.add(result);
    }
}
