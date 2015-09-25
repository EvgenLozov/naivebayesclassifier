import java.util.*;

/**
 * @author yevhenlozov
 */
public class Util {

    public List<String> getWords(String text) {
        String trimmed = text.trim();
        return trimmed.isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(trimmed.split("\\s+")));
    }

    public Integer sumOfValues(Map<?, Integer> map){
        Integer sum = 0;
        for (Integer value : map.values()) {
            sum += value;
        }
        return sum;
    }
}
