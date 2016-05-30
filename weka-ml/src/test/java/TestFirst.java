import org.junit.Before;
import org.junit.Test;

import com.kii.ml.weka.R1Classify;
import com.kii.ml.weka.WeatherEntity;

public class TestFirst {

	private R1Classify  classify;

	@Before
	public void init(){


		classify=new R1Classify();
	}

	@Test
	public void test(){


		WeatherEntity entity=new WeatherEntity();



		classify.doTest(entity);
	}
}
