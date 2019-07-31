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

	public void start() {

	}

	private List<EventLogModel> currentLogs = new ArrayList<EventLogModel>();

	public void update(long timestamp, final List<Event> list) {

		List<EventLogModel> temp = mapEvents(timestamp, list);

		currentLogs = new ArrayList<>(temp);
		MongoDashboardRepository.getInstance().saveEventLogs(temp);
	}

	public List<EventLogModel> getCurrentModel() {
		return currentLogs;

	}

	public List<EventLogModel> getAllModels() {
		return MongoDashboardRepository.getInstance().getEventLogs();
	}

	private List<EventLogModel> mapEvents(long timestamp, final List<Event> list) {
		List<EventLogModel> result = new ArrayList<EventLogModel>();
		for (int i = 0; i < list.size(); i++) {
			result.add(new EventLogModel(list.get(i).getTimestamp(), list.get(i).getEventType(),
					list.get(i).getEventMessage()));
		}

		return result;
	}

}
