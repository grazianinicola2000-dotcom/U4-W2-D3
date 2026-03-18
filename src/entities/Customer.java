package entities;

import java.util.Objects;
import java.util.Random;

public class Customer {
    private final long id;
    private String name;
    private int tier;

    public Customer(String name, int tier) {
        Random random = new Random();
        this.id = random.nextInt(100000, 1000000);
        this.name = name;
        this.tier = tier;
    }

// METHODS

    public int getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && tier == customer.tier && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tier);
    }
}
