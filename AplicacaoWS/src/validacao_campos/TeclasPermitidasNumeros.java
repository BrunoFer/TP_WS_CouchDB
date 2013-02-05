package validacao_campos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TeclasPermitidasNumeros extends PlainDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void insertString(int offset, String str, AttributeSet atributo) throws BadLocationException{
		super.insertString(offset, str.replaceAll("[^0-9]", ""), atributo);
	}

	public void replace(int offset, String str, AttributeSet atributo) throws BadLocationException{
		super.insertString(offset, str.replaceAll("[^0-9]", ""), atributo);
	}
}
