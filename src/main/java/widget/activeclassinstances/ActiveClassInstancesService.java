package widget.activeclassinstances;

import java.util.ArrayList;
import java.util.List;

public class ActiveClassInstancesService {

	private static ActiveClassInstancesService instance;

	private ActiveClassInstancesService() {
	}

	public static ActiveClassInstancesService getInstance() {
		if (ActiveClassInstancesService.instance == null) {
			ActiveClassInstancesService.instance = new ActiveClassInstancesService();
		}
		return ActiveClassInstancesService.instance;
	}

	public void start() {
		if (ActiveClassInstancesModel.ActiveClassInstances == null) {
			ActiveClassInstancesModel.ActiveClassInstances = new ArrayList<ActiveClassInstancesModel>();

			ActiveClassInstancesModel test = new ActiveClassInstancesModel("test_landscape_id123123", "classname_hi",
					212);

			ActiveClassInstancesModel.ActiveClassInstances.add(test);

			ActiveClassInstancesModel test2 = new ActiveClassInstancesModel("test_landscape_id234234", "classname_ho",
					5);
			ActiveClassInstancesModel.ActiveClassInstances.add(test2);

			ActiveClassInstancesModel test3 = new ActiveClassInstancesModel("test_landscape_id77777", "classname_ha",
					42);
			ActiveClassInstancesModel.ActiveClassInstances.add(test3);
		}

	}

	public void update(List<ActiveClassInstancesModel> activeClassInstances) {

		// ActiveClassInstancesModel.ActiveClassInstances = new
		// ArrayList<ActiveClassInstancesModel>();
		ActiveClassInstancesModel.ActiveClassInstances = activeClassInstances;

		//System.out.println("activeClassInstances Size nach update: " + activeClassInstances.size());
	}

	public List<ActiveClassInstancesModel> sortByInstances(List<ActiveClassInstancesModel> oldList) {
		System.out.println("Listsize: " + oldList.size());
		List<ActiveClassInstancesModel> newList = new ArrayList<ActiveClassInstancesModel>();

		while (oldList.size() != 0) {
			int highest = 0;
			int index = 0;

			for (int i = 0; i < oldList.size(); i++) {
				if (highest <= oldList.get(i).getInstances()) {
					highest = oldList.get(i).getInstances();
					index = i;
				}
			}

			newList.add(oldList.get(index));
			oldList.remove(index);
		}
		//System.out.println("Listsize: " + newList.size());
		return newList;
	}

}
