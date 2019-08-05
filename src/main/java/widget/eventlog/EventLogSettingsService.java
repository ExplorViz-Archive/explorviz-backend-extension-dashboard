package widget.eventlog;

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

	/**
	 * returns a RamCpuSettingsModel for a certain instanceID. if theres no entry in
	 * the database a default setting will be saved in the database
	 * 
	 * @param instanceID
	 * @return RamCpuSettingsModel
	 */
	public EventLogSettingsModel getSetting(int instanceID) {
		EventLogSettingsModel result = MongoDashboardRepository.getInstance().getEventLogSetting(instanceID);
		if(result == null) {
			setSetting(new EventLogSettingsModel(instanceID, 200));
			result = MongoDashboardRepository.getInstance().getEventLogSetting(instanceID);
			System.out.println(result);
		}
		return result;

	}

	public void setSetting(EventLogSettingsModel setting) {
		MongoDashboardRepository.getInstance().saveEventLogSetting(setting);
	}

}
