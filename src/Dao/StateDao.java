package Dao;

import java.util.List;

import model.entities.City;
import model.entities.State;

public interface StateDao {
	State findById ( int id);
	List<State> findAll();
	List<City> findAllCitys( State obj);
}
