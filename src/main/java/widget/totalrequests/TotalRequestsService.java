package widget.totalrequests;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import persistence.MongoDashboardRepository;

public class TotalRequestsService {

	private static TotalRequestsService instance;

	private TotalRequestsService() {
	}

	public static TotalRequestsService getInstance() {
		if (TotalRequestsService.instance == null) {
			TotalRequestsService.instance = new TotalRequestsService();
		}
		return TotalRequestsService.instance;
	}

	public void update(TotalRequestsModel m) {
		MongoDashboardRepository.getInstance().save(m.convert(), this);
	}

	public TotalRequestsModel getCurrentModel() {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "totalrequests");

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, 1, this);
		List<TotalRequestsModel> result = new ArrayList<TotalRequestsModel>();

		queryResult.forEach(map -> {
			result.add(TotalRequestsModel.convert(map));

		});

		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}

	}

	public List<TotalRequestsModel> getAllModels() {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "totalrequests");

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().query(query, this);
		List<TotalRequestsModel> result = new ArrayList<TotalRequestsModel>();

		queryResult.forEach(map -> {
			result.add(TotalRequestsModel.convert(map));

		});

		return result;
	}

	public List<TotalRequestsModel> getRecentModels() {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "totalrequests");

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, 10, this);
		List<TotalRequestsModel> result = new ArrayList<TotalRequestsModel>();

		queryResult.forEach(map -> {
			result.add(TotalRequestsModel.convert(map));

		});

		return result;
	}

	/**
	 * Gibt eine reduzierte Liste von TotalRequestsModel's aus der Datenbank zurück.
	 * Hierbei wird der durchschnitt der Daten gebildet.
	 * 
	 * @return List<TotalRequestsModel>
	 */
	/*
	 * public List<TotalRequestsModel> getReducedModels() {
	 * 
	 * List<TotalRequestsModel> allData =
	 * MongoDashboardRepository.getInstance().getAllTotalRequests();
	 * //System.out.println("Size: " + allData.size());
	 * 
	 * List<TotalRequestsModel> newList = new ArrayList<TotalRequestsModel>();
	 * 
	 * int length = allData.size(); int from = 0; int spectrum = 360;
	 * 
	 * while (length >= spectrum) { length -= spectrum; List<TotalRequestsModel>
	 * temp = allData.subList(from, from + spectrum);
	 * 
	 * int totalrequests = 0; long timestamp = 0;
	 * 
	 * String landscapeid = temp.get(0).getLandscapeID() + " to " +
	 * temp.get(temp.size() - 1).getLandscapeID();
	 * 
	 * for (int i = 0; i < temp.size(); i++) { timestamp +=
	 * temp.get(i).getTimestamp(); totalrequests += temp.get(i).getTotalRequests();
	 * }
	 * 
	 * totalrequests /= temp.size(); timestamp /= temp.size();
	 * 
	 * TotalRequestsModel averageModel = new TotalRequestsModel(landscapeid,
	 * totalrequests, timestamp); newList.add(averageModel);
	 * 
	 * from += 360; }
	 * 
	 * for (int i = length; i >= 0; i--) { newList.add(allData.get(allData.size() -
	 * i - 1)); }
	 * 
	 * return newList;
	 * 
	 * }
	 */

}
