package com.mycompany.exerciciosautomatos;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// ##########################################################################
// AFD que lê um número em binário (sem sinal) e aceita se for múltiplo de 5.
// ##########################################################################
public class Exercicio10 {

    enum Estado {
        Q0,
        Q1,
        Q2,
        Q3,
        Q4
    }

    static Estado transicao(Estado estadoAtual, char simbolo) {

        switch (estadoAtual) {

            case Q0:
                switch (simbolo) {
                    case '0':
                        return Estado.Q0;
                    case '1':
                        return Estado.Q1;
                    default:
                        throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
                }

            case Q1:
                switch (simbolo) {
                    case '0':
                        return Estado.Q2;
                    case '1':
                        return Estado.Q3;
                    default:
                        throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
                }

            case Q2:
                switch (simbolo) {
                    case '0':
                        return Estado.Q4;
                    case '1':
                        return Estado.Q0;
                    default:
                        throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
                }
                
            case Q3:
                switch (simbolo) {
                    case '0':
                        return Estado.Q1;
                    case '1':
                        return Estado.Q2;
                    default:
                        throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
                }
                
            case Q4:
                switch (simbolo) {
                    case '0':
                        return Estado.Q3;
                    case '1':
                        return Estado.Q4;
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
        return estado == Estado.Q0;
    }

    public static void main(String[] args) {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma cadeia de Σ = {0,1}: ");
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
