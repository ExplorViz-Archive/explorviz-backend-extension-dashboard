package widget.ramcpu;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * This class is the resource for the RamCpuSettings. The resource can be
 * accessed through http. the path is "widgets/ramcpusetting". The media type is
 * json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/ramcpusetting")
@PermitAll
public class RamCpuSettingsResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable over http.
	 * 
	 * @param instanceID the instance id of a ramcpu widget.
	 * @return it returns a RamCpuSettingsModel for a specific ramcpu widget.
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public RamCpuSettingsModel getSetting(@DefaultValue("0") @QueryParam("instanceID") int instanceID) {

		return RamCpuSettingsService.getInstance().getSetting(instanceID);

	}

	/**
	 * This method is accessable over http. it sets the settings for a ramcpu
	 * widget.
	 * 
	 * @param setting needs a RamCpuSettingsModel as parameter.
	 */
	@POST
	public Response setSetting(RamCpuSettingsModel setting) {

		RamCpuSettingsService.getInstance().setSetting(setting);
		return Response.status(200).entity(setting).build();
	}

}
