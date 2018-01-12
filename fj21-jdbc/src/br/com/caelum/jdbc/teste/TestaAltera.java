package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaAltera {
	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		Contato contato = new Contato();

		contato.setNome("Maluco Beleza");
		contato.setEmail("maluco@beleza.gov.br");
		contato.setEndereco("Palacio dos Malucos, 666");
		contato.setDataNascimento(Calendar.getInstance());
		contato.setId((long) 0);

		if(dao.altera(contato)) {
			System.out.println("Alteracao realizada com sucesso!");
		}
	}
}
