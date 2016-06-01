import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kii.ml.weka.ClassifyService;
import com.kii.ml.weka.WeatherEntity;
import com.kii.ml.weka.WeatherNumEntity;

public class TestFirst {


	@Before
	public void init(){



	}


	@Test
	public void testNum(){

		ClassifyService classify=new ClassifyService("ml_weather_number", ClassifyService.ClassifierType.DecisionTable);

		classify.train();

		WeatherNumEntity entity=new WeatherNumEntity();

		entity.setOutlook("sunny");
		entity.setHumidity(90);
		entity.setWindy("TRUE");
		entity.setTemperature(28.5f);


		String play=classify.doTest(entity);

		assertEquals("Yes",play);

		entity.setOutlook("rainy");
		entity.setHumidity(70);
		entity.setWindy("FALSE");
		entity.setTemperature(28.0f);

		play=classify.doTest(entity);

		assertEquals("Yes",play);

	}


	@Test
	public void test(){

		ClassifyService classify=new ClassifyService("ml_weather_nominal", ClassifyService.ClassifierType.PART);

		classify.train();

		WeatherEntity entity=new WeatherEntity();

		entity.setOutlook("sunny");
		entity.setTemperature("hot");
		entity.setHumidity("high");
		entity.setWindy("FALSE");


		String play=classify.doTest(entity);

		assertEquals("No",play);

		entity.setTemperature("cool");
		entity.setHumidity("normal");

		assertEquals("Yes",classify.doTest(entity));

	}
}
