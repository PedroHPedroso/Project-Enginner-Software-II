package Controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;



public class Menu1Controller {
    @FXML
    private void cadastrarLivro() throws IOException {
       App.setRoot("cadastrarLivro");
    }
    
     @FXML
    private void emprestimo() throws IOException {
       App.setRoot("emprestimo");
    }
    
    @FXML
    private void cadastrarAluno() throws IOException {
       App.setRoot("cadastrarAluno");
    }
    
    @FXML
    private void debito() throws IOException {
       App.setRoot("debito");
    }
    
    @FXML
    private void registrarDevolucao() throws IOException {
        App.setRoot("devolucao");
    }
    
    @FXML
    private void sair(){
        Platform.exit();
        System.exit(0);
    }
    
}

