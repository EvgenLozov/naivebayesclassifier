import java.util.*;

/**
 * @author yevhenlozov
 */
public class NaiveBayesClassifierModel {

    private List<String> classes = new ArrayList<>();

    private Map<String, Integer> classesFrequency = new HashMap<>();
    private Map<String, Integer> wordsCountByKlass = new HashMap<>();
    private Map<String, Map<String, Integer>> wordsFrequencyByKlass = new HashMap<>();
    private Set<String> uniqueWords = new HashSet<>();

    public void train(String text, String klass){
        if (!classes.contains(klass))
            addClass(klass);

        classesFrequency.put(klass, classesFrequency.get(klass) + 1);

        List<String> words = new Util().getWords(text);
        wordsCountByKlass.put(klass, wordsCountByKlass.get(klass) + words.size());

        Map<String, Integer> wordsFrequencyMap = wordsFrequencyByKlass.get(klass);

        for (String word : words) {
            if (!wordsFrequencyMap.containsKey(word)){
                wordsFrequencyMap.put(word, 1);
            } else {
                wordsFrequencyMap.put(word, wordsFrequencyMap.get(word) + 1);
            }
        }

        uniqueWords.addAll(words);
    }

    public List<ClassificationResult> classify(String text){
        Map<String, Double> rates = new HashMap<>();

        for (String klass : classes) {
            Double rate = calcRate(text, klass);
            rates.put(klass, rate);
        }

        List<ClassificationResult> results = new ArrayList<>();

        for (String klass : rates.keySet()) {
            results.add(new ClassificationResult(klass, rates.get(klass), normalize(klass, rates)));
        }

        return results;
    }

    private Double normalize(String klass, Map<String, Double> rates) {
        Double nominator = Math.exp(rates.get(klass));

        Double denominator = 0d;

        for (Double rate : rates.values()) {
            denominator += Math.exp(rate);
        }

        return nominator/denominator;
    }

    private Double calcRate(String text, String klass) {
        Map<String, Integer> wordsFrequency = wordsFrequencyByKlass.get(klass);

        Double rate = Math.log((double)classesFrequency.get(klass)/new Util().sumOfValues(classesFrequency));

        List<String> words = new Util().getWords(text);

        for (String word : words) {
            Integer wordFrequency = !wordsFrequency.containsKey(word) ? 0 : wordsFrequency.get(word);
            Double rateForWord = Math.log((double)(wordFrequency + 1)/(uniqueWords.size() + wordsCountByKlass.get(klass)));

            rate += rateForWord;
        }

        return rate;
    }

    private void addClass(String klass) {
        classes.add(klass);
        classesFrequency.put(klass, 0);
        wordsCountByKlass.put(klass, 0);
        wordsFrequencyByKlass.put(klass, new HashMap<>());
    }
}
