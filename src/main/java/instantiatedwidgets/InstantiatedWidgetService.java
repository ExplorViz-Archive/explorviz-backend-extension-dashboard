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

	public List<InstantiatedWidgetModel> getInstantiatedWidgets(long userID) {

		return MongoDashboardRepository.getInstance().getInstantiatedWidgets(userID);

	}

	public void setInstantiatedWidget(InstantiatedWidgetModel m) {
		MongoDashboardRepository.getInstance().saveInstantiatedWidget(m);
	}
	
	public void deleteInstantiatedWidgets(Long userID) {
		MongoDashboardRepository.getInstance().deleteInstantiatedWidgets(userID);
	}

}
