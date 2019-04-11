import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class ChatDialog extends JDialog{
    private static JLabel label;
    private static JTextField textField;
    private static JTextField textField2;

    private static JTextArea textArea;
    private static JLabel displayText;
    private JLabel status;
    private static JScrollPane scrollPanel;

    private static JFrame frame;


    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_DIMENSION = new Dimension(400, 600);

    /** Create a main dialog. */
    public ChatDialog() {
        this(DEFAULT_DIMENSION);
    }


    /** Create a main dialog of the given dimension. */
    public ChatDialog(Dimension dim) {
        super((JFrame) null, "JavaChat");
        configureGui();
        setSize(dim);
        //setResizable(false);
//        addComponentListener(new componentListener(400, 300));
        setLocationRelativeTo(null);
    }

    /** Configure GUI of this dialog. */
    private void configureGui() {
        //-- WRITE YOUR CODE HERE
            setLayout(new BorderLayout());

            /****** Top Of Page *******/
            JPanel northPane = new JPanel();
            JButton connectBtn = new JButton("Connect");

            // Change button text when clicked
            connectBtn.addActionListener(event -> {
                String s = event.getActionCommand();
                if(s.equals("Connect")){
                    //warn("Not connected to a server!");
                    connectBtn.setLabel("Disconnect");
                }
                else{
                    connectBtn.setLabel("Connect");
                    // warn("Not connected to a server!");
                }
            });

            textField = new JTextField("Server", 15);
            textField2 = new JTextField("Port", 4);

            northPane.add(connectBtn);
            northPane.add(textField);
            northPane.add(textField2);
            add(northPane, BorderLayout.PAGE_START);
            pack();



            /******* Botton Of Page *******/
            JPanel southPane = new JPanel();
            textField = new JTextField("Enter a message...",15);

            /* Display Warning Message If Not Connected */
            JButton sendBtn = new JButton("Send");
            southPane.add(textField);

            southPane.add(new JButton(new AbstractAction("Send") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = e.getActionCommand();
                    if(s.equals("Send") && connectBtn.getText().equals("Connect")) {
                        warn("Not Connected to a server!");
                    }
                }
            }));
            add(southPane, BorderLayout.PAGE_END);
            pack();
    }


    /** Show the given warning or error message in a modal dialog. */
    private void warn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "JavaChat",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        ChatDialog dialog = new ChatDialog();
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}