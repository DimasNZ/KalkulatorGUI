import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private JLabel realTimeLabel;
    private String operator;
    private double tempValue;
    private boolean isOperatorPressed;

    public KalkulatorGUI() {
        // Set up the frame
        setTitle("Kalkulator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create real-time display panel
        JPanel realTimePanel = new JPanel();
        realTimePanel.setLayout(new BorderLayout());
        realTimeLabel = new JLabel("Real-time Input: ");
        realTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        realTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        realTimeLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        realTimePanel.add(realTimeLabel, BorderLayout.NORTH);

        // Create display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        realTimePanel.add(display, BorderLayout.SOUTH);

        add(realTimePanel, BorderLayout.NORTH);

        // Create top panel for Clear and Equals buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 10, 10));

        JButton clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.BOLD, 24));
        clearButton.setBackground(Color.RED);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        clearButton.setPreferredSize(new Dimension(100, 50)); // Atur ukuran tombol menjadi 100x50 piksel
        topPanel.add(clearButton);

        JButton equalsButton = new JButton("=");
        equalsButton.setBounds(50, 50, 50, 50);
        equalsButton.setFont(new Font("Arial", Font.BOLD, 24));
        equalsButton.setBackground(Color.GREEN);
        equalsButton.setForeground(Color.WHITE);
        equalsButton.addActionListener(this);
        topPanel.add(equalsButton);
        


        add(topPanel, BorderLayout.CENTER);

        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Define buttons
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "+", "+/-"
        };

        // Add buttons to panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            button.setBackground(Color.LIGHT_GRAY);
            panel.add(button);
        }

        add(panel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            if (isOperatorPressed) {
                display.setText("");
                isOperatorPressed = false;
            }
            display.setText(display.getText() + command);
            realTimeLabel.setText("Real-time Input: " + display.getText());
        } else if ("+-*/".contains(command)) {
            tempValue = Double.parseDouble(display.getText());
            operator = command;
            isOperatorPressed = true;
            realTimeLabel.setText("Real-time Input: " + tempValue + " " + operator);
        } else if (command.equals("=")) {
            double currentValue = Double.parseDouble(display.getText());
            double result = 0;
            switch (operator) {
                case "+":
                    result = tempValue + currentValue;
                    break;
                case "-":
                    result = tempValue - currentValue;
                    break;
                case "*":
                    result = tempValue * currentValue;
                    break;
                case "/":
                    result = tempValue / currentValue;
                    break;
            }
            display.setText(String.valueOf(result));
            isOperatorPressed = true;
            realTimeLabel.setText("Real-time Input: " + result);
        } else if (command.equals("C")) {
            display.setText("");
            realTimeLabel.setText("Real-time Input: ");
        } else if (command.equals("+/-")) {
            double currentValue = Double.parseDouble(display.getText());
            display.setText(String.valueOf(currentValue * -1));
            realTimeLabel.setText("Real-time Input: " + display.getText());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KalkulatorGUI().setVisible(true);
            }
        });
    }
}
