package model;

import java.util.Date;

/**
*Classe responsavel por modelar os lugares disponiveis de uma sessão de um dia
*/
public class Assento {
    private static int CODIGO_GERADO=1;
    private int codigoAssento;
    private Sessao sessao;
    private int assentoLivres;
    private Date data;

    /**
     * Construtor com parametros
     * @param sessao recebe um objeto do tipo sessão
     * @param assentoLivres Recebe a quantidade de assentos disponiveis para sessão
     * @param data recebe a data do dia de uma determinada sessão
     */
    public Assento(Sessao sessao, int assentoLivres, Date data) {
        this.sessao = sessao;
        this.assentoLivres = assentoLivres;
        this.data = data;
    }

    /**
     * construtor sem parametro
     */
    public Assento() {
    }

    /**
     * getter da data
     * @return retorna uma data
     */
    public Date getData() {
        return data;
    }

    /**
     * getter do codigoAcesso
     * @return retorna o codigo do objeto acesso
     */
    public int getCodigoAssento() {
        return codigoAssento;
    }

    
    /**
     * getter do atributo assentoLivres
     * @return retorna um valor inteiro de lugares disponiveis para sessão do dia
     */
    public int getAssentoLivres() {
        return assentoLivres;
    }

    /**
     * metodo que diminui uma quantidade nos assentoLivres
     */
    public void ocuparLugar() {
        this.assentoLivres = this.assentoLivres-1;
    }
    
    /**
     * metodo responsavel por gerar um codigoFilme para objeto
     * @return retorna um codigoFilme
     */
    private int generateCodigo(){
        return(CODIGO_GERADO++);
    }    

    /**
     * getter da sessao
     * @return retorna o objeto sessao
     */
    public Sessao getSessao() {
        return sessao;
    }

    /**
     *toString
     * @return retorna o codigo, os dados da sessão, e a data 
     */
    @Override
    public String toString() {
        return "Código = " + codigoAssento + " " + sessao.toString() +  ", Data = " + data ;
    }

    
    
    
    
    
    
    
    
}
