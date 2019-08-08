package widget.eventlog;

import java.util.ArrayList;
import java.util.List;
import net.explorviz.shared.landscape.model.event.Event;
import persistence.MongoDashboardRepository;

public class EventLogService {

	private static EventLogService instance;

	private EventLogService() {
	}

	public static synchronized EventLogService getInstance() {
		if (EventLogService.instance == null) {
			EventLogService.instance = new EventLogService();
		}
		return EventLogService.instance;
	}

	private List<EventLogModel> currentLogs = new ArrayList<EventLogModel>();

	public void update(long timestamp, final List<Event> list) {

		if (list.size() > 0) {
			List<EventLogModel> temp = mapEvents(timestamp, list);
			EventLogInfoModel wrapper = new EventLogInfoModel(timestamp, list.size());

			currentLogs = new ArrayList<>(temp);

			MongoDashboardRepository.getInstance().saveEventLogs(temp);
			MongoDashboardRepository.getInstance().saveEventLogInfo(wrapper);
		}

	}

	public List<EventLogModel> getCurrentModel() {
		return currentLogs;

	}

	private List<EventLogModel> mapEvents(long timestamp, final List<Event> list) {
		List<EventLogModel> result = new ArrayList<EventLogModel>();
		for (int i = 0; i < list.size(); i++) {
			result.add(new EventLogModel(timestamp, list.get(i).getTimestamp(), list.get(i).getEventType(),
					list.get(i).getEventMessage()));
		}

		return result;
	}

	public List<EventLogModel> getEventLogModels(String timestampLandscape) {
		return MongoDashboardRepository.getInstance().getEventLogs(timestampLandscape);
	}

	public List<EventLogInfoModel> getInfoModels(int entries) {
		return MongoDashboardRepository.getInstance().getEventInfos(entries);
	}

}
