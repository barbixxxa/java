package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaRemove {
	public static void main(String[] args) {
		Contato contato = new Contato();
		ContatoDao dao = new ContatoDao();

		contato.setId((long) 4);

		if (dao.remove(contato)) {
			System.out.println("Contato removido com sucesso!");
		}
	}

}