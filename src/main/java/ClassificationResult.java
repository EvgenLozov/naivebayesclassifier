/**
 * @author yevhenlozov
 */
public class ClassificationResult {
    private String klass;
    private Double rate;
    private Double probability;

    public ClassificationResult(String klass, Double rate, Double probability) {
        this.klass = klass;
        this.rate = rate;
        this.probability = probability;
    }

    public String getKlass() {
        return klass;
    }

    public Double getRate() {
        return rate;
    }

    public Double getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return "Name of class: " + klass + ", probability - " + probability;
    }
}
