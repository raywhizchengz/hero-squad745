import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import models.Hero;
import models.Squad;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args){
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

    get("/", (req, res) ->{
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "welcome.hbs");
    }, new HandlebarsTemplateEngine());

    get("/welcome", (req, res) ->{
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "welcome.hbs");
    }, new HandlebarsTemplateEngine());

        post("/welcome", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String userName = request.queryParams("userName");
            request.session().attribute("userName", userName);
            model.put("userName", userName);

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/index", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            int yourHeroes = Hero.getAll().size();
            int yourSquads = Squad.getAllSquads().size();

            model.put("yourHeroes", yourHeroes);
            model.put("yourSquads", yourSquads);
            model.put("userName", req.session().attribute("userName"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

    //    display create hero form
        get("/heroes/new", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //       submit new hero
        post("/heroes/new", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            req.session().attribute("name", name);
            model.put("name", name);

            int age = Integer.parseInt(req.queryParams("age"));
            req.session().attribute("age", age);
            model.put("age", age);

            String superPower = req.queryParams("superPower");
            req.session().attribute("superPower", superPower);
            model.put("superPower", superPower);

            String weakness = req.queryParams("weakness");
            req.session().attribute("weakness", weakness);
            model.put("weakness", weakness);

            Hero newHero = new Hero(name, age, superPower, weakness);
            req.session().attribute("hero", newHero);
            model.put("hero", newHero);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //  show new squad form
        get("/squads/new", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //        submit new squad
        post("/squads/new", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> allHeroes = Hero.getAll();
            List<Hero> squadMembers = new ArrayList<>();

            String squadName = req.queryParams("squadName");
            req.session().attribute("squadName", squadName);
            model.put("squadName", squadName);

            String course = req.queryParams("course");
            req.session().attribute("course", course);
            model.put("course", course);

            String heroName = req.queryParams("heroName");
            req.session().attribute("heroName",heroName);
            model.put("heroName", heroName);
            for(Hero hero : allHeroes){
                if(heroName.equalsIgnoreCase(hero.getName())){
                   squadMembers.add(hero);
                }else{
                    System.out.println("enter a valid hero");
                }
            }
            for(Hero heroToAdd : squadMembers){
                Hero hero = heroToAdd;
                Squad newSquad = new Squad(squadName, course, hero );
                req.session().attribute("squad", newSquad);
                model.put("squad", newSquad);
            }
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//    show all heroes
    get("/heroes", (req, res) ->{
        Map<String, ArrayList<Hero>> model = new HashMap<>();
        ArrayList yourHeroes = Hero.getAll();
        model.put("yourHeroes", yourHeroes);
        return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //    show all squads
            get("/squads", (req, res) ->{
            Map<String, ArrayList<Squad>> model = new HashMap<>();
            ArrayList yourSquads = Squad.getAllSquads();
            model.put("yourSquads", yourSquads);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            int selectedId = Integer.parseInt(req.queryParams(":id"));
            Squad selectedSquad = Squad.findBySquadId(selectedId);
            model.put("squad", selectedSquad);
            return new ModelAndView(model, "squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
