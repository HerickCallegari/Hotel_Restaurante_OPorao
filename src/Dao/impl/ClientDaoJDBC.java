package Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.ClientDao;
import db.DB;
import db.DbException;
import model.entities.City;
import model.entities.Client;
import model.entities.State;

public class ClientDaoJDBC implements ClientDao {

	private Connection conn;

	public ClientDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Client obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement(
					"insert into client (name, identifyCode, email, number , endereco, DataInicio, cityId) value (?, ?, ?, ?, ?, ?, ?);");

			st.setString(1, obj.getName());
			st.setString(2, obj.getIdentifyCode());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getNumber());
			st.setString(5, obj.getEndereco());
			st.setDate(6, new java.sql.Date(obj.getDate().getTime()));
			st.setInt(7, obj.getCity().getId());

			st.execute();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void delete(Client obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM client WHERE id = ?");
			
			st.setInt(1, obj.getId());
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}
	
	@Override
	public void deleteById(int id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM client WHERE id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Client obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE client SET "
					+ "name = ?, "
					+ "identifyCode = ?, "
					+ "email = ?, "
					+ "number = ?, "
					+ "endereco = ?, "
					+ "DataInicio = ?, "
					+ "cityId = ? "
					+ "WHERE id = ?;");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getIdentifyCode());
			st.setString(3, obj.getEmail());
			st.setString(4, obj.getNumber());
			st.setString(5, obj.getEndereco());
			st.setDate(6, new java.sql.Date(obj.getDate().getTime()));
			st.setInt(7, obj.getCity().getId());
			st.setInt(8, obj.getId());
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Client findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT client.id,"
					+ " client.name, "
					+ "client.identifyCode, "
					+ "client.email, "
					+ "client.endereco, "
					+ "client.number, "
					+ "client.DataInicio, "
					+ "client.cityId, "
					+ "city.name AS city, "
					+ "city.stateId, "
					+ "state.name AS state "
					+ "FROM client "
					+ "JOIN city ON client.cityId = city.id "
					+ "JOIN state ON city.stateId = state.id "
					+ "where client.Id = ?;"); 
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if (rs.next()) {
			return instanciateClient(rs);
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}

	@Override
	public List<Client> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Client> clients = new ArrayList<Client>();
		try {
			st = conn.prepareStatement("SELECT client.id, "
					+ "client.name, "
					+ "client.identifyCode, "
					+ "client.email, "
					+ "client.endereco, "
					+ "client.number, "
					+ "client.DataInicio, "
					+ "client.cityId, "
					+ "city.name AS city, "
					+ "city.stateId, "
					+ "state.name AS state "
					+ "FROM client "
					+ "JOIN city ON client.cityId = city.id "
					+ "JOIN state ON city.stateId = state.id ");

			rs = st.executeQuery();
		
				while (rs.next()) {
					clients.add(instanciateClient(rs));
				}
			
			return clients;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Client instanciateClient(ResultSet rs) throws SQLException {
		if (rs != null) {
			State state = new State(rs.getInt("stateId"), rs.getString("state"));
			City city = new City(rs.getInt("CityId"), rs.getString("city"), state);

			return new Client(rs.getInt("id"), 
					rs.getString("name"), 
					rs.getString("identifyCode"),
					rs.getString("number"), 
					rs.getString("endereco"), 
					rs.getString("email"), 
					rs.getDate("DataInicio"),
					city);
		} else {
			return null;
		}
	}

}
