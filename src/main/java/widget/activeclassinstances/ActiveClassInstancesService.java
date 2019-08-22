package widget.activeclassinstances;

import java.util.ArrayList;
import java.util.List;

public class ActiveClassInstancesService {

	private static ActiveClassInstancesService instance;
	private List<ActiveClassInstancesModel> activeClassInstances = new ArrayList<ActiveClassInstancesModel>();

	private ActiveClassInstancesService() {
	}

	public static ActiveClassInstancesService getInstance() {
		if (ActiveClassInstancesService.instance == null) {
			ActiveClassInstancesService.instance = new ActiveClassInstancesService();
		}
		return ActiveClassInstancesService.instance;
	}



	public void update(List<ActiveClassInstancesModel> activeClassInstances) {
		this.activeClassInstances = activeClassInstances;
	}
	
	public List<ActiveClassInstancesModel> getActiveClassInstances(int amount) {

		
		if(amount == 0) {
			return activeClassInstances;
		}
		
		List<ActiveClassInstancesModel> temp = new ArrayList<>(activeClassInstances);
		
		

		
		if(amount >= temp.size()) 
		{
			amount = temp.size();
		}

	
		temp = ActiveClassInstancesService.getInstance().sortByInstances(temp);
		
		return temp.subList(0, amount);
	}

	public List<ActiveClassInstancesModel> sortByInstances(List<ActiveClassInstancesModel> oldList) {

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

		return newList;
	}

}
