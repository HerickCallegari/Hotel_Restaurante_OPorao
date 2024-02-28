package Dao;

import java.util.List;

import model.entities.Client;

public interface ClientDao {
	void insert ( Client obj);
	void delete ( Client obj);
	void update ( Client obj);
	Client findById ( int id);
	List<Client> findAll();
	void deleteById(int id);
}
