package langReco.reco;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import langReco.eval.Performance;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MyTestLanguageRecognizer2 {

	@Test
	public void testBaselineLanguageRecognizer() {
		//On prend les fichier de tests
		String goldSentPath = "data/gold/gold-sent.txt";
		String goldLangPath = "data/gold/gold-lang.txt";

		//On créé la classe de calcul
		MyLanguageRecognizer1 baseline = new MyLanguageRecognizer1("lm/fichConfig_trigram-16000.txt");

		//On fait les calcul et on regarde les performances
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
