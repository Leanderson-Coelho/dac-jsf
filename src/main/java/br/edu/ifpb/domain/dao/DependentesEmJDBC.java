package br.edu.ifpb.domain.dao;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Dependentes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class DependentesEmJDBC implements Dependentes {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Resource(name = "java:app/jdbc/postgres")
    private DataSource dataSource;

    private Connection connection;

    public DependentesEmJDBC(){}

    @PostConstruct
    public void init(){
        try {
            this.connection = this.dataSource.getConnection();
            this.log.info("Cliente Info:");
            this.log.info(String.valueOf(this.connection.getClientInfo()));
        } catch (SQLException e) {
            log.warning("VIsh é a conexao mesmo");
            e.printStackTrace();
        }
    }


    @Override
    public void novo(Dependente dependente) {
        try {
            String sql = "INSERT INTO dependente (nome,dataDeNascimento,uuid) VALUES (?,?,?)";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, dependente.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(dependente.getDataDeNascimento()));
            stmt.setString(3, dependente.getUuid());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Dependente> todos() {
        List<Dependente> dependentes = new ArrayList<>();
        try {
            String query = "SELECT * FROM dependente";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                dependentes.add(new Dependente(
                        result.getString(1),
                        result.getString(2),
                        result.getDate(3).toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dependentes;
    }

    @Override
    public void excluir(Dependente dependente) {
        try{
            String query = "DELETE FROM dependente WHERE uuid=?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, dependente.getUuid());
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Dependente dependente) {
        try{
            String query = "UPDATE dependente SET uuid=?, nome=?, dataDeNascimento=? WHERE uuid=?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, dependente.getUuid());
            stmt.setString(2, dependente.getNome());
            stmt.setDate(3, Date.valueOf(dependente.getDataDeNascimento()));
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dependente localizarDependenteComId(String uuid) {
        try {
            String query = "SELECT * FROM dependente WHERE uuid=?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, uuid);
            ResultSet result = stmt.executeQuery();
            if (result.next())
                return new Dependente(
                        result.getString(1),
                        result.getString(2),
                        result.getDate(3).toLocalDate()
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
