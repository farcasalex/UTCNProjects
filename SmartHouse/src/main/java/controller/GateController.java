package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GateController implements Initializable {

    @FXML
    private TextField text;

    private void switchGate() {
        if (text.getText().contains("opened")) text.setText("Gate closed");
        else text.setText("Gate opened");
    }

    /** From here starts the client-server implementation code **/

    private String serverAddress;
    private Scanner in;
    private PrintWriter out;
    private String name = null;

    public GateController(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    private GateController.ServerConnection serverConnection = new GateController.ServerConnection();
    private Thread thread = new Thread(serverConnection, "Server Connection");

    private class ServerConnection implements Runnable{
        private volatile boolean exit = false;

        public void run() {
            try {
                var socket = new Socket(serverAddress, 3300);
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                while(!exit){
                    while (in.hasNextLine()) {
                        var line = in.nextLine();
                        if (line.startsWith("SUBMITNAME")) {
                            // submit the name for verification
                            out.println("FrontGate");
                        } else if (line.startsWith("NAMEACCEPTED")) {
                            // receive the accepted name and store it locally
                            name = line.substring(13);
                            System.out.println("Name accepted : " + name);
                        } else if (line.startsWith("SWITCH")){
                            switchGate();
                        }
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Server connection terminated");
        }

        public void stop(){
            exit = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text.setText("Gate closed");
        try {
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
