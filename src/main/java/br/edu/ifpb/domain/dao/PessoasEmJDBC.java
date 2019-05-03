package br.edu.ifpb.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.domain.jdbc.ConnectionFactory;

public class PessoasEmJDBC {
	
	private ConnectionFactory conFactory;
	
	public PessoasEmJDBC() {}
	
	public void nova(Pessoa pessoa) throws SQLException {
		try(Connection connection = conFactory.getConnection()){
			String sql = "INSERT INTO pessoa (nome,cpf,dependente) VALUES (?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf().valor());
			stmt.setString(3, pessoa.getDependente().getUuid());
//			Salva o dependente vinculado a pessoa na tabela dependente
			novoDependente(pessoa.getDependente());
			stmt.execute();
		}

	}
	
	private void novoDependente(Dependente dependente) throws SQLException {
		try(Connection connection = conFactory.getConnection()){
			String sql = "INSERT INTO dependente (nome,dataDeNascimento,uuid) VALUES (?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, dependente.getNome());
			stmt.setDate(2, java.sql.Date.valueOf(dependente.getDataDeNascimento()));
			stmt.setString(3, dependente.getUuid());
			stmt.execute();
		}
	}

	public List<Pessoa> todas() throws SQLException {
		try(Connection connection = conFactory.getConnection()){
			String query = "SLECT *"
						+ "FROM pessoa";
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			List<Pessoa> pessoas = new ArrayList<>();
			while(result.next()) {
				pessoas.add(new Pessoa(result.getString(1),
						new CPF(result.getString(2)),
						localizarDependenteComId(result.getString(3)))
						);
			}
			return pessoas;
		}
	}

	public List<Pessoa> buscarPorCPF(String cpf) throws SQLException {
		try(Connection connection = conFactory.getConnection()){
			String query = "SELECT *"
						+ "FROM pessoa"
						+ "WHERE cpf=?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, cpf);
			ResultSet result = stmt.executeQuery();
			List<Pessoa> pessoas = new ArrayList<>();
			while(result.next()) {
				pessoas.add(new Pessoa(result.getString(1),new CPF(result.getString(2)),localizarDependenteComId(result.getString(3))));
			}
			return pessoas;
		}
	}

	public void excluir(Pessoa pessoa) throws SQLException {
		try(Connection connection = conFactory.getConnection()){
			String query = "DELETE FROM pessoa WHERE cpf=?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, pessoa.getCpf().valor());
			stmt.executeQuery();
		}

	}

	public void atualizar(Pessoa pessoa) throws SQLException {
		try(Connection connection = conFactory.getConnection()){
			String query = "UPDATE pessoa SET nome=?, cpf=?, dependente=? WHERE cpf=?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf().valor());
			stmt.setString(3, pessoa.getDependente().getUuid());
			stmt.setString(4, pessoa.getCpf().valor());
			stmt.executeQuery();
		}

	}

	public List<Dependente> todosOsDepentendes() throws SQLException {
		List<Pessoa> pessoas = todas();
		List<Dependente> dependentes = new ArrayList<>();
		for(Pessoa p: pessoas) {
			dependentes.add(localizarDependenteComId(p.getDependente().getUuid()));
		}
		return dependentes;
	}

	public Dependente localizarDependenteComId(String uuid) throws SQLException {
		try(Connection connection = conFactory.getConnection()){
			String query = "SELECT * FROM dependente WHERE uuid=?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, uuid);
			ResultSet result = stmt.executeQuery();
			if(result.next()) 
				return new Dependente(result.getString(1), result.getDate(2).toLocalDate());
			return null;
		}
	}

}
