package users;

import others.Printes;
import database.ConnectDB;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class GestorCISAM extends UserCISAM implements Funcionario {

    private int salario;
    private String funcao;

    public GestorCISAM(String cpf) {
        super(cpf);
    }

    public static void adicionarEspecialidade(String option) {

        StringBuilder textoTodo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\especialidades.txt"))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                textoTodo.append(linha.toUpperCase()).append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Printes.espaco();
            System.out.println("--> ARQUIVO NÃO ENCONTRADO.");
        } catch (IOException e) {
            Printes.espaco();
            System.out.println("--> ALGO DEU ERRADO.");
        }

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\especialidades.txt"));


            writer.write(String.format("%s%s", textoTodo, option));
            writer.close();
            Printes.espaco();

        } catch (FileNotFoundException e) {
            Printes.espaco();
            System.out.println("--> ARQUIVO NÃO ENCONTRADO.");
        } catch (IOException e) {
            Printes.espaco();
            System.out.println("--> ALGO DEU ERRADO.");
        }
    }

    public static void adicionarEspecialidade(String option, String quantidade) {
        boolean certo = true;
        for (char c : quantidade.toCharArray()) {
            if (!Character.isDigit(c)) {
                certo = false;
            }
        }
        if (certo) {
            StringBuilder textoTodo = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\especialidades.txt"))) {

                String linha;
                while ((linha = reader.readLine()) != null) {
                    textoTodo.append(linha.toUpperCase()).append("\n");
                }
                reader.close();
            } catch (FileNotFoundException e) {
                Printes.espaco();
                System.out.println("--> ARQUIVO NÃO ENCONTRADO.");
            } catch (IOException e) {
                Printes.espaco();
                System.out.println("--> ALGO DEU ERRADO.");
            }

            try {

                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\especialidades.txt"));


                writer.write(String.format("%s%s (%s profissionais)", textoTodo, option, quantidade));
                writer.close();
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
            System.out.println("--> DIGITE A QUANTIDADE COM NÚMEROS.");
        }
    }

    public static void excluirEspecialidade(String option) {
        if (!Objects.equals(option, "1")) {
            StringBuilder textoTodo = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\especialidades.txt"))) {

                String linha;
                while ((linha = reader.readLine()) != null) {
                    if (!linha.contains(option)) {
                        textoTodo.append(linha.toUpperCase()).append("\n");
                    }
                }
                reader.close();
            } catch (FileNotFoundException e) {
                Printes.espaco();
                System.out.println("--> ARQUIVO NÃO ENCONTRADO.");
            } catch (IOException e) {
                Printes.espaco();
                System.out.println("--> ALGO DEU ERRADO.");
            }

            try {

                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\especialidades.txt"));


                writer.write(String.format("%s", textoTodo));
                writer.close();
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
        }
    }

    public static String cadastrarmedico(String nome, String cpf, String email, String senha1, String senha2) {
        if ((!Objects.equals(nome, "")) && (!Objects.equals(cpf, "")) && (!Objects.equals(cpf, "00000000000")) && (!Objects.equals(email, "")) && (!Objects.equals(senha1, "")) && (!Objects.equals(senha2, "")) && (Objects.equals(senha1, senha2))) {
            if (!Logar.duplicidadeCPF(cpf)) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "INSERT INTO UserCISAM (IdUser, TipoDaConta, CPF, Email, Senha, Nome) VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, Logar.gerarIdNovo());
                    stmt.setString(2, "m");
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
                        } else if (Objects.equals(rapido, "m")) {
                            System.out.println("┌────────┬─────────────┬────────────┬────────┬─────────────────────\n" +
                                               "│   ID   │     CPF     │ Nascimento │  Conta │        Nome\n" +
                                               "├────────┼─────────────┼────────────┼────────┼─────────────────────");
                            rapido = "Médico";
                        } else if (Objects.equals(rapido, "g")) {
                            System.out.println("┌────────┬─────────────┬────────────┬────────┬─────────────────────\n" +
                                               "│   ID   │     CPF     │ Nascimento │  Conta │        Nome\n" +
                                               "├────────┼─────────────┼────────────┼────────┼─────────────────────");
                            rapido = "Gestor";
                        }

                        String rapido2 = rs.getString("Nascimento");
                        if (rapido2 == null) {
                            rapido2 = "00/00/0000";
                        }

                        System.out.printf("│ %s │ %s │ %s │ %s │ %s\n", rs.getString("IdUser"), rs.getString("CPF"), rapido2, rapido, rs.getString("Nome"));
                        if (Objects.equals(rapido, "Paciente")) {
                            System.out.println("└────────┴─────────────┴────────────┴──────────┴─────────────────────\n");
                        } else {
                            System.out.println("└────────┴─────────────┴────────────┴────────┴─────────────────────\n");
                        }


                    } else {
                        System.out.println("--> USUÁRIO NÃO ENCONTRADO.\n");
                    }


                } catch (SQLException e) {
                    System.out.println("--> ERRO AO ACESSAR BANCO DE DADOS.");
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }


            } else {
                System.out.println("--> DIGITE O ID COM 6 NÚMEROS.\n");
            }
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

                String caminhoArquivo = String.format("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\usuario%s.txt", id);

                try (FileWriter writer = new FileWriter(caminhoArquivo);) {

                    String rapido = rs.getString("TipoDaConta");
                    if (Objects.equals(rapido, "p")) {
                        rapido = "Paciente";
                    } else if (Objects.equals(rapido, "m")) {
                        rapido = "Médico";
                    } else if (Objects.equals(rapido, "g")) {
                        rapido = "Gestor";
                    }

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
                                "•Descrição feita por um gestor: %s.\n" +
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
                System.out.println("--> USUÁRIO NÃO ENCONTRADO.");
            }


        } catch (SQLException e) {
            System.out.println("--> ERRO AO ACESSAR BANCO DE DADOS.");
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
    }

    public static void serializacoesFeitas() {
        File pasta = new File("C:\\Users\\Victor\\IdeaProjects\\s-cisam");
        File[] arquivos = pasta.listFiles();

        System.out.println("•ARQUIVOS SERIALIZADOS:");
        for (File arquivo : arquivos) {
            if (arquivo.getName().contains("serial")) {
                System.out.printf("•%s\n", arquivo.getName());
            }
        }
        Printes.linha();
    }

    public static void excluirSerial(String exclu) {
        File arquivo = new File(String.format("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\serial%s.ser", exclu));

        boolean certo = true;

        for (char c : exclu.toCharArray()) {
            if (!Character.isDigit(c)) {
                certo = false;
            }
        }

        if (certo) {
            if (arquivo.exists()) {
                if (arquivo.delete()) {
                    Printes.espaco();
                    System.out.println("--> USUÁRIO DELETADO.");
                } else {
                    Printes.espaco();
                    System.out.println("--> USUÁRIO NÃO FOI DELETADO.");
                }
            } else {
                Printes.espaco();
                System.out.println("--> ESSE USUÁRIO NÃO EXISTE.");
            }

            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            String sqlDelete = "DELETE FROM UserCISAM WHERE IdUser = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {

                stmt.setString(1, exclu);

                int excluiu = stmt.executeUpdate();

                Printes.espaco();
                if (excluiu > 0) {
                    Printes.espaco();
                    System.out.println("--> AÇÃO EXECUTADA.");
                } else {
                    Printes.espaco();
                    System.out.println("--> NENHUMA LINHA FOI EXCLUIDA.");
                }

            } catch (SQLException e) {
                Printes.espaco();
                System.out.println("--> ERRO AO EXCLUIR USUÁRIO.");
                e.printStackTrace();
            } finally {
                db.closeConnection();
            }
        } else {
            Printes.espaco();
            System.out.println("--> O ID TEM QUE SER NÚMERICO.");
        }



    }
}
