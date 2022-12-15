# multify-files-transfer
Multify  files upload and download with password control system.
There are server and client sides for sending files and downloading files.
It is a local system that sockets used in.
Developers can change port number depend on the state.

After running the application, it is necessary to interact with Client.java. The file(s) are selected with the "Choose" button in the "jFrame" Frame.
After selecting the files to be sent with the "password" Frame created in the client class of the application, the password is entered for the file(s) uploaded by the user. The "password" Frame properties are defined in Client.java. After entering the password for the files uploaded by the user, the Action Listener, which is defined for the button on the frame screen, checks whether the password is entered in the password field. If the password is null, a warning is made for password entry. If the password is entered, "password frame is closing.
With the Action Listener defined to the Send button in the "jFrame" Frame, 2 Sockets are opened to send the "fileName", file content and password and the port numbers are determined. Moreover :
- Path is copied for file path transmission with FileInputStream.
- DataOutputStream and getOutputStream for information to be transmitted to Server.java
is being used.
- Different sockets are used to send the password.
- Thanks to these, the file(s) and password are transmitted to Server.java.

In the Server section of the application, the sent files are displayed and the files can be downloaded. In Server.java, connections are established with the sockets opened in Client.java with the ".accept()" function. Password and file names are converted to String type because they are transmitted in Array type during transmission. Server.java is shown on the Server screen after the sent files are received. MouseListener is defined to the panel to select the file. For the selected file, the user is given a choice on the buttons with the “jfPreview” Frame to download the file or not. The frame is closed by selecting the “No” button. The password is checked first by selecting the “Yes” button. Frame properties of password control screen are determined in “createPasswordFrame”. A passwordField is defined for the user to enter the password. A button has been added to check if the password is correct. Password entered by the ActionListener user defined for the button:
- If it is Null or not, a warning is given to enter the password if it is Null.
- If the password is correct or not, a warning is given to enter the correct password. - If the password is correct, the password control screen is closed.
At this point, the ActionListener defined for the "Yes" button is returned and the file(s) are downloaded.
