package hr.grocery.store.grocerystore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Table(name = "LOG")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    @Column(name = "LOGIN_TS")
    private Instant loginTs;
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_EVENT")
    private UserEvent userEvent;
}