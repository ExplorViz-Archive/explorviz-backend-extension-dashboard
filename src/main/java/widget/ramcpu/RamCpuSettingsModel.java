package widget.ramcpu;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

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

}
