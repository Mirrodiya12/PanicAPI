package com.panic.crudEvento.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class eventoService {

    HashMap<String, Object> data;
    private final eventoRepository eventoRepository;

    @Autowired
    public eventoService(eventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }
    public List<evento> getEvento(){
        return this.eventoRepository.findAll();

    }

    public ResponseEntity<Object> newEvento(evento evento) {
        Optional<evento> existingEvento = eventoRepository.findByName(evento.getName());
        data = new HashMap<>();

        if (evento.getId() == null) {
            // Si el evento no tiene un ID, entonces lo consideramos como un nuevo evento
            if (existingEvento.isPresent()) {
                data.put("error", true);
                data.put("message", "Ya existe un evento con ese nombre");
                return new ResponseEntity<>(data, HttpStatus.CONFLICT);
            }

            data.put("message", "Se creó correctamente");
        } else {
            // Si el evento tiene un ID, entonces lo actualizamos si existe
            if (existingEvento.isPresent() && !existingEvento.get().getId().equals(evento.getId())) {
                data.put("error", true);
                data.put("message", "Ya existe un evento con ese nombre");
                return new ResponseEntity<>(data, HttpStatus.CONFLICT);
            }

            data.put("message", "Se actualizó correctamente");
        }

        eventoRepository.save(evento);
        data.put("data", evento);

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteEvento(Long id){
        data = new HashMap<>();
        boolean existe=this.eventoRepository.existsById(id);
        if(!existe){
            data.put("error", true);
            data.put("message", "No existe un evento con ese id");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
        eventoRepository.deleteById(id);
        data.put("message", "Evento eliminado");
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

}
