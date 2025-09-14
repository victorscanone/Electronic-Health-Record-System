package users;

import others.Printes;
import database.ConnectDB;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MedicoCISAM extends UserCISAM implements Funcionario {

    private int salario;
    private String especialidade;

    public MedicoCISAM(String cpf) {
        super(cpf);
    }

    @Override
    public void procurarUsuario(String id) {
        if (!Objects.equals(id, "1")) {

            boolean certo = true;

            for (char c : id.toCharArray()) {
                if (!Character.isDigit(c)) {
                    certo = false;
                }
            }

            if (certo) {

                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlSearch = "SELECT IdUser, TipoDaConta, CPF, Nome, Nascimento FROM UserCISAM WHERE IdUser = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {

                    stmt.setString(1, id); // Aqui a query fica formada para a busca

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) { // Caso tenha esse ID no BD
                        //     ID        |            CPF           |  Nascimento  | Conta | Nome
                        String rapido = rs.getString("TipoDaConta");
                        if (Objects.equals(rapido, "p")) {

                            System.out.println("┌────────┬─────────────┬────────────┬──────────┬─────────────────────\n" +
                                    "│   ID   │     CPF     │ Nascimento │   Conta  │        Nome\n" +
                                    "├────────┼─────────────┼────────────┼──────────┼─────────────────────");
                            rapido = "Paciente";


                            String rapido2 = rs.getString("Nascimento");
                            if (rapido2 == null) {
                                rapido2 = "00/00/0000";
                            }

                            System.out.printf("│ %s │ %s │ %s │ %s │ %s\n", rs.getString("IdUser"), rs.getString("CPF"), rapido2, rapido, rs.getString("Nome"));

                            System.out.println("└────────┴─────────────┴────────────┴──────────┴─────────────────────\n");

                        } else {
                            System.out.println("--> ESSE ID NÃO É DE UM PACIENTE.\n");
                        }


                    } else {
                        System.out.println("--> USUÁRIO NÃO ENCONTRADO.\n");
                    }


                } catch (SQLException e) {
                    System.out.println("--> ERRO AO ACESSAR BANCO DE DADOS.\n");
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }


            } else {
                System.out.println("--> DIGITE O ID COM 6 NÚMEROS.\n");
            }
        }
    }

    public static boolean checarIdPaciente(String id) {
        boolean certo = true;

        for (char c : id.toCharArray()) {
            if (!Character.isDigit(c)) {
                certo = false;
                Printes.espaco();
                System.out.println("--> DIGITE O ID COM 6 NÚMEROS.");
            }
        }

        if (id.length() != 6) {
            certo = false;
            Printes.espaco();
            System.out.println("--> DIGITE O ID COM 6 NÚMEROS.");
        }

        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String sqlSearch = "SELECT IdUser, TipoDaConta, Nome, Nascimento FROM UserCISAM WHERE IdUser = ?";
        if (certo) {
            try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {
                stmt.setString(1, id); // Aqui a query fica formada para a busca

                ResultSet rs = stmt.executeQuery(); // Aqui a busca é executada

                if (!rs.next()) {
                    certo = false;
                    Printes.espaco();
                    System.out.println("--> NÃO EXISTE USUÁRIO COM ESSE ID.");
                } else {
                    if (!Objects.equals(rs.getString("TipoDaConta"), "p")) {
                        certo = false;
                        Printes.espaco();
                        System.out.println("--> ESSE ID NÃO É DE UM PACIENTE.");
                    }
                }

            } catch (SQLException e) {
                Printes.espaco();
                System.out.println("--> ERRO AO ACESSAR BANCO DE DADOS.");
                throw new RuntimeException(e);
            } finally {
                db.closeConnection();
            }
        }
        return certo;
    }

    public static void modoBeiraLeito2(String id) {
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String sqlSearch = "SELECT IdUser, TipoDaConta, Nome, Nascimento FROM UserCISAM WHERE IdUser = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {

            stmt.setString(1, id); // Aqui a query fica formada para a busca

            ResultSet rs = stmt.executeQuery(); // Aqui a busca é executada

            if (rs.next()) { // Caso tenha esse ID no BD. A partir daqui eu faço a busca no PacienteCISAM


                if (Objects.equals(rs.getString("TipoDaConta"), "p")) {
                    System.out.println("•Nome: " + rs.getString("Nome"));
                    System.out.println("•Nascimento: " + rs.getString("Nascimento"));
                    System.out.println();
                    System.out.println("•Dados de saúde + descrição:");

                    String sqlSearch2 = "SELECT IdUser, DadoDeSaúde, Descrição FROM PacienteCISAM WHERE IdUser = ?";

                    try (PreparedStatement stmt2 = conn.prepareStatement(sqlSearch2)) { // Aqui vou fazer a busca na planilha PacienteCISAM

                        stmt2.setString(1, id); // Aqui a query fica formada para a busca

                        ResultSet rs2 = stmt2.executeQuery();
                        while (rs2.next()) {
                            System.out.println("   - " + rs2.getString("DadoDeSaúde") + ": "+ rs2.getString("Descrição"));
                        }







                    } catch (SQLException e) {
                        System.out.println("--> ERRO AO ACESSAR BANCO DE DADOS.");
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                } else {
                    Printes.espaco();
                    System.out.println("--> ESSE ID NÃO É DE UM PACIENTE.");
                }
            } else {
                Printes.espaco();
                System.out.println("--> USUÁRIO NÃO ENCONTRADO.");
            }






        } catch (SQLException e) {
            Printes.espaco();
            System.out.println("--> ERRO AO ACESSAR BANCO DE DADOS.");
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void gerarTxt(String id, String descricao) {
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String sqlSearch = "SELECT IdUser, TipoDaConta, CPF, Nome, Nascimento FROM UserCISAM WHERE IdUser = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {

            stmt.setString(1, id); // Aqui a query fica formada para a busca

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // Caso tenha esse ID no BD

                if (Objects.equals(rs.getString("TipoDaConta"), "p")) {
                    String rapido = "Paciente";

                    String caminhoArquivo = String.format("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\usuario%s.txt", id);

                    try (FileWriter writer = new FileWriter(caminhoArquivo);) {

                        if (Objects.equals(descricao, "1")) {
                            writer.write(String.format("────────────────────────────\n" +
                                    "•Nome: %s;\n" +
                                    "•CPF: %s;\n" +
                                    "•ID: %s;\n" +
                                    "•Nascimento: %s;\n" +
                                    "•Tipo da conta: %s.\n" +
                                    "────────────────────────────\n", rs.getString("Nome"), rs.getString("CPF"), rs.getString("IdUser"), rs.getString("Nascimento"), rapido));
                            writer.close();
                        } else {
                            writer.write(String.format("────────────────────────────\n" +
                                    "•Nome: %s;\n" +
                                    "•CPF: %s;\n" +
                                    "•ID: %s;\n" +
                                    "•Nascimento: %s;\n" +
                                    "•Tipo da conta: %s.\n" +
                                    "────────────────────────────\n" +
                                    "•Descrição feita por um médico: %s.\n" +
                                    "────────────────────────────", rs.getString("Nome"), rs.getString("CPF"), rs.getString("IdUser"), rs.getString("Nascimento"), rapido, descricao));
                            writer.close();
                        }
                        Printes.espaco();


                    } catch (FileNotFoundException e) {
                        Printes.espaco();
                        System.out.println("--> ARQUIVO NÃO ENCONTRADO.");
                    } catch (IOException e) {
                        Printes.espaco();
                        System.out.println("--> ALGO DEU ERRADO.");
                    }
                } else {
                    Printes.espaco();
                    System.out.println("--> ESSE ID NÃO É DE UM PACIENTE.");
                }

            } else {
                Printes.espaco();
                System.out.println("--> USUÁRIO NÃO ENCONTRADO.");
            }


        } catch (SQLException e) {
            System.out.println("--> ERRO AO ACESSAR BANCO DE DADOS.");
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
    }
}
