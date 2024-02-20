package projetopoao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Projeto que representa um projeto inscrito no CISUC
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
class Projeto implements Serializable{
    private String nome;
    private String acronimo;
    private String dataInicial;
    private String dataFinal;
    private int duracao;
    private Pessoa responsavel;
    ArrayList<Pessoa> pessoasProjeto = new ArrayList<Pessoa>();
    ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    
    
    /**
    * Construtor da classe Projeto.
    * Esta função cria um novo Projeto com os dados recebidos.
    * 
    * @param nome Nome do Projeto
    * @param acronimo Acronimo do Projeto
    * @param dataInicial Data Inicial do Projeto
    * @param dataFinal Data Final do Projeto
    * @param duracao Duracao prevista do Projeto
    * @param responsavel Docente Responsavel pelo Projeto
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Projeto(String nome, String acronimo, String dataInicial, String dataFinal, int duracao, Pessoa responsavel) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.duracao = duracao;
        this.responsavel = responsavel;
    }

    /**
    * Retorna o Nome do Projeto
    * @return Variavel nome
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getNome() {
        return nome;
    }

    /**
    * Modifica o nome do Projeto
    * @param nome Novo nome
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
    * Retorna o Acronimo do Projeto
    * @return Variavel acronimo
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getAcronimo() {
        return acronimo;
    }

    /**
    * Modifica o acronimo do Projeto
    * @param acronimo Novo acronimo
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    /**
    * Retorna a Data Inicial do Projeto
    * @return Variavel dataInicial
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getDataInicial() {
        return dataInicial;
    }

    /**
    * Modifica a data Inicial do Projeto
    * @param dataInicial Nova data
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
    * Retorna a Data Final do Projeto
    * @return Variavel dataFinal
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getDataFinal() {
        return dataFinal;
    }

    /**
    * Modifica a data Final do Projeto
    * @param dataFinal Nova data
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
    * Retorna a Duração prevista para o Projeto
    * @return Variavel duracao
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public int getDuracao() {
        return duracao;
    }

    /**
    * Modifica a duração prevista para a conclusão Projeto
    * @param duracao Nova duração
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
    * Retorna as Pessoas associadas ao Projeto
    * @return Variavel pessoasProjeto
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public ArrayList<Pessoa> getPessoasProjeto() {
        return pessoasProjeto;
    }

    /**
    * Retorna as Tarefas associadas ao Projeto
    * @return Variavel tarefas
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public ArrayList<Tarefa> getTarefasProjeto() {
        return tarefas;
    }
    
    /**
    * Adiciona uma Pessoas à ArrayList que guarda as Pessoas associadas ao Projeto
    * @param p Pessoa a adicionar
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void addPessoa(Pessoa p){
        pessoasProjeto.add(p);
    }
    
    /**
    * Imprime no ecrã as Pessoas do Projeto
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void printPessoas(){
        for(int i = 0; i < pessoasProjeto.size(); i++){
            System.out.println(pessoasProjeto.get(i).getNome());
        }
    }
    
    /**
    * Retorna a Pessoa responsável pelo Projeto
    * @return Variavel responsavel
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Pessoa getResponsavel(){
        return responsavel;
    }
    
    /**
    * Adiciona uma tarefa à ArrayList que guarda as tarefas associadas ao Projeto
    * @param t Tarefa a adicionar
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void addTarefa(Tarefa t){
        tarefas.add(t);
    }
    
    /**
    * Remove uma tarefa da ArrayList que guarda as tarefas associadas ao Projeto
    * @param index Index a remover
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void removerTarefa(int index){
        tarefas.remove(index);
    }

    /**
    * Imprime no ecrã as tarefas do Projeto
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void printTarefas(){
        for(int i = 0; i < tarefas.size(); i++){
            System.out.println(tarefas.get(i).getNome());
        }
    }
}
