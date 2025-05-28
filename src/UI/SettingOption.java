package UI;

import java.util.function.Supplier;

public class SettingOption {
    private final String name;
    private final Supplier<String> valueSupplier;

    public SettingOption(String name, Supplier<String> valueSupplier) {
        this.name = name;
        this.valueSupplier = valueSupplier;
    }

    public String getDisplayText() {
        String value = valueSupplier.get();
        return value == null || value.isBlank() ? name : name + ": " + value;
    }

    public String getName() {
        return name;
    }
}
