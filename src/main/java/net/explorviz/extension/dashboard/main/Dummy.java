package net.explorviz.extension.dashboard.main;

import net.explorviz.shared.landscape.model.landscape.Landscape;
import net.explorviz.shared.landscape.model.landscape.Node;
import persistence.MongoDashboardRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import kafka.LandscapeSerializationHelper;
import net.explorviz.shared.landscape.model.event.EEventType;
import net.explorviz.shared.landscape.model.event.Event;
import net.explorviz.shared.landscape.model.helper.EProgrammingLanguage;

/**
 * This class is for managing the dummy modus. its a runnable class and need a
 * specific landscape inside the root folder to load it.
 * 
 * @author Florian Krippner
 *
 */
public class Dummy implements Runnable {

	private final LandscapeSerializationHelper serializationHelper;

	@Inject
	public Dummy(final LandscapeSerializationHelper serializationHelper) {
		this.serializationHelper = serializationHelper;
	}

	/**
	 * This method loads a Landscape from file and return it example:
	 * 1566895403860-42.json
	 * 
	 * @param filename
	 * @return returns a Landscape
	 */
	public Landscape loadLandscapeFromFile(String filename) {
		try {
			File f = new File(filename);
			if (f.exists()) {
				InputStream is = new FileInputStream(filename);
				String jsonTxt = IOUtils.toString(is, "UTF-8");
				Landscape l = serializationHelper.deserialize(jsonTxt);
				return l;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method update the Datashipper ever 10 seconds with dummy data
	 */
	@Override
	public void run() {
		MongoDashboardRepository.getInstance().getMongoHelper().getDatabase().drop();
		MongoDashboardRepository.getInstance().printDatabase();

		Landscape l1 = loadLandscapeFromFile("1566895403860-42.json");

		Node node1 = new Node("landscape-cf6f2ce2-ead9-4842-9490-5336a1f6d071-69");
		node1.setName("192.168.48.95:8090");
		node1.setCpuUtilization(0.74701461125848376);
		node1.setFreeRAM(9293254656L);
		node1.setUsedRAM(7810473984L);

		l1.getSystems().get(0).getNodeGroups().get(0).getNodes().add(node1);

		l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(0)
				.setProgrammingLanguage(EProgrammingLanguage.JAVA);
		l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(1)
				.setProgrammingLanguage(EProgrammingLanguage.JAVASCRIPT);

		int counter = 0;

		while (true) {
			try {
				java.sql.Timestamp timestamp = new java.sql.Timestamp(java.lang.System.currentTimeMillis());
				l1.getTimestamp().setTimestamp(timestamp.getTime());

				if (l1.getEvents().size() >= 2) {
					l1.getEvents().remove(1);
				}

				switch (counter % 5) {
				case 0:
					// totalrequest widget
					l1.getTimestamp().setTotalRequests(42);

					// ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.5);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.7);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(8503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(8600000000L);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(8503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(8600000000L);

					break;
				case 1:
					// totalrequest widget
					l1.getTimestamp().setTotalRequests(120);

					// ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.2);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.8);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(5503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(11600000000L);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(5503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(11600000000L);

					// Exception
					Event e = new Event("landscape-cf6f2ce2-ead9-4842-9490-5336a1f6d071-69", timestamp.getTime(),
							EEventType.NEWNODE, "New node '192.168.48.95:8084' detected");
					l1.getEvents().remove(0);
					l1.getEvents().add(e);

					// DataShipper.getInstance().update(l2);
					break;
				case 2:
					// totalrequest widget
					l1.getTimestamp().setTotalRequests(62);

					// ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.3);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.85);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(7503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(9600000000L);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(7503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(9600000000L);

					// Exception
					Event e2 = new Event("landscape-cf6f2ce2-ead9-4842-9490-5336a1f6d071-69", timestamp.getTime(),
							EEventType.EXCEPTION, "Evil exception");
					l1.getEvents().add(e2);
					break;
				case 3:
					// totalrequest widget
					l1.getTimestamp().setTotalRequests(300);

					// ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.25);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.8);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(1003728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(16100000000L);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(1003728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(16100000000L);
					break;
				case 4:
					// totalrequest widget
					l1.getTimestamp().setTotalRequests(70);

					// ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.4);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.6);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(3003728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(14100000000L);

					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(3003728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(14100000000L);
					break;
				default:
					break;
				}

				DataShipper.getInstance().update(l1);
				counter++;
				Thread.sleep(10000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
