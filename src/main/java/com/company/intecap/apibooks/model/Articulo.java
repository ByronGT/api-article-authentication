package com.company.intecap.apibooks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity(name = "articulo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Articulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull(message = "El nombre es requerido")
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotNull(message = "El precio es requerido")
    @NotBlank(message = "El precio es requerido")
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fabricante_id", nullable = true)

    private Fabricante fabricante;

}
