package widget.eventlog;


import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("widgets/eventlogsetting")
@PermitAll
public class EventLogSettingsResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public EventLogSettingsModel getSetting(@DefaultValue("0") @QueryParam("instanceID") int instanceID) {
		System.out.println("GET: " + instanceID);
		EventLogSettingsModel temp = EventLogSettingsService.getInstance().getSetting(instanceID);
		System.out.println("GET: " + instanceID);
		System.out.println(temp.toString());
		return temp;
	}

	@POST
	public Response setSetting(EventLogSettingsModel setting) {
		System.out.println("POST: " + setting.toString());
		EventLogSettingsService.getInstance().setSetting(setting);
		return Response.status(200).entity(setting).build();
	}

}

