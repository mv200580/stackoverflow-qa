import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by smv on 21/09/16.
 */
public class StreamCollectors {

    @AllArgsConstructor
    @ToString
    @Data
    class Entity {
        private String customer;
        private String product;
        private String productDetail;
    }


    @Test
    public void run() throws Exception {
        ArrayList<Entity> entities = new ArrayList<Entity>() {{
            add(new Entity("A", "A1", "A11"));
            add(new Entity("A", "A1", "A12"));
            add(new Entity("A", "A2", "A21"));
            add(new Entity("A", "A2", "A22"));
            add(new Entity("B", "B1", "B11"));
            add(new Entity("B", "B2", "B21"));
            add(new Entity("C", "C1", "C11"));
            add(new Entity("C", "C1", "C12"));
        }};

        String s = entities.stream().map(Entity::getCustomer).collect(Collectors.joining(", ")).toString();
        System.out.println(s);

        Map<String, Long> map = entities.stream().collect(Collectors.groupingBy(Entity::getCustomer, Collectors.counting()));
        System.out.println(map);



    }

}
