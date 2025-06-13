package com.tecabix.bz.persona;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tecabix.db.entity.Persona;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Usuario;
import com.tecabix.db.entity.UsuarioPersona;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.res.b.RSB012;
import com.tecabix.sv.rq.RQSV018;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Persona003BZ {

    /**
     * Repositorio para acceder a la entidad PersonaFisica.
     */
    private final PersonaFisicaRepository personaFisicaRepository;

    /**
     * Persona fisica no encontrada.
     */
    private static final String NO_SE_ENCONTRO_PERSONA;

    static {

        NO_SE_ENCONTRO_PERSONA = "No se encontro a la persona";
    }

    /**
     * Constructor de la clase Persona003BZ.
     *
     * @param repository Repositorio utilizado para acceder a los datos de
     *                   personas físicas.
     */
    public Persona003BZ(final PersonaFisicaRepository repository) {
        this.personaFisicaRepository = repository;
    }

    /**
     * Obtiene el nombre de la persona física asociada al identificador
     * proporcionado.
     *
     * @param rqsv018 petición con parámetros para obtener el nombre
     * @return {@link ResponseEntity} con un objeto {@link RSB012} que contiene
     *         el nombre de la persona o entidad física.
     */
    public ResponseEntity<RSB012> obtenerFisicaNombre(final RQSV018 rqsv018) {
        RSB012 rsb012 = rqsv018.getRsb012();
        Sesion sesion = rqsv018.getSesion();

        Usuario usuario = sesion.getUsuario();
        UsuarioPersona usuarioPersona = usuario.getUsuarioPersona();
        Persona persona = usuarioPersona.getPersona();
        Optional<PersonaFisica> personaOpt;
        personaOpt = personaFisicaRepository.findByPersona(persona.getId());

        if (personaOpt.isEmpty()) {
            rsb012.notFound(NO_SE_ENCONTRO_PERSONA);
        }

        PersonaFisica personaFisica = personaOpt.get();
        return rsb012.ok(personaFisica);
    }
}
