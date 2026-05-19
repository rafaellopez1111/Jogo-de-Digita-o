package br.edu.fei.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Frase {
    private String frase;
    public Frase(String frase){
        this.frase = frase;
    }
    public String getFrase() throws FileNotFoundException {
        return frase;
    }
    public boolean compara(String digitado){
        return frase.equals(digitado);
    }
}
