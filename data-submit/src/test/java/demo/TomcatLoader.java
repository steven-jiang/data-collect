package demo;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

public class TomcatLoader {


	public static void main( String[] args ) throws Exception
	{

//		System.setProperty("spring.profile","test");

//		System.setProperty("log4j.configurationFile","./data-submit/src/main/resources/log4j2.xml");
		String webappDirLocation = "./data-submit/src/main/webapp/";
		Tomcat tomcat = new Tomcat();

		tomcat.setHostname("localhost");
		tomcat.setPort(9090);

		tomcat.addWebapp("/api", new File(webappDirLocation).getAbsolutePath());

		tomcat.start();
		tomcat.getServer().await();
	}
}
