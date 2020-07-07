package models;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String name;
    private int age;
    private String superPower;
    private String weakness;
    private int id;
    private String heroesSquad;
    private static  ArrayList<Hero> listOfHeroes = new ArrayList<>();

    public Hero(String name, int age, String superPower, String weakness){
        this.name = name;
        this.age = age;
        this.superPower = superPower;
        this.weakness = weakness;
        listOfHeroes.add(this);
        this.id = listOfHeroes.size();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getSuperPower(){
        return superPower;
    }

    public void setSuperPower(String superPower){
        this.superPower = superPower;
    }

    public String getWeakness(){
        return weakness;
    }

    public void setWeakness(String weakness){
        this.weakness = weakness;
    }

    public int getId(){
        return id;
    }

    public String getHeroesSquad() {
        return heroesSquad;
    }

    public void setHeroesSquad(String heroesSquad) {
        this.heroesSquad = heroesSquad;
    }

    public static ArrayList<Hero> getAll(){
        return listOfHeroes;
    }

    public static Hero findById(int id){
        return listOfHeroes.get(id-1);
    }

    public static void clearAll(){
        listOfHeroes.clear();
    }

}

