package widget.ramcpu;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

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

	public static RamCpuSettingsModel convert(Map<String, Object> map) {
		String nodeName = (String) map.get("nodeName");
		int instanceID = (int) map.get("instanceID");

		return new RamCpuSettingsModel(nodeName, instanceID);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "ramcpusetting");
		result.put("nodeName", this.nodeName);
		result.put("instanceID", this.instanceID);

		return result;
	}

}
