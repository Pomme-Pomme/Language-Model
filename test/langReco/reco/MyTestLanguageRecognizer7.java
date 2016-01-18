package langReco.reco;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import langReco.eval.Performance;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MyTestLanguageRecognizer7 {

	@Test
	public void testBaselineLanguageRecognizer() {
		//On prend les fichier de tests
		String goldSentPath = "data/gold/test-sent.txt";

		//On créé la classe de calcul
		MyLanguageRecognizer4 baseline = new MyLanguageRecognizer4("lm/fichConfig_unigram-16000.txt");
		MyLanguageRecognizer4 baseline2 = new MyLanguageRecognizer4("lm/fichConfig_bigram-16000.txt");

		//On fait les calcul et on regarde les performances
		String hypLangFilePath = "data/test/test-lang-hyp1.txt";
		String hypLangFilePath2 = "data/test/test-lang-hyp2.txt";
		baseline.recognizeFileLanguage(goldSentPath, hypLangFilePath);
		baseline2.recognizeFileLanguage(goldSentPath, hypLangFilePath2);
	}


	@Rule
	public TestName name = new TestName();

	
	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}
}