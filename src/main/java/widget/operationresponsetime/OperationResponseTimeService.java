package widget.operationresponsetime;

import java.util.ArrayList;
import java.util.List;

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
		operationResponseTimes = sortByResponseTime(updatedOperationResponseTimes);
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
