package validacao_campos;

public class ValidaData {

	public String validaData(String data) {
		try {

			String dia, mes, ano;
			int diaint, mesint, anoint;
			int verificarano;// variável usada para ver se o ano termina em 00
			String verano;// variável usada para capturar os dois dígitos finais
							// do ano
			int bissexto = 0; // igual à zero = bissexto, diferente disto ano
								// normal

			dia = data.substring(0, 2);// captura apenas o dia
			mes = data.substring(3, 5);// captura apenas o mês
			ano = data.substring(6, 10);// captura apenas o ano

			diaint = Integer.parseInt(dia);// transforma o valor de String para
											// int
			mesint = Integer.parseInt(mes);
			anoint = Integer.parseInt(ano);

			// Verificar se ano é bissexto
			int valorano = anoint % 4;// captura o valor do resto

			if (valorano == 0) {// Anos em que o resto será zero
				verano = data.substring(8, 10);// captura os dois dígitos finais
												// do ano
				verificarano = Integer.parseInt(verano);// transforma verano em
														// int
				bissexto = 0;// aciona ano bissexto, senão tiver esta linha os
								// anos que são bissexto mas não termina em 00,
								// serão anos normais
				if (verificarano == 0) {
					// ***validação de datas para anos com fim 00
					valorano = anoint % 400;
					if (valorano == 0) {
						bissexto = 0;// ano bissexto
					} else {
						bissexto = 1;// ano normal
					}
					// ***fim da validação de datas para anos com fim 00
				}// fim do verificarano==0
			}// fim do valorano==0
			else {// Anos em que o resto não será zero
				bissexto = 1;// ano normal
			}

			// verificar validade do mês
			if (mesint > 0 && mesint <= 12) {
				// verificar se o mês é fevereiro
				if (mesint == 02) {
					if (diaint >= 30 && bissexto == 0) {// ano bissexto
						return "dia";
					} else if (diaint >= 29 && bissexto == 1) {// ano normal
						return "dia";
					} else {
						return null;
					}
				} else if (mesint == 1 || mesint == 3 || mesint == 5
						|| mesint == 7 || mesint == 8 || mesint == 10
						|| mesint == 12) {
					if (diaint > 31) {
						return "dia";
					} else {
						return null;
					}
				} else if (mesint == 4 || mesint == 6 || mesint == 9
						|| mesint == 11) {
					if (diaint > 30) {
						return "dia";
					} else {
						return null;
					}
				}

			}// fim do mesint>12 || mesint <=0
			else {
				return "mês";
			}

		} catch (NumberFormatException e) {
			return "erro";
		}

		return null;
	}

}
