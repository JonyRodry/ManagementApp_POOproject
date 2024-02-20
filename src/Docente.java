package projetopoao;

import java.io.Serializable;

/**
 * Classe que extende a classe abstrata Pessoa e representa um Docente inscrito no CISUC
 * <p>
 * Pertence ao package projetopoao que tem todas as classes utilizadas
 * <p>
 * Esta implementa um Serializable para poder ser utilizada no FicheiroObjetos
 * 
 * @see projetopoao.Pessoa
 * @see projetopoao.FicheiroObjetos
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
class Docente extends Pessoa implements Serializable{
    private int nMec;
    private String area;
    private int custo = 0;
    
    /**
    * Construtor da classe Docente.
    * Esta função cria um novo Docente com os dados recebidos.
    * 
    * @param nome Nome do Docente
    * @param email Email do Docente
    * @param nMec Número Mecanográfico do Docente
    * @param area Area do Docente
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Docente(String nome, String email, int nMec, String area) {
        super(nome, email);
        this.nMec = nMec;
        this.area = area;
    }

    /**
    * Retorna um int com o Numero Mecanográfico do Docente
    * @return Variavel nMec
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public int getnMec() {
        return nMec;
    }

    /**
    * Modifica o número mecanográfico à Pessoa
    * @param nMec - Novo número mecanográfico a introduzir
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setnMec(int nMec) {
        this.nMec = nMec;
    }

    /**
    * Retorna uma String que contem a área em que o Docente trabalha
    * @return Variavel area
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String getArea() {
        return area;
    }

    /**
    * Modifica a área da Pessoa
    * @param area - Novo área a introduzir
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void setArea(String area) {
        this.area = area;
    }

    /**
    * Retorna um inteiro com o custo do Docente
    * @return Variavel custo
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public int getCusto() {
        return custo;
    }
    
    public int getNProj(){
        return 0;
    }
    
    public void addNProj(){
        System.out.println("Nada");
    }
    
    public void addProj(){
        return;
    }
}
