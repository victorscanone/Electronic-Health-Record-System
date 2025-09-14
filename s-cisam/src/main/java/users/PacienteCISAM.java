package users;

import others.Printes;
import database.ConnectDB;
import dateAndTime.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PacienteCISAM extends UserCISAM {
    private String tamanhoLista;

    public PacienteCISAM(String cpf) {
        super(cpf);
    }

    public String getTamanhoLista() {
        return tamanhoLista;
    }

    public void listaDadosSaude(String idUser) {

        ArrayList<String> primeiraLinha = new ArrayList<>(Arrays.asList("N°", "Início", "Fim", "DadoDeSaúde", "Descrição")); // "  N° |   Início   |    Fim     | DadoDeSaúde + Descrição"
        ArrayList<ArrayList<String>> linhaDados = new ArrayList<>();
        linhaDados.add(primeiraLinha);

        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String sqlSearch = "SELECT IdUser, IdDadoDeSaúde, DataDeInicio, DataDeFim, DadoDeSaúde, Descrição FROM PacienteCISAM WHERE idUser = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {
            stmt.setString(1, idUser); // Aqui a query fica formada para a busca

            ResultSet rs = stmt.executeQuery(); // Aqui a query é executada

            System.out.println("  N° |   Início   |    Fim     | DadoDeSaúde + Descrição");
            String idDadoDeSaude = "000";
            while (rs.next()) {
                //count += 1;
                //String vez = String.format("%3d", count);

                idDadoDeSaude = rs.getString("IdDadoDeSaúde");
                String dataDeInicio = rs.getString("DataDeInicio");
                String dataDeFim = rs.getString("DataDeFim");
                String dadoDeSaude = rs.getString("DadoDeSaúde");
                String descricao = rs.getString("Descrição");

                String dataDeFim2 = (Objects.equals(dataDeFim, "")) ? "00/00/0000" : dataDeFim;

                System.out.printf("l%s | %s | %s | %s: %s\n", idDadoDeSaude, dataDeInicio, dataDeFim2, dadoDeSaude, descricao);

            }
            this.tamanhoLista = idDadoDeSaude;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
    }

    public void adicionarItemALista(String idUser, String dataDeInicio, String dataDeFim, String dadoDeSaude, String descricao, String tamanhoLista) {


        Data dataDeInicio2;
        if (!Objects.equals(dataDeInicio, "") && dataDeInicio.length() == 8) {
            dataDeInicio2 = new Data(dataDeInicio.substring(0, 2), dataDeInicio.substring(2, 4), dataDeInicio.substring(4, 8));
        } else {
            dataDeInicio2 = new Data("0", "0", "0");
        }

        Data dataDeFim2;
        if (!Objects.equals(dataDeFim, "") && dataDeFim.length() == 8) {
            dataDeFim2 = new Data(dataDeFim.substring(0, 2), dataDeFim.substring(2, 4), dataDeFim.substring(4, 8));
        } else {
            dataDeFim2 = new Data("0", "0", "0");
        }



        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        // Parte de pegar a quantidade de dados do ID
        int rapido = Integer.parseInt(tamanhoLista) + 1;
        String rapido2 = String.format("%3d", rapido);
        String idDadoDeSaude = rapido2.replace(" ", "0");



        String sqlInsert = "INSERT INTO PacienteCISAM (IdUser, IdDadoDeSaúde, DataDeInicio, DataDeFim, DadoDeSaúde, Descrição) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt2 = conn.prepareStatement(sqlInsert)) {

            stmt2.setString(1, idUser);
            stmt2.setString(2, idDadoDeSaude);
            stmt2.setString(3, dataDeInicio2.toString());
            stmt2.setString(4, dataDeFim2.toString());
            stmt2.setString(5, dadoDeSaude);
            stmt2.setString(6, descricao);

            stmt2.executeUpdate();


            Printes.espaco();

            System.out.println("--> DADO INSERIDO COM SUCESSO.");


        } catch (SQLException e) {
            Printes.espaco();
            System.out.println("--> ERRO AO INSERIR NOVO DADO.");
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

    }

    public void removerItemDaLista(String linha, String idUser) {
        boolean certo = true;

        if (linha.length() != 4) {
            certo = false;
            System.out.println("Aqui 1");
        }

        if (certo) {
            if (!Objects.equals(linha.substring(0, 1), "l")) {
                certo = false;
                System.out.println("Aqui 2");
            } else {
                if (Character.isDigit(Integer.parseInt(linha.substring(1, 4)))) {
                    certo = false;
                    System.out.println("Aqui 3");
                }
            }
        }

        if (certo) {
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            String sqlDelete = "DELETE FROM PacienteCISAM WHERE IdUser = ? AND IdDadoDeSaúde = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {



                stmt.setString(1, idUser);
                stmt.setString(2, linha.substring(1, 4));

                int excluiu = stmt.executeUpdate();

                Printes.espaco();
                if (excluiu > 0) {
                    Printes.espaco();
                    System.out.println("--> AÇÃO EXECUTADA.");
                } else {
                    Printes.espaco();
                    System.out.println("--> NENHUMA LINHA FOI EXCLUIDA.");
                }




                Printes.espaco();
                System.out.println("--> LINHA EXCLUIDA COM SUCESSO.");

            } catch (SQLException e) {
                Printes.espaco();
                System.out.println("--> ERRO AO EXCLUIR LINHA.");
                e.printStackTrace();
            } finally {
                db.closeConnection();
            }
        } else {
            Printes.espaco();
            System.out.println("--> ESCREVA NO FORMATO: l000.");
        }
    }
}
