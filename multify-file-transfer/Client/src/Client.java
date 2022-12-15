import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {

    public static int i=0;
    public static void main(String[] args) {

        // Accessed from within inner class needs to be final or effectively final.
        final File[] fileToSend = new File[10];

        // Set the frame to house everything.
        JFrame jFrame = new JFrame("BİL429's Client");
        // Set the size of the frame.
        jFrame.setSize(450, 450);
        // Make the layout to be box layout that places its children on top of each other.
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        // Make it so when the frame is closed the program exits successfully.
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title above panel.
        JLabel jlTitle = new JLabel("BİL429's File Sender");
        // Change the font family, size, and style.
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        // Add a border around the label for spacing.
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        // Make it so the title is centered horizontally.
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label that has the file name.
        JLabel jlFileName = new JLabel("Choose a file to send.");
        // Change the font.
        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
        // Make a border for spacing.
        jlFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        // Center the label on the x axis (horizontally).
        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel that contains the buttons.
        JPanel jpButton = new JPanel();
        // Border for panel that houses buttons.
        jpButton.setBorder(new EmptyBorder(75, 0, 10, 0));
        // Create send file button.
        JButton jbSendFile = new JButton("Send File");
        // Set preferred size works for layout containers.
        jbSendFile.setPreferredSize(new Dimension(150, 75));
        // Change the font style, type, and size for the button.
        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));
        // Make the second button to choose a file.
        JButton jbChooseFile = new JButton("Choose File");
        // Set the size which must be preferred size for within a container.
        jbChooseFile.setPreferredSize(new Dimension(150, 75));
        // Set the font for the button.
        jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));

        // Add the buttons to the panel.
        jpButton.add(jbSendFile);
        jpButton.add(jbChooseFile);
        jlFileName.setText("The file you want to send is: ");



        //PASSWORD FRAME

        // Set the frame to house everything.
        JFrame password = new JFrame("password");
        // Set the size of the frame.
        password.setSize(450, 450);
        // Make the layout to be box layout that places its children on top of each other.
        password.setLayout(new BoxLayout(password.getContentPane(), BoxLayout.Y_AXIS));
        // Make it so when the frame is closed the program exits successfully.
        password.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title above panel.
        JLabel jlPasswordTitle = new JLabel("password");
        // Change the font family, size, and style.
        jlPasswordTitle.setFont(new Font("Arial", Font.BOLD, 25));
        // Add a border around the label for spacing.
        jlPasswordTitle.setBorder(new EmptyBorder(20,0,10,0));
        // Make it so the title is centered horizontally.
        jlPasswordTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        password.add(jlPasswordTitle);

        // Label that has the file name.
        JLabel jlPasswordName = new JLabel("Please set a password for uploading.");
        // Change the font.
        jlPasswordName.setFont(new Font("Arial", Font.BOLD, 20));
        // Make a border for spacing.
        jlPasswordName.setBorder(new EmptyBorder(50, 0, 0, 0));
        // Center the label on the x axis (horizontally).
        jlPasswordName.setAlignmentX(Component.CENTER_ALIGNMENT);

        password.add(jlPasswordName);

        // Panel that contains the buttons.
        JPanel jpPasswordButton = new JPanel();
        // Border for panel that houses buttons.
        jpPasswordButton.setBorder(new EmptyBorder(75, 0, 10, 0));
        // Create set password button.
        JButton jbPassword = new JButton("Set Password");
        // Set preferred size works for layout containers.
        jbPassword.setPreferredSize(new Dimension(150, 75));
        // Change the font style, type, and size for the button.
        jbPassword.setFont(new Font("Arial", Font.BOLD, 20));

        // Panel that contains the buttons.
        JPanel jpPasswordField = new JPanel();
        // Border for panel that houses buttons.
        jpPasswordField.setBorder(new EmptyBorder(75, 0, 10, 0));
        // Create password field
        JPasswordField jPasswordField = new JPasswordField();
        // Set preferred size works for layout containers.
        jPasswordField.setPreferredSize(new Dimension(250,50));
        // Set background of field
        jPasswordField.setBackground(new Color(180,180,180));




        jpPasswordButton.add(jbPassword);
        jpPasswordField.add(jPasswordField);
        password.add(jpPasswordField);
        password.add(jpPasswordButton);



        // Button action for choosing the file.
        // This is an inner class so we need the fileToSend to be final.
        jbChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser to open the dialog to choose a file.
                JFileChooser jFileChooser = new JFileChooser();
                // Set the title of the dialog.
                jFileChooser.setDialogTitle("Choose file(s) to send.");
                // Set multiple file choose
                jFileChooser.setMultiSelectionEnabled(true);
                // Show the dialog and if a file is chosen from the file chooser execute the following statements.

                    if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        jFrame.setVisible(false);
                        password.setVisible(true);

                        // Get the selected file
                        fileToSend[i] = jFileChooser.getSelectedFile();
                        // Change the text of the java swing label to have the file name.
                        jlFileName.setText(jlFileName.getText()+ fileToSend[i].getName()+" ");
                        i++;

                    }
                }

        });


        // Sends the file when the button is clicked.
        jbSendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If a file has not yet been selected then display this message.
                if (fileToSend[0] == null) {
                    jlFileName.setText("Please choose a file to send first!");
                    // If a file has been selected then do the following.
                } else {
                    try {
                        for(int a=0;a<i;a++) {
                            // Create an input stream into the file you want to send.
                            FileInputStream fileInputStream = new FileInputStream(fileToSend[a].getAbsolutePath());
                            // Create a socket connection to connect with the server.
                            Socket socket = new Socket("localhost", 1234);
                            Socket socket1 = new Socket("localhost", 1235);
                            // Create an output stream to write to the server over the socket connection.
                            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            //----
                            // Get the password you want to send and store it in password.
                            DataOutputStream dataOutputStream1 = new DataOutputStream(socket1.getOutputStream());
                           var pass = jPasswordField.getPassword();
                            String passwordN = String.valueOf(pass);

                            // Convert the name of the password into an array of bytes to be sent to the server.
                            byte[] passwordNBytes = passwordN.getBytes();
                            // Create a byte array the size of the password so don't send too little or too much data to the server.
                            byte[] passwordBytes = new byte[(int) passwordN.length()];
                            // Send the length of the name of the password so server knows when to stop reading.
                            dataOutputStream1.writeInt(passwordNBytes.length);
                            // Send the PASSWORD.
                            dataOutputStream1.write(passwordNBytes);
                            // Send the length of the byte array so the server knows when to stop reading.
                            dataOutputStream1.writeInt(passwordBytes.length);
                            // Send the actual file.
                            dataOutputStream1.write(passwordBytes);
                            //----
                            // Get the name of the file you want to send and store it in filename.
                            String fileName = fileToSend[a].getName();
                            // Convert the name of the file into an array of bytes to be sent to the server.
                            byte[] fileNameBytes = fileName.getBytes();
                            // Create a byte array the size of the file so don't send too little or too much data to the server.
                            byte[] fileBytes = new byte[(int) fileToSend[a].length()];
                            // Put the contents of the file into the array of bytes to be sent so these bytes can be sent to the server.
                            fileInputStream.read(fileBytes);
                            // Send the length of the name of the file so server knows when to stop reading.
                            dataOutputStream.writeInt(fileNameBytes.length);
                            // Send the file name.
                            dataOutputStream.write(fileNameBytes);
                            // Send the length of the byte array so the server knows when to stop reading.
                            dataOutputStream.writeInt(fileBytes.length);
                            // Send the actual file.
                            dataOutputStream.write(fileBytes);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    i=0;
                    jlFileName.setText("The file you want to send is: ");
                }
            }
        });

        jbPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                var text = jPasswordField.getPassword();
                String pass = String.valueOf(text);
                System.out.print(pass);

                if(pass.equals(null)){

                    JWindow window = new JWindow();
                    // Set the size of the frame.
                    window.setSize(450, 450);
                    // Make the layout to be box layout that places its children on top of each other.
                    window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

                    // Title above panel.
                    JLabel jlwindowTitle = new JLabel("password");
                    // Change the font family, size, and style.
                    jlwindowTitle.setFont(new Font("Arial", Font.BOLD, 25));
                    // Add a border around the label for spacing.
                    jlwindowTitle.setBorder(new EmptyBorder(20,0,10,0));
                    // Make it so the title is centered horizontally.
                    jlwindowTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                    window.add(jlwindowTitle);

                    // Label that has the file name.
                    JLabel jlwindowName = new JLabel("Please set a password for uploading!");
                    // Change the font.
                    jlwindowName.setFont(new Font("Arial", Font.BOLD, 20));
                    // Make a border for spacing.
                    jlwindowName.setBorder(new EmptyBorder(50, 0, 0, 0));
                    // Center the label on the x axis (horizontally).
                    jlwindowName.setAlignmentX(Component.CENTER_ALIGNMENT);

                    window.add(jlwindowName);

                    // Panel that contains the buttons.
                    JPanel jpwindowButton = new JPanel();
                    // Border for panel that houses buttons.
                    jpwindowButton.setBorder(new EmptyBorder(75, 0, 10, 0));
                    // Create set password button.
                    JButton jbwindow = new JButton("Set Password");
                    // Set preferred size works for layout containers.
                    jbwindow.setPreferredSize(new Dimension(150, 75));
                    // Change the font style, type, and size for the button.
                    jbwindow.setFont(new Font("Arial", Font.BOLD, 20));

                    jpwindowButton.add(jbwindow);
                    window.add(jpwindowButton);

                    window.setVisible(true);

                    jbwindow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            window.setVisible(false);
                        }
                    });



                }
                else if (!pass.equals(null)){
                    jFrame.setVisible(true);
                    password.setVisible(false);

                }
            }
        });

        // Add everything to the frame and make it visible.
        jFrame.add(jlTitle);
        jFrame.add(jlFileName);
        jFrame.add(jpButton);
        jFrame.setVisible(true);
    }

}
