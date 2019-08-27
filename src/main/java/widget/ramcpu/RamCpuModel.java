package widget.ramcpu;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

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

	public String toString() {
		return "ramcpu - timestamp: " + timestamp + " nodeName: " + nodeName + " cpuUtilization: " + cpuUtilization
				+ " freeRam: " + freeRam + " usedRam: " + usedRam;
	}

	public static RamCpuModel convert(Map<String, Object> map) {
		long timestamp = (long) map.get("timestamp");
		String nodeName = (String) map.get("nodeName");
		double cpuUtilization = (double) map.get("cpuUtilization");
		long freeRam = (long) map.get("freeRam");
		long usedRam = (long) map.get("usedRam");

		return new RamCpuModel(timestamp, nodeName, cpuUtilization, freeRam, usedRam);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "ramcpu");
		result.put("timestamp", this.timestamp);
		result.put("nodeName", this.nodeName);
		result.put("cpuUtilization", this.cpuUtilization);
		result.put("freeRam", this.freeRam);
		result.put("usedRam", this.usedRam);

		return result;
	}

}
