package widget.activeclassinstances;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.explorviz.extension.dashboard.model.ClazzCommunicationModel;

@Path("widgets/activeclassinstances")
@PermitAll
public class ActiveClassInstancesResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/*
	@GET
	@Produces(MEDIA_TYPE)
	public List<ActiveClassInstancesModel> getModel() {

		return ActiveClassInstancesModel.ActiveClassInstances;

	}
	*/

	@GET
	//@Path("{amount}")
	@Produces(MEDIA_TYPE)
	public List<ActiveClassInstancesModel> getModel( @DefaultValue("0") @QueryParam("amount") int amount) {

		if(amount == 0) 
		{
			//System.out.println("standart");
			return ActiveClassInstancesModel.ActiveClassInstances;
		}
		
		List<ActiveClassInstancesModel> temp = new ArrayList<>(ActiveClassInstancesModel.ActiveClassInstances);
		
		
		//System.out.println("temp size: " + temp.size());
		
		if(amount >= temp.size()) 
		{
			amount = temp.size();
		}
		//Collections.addAll(arg0, arg1)
		
		//System.out.println("1#: " + temp.hashCode() + "      2#: " + ActiveClassInstancesModel.ActiveClassInstances.hashCode());
	
		temp = ActiveClassInstancesService.getInstance().sortByInstances(temp);
		
		return temp.subList(0, amount);

	}

}
