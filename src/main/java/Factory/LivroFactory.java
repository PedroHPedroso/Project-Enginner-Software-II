package Factory;

import model.Livro;

public class LivroFactory {
    public static Livro criarLivro(int disponibilidade, int prazo, String nome, String isbn, int edicao, int ano) {
        return new Livro(disponibilidade,prazo,nome,isbn,edicao,ano);
    }
}