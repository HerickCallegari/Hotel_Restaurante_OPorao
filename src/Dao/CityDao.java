package Dao;

import java.util.List;

import model.entities.City;

public interface CityDao {
	void insert ( City obj);
	void delete ( City obj);
	void update ( City obj);
	City findById ( int id);
	List<City> findAll();
}
