package instantiatedwidgets;

import java.util.List;
import persistence.MongoDashboardRepository;

/**
 * This class is the service class from the InstantiatedWidget. This class has
 * methods for setting and deleting InstantiatedWidgets. This class is a
 * singelton and only can be instantiated once.
 * 
 * @author Florian Krippner
 */
public class InstantiatedWidgetService {

	private static InstantiatedWidgetService instance;

	private InstantiatedWidgetService() {
	}

	public static InstantiatedWidgetService getInstance() {
		if (InstantiatedWidgetService.instance == null) {
			InstantiatedWidgetService.instance = new InstantiatedWidgetService();
		}
		return InstantiatedWidgetService.instance;
	}

	/**
	 * this method get all InstantiatedWidgets for a given user id.
	 * 
	 * @param userID needs the user id for the query
	 * @return returns a List of InstantiatedWidgetModels
	 */
	public List<InstantiatedWidgetModel> getInstantiatedWidgets(String userID) {

		return MongoDashboardRepository.getInstance().getInstantiatedWidgets(userID);

	}

	/**
	 * this method save a InstantiatedWidgetModel into the mongodb
	 * 
	 * @param model the InstantiatedWidgetModel that needs to be saved
	 */
	public void setInstantiatedWidget(InstantiatedWidgetModel model) {
		MongoDashboardRepository.getInstance().saveInstantiatedWidget(model);
	}

	/**
	 * This method deletes all InstantiatedWidgets for a given user id
	 * 
	 * @param userID
	 */
	public void deleteInstantiatedWidgets(String userID) {
		MongoDashboardRepository.getInstance().deleteAllInstantiatedWidgets(userID);
	}

	/**
	 * This method deletes for a given user a given InstantiatedWidget.
	 * 
	 * @param userID     the user id of the current user
	 * @param instanceID the instanceID of the InstantiatedWidget that needs to be
	 *                   deleted.
	 */
	public void deleteInstantiatedWidget(String userID, int instanceID) {
		MongoDashboardRepository.getInstance().deleteInstantiatedWidget(userID, instanceID);
	}

}
