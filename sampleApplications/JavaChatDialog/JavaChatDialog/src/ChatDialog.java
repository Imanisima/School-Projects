import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ChatDialog extends JDialog{
    private static JLabel label;
    private static JPanel northPane;
    private static JPanel southPane;
    private static JPanel chatBox;
    private static JButton connectBtn;
    private static JButton sendBtn;
    protected static JTextField textField;
    private static JTextField textField2;
    private final static String newline = "\n";

    protected static JTextArea textArea;
    private static JLabel displayText;
    private JLabel status;
    private static JScrollPane scrollPane;

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
            northPane = new JPanel();
            connectBtn = new JButton("Connect");

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


            /** Chat Area **/
            chatBox = new JPanel();
            textArea = new JTextArea(30, 30);
            textArea.setEditable(false);
            scrollPane = new JScrollPane(textArea);
            chatBox.add(scrollPane);
            add(chatBox, BorderLayout.CENTER);
            pack();



            /******* Botton Of Page *******/
            southPane = new JPanel();
            textField = new JTextField("Type something...",15);

            southPane.add(textField);
            southPane.add(new JButton(new AbstractAction("Send") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = e.getActionCommand();
                    if (connectBtn.getText().equals("Connect")) {
                        warn("Not Connected to a server!");
                    } else {

                        /* Display the message onto the screen */
                        String text = textField.getText();
                        textArea.append(text + newline);
                        textField.selectAll();
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                    }
                }
            }));

            add(southPane, BorderLayout.PAGE_END);
            pack();
    }


    /** Show the given warning or error message in a modal dialog. */
    private void warn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "JavaChat Dialog",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        ChatDialog dialog = new ChatDialog();
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}
