package widget.eventlog;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import persistence.MongoDashboardRepository;

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

	public EventLogSettingsModel getSetting(int instanceID) {
		// EventLogSettingsModel result =
		// MongoDashboardRepository.getInstance().getEventLogSetting(instanceID);

		Map<String, Object> query = new Hashtable<>();
		query.put("type", "eventlogsetting");
		query.put("instanceID", instanceID);

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().query(query, this);
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

	public void setSetting(EventLogSettingsModel setting) {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "eventlogsetting");
		query.put("instanceID", setting.getInstanceID());
		MongoDashboardRepository.getInstance().delete(query, this);

		MongoDashboardRepository.getInstance().save(setting.convert(), this);
	}

}
