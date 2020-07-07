package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {
    public Hero myHero(){
        return new Hero("Dracula",70,"speed","sunlight");
    }

    @After
    public void tearDown() throws Exception {
        Hero.clearAll();
    }

    @Test
    public void newHeroGetsCorrectlyInstantiated_true() {
        Hero hero = myHero();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void getName_canObtainValueOfName_String(){
        Hero hero = myHero();
        assertEquals("Dracula", hero.getName());
        assertEquals(70, hero.getAge());
        assertEquals("speed", hero.getSuperPower());
        assertEquals("sunlight", hero.getWeakness());
    }

    @Test
    public void returnsAllHeroes_2(){
        Hero hero = myHero();
        Hero mySecondHero = new Hero("Ragnar", 40, "sword","water");
        assertEquals(2, Hero.getAll().size());
    }

    @Test
    public void allHeroesAreAssignedAnId_2(){
        Hero hero = myHero();
        Hero mySecondHero = new Hero("Ragnar", 40, "sword","water");
        assertEquals(2, mySecondHero.getId());
    }

    @Test
    public void allSettersCanResetValues(){
        Hero hero = myHero();
        hero.setName("Ragnar");
        hero.setAge(40);
        hero.setSuperPower("sword");
        hero.setWeakness("water");
        assertEquals("Ragnar", hero.getName());
        assertEquals(40, hero.getAge());
        assertEquals("sword", hero.getSuperPower());
        assertEquals("water", hero.getWeakness());
    }

    @Test
    public void canFindPostById_testHero(){
        Hero testHero = myHero();
        Hero mySecondHero = new Hero("Ragnar", 40, "sword","water");
        assertEquals(testHero, Hero.findById(1));
    }

    @Before
    public void setUp() throws Exception {
    }


}