package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.MetodoPagoModel;
import compraya.api.services.MetodoPagoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/metodo-pagos")
public class MetodoPagoController {
    
    private final MetodoPagoService metodoPagoService;

    @Autowired
    public MetodoPagoController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getmetodo_pagos() {
        return metodoPagoService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createmetodo_pago(@RequestBody MetodoPagoModel metodo_pago) {
        return metodoPagoService.post(metodo_pago);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getmetodo_pagoById(@PathVariable("id") Long id) {
        return metodoPagoService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updatemetodo_pago(@RequestBody MetodoPagoModel metodo_pago, @PathVariable("id") Long id) {
        return metodoPagoService.put(metodo_pago, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletemetodo_pago(@PathVariable("id") Long id) {
        return metodoPagoService.delete(id);
    }
    
}

