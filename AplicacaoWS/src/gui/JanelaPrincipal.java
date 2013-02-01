package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import tratador_eventos.TratadorEventosCadastro;
import tratador_eventos.TratadorEventosConsulta;
import tratador_eventos.TratadorEventosMenu;
import tratador_eventos.TratadorEventosTabela;
import aplicacao.AcessoBanco;
import aplicacao.Contato;

public class JanelaPrincipal extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	// Elementos usado em todas as interfaces
	private MenuPrincipal menuJanela;
	private PainelPrincipal painelPrincipal;
	private AcessoBanco acessoBanco;
	private String imagemFundo;
	private String caminhoImgEditar;
	private String caminhoImgExcluir;
	private String caminhoImgCadastrar;
	private String iconeTitulo;
	private Image iconeBarra;

	// Elementos da Janela de Cadastro
	private JLabel nome = new JLabel("Nome: ");
	private JLabel apelido = new JLabel("Apelido: ");
	private JLabel telefoneResidencial = new JLabel("Tel. Residencial: ");
	private JLabel telefoneCelular = new JLabel("Tel. Cel.: ");
	private JLabel cidade = new JLabel("Cidade: ");
	private JLabel estado = new JLabel("Estado: ");
	private JTextField textoNome = new JTextField(45);
	private JTextField textoApelido = new JTextField(20);
	private JTextField textoTelRes = new JTextField(12);
	private JTextField textoTelCel = new JTextField(12);
	private JTextField textoCidade = new JTextField(25);
	private String[] estados = { "AC", "AL", "AP", "AM", "BA", "CE", "DF",
			"ES", "GO", "MA", "MT", "MS", "MG", "PR", "PB", "PA", "PE", "PI",
			"RJ", "RN", "RS", "RO", "RR", "SC", "SE", "SP", "TO" };
	private JComboBox comboEstados = new JComboBox(estados);
	private JButton botaoSalvar = new JButton("Salvar");
	private JButton botaoLimpar = new JButton("Limpar");

	// Elementos da janela de Consulta
	private JTable tabelaContatos;
	private TabelaContatos tabelaContatosModelo;
	private JTable tabela;
	private JPanel painelIcones;
	private Icon iconeCadastrar;
	private Icon iconeEditar;
	private JButton botaoCadastrar;
	private JButton botaoEditar;
	private Icon iconeExcluir;
	private JButton botaoExcluir;
	private JButton botaoCondicao;
	private JTextField textoLimite;
	private JTextField registroInicio;
	private JRadioButton decrescente;
	private JRadioButton crescente;

	// Tratadores de eventos das janelas
	private TratadorEventosMenu tratadorEventosMenu;
	private TratadorEventosCadastro tratadorEventosCadastro;
	private TratadorEventosTabela tratadorEventosTabela;
	private TratadorEventosConsulta tratadorEventosConsulta;

	/**
	 * Construtor da classe JanelaPrincipal
	 * 
	 * @param acessoBanco
	 */
	public JanelaPrincipal(AcessoBanco acessoBanco) {
		super();
		this.acessoBanco = acessoBanco;
		setTitle("Contact Application");
		setSize(900, 750);
		setLocationRelativeTo(null);
	}

	/**
	 * Função que fará a montagem do JFrame da aplicação.
	 */
	public void montarJanela() {
		tratadorEventosMenu = new TratadorEventosMenu(this);
		menuJanela = new MenuPrincipal();

		setJMenuBar(menuJanela);

		// Evento gerados pelo Menu
		menuJanela.getCadastro().addActionListener(tratadorEventosMenu);
		menuJanela.getExibir().addActionListener(tratadorEventosMenu);
		menuJanela.getExit().addActionListener(tratadorEventosMenu);
		menuJanela.getAplicacao().addActionListener(tratadorEventosMenu);
		menuJanela.getMetal().addActionListener(tratadorEventosMenu);
		menuJanela.getMotif().addActionListener(tratadorEventosMenu);
		menuJanela.getGtk().addActionListener(tratadorEventosMenu);
		menuJanela.getNimbus().addActionListener(tratadorEventosMenu);

		painelPrincipal = new PainelPrincipal(imagemFundo);
		getContentPane().add(painelPrincipal);
		Toolkit kit = Toolkit.getDefaultToolkit();
		iconeBarra = kit.getImage(iconeTitulo);  
		setIconImage(iconeBarra);
		
		repaint();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * Este método é utilizado para atualizar a área do painel principal,
	 * criando uma maneira de utilização do mesmo espaço para a criação de
	 * áreas com objetivos diferentes.
	 */
	public void limparTela() {
		painelPrincipal.removeAll();
		repaint();
		limparDados();
		setVisible(true);
	}

	/**
	 * Este método será utilizado para limpar os JTextField da tela de cadastro
	 * de Contatos.
	 */
	public void limparDados() {
		getTextoNome().setText("");
		getTextoApelido().setText("");
		getTextoTelRes().setText("");
		getTextoTelCel().setText("");
		getTextoCidade().setText("");
	}

	/**
	 * Método responsável por organizar todos os elementos da tela de cadastro de
	 * Contato.
	 */
	public void telaCadastrar() {
		tratadorEventosCadastro = new TratadorEventosCadastro(this, acessoBanco);

		MigLayout layoutDentro = new MigLayout("wrap 3");
		JPanel painelDentro = new JPanel();
		painelDentro.setLayout(layoutDentro);
		TitledBorder bordaPainelDentro = new TitledBorder("Cadastro de Contatos");
		bordaPainelDentro.setTitleFont(new Font("Times New Roman", Font.BOLD, 25));
		painelDentro.setBorder(bordaPainelDentro);
		
		JPanel botoes = new JPanel();
		botoes.add(botaoLimpar);
		botoes.add(botaoSalvar);
		botoes.setBackground(new Color(255,255,255,0));
		
		//cadastro.setFont(new Font("Times New Roman", Font.BOLD, 25));
		painelDentro.add(nome, "gapleft 70, gaptop 40");
		painelDentro.add(textoNome, "spanx 2, gapright 50");
		painelDentro.add(apelido, "gapleft 70");
		painelDentro.add(textoApelido, "spanx 2");
		painelDentro.add(telefoneResidencial, "gapleft 70");
		painelDentro.add(textoTelRes, "spanx 2");
		painelDentro.add(telefoneCelular, "gapleft 70");
		painelDentro.add(textoTelCel, "spanx 2");
		painelDentro.add(cidade, "gapleft 70");
		painelDentro.add(textoCidade, "spanx 2");
		painelDentro.add(estado, "gapleft 70");
		painelDentro.add(comboEstados, "spanx 2");
		painelDentro.add(botoes, "spanx 3, gapleft 270");

		painelDentro.setBackground(new Color(255,255,255,100));
		MigLayout layoutFora = new MigLayout();
		painelPrincipal.setLayout(layoutFora);
		painelPrincipal.add(painelDentro,"gapleft 50, gaptop 120");
		
		add(painelPrincipal);

		botaoSalvar.addActionListener(tratadorEventosCadastro);
		botaoLimpar.addActionListener(tratadorEventosCadastro);

		repaint();
		setVisible(true);
	}

	/**
	 * Esta função é responsável por recriar o PainelPrincipal de forma que
	 * a área seja utilizada para área de consulta de Contatos. A condição
	 * recebida como parâmetro será utilizada para montar a tabela de registros.
	 * 
	 * @param condicao
	 */
	public void telaConsultar(String condicao){
		tabela = getTabelaContatos(condicao);
		propriedadesTabela();
		tratadorEventosTabela = new TratadorEventosTabela(this);
		tabela.addMouseListener(tratadorEventosTabela);

		tratadorEventosConsulta = new TratadorEventosConsulta(this, tabela,
				acessoBanco, tabelaContatosModelo);
		
		// setando o layout do painel principal
		JPanel painelDentro = new JPanel();
		MigLayout migLayout = new MigLayout("wrap 4");
		painelDentro.setLayout(migLayout);
		TitledBorder bordaPainelPrincipal = new TitledBorder("Contatos");
		bordaPainelPrincipal.setTitleFont(new Font("Times New Roman", Font.BOLD, 25));
		painelDentro.setBorder(bordaPainelPrincipal);

		// setando as imagens dos botões de manipulação de contatos
		iconeEditar = new ImageIcon(caminhoImgEditar);
		botaoEditar = new JButton(iconeEditar);
		botaoEditar.setToolTipText("Editar");
		iconeExcluir = new ImageIcon(caminhoImgExcluir);
		botaoExcluir = new JButton(iconeExcluir);
		botaoExcluir.setToolTipText("Excluir");
		iconeCadastrar = new ImageIcon(caminhoImgCadastrar);
		botaoCadastrar = new JButton(iconeCadastrar);
		botaoCadastrar.setToolTipText("Cadastrar");
		botaoCadastrar.addActionListener(tratadorEventosConsulta);
		botaoEditar.addActionListener(tratadorEventosConsulta);
		botaoExcluir.addActionListener(tratadorEventosConsulta);

		// montanto o painel de botões de manipulação de contatos
		painelIcones = new JPanel();
		painelIcones.add(botaoEditar);
		painelIcones.add(botaoExcluir);
		painelIcones.add(botaoCadastrar);

		// acrescenta o painel icones no painel Principal
		painelIcones.setBackground(new Color(255,255,255,0));
		painelDentro.add(painelIcones, "spanx 4,gapleft 580, gaptop 10");

		// acrescentando a tabela com os dados no painel principal
		JScrollPane barraRolagem = new JScrollPane(tabela);
		painelDentro.add(barraRolagem, "spanx 4,gapleft 40, gaptop 10");

		// montando o painel de id inicial
		JPanel painelInicio = new JPanel();
		TitledBorder bordaInicio = new TitledBorder("Nome");
		registroInicio = new JTextField(5);
		painelInicio.add(registroInicio);
		painelInicio.setBackground(new Color(255,255,255,0));
		painelInicio.setBorder(bordaInicio);

		// montando o painel de limite
		JPanel painelLimite = new JPanel();
		TitledBorder bordaLimite = new TitledBorder("Limite");
		textoLimite = new JTextField(5);
		painelLimite.add(textoLimite);
		painelLimite.setBackground(new Color(255,255,255,0));
		painelLimite.setBorder(bordaLimite);
		
		decrescente = new JRadioButton("Decrescente");
		crescente = new JRadioButton("Crescente");
		crescente.setSelected(true);
		ButtonGroup g = new ButtonGroup();
		g.add(crescente);
		g.add(decrescente);

		// adiciona tratador de eventos para botao inicio e limite
		botaoCondicao = new JButton("OK");
		botaoCondicao.addActionListener(tratadorEventosConsulta);

		/* 
		 * Montando o painel de ferramentas de pesquisa com o painel de limite e
		 * o painel de id inicial
		 */
		JPanel painelFerramentas = new JPanel();
		TitledBorder tituloPainelFerramentas = new TitledBorder(
				"Ferramentas de pesquisa");
		painelFerramentas.setBorder(tituloPainelFerramentas);
		painelFerramentas.add(painelInicio);
		painelFerramentas.add(painelLimite);
		painelFerramentas.add(crescente);
		painelFerramentas.add(decrescente);
		painelFerramentas.add(botaoCondicao);

		// acrescenta ao painel principal, o painel inferior
		painelDentro.add(painelFerramentas,"gaptop 10,gapleft 200, spanx 4");

		painelFerramentas.setBackground(new Color(255,255,255,0));
		painelDentro.setBackground(new Color(255,255,255,100));
		MigLayout layoutFora = new MigLayout();
		painelPrincipal.setLayout(layoutFora);
		painelPrincipal.add(painelDentro,"gap 20 20 20 20");
		
		// acrescenta o painel principal na janela JFrame
		add(painelPrincipal);
		addKeyListener(this);
		repaint();
		setVisible(true);
	}

	/**
	 * Esta função seta as propriedades da tabela que receberá os contatos
	 * cadastrados no banco. Dentro das funções utilizadas estão o
	 * redimensionamento da tabela, com os respectivos tamanhos de colunas e
	 * linhas. Além disso, é definido a cor das suas linhas, do preenchimento de
	 * fundo e a fonte utilizada no texto.
	 */
	public void propriedadesTabela() {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(145);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(110);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(110);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(55);
		tabela.setRowHeight(23);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabela.setGridColor(new Color(40, 255, 40));
		tabela.setBackground(new Color(240, 255, 240));
		tabela.setFont(new Font("Verdana", Font.BOLD, 15));
		tabela.setPreferredScrollableViewportSize(new Dimension(720, 300));
		tabela.setAutoCreateRowSorter(true);
	}

	public JTable getTabelaContatos(String condicao){

		if (tabelaContatos == null) {
			tabelaContatos = new JTable();
			tabelaContatos.setModel(getTabelaModelo(condicao));
		} else {
			tabelaContatos.setModel(getTabelaModelo(condicao));
		}
		return tabelaContatos;
	}

	private TabelaContatos getTabelaModelo(String condicao){
		if (tabelaContatosModelo == null) {
			tabelaContatosModelo = new TabelaContatos(pegaContatos(condicao));
		} else {
			tabelaContatosModelo = new TabelaContatos(pegaContatos(condicao));
		}
		return tabelaContatosModelo;
	}

	private List<Contato> pegaContatos(String condicao){
		List<Contato> contatos = new ArrayList<Contato>();
		contatos = acessoBanco.buscaDocumentos(condicao);
		return contatos;
	}
	
	public JRadioButton getDecrescente() {
		return decrescente;
	}

	public void setDecrescente(JRadioButton decrescente) {
		this.decrescente = decrescente;
	}

	public JRadioButton getCrescente() {
		return crescente;
	}

	public void setCrescente(JRadioButton crescente) {
		this.crescente = crescente;
	}

	public String getIconeTitulo() {
		return iconeTitulo;
	}

	public void setIconeTitulo(String iconeTitulo) {
		this.iconeTitulo = iconeTitulo;
	}

	public String getImagemFundo() {
		return imagemFundo;
	}

	public void setImagemFundo(String imagemFundo) {
		this.imagemFundo = imagemFundo;
	}

	public JTextField getTextoLimite() {
		return textoLimite;
	}

	public void setTextoLimite(JTextField textoLimite) {
		this.textoLimite = textoLimite;
	}

	public JTextField getRegistroInicio() {
		return registroInicio;
	}

	public void setRegistroInicio(JTextField registroInicio) {
		this.registroInicio = registroInicio;
	}

	public JButton getBotaoCondicao() {
		return botaoCondicao;
	}

	public JButton getBotaoCadastrar() {
		return botaoCadastrar;
	}

	public JButton getBotaoEditar() {
		return botaoEditar;
	}

	public JButton getBotaoExcluir() {
		return botaoExcluir;
	}

	public String getCaminhoImgEditar() {
		return caminhoImgEditar;
	}

	public String getCaminhoImgCadastrar() {
		return caminhoImgCadastrar;
	}

	public void setCaminhoImgCadastrar(String caminhoImgCadastrar) {
		this.caminhoImgCadastrar = caminhoImgCadastrar;
	}

	public void setCaminhoImgEditar(String caminhoImgEditar) {
		this.caminhoImgEditar = caminhoImgEditar;
	}

	public String getCaminhoImgExcluir() {
		return caminhoImgExcluir;
	}

	public void setCaminhoImgExcluir(String caminhoImgExcluir) {
		this.caminhoImgExcluir = caminhoImgExcluir;
	}

	public JComboBox getComboEstados() {
		return comboEstados;
	}

	public void setComboEstados(JComboBox comboEstados) {
		this.comboEstados = comboEstados;
	}

	public JTextField getTextoNome() {
		return textoNome;
	}

	public void setTextoNome(JTextField textoNome) {
		this.textoNome = textoNome;
	}

	public JTextField getTextoApelido() {
		return textoApelido;
	}

	public JTextField getTextoTelRes() {
		return textoTelRes;
	}

	public JTextField getTextoTelCel() {
		return textoTelCel;
	}

	public JTextField getTextoCidade() {
		return textoCidade;
	}

	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setTextoApelido(JTextField textoApelido) {
		this.textoApelido = textoApelido;
	}

	public void setTextoTelRes(JTextField textoTelRes) {
		this.textoTelRes = textoTelRes;
	}

	public void setTextoTelCel(JTextField textoTelCel) {
		this.textoTelCel = textoTelCel;
	}

	public void setTextoCidade(JTextField textoCidade) {
		this.textoCidade = textoCidade;
	}

	public JButton getBotaoLimpar() {
		return botaoLimpar;
	}

	public MenuPrincipal getMenuJanela() {
		return menuJanela;
	}

	public void setMenuJanela(MenuPrincipal menuJanela) {
		this.menuJanela = menuJanela;
	}

	@Override
	public void keyPressed(KeyEvent evento) {
		if (evento.getKeyCode() == KeyEvent.VK_F5) {
			limparTela();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
