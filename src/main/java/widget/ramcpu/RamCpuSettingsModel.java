package widget.ramcpu;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This is the model for the RamCpuSettings.</br>
 * properties:</br>
 * {@link #nodeName}</br>
 * {@link #instanceID}
 * 
 * @author Florian Krippner
 *
 */
@Type("ramcpusetting")
public class RamCpuSettingsModel extends BaseModel {

	private String nodeName;
	private int instanceID;

	public RamCpuSettingsModel() {
		// default constructor for JSON API parsing
	}

	public RamCpuSettingsModel(String nodeName, int instanceID) {
		super();
		this.nodeName = nodeName;
		this.instanceID = instanceID;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(int instanceID) {
		this.instanceID = instanceID;
	}

	public String toString() {
		return "ramcpusetting - nodeName: " + nodeName + " instanceID: " + instanceID;
	}

	/**
	 * This method convert a Map of a (string,object) pair with the models
	 * properties as keys and the right objects as values to a model.
	 * 
	 * @param map a Map of (String, Object) pairs, that should have the properties
	 *            of this model included.
	 * @return return a RamCpuSettingsModel
	 */
	public static RamCpuSettingsModel convert(Map<String, Object> map) {
		String nodeName = (String) map.get("nodeName");
		int instanceID = (int) map.get("instanceID");

		return new RamCpuSettingsModel(nodeName, instanceID);
	}

	/**
	 * This method convert this model into a Map of (String, Object)
	 * reprensentation. The key/string is a propertiename of this model and the
	 * value/Object is the content.
	 * 
	 * @return returns a Map of (String,Object) pairs, which represent this model.
	 */
	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "ramcpusetting");
		result.put("nodeName", this.nodeName);
		result.put("instanceID", this.instanceID);

		return result;
	}

}
