package widget.ramcpu;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("widgets/ramcpusetting")
@PermitAll
public class RamCpuSettingsResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public RamCpuSettingsModel getSetting(@DefaultValue("0") @QueryParam("instanceID") int instanceID) {

		return RamCpuSettingsService.getInstance().getSetting(instanceID);

	}

	@POST
	public Response setSetting(RamCpuSettingsModel setting) {
		
		RamCpuSettingsService.getInstance().setSetting(setting);
		return Response.status(200).entity(setting).build();
	}

}
