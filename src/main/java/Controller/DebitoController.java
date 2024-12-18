package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.Aluno;
import model.Debito;
import Padroes.Dao;

public class DebitoController {

    @FXML
    private ChoiceBox<Aluno> boxAluno;

    @FXML
    private DatePicker pickerData;

    @FXML
    private TextField campoValor;

    private ObservableList<Aluno> listaAluno;
    private Dao<Debito> debitoDao;

    @FXML
    private void initialize() {
      
        Dao<Aluno> alunoDao = new Dao<>(Aluno.class);

       
        List<Aluno> alunos = alunoDao.listarTodos();
        listaAluno = FXCollections.observableArrayList(alunos);
        if (!listaAluno.isEmpty()) {
            boxAluno.setItems(listaAluno);
            boxAluno.setValue(listaAluno.get(0)); 
        }

        
        boxAluno.setConverter(new StringConverter<Aluno>() {
            @Override
            public String toString(Aluno aluno) {
                if (aluno != null) {
                    return aluno.getRA() + " - " + aluno.getNome(); 
                }
                return "";
            }

            @Override
            public Aluno fromString(String string) {
                return null; 
            }
        });

        
        pickerData.setValue(LocalDate.now());

        
        debitoDao = new Dao<>(Debito.class);
    }

    @FXML
    private void atribuirDebito() {
        
        Aluno alunoSelecionado = boxAluno.getValue();
        LocalDate data = pickerData.getValue();
        double valor;

        try {
            valor = Double.parseDouble(campoValor.getText());
        } catch (NumberFormatException e) {
            exibirAlertaErro("Erro", "Por favor, insira um valor válido.");
            return;
        }


       
        Debito debito = new Debito(alunoSelecionado, data, valor);
        debitoDao.inserir(debito);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("");
        alert.setContentText("Débito atribuído com sucesso.");
        alert.show();

        try {
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menu() throws IOException {
        App.setRoot("menu");
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
