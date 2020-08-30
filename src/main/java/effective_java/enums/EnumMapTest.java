package effective_java.enums;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class EnumMapTest {
    static class Plant {
        enum LifeCycle {
            ANNUAL,
            PERENNIAL,
            BIENNIAL;
        }

        final String name;
        final LifeCycle lifeCycle;

        public Plant(String name, LifeCycle lifeCycle) {
            this.name = name;
            this.lifeCycle = lifeCycle;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        List<Plant> garden = new ArrayList<>();
        garden.add(new Plant("aaa", Plant.LifeCycle.ANNUAL));
        garden.add(new Plant("bbb", Plant.LifeCycle.ANNUAL));
        garden.add(new Plant("ccc", Plant.LifeCycle.BIENNIAL));

        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);

        for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }

        for (Plant p : garden) {
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        }
    }
}
