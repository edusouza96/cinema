package model;

import java.util.Date;

/**
 *Classe responsavel por modelar a venda
 */
public class Venda {
    private static int CODIGO_GERADO = 1; 
    private int registroVenda;
    private Sessao filmeSalaSessao;
    private Date data;

    /**
     * Construtor sem parametro
     */
    public Venda() {
    }
    
   /**
    * Construtor com parametro
    * @param filmeSalaSessao recebe um objeto sessao
    * @param data recebe um objeto data
    */
    public Venda(Sessao filmeSalaSessao, Date data) {
        this.registroVenda = generateCodigo();
        this.filmeSalaSessao = filmeSalaSessao;
        this.data = data;
    }
    
    /**
    * Construtor com parametro
     * @param registroVenda recebe uma key para representar o registro de venda
    * @param filmeSalaSessao recebe um objeto sessao
    * @param data recebe um objeto data
    */
    public Venda(int registroVenda,Sessao filmeSalaSessao, Date data) {
        this.registroVenda = registroVenda;
        this.filmeSalaSessao = filmeSalaSessao;
        this.data = data;
    }

    /**
     * getter de registroVenda
     * @return  Retorna o valor do registro de venda
     */
    public int getRegistroVenda() {
        return registroVenda;
    }

    public void setRegistroVenda(int registroVenda) {
        this.registroVenda = registroVenda;
    }

    /**
     * getter filmeSalaSessao
     * @return retorna objeto sessao
     */
    public Sessao getFilmeSalaSessao() {
        return filmeSalaSessao;
    }

    /**
     * getter data
     * @return retorna objeto data
     */
    public Date getData() {
        return data;
    }

    /**
     * toString
     * @return retorna o registro de venda, a data e os dadsos da sessao
     */
    @Override
    public String toString() {
        return "Registro Venda = " + registroVenda + ", Data = " + data+ " " + filmeSalaSessao ;
    }
        
    /**
     * metodo responsavel por gerar um codigoFilme para objeto
     * @return retorna um codigoFilme
     */
    private int generateCodigo(){
        return(CODIGO_GERADO++);
    }
    
}
