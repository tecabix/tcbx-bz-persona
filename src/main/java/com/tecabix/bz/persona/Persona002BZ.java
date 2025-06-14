package com.tecabix.bz.persona;

import org.springframework.http.ResponseEntity;

import com.tecabix.db.entity.Sesion;
import com.tecabix.res.b.RSB013;
import com.tecabix.sv.rq.RQSV017;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Persona002BZ {

    /**
     * Obtiene el número de teléfono de la persona.
     *
     * @param rqsv017 petición con parámetros para obtener el teléfono
     * @return ResponseEntity con RSB013 que contiene el teléfono
     */
    public ResponseEntity<RSB013> obtenerTelefono(final RQSV017 rqsv017) {
        RSB013 rsb013 = rqsv017.getRsb013();
        Sesion sesion = rqsv017.getSesion();
        return rsb013.ok(sesion.getUsuario());
    }
}
