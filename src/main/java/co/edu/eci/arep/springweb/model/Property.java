package co.edu.eci.arep.springweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive number")
    private Double price;

    @NotNull(message = "Size is required")
    @Min(value = 1, message = "Size must be at least 1 mt^2 unit")
    private Integer size;

    private String description;
}