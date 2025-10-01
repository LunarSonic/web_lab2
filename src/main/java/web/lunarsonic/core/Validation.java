package web.lunarsonic.core;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Validation {
    private float x;
    private float y;
    private float r;
    private final String floatPattern = "^-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?$";
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
        if (parsedX <= -3 || parsedX >= 5) {
            errors.add("Координата X должна быть в диапазоне (-3; 5)");
            return;
        }
        this.x = parsedX;
    }

    private void validateY(Map<String, String> coordinates) {
        String yValue = findFirstNotEmptyValue(coordinates.get("yCanvasValue"), coordinates.get("y"));
        if (yValue == null) {
            errors.add("Координата Y отсутствует!");
            return;
        }
        if (!yValue.matches(floatPattern)) {
            errors.add("Координата Y должна быть числом с плавающей точкой!");
            return;
        }
        try {
            float parsedY = Float.parseFloat(yValue);
            if (parsedY < -5 || parsedY > 3) {
                errors.add("Координата Y должна быть в диапазоне [-5; 3]");
                return;
            }
            this.y = parsedY;
        } catch (NumberFormatException e) {
            errors.add("Координата Y должна быть числом!");
        }
    }

    private String findFirstNotEmptyValue(String... values) {
        for (String value: values) {
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }
        return null;
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
        if (parsedR < 1 || parsedR > 3) {
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

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }
}
