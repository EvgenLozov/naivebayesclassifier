import java.util.List;

/**
 * @author yevhenlozov
 */
public class Main {

    public static void main(String[] args) {
        NaiveBayesClassifierModel classifierModel = new NaiveBayesClassifierModel();

        classifierModel.train("sd fgjd,jf j", "good");
        classifierModel.train("sd fgjd sf", "good");
        classifierModel.train("sdsgj fgjd", "good");

        classifierModel.train("we! fgjd", "bad");
        classifierModel.train("fgjd sdfg", "bad");

        List<ClassificationResult> classificationResults = classifierModel.classify("fgjd sdfg");

        System.out.println(classificationResults.toString());
    }
}
