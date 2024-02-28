package Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.StateDao;
import db.DbException;
import model.entities.City;
import model.entities.State;

public class StateDaoJDBC implements StateDao{
	
	private Connection conn;
	
	public StateDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public State findById(int id) {
		return null;
	}

	private State instaciate(ResultSet rs) throws SQLException {
		State state = new State(rs.getInt("id"), rs.getString("name"));
		return state;
	}

	@Override
	public List<State> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<State> states = new ArrayList<>();
		try {
			st = conn.prepareStatement("select * from state");
			rs = st.executeQuery();
			while ( rs.next()) {
				states.add(instaciate(rs));
			}
			
		}catch ( SQLException e) {
			throw new DbException(e.getMessage());
		}
		return states;
	}

	@Override
	public List<City> findAllCitys(State obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<City> cities = new ArrayList<>();
		try {
			st = conn.prepareStatement("select city.* from city where stateId = ?;");
			st.setInt(1, obj.getId());
			rs = st.executeQuery();
			while ( rs.next()) {
				cities.add(new City(rs.getInt("id"), rs.getString("name"), obj));
			}
			}catch (SQLException e) {
				throw new DbException(e.getMessage()); 
			}
		
		return cities;
	}

}
