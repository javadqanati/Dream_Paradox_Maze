package UI;

import javax.swing.*;
import java.awt.*;
import Input.InputAction;
import Launcher.GamePanel;
import org.jetbrains.annotations.NotNull;

/**
 * Button represents a clickable button UI element that can handle actions and input events.
 * It extends the UIComponent class to inherit position and visibility, and adds behavior for input handling.
 */
public class Button extends UIComponent {
    private final JButton button;  // JButton that represents the button in the UI
    private final String label;    // The text displayed on the button
    private InputAction inputAction; // InputAction to be performed on button press

    /**
     * Constructor for creating a Button with a label and specific dimensions at a given position.
     *
     * @param label       the text displayed on the button
     * @param width       the width of the button
     * @param height      the height of the button
     * @param x           the x-coordinate of the button
     * @param y           the y-coordinate of the button
     * @param inputAction the InputAction to handle input events
     */
    public Button(String label, int width, int height, int x, int y, @NotNull InputAction inputAction) {
        super(new GamePanel());  // Initialize the parent UIComponent with position and size
        this.label = label;
        this.inputAction = inputAction;

        // Initialize the JButton component
        button = new JButton(label);
        button.setBounds(x, y, width, height); // Set button position and size
        button.setFocusable(false); // Disable focusability
        button.setContentAreaFilled(false); // Make the background transparent
        button.setBorderPainted(false); // Remove the border

        // Bind the input action to the button's action listener
        button.addActionListener(inputAction::performAction);
    }

    /**
     * Constructor for initializing a Button from an existing JButton component.
     *
     * @param x        the x-coordinate of the button
     * @param y        the y-coordinate of the button
     * @param width    the width of the button
     * @param height   the height of the button
     * @param button   the existing JButton component
     * @param label    the label of the button
     */
    public Button(int x, int y, int width, int height, JButton button, String label) {
        super(new GamePanel());
        this.button = button;
        this.label = label;
    }

    /**
     * Draws the button, setting its appearance (such as text, size, and colors) dynamically.
     * This method is called to update the visual representation of the button.
     */
    public void draw() {
        // Dynamically set the appearance of the button
        button.setText(label);  // Set the button's text
        button.setPreferredSize(new Dimension(getWidth(), getHeight()));  // Set preferred size
        button.setBackground(Color.LIGHT_GRAY); // Set background color
        button.setForeground(Color.BLACK); // Set text color
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Set font
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Set border
    }

    /**
     * Returns the JButton component.
     *
     * @return the JButton
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Sets a new InputAction for the button, allowing dynamic changes to its behavior.
     *
     * @param inputAction the new InputAction to be performed on press
     */
    public void setInputAction(InputAction inputAction) {
        this.inputAction = inputAction;
        // Rebind the action listener for the new input action
        button.removeActionListener(inputAction::performAction);
        button.addActionListener(inputAction::performAction);
    }
}
