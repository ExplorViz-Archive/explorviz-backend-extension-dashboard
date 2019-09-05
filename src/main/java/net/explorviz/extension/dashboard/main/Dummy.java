package net.explorviz.extension.dashboard.main;

import net.explorviz.shared.landscape.model.landscape.Landscape;
import net.explorviz.shared.landscape.model.landscape.Node;
import net.explorviz.shared.landscape.model.landscape.NodeGroup;
import net.explorviz.shared.landscape.model.store.Timestamp;
import persistence.MongoDashboardRepository;
import persistence.MongoHelper;
import net.explorviz.shared.landscape.model.landscape.System;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.servlet.ServletContainer;

import com.github.jasminb.jsonapi.ResourceConverter;

import kafka.LandscapeSerializationHelper;
import net.explorviz.shared.common.provider.GenericTypeFinder;
import net.explorviz.shared.landscape.model.application.Application;
import net.explorviz.shared.landscape.model.helper.EProgrammingLanguage;
import net.explorviz.shared.landscape.model.helper.TypeProvider;

public class Dummy implements Runnable {

	private final LandscapeSerializationHelper serializationHelper;

	@Inject
	public Dummy(final LandscapeSerializationHelper serializationHelper) {
		this.serializationHelper = serializationHelper;
	}

	public Landscape createTestLandscape() {

		try {
			File f = new File("1566895403860-42.json");
			if (f.exists()) {
				InputStream is = new FileInputStream("1566895403860-42.json");

				String jsonTxt = IOUtils.toString(is, "UTF-8");

				Landscape l = serializationHelper.deserialize(jsonTxt);
				// java.lang.System.out.println("done");
				// java.lang.System.out.println("timestamp: " +
				// l.getTimestamp().getTimestamp());

				return l;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * Timestamp timestamp = new Timestamp("1", 1566891095, 700); Landscape l = new
		 * Landscape("1", timestamp);
		 * 
		 * System system1 = new System("1"); system1.setName("System1");
		 * 
		 * NodeGroup ng = new NodeGroup("1"); ng.setName("Node Group 1");
		 * system1.getNodeGroups().add(ng);
		 * 
		 * Node n = new Node("1"); n.setName("Node 1"); n.setCpuUtilization(70);
		 * n.setFreeRAM(60000); n.setUsedRAM(6000);
		 * 
		 * ng.getNodes().add(n);
		 * 
		 * Application a = new Application("1"); a.setName("Applikationname 1");
		 * 
		 * a.setProgrammingLanguage(EProgrammingLanguage.JAVA);
		 * 
		 * n.getApplications().add(a);
		 * 
		 * l.getSystems().add(system1);
		 */

		return null;
	}

	@Override
	public void run() {
		MongoDashboardRepository.getInstance().getMongoHelper().getDatabase().drop();
		MongoDashboardRepository.getInstance().printDatabase();
		Landscape l = createTestLandscape();
		
		java.lang.System.out.println(	l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getId());
		
		Node node1 = new Node("landscape-cf6f2ce2-ead9-4842-9490-5336a1f6d071-69");
		node1.setName("192.168.48.95:8090");
		node1.setCpuUtilization(0.74701461125848376);
		node1.setFreeRAM(9293254656L);
		node1.setUsedRAM(7810473984L);
		
		l.getSystems().get(0).getNodeGroups().get(0).getNodes().add(node1);
		
		l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(0).setProgrammingLanguage(EProgrammingLanguage.JAVA);
		l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(1).setProgrammingLanguage(EProgrammingLanguage.JAVASCRIPT);

		int counter = 0;

		while (true) {
			try {
				java.sql.Timestamp timestamp = new java.sql.Timestamp(java.lang.System.currentTimeMillis());
				l.getTimestamp().setTimestamp(timestamp.getTime());

				switch (counter % 5) {
				case 0:
					//totalrequest widget
					l.getTimestamp().setTotalRequests(42);
					
					//ram cpu widget
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.5);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.7);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(8503728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(8600000000L);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(8503728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(8600000000L);
					
					//active class isntances widget
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(0).getApplicationCommunications().get(0).get
					break;
				case 1:
					//totalrequest widget
					l.getTimestamp().setTotalRequests(120);
					
					//ram cpu widget
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.2);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.8);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(5503728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(11600000000L);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(5503728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(11600000000L);
					break;
				case 2:
					//totalrequest widget
					l.getTimestamp().setTotalRequests(62);
					
					//ram cpu widget
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.3);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.85);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(7503728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(9600000000L);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(7503728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(9600000000L);
					break;
				case 3:
					//totalrequest widget
					l.getTimestamp().setTotalRequests(300);
					
					//ram cpu widget
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.25);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.8);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(1003728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(16100000000L);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(1003728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(16100000000L);
					break;
				case 4:
					//totalrequest widget
					l.getTimestamp().setTotalRequests(70);
					
					//ram cpu widget
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.4);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.6);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(3003728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(14100000000L);
					
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(3003728640L);
					l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(14100000000L);
					break;
				default:
					break;
				}

				DataShipper.getInstance().update(l);
				counter++;
				Thread.sleep(10000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
