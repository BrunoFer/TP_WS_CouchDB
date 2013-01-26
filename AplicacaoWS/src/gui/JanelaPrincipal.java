package gui;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import tratador_eventos.TratadorEventosCadastro;
import tratador_eventos.TratadorEventosConsulta;
import tratador_eventos.TratadorEventosMenu;
import tratador_eventos.TratadorEventosTabela;

import net.miginfocom.swing.MigLayout;

import aplicacao.AcessoBanco;
import aplicacao.Aluno;
import aplicacao.MenuPrincipal;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Elementos usado em todas as interfaces
	private MenuPrincipal menuJanela;
	private JPanel painelPrincipal;
	private AcessoBanco acessoBanco;
	
	//Elementos da Janela de Cadastro
	private JLabel cadastro = new JLabel("Cadastro de aluno");
	private JLabel nome = new JLabel("Nome: ");
	private JLabel telefone = new JLabel("Telefone: ");
	private JLabel idade = new JLabel("Idade: ");
	private JLabel sexo = new JLabel("Sexo: ");
	private JTextField textoNome = new JTextField(50);
	private JTextField textoTel = new JTextField(12);
	private JTextField textoIdade = new JTextField(5);
	private JRadioButton masc = new JRadioButton("Masculino");
	private JRadioButton fem = new JRadioButton("Feminino");
	private JButton botaoSalvar = new JButton("Salvar");
	private JButton botaoLimpar = new JButton("Limpar");
	private JPanel botoes = new JPanel();
	private ButtonGroup botoesSexo = new ButtonGroup();
	
	//Elementos da janela de Consulta
	JLabel consulta = new JLabel("Alunos cadastrados");
	private JTable tabelaAlunos;
	private TabelaAluno tabelaAlunosModelo;
	
	private JPanel painelIcones;
	private String caminhoImgEditar;
	private String caminhoImgExcluir;
	private Icon iconeEditar;
	private JButton botaoEditar;
	private Icon iconeExcluir;
	private JButton botaoExcluir;	
	
	//Tratadores de eventos das janelas
	private TratadorEventosMenu tratadorEventosMenu;
	private TratadorEventosCadastro tratadorEventosCadastro;
	private TratadorEventosTabela tratadorEventosTabela;
	private TratadorEventosConsulta tratadorEventosConsulta;
	
	
	public JanelaPrincipal(AcessoBanco acessoBanco) {
		super();
		this.acessoBanco = acessoBanco;
		setTitle("Application Student");
		
	}
	
	public void montarJanela() {
		tratadorEventosMenu = new TratadorEventosMenu(this);
		menuJanela = new MenuPrincipal();
	
		setJMenuBar(menuJanela);
	
		// Evento gerados pelo Menu
		menuJanela.getCadastro().addActionListener(tratadorEventosMenu);
		menuJanela.getExibir().addActionListener(tratadorEventosMenu);
		menuJanela.getExit().addActionListener(tratadorEventosMenu);

		painelPrincipal = new JPanel();
		add(painelPrincipal);
		
		setSize(800, 600);
		repaint();
		setVisible(true);
		setLocationRelativeTo(null);
		
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
		getTextoIdade().setText("");
		getTextoTel().setText("");
	}
	
	public void telaCadastrar() {
		tratadorEventosCadastro = new TratadorEventosCadastro(this,acessoBanco);
		
		MigLayout migLayout = new MigLayout("wrap 3");
		painelPrincipal.setLayout(migLayout);
		
		botoesSexo.add(masc);
		botoesSexo.add(fem);

		botoes.add(botaoLimpar);
		botoes.add(botaoSalvar);
		
		cadastro.setFont(new Font("Times New Roman", Font.BOLD, 20));
		painelPrincipal.add(cadastro,"gapleft 250, gaptop 140, spanx 3");
		painelPrincipal.add(nome, "gapleft 70, gaptop 40");
		painelPrincipal.add(textoNome,"spanx 2");
		painelPrincipal.add(telefone, "gapleft 70");
		painelPrincipal.add(textoTel, "spanx 2");
		painelPrincipal.add(idade, "gapleft 70");
		painelPrincipal.add(textoIdade, "spanx 2");
		painelPrincipal.add(sexo, "gapleft70");
		painelPrincipal.add(masc);
		painelPrincipal.add(fem);
		painelPrincipal.add(botoes, "spanx 3, gapleft 270");

		add(painelPrincipal);
		
		botaoSalvar.addActionListener(tratadorEventosCadastro);
		botaoLimpar.addActionListener(tratadorEventosCadastro);

		repaint();
		setVisible(true);
	}
	
	public void telaConsultar() throws IOException{
		tratadorEventosTabela = new TratadorEventosTabela(this);
		final JTable tabela = getTabelaAlunos();
		
		tratadorEventosConsulta = new TratadorEventosConsulta(this,tabela,acessoBanco);
		
		MigLayout migLayout = new MigLayout("wrap 4");
		painelPrincipal.setLayout(migLayout);
		
		consulta.setFont(new Font("Times New Roman", Font.BOLD, 20));
		painelPrincipal.add(consulta,"gapleft 230, gaptop 30, spanx 4");
		painelPrincipal.add(new JScrollPane(tabela), "spanx 4,gapleft 150, gaptop 30");
		
		iconeEditar = new ImageIcon(caminhoImgEditar);
		botaoEditar = new JButton(iconeEditar);
		iconeExcluir = new ImageIcon(caminhoImgExcluir);
		botaoExcluir = new JButton(iconeExcluir);
		
		painelIcones = new JPanel();
		painelIcones.add(botaoEditar);
		painelIcones.add(botaoExcluir);
		
		botaoEditar.addActionListener(tratadorEventosConsulta);
		botaoExcluir.addActionListener(tratadorEventosConsulta);
		
		tabela.addMouseListener(tratadorEventosTabela);
		
		painelPrincipal.add(painelIcones,"gaptop 30,gapleft 220, gapbottom 40, spanx 4");
		add(painelPrincipal);
		repaint();
		setVisible(true);
	}
	
	public JTable getTabelaAlunos() throws IOException{

		if (tabelaAlunos == null) {
            tabelaAlunos = new JTable();
            tabelaAlunos.setModel(getTabelaModelo());
        } else {
            tabelaAlunos.setModel(getTabelaModelo());
        }
        return tabelaAlunos;
	}
	
	private TabelaAluno getTabelaModelo() throws IOException {
        if (tabelaAlunosModelo == null) {
            tabelaAlunosModelo = new TabelaAluno(pegaAlunos());
        } else {
        	tabelaAlunosModelo = new TabelaAluno(pegaAlunos());
        }
        return tabelaAlunosModelo;
    }

	private List<Aluno> pegaAlunos() throws IOException {
        List<Aluno> alunos = new ArrayList<Aluno>();
		alunos = acessoBanco.buscaDocumentos();
        return alunos;
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

	public void setCaminhoImgEditar(String caminhoImgEditar) {
		this.caminhoImgEditar = caminhoImgEditar;
	}

	public String getCaminhoImgExcluir() {
		return caminhoImgExcluir;
	}

	public void setCaminhoImgExcluir(String caminhoImgExcluir) {
		this.caminhoImgExcluir = caminhoImgExcluir;
	}

	public JTextField getTextoNome() {
		return textoNome;
	}

	public void setTextoNome(JTextField textoNome) {
		this.textoNome = textoNome;
	}

	public JTextField getTextoTel() {
		return textoTel;
	}

	public void setTextoTel(JTextField textoTel) {
		this.textoTel = textoTel;
	}

	public JTextField getTextoIdade() {
		return textoIdade;
	}

	public void setTextoIdade(JTextField textoIdade) {
		this.textoIdade = textoIdade;
	}

	public JRadioButton getMasc() {
		return masc;
	}

	public void setMasc(JRadioButton masc) {
		this.masc = masc;
	}

	public JRadioButton getFem() {
		return fem;
	}

	public void setFem(JRadioButton fem) {
		this.fem = fem;
	}

	public JButton getBotaoSalvar() {
		return botaoSalvar;
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
}
