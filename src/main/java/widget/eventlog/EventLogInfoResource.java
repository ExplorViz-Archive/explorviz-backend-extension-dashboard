package widget.eventlog;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the EventLogInfo. The resource can be accessed
 * through http. the path is "widgets/eventloginfo". The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/eventloginfo")
@PermitAll
public class EventLogInfoResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * this method can be accessed throuh http and return a list of
	 * EventLogInfoModel as json
	 * 
	 * @param entries this parameter sets the size of the list.
	 * @return returns a list of EventLogInfoModels as a json.
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<EventLogInfoModel> getEventLogInfoModel(@DefaultValue("100") @QueryParam("entries") int entries) {

		return EventLogService.getInstance().getInfoModels(entries);

	}

}
