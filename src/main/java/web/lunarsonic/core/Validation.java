package web.lunarsonic.core;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Validation {
    private float x;
    private int y;
    private float r;
    public static final Set<Integer> availableY = Set.of(-5, -4, -3, -2, -1, 0, 1, 2, 3);
    public static final Set<Float> availableR = Set.of(1F, 1.5F, 2F, 2.5F, 3F);
    public static final String floatPattern = "^-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?$";
    private final List<String> errors = new ArrayList<>();

    public Validation() {}

    public void validateXYR(Map<String, String> coordinates) {
        validateX(coordinates);
        validateY(coordinates);
        validateR(coordinates);
    }

    private void validateX(Map<String, String> coordinates) {
        String xValue = coordinates.get("x");
        if (xValue.trim().isEmpty()) {
            errors.add("Координата X отсутствует!");
            return;
        }
        if (!xValue.matches(floatPattern)) {
            errors.add("Координата X должна быть числом с плавающей точкой!");
            return;
        }
        float parsedX = Float.parseFloat(xValue);
        if (parsedX < -3 || parsedX > 5) {
            errors.add("Координата Y должна быть от -3 до 5!");
            return;
        }
        this.x = parsedX;
    }

    private void validateY(Map<String, String> coordinates) {
        String yValue = coordinates.get("y");
        if (yValue.trim().isEmpty()) {
            errors.add("Координата Y отсутствует!");
            return;
        }
        try {
            int parsedY = Integer.parseInt(yValue);
            if (!availableY.contains(parsedY)) {
                errors.add("Координата Y должна быть от -5 до 3!");
                return;
            }
            this.y = parsedY;
        } catch (NumberFormatException e) {
            errors.add("Координата Y должна быть числом!");
        }
    }

    private void validateR(Map<String, String> coordinates) {
        String rValue = coordinates.get("r");
        if (rValue.trim().isEmpty()) {
            errors.add("Радиус R отсутствует!");
            return;
        }
        if (!rValue.matches(floatPattern)) {
            errors.add("Радиус R должен быть числом с плавающей точкой!");
            return;
        }
        float parsedR = Float.parseFloat(rValue);
        if (!availableR.contains(parsedR)) {
            errors.add("Радиус R должен быть равен одному из значений: 1.0, 1.5, 2.0, 2.5, 3.0");
            return;
        }
        this.r = parsedR;
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public float getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getR() {
        return r;
    }
}
