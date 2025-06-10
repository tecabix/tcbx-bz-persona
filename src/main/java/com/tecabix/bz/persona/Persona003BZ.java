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

	private PersonaFisicaRepository personaFisicaRepository;
	
	private String NO_SE_ENCONTRO_PERSONA = "No se encontro a la persona";
	
    public Persona003BZ(PersonaFisicaRepository personaFisicaRepository) {
		this.personaFisicaRepository = personaFisicaRepository;
	}

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
