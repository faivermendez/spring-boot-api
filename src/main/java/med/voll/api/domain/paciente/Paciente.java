package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.DireccionPaciente;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")


public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    private Boolean activo;

    @Embedded
    private DireccionPaciente direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.documentoIdentidad = datosRegistroPaciente.documentoIdentidad();
        this.telefono = datosRegistroPaciente.telefono();
        this.direccion = new DireccionPaciente(datosRegistroPaciente.direccion());
    }

    public void atualizarInformacion(DatosActualizarPaciente datosActualizarPaciente) {
        if (datosActualizarPaciente.nombre() != null)
            this.nombre = datosActualizarPaciente.nombre();

        if (datosActualizarPaciente.documentoIdentidad() != null)
            this.telefono = datosActualizarPaciente.documentoIdentidad();

        if (datosActualizarPaciente.direccion() != null)
            this.direccion = direccion.actualizarDatos(datosActualizarPaciente.direccion());
    }

    public void desactivarPaciente() {

        this.activo = false;
    }

}

