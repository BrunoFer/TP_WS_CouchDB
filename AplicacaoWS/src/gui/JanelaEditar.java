package gui;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import aplicacao.AcessoBanco;
import aplicacao.Aluno;

import tratador_eventos.TratadorEventosEditar;

public class JanelaEditar extends JDialog{
	/**
	 */
	private static final long serialVersionUID = 1L;
	private JPanel painelPrincipal;
	private JLabel edicao = new JLabel("Edição");
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
	private JButton botaoCancelar = new JButton("Cancelar");
	private JPanel botoes = new JPanel();
	private ButtonGroup botoesSexo = new ButtonGroup();
	private TratadorEventosEditar tratadorEventosEditar;
	private AcessoBanco acessoBanco;
	private Aluno aluno;
	private int numeroDocumento;
	
	public JanelaEditar(AcessoBanco acessoBanco, Aluno aluno){
		this.acessoBanco = acessoBanco;
		this.aluno = aluno;
		this.numeroDocumento = aluno.getId();
		setTitle("Editar aluno");
		setSize(250, 300);
	}
	
	public void montarJanelaEditar(){
		tratadorEventosEditar = new TratadorEventosEditar(acessoBanco,this);
		painelPrincipal = new JPanel();
		
		MigLayout migLayout = new MigLayout("wrap 3");
		painelPrincipal.setLayout(migLayout);
		
		botoesSexo.add(masc);
		botoesSexo.add(fem);

		botoes.add(botaoCancelar);
		botoes.add(botaoSalvar);
		
		edicao.setFont(new Font("Times New Roman", Font.BOLD, 20));
		painelPrincipal.add(edicao,"gaptop 10, spanx 2, wrap");
		painelPrincipal.add(nome, "gaptop 20");
		painelPrincipal.add(textoNome, "wrap");
		painelPrincipal.add(telefone);
		painelPrincipal.add(textoTel, "wrap");
		painelPrincipal.add(idade);
		painelPrincipal.add(textoIdade, "wrap");
		painelPrincipal.add(sexo);
		painelPrincipal.add(masc, "wrap");
		painelPrincipal.add(fem, "spanx 2,gapleft 65, wrap");
		painelPrincipal.add(botoes, "gapleft 55, spanx 2");

		textoNome.setText((String) aluno.getNome());
		textoTel.setText((String) aluno.getTelefone());
		textoIdade.setText(String.valueOf(aluno.getIdade()));
		add(painelPrincipal);
		
		botaoSalvar.addActionListener(tratadorEventosEditar);
		botaoCancelar.addActionListener(tratadorEventosEditar);

		repaint();
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
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

	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	public JLabel getNome() {
		return nome;
	}

	public void setNome(JLabel nome) {
		this.nome = nome;
	}

	public JRadioButton getMasc() {
		return masc;
	}

	public void setMasc(JRadioButton masc) {
		this.masc = masc;
	}
	
	
}
