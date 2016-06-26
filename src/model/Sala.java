package model;

/**
 *Classe Responsavel por modelar as salas
 */
public class Sala {
    private int numeroSala;
    private int quantidadeSala;

    /**
     * COnntrutor com os parametros
     * @param numeroSala recebe o numero da sala
     * @param quantidadeSala recebe a capacidade maxima permitida na sala
     */
    public Sala(int numeroSala, int quantidadeSala) {
        this.numeroSala = numeroSala;
        this.quantidadeSala = quantidadeSala;
    }

    /**
     * Contrutor sem parametros
     */
    public Sala() {
    }

    /**
     * getter do numero da sala
     * @return traz comom retorno o numero da sala
     */
    public int getNumeroSala() {
        return numeroSala;
    }

    /**
     * setter do numero da sala
     * @param numeroSala recebe um int para atribuir o numero da sala no objeto
     */
    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    /**
     * getter da quantidade sala
     * @return retorna a capacidade maxima da sala
     */
    public int getQuantidadeSala() {
        return quantidadeSala;
    }

    /**
     * setter da quantidade da sala
     * @param quantidadeSala recebe um int para atribuir na quantidade da sala do objeto
     */
    public void setQuantidadeSala(int quantidadeSala) {
        this.quantidadeSala = quantidadeSala;
    }

    /**
     * toString
     * @return retorna todos os atributos da classe
     */
    @Override
    public String toString() {
        return "Numero da Sala = " + numeroSala + ", Capacidade maxima da Sala = " + quantidadeSala;
    }
    
    
    
    
    
    
}
