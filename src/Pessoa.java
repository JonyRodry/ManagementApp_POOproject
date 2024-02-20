package projetopoao;

import java.io.Serializable;

/**
 * Classe Abstrata que representa uma pessoa inscrita no CISUC
 * <p>
 * Pertence ao package projetopoao que tem todas as classes utilizadas
 * <p>
 * Esta implementa um Serializable para poder ser utilizada no FicheiroObjetos
 * 
 * @see projetopoao.Docente
 * @see projetopoao.Bolseiro
 * @see projetopoao.FicheiroObjetos
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
abstract class Pessoa implements Serializable{
    private String nome;
    private String email;
    private double carga;
    
    /**
    * Recebe o custo
    * @see projetopoao.Docente#getCusto()
    * @see projetopoao.Licenciado#getCusto()
    * @see projetopoao.Mestrado#getCusto()
    * @return Variavel int custo
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */

    /**
    * Construtor da classe Pessoa.
    * Esta função cria uma nova Pessoa com os dados recebidos.
    * 
    * @param nome Nome da Pessoa
    * @param email Email da Pessoal.
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
    * Retorna uma String que contem o nome da Pessoa
    * @return Variavel nome
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getNome() {
        return nome;
    }

    /**
    * Modifica o nome à Pessoa
    * @param nome Novo nome a introduzir
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
    * Retorna uma String que contem o email da Pessoa
    * @return Variavel email
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getEmail() {
        return email;
    }

    /**
    * Modifica o email à Pessoa
    * @param email Novo email a introduzir
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
    * Retorna um Double que contem a carga atual da Pessoa
    * @return Variavel carga
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public double getCarga(){
        return carga;
    }
    
    /**
    * Adiciona carga à Pessoa
    * @param cargaAdd - Carga a incrementar
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void addCarga(double cargaAdd){
        this.carga = this.carga + cargaAdd;
    }

    /**
    * Diminui carga à Pessoa
    * @param cargaSub - Carga a decrementar
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void subCarga(double cargaSub){
        this.carga = this.carga - cargaSub;
    }   

    public abstract int getNProj();
    public abstract int getCusto();
    public abstract void addProj();
    public abstract void addNProj();
}
