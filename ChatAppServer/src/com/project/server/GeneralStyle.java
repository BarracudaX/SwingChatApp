package com.project.server;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class GeneralStyle {

    private static final Color BLUE_COLOR = Color.decode("#0d47a1");
    private static final Color RED_COLOR = Color.decode("#CC0000");
    private static final Color RED_LIGHT_COLOR = Color.decode("#ff4444");
    private static final Color WHITE_COLOR = Color.decode("#ffffff");
    private static final Color DARK_COLOR = Color.decode("#212121");
    private static final Color GREY_COLOR = Color.decode("#2E2E2E");
    private static final Font FONT = new Font("SansSerif", Font.PLAIN, 16);
    private static final int DEFAULT_TEXT_FIELD_SIZE = 20;
    private static final int DEFAULT_TEXT_AREA_COLUMNS = 20;
    private static final int DEFAULT_TEXT_AREA_ROWS = 10;

    public static class GeneralStyleBuilder{

        public GeneralStyleBuilder setBackgroundAsBlue(Component ...components) {
            for (Component component : components) {
                component.setBackground(BLUE_COLOR);
            }
            return this;
        }

        public GeneralStyleBuilder setBackgroundAsRed(Component ...components) {
            for (Component component : components) {
                component.setBackground(RED_COLOR);
            }
            return this;
        }

        public GeneralStyleBuilder setBackgroundAsDark(Component... components) {
            for (Component component : components) {
                component.setBackground(DARK_COLOR);
            }
            return this;
        }

        public GeneralStyleBuilder setBackgroundAsGrey(Component ...components) {
            for (Component component : components) {
                component.setBackground(GREY_COLOR);
            }
            return this;
        }

        public GeneralStyleBuilder setForegroundAsWhite(Component ...components) {
            for (Component component : components) {
                component.setForeground(WHITE_COLOR);
            }
            return this;
        }

        public GeneralStyleBuilder setFont(Component ...components) {
            for (Component component : components) {
                component.setFont(FONT);
            }
            return this;
        }

        public GeneralStyleBuilder setTextFieldSize(JTextField ...fields) {
            for (JTextField field : fields) {
                field.setColumns(DEFAULT_TEXT_FIELD_SIZE);
            }
            return this;
        }

        public GeneralStyleBuilder setTextAreaSize(JTextArea ...areas) {
            for (JTextArea area : areas) {
                area.setColumns(DEFAULT_TEXT_AREA_COLUMNS);
                area.setRows(DEFAULT_TEXT_AREA_ROWS);
            }
            return this;
        }

        public GeneralStyleBuilder setCursorAsHand(JButton... buttons) {
            for (JButton button : buttons) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            return this;
        }

        public GeneralStyleBuilder setNotEditable(JTextComponent... components) {
            for (JTextComponent component : components) {
                component.setEditable(false);
            }
            return this;
        }

        public GeneralStyleBuilder setHorizontalAlignmentToRight(JLabel... labels) {
            for (JLabel label : labels) {
                label.setHorizontalAlignment(SwingConstants.RIGHT);
            }
            return this;
        }

        public GeneralStyleBuilder setHorizontalAlignmentToCenter(JLabel... labels) {
            for (JLabel label : labels) {
                label.setHorizontalAlignment(SwingConstants.CENTER);
            }
            return this;
        }

        public GeneralStyleBuilder setBackgroundAsLightRed(Component ...components) {
            for (Component component : components) {
                component.setBackground(RED_LIGHT_COLOR);
            }
            return this;
        }
    }

}
