package com.example.demo.controller;

import com.example.demo.entity.Viaje;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Lugar;
import com.example.demo.repository.ViajeRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private LugarRepository lugarRepository;


    @GetMapping
    public String listarViajes(Model model) {
        List<Viaje> viajes = viajeRepository.findAll();
        model.addAttribute("viajes", viajes);
        return "viajes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoViaje(Model model) {
        Viaje viaje = new Viaje();
        List<Persona> personas = personaRepository.findAll();
        List<Lugar> lugares = lugarRepository.findAll();

        model.addAttribute("viaje", viaje);
        model.addAttribute("personas", personas);
        model.addAttribute("lugares", lugares);

        return "nuevo_viaje";
    }

    @PostMapping("/guardar")
    public String guardarViaje(@ModelAttribute("viaje") Viaje viaje) {
        viajeRepository.save(viaje);
        return "redirect:/viajes";
    }

    @GetMapping("/detalles/{id}")
    public String verDetallesViaje(@PathVariable("id") Integer id, Model model) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);
        model.addAttribute("viaje", viaje);
        return "detalles_viaje";
    }

    // Editar un viaje
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarViaje(@PathVariable("id") Integer id, Model model) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);
        List<Persona> personas = personaRepository.findAll();
        List<Lugar> lugares = lugarRepository.findAll();

        model.addAttribute("viaje", viaje);
        model.addAttribute("personas", personas);
        model.addAttribute("lugares", lugares);

        return "editar_viaje";
    }
    //e
    @GetMapping("/borrar/{id}")
    public String borrarViaje(@PathVariable("id") Integer id) {
        viajeRepository.deleteById(id);
        return "redirect:/viajes";
    }
    //fano
}
