/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "titulo")
public class Titulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 20)
    private int prazo;

    @Column(length = 100)
    private String nome;

    @Column(length = 20)
    private String isbn;

    @Column(length = 10)
    private int edicao;

    @Column(length = 4)
    private int ano;

    public Titulo() {}

    public Titulo(int prazo, String nome, String isbn, int edicao, int ano) {
        this.prazo = prazo;
        this.nome = nome;
        this.isbn = isbn;
        this.edicao = edicao;
        this.ano = ano;
    }

    public int getAno() {
            return ano;
        }

    public int getEdicao() {
        return edicao;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNome() {
        return nome;
    }
    
    public int getPrazo() {
            return prazo;
    }

    public void setPrazo(int prazo) {
            this.prazo = prazo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
