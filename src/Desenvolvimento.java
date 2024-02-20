package projetopoao;

import java.io.Serializable;

/**
 * Classe que extende a classe abstrata Tarefa e representa um Desenvolvimento
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
class Desenvolvimento extends Tarefa implements Serializable{
    private double esforco = 1;
    private String nome = "Desenvolvimento";

    /**
    * Construtor da classe Desenvolvimento.
    * Esta função cria um novo Desenvolvimento com os dados recebidos.
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
    public Desenvolvimento(String dataInicial, String dataFinal, int duracao, double taxaEx, Pessoa pessoaResponsavel) {
        super(dataInicial, dataFinal, duracao, taxaEx, pessoaResponsavel);
    }

    /**
    * Retorna um double com o esforço do Desenvolvimento
    * @return Variavel esforco
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public double getEsforco() {
        return esforco;
    }

    /**
    * Retorna uma String com o Nome dado ao Desenvolvimento
    * @return Variavel nome
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getNome(){
        return this.nome;
    }

    /**
    * Associa uma Pessoa ao Desenvolvimento
    * @param p Pessoa a ser associar ao Desenvolvimento
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void addPessoaTarefa(Pessoa p){
        pessoasTarefa.add(p);
    }
}
