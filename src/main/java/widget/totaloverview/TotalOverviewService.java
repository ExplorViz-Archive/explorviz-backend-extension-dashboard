package widget.totaloverview;

public class TotalOverviewService {
	
	private static TotalOverviewService instance;

	private TotalOverviewService() {
	}

	public static TotalOverviewService getInstance() {
		if (TotalOverviewService.instance == null) {
			TotalOverviewService.instance = new TotalOverviewService();
		}
		return TotalOverviewService.instance;
	}

	private TotalOverviewModel model;

	public void update(TotalOverviewModel updatedmodel) {
		//System.out.println("updated: " + updatedmodel.toString());
		model = updatedmodel;

	}

	public TotalOverviewModel getTotalOverviewWidgetModel() {
		if (model == null) {
			return null;
		} else {
			return this.model;
		}
	}

}