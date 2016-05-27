package Dao;

import java.util.List;
import model.Filme;

public interface FilmeDao extends Dao<Filme>{
    public Filme procurarPorNome(String nome);
    public List<Filme> procurarPorGenero(String genero);
}
