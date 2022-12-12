package dao;

import java.util.List;
import java.util.Map;

import fc.tag.Tag;

public interface DaoMediator {
	public List<Map<String, Object>> getDigitalMovies(List<List<Tag>> list) throws Exception;
	public List<Map<String, Object>> getCardInfo(int id) throws Exception;
	public List<Map<String, Object>> getAbonnesFromClient(int id) throws Exception;
	public List<Map<String, Object>> getStockBluRay(int id) throws Exception;
}
