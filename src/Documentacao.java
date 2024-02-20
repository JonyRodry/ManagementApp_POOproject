package projetopoao;

import java.io.Serializable;

/**
 * Classe que extende a classe abstrata Tarefa e representa uma Documentação
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
class Documentacao extends Tarefa implements Serializable{
    private double esforco = 0.25;
    private String nome = "Documentacao";

    /**
    * Construtor da classe Documentação.
    * Esta função cria uma nova Documentação com os dados recebidos.
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
    public Documentacao(String dataInicial, String dataFinal, int duracao, double taxaEx, Pessoa pessoaResponsavel) {
        super(dataInicial, dataFinal, duracao, taxaEx, pessoaResponsavel);
    }

    /**
    * Retorna um double com o esforço da Documentação
    * @return Variavel esforco
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public double getEsforco() {
        return esforco;
    }
    
    /**
    * Retorna uma String com o Nome dado à Documentação
    * @return Variavel nome
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getNome(){
        return this.nome;
    }
    
    /**
    * Associa uma Pessoa à Documentação
    * @param p Pessoa a ser associar à Documentação
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void addPessoaTarefa(Pessoa p){
        pessoasTarefa.add(p);
    }
}
