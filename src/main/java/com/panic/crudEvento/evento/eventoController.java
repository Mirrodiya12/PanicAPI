package com.panic.crudEvento.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/evento")
public class eventoController {
    private final eventoService eventoService;

    @Autowired
    public eventoController(eventoService eventoService){
        this.eventoService = eventoService;

    }

    @GetMapping
    public List<evento> getEvento(){
        return eventoService.getEvento();
    }

    @PostMapping
    public ResponseEntity<Object> regitrarEvento(@RequestBody evento evento){
        return this.eventoService.newEvento(evento);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarEvento(@RequestBody evento evento){
        return this.eventoService.newEvento(evento);
    }

    @DeleteMapping(path = "{eventoId}")
    public ResponseEntity<Object> eliminarEvento(@PathVariable("eventoId") Long id){
        return this.eventoService.deleteEvento(id);
    }

}
