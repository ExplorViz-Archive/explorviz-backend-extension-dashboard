package widget.eventlog;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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

			for (EventLogModel m : temp) {
				MongoDashboardRepository.getInstance().save(m.convert(), this);
			}
			MongoDashboardRepository.getInstance().save(wrapper.convert(), this);
			
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
		/*
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "eventlog");
		query.put("timestampLandscape", timestampLandscape);

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().query(query, this);
		List<EventLogModel> result = new ArrayList<EventLogModel>();

		queryResult.forEach(map -> {
			System.out.println(EventLogModel.convert(map).toString());
			result.add(EventLogModel.convert(map));

		});
		*/
		return MongoDashboardRepository.getInstance().getEventLogs(timestampLandscape);
		
		//return result;
	}

	public List<EventLogInfoModel> getInfoModels(int entries) {
		Map<String, Object> query = new Hashtable<>();
		query.put("type", "eventloginfo");

		List<Map<String, Object>> queryResult = MongoDashboardRepository.getInstance().query(query,entries, this);
		List<EventLogInfoModel> result = new ArrayList<EventLogInfoModel>();

		queryResult.forEach(map -> {
			result.add(EventLogInfoModel.convert(map));

		});
			
		return result;
	}

}
