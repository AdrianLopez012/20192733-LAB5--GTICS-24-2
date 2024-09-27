package com.example.demo.controller;

import com.example.demo.entity.Mascota;
import com.example.demo.entity.Persona;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;



    //Mosu
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public String listarMascotas(Model model) {
        List<Mascota> mascotas = mascotaRepository.findAll();
        model.addAttribute("mascotas", mascotas);
        return "mascotas";  // Vista mascotas.html
    }
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaMascota(Model model) {
        Mascota mascota = new Mascota();
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("mascota", mascota);
        model.addAttribute("personas", personas);
        return "nueva_mascota";
    }

    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute("mascota") Mascota mascota) {
        mascotaRepository.save(mascota);
        return "redirect:/mascotas";
    }

    @GetMapping("/detalles/{id}")
    public String verDetallesMascota(@PathVariable("id") Integer id, Model model) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        model.addAttribute("mascota", mascota);
        return "detalles_mascota";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMascota(@PathVariable("id") Integer id, Model model) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("mascota", mascota);
        model.addAttribute("personas", personas);
        return "editar_mascota";
    }

    @GetMapping("/borrar/{id}")
    public String borrarMascota(@PathVariable("id") Integer id) {
        mascotaRepository.deleteById(id);
        return "redirect:/mascotas";
    }
}
