package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccionPaciente(
        @NotBlank
        String urbanización,
        @NotBlank
        String distrito,
        @NotBlank
        String codigoPostal,
        @NotBlank
        String complemento,
        String numero,
        @NotBlank
        String provincia,
        @NotBlank
        String ciudad) {

}
