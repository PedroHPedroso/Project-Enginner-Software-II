package Factory;

import model.Aluno;
import model.Emprestimo;
import java.time.LocalDate;

public class EmprestimoFactory {
    public static Emprestimo criarEmprestimo(LocalDate dataEmprestimo, Aluno aluno) {
        LocalDate dataPrevista = dataEmprestimo.plusDays(15);
        return new Emprestimo(dataEmprestimo, dataPrevista, aluno);
    }
}