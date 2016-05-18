package test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/kii/datacollect/web/servletContext.xml")
public class TestTemplate {



	@Before
	public void before(){
		System.setProperty("log4j.configurationFile","./data-submit/src/main/webapp/WEB-INF/log4j2.xml");


	}
}
