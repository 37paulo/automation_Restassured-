package com.montanha.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.montanha.pojo.Viagem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ViagemDataFactory {

    public static Viagem criarViagem() throws  IOException {

        ObjectMapper ObjectMapper  = new ObjectMapper();
        Viagem viagem = ObjectMapper.readValue(new FileInputStream("src/test/resources/requestBody/postV1Viagens.json"), Viagem.class);
        return viagem;
    }
    public static Viagem viagemValida() throws IOException {

        Viagem viagemVaslida = criarViagem();
        return viagemVaslida;
    }

    public static Viagem viagemSemDestino() throws IOException {

        Viagem viagemsemdestino = criarViagem();
        viagemsemdestino.setLocalDeDestino("");
        return viagemsemdestino;
    }
}
