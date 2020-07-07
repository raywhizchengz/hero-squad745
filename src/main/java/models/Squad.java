package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String squadName;
    private String course;
    public static boolean hasMaxMembers = false;
    public static boolean createdHero = false;
    private int squadId;
    private static  ArrayList <Hero> listOfHeroes = new ArrayList<>();
    private static ArrayList<Squad> listOfSquads = new ArrayList<>();

    public Squad(String squadName, String course, Hero hero){
        this.squadName = squadName;
        this.course = course;
        listOfSquads.add(this);
        this.squadId = listOfSquads.size();
        heroToAdd(hero.getId());
        if(createdHero){
            hero.setHeroesSquad(squadName);
            listOfHeroes.add(hero);
            listOfSquads.add(this);
            this.squadId = listOfSquads.size();
        }else{
            System.out.println("hero is not registered");
        }
    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public String getCourse() {
        return course;
    }

    public int getSquadId(){
        return squadId;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public static ArrayList<Squad> getAllSquads(){
        return listOfSquads;
    }

    public static ArrayList<Hero> getAll(){
        return listOfHeroes;
    }

    public static Squad findBySquadId(int SquadId){
        return listOfSquads.get(SquadId-1);
    }

    public void addHeroToSquad(Hero hero){
        if(listOfHeroes.size() >= 4){
            hasMaxMembers = true;
        }else{
            listOfHeroes.add(hero);
        }
    }

    public boolean squadIsMaximum(){
        return hasMaxMembers;
    }

    private static void heroToAdd(int idOfHero){
        for(Hero hero : Hero.getAll()){
            if(hero.getId() == idOfHero){
                createdHero = true;
                break;
            }
        }
    }

    public static void clearAllSquads(){
        listOfSquads.clear();
    }

}
