package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //this creats a bean
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init(){
        dishes.add(new Dish("1", "Pizza", "Italian", 30));
        dishes.add(new Dish("2", "Burger", "American", 20));
        dishes.add(new Dish("3", "Sarma", "Macedonian", 90));
        dishes.add(new Dish("4", "Pasta", "Italian", 35));
        dishes.add(new Dish("5", "Crepe", "French", 25));

        chefs.add(new Chef(1L, "James", "Green", "Personal chef", new ArrayList<>()));
        chefs.add(new Chef(2L, "Jane", "Doe", "Healthy food chef", new ArrayList<>()));
        chefs.add(new Chef(3L, "Oliver", "Stone", "World famous chef", new ArrayList<>()));
        chefs.add(new Chef(4L, "Maya", "Stone", "Tv show chef", new ArrayList<>()));
        chefs.add(new Chef(5L, "Harry", "Potter", "Hogwats chef", new ArrayList<>()));

    }

}
