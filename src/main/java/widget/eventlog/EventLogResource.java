package widget.eventlog;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.explorviz.shared.landscape.model.event.EEventType;


@Path("widgets/eventlogwrapper")
@PermitAll
public class EventLogResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";


	/*
	@GET
	@Produces(MEDIA_TYPE)
	public List<EventLogModel> getListModel(@DefaultValue("current") @QueryParam("action") String action) {


		if (action.equals("all")) {
			return EventLogService.getInstance().getAllModels();
		}

		if (action.equals("current")) {
			return EventLogService.getInstance().getCurrentModel();
		}

		return EventLogService.getInstance().getCurrentModel();

	}
	*/
	
	@GET
	@Produces(MEDIA_TYPE)
	public EventLogWrapperModel getListModel() {


		List<EventLogModel> test = new ArrayList<EventLogModel>();
		test.add(new EventLogModel(1002,EEventType.EXCEPTION, "hallo noob"));
		test.add(new EventLogModel(1003,EEventType.NEWAPPLICATION, "hallo bob"));

		return  new EventLogWrapperModel(0, 20, test);

	}

}
