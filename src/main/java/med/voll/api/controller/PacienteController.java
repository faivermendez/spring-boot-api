package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccionPaciente;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),paciente.getEmail(),
                paciente.getDocumentoIdentidad(),paciente.getTelefono(),
                new DatosDireccionPaciente(paciente.getDireccion().getUrbanización(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCodigoPostal(), paciente.getDireccion().getComplemento(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getProvincia(),paciente.getDireccion().getCiudad()));
        URI uri =uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(size = 10) Pageable paginacion) {
       // var page = repository.findAllByAtivoTrue(paginacion).map(DatosListaPaciente::new);
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion).map(DatosListaPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datos) {
        Paciente paciente = pacienteRepository.getReferenceById(datos.id());
        paciente.atualizarInformacion(datos);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),paciente.getEmail(),
                paciente.getDocumentoIdentidad(),paciente.getTelefono(),
                new DatosDireccionPaciente(paciente.getDireccion().getUrbanización(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCodigoPostal(), paciente.getDireccion().getComplemento(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getProvincia(),paciente.getDireccion().getCiudad())));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornaDatosPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        var datosPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),paciente.getEmail(),
                paciente.getDocumentoIdentidad(),paciente.getTelefono(),
                new DatosDireccionPaciente(paciente.getDireccion().getUrbanización(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCodigoPostal(), paciente.getDireccion().getComplemento(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getProvincia(),paciente.getDireccion().getCiudad()));

        return ResponseEntity.ok(datosPaciente);
    }

}
