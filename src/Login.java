import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Login extends JPanel implements ActionListener {
    private final JTextField usernameField;
    private final JTextField databaseField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JLabel titleLabel;
    private final JLabel usernameLabel;
    private final JLabel databaseLabel;
    private final JLabel passwordLabel;
    private final JPanel formPanel;
    
    public Login() {
        // Set overall layout
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title
        titleLabel = new JLabel("Java Admin", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Username Label and Field
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(usernameField, gbc);

        // Database Label and Field
        databaseLabel = new JLabel("Database:");
        databaseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(databaseLabel, gbc);

        databaseField = new JTextField(20);
        databaseField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(databaseField, gbc);

        // Password Label and Field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Sign In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.GRAY);
        loginButton.setForeground(new Color(70,70,70));
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        formPanel.add(loginButton, gbc);

        // Add action listener
        loginButton.addActionListener(this::actionPerformed);

        // Add form panel to main panel
        add(formPanel, BorderLayout.CENTER);
    }

    public void clear() {
        usernameField.setText("");
        passwordField.setText("");
        databaseField.setText("");
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getDatabaseName() {
        return databaseField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get login credentials
        String username = getUsername();
        String password = getPassword();
        String database = getDatabaseName();

        try {
            // Use Connect class to check connection
            boolean connected = Connect.isConnected(database, username, password);
            
            if (connected) {
                JOptionPane.showMessageDialog(this, 
                    "Login Successful!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Here you can open the main application window
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Login Failed", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Connection Error: " + Connect.getErrorType(ex), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Form");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Login());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}