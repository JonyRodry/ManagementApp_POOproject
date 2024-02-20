package projetopoao;

import java.io.Serializable;

/**
 * Classe que extende a classe abstrata Bolseiro e representa um Douturado inscrito no CISUC
 * <p>
 * Pertence ao package projetopoao que tem todas as classes utilizadas
 * <p>
 * Esta implementa um Serializable para poder ser utilizada no FicheiroObjetos
 * 
 * @see projetopoao.Pessoa
 * @see projetopoao.Bolseiro
 * @see projetopoao.FicheiroObjetos
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
class Doutorado extends Bolseiro implements Serializable{
    private int custo = 1200;
    private int nProj = 0;

    /**
    * Construtor da classe Doutorado.
    * Esta função cria um novo Doutorado com os dados recebidos.
    * Recebe dados através da função super() que é o construtor da classe Bolseiro
    * 
    * @param nome Nome do Bolseiro
    * @param email Email do Bolseiro
    * @param dataInicial Data Inicial da Bolsa do Licenciado
    * @param dataFinal Data Final da Bolsa do Licenciado
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Doutorado(String nome, String email, String dataInicial, String dataFinal) {
        super(nome, email, dataInicial, dataFinal);
    }

    /**
    * Retorna um inteiro com o custo do Doutorado
    * @return Variavel custo
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
   public int getCusto() {
        return custo;
    }
    
    public int getNProj(){
        return nProj;
    }
    
    public void addNProj(){
        this.nProj = this.nProj + 1;
    }
    
    public void addProj(){
        return;
    }
}
