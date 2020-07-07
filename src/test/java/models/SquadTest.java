package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {

    private Hero testHero() {
        return new Hero("Lucifer", 100, "fire", "holywater");
    }
    private Hero testHero2(){
        return new Hero("Angel", 120, "holywater", "fire");
    }
    private Squad testSquad(Hero hero){
        return new Squad("Hellhound", "killing", hero);
    }
    private Squad testSquad2(Hero hero){
        return new Squad("heaven", "saving", hero);
    }

    @After
    public void tearDown() throws Exception {
        Squad.clearAllSquads();
    }

    @Test
    public void squadCanInstantiate_true(){
        Squad squad = testSquad(testHero2());
        assertEquals(true, squad instanceof Squad);
    }

    @Test
    public void getterMethodsCanRetrieveValues(){
        Squad squad = testSquad(testHero());
        assertEquals("Hellhound", squad.getSquadName());
        assertEquals("killing", squad.getCourse());
    }

    @Test
    public void addheroToSquad_2(){
        Squad squad1 = testSquad2(testHero());
        squad1.addHeroToSquad(testHero2());
        assertEquals(2, squad1.getAllSquads().size());
    }

    @Test
    public void addHeroToSquad_heroesNotMoreThan4(){
        Squad squad1 = testSquad(testHero());
        squad1.addHeroToSquad(testHero2());
        squad1.addHeroToSquad(new Hero("Spiderman", 23, "super reflexes", "too young"));
        squad1.addHeroToSquad(new Hero("aquaman", 32, "swimming", "outside water"));
        squad1.addHeroToSquad(new Hero("deadPool", 25, "immortal", "regrowth"));
        assertEquals(4, squad1.getAll().size());
        assertTrue(squad1.squadIsMaximum());
    }

    @Before
    public void setUp() throws Exception {
    }
}