import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ChatDialog extends JDialog {
    private static JPanel northPane;
    private static JPanel southPane;
    private static JPanel chatBox;
    private static JButton connectBtn;
    private static JButton sendBtn;

    protected static JTextField textField;
    private static JTextField textField2;
    
    private final static String newline = "\n";
    protected static JTextArea textArea;
    
    private static JScrollPane scrollPane;

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_DIMENSION = new Dimension(400, 420);

    /** Create a main dialog. */
    public ChatDialog() {
        this(DEFAULT_DIMENSION);
    }


    /** Create a main dialog of the given dimension. */
    public ChatDialog(Dimension dim) {
        super((JFrame) null, "JavaChat");
        configureGui();
        setSize(dim);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /** Configure GUI of this dialog. */
    private void configureGui() {
            setLayout(new BorderLayout());
            setBackground(new Color(196,190,7));

            /****** Top Of Page *******/
            northPane = new JPanel();
            connectBtn = new JButton("Connect");
            connectBtn.setToolTipText("Press to connect to network!");

            // Change button text when clicked
            connectBtn.addActionListener(event -> {
                String s = event.getActionCommand();
                if(s.equals("Connect")){
                    connectBtn.setLabel("Disconnect");
                    connectBtn.setToolTipText("Press to disconnect from the network!");
                    setBackground(new Color(196,190,7));
                }
                else if(s.equals("Disconnect")){
                    connectBtn.setLabel("Connect");
                    setBackground(Color.RED);
                }
            });

            textField = new JTextField("Server", 15);
            focusListener(textField);

            textField2 = new JTextField("Port", 4);
            focusListener(textField2);

            northPane.add(connectBtn);
            northPane.add(textField);
            northPane.add(textField2);

            add(northPane, BorderLayout.PAGE_START);
            pack();


            /** Chat Area **/
            chatBox = new JPanel();
            textArea = new JTextArea(30, 30);
            textArea.setBorder(BorderFactory.createLineBorder(Color.black));
            textArea.setLineWrap(true);
            textArea.setEditable(false);

            scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(350, 300));
            chatBox.add(scrollPane, BorderLayout.CENTER);

            add(chatBox, BorderLayout.CENTER);
            pack();


            /******* Botton Of Page *******/
            southPane = new JPanel();

            textField = new JTextField(15);
            textField = new JTextField("Type something...",15);
            focusListener(textField);
            southPane.add(textField);

            sendBtn = new JButton("Send");
            sendBtn.setToolTipText("Press to send a message!");
            southPane.add(new JButton(new AbstractAction("Send") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = e.getActionCommand();
                    if (connectBtn.getText().equals("Connect")) {
                        warn("Not connected to a server!");
                        setBackground(Color.RED);

                    } else {
                        /* Display the message onto the screen */
                        String text = textField.getText();
                        textArea.setForeground(Color.BLUE);
                        textArea.append(">>: " + text + newline);
                        textField.selectAll();
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                        textField.setText("");
                    }
                }
            }));

            add(southPane, BorderLayout.PAGE_END);
            pack();
    }


    /** Show the given warning or error message in a modal dialog. */
    private void warn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "JavaChat Dialog",
                JOptionPane.ERROR_MESSAGE);
    }

    /* when field is clicked on, text will disappear */
    public void focusListener(JTextField textField){
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField src = (JTextField)e.getComponent();
                src.setText("");
                src.removeFocusListener(this);
            }
        });
    }

    public static void main(String[] args) {
        ChatDialog dialog = new ChatDialog();
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}
