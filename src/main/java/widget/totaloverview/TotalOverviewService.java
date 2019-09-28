package widget.totaloverview;

/**
 * This class is the service class of the TotalOverview widget. This class is a
 * singelton.
 * 
 * @author Florian Krippner
 *
 */
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

	// holds the latest TotalOverviewModel of the last landscape
	private TotalOverviewModel model;

	/**
	 * update the total overview widget with newer data
	 * 
	 * @param updatedmodel the new TotalOverviewModel that needs to be updated.
	 */
	public void update(TotalOverviewModel updatedmodel) {
		model = updatedmodel;
	}

	/**
	 * 
	 * @return returns the TotalOverviewModel of the last landscape.
	 */
	public TotalOverviewModel getTotalOverviewWidgetModel() {
		if (model == null) {
			return null;
		} else {
			return this.model;
		}
	}

}