package projetopoao;
import java.io.*;

/**
 * Ficheiro que guarda a informação necessária para correr o programa.
 * <p>
 * Pertence ao package projetopoao que tem todas as classes utilizadas
 * 
 * @see projetopoao.Projeto
 * @see projetopoao.Pessoa
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
public class FicheiroObjetos {
    private ObjectInputStream iS;
    private ObjectOutputStream oS;
    
    /**
    * Construtor da classe FicheiroObjetos.
    * @see java.io.FileInputStream
    * @see java.io.FileOutputStream
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public FicheiroObjetos(){
    }
    
    /**
    * Abre um ficheiro de objetos para leitura
    * @param nomeFich diretoria do ficheiro de objetos
    * @see java.io.FileInputStream
    * @throws java.io.IOException se não existir o ficheiro
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void abreLeitura(String nomeFich) throws IOException{
        iS = new ObjectInputStream(new FileInputStream(nomeFich));
    }

    /**
    * Abre um ficheiro de objetos para escrita
    * @param nomeFich diretoria do ficheiro de objetos
    * @see java.io.FileOutputStream 
    * @throws java.io.IOException se não existir o ficheiro
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void abreEscrita(String nomeFich) throws IOException{
        oS = new ObjectOutputStream(new FileOutputStream(nomeFich));
    }
    
    /**
    * Lê o próximo objeto no ficheiro de objetos
    * @return Object 
    * @see java.io.FileInputStream
    * @throws java.io.IOException se não for possível ler ou ainda não esteja aberto para leitura
    * @throws java.lang.ClassNotFoundException caso a class que se esteja a ler não seja conhecida pelo programa
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public Object leObjeto() throws IOException, ClassNotFoundException{
        return iS.readObject();
    }
    
    /**
    * Escreve no ficheiro de objetos um Object
    * @param o Informação que vai ser guardada no ficheiro
    * @see java.io.FileOutputStream
    * @throws java.io.IOException se não for possível escrever ou ainda não esteja aberto para escrita
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void escreveObjeto(Object o) throws IOException{
        oS.writeObject(o);
    }
    
    /**
    * Fecha o ficheiro de objetos para leitura
    * @see java.io.FileInputStream
    * @throws java.io.IOException se não for possível fechar para leitura
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void fechaLeitura() throws IOException{
        iS.close();
    }
    
    /**
    * Fecha o ficheiro de objetos para escrita
    * @see java.io.FileOutputStream
    * @throws java.io.IOException se não for possível fechar para escrita
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void fechaEscrita() throws IOException{
        oS.close();
    }
}
