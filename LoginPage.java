import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage {
    private final JFrame frame;
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginPage() {
        frame = new JFrame("Login - Equipment Tracker");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null); // center the window

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        frame.add(userLabel, gbc);
        gbc.gridx = 1;
        frame.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        frame.add(passLabel, gbc);
        gbc.gridx = 1;
        frame.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        frame.add(loginButton, gbc);

        loginButton.addActionListener(this::handleLogin);

        frame.setVisible(true);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("aat") && password.equals("password")) {
            frame.dispose(); // close login window
            new MaintenanceTrackerUI().createUI(); // launch main app
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
