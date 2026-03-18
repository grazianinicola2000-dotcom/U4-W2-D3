package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Order {
    private final long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    public Order(List<Product> products, Customer customer) {
        Random random = new Random();
        this.id = random.nextInt(100000, 1000000);
        this.status = "pending";
        this.orderDate = LocalDate.now();
        this.deliveryDate = LocalDate.now().plusDays(3);
        this.products = products;
        this.customer = customer;
    }


//    METHODS

    public LocalDate getOrderDate() {
        return orderDate;
    }


    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }


    public List<Product> getProducts() {
        return products;
    }


    public Customer getCustomer() {
        return customer;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(status, order.status) && Objects.equals(orderDate, order.orderDate) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(products, order.products) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, orderDate, deliveryDate, products, customer);
    }
}
