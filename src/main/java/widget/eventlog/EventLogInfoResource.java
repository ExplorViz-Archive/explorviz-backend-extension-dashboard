package widget.eventlog;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("widgets/eventloginfo")
@PermitAll
public class EventLogInfoResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<EventLogInfoModel> getEventLogInfoModel() {

		return EventLogService.getInstance().getInfoModels();

	}

}