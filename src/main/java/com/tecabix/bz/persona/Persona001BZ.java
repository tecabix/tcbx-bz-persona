package com.tecabix.bz.persona;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tecabix.db.entity.Persona;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Usuario;
import com.tecabix.db.entity.UsuarioPersona;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.res.b.RSB011;
import com.tecabix.sv.rq.RQSV016;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Persona001BZ {

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
     * Constructor de la clase Persona001BZ.
     *
     * @param respository Repositorio utilizado para acceder a
     *                                los datos de personas físicas.
     */
    public Persona001BZ(final PersonaFisicaRepository respository) {
        this.personaFisicaRepository = respository;
    }

    /**
     * Obtiene la dirección asociada al identificador proporcionado.
     *
     * @param rqsv016 petición con parámetros para obtener la dirección
     * @return {@link ResponseEntity} con un objeto {@link RSB011} que contiene
     *         información de dirección.
     */
    public ResponseEntity<RSB011> obtenerDireccion(final RQSV016 rqsv016) {

        RSB011 rsb011 = rqsv016.getRsb011();
        Sesion sesion = rqsv016.getSesion();
        Usuario usuario = sesion.getUsuario();
        UsuarioPersona usuarioPersona = usuario.getUsuarioPersona();
        Persona persona = usuarioPersona.getPersona();
        Optional<PersonaFisica> personaOpt;
        personaOpt = personaFisicaRepository.findByPersona(persona.getId());

        if (personaOpt.isEmpty()) {
            rsb011.notFound(NO_SE_ENCONTRO_PERSONA);
        }

        PersonaFisica personaFisica = personaOpt.get();
        return rsb011.ok(personaFisica.getDireccion());
    }
}
