package Dao;

import Dao.impl.ClientDaoJDBC;
import Dao.impl.StateDaoJDBC;
import db.DB;

public class DaoFactory {
	public static ClientDao createClientDao() {
		return new ClientDaoJDBC(DB.getConnection());
	}
	
	public static StateDao createStateDao() {
		return new StateDaoJDBC(DB.getConnection());
	}
}
