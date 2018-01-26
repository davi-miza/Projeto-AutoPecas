package br.com.autopecas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.autopecas.bean.loginBean;
import br.com.autopecas.database.Database;

public class loginDAO {
	
	private Connection connection;
	
	public void getConnection() throws SQLException {
		try (Connection connection  = new Database().getConnection()) {
		}
	}

	public void insere(loginBean login) throws SQLException {
			String sql = "INSERT INTO Login(nome, senha, chave) VALUES (?,?,?)";
		
			getConnection();
			try(PreparedStatement stm = connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS)) {
				stm.setString(1, login.getNome());
				stm.setString(2, login.getSenha());
				stm.setString(3, login.getChave());
				stm.execute();
				
				try (ResultSet rs = stm.getGeneratedKeys()){
					if (rs.next()) {
						int id = rs.getInt("id");
						login.setId(id);
					}
				}
			}
	}
	
	
	public void delete(loginBean login) throws SQLException {
		String sql = "DELETE FROM Login WHERE id = ?";
		
		getConnection();
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setInt(1, login.getId());
			stm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	
	public void update(loginBean login) throws SQLException {
		String sql = "UPDATE Login SET nome = ?, senha = ?, chave = ? WHERE id = ?";
		
		getConnection();
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, login.getNome());
			stm.setString(2, login.getSenha());
			stm.setString(3, login.getChave());
			stm.setInt(4, login.getId());
			stm.execute();
		}
	}
	
	public ArrayList<loginBean> list() throws SQLException {
		String sql = "SELECT * FROM Login";
		ArrayList<loginBean> lista = new ArrayList<>();
		
		getConnection();
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.executeQuery();
			
			try(ResultSet rs = stm .getResultSet()) {
				while (rs.next()) {
					loginBean login = new loginBean();
					login.setId(rs.getInt("id"));
					login.setNome(rs.getString("nome"));
					login.setSenha(rs.getString("senha"));
					login.setChave(rs.getString("chave"));
					lista.add(login);
				}
			} 
		}
		
		return lista;
	}
}
