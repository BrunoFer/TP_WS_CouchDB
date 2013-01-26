package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import tratador_eventos.TratadorEventosCadastro;
import tratador_eventos.TratadorEventosMenu;
import tratador_eventos.TratadorEventosTabela;

import net.miginfocom.swing.MigLayout;

import aplicacao.AcessoBanco;
import aplicacao.Aluno;
import aplicacao.MenuPrincipal;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textoNome = new JTextField(50);
	private JLabel nome = new JLabel("Nome: ");
	private JLabel telefone = new JLabel("Telefone: ");
	private JLabel idade = new JLabel("Idade: ");
	private JLabel sexo = new JLabel("Sexo: ");
	private JTextField textoTel = new JTextField(12);
	private JTextField textoIdade = new JTextField(5);
	private JPanel painelPrincipal;
	private JRadioButton masc = new JRadioButton("Masculino");
	private JRadioButton fem = new JRadioButton("Feminino");
	private JButton botaoSalvar = new JButton("Salvar");
	private JButton botaoLimpar = new JButton("Limpar");
	private JPanel botoes = new JPanel();
	private ButtonGroup botoesSexo = new ButtonGroup();
	
	private MigLayout migLayout = new MigLayout("wrap 3");
	
	private MenuPrincipal menuJanela;
	private TratadorEventosMenu tratadorEventosMenu;
	private TratadorEventosCadastro tratadorEventos;
	private TratadorEventosTabela tratadorTabela;
	
	private AcessoBanco acessoBanco;
	
	private JTable tabelaAlunos;
	private TabelaAluno tabelaAlunosModelo;
	
	
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
	
	public void telaCadastrar() {
		tratadorEventos = new TratadorEventosCadastro(this,acessoBanco);
		
		painelPrincipal.setLayout(migLayout);
		
		botoesSexo.add(masc);
		botoesSexo.add(fem);

		botoes.add(botaoLimpar);
		botoes.add(botaoSalvar);
		
		painelPrincipal.add(nome, "gapleft 70, gaptop 150");
		painelPrincipal.add(textoNome,"spanx 2");
		painelPrincipal.add(telefone, "gapleft 70");
		painelPrincipal.add(textoTel, "spanx 2");
		painelPrincipal.add(idade, "gapleft 70");
		painelPrincipal.add(textoIdade, "spanx 2");
		painelPrincipal.add(sexo, "gapleft 70");
		painelPrincipal.add(masc);
		painelPrincipal.add(fem);
		painelPrincipal.add(botoes, "spanx 3, gapleft 270");

		add(painelPrincipal);
		
		botaoSalvar.addActionListener(tratadorEventos);
		botaoLimpar.addActionListener(tratadorEventos);

		repaint();
		setVisible(true);
	}
	
	public void limparTela() {
		painelPrincipal.removeAll();
		repaint();
		setVisible(true);
	}
	
	public void limparDados() {
		getTextoNome().setText("");
		getTextoIdade().setText("");
		getTextoTel().setText("");
	}
	
	public void telaConsultar() throws IOException{
		tratadorTabela = new TratadorEventosTabela(this);
		
		painelPrincipal.setLayout(migLayout);
		final JTable tabela = getTabelaAlunos();
		tabela.addMouseListener(tratadorTabela);
		painelPrincipal.add(new JScrollPane(tabela));
		add(painelPrincipal);
		repaint();
		setVisible(true);
	}
	
	public JTable getTabelaAlunos() throws IOException{

		if (tabelaAlunos == null) {
            tabelaAlunos = new JTable();
            tabelaAlunos.setModel(getTabelaModelo());
        }
        return tabelaAlunos;
	}
	
	private TabelaAluno getTabelaModelo() throws IOException {
        if (tabelaAlunosModelo == null) {
            tabelaAlunosModelo = new TabelaAluno(pegaAlunos());
        }
        return tabelaAlunosModelo;
    }

	private List<Aluno> pegaAlunos() throws IOException {
        List<Aluno> alunos = new ArrayList<Aluno>();
		alunos = acessoBanco.buscaDocumentos();
        return alunos;
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
