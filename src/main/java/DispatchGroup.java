import java.util.ArrayList;
import java.util.List;

public class DispatchGroup {
    private static final int nbMinGroup = 3;

    public static List<List<String>> dispatchGroup(List<String> list, int size){
        List<List<String>> groups = new ArrayList<>();
        int counter = 0;
        for(String s : list){
            if((counter++ % size) == 0)
                groups.add(new ArrayList<>());
        }

        return null;
    }
}
