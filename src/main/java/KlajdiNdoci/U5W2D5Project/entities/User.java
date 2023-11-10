package KlajdiNdoci.U5W2D5Project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<Device> devices;
}