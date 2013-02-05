package validacao_campos;

public class ValidaData {

	public String validaData(String data) {
		try {

			int dia, mes, ano;
			int verificaAno;// variável usada para ver se o ano termina em 00
			String verano;// variável usada para capturar os dois dígitos finais
							// do ano
			int bissexto = 0; // igual à zero = bissexto, diferente disto ano
								// normal

			// captura os valores da string data e passa para inteiro
			dia = Integer.parseInt(data.substring(0, 2));
			mes = Integer.parseInt(data.substring(3, 5));
			ano = Integer.parseInt(data.substring(6, 10));

			// Verificar se ano é bissexto
			int valorano = ano % 4;// captura o valor do resto

			if (valorano == 0) {// Anos em que o resto será zero
				verano = data.substring(8, 10);// captura os dois dígitos finais
												// do ano
				verificaAno = Integer.parseInt(verano);// transforma verano em
														// int
				bissexto = 0;// aciona ano bissexto, senão tiver esta linha os
								// anos que são bissexto mas não termina em 00,
								// serão anos normais
				if (verificaAno == 0) {
					// ***validação de datas para anos com fim 00
					valorano = ano % 400;
					if (valorano == 0) {
						bissexto = 0;// ano bissexto
					} else {
						bissexto = 1;// ano normal
					}
					// ***fim da validação de datas para anos com fim 00
				}// fim do verificaAno==0
			}// fim do valorano==0
			else {// Anos em que o resto não será zero
				bissexto = 1;// ano normal
			}

			// verifica validade do mês
			if (mes > 0 && mes <= 12) {
				// verifica se o mês é fevereiro
				if (mes == 02) {
					if (dia >= 30 && bissexto == 0) {// ano bissexto
						return "dia";
					} else if (dia >= 29 && bissexto == 1) {// ano normal
						return "dia";
					} else {
						return null;
					}
				} else if (mes == 1 || mes == 3 || mes == 5 || mes == 7
						|| mes == 8 || mes == 10 || mes == 12) {
					if (dia > 31) {
						return "dia";
					} else {
						return null;
					}
				} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
					if (dia > 30) {
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
