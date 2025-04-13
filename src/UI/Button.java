package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Button extends UIComponent {
    private ActionListener actionListener;
    private JButton button;
    private String label;
    public Button(String label, int width, int height,
                  int x, int y, ActionListener actionListener) {
        super(width, height, x, y);
        this.actionListener = actionListener;
        this.label=label;
        button = new JButton(label);
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(actionListener);

    }

    public void draw() {
        JButton jButton = getButton(); // Assuming you have a getButton() method

        // Set temporary placeholder styles
        jButton.setText("Button"); // Or set it dynamically if your class supports labels
        jButton.setPreferredSize(new Dimension(150, 50));
        jButton.setBackground(Color.LIGHT_GRAY);
        jButton.setForeground(Color.BLACK);
        jButton.setFont(new Font("Arial", Font.BOLD, 16));
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public void onClick() {
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public JButton getButton() {
        return button;
    }

}
