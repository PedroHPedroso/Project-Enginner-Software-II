package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Emprestimo;
import model.ItemEmprestimo;
import model.Livro;
import Padroes.Dao;

public class DevolucaoController {

    @FXML
    private TextField campoCodigoEmprestimo;

    @FXML
    private TextField campoCodigoLivro;

    @FXML
    private Label labelMensagem;

    private final Dao<Emprestimo> emprestimoDao = new Dao<>(Emprestimo.class);
    private final Dao<ItemEmprestimo> itemEmprestimoDao = new Dao<>(ItemEmprestimo.class);
    private final Dao<Livro> livroDao = new Dao<>(Livro.class);

    @FXML
private void registrarDevolucao() {
    String codigoLivro = campoCodigoLivro.getText();

    if (codigoLivro.isEmpty()) {
        exibirMensagemErro("Por favor, insira o código do livro.");
        return;
    }

    try {
        Livro livro = livroDao.buscarPorCampo("codigo", Integer.parseInt(codigoLivro));

        if (livro == null) {
            exibirMensagemErro("Livro não encontrado.");
            return;
        }

        List<ItemEmprestimo> itensEmprestimo = itemEmprestimoDao.buscarPorCampos("livro.codigo", livro.getCodigo());

        if (itensEmprestimo == null || itensEmprestimo.isEmpty()) {
            exibirMensagemErro("O livro informado não está associado a nenhum empréstimo.");
            return;
        }

        ItemEmprestimo itemPendente = null;
        for (ItemEmprestimo item : itensEmprestimo) {
            if (item.getDataDevolucao() == null) {
                itemPendente = item;
                break;
            }
        }

        if (itemPendente == null) {
            exibirMensagemErro("O livro já foi devolvido.");
            return;
        }

        itemPendente.setDataDevolucao(LocalDate.now());
        itemEmprestimoDao.alterar(itemPendente);

        livro.setDisponivel(1);
        livroDao.alterar(livro);

        exibirMensagemSucesso("Devolução registrada com sucesso.");
        limparCampos();

    } catch (NumberFormatException e) {
        exibirMensagemErro("O código do livro deve ser um número.");
    } catch (Exception e) {
        exibirMensagemErro("Erro ao registrar devolução. Tente novamente.");
        e.printStackTrace();
    }
}


    @FXML
    private void limparCampos() {
        campoCodigoEmprestimo.clear();
        campoCodigoLivro.clear();
        labelMensagem.setText("");
    }

    private void exibirMensagemErro(String mensagem) {
        labelMensagem.setText(mensagem);
        labelMensagem.setStyle("-fx-text-fill: red;");
    }

    private void exibirMensagemSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    @FXML
    private void menu() throws IOException{
        App.setRoot("menu");
    }
}