package projetopoao;

import java.io.Serializable;

/**
 * Classe que extende a classe abstrata Tarefa e representa um Design
 * <p>
 * Pertence ao package projetopoao que tem todas as classes utilizadas
 * <p>
 * Esta implementa um Serializable para poder ser utilizada no FicheiroObjetos
 * 
 * @see projetopoao.Pessoa
 * @see projetopoao.Tarefa
 * @see projetopoao.FicheiroObjetos
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
class Design extends Tarefa implements Serializable{
    private double esforco = 0.5;
    private String nome = "Design";

    /**
    * Construtor da classe Design.
    * Esta função cria um novo Design com os dados recebidos.
    * Recebe os dados através da função super() que é o construtor da classe Bolseiro
    * 
    * @param duracao Duração da Documentação
    * @param taxaEx Taxa de Execução da Documentação
    * @param dataInicial Data Inicial da Documentação
    * @param dataFinal Data Final da Documentação
    * @param pessoaResponsavel Pessoa responsável pela Documentação
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Design(String dataInicial, String dataFinal, int duracao, double taxaEx, Pessoa pessoaResponsavel) {
        super(dataInicial, dataFinal, duracao, taxaEx, pessoaResponsavel);
    }
    
    /**
    * Retorna um double com o esforço do Design
    * @return Variavel esforco
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public double getEsforco() {
        return esforco;
    }
    
    /**
    * Retorna uma String com o Nome dado ao Design
    * @return Variavel nome
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getNome(){
        return this.nome;
    }

    /**
    * Associa uma Pessoa ao Design
    * @param p Pessoa a ser associar ao Desenvolvimento
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void addPessoaTarefa(Pessoa p){
        pessoasTarefa.add(p);
    }
}
