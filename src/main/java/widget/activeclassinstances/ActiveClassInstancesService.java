package widget.activeclassinstances;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the service class for the ActiveClassInstances Widget. This class is
 * a singelton and have methods for updating the current models or get the
 * current models
 * 
 * @author Florian Krippner
 *
 */
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

	/**
	 * This class update the current list of ActiveClassInstancesModels with a new
	 * one.
	 * 
	 * @param activeClassInstances the new List of ActiveClassInstancesModels
	 */
	public void update(List<ActiveClassInstancesModel> activeClassInstances) {
		this.activeClassInstances = activeClassInstances;
	}

	/**
	 * This method returns the current list of ActiveClassInstancesModels.
	 * 
	 * @param amount this sets the amount of models to be returned. if this is set
	 *               to zero, then there is no limit.
	 * @return returns a list of ActiveClassInstancesModels
	 */
	public List<ActiveClassInstancesModel> getActiveClassInstances(int amount) {

		if (amount == 0) {
			return activeClassInstances;
		}

		List<ActiveClassInstancesModel> temp = new ArrayList<>(activeClassInstances);

		if (amount >= temp.size()) {
			amount = temp.size();
		}

		temp = ActiveClassInstancesService.getInstance().sortByInstances(temp);

		return temp.subList(0, amount);
	}

	/**
	 * This method can sort a list of ActiveClassInstancesModel by the instance
	 * amount.
	 * 
	 * @param oldList the list to be sorted.
	 * @return returns a sorted list of ActiveClassInstancesModels
	 */
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
