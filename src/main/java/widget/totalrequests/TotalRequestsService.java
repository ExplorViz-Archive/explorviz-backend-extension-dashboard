package widget.totalrequests;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import persistence.MongoDashboardRepository;

/**
 * This is the service class of the total requests widget. This class is a
 * singelton.
 * 
 * @author Florian Krippner
 *
 */
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

	/**
	 * This method updates the widget with newer data
	 * 
	 * @param m a newer TotalRequestsModel for the update
	 */
	public void update(TotalRequestsModel m) {
		MongoDashboardRepository.getInstance().save(m.convert(), this);
	}

	/**
	 * 
	 * @return This method returns the TotalRequestsModel of the last landscape
	 */
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

	/**
	 * 
	 * @return This method returns a list of all TotalRequestsModels in the
	 *         database.
	 */
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

	/**
	 * 
	 * @return This method returns a list of the last 10 TotalRequestsModels from
	 *         the last 10 landscapes.
	 */
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

}
