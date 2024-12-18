package model;

import java.time.LocalDate;

public class Devolucao {

    private ItemEmprestimo itemEmprestimo;
    private LocalDate dataDevolucao;

    public Devolucao(ItemEmprestimo itemEmprestimo, LocalDate dataDevolucao) {
        this.itemEmprestimo = itemEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public ItemEmprestimo getItemEmprestimo() {
        return itemEmprestimo;
    }

    public void setItemEmprestimo(ItemEmprestimo itemEmprestimo) {
        this.itemEmprestimo = itemEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Devolucao{" +
                "itemEmprestimo=" + itemEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
