import Entity.EntityA;
import Entity.EntityB;

import java.util.List;

public class Phisics {

    public static boolean Collision(EntityA entA, List<EntityB>entB) {
        for (int i = 0; i <entB.size() ; i++) {
            if (entA.getBounds().intersects(entB.get(i).getBounds())){
                return true;
            }
        }
        return false;
    }
    public static boolean Collision(EntityB entB, List<EntityA>entA) {
        for (int i = 0; i <entA.size() ; i++) {
            if (entB.getBounds().intersects(entA.get(i).getBounds())){
                return true;
            }
        }
        return false;
    }
}
