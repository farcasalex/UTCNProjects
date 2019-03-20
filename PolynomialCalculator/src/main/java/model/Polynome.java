// Created by Farcas Alexandru
// Group 30221
// UTCN 2019
package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynome {
    List<Monome> polynome = new ArrayList<Monome>();

    public Polynome(List<Monome> polynome) {
        this.polynome = polynome;
        try {
            this.normalizePolynome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Polynome(String polynome) throws Exception {
        String regex = "([+-]?[\\d]+[x][\\^][\\d]+)";
        String compare = "";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(polynome);
        List<Monome> newPolynome = new ArrayList<Monome>();
        if (isPolynome(polynome)) {
            // transmitem fiecare monom constructorului din string din clasa Monome
            while (matcher.find()) {
                Monome monome = new Monome(matcher.group());
                newPolynome.add(monome);
            }
            this.polynome = newPolynome;
            this.normalizePolynome();
        }
        else
            throw new Exception("Invalid Polynomial expression!");
    }

    public List<Monome> getPolynome() {
        return polynome;
    }

    public void setPolynome(List<Monome> polynome) {
        this.polynome = polynome;
    }

    public void normalizePolynome() throws Exception {
        try{
            // sortam polinomul in ordine descrescatoare a gradelor
            Collections.sort(this.polynome);
            List<Monome> removable = new ArrayList<Monome>();
            // parcurgem polinomul pentru a uni monoamele de acelasi grad
            if (this.polynome.size() > 1){
                for (int i = 1; i < this.polynome.size(); i++){
                    if (this.polynome.get(i).getDegree() == this.polynome.get(i - 1).getDegree()){
                        this.polynome.get(i).setCoefficient(this.polynome.get(i).getCoefficient() + this.polynome.get(i - 1).getCoefficient());
                        removable.add(this.polynome.get(i - 1));
                    }
                    // daca coeficientul este null monomul trebuie inlaturat
                    if (this.polynome.get(i - 1).getCoefficient() == 0){
                        removable.add(this.polynome.get(i - 1));
                    }
                }
                if (this.polynome.get(this.polynome.size() - 1).getCoefficient() == 0){
                    removable.add(this.polynome.get(this.polynome.size() - 1));
                }
                // efectuam stergerea monoamelor nulle sau de grad egal
                for (Monome i : removable) {
                    this.polynome.remove(i);
                }
            }
            else {
                if(this.polynome.isEmpty() == false && this.polynome.get(0).coefficient == 0)
                    this.polynome.remove(0);
            }
        }
        catch (Exception e){
            throw new Exception("Normalization problem!");
        }
    }

    private boolean isPolynome(String polynome){
        String regex = "([+-]?[\\d]+[x][\\^][\\d]+)";
        String compare = "";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(polynome);

        while (matcher.find()) {
            compare = compare + matcher.group();
        }
        if(polynome.compareTo(compare) == 0)
            return true;
        return false;
    }

    private Monome hasDegree(Monome monome){
        for (Monome i : this.polynome) {
            if (i.getDegree() == monome.getDegree())
                return i;
        }
        return null;
    }

    // am optat pentru o afisare mai elaborata
    // puteam alege o varianta mai simpla insa am preferat aspectul in detrimentul eficientei
    public  String printPolynome(){
        String result = "";
        // in cazul in care avem un polinom gol returnam 0
        if (this.polynome.size() == 0)
            return "0";
        else{
            // incercam sa rotunjim valorile coeficientilor pentru a nu fi afisate cu virgula unde nu e cazul
            // primul monom il afisam fara semn pozitiv
            if (Math.round(this.polynome.get(0).getCoefficient()) == this.polynome.get(0).getCoefficient())
                result = Math.round(this.polynome.get(0).getCoefficient()) + "x^" + this.polynome.get(0).getDegree();
            else
                result = this.polynome.get(0).getCoefficient() + "x^" + this.polynome.get(0).getDegree();
            // urmatoarele monoame trebuie parcurse folosind un for clasic
            // le afisam si semnul pozitiv
            for (int i = 1; i < this.polynome.size(); i++){
                if (this.polynome.get(i).getDegree() == 0){
                    if (this.polynome.get(i).getCoefficient() == Math.round(this.polynome.get(i).getCoefficient()))
                        if (this.polynome.get(i).getCoefficient() > 0)
                            result = result + "+" + Math.round(this.polynome.get(i).getCoefficient());
                        else{
                            if (this.polynome.get(i).getCoefficient() < 0)
                                result = result + Math.round(this.polynome.get(i).getCoefficient());
                        }
                    else
                    if (this.polynome.get(i).getCoefficient() > 0)
                        result = result + "+" + this.polynome.get(i).getCoefficient();
                    else
                        result = result + this.polynome.get(i).getCoefficient();
                }
                else {
                    if (this.polynome.get(i).getCoefficient() == 0){}
                    else{
                        if (this.polynome.get(i).getCoefficient() == Math.round(this.polynome.get(i).getCoefficient()))
                            if (this.polynome.get(i).getCoefficient() > 0)
                                result = result + "+" + Math.round(this.polynome.get(i).getCoefficient()) + "x^" + this.polynome.get(i).getDegree();
                            else
                                result = result + Math.round(this.polynome.get(i).getCoefficient()) + "x^" + this.polynome.get(i).getDegree();
                        else
                        if (this.polynome.get(i).getCoefficient() > 0)
                            result = result + "+" + this.polynome.get(i).getCoefficient() + "x^" + this.polynome.get(i).getDegree();
                        else
                            result = result + this.polynome.get(i).getCoefficient() + "x^" + this.polynome.get(i).getDegree();
                    }
                }
            }
            return result;

        }
    }

    // adunarea se face monom cu monom
    public void addPolynome(Polynome polynome) throws Exception {
        Monome sameDegree;
        for (Monome i : polynome.getPolynome()) {
            if (this.hasDegree(i) != null){
                sameDegree = this.getPolynome().get(this.getPolynome().indexOf(this.hasDegree(i)));
                sameDegree.setCoefficient(sameDegree.getCoefficient() + i.getCoefficient());
            }
            else
                this.getPolynome().add(i);
        }
        this.normalizePolynome();
    }

    public void substractPolynome(Polynome polynome) throws Exception {
        Monome sameDegree;
        for (Monome i : polynome.getPolynome()) {
            if (this.hasDegree(i) != null){
                sameDegree = this.getPolynome().get(this.getPolynome().indexOf(this.hasDegree(i)));
                sameDegree.setCoefficient(sameDegree.getCoefficient() - i.getCoefficient());
            }
            else{
                i.setCoefficient(0 - i.getCoefficient());
                this.getPolynome().add(i);
            }
        }
        this.normalizePolynome();
    }

    public void multiplyPolynome(Polynome polynome){
        try{
            List<Monome> result = new ArrayList<Monome>();
            // inmultim fiecare termen cu toti ceilalti
            for (Monome i : this.getPolynome()){
                for (Monome j : polynome.getPolynome()){
                    Monome monome = new Monome(i.getDegree() + j.getDegree(),i.getCoefficient() * j.getCoefficient());
                    result.add(monome);
                }
            }
            this.setPolynome(result);
            this.normalizePolynome();
        }
        catch (Exception e){
        e.printStackTrace();
        }
    }

    public Polynome dividePolynome(Polynome polynome) throws Exception {
        if (this.polynome.isEmpty() == true){
            return this;
        }
        else
        if (this.polynome.get(0).getDegree() < polynome.polynome.get(0).getDegree())
            throw new Exception("Can't divide by a higher degree polynomial expression!");
        else {
            Polynome result = new Polynome(new ArrayList<Monome>());
            Polynome divided = new Polynome(new ArrayList<Monome>());
            Polynome aux = new Polynome(new ArrayList<Monome>());
            try{
                for (Monome i: this.polynome)
                    divided.polynome.add(new Monome(i.getDegree(),i.getCoefficient()));
                do{
                    int degree = divided.polynome.get(0).getDegree() - polynome.polynome.get(0).getDegree();
                    float coefficient = divided.polynome.get(0).getCoefficient() / polynome.polynome.get(0).getCoefficient();
                    Monome monome = new Monome(degree, coefficient);
                    result.polynome.add(monome);
                    aux.polynome.add(monome);
                    aux.multiplyPolynome(polynome);
                    divided.substractPolynome(aux);
                    aux.polynome.clear();
                    divided.normalizePolynome();
                }
                while (divided.polynome.isEmpty() == false && divided.polynome.get(0).getDegree() >= polynome.polynome.get(0).getDegree());
                // se ececuta operatia de impartire dupa modelul matematic
                // pana in momentul in care se incearca impartirea cu un monom de grad mai mare
                this.polynome = result.polynome;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                // polinomul returnat reprezinta restul impartireii
                return divided;
            }
        }
    }

    public void integratePolynome(){
        try{
            for (Monome i : this.getPolynome()) {
                i.setDegree(i.getDegree() + 1);
                i.setCoefficient(i.getCoefficient() / i.getDegree());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void derivePolynome(){
        try{
            for (Monome i : this.getPolynome()){
                i.setCoefficient(i.getCoefficient() * i.getDegree());
                i.setDegree(i.getDegree() - 1);
            }
            this.normalizePolynome();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
