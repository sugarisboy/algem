package dev.uniayr.algrabra;

import java.util.*;

public class Polynom {

    private double arr[];

    public Polynom(double... arr) {
        this.arr = arr;
    }

    // Map<Root, Count>
    public Map<Double, Integer> roots() {
        Map<Double, Integer> map = new HashMap<>();
        return roots(map);
    }

    private Map<Double, Integer> roots(Map<Double, Integer> map) {
        List<Double> denominators = findDenominators(arr[arr.length - 1]);
        for (double x : denominators) {
            // Является корнем
            if (cal(x) == 0) {
                Polynom rootPolynom = new Polynom(1, -x);
                Polynom result = this.divide(rootPolynom);
                if (map.containsKey(x)) {
                    map.put(x, map.get(x) + 1);
                } else {
                    map.put(x, 1);
                }
                return result.roots(map);
            }
        }
        if (this.degree() == 1) {
            double x = arr[1] / arr[0];
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }
        }
        return map;
    }

    public Polynom divide(Polynom p) {
        Polynom answer = new Polynom();
        int m = degree();
        int n = p.degree();
        if(m<n) {
            double[] q = {0};
            return answer;
        }
        double[] quotient = new double[m-n+1];
        double[] coef = new double[m+1];
        for(int k = 0; k<=m; k++) {
            coef[k] = arr[k];
        }
        double norm = 1/p.coefficient(n);
        for(int k = m-n; k>=0; k--) {
            quotient[k] = coef[n+k]*norm;
            for(int j = n+k-1; j>=k; j--) {
                coef[j] -= quotient[k]*p.coefficient(j-k);
            }
        }
        double[] remainder = new double[n];
        for(int k = 0; k<n; k++) {
            remainder[k] = coef[k];
        }
        answer = new Polynom(quotient);
        return answer;
    }

    public double cal(double x) {
        double y = 0.0;
        for (int i = 0; i < arr.length; i++) {
            y += arr[i] * Math.pow(x, arr.length - 1 - i);
        }
        return y;
    }

    public double coefficient(int n) {
        return(n<arr.length) ? arr[n] : 0;
    }

    public double[] getValues() {
        return arr.clone();
    }

    public int degree() {
        return arr.length - 1;
    }

    private List<Double> findDenominators(double x0) {
        List<Double> list = new ArrayList<>();
        for (double i = 0; i < Math.abs(x0); i++) {
            if (x0 % i == 0) {
                list.add(i);
                list.add(-i);
            }
        }
        return list;
    }
}
