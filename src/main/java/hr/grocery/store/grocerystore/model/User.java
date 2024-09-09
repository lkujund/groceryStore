package hr.grocery.store.grocerystore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;



//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "USER_ROLE",
//            joinColumns =  @JoinColumn(name = "USER_ID", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id"))
//    private List<Role> roles;
}
