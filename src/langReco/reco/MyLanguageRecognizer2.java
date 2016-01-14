package langReco.reco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import langModel.MyNaiveLanguageModel;
import langModel.MyNgramCounts;
import langModel.NgramCounts;

public class MyLanguageRecognizer2 extends LanguageRecognizer{

	
	protected Map<String,Map<String,MyNaiveLanguageModel>> lmMap;
	
	
	/**
	 * Constructeur dans lequel on charge un fichier de configuration
	 * @param configFile  
	 */
	public MyLanguageRecognizer2(String configFile){
		//langNgramCountMap
		langNgramCountMap = new HashMap<String,Map<String,String>>();
		loadNgramCountPath4Lang(configFile);
		
		//lang
		lang = new ArrayList<String>(getLanguages());
		
		//lmMap
		lmMap = new HashMap<String,Map<String,MyNaiveLanguageModel>>();
		
		for(String l : this.getLanguages()){
			for(String idlm: this.getNgramCountNames(l)){
				
				//Création ngram
				NgramCounts ngram = new MyNgramCounts();
				ngram.readNgramCountsFile(this.getNgramCountPath(l, idlm));
				
				//Création language modele
				MyNaiveLanguageModel lm = new MyNaiveLanguageModel();
				lm.setNgramCounts(ngram);
				
				//Création des maps
				HashMap<String,MyNaiveLanguageModel> mapPassage = new HashMap<String,MyNaiveLanguageModel>();
				mapPassage.put(idlm, lm);
				lmMap.put(l, mapPassage);
			}
		}
	}
	
	
	/**
	 * Retourne la langue reconnue
	 */
	public String recognizeSentenceLanguage(String sentence) {
		
		lang = new ArrayList<String>(getLanguages());
		
		Double proba=0.0;
		String language="";
		
		for(String l : lang){
			for(MyNaiveLanguageModel mllm : lmMap.get(l).values()){
				if(mllm.getSentenceProb(sentence)>proba){
					proba = mllm.getSentenceProb(sentence);
					language = l;
				}
			}	
		}
			System.out.println(language + proba);
			//System.out.println(list.size());
			//System.out.println(lang.size());
			return language;
	}


}
