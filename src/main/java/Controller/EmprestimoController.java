package Controller;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Aluno;
import model.Debito;
import model.Emprestimo;
import model.ItemEmprestimo;
import model.Livro;
import Factory.EmprestimoFactory;
import Factory.LivroFactory;
import Padroes.Dao;

public class EmprestimoController {

    @FXML
    private TextField campoCodigoAluno;

    @FXML
    private TextField campoCodigoLivro;

    @FXML
    private DatePicker pickerDataEmprestimo;

    @FXML
    private ListView<String> listaLivrosAdicionados;

    @FXML
    private Button botaoAdicionarLivro;

    @FXML
    private Button botaoConcluirEmprestimo;

    private ObservableList<String> livrosAdicionados;
    private Dao<Aluno> alunoDao;
    private Dao<Livro> livroDao;
    private Dao<Emprestimo> emprestimoDao;
    private Dao<ItemEmprestimo> itemEmprestimoDao;
    private Dao<Debito> debitoDao;

    @FXML
    private void initialize() {
        alunoDao = new Dao<>(Aluno.class);
        livroDao = new Dao<>(Livro.class);
        emprestimoDao = new Dao<>(Emprestimo.class);
        itemEmprestimoDao = new Dao<>(ItemEmprestimo.class);
        debitoDao = new Dao<>(Debito.class);
        

        livrosAdicionados = FXCollections.observableArrayList();
        listaLivrosAdicionados.setItems(livrosAdicionados);

        pickerDataEmprestimo.setValue(LocalDate.now());
    }

    @FXML
    private void adicionarLivro() {
        String codigoLivroTexto = campoCodigoLivro.getText();
        if (codigoLivroTexto.isEmpty()) {
            exibirAlertaErro("Erro", "Por favor, insira o código do livro (ISBN).");
            return;
        }

        try {
            for (String livro : livrosAdicionados) {
                if (livro.contains(codigoLivroTexto)) {
                    exibirAlertaErro("Erro", "Este livro já foi adicionado.");
                    return;
                }
            }

            Livro livro = livroDao.buscarPorISBN(codigoLivroTexto);

            if (livro == null) {
                exibirAlertaErro("Erro", "Livro com o ISBN informado não existe.");
                return;
            }

            if (livro.getDisponivel() == 0) {
                exibirAlertaErro("Erro", "O livro com o ISBN informado não está disponível para empréstimo.");
                return;
            }

            livrosAdicionados.add("ISBN: " + livro.getTitulo().getIsbn() + " - Título: " + livro.getTitulo().getNome());
            exibirMensagem("Livro adicionado", "O livro foi adicionado à lista de empréstimo.");
            campoCodigoLivro.clear();

        } catch (Exception e) {
            exibirAlertaErro("Erro", "Erro ao adicionar o livro. Verifique o ISBN e tente novamente.");
        }
    }

    @FXML
    private void concluirEmprestimo() {
        String codigoAlunoTexto = campoCodigoAluno.getText();
        if (codigoAlunoTexto.isEmpty()) {
            exibirAlertaErro("Erro", "Por favor, insira o código do aluno (RA).");
            return;
        }

        try {
            
            Aluno aluno = alunoDao.buscarPorCampo("RA", Integer.parseInt(codigoAlunoTexto));
            if (aluno == null) {
                exibirAlertaErro("Erro", "Aluno com o RA informado não existe.");
                return;
            }
            
            Debito debito = debitoDao.buscarPorCampo("aluno", aluno.getRA());
            if (debito != null) {
                exibirAlertaErro("Erro", "O aluno possui um débito pendente. Não é possível concluir o empréstimo.");
                return;
            }

            if (livrosAdicionados.isEmpty()) {
                exibirAlertaErro("Erro", "Nenhum livro foi adicionado ao empréstimo.");
                return;
            }

            int maiorPrazo = 0;

            LocalDate dataEmprestimo = pickerDataEmprestimo.getValue();
            Emprestimo emprestimo = EmprestimoFactory.criarEmprestimo(dataEmprestimo, aluno);
            emprestimoDao.inserir(emprestimo);

            for (String livroInfo : livrosAdicionados) {
                String isbn = livroInfo.split(" - ")[0].replace("ISBN: ", "");
                Livro livro = livroDao.buscarPorISBN(isbn);

                if (livro != null) {
                    int prazo = livro.getTitulo().getPrazo();

                    if (prazo > maiorPrazo) {
                        maiorPrazo = prazo;
                    }

                    ItemEmprestimo itemEmprestimo = new ItemEmprestimo(emprestimo, livro);
                    itemEmprestimoDao.inserir(itemEmprestimo);

                    livro.setDisponivel(0);
                    livroDao.atualizar(livro);
                }
            }

            LocalDate dataPrevista = dataEmprestimo.plusDays(maiorPrazo);
            emprestimo.setDataPrevista(dataPrevista);
            emprestimoDao.atualizar(emprestimo);

            exibirMensagem("Empréstimo Concluído", "O empréstimo foi registrado com sucesso.\nData de devolução: " + dataPrevista);
            limparFormulario();

        } catch (NumberFormatException e) {
            exibirAlertaErro("Erro", "O RA do aluno deve ser um número.");
        } catch (Exception e) {
            exibirAlertaErro("Erro", "Erro ao concluir o empréstimo. Tente novamente.");
            e.printStackTrace();
        }
    }

    private void limparFormulario() {
        campoCodigoAluno.clear();
        campoCodigoLivro.clear();
        pickerDataEmprestimo.setValue(LocalDate.now());
        livrosAdicionados.clear();
    }

    private void exibirMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void voltarAoMenu() throws IOException {
        App.setRoot("menu");
    }
}
