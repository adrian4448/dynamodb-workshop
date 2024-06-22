package com.adrianm.dynamodb_crud.service;

import com.adrianm.dynamodb_crud.model.Endereco;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private DynamoDbTemplate dynamoDbTemplate;

    public Endereco create(Endereco endereco) {
        return dynamoDbTemplate.save(endereco);
    }

    public List<Endereco> findAll(String username) {
        Key key = Key.builder().partitionValue(username).build();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(key);

        QueryEnhancedRequest queryEnhancedRequest = QueryEnhancedRequest
                .builder()
                .queryConditional(queryConditional)
                .build();

        var enderecoPage = dynamoDbTemplate.query(queryEnhancedRequest, Endereco.class);

        return enderecoPage
                .items()
                .stream()
                .toList();
    }

    public Endereco findById(String username, String id) {
        Key key = Key
                .builder()
                .partitionValue(username)
                .sortValue(id)
                .build();

        return dynamoDbTemplate.load(key, Endereco.class);
    }

    public Endereco update(Endereco endereco) {
        Endereco enderecoFound = findById(endereco.getUsername(), endereco.getId());

        if (enderecoFound == null) {
            return null;
        }

        return dynamoDbTemplate.update(endereco);
    }

    public void delete(String username, String id) {
        Key key = Key
                .builder()
                .partitionValue(username)
                .sortValue(id)
                .build();

        dynamoDbTemplate.delete(key, Endereco.class);
    }
}
