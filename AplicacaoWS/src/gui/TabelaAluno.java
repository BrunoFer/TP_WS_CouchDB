package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicacao.Aluno;

public class TabelaAluno extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Aluno> linhas;
	
	private String[] colunas = new String[] { "Nome", "Endereço", "Idade", "Sexo"};
	
	private static final int NOME = 0;
	private static final int TELEFONE = 1;
	private static final int IDADE = 2;
	private static final int SEXO = 3;

	public TabelaAluno() {
        linhas = new ArrayList<Aluno>();
    }
 
    public TabelaAluno(List<Aluno> listaAlunos) {
        linhas = new ArrayList<Aluno>(listaAlunos);
    }

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
	    return colunas[columnIndex];
	};

	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    switch (columnIndex) {
	    case NOME:
	        return String.class;
	    case TELEFONE:
	        return String.class;
	    case IDADE:
	    	return Integer.class;
	    case SEXO:
	    	return String.class;
	    default:
	        // Não deve ocorrer, pois só existem 4 colunas
	        throw new IndexOutOfBoundsException("Numero excedido de colunas");
	    }
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    // Pega o sócio referente a linha especificada.
	    Aluno aluno = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case NOME:
	        return aluno.getNome();
	    case TELEFONE:
	        return aluno.getTelefone();
	    case IDADE:
	    	return aluno.getIdade();
	    case SEXO:
	    	return aluno.getSexo();
	    default:
	        // Não deve ocorrer, pois só existem 4 colunas
	        throw new IndexOutOfBoundsException("Número excedido de colunas");
	    }
	}

	@Override
	public void setValueAt(Object valor, int rowIndex, int columnIndex) {
	    // Pega o sócio referente a linha especificada.
	    Aluno aluno = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case NOME:
	        aluno.setNome((String) valor);
	        break;
	    case TELEFONE:
	        aluno.setTelefone((String) valor);
	        break;
	    case IDADE:
	    	aluno.setIdade((Integer) valor);
	    	break;
	    case SEXO:
	    	aluno.setSexo((String) valor);
	    	break;
	    default:
	        // Não deve ocorrer, pois só existem 4 colunas
	        throw new IndexOutOfBoundsException("Número excedido de colunas");
	    }
	     
	    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}

	// Retorna o sócio referente a linha especificada
	public Aluno getAluno(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o sócio especificado ao modelo
	public void addSocio(Aluno socio) {
	    // Adiciona o registro.
	    linhas.add(socio);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o sócio da linha especificada.
	public void removeAluno(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de sócios no final da lista.
	public void addListaAlunos(List<Aluno> alunos) {
	    // Pega o tamanho antigo da tabela, que servirá
	    // como índice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(alunos);
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(indice, indice + alunos.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de sócios.
	    linhas.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}

}
