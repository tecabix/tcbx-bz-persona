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
	
	private PersonaFisicaRepository personaFisicaRepository;
	
	private String NO_SE_ENCONTRO_PERSONA = "No se encontro a la persona";
	
	public Persona001BZ(PersonaFisicaRepository personaFisicaRepository) {
		this.personaFisicaRepository = personaFisicaRepository;
	}

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
