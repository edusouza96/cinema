package model;

import java.util.Date;
import util.DateUtil;

/**
 *classe responsavel por fazer o modelo da sessão
 */
public class Sessao {
    private static int CODIGO_GERADO=1;
    private int codigoSessao;
    private Filme filme;
    private Sala sala;
    private Date horario;
    private String nomeFilme;
    private int numeroSala;

    /**
     * Contrutor que recebe os parametros para iniciar os atribbutos do objetos
     * @param filme Recebe um objeto do tipo filme
     * @param sala Recebe um objeto do tipo sala 
     * @param horario Recebe um objeto do tipo Date
     */
    public Sessao(Filme filme, Sala sala, Date horario) {
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
    }
    
    /**
     * Contrutor que recebe os parametros para iniciar os atribbutos do objetos
     * @param codigoSessao: Recebe o código da sessao
     * @param filme Recebe um objeto do tipo filme
     * @param sala Recebe um objeto do tipo sala 
     * @param horario Recebe um objeto do tipo Date
     */
    public Sessao(int codigoSessao,  Sala sala,Filme filme, Date horario) {
        this.codigoSessao = codigoSessao;
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
    }

    /**
     * contrutor sem parametro
     */
    public Sessao() {
    }

    public void SessaoAdaptada(int codigoSessao,  int sala, String filme, Date horario) {
        this.codigoSessao = codigoSessao;
        this.nomeFilme = filme;
        this.numeroSala = sala;
        this.horario = horario;
    }
    /**
     * getter do codigo da sessao
     * @return retorna o codigo da sessao
     */
    public int getCodigoSessao() {
        return codigoSessao;
    }

    /**
     * setter da sessao
     * @param codigoSessao recebe por parametro um int que representa o codigo 
     */
    public void setCodigoSessao(int codigoSessao) {
        this.codigoSessao = codigoSessao;
    }

    /**
     * getter do file
     * @return retorna o objeto filme
     */
    public Filme getFilme() {
        return filme;
    }

    /**
     * setter do filme
     * @param filme recebe por parametro o objeto filme
     */
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    /**
     * getter da sala
     * @return Retorna o objeto sala
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * setter da sala
     * @param sala recebe por parametro o objeto sala
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * getter do horario
     * @return retorna o objeto horario
     */
    public Date getHorario() {
        return horario;
    }

    /**
     * setter do horario
     * @param horario recebe por parametro o objeto horario
     */
    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    /**
     * toString
     * @return retorna o nome do filme, o numero da sala e o horario da sessão
     */
    @Override
    public String toString() {
        String hora = DateUtil.hourToString(horario);
        return "Filme = " + filme.getNomeFilme() + " | Sala = " + sala.getNumeroSala() + " | Hora = " + hora;
    }
    
    public String toString2() {
        String hora = DateUtil.hourToString(horario);
        return "Filme = " + filme.getNomeFilme() + "\n Sala = " + sala.getNumeroSala() + "\n Hora = " + hora;
    }
    
    /**
     * metodo responsavel por gerar um codigo para objeto
     * @return retorna um codigo
     */
    private int generateCodigo(){
        return(CODIGO_GERADO++);
    }     
    
}
