package instantiatedwidgets;

import java.util.ArrayList;
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
		
		InstantiatedWidgetModel m = new InstantiatedWidgetModel(userID, "empty", 0);
		
		List<InstantiatedWidgetModel> test = new ArrayList<InstantiatedWidgetModel>();
		test.add(m);
		
		
		MongoDashboardRepository.getInstance().saveInstantiatedWidgets(userID, test);
		

		return MongoDashboardRepository.getInstance().getInstantiatedWidgets(userID) ;

	}

}
