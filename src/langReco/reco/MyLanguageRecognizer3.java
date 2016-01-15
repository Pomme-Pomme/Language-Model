package langReco.reco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;
import langModel.NgramCounts;

public class MyLanguageRecognizer3 extends LanguageRecognizer{

	
	protected Map<String,Map<String,MyLaplaceLanguageModel>> lmMap;
	
	
	/**
	 * Constructeur dans lequel on charge un fichier de configuration
	 * @param configFile  
	 */
	public MyLanguageRecognizer3(String configFile){
		//langNgramCountMap qui permettera de passer à lmMap
		langNgramCountMap = new HashMap<String,Map<String,String>>();
		loadNgramCountPath4Lang(configFile);
		
		//lang, où on stock les differentes langues possibles
		lang = new ArrayList<String>(getLanguages());
		
		//lmMap, la variable de classe
		lmMap = new HashMap<String,Map<String,MyLaplaceLanguageModel>>();
		
		//Pour chaque modele de langage de chaque langue
		for(String l : this.getLanguages()){
			for(String idlm: this.getNgramCountNames(l)){
				
				//Création ngram
				NgramCounts ngram = new MyNgramCounts();
				ngram.readNgramCountsFile(this.getNgramCountPath(l, idlm));
				
				//Création modele de langage
				MyLaplaceLanguageModel lm = new MyLaplaceLanguageModel();
				lm.setNgramCounts(ngram);
				
				//Création de la map lmMap, qui lie les langage de model avec le nom des langues
				HashMap<String,MyLaplaceLanguageModel> mapPassage = new HashMap<String,MyLaplaceLanguageModel>();
				mapPassage.put(idlm, lm);
				lmMap.put(l, mapPassage);
			}
		}
	}
	
	
	/**
	 * Retourne la langue reconnue
	 */
	public String recognizeSentenceLanguage(String sentence, int order) {
		
		lang = new ArrayList<String>(getLanguages());
		
		//La variable pour calculer les probas
		Double proba=0.0;
		Double probaPassage=0.0;
		String language="";
		
		//Pour chaque modele de langage (de chaque langue)
		for(String l : lang){
			for(MyLaplaceLanguageModel mllm : lmMap.get(l).values()){
				
				//On additionne les probabilité pour tous les ordres possibles
				probaPassage = 0.0;
				for(int i=order; i>0; i--){
				probaPassage += mllm.getSentenceProb2(sentence, order); 
				}
				//Si la probabilite trouvée pour cette langue est la plus grande
				if(probaPassage>proba){
					//Alors on sauvegarde cette proba et la langue reconnue
					proba = probaPassage;
					language = l;
				}
				
				
				
			}
		}
			//tests
			//System.out.println(language + proba);
			//System.out.println(list.size());
			//System.out.println(lang.size());
			return language;
	}


	@Override
	public String recognizeSentenceLanguage(String sentence) {
		// TODO Auto-generated method stub
		return null;
	}


}