package test;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/kii/datacollect/web/servletContext.xml")
public class TestTemplateWithoutMock {

	@Before
	public void before() throws JsonProcessingException {
		System.setProperty("log4j.configurationFile",".data-submit/src/main/webapp/WEB-INF/log4j2.xml");


	}
}
