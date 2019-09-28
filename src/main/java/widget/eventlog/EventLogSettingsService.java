package widget.eventlog;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import persistence.MongoDashboardRepository;

/**
 * This is the service class for the EventLogSettings. This class is a
 * singelton.
 * 
 * @author Florian Krippner
 *
 */
public class EventLogSettingsService {

	private static EventLogSettingsService instance;

	private EventLogSettingsService() {
	}

	public static EventLogSettingsService getInstance() {
		if (EventLogSettingsService.instance == null) {
			EventLogSettingsService.instance = new EventLogSettingsService();
		}
		return EventLogSettingsService.instance;
	}

	/**
	 * This function returns a EventLogSettingsModel from the database that is
	 * connected to a instanceID of a eventlog widget.
	 * 
	 * @param instanceID the instance id of a widget
	 * @return returns a EventLogSettingsModel
	 */
	public EventLogSettingsModel getSetting(int instanceID) {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "eventlogsetting");
		query.put("instanceID", instanceID);

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, this);
		List<EventLogSettingsModel> result = new ArrayList<EventLogSettingsModel>();

		queryResult.forEach(map -> {
			result.add(EventLogSettingsModel.convert(map));
		});

		if (result.isEmpty()) {
			setSetting(new EventLogSettingsModel(instanceID, 200));
			return getSetting(instanceID);
		}
		return result.get(0);

	}

	/**
	 * This function can set a EventLogSettingsModel for a eventlog widget.
	 * 
	 * @param setting a EventLogSettingsModel is required.
	 */
	public void setSetting(EventLogSettingsModel setting) {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "eventlogsetting");
		query.put("instanceID", setting.getInstanceID());
		MongoDashboardRepository.getInstance().delete(query, this);

		MongoDashboardRepository.getInstance().save(setting.convert(), this);
	}

}
