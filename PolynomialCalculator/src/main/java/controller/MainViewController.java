// Created by Farcas Alexandru
// Group 30221
// UTCN 2019
package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Polynome;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    Polynome firstPolynome, secondPolynome = null;

    @FXML
    private TextField input;

    @FXML
    private TextArea display;

    @FXML
    private Button add;

    @FXML
    private Button substract;

    @FXML
    private Button multiply;

    @FXML
    private Button divide;

    @FXML
    private Button derive;

    @FXML
    private Button integrate;

    @FXML
    private Button submit;

    @FXML
    private Button clear;

    @FXML
    void addAction(ActionEvent event) {
        try {
            if (input.getText().equals(""))
                throw new Exception("Empty input string!");
            secondPolynome = new Polynome(input.getText());
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            firstPolynome.addPolynome(secondPolynome);
            display.setText(display.getText() + "\n" + secondPolynome.printPolynome() + "\n"
                    + ".......................................................................................................  +"
                    + "\n" + firstPolynome.printPolynome() );
            add.setDisable(true);
            substract.setDisable(true);
            multiply.setDisable(true);
            divide.setDisable(true);
            submit.setDisable(false);
            derive.setDisable(false);
            integrate.setDisable(false);
            input.setText("");
        } catch(Exception e) {
            input.setStyle("-fx-border-color:red red red red;" +  "-fx-text-fill: red");
        }
    }

    @FXML
    void substractAction(ActionEvent event) {
        try {
            if (input.getText().equals(""))
                throw new Exception("Empty input string!");
            secondPolynome = new Polynome(input.getText());
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            firstPolynome.substractPolynome(secondPolynome);
            display.setText(display.getText() + "\n" + secondPolynome.printPolynome() + "\n"
                    + ".......................................................................................................  -"
                    + "\n" + firstPolynome.printPolynome() );
            add.setDisable(true);
            substract.setDisable(true);
            multiply.setDisable(true);
            divide.setDisable(true);
            submit.setDisable(false);
            derive.setDisable(false);
            integrate.setDisable(false);
            input.setText("");
        } catch(Exception e) {
            input.setStyle("-fx-border-color:red red red red;" +  "-fx-text-fill: red");
        }
    }

    @FXML
    void deriveAction(ActionEvent event) {
        try {
            if (input.getText().equals(""))
                throw new Exception("Empty input string!");
            firstPolynome = new Polynome(input.getText());
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            display.setText("(" + firstPolynome.printPolynome() + ")'" + "\n");
            firstPolynome.derivePolynome();
            display.setText(display.getText() + "= " + firstPolynome.printPolynome());
            input.setText("");
        } catch (Exception e) {
            input.setStyle("-fx-border-color:red red red red;" +  "-fx-text-fill: red");
        }
    }

    @FXML
    void integrateAction(ActionEvent event) {
        try {
            if (input.getText().equals(""))
                throw new Exception("Empty input string!");
            firstPolynome = new Polynome(input.getText());
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            display.setText("∫(" + firstPolynome.printPolynome() + ")dx" + "\n");
            firstPolynome.integratePolynome();
            display.setText(display.getText() + "= " + firstPolynome.printPolynome() + "+C");
            input.setText("");
        } catch (Exception e) {
            input.setStyle("-fx-border-color:red red red red;" +  "-fx-text-fill: red");
        }
    }

    @FXML
    void multiplyAction(ActionEvent event) {
        try {
            if (input.getText().equals(""))
                throw new Exception("Empty input string!");
            secondPolynome = new Polynome(input.getText());
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            firstPolynome.multiplyPolynome(secondPolynome);
            display.setText(display.getText() + "\n" + secondPolynome.printPolynome() + "\n"
                    + ".......................................................................................................  *"
                    + "\n" + firstPolynome.printPolynome() );
            add.setDisable(true);
            substract.setDisable(true);
            multiply.setDisable(true);
            divide.setDisable(true);
            submit.setDisable(false);
            derive.setDisable(false);
            integrate.setDisable(false);
            input.setText("");
        } catch(Exception e) {
            input.setStyle("-fx-border-color:red red red red;" +  "-fx-text-fill: red");
        }
    }

    @FXML
    void divideAction(ActionEvent event) {
        try {
            if (input.getText().equals(""))
                throw new Exception("Empty input string!");
            Polynome rest;
            secondPolynome = new Polynome(input.getText());
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            rest = firstPolynome.dividePolynome(secondPolynome);
            if (!rest.printPolynome().equals("0")){
                display.setText(display.getText() + "\n" + secondPolynome.printPolynome() + "\n"
                        + ".......................................................................................................  :"
                        + "\n" + firstPolynome.printPolynome() + "+(" + rest.printPolynome() + ")/" + secondPolynome.printPolynome());
            }
            else{
                display.setText(display.getText() + "\n" + secondPolynome.printPolynome() + "\n"
                        + ".......................................................................................................  :"
                        + "\n" + firstPolynome.printPolynome());
            }
            add.setDisable(true);
            substract.setDisable(true);
            multiply.setDisable(true);
            divide.setDisable(true);
            submit.setDisable(false);
            derive.setDisable(false);
            integrate.setDisable(false);
            input.setText("");
        } catch(Exception e) {
            input.setStyle("-fx-border-color:red red red red;" +  "-fx-text-fill: red");
        }
    }

    @FXML
    void submitAction(ActionEvent event) {
        try {
            if (input.getText().equals(""))
                throw new Exception("Empty input string!");
            firstPolynome = new Polynome(input.getText());
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            display.setText(firstPolynome.printPolynome());
            add.setDisable(false);
            substract.setDisable(false);
            multiply.setDisable(false);
            divide.setDisable(false);
            submit.setDisable(true);
            derive.setDisable(true);
            integrate.setDisable(true);
            input.setText("");
        } catch (Exception e) {
            input.setStyle("-fx-border-color:red red red red;" +  "-fx-text-fill: red");
        }
    }

    @FXML
    void clearAction(ActionEvent event) {
        try {
            firstPolynome = secondPolynome = null;
            display.setText("");
            input.setText("");
            input.setStyle("-fx-border-color:#bbbbbb #bbbbbb #bbbbbb #bbbbbb;" + "-fx-text-fill: #bbbbbb;");
            add.setDisable(true);
            substract.setDisable(true);
            multiply.setDisable(true);
            divide.setDisable(true);
            submit.setDisable(false);
            derive.setDisable(false);
            integrate.setDisable(false);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        display.setMouseTransparent(true);
        add.setDisable(true);
        substract.setDisable(true);
        multiply.setDisable(true);
        divide.setDisable(true);
    }
}
