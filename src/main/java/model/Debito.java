/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import Padroes.Persistivel;

@Entity
@Table(name = "debito")
public class Debito implements Serializable, Persistivel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    
    @JoinColumn(name = "aluno", referencedColumnName = "RA")
    @ManyToOne
    private Aluno aluno;
    
    @Column
    private double valor;
    
    @Column
    private LocalDate data; 
    
    public Debito(Aluno aluno, LocalDate data, double valor){
        this.aluno = aluno;
        this.data = data;
        this.valor = valor;
    }
}
