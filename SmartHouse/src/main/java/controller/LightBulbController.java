package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.UUID;

public class LightBulbController implements Initializable {

    private final String id = UUID.randomUUID().toString();

    @FXML
    private ImageView off;

    @FXML
    private ImageView on;

    @FXML
    void turnOnOff() {
        if (on.isVisible()) {
            on.setVisible(false);
            off.setVisible(true);
        } else {
            on.setVisible(true);
            off.setVisible(false);
        }
    }

    /** From here starts the client-server implementation code **/

    private String serverAddress;
    private Scanner in;
    private PrintWriter out;
    private String name = null;

    public LightBulbController(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    private LightBulbController.ServerConnection serverConnection = new LightBulbController.ServerConnection();
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
                            // submit name for verification and increment the id
                            out.println("LightBulb_" + id);
                        } else if (line.startsWith("NAMEACCEPTED")) {
                            // receive the accepted name and store it locally
                            name = line.substring(13);
                            System.out.println("Name accepted : " + name);
                        } else if (line.startsWith("SWITCHLIGHT")){
                            turnOnOff();
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
        on.setVisible(false);
        try {
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}