package langReco.reco;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import langReco.eval.Performance;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MyTestLanguageRecognizer1 {

	@Test
	public void testBaselineLanguageRecognizer() {
		String goldSentPath = "data/gold/gold-sent.txt";
		String goldLangPath = "data/gold/gold-lang.txt";


		MyLanguageRecognizer1 baseline = new MyLanguageRecognizer1("lm/fichConfig_bigram-100.txt");


		String hypLangFilePath = "/tmp/hyp";
		baseline.recognizeFileLanguage(goldSentPath, hypLangFilePath);
		System.out.printf("System performance = %f\n", Performance.evaluate(goldLangPath, hypLangFilePath));
	}


	@Rule
	public TestName name = new TestName();

	
	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}
}
