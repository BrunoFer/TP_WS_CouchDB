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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import tratador_eventos.TratadorEventosCadastro;
import tratador_eventos.TratadorEventosMenu;

import net.miginfocom.swing.MigLayout;

import aplicacao.AcessoBanco;
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
	//private JCheckBox textoSexo = new JCheckBox();
	private JRadioButton masc = new JRadioButton("Masculino");
	private JRadioButton fem = new JRadioButton("Feminino");
	private JButton botaoSalvar = new JButton("Salvar");
	private JButton botaoLimpar = new JButton("Limpar");
	private JPanel botoes = new JPanel();
	private ButtonGroup botoesSexo = new ButtonGroup();
	private JSeparator separacao1 = new JSeparator();
	private TratadorEventosCadastro tratadorEventos;
	private MenuPrincipal menuJanela;
	private MigLayout migLayout = new MigLayout("wrap 3");
	private TratadorEventosMenu tratadorEventosMenu;
	private JTextArea areaTexto = new JTextArea();
	private AcessoBanco acessoBanco;
	
	public JanelaPrincipal() {
		super();
		acessoBanco = new AcessoBanco();
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
		tratadorEventos = new TratadorEventosCadastro(this);
		
		painelPrincipal.setLayout(migLayout);
		
		botoesSexo.add(masc);
		botoesSexo.add(fem);

		botoes.add(botaoLimpar);
		botoes.add(botaoSalvar);
		
		separacao1.setSize(500, 500);
		separacao1.setBackground(new Color(100,100,100));
		painelPrincipal.add(separacao1, "spanx 3");
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

		setTitle("Cadastro de alunos");
		repaint();
		setVisible(true);
	}
	
	public void telaConsultar(){
		String capturaJson;
		JSONObject json;
		String qtdeDocumentos = "";
		
		capturaJson = acessoBanco.getRegistro();
		try {
			json = new JSONObject(capturaJson.toString());
			qtdeDocumentos = json.getString("doc_count");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		areaTexto.setSize(100, 100);
		areaTexto.setText("Número de documentos cadastrados: "+qtdeDocumentos);
		add(areaTexto);
		
		setTitle("Consulta de alunos");
		repaint();
		setVisible(true);
	}
	
	public void Limpar() {
		painelPrincipal.removeAll();
		repaint();
		setVisible(true);
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
