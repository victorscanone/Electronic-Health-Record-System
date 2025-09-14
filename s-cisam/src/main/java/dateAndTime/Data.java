package dateAndTime;

import others.Printes;
import java.io.Serializable;

public class Data implements Serializable {
    int dia;
    int mes;
    int ano;


    public Data(String dia1, String mes1, String ano1) {

        boolean certo = true;
        if ((dia1 == null) || (mes1 == null) || (ano1 == null)) {
            this.ano = 0;
            this.mes = 0;
            this.dia = 0;

        } else {
            for (char a : dia1.toCharArray()) {
                if (!Character.isDigit(a)) {
                    certo = false;
                }
            }

            for (char b : mes1.toCharArray()) {
                if (!Character.isDigit(b)) {
                    certo = false;
                }
            }

            for (char c : ano1.toCharArray()) {
                if (!Character.isDigit(c)) {
                    certo = false;
                }
            }

            if (certo) {
                int dia = Integer.parseInt(dia1);
                int mes = Integer.parseInt(mes1);
                int ano = Integer.parseInt(ano1);

                if ((2025 >= ano) && (1900 <= ano)) {
                    if ((mes >= 1) && (mes <= 12)) {
                        if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && ((dia >= 1) && (dia <= 31))) {
                            this.ano = ano;
                            this.mes = mes;
                            this.dia = dia;
                            Printes.espaco();
                        } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia >= 1 && dia <= 30) {
                            this.ano = ano;
                            this.mes = mes;
                            this.dia = dia;
                            Printes.espaco();
                        } else if ((mes == 2) && dia >= 1 && dia <= 29 && ano % 400 == 0) {
                            this.ano = ano;
                            this.mes = mes;
                            this.dia = dia;
                            Printes.espaco();
                        } else if (mes == 2 && dia >= 1 && dia <= 28) {
                            this.ano = ano;
                            this.mes = mes;
                            this.dia = dia;
                            Printes.espaco();
                        } else {
                            Printes.espaco();
                            System.out.println("--> ERRO COM A DATA. DIA OU MÊS ERRADO.");
                        }

                    } else {
                        Printes.espaco();
                        System.out.println("--> ERRO COM A DATA. DIA OU MÊS ERRADO.");
                    }
                } else if (dia == 0 && mes == 0 && ano == 0) {
                    this.ano = 0;
                    this.mes = 0;
                    this.dia = 0;
                } else  {
                    Printes.espaco();
                    System.out.println("--> ERRO COM A DATA. ANO ERRADO. DIGITE UM ANO DE 1900 - 2025.");
                }
            } else {
                System.out.println("--> ERRO: CARACTERES NÃO NÚMERICOS");
            }
        }
    }

    public void atualizarData(String dia1, String mes1, String ano1) {
        int diaAntigo = this.dia;
        int mesAntigo = this.mes;
        int anoAntigo = this.ano;

        boolean certo = true;

        for (char a : dia1.toCharArray()) {
            if (!Character.isDigit(a)) {
                certo = false;
            }
        }

        for (char b : mes1.toCharArray()) {
            if (!Character.isDigit(b)) {
                certo = false;
            }
        }

        for (char c : ano1.toCharArray()) {
            if (!Character.isDigit(c)) {
                certo = false;
            }
        }


        if (certo) {
            int dia = Integer.parseInt(dia1);
            int mes = Integer.parseInt(mes1);
            int ano = Integer.parseInt(ano1);

            if ((2025 >= ano) && (1900 <= ano)) {
                if ((mes >= 1) && (mes <= 12)) {
                    if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && ((dia >= 1) && (dia <= 31))) {
                        this.ano = ano;
                        this.mes = mes;
                        this.dia = dia;
                        Printes.espaco();
                    } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia >= 1 && dia <= 30) {
                        this.ano = ano;
                        this.mes = mes;
                        this.dia = dia;
                        Printes.espaco();
                    } else if ((mes == 2) && dia >= 1 && dia <= 29 && ano % 400 == 0) {
                        this.ano = ano;
                        this.mes = mes;
                        this.dia = dia;
                        Printes.espaco();
                    } else if (mes == 2 && dia >= 1 && dia <= 28) {
                        this.ano = ano;
                        this.mes = mes;
                        this.dia = dia;
                        Printes.espaco();
                    } else {
                        Printes.espaco();
                        System.out.println("--> ERRO COM A DATA. DIA OU MÊS ERRADO.");
                    }

                } else {
                    Printes.espaco();
                    System.out.println("--> ERRO COM A DATA. DIA OU MÊS ERRADO.");
                }
            } else {
                Printes.espaco();
                System.out.println("--> ERRO COM A DATA. ANO ERRADO. DIGITE UM ANO DE 1900 - 2025.");
            }
        } else {
            System.out.println("--> ERRO: CARACTERES NÃO NÚMERICOS");
        }
    }

    public String toString() {
        String data = String.format("%2d/%2d/%4d", this.dia, this.mes, this.ano);
        return data.replace(" ", "0");
    }
}
