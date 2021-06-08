package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car toyota = new Car("toyota", 12312);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", toyota));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("honda", 33442)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("VW", 141253264)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("scoda", 456465886)));

        List<User> users = userService.listUsers();
        printUsersList(users);

        List<User> usersCar = userService.getUserByCar(toyota);
        printUsersList(usersCar);

        context.close();
    }

    private static void printUsersList(List<User> usersCar) {
        for (User user: usersCar){
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar());
            System.out.println();
        }
    }
}
