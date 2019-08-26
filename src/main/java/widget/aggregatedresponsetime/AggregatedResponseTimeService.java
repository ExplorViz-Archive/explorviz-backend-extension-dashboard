package widget.aggregatedresponsetime;

import java.util.ArrayList;
import java.util.List;

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

	public void update(List<AggregatedResponseTimeModel> updatedAggregatedResponseTimes) {
		
		if(!updatedAggregatedResponseTimes.isEmpty()) {
			AggregatedResponseTimeInfoModel info = new AggregatedResponseTimeInfoModel(updatedAggregatedResponseTimes.get(0).getTimestampLandscape(), updatedAggregatedResponseTimes.size());
			MongoDashboardRepository.getInstance().saveAggregatedResponseTimeInfo(info);
		
		for (AggregatedResponseTimeModel m : updatedAggregatedResponseTimes) {
			MongoDashboardRepository.getInstance().saveAggregatedResponseTime(m);
			System.out.println(m.toString());
		}
		}
	}

	public List<AggregatedResponseTimeModel> getAggregatedResponseTimes(long timestampLandscape) {
		return MongoDashboardRepository.getInstance().getAggregatedResponseTimes(timestampLandscape);

	}
	
	public List<AggregatedResponseTimeInfoModel> getAggregatedResponseTimeInfos(int limit){
		return MongoDashboardRepository.getInstance().getAggregatedResponseTimeInfos(limit);
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
