package widget.operationresponsetime;

import java.util.ArrayList;
import java.util.List;

import persistence.MongoDashboardRepository;

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

	private List<OperationResponseTimeModel> operationResponseTimes = new ArrayList<OperationResponseTimeModel>();

	public void update(List<OperationResponseTimeModel> updatedOperationResponseTimes) {

		if (!updatedOperationResponseTimes.isEmpty()) {

			for (OperationResponseTimeModel m : updatedOperationResponseTimes) {

				MongoDashboardRepository.getInstance().saveOperationResponseTime(m);
			}

			MongoDashboardRepository.getInstance().saveOperationResponseTimeInfo(new OperationResponseTimeInfoModel(updatedOperationResponseTimes.get(0).getTimestampLandscape(),updatedOperationResponseTimes.size()));
			/*
			for(OperationResponseTimeInfoModel m : MongoDashboardRepository.getInstance().getOperationResponseTimeInfos(10)) {
				System.out.println(m.toString());
			}
			
			
			List<OperationResponseTimeModel> test = MongoDashboardRepository.getInstance()
					.getOperationResponseTime(updatedOperationResponseTimes.get(0).getTimestampLandscape(), 10000);
					
			
			for (OperationResponseTimeModel m : test) {

				System.out.println(m.toString());
			}
			*/
		}

		operationResponseTimes = sortByResponseTime(updatedOperationResponseTimes);
	}
	
	public List<OperationResponseTimeInfoModel> getOperationResponseTimeInfo(int limit){
		return MongoDashboardRepository.getInstance().getOperationResponseTimeInfos(limit);
	}

	public List<OperationResponseTimeModel> getOperationResponseTimes(long timestampLandscape, int limit) {
		List<OperationResponseTimeModel> result = MongoDashboardRepository.getInstance().getOperationResponseTime(timestampLandscape, limit);
		if(result != null) {
			return sortByResponseTime(result);
		}
		return null;
	
	}

	public List<OperationResponseTimeModel> getOperationResponseTimes(int limit) {

		if (limit >= operationResponseTimes.size()) {
			limit = operationResponseTimes.size();
		}

		return operationResponseTimes.subList(0, limit);
	}

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
