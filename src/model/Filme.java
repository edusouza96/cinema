package model;

/**
 *Classe Responsavel por modelar os  filmes
 */
public class Filme {
    private static int CODIGO_GERADO=1;
    private int codigoFilme;
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
        this.codigoFilme = generateCodigo();
        this.nomeFilme = nomeFilme;
        this.genero = genero;
        this.sinopse = sinopse;
    }
     /**
     * Construtor com parametros
     * @param codigoFilme: Recebe o código do filme
     * @param nomeFilme: Recebe o nome do filme
     * @param genero: Recebe o genero do filme
     * @param sinopse: Recebe  a sinopse do filme
     */
    public Filme(int codigoFilme, String nomeFilme, String genero, String sinopse) {
        this.codigoFilme = codigoFilme;
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
     * getter do codigoFilme
     * @return retorna o código do filme
     */
    public int getCodigoFilme() {
        return codigoFilme;
    }
    /**
     * setter do codigoFilme
     * @param codigoFilme recebe um código para inserir na classe
     */
    public void setCodigoFilme(int codigoFilme) {
        this.codigoFilme = codigoFilme;
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
        return "Código = " + codigoFilme + ", Nome Filme = " + nomeFilme + ", Gênero = " + genero + ", Sinopse = " + sinopse;
    }
    /**
     * metodo responsavel por gerar um codigoFilme para objeto
     * @return retorna um codigoFilme
     */
    private int generateCodigo(){
        return(CODIGO_GERADO++);
    }    
}
