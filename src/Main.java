import com.github.javafaker.Faker;
import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

public class Main {
    static void main(String[] args) {

        Supplier<ArrayList<Product>> productsConstructor = () -> {
            ArrayList<Product> products = new ArrayList<>();
            Random random = new Random();
            Faker faker = new Faker(Locale.ITALIAN);
            for (int i = 0; i < 20; i++) {
                products.add(new Product(faker.commerce().productName(), "books", Math.round(random.nextDouble(80, 120) * 100.0) / 100.0));
                products.add(new Product(faker.commerce().productName(), "baby", Math.round(random.nextDouble(80, 120) * 100.0) / 100.0));
                products.add(new Product(faker.commerce().productName(), "boys", Math.round(random.nextDouble(80, 120) * 100.0) / 100.0));
            }
            return products;
        };

        ArrayList<Product> productsList = productsConstructor.get();

        ArrayList<Product> products1 = newProducts("books", "boys");
        ArrayList<Product> products2 = newProducts("baby", "books");
        ArrayList<Product> products3 = newProducts("boys", "movie");
        ArrayList<Product> products4 = newProducts("sport", "baby");
        ArrayList<Product> products5 = newProducts("movie", "boh");


        Supplier<ArrayList<Customer>> customerConstructor = () -> {
            ArrayList<Customer> customers = new ArrayList<>();
            Random random = new Random();
            Faker faker = new Faker(Locale.ITALIAN);
            for (int i = 0; i < 10; i++) {
                customers.add(new Customer(faker.name().firstName(), random.nextInt(1, 6)));
            }
            return customers;
        };

        ArrayList<Customer> customers = customerConstructor.get();
//        customers.forEach(customer -> System.out.println(customer));


        // EXERCISE 1
        System.out.println("---------------ONLI BOOKS > 100$---------------");
        List<Product> productBooks = productsList.stream().filter(product -> Objects.equals(product.getCategory(), "books") && product.getPrice() > 100)
                .toList();
        productBooks.forEach(product -> System.out.println(product));


        // EXERCISE 2
        System.out.println("----------------ORDER LIST WITH PRODUCTS IN THE BABY CATEGORY------------------------");
        Order order1 = new Order(products1, newCustomer());
        Order order2 = new Order(products2, newCustomer());
        Order order3 = new Order(products3, newCustomer());
        Order order4 = new Order(products4, newCustomer());
        Order order5 = new Order(products5, newCustomer());
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);

        List<Order> ordersWithBabyCategory = orders.stream().filter(order -> order.getProducts().stream().anyMatch(product -> Objects.equals(product.getCategory(), "baby"))).toList();
        ordersWithBabyCategory.forEach(order -> System.out.println(order));


        // EXERCISE 3
        List<Product> boysFilter = productsList.stream().filter(product -> Objects.equals(product.getCategory(), "boys")).toList();
        System.out.println("-----------Prodotti categoria boys---------------");
        boysFilter.forEach(product -> System.out.println(product));
        boysFilter.forEach(product -> product.setPrice(product.getPrice() * 0.9));
        System.out.println("-------------Prodotti scontati----------");
        boysFilter.forEach(product -> System.out.println(product));

        //EXERCISE 4
        List<Order> ordersOverTier2 = orders.stream().filter(order -> order.getCustomer().getTier() > 2).toList();
        System.out.println("----------Orders by customer over tier 2---------------");
        ordersOverTier2.forEach(order -> System.out.println(order));
        List<Order> orderByDate = ordersOverTier2.stream().filter(order -> order.getOrderDate().isAfter(LocalDate.of(2026, 2, 16)) && order.getOrderDate().isBefore(LocalDate.of(2026, 3, 19))).toList();
        System.out.println("---------Orders done afeter 2026-02-16 and before 2026-02-19---------");
        orderByDate.forEach(order -> System.out.println(order));
    }

    public static ArrayList<Product> newProducts(String category, String category2) {
        ArrayList<Product> products = new ArrayList<>();
        Random random = new Random();
        Faker faker = new Faker(Locale.ITALIAN);
        for (int i = 0; i < 2; i++) {
            products.add(new Product(faker.commerce().productName(), category, Math.round(random.nextDouble(80, 120) * 100.0) / 100.0));
            products.add(new Product(faker.commerce().productName(), category2, Math.round(random.nextDouble(80, 120) * 100.0) / 100.0));
        }
        return products;
    }

    public static Customer newCustomer() {
        Random random = new Random();
        Faker faker = new Faker(Locale.ITALIAN);
        return new Customer(faker.name().firstName(), random.nextInt(1, 6));
    }
}
