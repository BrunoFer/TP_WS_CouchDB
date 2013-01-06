package gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import aplicacao.MenuPrincipal;
import aplicacao.TratadorEventos;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textoNome = new JTextField(50);
	private JLabel nome = new JLabel("Nome: ");
	private JLabel telefone = new JLabel("Telefone: ");
	private JLabel idade = new JLabel("Idade: ");
	private JLabel sexo = new JLabel("Sexo: ");
	private JTextField textoTel = new JTextField(12);
	private JTextField textoIdade = new JTextField(5);
	//private JCheckBox textoSexo = new JCheckBox();
	private JRadioButton masc = new JRadioButton("Masculino");
	private JRadioButton fem = new JRadioButton("Feminino");
	private JButton botaoSalvar = new JButton("Salvar");
	private JButton botaoLimpar = new JButton("Limpar");
	private JPanel botoes = new JPanel();
	private ButtonGroup botoesSexo = new ButtonGroup();
	private JSeparator separacao1 = new JSeparator();
	private TratadorEventos tratadorEventos;
	private MenuPrincipal menuJanela = new MenuPrincipal();
	
	public JanelaPrincipal() {
		super();
	}

	public void montarJanela() {
		tratadorEventos = new TratadorEventos(this);
		MigLayout migLayout = new MigLayout("wrap 3");
		setLayout(migLayout);

		setJMenuBar(menuJanela);
		
		botoesSexo.add(masc);
		botoesSexo.add(fem);

		botoes.add(botaoLimpar);
		botoes.add(botaoSalvar);
		
		separacao1.setSize(500, 500);
		separacao1.setBackground(new Color(100,100,100));
		add(separacao1, "spanx 3");
		add(nome, "gapleft 70, gaptop 150");
		add(textoNome,"spanx 2");
		add(telefone, "gapleft 70");
		add(textoTel, "spanx 2");
		add(idade, "gapleft 70");
		add(textoIdade, "spanx 2");
		add(sexo, "gapleft 70");
		add(masc);
		add(fem);
		add(botoes, "spanx 3, gapleft 270");

		botaoSalvar.addActionListener(tratadorEventos);
		botaoLimpar.addActionListener(tratadorEventos);
		
		setTitle("Cadastro de alunos");
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
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

}
