package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TratadorEventosMenu implements ActionListener{

	private JanelaPrincipal janelaPrincipal;
	public static final String acessoBanco = "http://localhost:28017/";
	public static final String nomeBanco = "bancodobrasil";
	public static final String nomeColecao = "alunos";
	private URL urlConsulta;
	private BufferedReader bReader;
	private InputStreamReader iReader;
	private String capturaJson;

	public TratadorEventosMenu(JanelaPrincipal janelaPrincipal) {
		this.janelaPrincipal = janelaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == janelaPrincipal.getMenuJanela().getCadastro())
		{
			janelaPrincipal.Limpar();
			janelaPrincipal.Cadastrar();
		} else
			if (e.getSource() == janelaPrincipal.getMenuJanela().getExibir())
			{
				janelaPrincipal.Limpar();
				try {
					this.urlConsulta = new URL(acessoBanco + nomeBanco + "/" + nomeColecao + "/");


					// Cria um stream de entrada do conteúdo.
					this.iReader = new InputStreamReader( this.urlConsulta.openStream() );
					this.bReader = new BufferedReader( this.iReader );

					this.capturaJson = "";

					// Capturando as linhas com a resposta da consulta ao site dos correios.
					while ( this.bReader.ready()){
						this.capturaJson += this.bReader.readLine();
					}
					
					System.out.println(capturaJson);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
				if (e.getSource() == janelaPrincipal.getMenuJanela().getExit())
				{
					System.exit(0);
				}
	}

}
