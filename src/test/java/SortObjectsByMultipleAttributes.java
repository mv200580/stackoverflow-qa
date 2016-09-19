import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Sorting objects by multiple attributes
 * http://stackoverflow.com/questions/39571766/sorting-objects-by-multiple-attributes
 *
 * Created by smv on 19/09/16.
 */
public class SortObjectsByMultipleAttributes {

    @AllArgsConstructor
    @Data
    @ToString
    class Obj {
        public String name;
        public int num;
    }

    @Test
    public void run() {

        ArrayList<Obj> ls = new ArrayList<>();
        ls.add(new Obj("a", 20));
        ls.add(new Obj("a", 10));
        ls.add(new Obj("c", 50));
        ls.add(new Obj("c", 20));
        ls.add(new Obj("d", 10));
        ls.add(new Obj("d", 20));

        ls.sort(Comparator.comparing(Obj::getName).thenComparing(Obj::getNum));
        for (Obj s: ls) {
            System.out.println(s);
        }



    }

}
