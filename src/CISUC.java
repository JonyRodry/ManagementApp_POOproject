package projetopoao;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Classe que representa o Projeto, esta possui toda a informacao sobre este e a 
 * interface para uso do utilizador.
 * <p>
 * Variáveis do programa:
 * <ul>
 * <li> projetos - List que contém todos os projetos de investigação ativos do CISUC.
 * <li> pessoas - List que contém todas pessoas inscritas no CISUC.
 * <li> projetosInacabados - List ue contém todos os projetos de investigação que não foram concluídos dentro da data prevista.
 * <li> projetosConcluidos - List ue contém todos os projetos de investigação que já foram terminados.
 * <li> fichObjectos - ficheiro de objetos que vai sendo actualizado ao longo do programa para guardar a informação deste.
 * <li> docentes - List que contém todos os Docentes inscritas no CISUC.
 * <li> date - Data obtidade pela função GregorianCalendar();
 * </ul>
 * <p>
 * A interface está definida por uma Frame que contém vários JPanels.
 * Cada JPanel tem um GridBagLayout
 * Durante as trocas de páginas os JPanels vao sendo definidos com visibilidade a falso, menos o que se quer que fique visivel para o utilizador.
 * <p>
 * Interface Panels:
 * <ul>
 * <li> dataPanel - Menu validar Data
 * <li> menuInicial - Menu Inicial
 * <li> menu1 - Menu de Criar Projetos e Associar Pessoas
 * <ul>
 * <li> menu11 - Menu de Criar Projetos
 * <li> menu12 - Menu de Associar Pessoas
 * </ul>
 * <li> menu 2 - Editar os Projetos
 * <ul>
 * <li> menu 21 - Listar, criar e eliminar tarefas
 * <li> menu 22 - Atribuir Tarefas
 * <li> menu 23 - Atualizar taxa de execução
 * <li> menu 24 - Ver tarefas não iniciadas
 * <li> menu 25 - Ver tarefas não concluídas nas datas estimadas
 * <li> menu 26 - Ver tarefas concluídas
 * <li> menu 27 - Indicar o custo do Projeto
 * <li> menu 28 - Terminar o Projeto
 * </ul>
 * <li> menu 3 - Listar os projetos não concluídos na data estimada
 * <li> menu 4 - Listar os projetos concluídos dentro da data estimada
 * </ul>
 * <p>
 * 
 * @see projetopoao.Projeto
 * @see projetopoao.Pessoa
 * @see javax.swing.JFrame
 * 
 * @author      Carlos Abegão
 * @author      João Rodrigues
 */
public class CISUC{
    private FicheiroObjetos fichObjetos;
    private List<Projeto> projetos;
    private List<Pessoa> pessoas;
    private List<Projeto> projetosInacabados;
    private List<Projeto> projetosConcluidos;
    private List<Pessoa> docentes;
    private List<Pessoa> bolseiros;
    GregorianCalendar date = new GregorianCalendar();
    GregorianCalendar date2 = new GregorianCalendar();    
    
    /**
    * Construtor da classe CISUC.Esta funcao primeiro verifica se existe algum ficheiro arquivo.dat.Caso exista, lê a informação do projeto a partir do ficheiro de objetos
    *   Caso não exista, irá ler a informação a partir do ficheiro texto FileInicial.txt
    * 
    *   Inicializa a sua interface (parte gráfica do projeto)
    * 
    * @throws java.io.FileNotFoundException - Ficheiro não existe
    * @throws java.lang.ClassNotFoundException  - Classe não existe
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public CISUC() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        File f = new File("arquivo.dat");
        
        if(f.exists()){
            //Lê dos ficheiros de Objeto
            fichObjetos = new FicheiroObjetos();
            try{
                fichObjetos.abreLeitura("arquivo.dat");
                pessoas = (ArrayList<Pessoa>) fichObjetos.leObjeto();
                projetos = (ArrayList<Projeto>) fichObjetos.leObjeto();
                projetosConcluidos = (ArrayList<Projeto>) fichObjetos.leObjeto();
                projetosInacabados = (ArrayList<Projeto>) fichObjetos.leObjeto();
                docentes = (ArrayList<Pessoa>) fichObjetos.leObjeto();
                bolseiros = (ArrayList<Pessoa>) fichObjetos.leObjeto();
            }
            catch(IOException | ClassNotFoundException ex){
                //mensagem de erro na interface
                System.out.println(ex.toString());
                System.out.println("Erro a abrir o ficheiro de objetos arquivo.dat");
                System.exit(0);
            }
            try {
                fichObjetos.fechaLeitura();
                fichObjetos.abreEscrita("arquivo.dat");
                fichObjetos.escreveObjeto(pessoas);
                fichObjetos.escreveObjeto(projetos);
                fichObjetos.escreveObjeto(projetosConcluidos);
                fichObjetos.escreveObjeto(projetosInacabados);
                fichObjetos.escreveObjeto(docentes);
                fichObjetos.escreveObjeto(bolseiros);
                
            } 
            catch (IOException ex) {
                System.out.println("Erro a criar o ficheiro de objetos arquivo.dat");
                System.exit(0);
            }
        }
        else{
            try(FileReader ficheiro = new FileReader("FileInicial.txt")){
                BufferedReader ler = new BufferedReader(ficheiro);
                
                pessoas = new ArrayList<Pessoa>();
                projetos = new ArrayList<Projeto>();
                projetosConcluidos = new ArrayList<Projeto>();
                projetosInacabados = new ArrayList<Projeto>();
                docentes = new ArrayList<Pessoa>();
                bolseiros = new ArrayList<Pessoa>();
                
                String linha = ler.readLine();
                
                while(linha != null){
                    String[] div = linha.split(",");
                    if(div[0].equals("Projeto")){
                        String nome = div[1];
                        String acronimo = div[2];
                        String inicio = div[3];
                        String fim = div[4];
                        int duracao = parseInt(div[5]);
                        String pessoaResp = div[6];
                        for(int h = 0; h < docentes.size(); h++){
                            if(docentes.get(h).getNome().equals(pessoaResp)){
                               projetos.add(new Projeto(nome, acronimo, inicio, fim, duracao, docentes.get(h)));
                            }
                            else{
                                System.out.println("ERRO AO CRIAR PROJETO");
                            }
                        }
                    }
                    else{
                        String tipo = div[1];
                        String nome = div[2];
                        String email = div[3];
                        String dataInicial = div[4];
                        String dataFinal = div[5];
                        
                        switch (tipo) {
                            case "Licenciado":
                                pessoas.add(new Licenciado(nome, email, dataInicial, dataFinal));
                                bolseiros.add(new Licenciado(nome, email, dataInicial, dataFinal));
                                break;
                            case "Mestrado":
                                pessoas.add(new Mestrado(nome, email, dataInicial, dataFinal));
                                bolseiros.add(new Mestrado(nome, email, dataInicial, dataFinal));
                                break;
                            case "Douturado":
                                pessoas.add(new Doutorado(nome, email, dataInicial, dataFinal));
                                bolseiros.add(new Doutorado(nome, email, dataInicial, dataFinal));
                                break;
                            case "Docente":
                                pessoas.add(new Docente(nome, email, parseInt(dataInicial), dataFinal));
                                docentes.add(new Docente(nome, email, parseInt(dataInicial), dataFinal));
                                break;
                            default:
                                break;
                        }
                    }
                    linha = ler.readLine();
                }
            }
            
            //Cria o 1º fichObjetos
            fichObjetos = new FicheiroObjetos();
            try {
                fichObjetos.abreEscrita("arquivo.dat");
                
            } catch (IOException ex) {
                //mensagem de erro na interface
                System.out.println("Erro a criar o ficheiro de objetos arquivo.dat");
                System.exit(0);
            }
            try {
                fichObjetos.escreveObjeto(pessoas);
                fichObjetos.escreveObjeto(projetos);
                fichObjetos.escreveObjeto(projetosConcluidos);
                fichObjetos.escreveObjeto(projetosInacabados);
                fichObjetos.escreveObjeto(docentes);
                fichObjetos.escreveObjeto(bolseiros);
                fichObjetos.fechaEscrita();
            } catch (IOException ex) {
                 System.out.println(ex.toString());
                //mensagem de erro na interface
                System.out.println("Erro a criar o ficheiro de objetos arquivo.dat");
                System.exit(0);
            }
        }
        //Inicia a Interface
        initInterface();    
    }
    
    /**
    * Inicia os componentes da interface.
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public void initInterface(){
        JFrame frame = new JFrame();
        frame.setTitle("PROJETO");
        frame.setSize(700, 500);
        //frame.getContentPane().add(new JPanelWithBackground("sample.jpeg"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel dataPanel, menuInicial, menu1, menu11, menu12, menu2, menu3, menu4, menu21, menu22, menu23, menu24, menu25, menu26, menu27, menu28;        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 60;
        gbc.ipady = 40;
        gbc.insets = new Insets(5, 5, 5, 5);
                    
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        JLabel dataLabel = new JLabel("Coloque uma data [DD/MM/YYYY]:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dataPanel.add(dataLabel, gbc);
        JTextField dataText = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dataPanel.add(dataText, gbc);
        JButton validarData = new JButton("Validar Data");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dataPanel.add(validarData, gbc);
        
        frame.add(dataPanel);
        frame.setVisible(true);    
       
        //MENU INICIAL
        menuInicial = new JPanel();
        menuInicial.setLayout(new GridBagLayout());
        JButton Opcao1 = new JButton("Criar Projetos e Associar pessoas");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuInicial.add(Opcao1, gbc);
        JButton Opcao2 = new JButton("Editar um Projeto");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuInicial.add(Opcao2, gbc);
        JButton Opcao3 = new JButton("Listar Projetos não concluidos na data estimada");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuInicial.add(Opcao3, gbc);
        JButton Opcao4 = new JButton("Listar Projetos concluidos");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuInicial.add(Opcao4, gbc);
        JButton exit = new JButton("EXIT");
        gbc.gridx = 0;
        gbc.gridy = 4;
        menuInicial.add(exit, gbc);
        
        //MENU 1
        menu1 = new JPanel();
        menu1.setLayout(new GridBagLayout());
        JButton Opcao11 = new JButton("Criar Projeto");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 40;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu1.add(Opcao11, gbc);
        JButton Opcao12 = new JButton("Associar pessoas a um projeto");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu1.add(Opcao12, gbc);
        JButton voltar1 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu1.add(voltar1, gbc);
        JButton exit1 = new JButton("EXIT");
        gbc.gridx = 0;
        gbc.gridy = 3;
        menu1.add(exit1, gbc);
        
        //MENU 11 -> Criar Projeto
        menu11 = new JPanel();
        menu11.setLayout(new GridBagLayout());
        JLabel labelNomeProjeto = new JLabel("Nome:");
        JLabel labelAcronimo = new JLabel("Acronimo:");
        JLabel labelDataInicial = new JLabel("Data Inicial:");
        JLabel labelDataFinal = new JLabel("Data Final:");
        JLabel labelDuracao = new JLabel("Duração:");
        JTextField nomeProjeto = new JTextField(15);
        JTextField acronimo = new JTextField(15);
        JTextField dataInicial = new JTextField(15);
        JTextField dataFinal = new JTextField(15);
        JTextField duracao = new JTextField(15);
        
        JPanel panelNomeProjeto = new JPanel();        
        JPanel panelAcronimo = new JPanel();
        JPanel panelDataInicial = new JPanel();
        JPanel panelDataFinal = new JPanel();
        JPanel panelDuracao = new JPanel();
        JPanel panelResponsavel = new JPanel();
        
        panelNomeProjeto.add(labelNomeProjeto);
        panelNomeProjeto.add(nomeProjeto);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(panelNomeProjeto, gbc);
        panelAcronimo.add(labelAcronimo);
        panelAcronimo.add(acronimo);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(panelAcronimo, gbc);
        panelDataInicial.add(labelDataInicial);
        panelDataInicial.add(dataInicial);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(panelDataInicial, gbc);
        panelDataFinal.add(labelDataFinal);
        panelDataFinal.add(dataFinal);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(panelDataFinal, gbc);
        panelDuracao.add(labelDuracao);
        panelDuracao.add(duracao);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(panelDuracao, gbc);
        JButton buttonAddProjeto = new JButton("Adicionar Projeto");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(buttonAddProjeto, gbc);
        JButton voltar11 = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(voltar11, gbc);
        String nomesDocentes[] = getNomesDocentes(docentes);
        JComboBox comboPessoas = new JComboBox(nomesDocentes);
        panelResponsavel.add(comboPessoas);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu11.add(panelResponsavel, gbc);
        
        //Menu12 -> Associar pessoas a um projeto
        menu12 = new JPanel();
        menu12.setLayout(new GridBagLayout());
        JLabel labelString = new JLabel("Escoha um dos projetos para associar pessoas",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu12.add(labelString, gbc);
        String nomesProjetos[] = getNomesProjetos(projetos);
        JComboBox comboProjetos = new JComboBox(nomesProjetos);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu12.add(comboProjetos, gbc);
        JLabel labelRestante = new JLabel("Escoha mais pessoas para realizar o projeto",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu12.add(labelRestante, gbc);
        
        DefaultListModel listaPessoas = new DefaultListModel();
        for(int k = 0; k < pessoas.size(); k++){
            listaPessoas.addElement(pessoas.get(k).getNome());
        }
        JList lista = new JList(listaPessoas);
        JScrollPane listaScroll = new JScrollPane(lista);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu12.add(listaScroll, gbc);
        JButton addPessoas = new JButton("Adicionar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu12.add(addPessoas, gbc);
        JButton voltar12 = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu12.add(voltar12, gbc);
        
        //Menu2 -> Editar Projetos
        menu2 = new JPanel();
        menu2.setLayout(new GridBagLayout());
        JButton Opcao21 = new JButton("Listar, criar e eliminar tarefas");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao21, gbc);
        JButton Opcao22 = new JButton("Atribuir Tarefas");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao22, gbc);
        JButton Opcao23 = new JButton("Atualizar taxa de execução");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao23, gbc);
        JButton Opcao24 = new JButton("Ver Tarefas não iniciadas");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao24, gbc);
        JButton Opcao25 = new JButton("Ver Tarefas não concluída nas data estimada");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao25, gbc);
        JButton Opcao26 = new JButton("Ver Tarefas Concluídas");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao26, gbc);
        JButton Opcao27 = new JButton("Indicar Custo do Projeto");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao27, gbc);
        JButton Opcao28 = new JButton("Terminar Projeto");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(Opcao28, gbc);
        JButton voltar2 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu2.add(voltar2, gbc);
        JButton exit2 = new JButton("EXIT");
        gbc.gridx = 1;
        gbc.gridy = 4;
        menu2.add(exit2, gbc);

        //Menu21 -> Listar, criar e eliminar tarefas
        menu21 = new JPanel();
        menu21.setLayout(new GridBagLayout());
        JButton Opcao211 = new JButton("Ver Tarefas de um Projeto");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu21.add(Opcao211, gbc);
        JButton Opcao212 = new JButton("Criar Tarefas para um Projeto");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu21.add(Opcao212, gbc);
        JButton Opcao213 = new JButton("Eliminar Tarefas de um Projeto");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu21.add(Opcao213, gbc);
        JButton voltar21 = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu21.add(voltar21, gbc);

        //Menu211 -> Ver Tarefas de um Projeto
        JPanel menu211 = new JPanel();
        menu211.setLayout(new GridBagLayout());
        String nomesProjetos2[] = getNomesProjetos(projetos);
        JComboBox comboProjetos2 = new JComboBox(nomesProjetos2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu211.add(comboProjetos2, gbc);
        JLabel verTarefas = new JLabel("Carregue no mostrar");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu211.add(verTarefas, gbc);
        JButton mostrarTarefas = new JButton("Mostrar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu211.add(mostrarTarefas, gbc);
        JButton voltar211 = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu211.add(voltar211, gbc);
        
        //Menu212 -> Criar Tarefas para um Projeto
        JPanel menu212 = new JPanel();
        menu212.setLayout(new GridBagLayout());
        JLabel labelProjeto212 = new JLabel("Projeto:");
        JLabel labelTarefa212 = new JLabel("Tarefa:");
        JLabel labelResponsavel212 = new JLabel("Responsável:");
        JPanel panelProjeto212 = new JPanel();
        JPanel panelTarefa212 = new JPanel();
        JPanel panelResponsavel212 = new JPanel();
        
        String nomesProjetos3[] = getNomesProjetos(projetos);
        JComboBox comboProjetos3 = new JComboBox(nomesProjetos3);
        panelProjeto212.add(labelProjeto212);
        panelProjeto212.add(comboProjetos3);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(panelProjeto212, gbc);
        String[] nomesPessoasProjeto = {" "};
        JComboBox comboPP = new JComboBox(nomesPessoasProjeto);
        panelResponsavel212.add(labelResponsavel212);
        panelResponsavel212.add(comboPP);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(panelResponsavel212, gbc);
        String[] tiposTarefa = {"Design", "Desenvolvimento", "Documentação"};
        JComboBox selecionarTarefa = new JComboBox(tiposTarefa);
        panelTarefa212.add(labelTarefa212);
        panelTarefa212.add(selecionarTarefa);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(panelTarefa212, gbc);
        JLabel tarefaDataInicial = new JLabel("Data Inicial:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(tarefaDataInicial, gbc);
        JTextField tDataInicial = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(tDataInicial, gbc);
        JLabel tarefaDataFinal = new JLabel("Data Final:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(tarefaDataFinal, gbc);
        JTextField tDataFinal = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(tDataFinal, gbc);
        JLabel tarefaDuracao = new JLabel("Duração:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(tarefaDuracao, gbc);
        JTextField tDuracao = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(tDuracao, gbc);
        JButton addTarefas = new JButton("Adcionar");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(addTarefas, gbc);
        JButton voltar212 = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu212.add(voltar212, gbc);
        
        //Menu213 -> Eliminar Tarefas de um Projeto
        JPanel menu213 = new JPanel();
        menu213.setLayout(new GridBagLayout());
        String nomesProjetos4[] = getNomesProjetos(projetos);
        JComboBox comboProjetos4 = new JComboBox(nomesProjetos4);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu213.add(comboProjetos4, gbc);
        String tarefasProjetos[] = getTarefasProjetos(projetos, projetos.get(0).getNome());
        JComboBox comboTP = new JComboBox(tarefasProjetos);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu213.add(comboTP, gbc);
        JButton eliminar = new JButton("Eliminar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu213.add(eliminar, gbc);
        JButton voltar213 = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu213.add(voltar213, gbc);
        
        //Menu22 -> Atribuir Tarefas a Pessoas
        menu22 = new JPanel();
        menu22.setLayout(new GridBagLayout());
        JLabel labelStringP = new JLabel("Escoha um dos projetos",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(labelStringP, gbc);
        JComboBox comboProjetos22 = new JComboBox(nomesProjetos);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(comboProjetos22, gbc);
        JLabel labelStringT = new JLabel("Escoha uma das Tarefas",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(labelStringT, gbc);
        String tarefasProjetos22[] = getTarefasProjetos(projetos, projetos.get(0).getNome());
        JComboBox comboTarefas22 = new JComboBox(tarefasProjetos22);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(comboTarefas22, gbc);
        JLabel labelRestanteP = new JLabel("Escoha as Pessoas para Tarefas",SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(labelRestanteP, gbc);   
        
        DefaultListModel listaPessoas22 = new DefaultListModel();

        JList lista22 = new JList(listaPessoas22);
        JScrollPane listaScroll22 = new JScrollPane(lista22);
        listaScroll22.setPreferredSize(new Dimension(200, 200));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(listaScroll22, gbc);
        JButton addPessoas22 = new JButton("Adicionar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(addPessoas22, gbc);
        JButton voltar22 = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu22.add(voltar22, gbc);

        //Menu23 -> Atualizar taxa de execução
        menu23 = new JPanel();
        menu23.setLayout(new GridBagLayout());
        JLabel labelString23 = new JLabel("Escoha um dos projetos: ", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu23.add(labelString23, gbc);
        String nomesProjetos23[] = getNomesProjetos(projetos);
        JComboBox comboProjetos23 = new JComboBox(nomesProjetos23);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu23.add(comboProjetos23, gbc);
        JLabel labelString23_2 = new JLabel("Escoha uma das tarefas: ", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu23.add(labelString23_2, gbc);
        String nomesTarefas[] = {"Design", "Desenvolvimento", "Documentação"};
        JComboBox comboTarefas = new JComboBox(nomesTarefas);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu23.add(comboTarefas, gbc);
        String taxaAtual23 = "0 - 100%";
        JTextField novaTaxaT = new JTextField(taxaAtual23);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu23.add(novaTaxaT, gbc);
        JButton atualizarTaxa = new JButton("Atualizar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu23.add(atualizarTaxa, gbc);
        JButton voltar23 = new JButton("Voltar");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu23.add(voltar23, gbc);
        
        //Menu24 -> Listar tarefas não iniciadas
        menu24 = new JPanel();
        menu24.setLayout(new GridBagLayout());
        String nomesProjectos24[] = getNomesProjetos(projetos);
        JComboBox comboProjetos24 = new JComboBox(nomesProjectos24);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu24.add(comboProjetos24, gbc);
        JLabel tarefas24 = new JLabel("Selecione um Projeto");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu24.add(tarefas24, gbc);
        JButton voltar24 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu24.add(voltar24, gbc);
        
        //Menu25 -> Listar Tarefas Concluídas
        menu25 = new JPanel();
        menu25.setLayout(new GridBagLayout());
        String nomesProjectos25[] = getNomesProjetos(projetos);
        JComboBox comboProjetos25 = new JComboBox(nomesProjectos25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu25.add(comboProjetos25, gbc);
        JLabel tarefasInacabadas25 = new JLabel("Selecione um Projeto");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu25.add(tarefasInacabadas25, gbc);
        JButton voltar25 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu25.add(voltar25, gbc);
        
        //Menu26 -> Listar Tarefas Concluídas
        menu26 = new JPanel();
        menu26.setLayout(new GridBagLayout());
        String nomesProjectos26[] = getNomesProjetos(projetos);
        JComboBox comboProjetos26 = new JComboBox(nomesProjectos26);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu26.add(comboProjetos26, gbc);
        JLabel tarefasConcluidas26 = new JLabel("Selecione um Projeto");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu26.add(tarefasConcluidas26, gbc);
        JButton voltar26 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu26.add(voltar26, gbc);
        
        //Menu27 -> Indicar Custo do Projeto
        menu27 = new JPanel();
        menu27.setLayout(new GridBagLayout());
        JLabel custosText = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu27.add(custosText, gbc);
        JButton voltar27 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu27.add(voltar27, gbc);        

        //Menu 28 -> Terminar Projetos
        menu28 = new JPanel();
        menu28.setLayout(new GridBagLayout());
        String nomesProjectos28[] = getNomesProjetos(projetos);
        JComboBox comboProjetos28 = new JComboBox(nomesProjectos28);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu28.add(comboProjetos28, gbc);
        JPanel dataPanel28 = new JPanel();
        dataPanel28.setLayout(new GridBagLayout());
        
        JLabel dataLabel28 = new JLabel("Coloque uma data [DD/MM/YYYY]:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dataPanel28.add(dataLabel28, gbc);
        JTextField dataText28 = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dataPanel28.add(dataText28, gbc);
        JButton validarData28 = new JButton("Validar Data");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dataPanel28.add(validarData28, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        menu28.add(dataPanel28, gbc);
        JButton terminar28Button = new JButton("Terminar Projeto");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu28.add(terminar28Button, gbc);
        JButton voltar28 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu28.add(voltar28, gbc);
        
        //MENU 3 -> Listar Projetos não concluídos na data prevista
        menu3 = new JPanel();
        menu3.setLayout(new GridBagLayout());
        JLabel labelProjetosInacabados = new JLabel("Projetos não concluídos na data prevista: \n", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu3.add(labelProjetosInacabados, gbc);
        JButton voltar3 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu3.add(voltar3, gbc);
        JButton exit3 = new JButton("EXIT");
        gbc.gridx = 0;
        gbc.gridy = 2;
        menu3.add(exit3, gbc);
        
        //MENU 4 -> Listar Projetos concluídos
        menu4 = new JPanel();
        menu4.setLayout(new GridBagLayout());
        JLabel labelProjetosConcluidos = new JLabel("Projetos concluídos: \n", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu4.add(labelProjetosConcluidos, gbc);
        JButton voltar4 = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu4.add(voltar4, gbc);
        JButton exit4 = new JButton("EXIT");
        gbc.gridx = 0;
        gbc.gridy = 2;
        menu4.add(exit4, gbc);
        
        //FUNCIONALIDADES BOTÕES
        
        validarData.addActionListener((ActionEvent e) -> {
            if(verificaData(dataText.getText()) == true) {
                String[] infoData = dataText.getText().split("/");
                int dia = Integer.parseInt(infoData[0]);
                int mes = Integer.parseInt(infoData[1]) ;
                int ano = Integer.parseInt(infoData[2]);
                date.set(ano, mes, dia);
                JOptionPane.showMessageDialog(null, "Data Guardada com Sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                dataPanel.setVisible(false);
                frame.add(menuInicial);
                menuInicial.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Formato Errado!","Erro ao adicionar Data", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        buttonAddProjeto.addActionListener((ActionEvent e) -> {
            if (nomeProjeto.getText().equals("") || acronimo.getText().equals("") || dataInicial.getText().equals("") || dataFinal.getText().equals("") || duracao.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos os campos têm de estar completos","Erro ao criar Projeto", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            try {
                String nomeRegistar = nomeProjeto.getText();
                if (projetos.size() > 0) {
                    if(verificarNome(nomeRegistar,projetos) == 0){
                        JOptionPane.showMessageDialog(null, "Esse nome já foi usado!!!","Erro ao criar Projeto", JOptionPane.WARNING_MESSAGE);
                    }
                }
                String acronimoRegistar = acronimo.getText();
                String dataInicialRegistar = dataInicial.getText();
                String dataFinalRegistar = dataFinal.getText();
                int duracaoRegistar = Integer.parseInt(duracao.getText());
                for(int x = 0; x < pessoas.size(); x++){
                    Object responsavel = comboPessoas.getSelectedItem();
                    if(pessoas.get(x).getNome().equals(responsavel)){
                        projetos.add(new Projeto(nomeRegistar, acronimoRegistar, dataInicialRegistar, dataFinalRegistar, duracaoRegistar, pessoas.get(x)));
                    }
                }
                
                comboProjetos.addItem(nomeRegistar);
                comboProjetos2.addItem(nomeRegistar);
                comboProjetos3.addItem(nomeRegistar);
                comboProjetos4.addItem(nomeRegistar);
                
                comboProjetos23.addItem(nomeRegistar);
                comboProjetos24.addItem(nomeRegistar);
                comboProjetos25.addItem(nomeRegistar);
                comboProjetos26.addItem(nomeRegistar);
                comboProjetos28.addItem(nomeRegistar);
                
                JOptionPane.showMessageDialog(null, "Projeto criado com sucesso!","Parabéns", JOptionPane.PLAIN_MESSAGE);
                menu11.setVisible(false);
                frame.add(menuInicial);
                menuInicial.setVisible(true);
            }
            catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Duração é em meses, logo têm de ser numeros","Erro ao criar Projeto", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        //MENU 1 -> Criar Projetos e associar Pessoas
        Opcao1.addActionListener((ActionEvent e) -> {           
            menuInicial.setVisible(false);
            frame.add(menu1);
            menu1.setVisible(true);          
        });

        //Menu 11 -> Criar Projeto
        Opcao11.addActionListener((ActionEvent e) -> {           
            menu1.setVisible(false);
            frame.add(menu11);
            menu11.setVisible(true);          
        });

        //Menu 12 -> Associar pessoas a um projeto
        Opcao12.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                Object projetoSelect12 = comboProjetos.getSelectedItem();
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect12)){
                        for(int j = 0; j < pessoas.size(); j++){
                            if(projetos.get(i).getResponsavel().getNome().equals(pessoas.get(j).getNome())){
                                listaPessoas.remove(j);
                            }
                        }
                    }
                }
                menu1.setVisible(false);
                frame.add(menu12);
                menu12.setVisible(true);
            }
        });
        
        comboProjetos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object projetoSelect12 = comboProjetos.getSelectedItem();
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect12)){
                        listaPessoas.removeAllElements();
                        for(int j = 0; j < pessoas.size(); j++){
                            if(projetos.get(i).getResponsavel().getNome().equals(pessoas.get(j).getNome())){
                            }
                            else{
                                listaPessoas.addElement(pessoas.get(j).getNome());
                            }
                        }
                    }
                }
            }
        });        
        
        addPessoas.addActionListener((ActionEvent e) -> {           
            Object projetoSelect = comboProjetos.getSelectedItem();
            List pessoaSelect = lista.getSelectedValuesList();
            
            for(int i = 0; i < projetos.size(); i++){
                if(projetos.get(i).getNome().equals(projetoSelect)){
                    for(int j = 0; j < pessoas.size(); j++){
                        for(int k= 0; k < pessoaSelect.size(); k++){
                            if(pessoas.get(j).getNome().equals(pessoaSelect.get(k))){
                                for(int r = 0; r < bolseiros.size(); r++){
                                    if(pessoaSelect.get(k).equals(bolseiros.get(r).getNome())){
                                        if(bolseiros.get(r).getNProj() == 0){
                                            projetos.get(i).addPessoa(pessoas.get(j));       
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Pessoa " + pessoas.get(j).getNome() + " está incluido em um Projeto e não pode estar em mais nenhum!! Escolha outra pessoa","Erro", JOptionPane.WARNING_MESSAGE);
                                            return;
                                        }
                                    }
                                    else{
                                        projetos.get(i).addPessoa(pessoas.get(j));
                                       
                                    }          
                                }    
                            }
                        }
                    }                    
                }                
            }
            JOptionPane.showMessageDialog(null, "Pessoas adicionadas ao Projeto!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
        });
        
        //MENU 2 -> Editar Projetos
        Opcao2.addActionListener((ActionEvent e) -> {           
            menuInicial.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);
        });
        
        //Menu21 -> 
        Opcao21.addActionListener((ActionEvent e) -> {
           menu2.setVisible(false);
           frame.add(menu21);
           menu21.setVisible(true);
        });
        
        Opcao211.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                menu21.setVisible(false);
                frame.add(menu211);
                menu211.setVisible(true);
            }
        });
        
        mostrarTarefas.addActionListener((ActionEvent e) -> {
            verTarefas.setText(" ");
            Object projetoSelect = comboProjetos2.getSelectedItem();
            System.out.println(projetoSelect);
            for(int i = 0; i < projetos.size(); i++){
                if(projetos.get(i).getNome().equals(projetoSelect)){
                    for(int j = 0; j < projetos.get(i).getTarefasProjeto().size(); j++){
                        verTarefas.setText(verTarefas.getText() + " - " + projetos.get(i).getTarefasProjeto().get(j).getNome());
                    }
                }
            }
            if(verTarefas.getText().equals(" ")){
                verTarefas.setText("Sem Tarefas");               
            }
        }); 
        
        Opcao212.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro", JOptionPane.WARNING_MESSAGE);
            }
            else{
                comboPP.removeAllItems();
                String pessoasProjetos212[] = getPessoasProjetos(projetos, projetos.get(0).getNome());
                for(int k = 0; k < pessoasProjetos212.length; k++){
                    if(pessoasProjetos212[k] != null){
                        comboPP.addItem(pessoasProjetos212[k]);
                    }                    
                }
                menu21.setVisible(false);
                frame.add(menu212);
                menu212.setVisible(true);
            }
        });
        
        comboProjetos3.addActionListener(new ActionListener(){           
            public void actionPerformed(ActionEvent e){
                comboPP.removeAllItems();
                Object projetoSelect = comboProjetos3.getSelectedItem();
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect)){
                        for(int j = 0; j < projetos.get(i).getPessoasProjeto().size(); j++){
                            comboPP.addItem(projetos.get(i).getPessoasProjeto().get(j).getNome());
                        }                  
                    }    
                }
            }
        });
        
        addTarefas.addActionListener((ActionEvent e) -> {
            Object projetoSelect = comboProjetos3.getSelectedItem();
            Object pessoaSelect = comboPP.getSelectedItem();
            Object tipoT = selecionarTarefa.getSelectedItem();
            String dataInicialTarefa = tDataInicial.getText();
            String dataFinalTarefa = tDataFinal.getText();
            int duracaoTarefa = Integer.parseInt(tDuracao.getText());
            for(int i = 0; i < projetos.size(); i++){
                if(projetos.get(i).getNome().equals(projetoSelect)){
                    for(int j = 0; j < pessoas.size(); j++){
                        if(pessoaSelect.equals(pessoas.get(j).getNome())){
                            if(pessoas.get(j).getCarga() < 1){
                                if(tipoT.equals("Desenvolvimento")){
                                    projetos.get(i).addTarefa(new Desenvolvimento(dataInicialTarefa, dataFinalTarefa, duracaoTarefa, 0, pessoas.get(j)));
                                    pessoas.get(j).addCarga(1);
                                    System.out.println("LISTA TAREFAS NESTE PROJETO");
                                    projetos.get(i).printTarefas();
                                }
                                else if(tipoT.equals("Design")){
                                    Design t = new Design(dataInicialTarefa, dataFinalTarefa, duracaoTarefa, 0, pessoas.get(j));
                                    projetos.get(i).addTarefa(t);
                                    pessoas.get(j).addCarga(1);
                                    //projetos.get(i).addTarefa(new Design(dataInicialTarefa, dataFinalTarefa, duracaoTarefa, 0, pessoas.get(j)));
                                    System.out.println("LISTA TAREFAS NESTE PROJETO");
                                    projetos.get(i).printTarefas();
                                }
                                else{
                                    projetos.get(i).addTarefa(new Documentacao(dataInicialTarefa, dataFinalTarefa, duracaoTarefa, 0, pessoas.get(j)));
                                    System.out.println("LISTA TAREFAS NESTE PROJETO");
                                    projetos.get(i).printTarefas();
                                }
                            }
                        }
                    }                  
                }             
            }
            menu212.setVisible(false);
            frame.add(menu21);
            menu21.setVisible(true);
        });
        
        Opcao213.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro", JOptionPane.WARNING_MESSAGE);
            }
            else{
                menu21.setVisible(false);
                frame.add(menu213);
                menu213.setVisible(true);
            }
        });
        
        comboProjetos4.addActionListener(new ActionListener(){           
            public void actionPerformed(ActionEvent e){
                comboTP.removeAllItems();
                String nome = " ";
                Object projetoSelect = comboProjetos4.getSelectedItem();
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect)){
                        nome = projetos.get(i).getNome();
                    }
                }
                
                String nomesTP[] = getTarefasProjetos(projetos, nome);
                for(int j = 0; j < nomesTP.length; j++){
                    comboTP.addItem(nomesTP[j]);
                }
            }
        });
        
        eliminar.addActionListener((ActionEvent e) -> {
            Object projetoSelect = comboProjetos3.getSelectedItem();
            Object tarefaSelect = comboTP.getSelectedItem();
            for(int i = 0; i < projetos.size(); i++){
                if(projetos.get(i).getNome().equals(projetoSelect)){
                    for(int j = 0; j < projetos.get(i).getTarefasProjeto().size(); j++){
                        if(tarefaSelect.equals(projetos.get(i).getTarefasProjeto().get(j).getNome())){
                             projetos.get(i).removerTarefa(j);
                        }
                    }                  
                }             
            }
            menu213.setVisible(false);
            frame.add(menu21);
            menu21.setVisible(true);
        });;
        
        //MENU 22
        Opcao22.addActionListener((ActionEvent e) -> {
            String tarefasProjetosM22[] = getTarefasProjetos(projetos, projetos.get(0).getNome());
            for(int j = 0; j < tarefasProjetosM22.length; j++){
                comboTarefas22.addItem(tarefasProjetosM22[j]);
            }
            
            String pessoasProjetos22[] = getPessoasProjetos(projetos, projetos.get(0).getNome());
            for(int k = 0; k < pessoasProjetos22.length; k++){
                listaPessoas22.addElement(pessoasProjetos22[k]);
            }
            
            menu2.setVisible(false);
            frame.add(menu22);
            menu22.setVisible(true);
        });
        
        comboProjetos22.addActionListener(new ActionListener(){           
            public void actionPerformed(ActionEvent e){
                listaPessoas22.removeAllElements();
                comboTarefas22.removeAllItems();
                String nome = " ";
                Object projetoSelect = comboProjetos22.getSelectedItem();
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect)){
                        nome = projetos.get(i).getNome();
                    }
                }
                
                String tarefasProjetos22[] = getTarefasProjetos(projetos, nome);
                for(int j = 0; j < tarefasProjetos22.length; j++){
                    comboTarefas22.addItem(tarefasProjetos22[j]);
                }               
                
                String pessoasProjetos22[] = getPessoasProjetos(projetos, nome);
                for(int k = 0; k < pessoasProjetos22.length; k++){
                    listaPessoas22.addElement(pessoasProjetos22[k]);
                }
            }
        });
        
        addPessoas22.addActionListener((ActionEvent e) -> {           
            Object projetoSelect = comboProjetos22.getSelectedItem();
            Object tarefaSelect = comboTarefas22.getSelectedItem();
            List pessoaSelect = lista22.getSelectedValuesList();
            
            for(int i = 0; i < projetos.size(); i++){
                if(projetos.get(i).getNome().equals(projetoSelect)){
                    for(int j = 0; j < pessoas.size(); j++){
                        for(int k = 0; k < pessoaSelect.size(); k++){
                            if(pessoas.get(j).getNome().equals(pessoaSelect.get(k))){
                                for(int l = 0; l<projetos.get(i).getTarefasProjeto().size(); l++){
                                    if(tarefaSelect.equals(projetos.get(i).getTarefasProjeto().get(l).getNome())){
                                        if(pessoas.get(j).getCarga() + projetos.get(i).getTarefasProjeto().get(l).getEsforco() <= 1){
                                            projetos.get(i).getTarefasProjeto().get(l).addPessoaTarefa(pessoas.get(j));
                                            pessoas.get(j).addCarga(projetos.get(i).getTarefasProjeto().get(l).getEsforco());
                                            //JOptionPane.showMessageDialog(null, "Pessoa " + pessoas.get(j).getNome() +  " adicionada à Tarefa!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Pessoa " + pessoas.get(j).getNome() + " já tem a carga toda!! Escolha outra pessoa", "Erro ao atribuir Tarefas Projeto", JOptionPane.PLAIN_MESSAGE);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }                    
                }                
            }
        });

        //Menu 23 -> Atualizar taxa de execução
        Opcao23.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else{
                atualizarTaxa.setEnabled(false);
                menu2.setVisible(false);
                frame.add(menu23);
                menu23.setVisible(true);
            }
        });

        //Botões MENU23
        comboTarefas.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Object projetoSelect23_2 = comboProjetos23.getSelectedItem();
                String tarefaSelecionada23_2 = (String) comboTarefas.getSelectedItem();

                for(int i = 0; i < projetos.size(); i++ ){
                    if(projetos.get(i).getNome().equals(projetoSelect23_2)){
                        ArrayList<Tarefa> tarefasProjeto_2 = projetos.get(i).getTarefasProjeto();
                        if(tarefasProjeto_2.size() >= 1){
                            for(int j = 0; j < tarefasProjeto_2.size(); j++){
                                if(tarefasProjeto_2.get(j).getNome().equals(tarefaSelecionada23_2)){
                                    novaTaxaT.setText(Double.toString(tarefasProjeto_2.get(j).getTaxa()));
                                    atualizarTaxa.setEnabled(true);
                                }
                                else{
                                    atualizarTaxa.setEnabled(false);
                                }
                            }
                        }
                        else{
                            atualizarTaxa.setEnabled(false);
                        }
                    }
                }
            }
        });

        atualizarTaxa.addActionListener((ActionEvent e) -> {
            String texto23 = novaTaxaT.getText();
            Object projetoSelect23 = comboProjetos.getSelectedItem();
            String tarefaSelecionada23 = (String) comboTarefas.getSelectedItem();
            try{
                int numero23 = Integer.parseInt(texto23);
                if(numero23 > 0 && numero23 <= 100){
                    //Atualizar a taxa
                    for(int i = 0; i < projetos.size(); i++ ){
                        if(projetos.get(i).getNome().equals(projetoSelect23)){
                            ArrayList<Tarefa> tarefasProjeto = projetos.get(i).getTarefasProjeto();
                            System.out.println(tarefasProjeto);
                            for(int j = 0; j < tarefasProjeto.size(); j++){
                                if(tarefasProjeto.get(j).getNome().equals(tarefaSelecionada23)){
                                    tarefasProjeto.get(j).atualizaTaxa(numero23);
                                }
                            }
                        }
                        else{
                            for(int k = 0; k < projetosConcluidos.size(); k++ ){
                                if(projetos.get(i).getNome().equals(projetoSelect23)){
                                    JOptionPane.showMessageDialog(null, "Projeto já Concluído!","Erro ao atualizar a taxa", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                    }
                }
                else{
                    //Não está dentro do range
                    JOptionPane.showMessageDialog(null, "Número introduzido fora de range!","Erro ao atualizar a taxa", JOptionPane.WARNING_MESSAGE);
                }

            }catch(NumberFormatException nfe){
                //Não é numero
                JOptionPane.showMessageDialog(null, "Texto introduzido não é um número!","Erro ao atualizar a taxa", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        //Botões Menu 24 -> Listar Tarefas não iniciadas
        Opcao24.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro", JOptionPane.WARNING_MESSAGE);
            }
            else{
                menu2.setVisible(false);
                frame.add(menu24);
                menu24.setVisible(true);
            }
        });
        
        comboProjetos24.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tarefas24.setText("Selecione um projeto!");
                Object projetoSelect24 = comboProjetos25.getSelectedItem();
                
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect24)){
                        if(projetos.get(i).getTarefasProjeto().size() < 1){
                            //Não há tarefas no Projeto
                            tarefas24.setText("Não há tarefas no projeto!");
                        }
                        else{
                            for(int j = 0; j < projetos.get(i).getTarefasProjeto().size(); j++){
                                if(projetos.get(i).getTarefasProjeto().get(j).getTaxa() < 0.1){
                                    tarefas24.setText(tarefas24.getText() + projetos.get(i).getTarefasProjeto().get(j).getNome() + ";     ");
                                }
                            }
                        }
                    }
                }
            }
        }); 
        
        //Botões Menu 25 -> Listar Tarefas Inacabadas na data
        Opcao25.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro", JOptionPane.WARNING_MESSAGE);
            }
            else{
                menu2.setVisible(false);
                frame.add(menu25);
                menu25.setVisible(true);
            }
        });

        comboProjetos25.addActionListener(new ActionListener(){           
            public void actionPerformed(ActionEvent e){
                tarefasInacabadas25.setText("Selecione um projeto!");
                Object projetoSelect25 = comboProjetos25.getSelectedItem();
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect25)){
                        if(projetos.get(i).getTarefasProjeto().size() < 1){
                            //Não há tarefas no Projeto
                            tarefasInacabadas25.setText("Não há tarefas no projeto!");
                        }
                        else{
                            for(int j = 0; j < projetos.get(i).getTarefasProjeto().size(); j++){
                                if(projetos.get(i).getTarefasProjeto().get(j).getTaxa() < 100){
                                    //VERIFICAR SE JÁ PASSOU O PRAZO
                                    //if(projetos.get(i).getTarefasProjeto().get(j).getDataFinal()
                                    
                                    tarefasInacabadas25.setText(tarefasInacabadas25.getText() + projetos.get(i).getTarefasProjeto().get(j).getNome() + ";     ");
                                }
                            }
                        }
                    }
                }
            }
        });
        
        //Botões Menu 26 -> Listar Tarefas Concluídas
        Opcao26.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro", JOptionPane.WARNING_MESSAGE);
            }
            else{
                menu2.setVisible(false);
                frame.add(menu26);
                menu26.setVisible(true);
            }
        });

        comboProjetos26.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                Object projetoSelect26 = comboProjetos26.getSelectedItem();
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoSelect26)){
                        if(projetos.get(i).getTarefasProjeto().size() < 1){
                            //Não há tarefas no Projeto
                            tarefasConcluidas26.setText("Não há tarefas no projeto!");
                        }
                        else{
                            for(int j = 0; j < projetos.get(i).getTarefasProjeto().size(); j++){
                                if(projetos.get(i).getTarefasProjeto().get(j).getTaxa() == 100){
                                    tarefasConcluidas26.setText(tarefasConcluidas26.getText() + projetos.get(i).getTarefasProjeto().get(j).getNome() + "\n\t");
                                }
                            }
                        }
                    }
                }
            }
        });

        //Menu 27 -> Indicar Custo do Projeto
        Opcao27.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos
                JOptionPane.showMessageDialog(null, "Não há projetos!","Erro", JOptionPane.WARNING_MESSAGE);
            }
            else{
                String stringCusto;
                custosText.setText(" ");
                for(int i = 0; i < projetos.size(); i++){
                    stringCusto = "<html>" + "<br>" + (custosText.getText() + projetos.get(i).getNome() + ": " + getCustoProjeto(projetos, projetos.get(i).getNome())) + "<br/>" + "<html/>";
                    custosText.setText(stringCusto);
                }
                menu2.setVisible(false);
                frame.add(menu27);
                menu27.setVisible(true);
            }
        });
       
        //Menu 28 -> Terminar o Projeto
        Opcao28.addActionListener((ActionEvent e) -> {
            if(projetos.size() < 1){
                //Não há projetos para Terminar
                JOptionPane.showMessageDialog(null, "Não há projetos para Terminar!","Erro ao terminar Projeto!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                terminar28Button.enable(false);
                menu2.setVisible(false);
                frame.add(menu28);
                menu28.setVisible(true);
            }
        });
        
        validarData28.addActionListener((ActionEvent e) -> {
            if(verificaData(dataText28.getText()) == true) {
                String[] infoData2 = dataText28.getText().split("/");
                int dia2 = Integer.parseInt(infoData2[0]);
                int mes2 = Integer.parseInt(infoData2[1]) ;
                int ano2 = Integer.parseInt(infoData2[2]);
                date2.set(ano2, mes2, dia2);
                
                //Só passando pelo botão mudava para ativo o aspeto
                terminar28Button.enable(true);
            }else{
                terminar28Button.enable(false);
                JOptionPane.showMessageDialog(null, "Formato Errado!","Erro ao adicionar Data", JOptionPane.WARNING_MESSAGE);
            }
        });

        terminar28Button.addActionListener((ActionEvent e) -> {
            Object projetoTerminar28 = comboProjetos.getSelectedItem();
            if(projetos.size() < 1){
                //Não há projetos para Terminar
                JOptionPane.showMessageDialog(null, "Não há projetos para Terminar!","Erro ao terminar Projeto!", JOptionPane.WARNING_MESSAGE);
            }
            else{
                for(int i = 0; i < projetos.size(); i++){
                    if(projetos.get(i).getNome().equals(projetoTerminar28)){
                        if(projetos.get(i).getTarefasProjeto().size() > 0){
                            for(int j = 0; j < projetos.get(i).getTarefasProjeto().size(); j++){
                                if(projetos.get(i).getTarefasProjeto().get(j).getTaxa() < 100 ){
                                    JOptionPane.showMessageDialog(null, "Ainda tem tarefas por completar!","Erro ao terminar Projeto!", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                                else{
                                    //Terminar Projeto e adicionar a lista dos concluidos
                                    if(date.getTimeInMillis()+ (projetos.get(i).getDuracao() * 86400000) <= date2.getTimeInMillis()){
                                        projetosConcluidos.add(projetos.get(i));
                                        projetos.remove(projetos.get(i));
    //                                    comboProjetos.removeItem(nomesProjetos[i]);
    //                                    comboProjetos2.removeItem(nomesProjetos[i]);
    //                                    comboProjetos3.removeItem(nomesProjetos[i]);
    //                                    comboProjetos4.removeItem(nomesProjetos[i]);
    //                                    comboProjetos23.removeItem(nomesProjetos[i]);
    //                                    comboProjetos24.removeItem(nomesProjetos[i]);
    //                                    comboProjetos25.removeItem(nomesProjetos[i]);
    //                                    comboProjetos26.removeItem(nomesProjetos[i]);
    //                                    comboProjetos28.removeItem(nomesProjetos[i]);
                                        menu28.setVisible(false);
                                        frame.add(menu2);
                                        menu2.setVisible(true);
                                    }
                                    else{
                                        projetosInacabados.add(projetos.get(i));
                                        projetos.remove(projetos.get(i));
                                        menu28.setVisible(false);
                                        frame.add(menu2);
                                        menu2.setVisible(true);                                        
                                    }
                                }
                            }
                        }
                        else{
                            if(date.getTimeInMillis()+ (projetos.get(i).getDuracao() * 86400000) <= date2.getTimeInMillis()){
                                projetosConcluidos.add(projetos.get(i));
                                projetos.remove(projetos.get(i));
                                menu28.setVisible(false);
                                frame.add(menu2);
                                menu2.setVisible(true);
                            }
                            else{
                                projetosInacabados.add(projetos.get(i));
                                projetos.remove(projetos.get(i));
                                menu28.setVisible(false);
                                frame.add(menu2);
                                menu2.setVisible(true);                                        
                            }
                        }
                    }
                }
            }
        });

        //MENU 3 -> Listar Projetos Inacabados
        Opcao3.addActionListener((ActionEvent e) -> {
            labelProjetosInacabados.setText(" ");
            if(projetosInacabados.size() > 0){
                String textImprimir3;
                labelProjetosInacabados.setText(" ");
                for(int i = 0; i < projetosInacabados.size(); i++){
                    textImprimir3 = "<html>" + "<br>" + (labelProjetosInacabados.getText()+ " " + projetosInacabados.get(i).getNome() + " #Tarefas:" + projetosInacabados.get(i).getTarefasProjeto().size()) + "<br/>" + "<html/>";
                    labelProjetosInacabados.setText(textImprimir3);
                }
            }else{
                labelProjetosInacabados.setText("Sem projetos incabados no prazo!!");
            }
            menuInicial.setVisible(false);
            frame.add(menu3);
            menu3.setVisible(true);
        });
        
        //MENU 4 -> Listar Projetos Concluídos
        Opcao4.addActionListener((ActionEvent e) -> {
            labelProjetosConcluidos.setText(" ");
            if(projetosConcluidos.size() > 0){
                String textImprimir4;
                labelProjetosConcluidos.setText(" ");
                for(int i = 0; i < projetosConcluidos.size(); i++){
                    textImprimir4 = "<html>" + "<br>" + (labelProjetosConcluidos.getText()+ " " + projetosConcluidos.get(i).getNome() + " #Tarefas:" + projetosConcluidos.get(i).getTarefasProjeto().size()) + "<br/>" + "<html/>";
                    labelProjetosConcluidos.setText(textImprimir4);
                }
            }else{
                labelProjetosConcluidos.setText("Sem projetos concluídos!!");
            }
            menuInicial.setVisible(false);
            frame.add(menu4);
            menu4.setVisible(true);          
        });

        //BOTÕES VOLTAR ----------------/-----------
        voltar1.addActionListener((ActionEvent e) -> {           
            menu1.setVisible(false);
            frame.add(menuInicial);
            menuInicial.setVisible(true);
        });
        
        voltar11.addActionListener((ActionEvent e) -> {           
            menu11.setVisible(false);
            frame.add(menu1);
            menu1.setVisible(true);
        });
        
        voltar12.addActionListener((ActionEvent e) -> {           
            menu12.setVisible(false);
            frame.add(menu1);
            menu1.setVisible(true);
        });
        
        voltar2.addActionListener((ActionEvent e) -> {           
            menu2.setVisible(false);
            frame.add(menuInicial);
            menuInicial.setVisible(true);
        });
        
        voltar21.addActionListener((ActionEvent e) -> {           
            menu21.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });

        voltar211.addActionListener((ActionEvent e) -> {           
            menu211.setVisible(false);
            frame.add(menu21);
            menu21.setVisible(true);         
        });
        
        voltar212.addActionListener((ActionEvent e) -> {           
            menu212.setVisible(false);
            frame.add(menu21);
            menu21.setVisible(true);         
        });
        
        voltar213.addActionListener((ActionEvent e) -> {           
            menu213.setVisible(false);
            frame.add(menu21);
            menu21.setVisible(true);         
        });

        voltar22.addActionListener((ActionEvent e) -> {
            menu22.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });
        
        voltar23.addActionListener((ActionEvent e) -> {
            menu23.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });
        
        voltar24.addActionListener((ActionEvent e) -> {           
            menu24.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });
        
        voltar25.addActionListener((ActionEvent e) -> {           
            menu25.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });
        
        voltar26.addActionListener((ActionEvent e) -> {           
            menu26.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });
        
        voltar27.addActionListener((ActionEvent e) -> {           
            menu27.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });

        voltar28.addActionListener((ActionEvent e) -> {
            menu28.setVisible(false);
            frame.add(menu2);
            menu2.setVisible(true);         
        });

        voltar3.addActionListener((ActionEvent e) -> {        
            menu3.setVisible(false);
            frame.add(menuInicial);
            menuInicial.setVisible(true);         
        });        

        voltar4.addActionListener((ActionEvent e) -> {        
            menu4.setVisible(false);
            frame.add(menuInicial);
            menuInicial.setVisible(true);         
        });
        
        //BOTÕES EXIT ---------/----------------
        exit.addActionListener((ActionEvent e) -> {
            exit();
        });
        
        exit1.addActionListener((ActionEvent e) -> {
            exit();
        });
        
        exit2.addActionListener((ActionEvent e) -> {
            exit();
        });
        
        exit3.addActionListener((ActionEvent e) -> {
            exit();
        });
        
        exit4.addActionListener((ActionEvent e) -> {
            exit();
        });
        
    }

    /**
    * Verifica se o Projeto escolhido está nos projetos ativos do CISUC
    * @param valor - Nome do Projeto que se deseja
    * @param projetos - List que contém todos os projetos de investigação ativos do CISUC.
    * @return 1 - Falso  || 0 - Verdadeiro
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public int verificarNome(String valor, List<Projeto> projetos){
    for (int i = 0; i < projetos.size();i++) {
        if (projetos.get(i).getNome().equals(valor)){
            return 0;
        }
    }
    return 1;
    }
    
    /**
    * Retorna o nome de todos os projetos ativos no CISUC
    * @param projetos - List que contém todos os projetos de investigação ativos do CISUC.
    * @return Array com os nomes de todos os projetos ativos no CISUC
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String[] getNomesProjetos(List<Projeto> projetos) {
        String[] nomes = new String[projetos.size()];
        for (int i = 0; i < projetos.size(); i++) {
            nomes[i] = projetos.get(i).getNome();
        }
        return nomes;
    }
    
    /**
    * Retorna o nome de todas as pessoas inscritas no CISUC
    * @param pessoas - List que contém todas pessoas inscritas no CISUC.
    * @return Array com os nomes de todas as pessoas inscritas no CISUC
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String[] getNomesPessoas(List<Pessoa> pessoas) {
        String[] nomes = new String[pessoas.size()];
        for (int i = 0; i < pessoas.size(); i++) {
            nomes[i] = pessoas.get(i).getNome();
        }      
        return nomes;
    }
    
    /**
    * Retorna o nome de todas os docentes inscritos no CISUC
    * @param docentes - List que contém todos docentes inscritas no CISUC.
    * @return Array com os nomes de todas os docentes inscritas no CISUC
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String[] getNomesDocentes(List<Pessoa> docentes) {
        String[] nomes = new String[docentes.size()];
        for (int i = 0; i < docentes.size(); i++) {
            nomes[i] = docentes.get(i).getNome();
        }      
        return nomes;
    }

    /**
    * Retorna o nome[ID] de todas as tarefas que um projeto tenha
    * @param nomeProjeto - Nome do Projeto que se deseja aceder
    * @param projetos - List que contém todos os projetos de investigação ativos do CISUC.
    * @return Array com as tarefas do Projeto desejado
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public String[] getTarefasProjetos(List<Projeto> projetos, String nomeProjeto){
        
        String[] tarefasProjetos = new String[projetos.size()];        
        for (int i = 0; i < projetos.size(); i++){
            if(nomeProjeto.equals(projetos.get(i).getNome())){
                for(int j = 0; j < projetos.get(i).getTarefasProjeto().size(); j++){
                    tarefasProjetos[j] = projetos.get(i).getTarefasProjeto().get(j).getNome();
                }
            }
        }
        return tarefasProjetos;      
    }

    /**
    * Retorna o custo de um projeto ativo no CISUC
    * @param nomeProjeto - Nome do Projeto ao qual se deseja aceder
    * @param projetos - List que contém todos os projetos de investigação ativos do CISUC.
    * @return Custo de um projeto especifico
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public double getCustoProjeto(List<Projeto> projetos, String nomeProjeto){
        //carga da tarefa * custo da pessoa * duracao da tarefa
        int custoPessoa = 0;
        double esforcoTarefa;
        int duracaoTarefa;
        double custo = 0;
        for (int i = 0; i < projetos.size(); i++){
            if(nomeProjeto.equals(projetos.get(i).getNome())){
                for(int j = 0; j < projetos.get(i).getPessoasProjeto().size(); j++){
                    if(projetos.get(i).getPessoasProjeto().get(j).getCarga() != 0){
                        custoPessoa = projetos.get(i).getPessoasProjeto().get(j).getCusto();
                        for(int k = 0; k < projetos.get(i).getTarefasProjeto().size(); k++){
                            esforcoTarefa = projetos.get(i).getTarefasProjeto().get(k).getEsforco();
                            duracaoTarefa = projetos.get(i).getTarefasProjeto().get(k).getDuracao();

                            custo = (double) custo + (custoPessoa * esforcoTarefa * duracaoTarefa);
                        }
                    }
                }
            }
        }
        System.out.println(custo);
        return custo;
    }

    /**
    * Atualiza o ficheiro arquivo.dat com nova informacao
    * Remove primeiro toda a informação contida no ficheiro
    * De seguida insere a nova.
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    private void updateFichObjetos(){
        try {
            //fichObjetos.fechaEscrita();
            fichObjetos.abreLeitura("arquivo.dat");
            fichObjetos.leObjeto();
            fichObjetos.leObjeto();
            fichObjetos.fechaLeitura();
            fichObjetos.abreEscrita("arquivo.dat");
            fichObjetos.escreveObjeto(pessoas);
            fichObjetos.escreveObjeto(projetos);
            fichObjetos.escreveObjeto(projetosConcluidos);
            fichObjetos.escreveObjeto(projetosInacabados);
            fichObjetos.escreveObjeto(docentes);
            fichObjetos.escreveObjeto(bolseiros);
            fichObjetos.fechaEscrita();
                   
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
            //mensagem de erro na interface
            System.out.println("Erro ao editar o ficheiro de objetos arquivo.dat");
            System.exit(0);
        }
        //mensagem de erro na interface
    }
    
    /**
    * Funcao que termina o programa. 
    * Actualiza o ficheiro de objetos e fecha a escrita.
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    private void exit(){
//        for (int i = 0; i < projetos.size(); i++){
//            System.out.print(projetos.get(i).getNome() + "  ");
//        }
        updateFichObjetos();        
//        for (int i = 0; i < projetos.size(); i++){
//            System.out.print(projetos.get(i).getNome() + "  ");
//        }
        try {
            fichObjetos.fechaEscrita();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            //mensagem de erro na interface
            System.out.println("Erro com o ficheiro de objetos arquivo.dat");
            System.exit(0);
        }
        System.exit(0);
    }
    
    /**
    * Verifica se a data introduzida está no formato correto
    * @param verData Data a verificar
    * @return true Formato Correto || false Formato Errado
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues
    */
    public boolean verificaData(String verData){
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false);
        try{
            formatoData.parse(verData.trim());
        }catch(ParseException pe){
            return false;
        }
        return true;
    }

    /**
    * Retorna as Pessoas pertencentes ao Projeto desejado
    * @param  projetos - List que contém todos os projetos de investigação ativos do CISUC
    * @param nomeProjeto - Projeto Desejado
    * @return Array de Strings com o nome das Pessoas pertencentes ao Projeto desejado
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues

    */
    public String[] getPessoasProjetos(List<Projeto> projetos, String nomeProjeto){

            String[] tarefasProjetos = new String[projetos.size()];        
            for (int i = 0; i < projetos.size(); i++){
                if(nomeProjeto.equals(projetos.get(i).getNome())){
                    for(int j = 0; j < projetos.get(i).getPessoasProjeto().size(); j++){
                        tarefasProjetos[j] = projetos.get(i).getPessoasProjeto().get(j).getNome();
                    }
                }
            }
            return tarefasProjetos;      
        }

    /**
    * Funcao main, inicializa o programa.
    * @param args ?
    * @throws java.io.FileNotFoundException - Ficheiro não existe
    * @throws java.lang.ClassNotFoundException - Classe não existe
    * 
    * @author      Carlos Abegão
    * @author      João Rodrigues

    */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        new CISUC();
    }
}