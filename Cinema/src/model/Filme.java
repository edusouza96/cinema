package model;

/**
 *Classe Responsavel por modelar os  filmes
 */
public class Filme {
    private int codigo;
    private String nomeFilme;
    private String genero;
    private String sinopse;
    /**
     * Construtor com parametros
     * @param nomeFilme: Recebe o nome do filme
     * @param genero: Recebe o genero do filme
     * @param sinopse: Recebe  a sinopse do filme
     */
    public Filme(String nomeFilme, String genero, String sinopse) {
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.sinopse = sinopse;
    }
    /**
     * Contrutor sem parametro
     */
    public Filme() {
    }
    /**
     * getter do codigo
     * @return retorna o código do filme
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * setter do codigo
     * @param codigo recebe um código para inserir na classe
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
     /**
     * getter do nome
     * @return retorna o nome do filme
     */
    public String getNomeFilme() {
        return nomeFilme;
    }
    /**
     * setter do nome
     * @param nomeFilme recebe o nome do filme para inserir na classe
     */
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }
    /**
     * getter do genero
     * @return retorna o genero do filme
     */
    public String getGenero() {
        return genero;
    }
     /**
     * setter do genero
     * @param genero recebe o genero do filme para inserir na classe
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
     /**
     * getter da sinopse
     * @return retorna a sinopse do filme
     */
    public String getSinopse() {
        return sinopse;
    }
    /**
     * setter da sinopse
     * @param sinopse recebe a sinopse do filme para inserir na classe
     */
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    /**
     * toString
     * @return retorna os atributos da classe
     */
    @Override
    public String toString() {
        return "Código = " + codigo + ", Nome Filme = " + nomeFilme + ", Gênero = " + genero + ", Sinopse = " + sinopse;
    }
    
}
