package projetopoao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Abstrata que representa uma tarefa de um projeto.
 * <p>
 * Pertence ao package projetopoao que tem todas as classes utilizadas
 * <p>
 * Esta implementa um Serializable para poder ser utilizada no FicheiroObjetos
 * 
 * @see projetopoao.Desenvolvimento
 * @see projetopoao.Design
 * @see projetopoao.Documentacao
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
abstract class Tarefa implements Serializable{
    private String dataInicial;
    private String dataFinal;
    private int duracao;
    private double taxaEx;
    ArrayList<Pessoa> pessoasTarefa = new ArrayList<Pessoa>();

    /**
    * Construtor da classe Pessoa.
    * Esta função cria uma nova Pessoa com os dados recebidos.
    * 
    * @param dataInicial Data Inicial da Tarefa.
    * @param dataFinal Data Final para completar a Tarefa.
    * @param duracao Duração prevista da Tarefa
    * @param taxaEx Taxa de Execução da Tarefa
    * @param pessoaResponsavel Pessoa responsável pela Tarefa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Tarefa(String dataInicial, String dataFinal, int duracao, double taxaEx, Pessoa pessoaResponsavel) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.duracao = duracao;
        this.taxaEx = taxaEx;
    }
    
    /**
    * Atualiza a Taxa de execução da Tarefa
    * @param taxaNova Nova taxa de execução da Tarefa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void atualizaTaxa(double taxaNova){
        this.taxaEx = taxaNova;
    }
    
    /**
    * Recebe a Taxa de execução da Tarefa
    * @return Taxa de Execução em double
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public double getTaxa(){
        return taxaEx;
    }

    /**
    * Recebe a data Final da Tarefa
    * @return Data Final da Tarefa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getDataFinal(){
        return dataFinal;        
    }

    /**
    * Recebe a data Inicial da Tarefa
    * @return Data Inicial da Tarefa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getDataInicial(){
        return dataInicial;        
    }
    
    /**
    * Recebe a duração prevista da Tarefa
    * @return Duração em dias
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public int getDuracao(){
        return duracao;
    }
    
    /**
    * Recebe o nome
    * @see projetopoao.Desenvolvimento#getNome()
    * @see projetopoao.Design#getNome()
    * @see projetopoao.Documentacao#getNome()
    * @return Nome da Tarefa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public abstract String getNome();
    
    /**
    * Recebe o Esforço
    * @see projetopoao.Desenvolvimento#getEsforco()
    * @see projetopoao.Design#getEsforco()
    * @see projetopoao.Documentacao#getEsforco()
    * @return Esforço da Tarefa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public abstract double getEsforco();
    
    /**
    * Atribui uma Pessoa à tarefa
    * @see projetopoao.Desenvolvimento#addPessoaTarefa(projetopoao.Pessoa)
    * @see projetopoao.Design#addPessoaTarefa(projetopoao.Pessoa)
    * @see projetopoao.Documentacao#addPessoaTarefa(projetopoao.Pessoa)
    * @param p Pessoa a atribuir a tarefa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public abstract void addPessoaTarefa(Pessoa p);
    
}
