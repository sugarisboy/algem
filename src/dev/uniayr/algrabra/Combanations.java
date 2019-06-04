package dev.uniayr.algrabra;

import java.util.HashSet;
import java.util.Set;

public class Combanations {

    public static Set<Set<Integer>> generate(int result, int all) {
        result = all - result;
        Set<Integer> data = new HashSet<>();
        for (int i = 1; i <= all; i++) {
            data.add(i);
        }
        return generate(data, result);
    }

    private static Set<Set<Integer>> generate(Set<Integer> set, int k) {
        if (k == 0)
            return Set.of(set);

        Set<Set<Integer>> out = new HashSet<>();
        for (Integer i : set) {
            Set<Integer> local = new HashSet<>(Set.copyOf(set));
            local.remove(i);
            out.addAll(generate(local, k-1));
        }
        return out;
    }
}
