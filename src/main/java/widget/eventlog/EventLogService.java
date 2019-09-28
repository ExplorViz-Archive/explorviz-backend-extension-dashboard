package widget.eventlog;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import net.explorviz.shared.landscape.model.event.Event;
import persistence.MongoDashboardRepository;

/**
 * This is the service class of the Eventlog Widget. This class is a singelton.
 * 
 * @author Florian Krippner
 *
 */
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

	// this holds the logs from the last landscape, so no database query is required
	// if requested.
	private List<EventLogModel> currentLogs = new ArrayList<EventLogModel>();

	/**
	 * This method updates the widgets data and saves it into a database.
	 * 
	 * @param timestamp the timestamp of the landscape
	 * @param list      a list of Events
	 */
	public void update(long timestamp, final List<Event> list) {

		if (list.size() > 0) {
			List<EventLogModel> temp = mapEvents(timestamp, list);
			EventLogInfoModel wrapper = new EventLogInfoModel(timestamp, list.size());

			currentLogs = new ArrayList<>(temp);

			for (EventLogModel m : temp) {
				MongoDashboardRepository.getInstance().save(m.convert(), this);
			}
			MongoDashboardRepository.getInstance().save(wrapper.convert(), this);

		}

	}

	/**
	 * 
	 * @return returns the list of EventLogModel of the last landscape
	 */
	public List<EventLogModel> getCurrentModel() {
		return currentLogs;

	}

	/**
	 * this private function can map a list of Events to a list of our EventLogModel
	 * 
	 * @param timestamp the timestamp of the landscape
	 * @param list      the list of Events
	 * @return returns a list of EventLogModels
	 */
	private List<EventLogModel> mapEvents(long timestamp, final List<Event> list) {
		List<EventLogModel> result = new ArrayList<EventLogModel>();
		for (int i = 0; i < list.size(); i++) {
			result.add(new EventLogModel(timestamp, list.get(i).getTimestamp(), list.get(i).getEventType(),
					list.get(i).getEventMessage()));
		}

		return result;
	}

	/**
	 * @param timestampLandscape the timestamp of the landscape
	 * @return returns a list of EventLogModels from a specific landscape
	 */
	public List<EventLogModel> getEventLogModels(String timestampLandscape) {
		return MongoDashboardRepository.getInstance().getEventLogs(timestampLandscape);
	}

	/**
	 * @param entries this parameter specify the size of the list.
	 * @return returns a list of EventLogInfoModels.
	 */
	public List<EventLogInfoModel> getInfoModels(int entries) {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "eventloginfo");

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().querySort(query, entries, this);
		List<EventLogInfoModel> result = new ArrayList<EventLogInfoModel>();

		queryResult.forEach(map -> {
			result.add(EventLogInfoModel.convert(map));

		});

		return result;
	}

}
