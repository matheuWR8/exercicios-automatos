package com.mycompany.exerciciosautomatos;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// #########################################################
// AFD que aceita strings com exatamente 2 ocorrências de a.
// #########################################################
public class Exercicio8 {

    enum Estado {
        Q0,
        Q1,
        Q2
    }

    static Estado transicao(Estado estadoAtual, char simbolo) {

        switch (estadoAtual) {

            case Q0:
                switch (simbolo) {
                    case 'a':
                        return Estado.Q1;
                    case 'b':
                        return Estado.Q0;
                    default:
                        throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
                }

            case Q1:
                switch (simbolo) {
                    case 'a':
                        return Estado.Q2;
                    case 'b':
                        return Estado.Q1;
                    default:
                        throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
                }

            case Q2:
                switch (simbolo) {
                    case 'a':
                        throw new NoTransitionException();
                    case 'b':
                        return Estado.Q2;
                    default:
                        throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
                }


            default:
                throw new IllegalStateException("Estado inválido");
        }
    }

    // Reconhecedor do autômato
    static boolean reconhece(String cadeia) {
        Estado estado = Estado.Q0;

        try {
            for (int i = 0; i < cadeia.length(); i++) {
                estado = transicao(estado, cadeia.charAt(i));
            }
        } catch (NoTransitionException e) {
            return false;
        }

        // Estado de aceitação
        return estado == Estado.Q2;
    }

    public static void main(String[] args) {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma cadeia de Σ = {a,b}: ");
        String cadeia = scanner.nextLine().trim();

        try {
            if (reconhece(cadeia)) {
                System.out.println("Cadeia ACEITA");
            } else {
                System.out.println("Cadeia REJEITADA");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}
