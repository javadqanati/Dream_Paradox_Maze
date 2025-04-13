package UI;

import java.util.List;

public class UIManager {
    private List<UIComponent> components;

    public UIManager(List<UIComponent> components) {
        this.components = components;
    }

    // Initialize UI components (set them up, set initial properties, add them to the container)
    public void initializeUI() {
        for (UIComponent component : components) {
            component.setVisible(true);
        }
    }

    public void addComponent(UIComponent component) {
        components.add(component);
    }

    public void removeComponent(UIComponent component) {
        components.remove(component);
    }
}
