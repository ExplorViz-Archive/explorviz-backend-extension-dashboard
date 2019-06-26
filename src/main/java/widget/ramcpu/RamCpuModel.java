package widget.ramcpu;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

@Type("ramcpu")
public class RamCpuModel extends BaseModel {

	// the timestamp is used as a id
	private long timestamp;

	private String nodeName;
	
	private double cpuUtilization;
	
	private long freeRam;
	
	private long usedRam;

	public RamCpuModel() {
		// default constructor for JSON API parsing
	}


	public RamCpuModel(long timestamp, String nodeName, double cpuUtilization, long freeRam, long usedRam) {
		this.timestamp = timestamp;
		this.nodeName = nodeName;
		this.cpuUtilization = cpuUtilization;
		this.freeRam = freeRam;
		this.usedRam = usedRam;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public String getNodeName() {
		return nodeName;
	}


	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}


	public double getCpuUtilization() {
		return cpuUtilization;
	}


	public void setCpuUtilization(double cpuUtilization) {
		this.cpuUtilization = cpuUtilization;
	}


	public long getFreeRam() {
		return freeRam;
	}


	public void setFreeRam(long freeRam) {
		this.freeRam = freeRam;
	}


	public long getUsedRam() {
		return usedRam;
	}


	public void setUsedRam(long usedRam) {
		this.usedRam = usedRam;
	}
	
}

