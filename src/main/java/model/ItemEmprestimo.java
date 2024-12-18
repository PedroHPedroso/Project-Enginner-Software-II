package model;

import Padroes.Dao;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import Padroes.Dao;
import Padroes.Persistivel;

@Entity
@Table(name = "item_emprestimo")
public class ItemEmprestimo implements Serializable, Persistivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = false)
    private Emprestimo emprestimo;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    public ItemEmprestimo() {
    }

    public ItemEmprestimo(Emprestimo emprestimo, Livro livro) {
        this.emprestimo = emprestimo;
        this.livro = livro;
         Dao<Livro> livroDao = new Dao<>(Livro.class);
         livro.setDisponivel(0);
         livroDao.atualizar(livro);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "ItemEmprestimo{" +
               "codigo=" + codigo +
               ", emprestimo=" + emprestimo +
               ", livro=" + livro +
               ", dataDevolucao=" + dataDevolucao +
               '}';
    }
}