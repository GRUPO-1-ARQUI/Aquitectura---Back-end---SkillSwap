package com.upc.innovify.controller;

import com.upc.innovify.model.Favorito;
import com.upc.innovify.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoRepository favoritoRepository;

    @PostMapping
    public Favorito create(@RequestBody Favorito favorito) {
        return favoritoRepository.save(favorito);
    }
}