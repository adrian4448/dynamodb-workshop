package com.adrianm.dynamodb_crud.resource;

import com.adrianm.dynamodb_crud.model.Endereco;
import com.adrianm.dynamodb_crud.service.EnderecoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("username") String username, @RequestBody CreateEnderecoDTO enderecoDTO) {
        Endereco endereco = Endereco.fromDTO(enderecoDTO);

        endereco.setId(UUID.randomUUID().toString());
        endereco.setUsername(username);

        enderecoService.create(endereco);
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Endereco> listAll(@PathVariable("username") String username) {
        return enderecoService.findAll(username);
    }

    @GetMapping("/{username}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco findById(@PathVariable("username") String username ,@PathVariable("id") String id) {
        return enderecoService.findById(username, id);
    }

    @PutMapping("/{username}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco update(@PathVariable("username") String username ,@PathVariable("id") String id, @RequestBody CreateEnderecoDTO createEnderecoDTO) {
        Endereco endereco = Endereco.fromDTO(createEnderecoDTO);

        endereco.setId(id);
        endereco.setUsername(username);

        return enderecoService.update(endereco);
    }

    @DeleteMapping("{username}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("username") String username ,@PathVariable("id") String id) {
        enderecoService.delete(username, id);
    }
}
