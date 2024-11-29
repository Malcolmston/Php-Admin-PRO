import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel implements ActionListener {
    private final JTextField username = new JTextField();
    private final JPasswordField password = new JPasswordField();
    private final JButton loginButton = new JButton("Login");

    private final JLabel usernameLabel = new JLabel("Username");
    private final JLabel passwordLabel = new JLabel("Password");

    private final JPanel logiPanel = new JPanel();

    public Login() {
        logiPanel.setLayout( new GridLayout(2, 2) );
        logiPanel.add( usernameLabel );
        logiPanel.add(username);
        logiPanel.add( passwordLabel );
        logiPanel.add(password);

        add(logiPanel);

        add(loginButton);
        loginButton.addActionListener(this::actionPerformed);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public void clear() {
        username.setText("");
        password.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
    }
}

