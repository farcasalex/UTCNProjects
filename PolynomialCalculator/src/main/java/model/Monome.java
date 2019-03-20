// Created by Farcas Alexandru
// Group 30221
// UTCN 2019
package model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monome implements Comparable<Monome>{
    int degree;
    float coefficient;

    public Monome(int degree, float coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;
    }

    public Monome(String monome) {
        Pattern pattern = Pattern.compile("(-?\\d+)");
        Matcher matcher = pattern.matcher(monome);
        matcher.find();
        this.coefficient = Integer.parseInt(monome.substring(matcher.start(),matcher.end()));
        matcher.find();
        this.degree = Integer.parseInt(monome.substring(matcher.start(),matcher.end()));
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    // folosim pentru ordonarea descrescatoare a polinoamelor
    @Override
    public int compareTo(Monome o) {
        if (this.getDegree() == o.getDegree())
            return 0;
        else
            if (this.getDegree() < o.getDegree())
                return 1;
            else
                return -1;
    }
}
