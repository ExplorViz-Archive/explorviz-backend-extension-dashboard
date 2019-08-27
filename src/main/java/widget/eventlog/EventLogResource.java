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
	public List<EventLogModel> getListModel(
			@DefaultValue("0") @QueryParam("timestampLandscape") String timestampLandscape) {
		System.out.println("ressource");
		return EventLogService.getInstance().getEventLogModels(timestampLandscape);

	}
}
