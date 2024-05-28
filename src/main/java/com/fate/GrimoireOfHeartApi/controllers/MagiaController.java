package com.fate.GrimoireOfHeartApi.controllers;

import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
import com.fate.GrimoireOfHeartApi.services.MagiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@ResponseBody
@RequestMapping("/magia")
public class MagiaController {
    @Autowired
    private MagiaService magiaService;
    @GetMapping("/getMagia/{id}")
    public Optional<Magia> getMagia(@PathVariable(value = "id") int idMagia) throws Exception {
        return magiaService.getMagia(idMagia);
    }
    @PostMapping("/salvarMagia")
    public void salvarMagia(@RequestBody Magia magia){
        magiaService.salvarMagia(magia);
    }

}
