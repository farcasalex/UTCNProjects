package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;

public class ServerWindowController implements Initializable {

    public static final String PASSWORD = "avion2020";

    @FXML
    private TextArea devices;

    @FXML
    private Button lightBulb;

    @FXML
    private TextArea logView;

    @FXML
    private Button gate;

    @FXML
    private TextArea memo;

    @FXML
    private TextField time;

    @FXML
    private PasswordField password;

    @FXML
    void gateAction(ActionEvent event) {
        command = "gate";
    }

    @FXML
    void lightBulbAction(ActionEvent event) {
        command = "light";
    }

    /** From here starts the client-server implementation code **/

    // Used as a signal to control the connected devices based on user input
    private static volatile String command = "";

    // Store all unique devices names that have been accepted by the user
    private static Set<String> acceptedNames = new HashSet<>();

    // Store all unique devices names that are currently connected
    private static Set<String> connectedNames = new HashSet<>();

    // The set of all the print writers for all connected devices
    private static Set<PrintWriter> writeAll = new HashSet<>();

    // The set of all the light bulb writers for all connected light bulb devices
    private static Set<PrintWriter> writeLightBulbs = new HashSet<>();

    // Store the gates writer
    private static PrintWriter writeGate;

    private static class Handler implements Runnable {

        private String name;
        private Socket socket;
        private Scanner in;
        private PrintWriter out;
        private TextArea devices;
        private TextArea logView;
        private TextArea memo;
        private Button lightBulb;
        private Button gate;
        private PasswordField password;

        public Handler(Socket socket, TextArea devices, TextArea logView, TextArea memo, Button lightBulb, Button gate, PasswordField password) {
            this.socket = socket;
            this.devices = devices;
            this.logView = logView;
            this.memo = memo;
            this.lightBulb = lightBulb;
            this.gate = gate;
            this.password = password;
        }

        @Override
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                // Continue to request for a name until a unique one has been received
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.nextLine();
                    if (name == null) {
                        return;
                    }

                    // TODO: ask user to accept or deny the new device
                    acceptedNames.add(name);

                    synchronized (acceptedNames) {
                        if (!name.isBlank() && acceptedNames.contains(name) && !connectedNames.contains(name)) {
                            connectedNames.add(name);
                            break;
                        }
                    }
                }

                // validate the connection by sending the accepted name to the device
                out.println("NAMEACCEPTED " + name);
                System.out.println(name + " connected");
                devices.setText(devices.getText() + name + " connected");

                // add the socket's print writer to the set of all writers so this client can receive broadcast messages
                writeAll.add(out);

                // if he device is a light bulb, add the socket's print writer to the set of light bulb writers
                if (name.contains("LightBulb")){
                    writeLightBulbs.add(out);
                }

                // if he device is a gate, store the socket's print writer
                if (name.contains("FrontGate")){
                    writeGate = out;
                }

                // keep processing the user generated signals
                while (true){
                    synchronized (command){
                        synchronized (logView){
                            switch (command){
                                case "light":  {
                                    logView.setText("Switching lights...\n" + logView.getText());
                                    for (PrintWriter writer:ServerWindowController.writeLightBulbs) {
                                        writer.println("SWITCHLIGHT");
                                    }
                                    logView.setText("All lights were successfully switched\n" + logView.getText());
                                    command = "";
                                    break;
                                }
                                case "gate":  {
                                    if (!password.getText().equals("")){
                                        if (password.getText().equals(PASSWORD)){
                                            logView.setText("Switching the front gate...\nAccess granted\n" + logView.getText());
                                            writeGate.println("SWITCH");
                                            logView.setText("Gate switch signal successfully sent\n" + logView.getText());
                                            password.setText("");
                                        }else{
                                            logView.setText("Access denied. Wrong password!\n" + logView.getText());
                                        }
                                    }else{
                                        logView.setText("Enter password first!\n" + logView.getText());
                                    }
                                    command = "";
                                    break;
                                }
                                default: break;
                            }
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    writeAll.remove(out);
                    writeLightBulbs.remove(out);
                }
                if (name != null) {
                    connectedNames.remove(name);
                }
                try {
                    socket.close();
                    System.out.println(name + " disconnected");
                    devices.setText(devices.getText() + "\n" + name + " disconnected");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private ServerWindowController.ClientsHandler serverConnection = new ServerWindowController.ClientsHandler();
    private Thread thread = new Thread(serverConnection, "Clients Handler");

    private class ClientsHandler implements Runnable{
        private volatile boolean exit = false;

        public void run() {
            System.out.println("The server is running...");
            var pool = Executors.newFixedThreadPool(500);
            try (var listener = new ServerSocket(3300)) {
                while (true) {
                    pool.execute(new ServerWindowController.Handler(listener.accept(), devices, logView, memo, lightBulb, gate, password));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void stop(){
            exit = true;
        }
    }

    private Thread setTime = new Thread(() -> {
        while (true){
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            if (!time.getText().equals(formatter.format(date))) time.setText(formatter.format(date));
        }
    });

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        password.setText("");
        memo.setWrapText(true);
        memo.setText(memo.getText() + "\n\t⭐ don't forget to buy more milk" +
                                      "\n\t⭐ business meeting at 5 pm on friday");
        try {
            thread.start();
            setTime.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
