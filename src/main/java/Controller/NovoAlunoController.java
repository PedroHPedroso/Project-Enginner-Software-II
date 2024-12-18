/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.Aluno;
import Padroes.Dao;

public class NovoAlunoController {
    
    @FXML
    private TextField campoRA;
    
    @FXML
    private TextField campoNome;
    
    @FXML
    private TextField campoCPF;
    
    @FXML
    private TextField campoIdade;
    
    @FXML
    private void cadastrarAluno(){
        Aluno aluno = new Aluno(
         Integer.parseInt(campoRA.getText()), 
        campoNome.getText(), 
         campoCPF.getText(), 
       Integer.parseInt(campoIdade.getText())
    );

        Dao<Aluno> dao = new Dao(Aluno.class); 
        dao.inserir(aluno);
        clear();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("aluno cadastrado");
        alert.show();
        
        try {
            menu();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        
    }
    
    @FXML
    private void clear(){
        campoRA.setText("");
        campoNome.setText("");
        campoCPF.setText("");
        campoIdade.setText("");
    }
    
    @FXML
    private void menu() throws IOException{
        App.setRoot("menu");
    }
}
