import org.junit.Before;
import org.junit.Test;

import com.kii.ml.weka.R1Classify;
import com.kii.ml.weka.WeatherEntity;
import com.kii.ml.weka.WeatherNumEntity;

public class TestFirst {


	@Before
	public void init(){



	}


	@Test
	public void testNum(){

		R1Classify  classify=new R1Classify("ml_weather_number");


		WeatherNumEntity entity=new WeatherNumEntity();

		entity.setOutlook("sunny");
		entity.setHumidity(85);
		entity.setWindy("FALSE");
		entity.setTemperature(28.5f);


		String play=classify.doTest(entity);

		System.out.println("result:"+play);
	}


	@Test
	public void test(){

		R1Classify  classify=new R1Classify("ml_weather_nominal");


		WeatherEntity entity=new WeatherEntity();

		entity.setOutlook("overcast");
		entity.setHumidity("high");
		entity.setWindy("FALSE");
		entity.setTemperature("mild");


		String play=classify.doTest(entity);

		System.out.println("result:"+play);
	}
}
