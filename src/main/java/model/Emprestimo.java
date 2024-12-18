package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import Padroes.Persistivel;

@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable, Persistivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_prevista", nullable = false)
    private LocalDate dataPrevista;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    public Emprestimo() {
    }

    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataPrevista, Aluno aluno) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.aluno = aluno;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
               "codigo=" + codigo +
               ", dataEmprestimo=" + dataEmprestimo +
               ", dataPrevista=" + dataPrevista +
               ", aluno=" + aluno +
               '}';
    }
}