package net.explorviz.extension.dashboard.main;

import net.explorviz.shared.landscape.model.landscape.Landscape;
import net.explorviz.shared.landscape.model.landscape.Node;
import net.explorviz.shared.landscape.model.landscape.NodeGroup;
import net.explorviz.shared.landscape.model.store.Timestamp;
import net.explorviz.shared.landscape.model.landscape.System;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import com.github.jasminb.jsonapi.ResourceConverter;

import kafka.LandscapeSerializationHelper;
import net.explorviz.shared.common.provider.GenericTypeFinder;
import net.explorviz.shared.landscape.model.application.Application;
import net.explorviz.shared.landscape.model.helper.EProgrammingLanguage;
import net.explorviz.shared.landscape.model.helper.TypeProvider;

public class Debug {

	public Landscape createTestLandscape() {

		try {
			File f = new File("1566895403860-42.json");
			if (f.exists()) {
				InputStream is = new FileInputStream("1566895403860-42.json");

				String jsonTxt = IOUtils.toString(is, "UTF-8");
				
				ResourceConverter converter = new ResourceConverter();
				converter.registerType(Landscape.class);
				
				/*
				TypeProvider.getExplorVizCoreTypesAsMap().forEach((classname, classRef) -> {
					GenericTypeFinder.getTypeMap().put(classname, classRef);
				});
				*/
			
				
				//register(Landscape.class);
				LandscapeSerializationHelper serializationHelper = new LandscapeSerializationHelper(new ResourceConverter());
				
				Landscape l = serializationHelper.deserialize(jsonTxt);
				java.lang.System.out.println("done");
				java.lang.System.out.println("timestamp: " + l.getTimestamp().getTimestamp());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		Timestamp timestamp = new Timestamp("1", 1566891095, 700);
		Landscape l = new Landscape("1", timestamp);

		System system1 = new System("1");
		system1.setName("System1");

		NodeGroup ng = new NodeGroup("1");
		ng.setName("Node Group 1");
		system1.getNodeGroups().add(ng);

		Node n = new Node("1");
		n.setName("Node 1");
		n.setCpuUtilization(70);
		n.setFreeRAM(60000);
		n.setUsedRAM(6000);

		ng.getNodes().add(n);

		Application a = new Application("1");
		a.setName("Applikationname 1");

		a.setProgrammingLanguage(EProgrammingLanguage.JAVA);

		n.getApplications().add(a);

		l.getSystems().add(system1);

		return l;
	}
}
