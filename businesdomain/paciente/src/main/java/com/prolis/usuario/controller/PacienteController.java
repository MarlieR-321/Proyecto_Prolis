package com.prolis.usuario.controller;

import com.prolis.usuario.entity.Paciente;
import com.prolis.usuario.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "{http://localhost:8080}")
@RestController
@AllArgsConstructor
@RequestMapping("api/pacientes")
public class PacienteController {

    private PacienteService px_service;

    @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente p)
    {
        Paciente px = px_service.crearPaciente(p);
        return new ResponseEntity<>(px, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        List<Paciente> pxs = px_service.obtenerPacientes();
        return new ResponseEntity<>(pxs, HttpStatus.OK);
    }

    // http://localhost:8080/api/pacientes/1
    @GetMapping("{id}")
    public ResponseEntity<Paciente> obtenerPxPorId(@PathVariable("id") Long id)
    {
        Paciente px = px_service.listarPorId(id);
        return new ResponseEntity<>(px, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Paciente> actualizarPx(@PathVariable("id") Long id, @RequestBody Paciente input)
    {
        input.setIdpaciente(id);
        Paciente px = px_service.actualizarPaciente(input);
        return new ResponseEntity<>(px, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarPx(@PathVariable("id") Long id)
    {
        px_service.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente!!", HttpStatus.OK);
    }
}
