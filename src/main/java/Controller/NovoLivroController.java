package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.Livro;
import Padroes.Dao;

public class NovoLivroController {
    
    @FXML
    private TextField campoDisponibilidade; 
    
    @FXML
    private TextField campoNome; 
    
    @FXML
    private TextField campoPrazo;  
    
    @FXML
    private TextField campoIsbn;
    
    @FXML
    private TextField campoEdicao;
    
    @FXML
    private TextField campoAno; 
    
    
    
    @FXML
    private void cadastrarLivro() {
        try {
            Livro livro = new Livro(
                Integer.parseInt(campoDisponibilidade.getText()),
                Integer.parseInt(campoPrazo.getText()),
                campoNome.getText(),
                campoIsbn.getText(),
                Integer.parseInt(campoEdicao.getText()),
                Integer.parseInt(campoAno.getText())
            );

            Dao<Livro> dao = new Dao<>(Livro.class);
            dao.inserir(livro);

            limparCampos();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Livro cadastrado com sucesso!");
            alert.show();

            voltarAoMenu();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao cadastrar livro: " + e.getMessage());
            alert.show();
        }
    }

    
    @FXML
    private void limparCampos(){
        campoDisponibilidade.setText("");
        campoNome.setText("");
        campoPrazo.setText("");
        campoIsbn.setText("");
        campoEdicao.setText("");
        campoAno.setText("");
    }
      
    @FXML
    private void voltarAoMenu() throws IOException{
        App.setRoot("menu");
    }
    
    
}



