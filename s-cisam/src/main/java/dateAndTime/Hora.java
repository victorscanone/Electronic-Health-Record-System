package dateAndTime;

import others.Printes;

public class Hora {
    int hora;
    int min;

    public Hora(String hora1, String min1) {
        // hora >= 00 && hora <=23
        // min >= 00 && min <= 59

        boolean certo = true;

        if ((hora1 == null) || (min1 == null)) {
            this.hora = 0;
            this.min = 0;

        } else {
            for (char a : hora1.toCharArray()) {
                if (!Character.isDigit(a)) {
                    certo = false;
                }
            }

            for (char b : min1.toCharArray()) {
                if (!Character.isDigit(b)) {
                    certo = false;
                }
            }

            if (certo) {
                int hora = Integer.parseInt(hora1);
                int min = Integer.parseInt(min1);

                if (hora >= 0 && hora <=23) {
                    if (min >= 0 && min <= 59) {
                        this.hora = hora;
                        this.min = min;
                    } else {
                        Printes.espaco();
                        System.out.println("--> ERRO COM O HORARIO. MINUTO ESCRITO ERRADO.");
                    }
                } else {
                    Printes.espaco();
                    System.out.println("--> ERRO COM O HORARIO. HORA ESCRITO ERRADO.");
                }

            } else {
                System.out.println("--> ERRO: CARACTERES NÃO NÚMERICOS");
            }
        }
    }

    @Override
    public String toString() {
        String rapido = String.format("%2d:%2d", this.hora, this.min);
        return rapido.replace(" ", "0");
    }
}
