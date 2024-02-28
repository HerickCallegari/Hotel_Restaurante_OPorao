package model.services;

import java.util.List;

import Dao.DaoFactory;
import Dao.StateDao;
import model.entities.City;
import model.entities.State;

public class StateService {
	
	private StateDao dao = DaoFactory.createStateDao();
	
	public List<State> findAll() {
		return dao.findAll();
	}
	
	public List<City> findAllCities(State obj) {
		return dao.findAllCitys(obj);
	}
}
