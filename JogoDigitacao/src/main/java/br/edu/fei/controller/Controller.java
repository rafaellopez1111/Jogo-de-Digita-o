package br.edu.fei.controller;

import br.edu.fei.model.Frase;
import br.edu.fei.view.TelaDigitacao;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private TelaDigitacao tela_view;
    private ArrayList<Frase> array_frases;
    private int indice;
    private int score;

    public Controller(TelaDigitacao tela_view) {
        this.tela_view = tela_view;
        indice = 0;
        score = 0;
        array_frases = ler_arquivo();
        mostrar_frase();
    }

    public ArrayList<Frase> ler_arquivo() {
        ArrayList<Frase> array_frases =
                new ArrayList<Frase>();
        try {
            FileReader arquivo = new FileReader("frases.txt");
            BufferedReader br = new BufferedReader(arquivo);
            String str;
            while((str = br.readLine()) != null) {
                Frase frase = new Frase(str);
                array_frases.add(frase);
            }
            br.close();
        } catch(IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Arquivo não encontrado!"
            );
        }
        return array_frases;
    }

    public void conferir() {
        String digitado = tela_view.getTextoDigitado();
        boolean correta = array_frases.get(indice).compara(digitado);
        if(correta) {
            score++;
            JOptionPane.showMessageDialog(null, "Frase correta!");
        }
        else {
            JOptionPane.showMessageDialog(
                    null,
                    "Frase errada!"
            );
        }
        indice++;
        if(indice >= array_frases.size()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Fim do jogo!\nScore: "
                            + score
            );
            System.exit(0);
        }
        else {
            mostrar_frase();
        }
    }

    public void mostrar_frase() {
        try {
            tela_view.setFrase(array_frases.get(indice).getFrase()
            );
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao mostrar frase.");
        }
        tela_view.limparCampo();
    }
}