package widget.aggregatedresponsetime;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import persistence.MongoDashboardRepository;

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
	
	public List<AggregatedResponseTimeModel> getLastAggregatedResponseTimes(int limit) {
		
		if (limit >= aggregatedResponseTimes.size()) {
			limit = aggregatedResponseTimes.size();
		}

		return aggregatedResponseTimes.subList(0, limit);
	}

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
