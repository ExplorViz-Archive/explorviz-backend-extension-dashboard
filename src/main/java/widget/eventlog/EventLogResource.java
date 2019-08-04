package widget.eventlog;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/eventlog")
@PermitAll
public class EventLogResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<EventLogModel> getListModel(@DefaultValue("0") @QueryParam("timestampLandscape") String timestampLandscape) {

		System.out.println("timestampLandscape: " + timestampLandscape);
		return EventLogService.getInstance().getEventLogModels(timestampLandscape);

	}

	/*
	 * @GET
	 * 
	 * @Produces(MEDIA_TYPE) public EventLogWrapperModel getListModel() {
	 * 
	 * 
	 * List<EventLogModel> test = new ArrayList<EventLogModel>(); test.add(new
	 * EventLogModel(1002,EEventType.EXCEPTION, "hallo noob")); test.add(new
	 * EventLogModel(1003,EEventType.NEWAPPLICATION, "hallo bob"));
	 * 
	 * return new EventLogWrapperModel(0, 20, test);
	 * 
	 * }
	 */

}
