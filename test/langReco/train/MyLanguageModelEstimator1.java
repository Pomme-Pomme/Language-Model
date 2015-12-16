package langReco.train;


import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import langReco.eval.Performance;
import langModel.LanguageModel;
import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;
import langModel.NgramCounts;

/**
 * @author hernandez-n
 *
 */
public class MyLanguageModelEstimator1 {

	/**
	 * Duplicate this method as many times as you want to create a language model.
	 * Give it an explicit name with respect to the created language model e.g. testCreateLmWordLangEnOrder3
	 * to create a language model with words from texts in English and order 3.
	 */
	@Test
	public void testCreateLmWordLangEnOrder3() {
		
		File dossier = new File("data/train");
		File[] list = dossier.listFiles();
		
		for(File f : list){
		    if(f.getName().startsWith("train-")){
				//Création ngram
				NgramCounts ngram = new MyNgramCounts();
				ngram.scanTextFile(f.getPath(), 3);
				//Ecriture du lm
				String nameFile = f.getName();
				nameFile = nameFile.replace("txt", "lm");
				ngram.writeNgramCountFile("lm/trigram-16000-"+nameFile);
		    }	
		}
	}

	@Test
	public void testCreateLmWordLangEnOrder2() {
		
		File dossier = new File("data/train");
		File[] list = dossier.listFiles();
		
		for(File f : list){
		    if(f.getName().startsWith("train-")){
				//Création ngram
				NgramCounts ngram = new MyNgramCounts();
				ngram.scanTextFile(f.getPath(), 2);
				//Ecriture du lm
				String nameFile = f.getName();
				nameFile = nameFile.replace("txt", "lm");
				ngram.writeNgramCountFile("lm/bigram-16000-"+nameFile);
		    }	
		}
	}
	
	@Test
	public void testCreateLmWordLangEnOrder1() {
		
		File dossier = new File("data/train");
		File[] list = dossier.listFiles();
		
		for(File f : list){
		    if(f.getName().startsWith("train-")){
				//Création ngram
				NgramCounts ngram = new MyNgramCounts();
				ngram.scanTextFile(f.getPath(), 1);
				//Ecriture du lm
				String nameFile = f.getName();
				nameFile = nameFile.replace("txt", "lm");
				ngram.writeNgramCountFile("lm/uni-16000-"+nameFile);
		    }	
		}
	}
	
	@Rule
	public TestName name = new TestName();

	
	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}

}
