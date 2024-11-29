import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel implements ActionListener {
    private final JTextField username = new JTextField();
    private final JPasswordField password = new JPasswordField();

    private final JLabel usernameLabel = new JLabel("Username");
    private final JLabel passwordLabel = new JLabel("Password");

    public Login() {
        setLayout( new GridLayout(2, 2) );
        add( usernameLabel );
        add(username);
        add( passwordLabel );
        add(password);
    }
}

