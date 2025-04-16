package UI;

/**
 * UIComponent is the base class for all UI elements. It defines common properties like
 * position, size, and visibility, which can be extended by other UI components.
 */
abstract class UIComponent {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible = true;

    /**
     * Constructor to initialize a UIComponent with position and size.
     *
     * @param x      the x-coordinate of the component
     * @param y      the y-coordinate of the component
     * @param width  the width of the component
     * @param height the height of the component
     */
    public UIComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters and Setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
