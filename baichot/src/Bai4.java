import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Bai4 extends JFrame {
    private static class CalculatorEngine {
        private static final DecimalFormat DECIMAL_FORMAT;

        static {
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
            DECIMAL_FORMAT = new DecimalFormat("0.############", symbols);
        }

        public double calculateBinary(double left, double right, String operator) {
            switch (operator) {
                case "+":
                    return left + right;
                case "-":
                    return left - right;
                case "*":
                    return left * right;
                case "/":
                    if (right == 0) {
                        throw new ArithmeticException("Khong the chia cho 0.");
                    }
                    return left / right;
                case "%":
                    if (right == 0) {
                        throw new ArithmeticException("Khong the chia lay du cho 0.");
                    }
                    return left % right;
                default:
                    throw new IllegalArgumentException("Phep tinh khong hop le: " + operator);
            }
        }

        public double calculateUnary(double value, String operator) {
            switch (operator) {
                case "sqrt":
                    if (value < 0) {
                        throw new ArithmeticException("Khong the tinh can bac hai cua so am.");
                    }
                    return Math.sqrt(value);
                case "square":
                    return value * value;
                case "reciprocal":
                    if (value == 0) {
                        throw new ArithmeticException("Khong the lay nghich dao cua 0.");
                    }
                    return 1 / value;
                default:
                    throw new IllegalArgumentException("Phep tinh khong hop le: " + operator);
            }
        }

        public String format(double value) {
            return DECIMAL_FORMAT.format(value);
        }
    }

    private final CalculatorEngine engine = new CalculatorEngine();
    private final JLabel expressionLabel = new JLabel(" ", SwingConstants.RIGHT);
    private final JTextField displayField = new JTextField("0");

    private Double firstOperand;
    private String pendingOperation;
    private boolean startNewNumber = true;

    public Bai4() {
        super("Calculator");
        initializeFrame();
        initializeComponents();
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 560);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initializeComponents() {
        JPanel rootPanel = new JPanel(new BorderLayout(12, 12));
        rootPanel.setBackground(new Color(244, 246, 248));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        expressionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        expressionLabel.setForeground(new Color(110, 118, 129));

        displayField.setFont(new Font("Segoe UI", Font.BOLD, 30));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setEditable(false);
        displayField.setMargin(new Insets(12, 10, 12, 10));
        displayField.setBorder(BorderFactory.createLineBorder(new Color(210, 214, 220)));
        displayField.setBackground(Color.WHITE);

        JPanel displayPanel = new JPanel(new BorderLayout(4, 8));
        displayPanel.setOpaque(false);
        displayPanel.add(expressionLabel, BorderLayout.NORTH);
        displayPanel.add(displayField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 4, 8, 8));
        buttonPanel.setOpaque(false);

        String[][] buttonRows = {
            {"%", "CE", "C", "<-"},
            {"1/x", "x^2", "sqrt", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"+/-", "0", ".", "="}
        };

        for (String[] row : buttonRows) {
            for (String text : row) {
                buttonPanel.add(createButton(text));
            }
        }

        rootPanel.add(displayPanel, BorderLayout.NORTH);
        rootPanel.add(buttonPanel, BorderLayout.CENTER);
        setContentPane(rootPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color fillColor = getBackground();
                if (getModel().isArmed()) {
                    fillColor = fillColor.darker();
                } else if (getModel().isRollover()) {
                    fillColor = fillColor.brighter();
                }

                graphics2D.setColor(fillColor);
                graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                graphics2D.dispose();

                super.paintComponent(graphics);
            }
        };

        button.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        button.setRolloverEnabled(true);
        button.setForeground(new Color(24, 28, 33));

        if (isDigit(text) || ".".equals(text) || "+/-".equals(text)) {
            button.setBackground(Color.WHITE);
        } else if ("=".equals(text)) {
            button.setBackground(new Color(0, 120, 215));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(230, 233, 237));
        }

        button.addActionListener(event -> handleCommand(text));
        return button;
    }

    private void handleCommand(String command) {
        try {
            if (isDigit(command)) {
                appendDigit(command);
                return;
            }

            switch (command) {
                case ".":
                    appendDecimalPoint();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                    setOperation(command);
                    break;
                case "=":
                    calculateResult();
                    break;
                case "CE":
                    clearEntry();
                    break;
                case "C":
                    clearAll();
                    break;
                case "<-":
                    backspace();
                    break;
                case "+/-":
                    toggleSign();
                    break;
                case "sqrt":
                    applyUnaryOperation("sqrt", "sqrt(" + displayField.getText() + ")");
                    break;
                case "x^2":
                    applyUnaryOperation("square", "sqr(" + displayField.getText() + ")");
                    break;
                case "1/x":
                    applyUnaryOperation("reciprocal", "1/(" + displayField.getText() + ")");
                    break;
                default:
                    throw new IllegalArgumentException("Lenh khong duoc ho tro: " + command);
            }
        } catch (ArithmeticException | IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private void appendDigit(String digit) {
        if (startNewNumber || "0".equals(displayField.getText())) {
            displayField.setText(digit);
        } else {
            displayField.setText(displayField.getText() + digit);
        }
        startNewNumber = false;
    }

    private void appendDecimalPoint() {
        if (startNewNumber) {
            displayField.setText("0.");
            startNewNumber = false;
            return;
        }

        if (!displayField.getText().contains(".")) {
            displayField.setText(displayField.getText() + ".");
        }
    }

    private void setOperation(String operation) {
        double currentValue = getDisplayValue();

        if (pendingOperation != null && startNewNumber) {
            pendingOperation = operation;
            expressionLabel.setText(engine.format(firstOperand) + " " + operation);
            return;
        }

        if (firstOperand == null) {
            firstOperand = currentValue;
        } else if (pendingOperation != null) {
            firstOperand = engine.calculateBinary(firstOperand, currentValue, pendingOperation);
            displayField.setText(engine.format(firstOperand));
        }

        pendingOperation = operation;
        expressionLabel.setText(engine.format(firstOperand) + " " + operation);
        startNewNumber = true;
    }

    private void calculateResult() {
        if (pendingOperation == null || firstOperand == null || startNewNumber) {
            return;
        }

        double secondOperand = getDisplayValue();
        double result = engine.calculateBinary(firstOperand, secondOperand, pendingOperation);

        expressionLabel.setText(
            engine.format(firstOperand) + " " + pendingOperation + " " + engine.format(secondOperand) + " ="
        );
        displayField.setText(engine.format(result));

        firstOperand = null;
        pendingOperation = null;
        startNewNumber = true;
    }

    private void applyUnaryOperation(String operation, String expressionText) {
        double currentValue = getDisplayValue();
        double result = engine.calculateUnary(currentValue, operation);
        displayField.setText(engine.format(result));
        expressionLabel.setText(expressionText);
        startNewNumber = true;
    }

    private void clearEntry() {
        displayField.setText("0");
        startNewNumber = true;
    }

    private void clearAll() {
        displayField.setText("0");
        expressionLabel.setText(" ");
        firstOperand = null;
        pendingOperation = null;
        startNewNumber = true;
    }

    private void backspace() {
        if (startNewNumber) {
            return;
        }

        String currentText = displayField.getText();
        if (currentText.length() <= 1 || (currentText.length() == 2 && currentText.startsWith("-"))) {
            displayField.setText("0");
            startNewNumber = true;
            return;
        }

        displayField.setText(currentText.substring(0, currentText.length() - 1));
    }

    private void toggleSign() {
        if ("0".equals(displayField.getText())) {
            return;
        }

        if (displayField.getText().startsWith("-")) {
            displayField.setText(displayField.getText().substring(1));
        } else {
            displayField.setText("-" + displayField.getText());
        }
    }

    private double getDisplayValue() {
        return Double.parseDouble(displayField.getText());
    }

    private boolean isDigit(String text) {
        return text.length() == 1 && Character.isDigit(text.charAt(0));
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Loi", JOptionPane.ERROR_MESSAGE);
        clearAll();
    }

    public static void main(String[] args) {
        if (args.length > 0 && "--test".equals(args[0])) {
            CalculatorEngine engine = new CalculatorEngine();
            System.out.println("===== BAI 4: KIEM TRA LOGIC CALCULATOR =====");
            System.out.println("15 + 5 = " + engine.format(engine.calculateBinary(15, 5, "+")));
            System.out.println("15 - 5 = " + engine.format(engine.calculateBinary(15, 5, "-")));
            System.out.println("15 * 5 = " + engine.format(engine.calculateBinary(15, 5, "*")));
            System.out.println("15 / 5 = " + engine.format(engine.calculateBinary(15, 5, "/")));
            System.out.println("15 % 4 = " + engine.format(engine.calculateBinary(15, 4, "%")));
            System.out.println("sqrt(81) = " + engine.format(engine.calculateUnary(81, "sqrt")));
            System.out.println("7^2 = " + engine.format(engine.calculateUnary(7, "square")));
            System.out.println("1 / 8 = " + engine.format(engine.calculateUnary(8, "reciprocal")));
            return;
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            Bai4 calculatorApp = new Bai4();
            calculatorApp.setVisible(true);
        });
    }
}
