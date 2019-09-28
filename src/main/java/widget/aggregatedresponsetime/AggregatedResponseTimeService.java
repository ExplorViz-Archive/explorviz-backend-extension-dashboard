package widget.aggregatedresponsetime;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import persistence.MongoDashboardRepository;

/**
 * This is the service class for the AggregatedResponseTime widget. This class
 * is a singelton.
 * 
 * @author Florian Krippner
 *
 */
public class AggregatedResponseTimeService {

	private static AggregatedResponseTimeService instance;

	private AggregatedResponseTimeService() {
	}

	public static synchronized AggregatedResponseTimeService getInstance() {
		if (AggregatedResponseTimeService.instance == null) {
			AggregatedResponseTimeService.instance = new AggregatedResponseTimeService();
		}
		return AggregatedResponseTimeService.instance;
	}

	private List<AggregatedResponseTimeModel> aggregatedResponseTimes = new ArrayList<AggregatedResponseTimeModel>();

	/**
	 * This function updates the model with new data. The data is getting saved into
	 * the database.
	 * 
	 * @param updatedAggregatedResponseTimes needs a new list of
	 *                                       AggregatedResponseTimeModels
	 */
	public void update(List<AggregatedResponseTimeModel> updatedAggregatedResponseTimes) {

		if (!updatedAggregatedResponseTimes.isEmpty()) {
			Map<String, Object> table = new Hashtable<>();
			table.put("type", "aggregatedresponsetimeinfo");
			table.put("timestampLandscape", updatedAggregatedResponseTimes.get(0).getTimestampLandscape());
			table.put("entries", updatedAggregatedResponseTimes.size());
			MongoDashboardRepository.getInstance().save(table, this);

			for (AggregatedResponseTimeModel m : updatedAggregatedResponseTimes) {
				MongoDashboardRepository.getInstance().save(m.convert(), this);
			}
		}

		aggregatedResponseTimes = sortByResponseTime(updatedAggregatedResponseTimes);
	}

	/**
	 * @param timestampLandscape The timestamp of a landscape.
	 * @return This function returns a list of AggregatedResponseTimeModels to a
	 *         given timestamp of a landscape.
	 */
	public List<AggregatedResponseTimeModel> getAggregatedResponseTimes(long timestampLandscape) {

		Map<String, Object> query = new Hashtable<>();
		query.put("type", "aggregatedresponsetime");
		query.put("timestampLandscape", timestampLandscape);

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, this);
		List<AggregatedResponseTimeModel> result = new ArrayList<AggregatedResponseTimeModel>();

		queryResult.forEach(map -> {
			result.add(AggregatedResponseTimeModel.convert(map));

		});

		return sortByResponseTime(result);

	}

	/**
	 * 
	 * @param limit this paramter limits the amount of entries in the list. the list
	 *              is sorted.
	 * @return This function returns the list of AggregatedResponseTimeModels from
	 *         the last landscape.
	 */
	public List<AggregatedResponseTimeModel> getLastAggregatedResponseTimes(int limit) {

		if (limit >= aggregatedResponseTimes.size()) {
			limit = aggregatedResponseTimes.size();
		}

		return aggregatedResponseTimes.subList(0, limit);
	}

	/**
	 * 
	 * @param limit this paramter limits the amount of entries in the list.
	 * @return returns a list of AggregatedResponseTimeInfoModels
	 */
	public List<AggregatedResponseTimeInfoModel> getAggregatedResponseTimeInfos(int limit) {

		Map<String, Object> query = new Hashtable<>();
		query.put("type", "aggregatedresponsetimeinfo");
		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, limit, this);

		List<AggregatedResponseTimeInfoModel> result = new ArrayList<AggregatedResponseTimeInfoModel>();
		queryResult.forEach(map -> {
			result.add(AggregatedResponseTimeInfoModel.convert(map));

		});
		return result;

	}

	/**
	 * This method sort a list of AggregatedResponseTimeModels. it gets sorted by
	 * the response time. largest first.
	 * 
	 * @param oldList this parameter is the list that needs to be sorted
	 * @return returns a list of AggregatedResponseTimeModels
	 */
	public List<AggregatedResponseTimeModel> sortByResponseTime(List<AggregatedResponseTimeModel> oldList) {
		List<AggregatedResponseTimeModel> newList = new ArrayList<AggregatedResponseTimeModel>();

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
