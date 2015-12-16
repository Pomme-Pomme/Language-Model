package langReco.reco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;
import langModel.NgramCounts;

public class MyLanguageRecognizer1 extends LanguageRecognizer{

	
	protected Map<String,Map<String,MyLaplaceLanguageModel>> lmMap;
	
	
	/**
	 * Constructeur dans lequel on charge un fichier de configuration
	 * @param configFile  
	 */
	public MyLanguageRecognizer1(String configFile){
		//langNgramCountMap
		langNgramCountMap = new HashMap<String,Map<String,String>>();
		loadNgramCountPath4Lang(configFile);
		
		//lang
		lang = new ArrayList<String>(getLanguages());
		
		//lmMap
		lmMap = new HashMap<String,Map<String,MyLaplaceLanguageModel>>();
		
		for(String l : this.getLanguages()){
			for(String idlm: this.getNgramCountNames(l)){
				
				//Création ngram
				NgramCounts ngram = new MyNgramCounts();
				ngram.readNgramCountsFile(this.getNgramCountPath(l, idlm));
				
				//Création language modele
				MyLaplaceLanguageModel lm = new MyLaplaceLanguageModel();
				lm.setNgramCounts(ngram);
				
				//Création des maps
				HashMap<String,MyLaplaceLanguageModel> mapPassage = new HashMap<String,MyLaplaceLanguageModel>();
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
			for(MyLaplaceLanguageModel mllm : lmMap.get(l).values()){
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
