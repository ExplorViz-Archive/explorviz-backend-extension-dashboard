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
import net.explorviz.shared.landscape.model.event.EEventType;
import net.explorviz.shared.landscape.model.event.Event;
import net.explorviz.shared.landscape.model.helper.EProgrammingLanguage;
import net.explorviz.shared.landscape.model.helper.TypeProvider;

public class Dummy implements Runnable {

	private final LandscapeSerializationHelper serializationHelper;

	@Inject
	public Dummy(final LandscapeSerializationHelper serializationHelper) {
		this.serializationHelper = serializationHelper;
	}
	
	//example: 1566895403860-42.json
	public Landscape loadLandscapeFromFile(String filename) {
		try {
			File f = new File(filename);
			if (f.exists()) {
				InputStream is = new FileInputStream(filename);

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
		return null;
	}
/*
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

		
		  Timestamp timestamp = new Timestamp("1", 1566891095, 700); Landscape l = new
		  Landscape("1", timestamp);
		  
		  System system1 = new System("1"); system1.setName("System1");
		 
		  NodeGroup ng = new NodeGroup("1"); ng.setName("Node Group 1");
		  system1.getNodeGroups().add(ng);
		  
		  Node n = new Node("1"); n.setName("Node 1"); n.setCpuUtilization(70);
		  n.setFreeRAM(60000); n.setUsedRAM(6000);
		  
		  ng.getNodes().add(n);
		  
		  Application a = new Application("1"); a.setName("Applikationname 1");
		  
		  a.setProgrammingLanguage(EProgrammingLanguage.JAVA);
		  
		  n.getApplications().add(a);
		  
		  l.getSystems().add(system1);
		 

		return null;
		
	}
	*/

	@Override
	public void run() {
		MongoDashboardRepository.getInstance().getMongoHelper().getDatabase().drop();
		MongoDashboardRepository.getInstance().printDatabase();
		
		Landscape l1 = loadLandscapeFromFile("1566895403860-42.json");
		//Landscape l1 = loadLandscapeFromFile("landscape-1.json");
		//Landscape l2 = loadLandscapeFromFile("landscape-2.json");
		//Landscape l3 = loadLandscapeFromFile("landscape-3.json");
		
		//java.lang.System.out.println(	l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getId());
		
		Node node1 = new Node("landscape-cf6f2ce2-ead9-4842-9490-5336a1f6d071-69");
		node1.setName("192.168.48.95:8090");
		node1.setCpuUtilization(0.74701461125848376);
		node1.setFreeRAM(9293254656L);
		node1.setUsedRAM(7810473984L);
		
		l1.getSystems().get(0).getNodeGroups().get(0).getNodes().add(node1);
		
		l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(0).setProgrammingLanguage(EProgrammingLanguage.JAVA);
		l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(1).setProgrammingLanguage(EProgrammingLanguage.JAVASCRIPT);

		int counter = 0;

		while (true) {
			try {
				java.sql.Timestamp timestamp = new java.sql.Timestamp(java.lang.System.currentTimeMillis());
				l1.getTimestamp().setTimestamp(timestamp.getTime());
				
				if(l1.getEvents().size() >= 2) {
					l1.getEvents().remove(1);
				}

				switch (counter % 5) {
				case 0:
					//totalrequest widget
					l1.getTimestamp().setTotalRequests(42);
					
					//ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.5);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.7);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(8503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(8600000000L);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(8503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(8600000000L);
					
					//active class isntances widget
					//l.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(0).getApplicationCommunications().get(0).get
					
					//java.lang.System.out.println(l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).getApplications().get(0).getComponents().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).getFullQualifiedName());
					
					//DataShipper.getInstance().update(l1);
					break;
				case 1:
					//totalrequest widget
					l1.getTimestamp().setTotalRequests(120);
					
					//ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.2);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.8);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(5503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(11600000000L);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(5503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(11600000000L);
				
					
					//Exception
					Event e = new Event("landscape-cf6f2ce2-ead9-4842-9490-5336a1f6d071-69", timestamp.getTime(), EEventType.NEWNODE, "New node '192.168.48.95:8084' detected");
					l1.getEvents().remove(0);
					l1.getEvents().add(e);
					
					//DataShipper.getInstance().update(l2);
					break;
				case 2:
					//totalrequest widget
					l1.getTimestamp().setTotalRequests(62);
					
					//ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.3);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.85);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(7503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(9600000000L);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(7503728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(9600000000L);
					
					//Exception
					Event e2 = new Event("landscape-cf6f2ce2-ead9-4842-9490-5336a1f6d071-69", timestamp.getTime(), EEventType.EXCEPTION, "Evil exception");
					l1.getEvents().add(e2);
					
					//test
					
					
					//DataShipper.getInstance().update(l3);
					break;
				case 3:
					//totalrequest widget
					l1.getTimestamp().setTotalRequests(300);
					
					//ram cpu widget
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setCpuUtilization(0.25);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setCpuUtilization(0.8);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setFreeRAM(1003728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(0).setUsedRAM(16100000000L);
					
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setFreeRAM(1003728640L);
					l1.getSystems().get(0).getNodeGroups().get(0).getNodes().get(1).setUsedRAM(16100000000L);
					break;
				case 4:
					//totalrequest widget
					l1.getTimestamp().setTotalRequests(70);
					
					//ram cpu widget
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
