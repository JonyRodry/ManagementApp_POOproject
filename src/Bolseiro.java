package projetopoao;

import java.io.Serializable;

/**
 * Classe Abstrata que extende uma Pessoa representa um bolseiro inscrita no CISUC
 * <p>
 * Pertence ao package projetopoao que tem todas as classes utilizadas
 * <p>
 * Esta implementa um Serializable para poder ser utilizada no FicheiroObjetos
 * 
 * @see projetopoao.Docente
 * @see projetopoao.Bolseiro
 * @see projetopoao.Licenciado
 * @see projetopoao.Mestrado
 * @see projetopoao.Doutorado
 * @see projetopoao.FicheiroObjetos
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
abstract class Bolseiro extends Pessoa implements Serializable{
    private String dataInicial;
    private String dataFinal;

    public Bolseiro(String nome, String email, String dataInicial, String dataFinal) {
        super(nome, email);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}