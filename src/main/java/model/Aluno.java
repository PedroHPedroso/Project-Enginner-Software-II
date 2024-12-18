/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import Padroes.Persistivel;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable, Persistivel {
    @Id
    private Integer RA;
    
    @Column
    private String nome;
    
    @Column
    private String CPF;
    
    @Column
    private int idade;
    
    public Aluno(){}
    
    public Aluno(int RA,String nome, String CPF, int idade){
        this.RA = RA;
        this.nome = nome;
        this.CPF = CPF;
        this.idade = idade;
    }
    
    public Integer getRA(){
        return RA;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getCPF(){
        return CPF;
    }
    
    public Integer getIdade(){
        return idade;
    }
    
    public void setRA(Integer RA){
        this.RA = RA;
    }
        
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setCPF(String CPF){
        this.CPF = CPF;
    }
    
    public void setIdade(Integer idade){
        this.idade = idade;
    }
}
