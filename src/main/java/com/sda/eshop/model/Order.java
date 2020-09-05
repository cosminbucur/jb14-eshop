package com.sda.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Order")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(
        mappedBy = "orders",
        fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public Order() {
    }

    // need a user and a list of products
    public Order(User user, List<Product> products) {
        // TODO: finish implementation
        // calculate total price
        // get price from all products
        this.totalPrice = products.stream()
            .mapToDouble(product -> product.getPrice())
            .sum();

        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // just for example
    private Double calculateTotalPrice(List<Product> products) {
        Double result = 0.0;
        // item : collection - enhanced for
        for (Product product : products) {
            result += product.getPrice();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, user);
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", totalPrice=" + totalPrice +
            '}';
    }
}
