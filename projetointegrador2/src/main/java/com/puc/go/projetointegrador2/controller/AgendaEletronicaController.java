package com.puc.go.projetointegrador2.controller;

import com.puc.go.projetointegrador2.contato.Contato;
import com.puc.go.projetointegrador2.contato.ContatoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/contatos")    
    public List<Contato> listarTodosContatos(){
        return contatoRepository.findAll();

    @GetMapping("/contato/{nome}")
    public ResponseEntity<Contato> buscarPorNome(@PathVariable String nome) {
        Optional<Contato> contato = Optional.ofNullable(contatoRepository.findByNome(nome));

        if (contato.isPresent()) {
            return ResponseEntity.ok(contato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") Long id){
        Optional<Contato> contato = contatoRepository.findById(id);
        if(contato.isPresent()){
            contatoRepository.delete(contato.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
