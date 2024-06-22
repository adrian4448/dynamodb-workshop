package com.adrianm.dynamodb_crud.model;

import com.adrianm.dynamodb_crud.resource.CreateEnderecoDTO;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Endereco {

    private String username;
    private String id;
    private String logradouro;
    private String bairro;
    private Long numero;
    private String cidade;
    private String estado;

    public Endereco() {

    }

    public Endereco(String logradouro, String bairro, Long numero, String cidade, String estado) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public static Endereco fromDTO(CreateEnderecoDTO dto) {
        return new Endereco(
                dto.getLogradouro(),
                dto.getBairro(),
                dto.getNumero(),
                dto.getCidade(),
                dto.getEstado()
        );
    }

    @DynamoDbAttribute("username")
    @DynamoDbPartitionKey
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDbAttribute("id")
    @DynamoDbSortKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbAttribute("logradouro")
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @DynamoDbAttribute("bairro")
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @DynamoDbAttribute("numero")
    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @DynamoDbAttribute("cidade")
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @DynamoDbAttribute("estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
