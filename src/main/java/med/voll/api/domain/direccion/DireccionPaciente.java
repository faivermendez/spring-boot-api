package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionPaciente {

    private String urbanización;
    private String distrito;
    private String codigoPostal;
    private String complemento;
    private String numero;
    private String provincia;
    private String ciudad;

    public DireccionPaciente(DatosDireccionPaciente direccionPaciente) {
        this.urbanización=direccionPaciente.urbanización();
        this.distrito=direccionPaciente.distrito();
        this.codigoPostal=direccionPaciente.codigoPostal();
        this.complemento=direccionPaciente.complemento();
        this.numero=direccionPaciente.numero();
        this.provincia=direccionPaciente.provincia();
        this.ciudad=direccionPaciente.ciudad();
    }

    public DireccionPaciente actualizarDatos(DatosDireccionPaciente direccionPaciente) {
        this.urbanización=direccionPaciente.urbanización();
        this.distrito=direccionPaciente.distrito();
        this.codigoPostal=direccionPaciente.codigoPostal();
        this.complemento=direccionPaciente.complemento();
        this.numero=direccionPaciente.numero();
        this.provincia=direccionPaciente.provincia();
        this.ciudad=direccionPaciente.ciudad();
        return this;
    }
}
