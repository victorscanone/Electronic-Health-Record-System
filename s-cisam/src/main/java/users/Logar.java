package users;

import others.Printes;
import database.ConnectDB;

import java.sql.ResultSet;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Logar {

    public static String logar(String cpf, String senha) {
        String retorno = "";
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();



        String sqlSearch = "SELECT CPF, Senha, TipoDaConta FROM UserCISAM WHERE CPF = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {

            stmt.setString(1, cpf); // Aqui a query fica formada para a busca

            ResultSet rs = stmt.executeQuery(); // Aqui a query é executada

            if (rs.next()) {
                if (Objects.equals(senha, rs.getString("Senha")) && Objects.equals("p", rs.getString("TipoDaConta"))) {
                    retorno = "p";
                } else if (Objects.equals(senha, rs.getString("Senha")) && Objects.equals("m", rs.getString("TipoDaConta"))) {
                    retorno = "m";
                } else if (Objects.equals(senha, rs.getString("Senha")) && Objects.equals("g", rs.getString("TipoDaConta"))) {
                    retorno = "g";
                } else {
                    retorno = "";
                }
            } else {
                retorno = "";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
        return retorno;
    }

    public static String criarConta(String nome, String cpf, String email, String senha1, String senha2) {
        if ((!Objects.equals(nome, "")) && (!Objects.equals(cpf, "")) && (!Objects.equals(cpf, "00000000000")) && (!Objects.equals(email, "")) && (!Objects.equals(senha1, "")) && (!Objects.equals(senha2, "")) && (Objects.equals(senha1, senha2))) {
            if (!duplicidadeCPF(cpf)) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "INSERT INTO UserCISAM (IdUser, TipoDaConta, CPF, Email, Senha, Nome) VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, gerarIdNovo());
                    stmt.setString(2, "p");
                    stmt.setString(3, cpf);
                    stmt.setString(4, email);
                    stmt.setString(5, senha1);
                    stmt.setString(6, nome);

                    stmt.executeUpdate();


                    Printes.espaco();
                    System.out.println("--> USUÁRIO INSERIDO COM SUCESSO.");
                    return cpf;
                } catch (SQLException e) {
                    Printes.espaco();
                    System.out.println("--> ERRO AO INSERIR NOVO USUÁRIO.");
                    e.printStackTrace();
                } finally {
                    db.closeConnection();
                }

            } else {
                Printes.espaco();
                System.out.println("--> ESSE CPF JÁ FOI USADO.");
                return "";
            }
        } else {
            Printes.espaco();
            System.out.println("--> DADOS ERRADOS OU NÃO INSERIDOS. TENTE NOVAMENTE.");
            return "";
        }
        return "";
    }

    public static String gerarIdNovo() {

        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String retorno = "";

        String sqlSearch = "SELECT IdUser FROM UserCISAM WHERE IdUser = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {


            for (int i = 1; i <= 99; i++) {

                String vez = String.format("%6s", Integer.toString(i));
                String idDaVez =  vez.replace(" ", "0");

                stmt.setString(1, idDaVez); // Aqui a query fica formada para a busca

                ResultSet rs = stmt.executeQuery(); // Aqui a query é executada
                if (!rs.next()) {
                    retorno = idDaVez;
                    break;
                }
                retorno = "Sem ID";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
        return retorno;
    }

    public static boolean duplicidadeCPF(String cpf) {
        boolean retorno = false;

        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String sqlSearch = "SELECT CPF FROM UserCISAM WHERE CPF = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {



            stmt.setString(1, cpf); // Aqui a query fica formada para a busca

            ResultSet rs = stmt.executeQuery(); // Aqui a query é executada

            if (rs.next()) {
                retorno = true;
            } else {
                retorno = false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
        return retorno;
    }



    public static String checarNome(String nome, String atual) {
        if (Objects.equals(nome, "1")) {
            Printes.espaco();
            return atual;
        }
        if (nome != null){
            for (char c : nome.toCharArray()) {
                if (Character.isDigit(c)) {
                    Printes.espaco();
                    System.out.println("--> OPÇÃO INVALIDA. É PRECISO ESCREVER UM NOME COM LETRAS.");
                    return atual;
                }
            }
        } else {
            Printes.espaco();
            System.out.println("--> OPÇÃO INVALIDA. É PRECISO ESCREVER UM NOME COM LETRAS.");
            return atual;
        }
        Printes.espaco();
        return nome;
    }

    public static String checarCPF(String cpf, String atual) {
        if (Objects.equals(cpf, "1")) {
            Printes.espaco();
            return atual;
        }

        if (cpf.length() == 11) {
            for (char c : cpf.toCharArray()) {
                if (!Character.isDigit(c)) {
                    Printes.espaco();
                    System.out.println("--> OPÇÃO INVÁLIDA. O CPF DEVE TER 11 CARACTERES, SENDO TODOS NÚMEROS.");
                    return atual;
                }
            }
            Printes.espaco();
            return cpf;
        } else {
            Printes.espaco();
            System.out.println("--> OPÇÃO INVÁLIDA. O CPF DEVE TER 11 CARACTERES, SENDO TODOS NÚMEROS.");
            return atual;
        }
    }

    public static String checarEmail(String email, String atual) {
        if (Objects.equals(email, "1")) {
            Printes.espaco();
            return atual;
        }

        boolean temArroba = false;
        boolean temPonto = false;

        for (char c : email.toCharArray()) {
            if (Objects.equals(c, '@')) temArroba = true;
            if (Objects.equals(c, '.')) temPonto = true;
        }
        if (temArroba && temPonto) {
            Printes.espaco();
            return email;
        } else {
            Printes.espaco();
            System.out.println("--> OPÇÃO INVALIDA. O E-MAIL DEVE TER ARROBA E UM DOMÍNIO.");
            return "";
        }
    }

    public static String checarSenha(String senha, String atual) {
        if (Objects.equals(senha, "1")) {
            Printes.espaco();
            return atual;
        }

        if (senha.length() >= 8) {
            Printes.espaco();
            return senha;
        } else {
            Printes.espaco();
            System.out.println("--> OPÇÃO INVALIDA. A SENHA DEVE TER 8 OU MAIS CARACTERES E NENHUM ESPAÇO.");
            return "";
        }
    }

    public static String checarSenha2(String senha1, String senha2, String falha) {
        if (Objects.equals(senha2, "1")) {
            Printes.espaco();
            return falha;
        } else {
            if (Objects.equals(senha2, senha1)) {
                Printes.espaco();
                return senha2;
            } else {
                Printes.espaco();
                System.out.println("--> OPÇÃO INVALIDA. AS SENHAS ESTÃO DIFERENTES.");
                return "";
            }
        }
    }
}
