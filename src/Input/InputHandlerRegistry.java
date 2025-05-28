package Input;

import java.util.HashMap;
import java.util.Map;

public class InputHandlerRegistry {
    private static final Map<String, ScreenInputHandler> registry = new HashMap<>();

    public static void register(String stateName, ScreenInputHandler handler) {
        registry.put(stateName, handler);
    }

    public static ScreenInputHandler get(String stateName) {
        return registry.get(stateName);
    }
}