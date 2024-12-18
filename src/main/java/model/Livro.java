package model;

import Factory.LivroFactory;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import Padroes.Persistivel;

@Entity
@Table(name = "livro")
public class Livro implements Serializable, Persistivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 20)
    private int disponivel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "titulo_id", nullable = false)
    private Titulo titulo;

    public Livro() {}

    public Livro(int disponivel, int prazo, String nome, String isbn, int edicao, int ano) {
        this.disponivel = disponivel;
        this.titulo = new Titulo(prazo, nome, isbn, edicao, ano);
    }

    public static Livro criarLivro(int disponibilidade, int prazo, String nome, String isbn, int edicao, int ano) {
        return LivroFactory.criarLivro(disponibilidade, prazo, nome, isbn, edicao, ano);
    }

    public int verPrazo() {
        return titulo.getPrazo();
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
