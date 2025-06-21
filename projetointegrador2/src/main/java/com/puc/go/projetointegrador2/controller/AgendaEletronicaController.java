package com.puc.go.projetointegrador2.controller;

import com.puc.go.projetointegrador2.contato.Contato;
import com.puc.go.projetointegrador2.contato.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("agendaEletronica")
public class AgendaEletronicaController {

    @Autowired
    private ContatoRepository contatoRepository;

    @PostMapping
    public ResponseEntity<Contato> criarContato(@RequestBody Contato contato) {
        Contato novoContato = contatoRepository.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);
    }

    @GetMapping("/contato/{name}")
    public ResponseEntity<Contato> buscarPorNome(@PathVariable String name) {
        Optional<Contato> contato = Optional.ofNullable(contatoRepository.findByName(name));

        if (contato.isPresent()) {
            return ResponseEntity.ok(contato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
