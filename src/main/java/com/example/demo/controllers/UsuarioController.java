package com.example.demo.controllers;

import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return usuarioService.getUsers();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return this.usuarioService.saveUser(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerPorId(@PathVariable("id") Long id) {
        return this.usuarioService.getById(id);
    }

    // /query?prioridad=2
    @GetMapping(path = "/query")
    public ArrayList<UsuarioModel> obtenerPorPrioridad(@RequestParam("prioridad") Integer prioridad) {
        return this.usuarioService.getByPrioridad(prioridad);
    }

    @DeleteMapping(path = "{id}")
    public String borrarPorId(@PathVariable("id") Long id) {
        boolean ok = this.usuarioService.deleteUsuario(id);
        if(ok) {
            return id + "deleted";
        } else {
            return "Could not delete" + id;
        }
    }


}
