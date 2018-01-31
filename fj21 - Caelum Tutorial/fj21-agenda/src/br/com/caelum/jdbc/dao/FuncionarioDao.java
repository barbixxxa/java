package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDao {
	private Connection connection;

	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Funcionario funcionario) {
		String sql = "insert into funcionarios " + "(nome,usuario,senha)" + " values (?,?,?)";

		try {
			// prepared statement para insercao
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Funcionario> getLista() {
		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from funcionarios");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto funcionario
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));

				// adicionando o objeto a lista
				funcionarios.add(funcionario);
				if (funcionarios.isEmpty()) {
					System.out.println("Nenhum Funcionario Cadastrado!");
				}
			}
			rs.close();
			stmt.close();
			return funcionarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Funcionario pesquisar(int id) {
		try {

			PreparedStatement stmt = this.connection.prepareStatement("select * from funcionarios where id=" + id);
			ResultSet rs = stmt.executeQuery();
			Funcionario funcionario = null;

			while (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));

			}

			rs.close();
			stmt.close();

			return funcionario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean altera(Funcionario funcionario) {
		Boolean alteracao = false;
		String sql = "update funcionarios set nome=?, usuario=?, senha=? where id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.setLong(4, funcionario.getId());

			alteracao = true;

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return alteracao;
	}

	public boolean remove(Funcionario funcionario) {
		boolean removido = false;
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from funcionarios where id=?");
			stmt.setLong(1, funcionario.getId());
			stmt.execute();
			stmt.close();
			removido = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return removido;
	}

}
