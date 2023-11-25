package com.company.intecap.apibooks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "fabricante")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Fabricante implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre es requerido")
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "fabricante"
    )
    @JsonIgnore
    private Set<Articulo> articulos;
}
