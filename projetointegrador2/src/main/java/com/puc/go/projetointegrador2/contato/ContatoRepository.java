package com.puc.go.projetointegrador2.contato;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato,Long> {

 //Posso estar errado nessa parte de baixo, mas vai ser o metódo que estou usando para buscar o nome
    Contato findByNome (String nome);
}
