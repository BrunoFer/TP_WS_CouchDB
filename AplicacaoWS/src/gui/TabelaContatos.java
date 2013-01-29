package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import aplicacao.Contato;

public class TabelaContatos extends AbstractTableModel{
	/**
	 */
	private static final long serialVersionUID = 1L;
	private List<Contato> linhas;
	
	private String[] colunas = new String[] { "Id", "Nome", "Apelido", "Tel Residencial", "Tel Cel", "Cidade", "Estado"};
	
	private static final int ID = 0;
	private static final int NOME = 1;
	private static final int APELIDO = 2;
	private static final int TELEFONERES = 3;
	private static final int TELEFONECEL = 4;
	private static final int CIDADE = 5;
	private static final int ESTADO = 6;

	public TabelaContatos() {
        linhas = new ArrayList<Contato>();
    }
 
    public TabelaContatos(List<Contato> listaContatos) {
        linhas = new ArrayList<Contato>(listaContatos);
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
	    case ID:
	    	return Integer.class;
	    case NOME:
	        return String.class;
	    case APELIDO:
	    	return String.class;
	    case TELEFONERES:
	        return String.class;
	    case TELEFONECEL:
	    	return String.class;
	    case CIDADE:
	    	return String.class;
	    case ESTADO:
	    	return String.class;
	    default:
	        // Não deve ocorrer, pois só existem 7 colunas
	        throw new IndexOutOfBoundsException("Numero excedido de colunas");
	    }
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    // Pega o contato referente a linha especificada.
	    Contato contato = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
	    	return contato.getId();
	    case NOME:
	        return contato.getNome();
	    case APELIDO:
	        return contato.getApelido();
	    case TELEFONERES:
	    	return contato.getTelefoneResidencial();
	    case TELEFONECEL:
	    	return contato.getTelefoneCelular();
	    case CIDADE:
	    	return contato.getCidade();
	    case ESTADO:
	    	return contato.getEstado();
	    default:
	        // Não deve ocorrer, pois só existem 7 colunas
	        throw new IndexOutOfBoundsException("Número excedido de colunas");
	    }
	}

	@Override
	public void setValueAt(Object valor, int rowIndex, int columnIndex) {
	    // Pega o contato referente a linha especificada.
	    Contato contato = linhas.get(rowIndex);
	 
	    switch (columnIndex) {
	    case ID:
	    	contato.setId((Integer) valor);
	    case NOME:
	    	contato.setNome((String) valor);
	        break;
	    case APELIDO:
	    	contato.setApelido((String) valor);
	        break;
	    case TELEFONERES:
	    	contato.setTelefoneResidencial((String) valor);
	    	break;
	    case TELEFONECEL:
	    	contato.setTelefoneCelular((String) valor);
	    	break;
	    case CIDADE:
	    	contato.setCidade((String) valor);
	    	break;
	    case ESTADO:
	    	contato.setEstado((String) valor);
	    	break;
	    default:
	        // Não deve ocorrer, pois só existem 4 colunas
	        throw new IndexOutOfBoundsException("Número excedido de colunas");
	    }
	     
	    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}

	// Retorna o contato referente a linha especificada
	public Contato getContato(int indiceLinha) {
	    return linhas.get(indiceLinha);
	}
	 
	// Adiciona o contato especificado ao modelo
	public void addContato(Contato contato) {
	    // Adiciona o registro.
	    linhas.add(contato);
	 
	    // Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    int ultimoIndice = getRowCount() - 1;
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	// Remove o contato da linha especificada.
	public void removeContato(int indiceLinha) {
	    // Remove o registro.
	    linhas.remove(indiceLinha);
	 
	    // Notifica a mudança.
	    fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	 
	// Adiciona uma lista de contatos no final da lista.
	public void addListaContatos(List<Contato> contatos) {
	    // Pega o tamanho antigo da tabela, que servirá
	    // como índice para o primeiro dos novos registros
	    int indice = getRowCount();
	 
	    // Adiciona os registros.
	    linhas.addAll(contatos);
	 
	    // Notifica a mudança.
	    fireTableRowsInserted(indice, indice + contatos.size());
	}
	 
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista de contatos.
	    linhas.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}

}