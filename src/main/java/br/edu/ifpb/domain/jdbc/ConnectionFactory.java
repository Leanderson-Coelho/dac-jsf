package br.edu.ifpb.domain.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @deprecated Porque é ruim
 */
public class ConnectionFactory {
	//fonte: https://github.com/Projeto1-PW1/Leriado/blob/master/leriadoApp/src/main/java/com/ifpb/edu/model/jdbc/ConnectionFactory.java
	//REUSO DO CÓDIGO DO PROJETO DE PW1 QUE PARTICIPEI PARA CONEXÃO COM BANCO
	
	private static String user;
	private static String password;
	private static String url;
	private static String driver;
	private static ConnectionFactory instance = null;
	private static Connection connection = null;
	
	private ConnectionFactory() {}
	
	public static ConnectionFactory getInstance() {
		if(instance == null) {
			synchronized (ConnectionFactory.class) {
				if(instance == null) {
					instance = new ConnectionFactory();
				}
			}
		}
		return instance;
	}
	
	static {
		Properties properties = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		url = properties.getProperty("database.url");
		user = properties.getProperty("database.user");
		password = properties.getProperty("database.password");
		driver = properties.getProperty("database.driver");
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return connection;
	}
}
