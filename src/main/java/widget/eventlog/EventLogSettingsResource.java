package widget.eventlog;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * This class is the resource for the EventLogSettings. The resource can be
 * accessed through http. the path is "widgets/eventlogsetting". The media type
 * is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/eventlogsetting")
@PermitAll
public class EventLogSettingsResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This class is accessable over http and returns a EventLogSettingsModel for a
	 * given widget instance.
	 * 
	 * @param instanceID the instanc ide of the eventlog widget
	 * @return returns a EventLogSettingsModel
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public EventLogSettingsModel getSetting(@DefaultValue("0") @QueryParam("instanceID") int instanceID) {
		EventLogSettingsModel temp = EventLogSettingsService.getInstance().getSetting(instanceID);
		return temp;
	}

	/**
	 * This method is accessable over http and can set a EventLogSettingsModel for a
	 * eventlog widget.
	 * 
	 * @param setting require a EventLogSettingsModel
	 */
	@POST
	public Response setSetting(EventLogSettingsModel setting) {
		EventLogSettingsService.getInstance().setSetting(setting);
		return Response.status(200).entity(setting).build();
	}

}
