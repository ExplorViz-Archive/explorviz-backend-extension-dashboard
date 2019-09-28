package widget.operationresponsetime;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import persistence.MongoDashboardRepository;

/**
 * This is the service class of the OperationResponseTime Widget. This class is
 * a singelton
 * 
 * @author Florian Krippner
 *
 */
public class OperationResponseTimeService {

	private static OperationResponseTimeService instance;

	private OperationResponseTimeService() {
	}

	public static synchronized OperationResponseTimeService getInstance() {
		if (OperationResponseTimeService.instance == null) {
			OperationResponseTimeService.instance = new OperationResponseTimeService();
		}
		return OperationResponseTimeService.instance;
	}

	// a list of the OperationResponseTimeModels of the latest landscape
	private List<OperationResponseTimeModel> operationResponseTimes = new ArrayList<OperationResponseTimeModel>();

	/**
	 * This method updates the OperationResponseTime widget with new data. the data
	 * will be stored inside the database.
	 * 
	 * @param updatedOperationResponseTimes a list of OperationResponseTimeModels
	 *                                      for the update.
	 */
	public void update(List<OperationResponseTimeModel> updatedOperationResponseTimes) {

		if (!updatedOperationResponseTimes.isEmpty()) {

			for (OperationResponseTimeModel m : updatedOperationResponseTimes) {

				MongoDashboardRepository.getInstance().save(m.convert(), this);
			}

			Map<String, Object> table = new Hashtable<>();
			table.put("type", "operationresponsetimeinfo");
			table.put("timestampLandscape", updatedOperationResponseTimes.get(0).getTimestampLandscape());
			table.put("amount", updatedOperationResponseTimes.size());
			MongoDashboardRepository.getInstance().save(table, this);
		}

		operationResponseTimes = sortByResponseTime(updatedOperationResponseTimes);
	}

	/**
	 * 
	 * @param limit this parameter limit the size of the returned list
	 * @return This method returns a list of OperationResponseTimeInfoModel
	 */
	public List<OperationResponseTimeInfoModel> getOperationResponseTimeInfo(int limit) {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "operationresponsetimeinfo");
		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, limit, this);

		List<OperationResponseTimeInfoModel> result = new ArrayList<OperationResponseTimeInfoModel>();
		queryResult.forEach(map -> {
			result.add(OperationResponseTimeInfoModel.convert(map));

		});
		return result;
	}

	/**
	 * 
	 * @param timestampLandscape the timestamp of a landscape for the query
	 * @param limit              this paramter limit the size of the returned list
	 * @return this method returns a list of OperationResponseTimeModels for a given
	 *         landscape
	 */
	public List<OperationResponseTimeModel> getOperationResponseTimes(long timestampLandscape, int limit) {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "operationresponsetime");
		query.put("timestampLandscape", timestampLandscape);
		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, limit, this);

		List<OperationResponseTimeModel> result = new ArrayList<OperationResponseTimeModel>();
		queryResult.forEach(map -> {
			result.add(OperationResponseTimeModel.convert(map));

		});
		return sortByResponseTime(result);

	}

	/**
	 * 
	 * @param limit limit the size of the returned list
	 * @return this function returns a list of OperationResponseTimeModel of the
	 *         latest landscape
	 */
	public List<OperationResponseTimeModel> getOperationResponseTimes(int limit) {

		if (limit >= operationResponseTimes.size()) {
			limit = operationResponseTimes.size();
		}

		return operationResponseTimes.subList(0, limit);
	}

	/**
	 * this list sort a list of OperationResponseTimeModels by the response time.
	 * biggest first.
	 * 
	 * @param oldList the list to sort
	 * @return returns a list of OperationResponseTimeModel sorted.
	 */
	public List<OperationResponseTimeModel> sortByResponseTime(List<OperationResponseTimeModel> oldList) {
		List<OperationResponseTimeModel> newList = new ArrayList<OperationResponseTimeModel>();

		while (oldList.size() != 0) {
			float highest = 0;
			int index = 0;

			for (int i = 0; i < oldList.size(); i++) {
				if (highest <= oldList.get(i).getAverageResponseTime()) {
					highest = oldList.get(i).getAverageResponseTime();
					index = i;
				}
			}

			newList.add(oldList.get(index));
			oldList.remove(index);
		}
		return newList;
	}

}
