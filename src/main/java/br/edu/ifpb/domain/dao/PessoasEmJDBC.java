package br.edu.ifpb.domain.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.domain.*;
import br.edu.ifpb.domain.jdbc.ConnectionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;

@Stateless
public class PessoasEmJDBC implements Pessoas {


    @Inject
    private Dependentes dependentes;

    @Resource(name = "java:app/jdbc/postgres")
    private DataSource dataSource;

    private Connection connection;


    public PessoasEmJDBC() {
    }

    @PostConstruct
    public void init() {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void nova(Pessoa pessoa) {
        try {
            String sql = "INSERT INTO pessoa (nome,cpf,dependente_id) VALUES (?,?,?)";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf().valor());
            if (pessoa.getDependente() != null) {
                stmt.setString(3, pessoa.getDependente().getUuid());
//			Salva o dependente vinculado a pessoa na tabela dependente
                dependentes.novo(pessoa.getDependente());
            } else {
                stmt.setString(3, null);
            }
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Pessoa> todas() {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            String query = "SELECT * FROM pessoa";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                pessoas.add(new Pessoa(result.getString(2),
                        new CPF(result.getString(3)),
                        dependentes.localizarDependenteComId(result.getString(4)))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public List<Pessoa> buscarPorCPF(String cpf) {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            String query = "SELECT * FROM pessoa WHERE cpf=?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, cpf);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                pessoas.add(new Pessoa(result.getString(1), new CPF(result.getString(2)), dependentes.localizarDependenteComId(result.getString(3))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void excluir(Pessoa pessoa) {
        try{
            String query = "DELETE FROM pessoa WHERE cpf=?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, pessoa.getCpf().valor());
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void atualizar(Pessoa pessoa) {
        try{
            String query = "UPDATE pessoa SET nome=?, cpf=?, dependente_id=? WHERE cpf=?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf().valor());
            if (pessoa.getDependente() != null) {
                stmt.setString(3, pessoa.getDependente().getUuid());
            } else {
                stmt.setString(3, null);
            }
            stmt.setString(4, pessoa.getCpf().valor());
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
