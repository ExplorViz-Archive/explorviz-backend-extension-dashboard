package instantiatedwidgets;

import java.util.List;

import persistence.MongoDashboardRepository;

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

	public List<InstantiatedWidgetModel> getInstantiatedWidgets(String userID) {

		return MongoDashboardRepository.getInstance().getInstantiatedWidgets(userID);

	}

	public void setInstantiatedWidget(InstantiatedWidgetModel model) {
		MongoDashboardRepository.getInstance().saveInstantiatedWidget(model);
	}
	
	public void deleteInstantiatedWidgets(String userID) {
		MongoDashboardRepository.getInstance().deleteInstantiatedWidgets(userID);
	}

}
