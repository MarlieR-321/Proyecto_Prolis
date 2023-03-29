package com.prolis.usuario.service.impl;

import com.prolis.usuario.entity.Paciente;
import com.prolis.usuario.repository.PacienteRepository;
import com.prolis.usuario.service.PacienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private final PacienteRepository pacienteRepository;
    @Override
    public Paciente crearPaciente(Paciente p) {
        return pacienteRepository.save(p);
    }

    @Override
    public Paciente listarPorId(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        return optionalPaciente.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente actualizarPaciente(Paciente p) {
        Paciente existe = pacienteRepository.findById(p.getIdpaciente()).get();
        existe.setCedula(p.getCedula());
        existe.setNombres(p.getNombres());
        existe.setApellidos(p.getApellidos());
        existe.setTelefono(p.getTelefono());
        existe.setDireccion(p.getDireccion());
        return pacienteRepository.save(existe);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
