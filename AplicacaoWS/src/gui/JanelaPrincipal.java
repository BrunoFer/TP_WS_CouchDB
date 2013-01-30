package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JPanel painelPrincipal;
	private AcessoBanco acessoBanco;

	// Elementos da Janela de Cadastro
	private JLabel cadastro = new JLabel("Cadastro de Contato");
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
	private JPanel botoes = new JPanel();

	// Elementos da janela de Consulta
	JLabel consulta = new JLabel("Contatos cadastrados");
	private JTable tabelaContatos;
	private TabelaContatos tabelaContatosModelo;
	private JTable tabela;

	private JPanel painelIcones;
	private String caminhoImgEditar;
	private String caminhoImgExcluir;
	private String caminhoImgCadastrar;
	private Icon iconeCadastrar;
	private Icon iconeEditar;
	private JButton botaoCadastrar;
	private JButton botaoEditar;
	private Icon iconeExcluir;
	private JButton botaoExcluir;

	private JButton botaoCondicao;
	private JTextField textoLimite;
	private JTextField registroInicio;

	// Tratadores de eventos das janelas
	private TratadorEventosMenu tratadorEventosMenu;
	private TratadorEventosCadastro tratadorEventosCadastro;
	private TratadorEventosTabela tratadorEventosTabela;
	private TratadorEventosConsulta tratadorEventosConsulta;

	public JanelaPrincipal(AcessoBanco acessoBanco) {
		super();
		this.acessoBanco = acessoBanco;
		setTitle("Contact Application");
		setSize(900, 700);
		setLocationRelativeTo(null);
	}

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

		painelPrincipal = new JPanel();
		add(painelPrincipal);

		repaint();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void limparTela() {
		painelPrincipal.removeAll();
		repaint();
		limparDados();
		setVisible(true);
	}

	public void limparDados() {
		getTextoNome().setText("");
		getTextoApelido().setText("");
		getTextoTelRes().setText("");
		getTextoTelCel().setText("");
		getTextoCidade().setText("");
	}

	public void telaCadastrar() {
		tratadorEventosCadastro = new TratadorEventosCadastro(this, acessoBanco);

		MigLayout migLayout = new MigLayout("wrap 3");
		painelPrincipal.setLayout(migLayout);

		botoes.add(botaoLimpar);
		botoes.add(botaoSalvar);

		cadastro.setFont(new Font("Times New Roman", Font.BOLD, 25));
		painelPrincipal.add(cadastro, "gapleft 260, gaptop 120, spanx 3");
		painelPrincipal.add(nome, "gapleft 70, gaptop 40");
		painelPrincipal.add(textoNome, "spanx 2");
		painelPrincipal.add(apelido, "gapleft 70");
		painelPrincipal.add(textoApelido, "spanx 2");
		painelPrincipal.add(telefoneResidencial, "gapleft 70");
		painelPrincipal.add(textoTelRes, "spanx 2");
		painelPrincipal.add(telefoneCelular, "gapleft 70");
		painelPrincipal.add(textoTelCel, "spanx 2");
		painelPrincipal.add(cidade, "gapleft 70");
		painelPrincipal.add(textoCidade, "spanx 2");
		painelPrincipal.add(estado, "gapleft 70");
		painelPrincipal.add(comboEstados, "spanx 2");
		painelPrincipal.add(botoes, "spanx 3, gapleft 270");

		add(painelPrincipal);

		botaoSalvar.addActionListener(tratadorEventosCadastro);
		botaoLimpar.addActionListener(tratadorEventosCadastro);

		repaint();
		setVisible(true);
	}

	public void telaConsultar(String condicao) throws IOException {
		tratadorEventosTabela = new TratadorEventosTabela(this);
		tabela = getTabelaContatos(condicao);
		tabela.addMouseListener(tratadorEventosTabela);

		tratadorEventosConsulta = new TratadorEventosConsulta(this, tabela,
				acessoBanco, tabelaContatosModelo);

		// setando o layout do painel principal
		MigLayout migLayout = new MigLayout("wrap 4");
		painelPrincipal.setLayout(migLayout);

		// colocando o label do titulo no painel principal
		consulta.setFont(new Font("Times New Roman", Font.BOLD, 25));
		painelPrincipal.add(consulta, "gapleft 300, gaptop 20, spanx 4");

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
		painelPrincipal.add(painelIcones, "spanx 4,gapleft 650, gaptop 10");

		// acrescentando a tabela com os dados no painel principal
		propriedadesTabela();
		JScrollPane barraRolagem = new JScrollPane(tabela);
		painelPrincipal.add(barraRolagem, "spanx 4,gapleft 80, gaptop 10");

		// montando o painel de id inicial
		JPanel painelInicio = new JPanel();
		TitledBorder bordaInicio = new TitledBorder("ID inicio");
		registroInicio = new JTextField(5);
		painelInicio.add(registroInicio);
		painelInicio.setBorder(bordaInicio);

		// montando o painel de limite
		JPanel painelLimite = new JPanel();
		TitledBorder bordaLimite = new TitledBorder("Limite");
		textoLimite = new JTextField(5);
		painelLimite.add(textoLimite);
		painelLimite.setBorder(bordaLimite);

		// adiciona tratador de eventos para botao inicio e limite
		botaoCondicao = new JButton("OK");
		botaoCondicao.addActionListener(tratadorEventosConsulta);

		// montando o painel de ferramentas de pesquisa com o painel de limite e
		// o painel de id inicial
		JPanel painelFerramentas = new JPanel();
		TitledBorder tituloPainelFerramentas = new TitledBorder(
				"Ferramentas de pesquisa");
		painelFerramentas.setBorder(tituloPainelFerramentas);
		painelFerramentas.add(painelInicio);
		painelFerramentas.add(painelLimite);
		painelFerramentas.add(botaoCondicao);

		// acrescenta ao painel principal, o painel inferior
		painelPrincipal.add(painelFerramentas,
				"gaptop 30,gapleft 300, gapbottom 40, spanx 4");

		// acrescenta o painel principal na janela JFrame
		add(painelPrincipal);

		addKeyListener(this);

		repaint();
		setVisible(true);
	}

	/*
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
		tabela.setPreferredScrollableViewportSize(new Dimension(720, 450));
	}

	public JTable getTabelaContatos(String condicao) throws IOException {

		if (tabelaContatos == null) {
			tabelaContatos = new JTable();
			tabelaContatos.setModel(getTabelaModelo(condicao));
		} else {
			tabelaContatos.setModel(getTabelaModelo(condicao));
		}
		return tabelaContatos;
	}

	private TabelaContatos getTabelaModelo(String condicao) throws IOException {
		if (tabelaContatosModelo == null) {
			tabelaContatosModelo = new TabelaContatos(pegaContatos(condicao));
		} else {
			tabelaContatosModelo = new TabelaContatos(pegaContatos(condicao));
		}
		return tabelaContatosModelo;
	}

	private List<Contato> pegaContatos(String condicao) throws IOException {
		List<Contato> contatos = new ArrayList<Contato>();
		contatos = acessoBanco.buscaDocumentos(condicao);
		return contatos;
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

	public void setBotaoSalvar(JButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public JButton getBotaoLimpar() {
		return botaoLimpar;
	}

	public void setBotaoLimpar(JButton botaoLimpar) {
		this.botaoLimpar = botaoLimpar;
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
