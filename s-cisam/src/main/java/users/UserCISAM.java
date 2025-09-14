package users;

import others.Printes;
import database.ConnectDB;
import dateAndTime.Data;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public abstract class UserCISAM implements Serializable {

    private String idUser;
    private String nome;
    private Data dataDeNascimento;
    private String cpf;
    private char sexoBiologico;
    private String nomeDaMae;
    private String telefoneFixo;
    private String telefoneCelular;
    private String email;
    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private String complemento;
    private char tipodaConta;
    private String senha;

    public UserCISAM(String cpf) {
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();

        String sqlSearch = "SELECT IdUser, TipoDaConta, CPF, Email, Senha, Nome, TelefoneFixo, TelefoneCelular, NomeDaMãe, Nascimento, SexoBiológico, País, Estado, CEP, Cidade, Bairro, Rua, Complemento FROM UserCISAM WHERE CPF = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlSearch)) {

            stmt.setString(1, cpf); // Aqui a query fica formada para a busca

            ResultSet rs = stmt.executeQuery(); // Aqui a query é executada

            if (rs.next()) { // True caso tenha o cpf


                String dataDoUsuario = rs.getString("Nascimento");

                String dia;
                String mes;
                String ano;

                if (dataDoUsuario == null) {
                    dia = "0";
                    mes = "0";
                    ano = "0";
                } else {
                    dia = dataDoUsuario.substring(0, 2);
                    mes = dataDoUsuario.substring(3, 5);
                    ano = dataDoUsuario.substring(6, 10);
                }

                this.idUser = rs.getString("IdUser"); // Capturar o valor
                this.nome = rs.getString("Nome");
                this.dataDeNascimento = new Data(dia, mes, ano);
                this.cpf = rs.getString("CPF");
                if (rs.getString("SexoBiológico") == null) {
                    this.sexoBiologico = ' ';
                } else {
                    this.sexoBiologico = (rs.getString("SexoBiológico").charAt(0));
                }
                this.nomeDaMae = rs.getString("NomeDaMãe");
                this.telefoneFixo = rs.getString("TelefoneFixo"); // 10 digitos
                this.telefoneCelular = rs.getString("TelefoneCelular"); // 13 digitos
                this.email = rs.getString("Email");
                this.pais = rs.getString("País");
                this.estado = rs.getString("Estado");
                this.cidade = rs.getString("Cidade");
                this.cep = rs.getString("CEP");
                this.bairro = rs.getString("Bairro");
                this.rua = rs.getString("Rua");
                this.complemento = rs.getString("Complemento");
                this.tipodaConta = rs.getString("TipoDaConta").charAt(0);
                this.senha = rs.getString("Senha");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.closeConnection();
        }
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {

        String novo = Logar.checarNome(nome, this.nome);

        if (!Objects.equals(novo, this.nome)) {
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            String sqlInsert = "UPDATE UserCISAM SET Nome = ? WHERE CPF = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                stmt.setString(1, nome);  // Aqui a query fica formada para a busca
                stmt.setString(2, this.cpf);
                int rs = stmt.executeUpdate(); // Aqui a query é executada


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                db.closeConnection();
            }
            this.nome = nome;
            Printes.espaco();
        }



    }

    public String getDataDeNascimento() {
        if (dataDeNascimento.toString() == null) {
            return "00/00/0000";
        } else {
            return dataDeNascimento.toString();
        }
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        if (!Objects.equals(dataDeNascimento, "1")) {
            String velhaData = this.dataDeNascimento.toString();

            if (dataDeNascimento.length() == 8) {
                String dia = dataDeNascimento.substring(0, 2);
                String mes = dataDeNascimento.substring(2, 4);
                String ano = dataDeNascimento.substring(4, 8);

                this.dataDeNascimento.atualizarData(dia, mes, ano);
            } else {
                Printes.espaco();
                System.out.println("--> DATA ESCRITA ERRADA.");
            }


            if (!Objects.equals(velhaData, this.dataDeNascimento.toString())) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "UPDATE UserCISAM SET Nascimento = ? WHERE CPF = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, this.dataDeNascimento.toString());  // Aqui a query fica formada para a busca
                    stmt.setString(2, this.cpf);
                    int rs = stmt.executeUpdate(); // Aqui a query é executada


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }
            }
        } else {
            Printes.espaco();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {

        boolean simnao = Logar.duplicidadeCPF(cpf);


        if (simnao) {
            Printes.espaco();
            System.out.println("--> OPÇÃO INVÁLIDA.");
        } else {
            String novo = Logar.checarCPF(cpf, this.cpf);

            if (!Objects.equals(novo, this.cpf)) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "UPDATE UserCISAM SET CPF = ? WHERE CPF = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, cpf);  // Aqui a query fica formada para a busca
                    stmt.setString(2, this.cpf);
                    int rs = stmt.executeUpdate(); // Aqui a query é executada


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }
                this.cpf = cpf;
                Printes.espaco();
            }

        }

    }

    public char getSexoBiologico() {
        return sexoBiologico;
    }

    public void setSexoBiologico(String sexoBiologico) {
        if (sexoBiologico.length() == 1) {
            if (Objects.equals(sexoBiologico, "h") || Objects.equals(sexoBiologico, "m")) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "UPDATE UserCISAM SET SexoBiológico = ? WHERE CPF = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, sexoBiologico);  // Aqui a query fica formada para a busca
                    stmt.setString(2, this.cpf);
                    int rs = stmt.executeUpdate(); // Aqui a query é executada


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }
                this.sexoBiologico = sexoBiologico.charAt(0);
                Printes.espaco();


            } else if (Objects.equals(sexoBiologico, "1")) {
                Printes.espaco();
            } else {
                Printes.espaco();
                System.out.println("--> OPÇÃO INVALIDA. DIGITE 'H' OU 'M'.");
            }
        } else {
            Printes.espaco();
            System.out.println("--> OPÇÃO INVALIDA. DIGITE 'H' OU 'M'.");
        }
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        String novo = Logar.checarNome(nomeDaMae, this.nomeDaMae);

        if (!Objects.equals(novo, this.nomeDaMae)) {
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            String sqlInsert = "UPDATE UserCISAM SET NomeDaMãe = ? WHERE CPF = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                stmt.setString(1, nomeDaMae);  // Aqui a query fica formada para a busca
                stmt.setString(2, this.cpf);
                int rs = stmt.executeUpdate(); // Aqui a query é executada


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                db.closeConnection();
            }
            this.nomeDaMae = nomeDaMae;
            Printes.espaco();
        }
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {

        if (!Objects.equals(telefoneFixo, "1")) {
            boolean certo = true;

            for (char c : telefoneFixo.toCharArray()) {
                if (!Character.isDigit(c)) {
                    certo = false;
                }
            }

            if (telefoneFixo.length() != 10) {
                certo = false;
            }

            if (certo) {

                if (!Objects.equals(telefoneFixo, this.telefoneFixo)) {

                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String sqlInsert = "UPDATE UserCISAM SET TelefoneFixo = ? WHERE CPF = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, telefoneFixo);  // Aqui a query fica formada para a busca
                        stmt.setString(2, this.cpf);
                        int rs = stmt.executeUpdate(); // Aqui a query é executada


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                    this.telefoneFixo = telefoneFixo;
                    Printes.espaco();
                } else {
                    Printes.espaco();
                }

            } else {
                Printes.espaco();
                System.out.println("--> DIGITE UM TELEFONE COM 10 NÚMEROS.");
            }
        } else {
            Printes.espaco();
        }
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {

        if (!Objects.equals(telefoneCelular, "1")) {
            boolean certo = true;

            for (char c : telefoneCelular.toCharArray()) {
                if (!Character.isDigit(c)) {
                    certo = false;
                }
            }

            if (telefoneCelular.length() != 13) {
                certo = false;
            }

            if (certo) {

                if (!Objects.equals(telefoneCelular, this.telefoneCelular)) {

                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String sqlInsert = "UPDATE UserCISAM SET TelefoneCelular = ? WHERE CPF = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, telefoneCelular);  // Aqui a query fica formada para a busca
                        stmt.setString(2, this.cpf);
                        int rs = stmt.executeUpdate(); // Aqui a query é executada


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                    this.telefoneCelular = telefoneCelular;
                    Printes.espaco();
                } else {
                    Printes.espaco();
                }

            } else {
                Printes.espaco();
                System.out.println("--> DIGITE UM TELEFONE COM 13 NÚMEROS.");
            }
        } else {
            Printes.espaco();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Objects.equals(email, "1")) {
            String novo = this.email;

            boolean temArroba = false;
            boolean temPonto = false;

            for (char c : email.toCharArray()) {
                if (Objects.equals(c, '@')) temArroba = true;
                if (Objects.equals(c, '.')) temPonto = true;
            }

            if (temArroba && temPonto) {
                novo = email;
            } else {
                Printes.espaco();
                System.out.println("--> OPÇÃO INVALIDA. O E-MAIL DEVE TER ARROBA E UM DOMÍNIO.");
            }

            if (!Objects.equals(novo, this.email)) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "UPDATE UserCISAM SET Email = ? WHERE CPF = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, email);  // Aqui a query fica formada para a busca
                    stmt.setString(2, this.cpf);
                    int rs = stmt.executeUpdate(); // Aqui a query é executada


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }
                this.email = email;
                Printes.espaco();
            } else {
                Printes.espaco();
            }
        } else {
            Printes.espaco();
        }
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        if (!Objects.equals(pais, "1")) {

            boolean certo = true;

            for (char c : pais.toCharArray()) {
                if (Character.isDigit(c)) {
                    certo = false;
                    Printes.espaco();
                    System.out.println("--> DIGITE O NOME DO SEU PAÍS APENAS COM LETRAS.");
                }
            }

            String novo;

            if (certo) {
                novo = pais;


                if (!Objects.equals(novo, this.pais)) {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String sqlInsert = "UPDATE UserCISAM SET País = ? WHERE CPF = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, pais);  // Aqui a query fica formada para a busca
                        stmt.setString(2, this.cpf);
                        int rs = stmt.executeUpdate(); // Aqui a query é executada


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                    this.pais = pais;
                    Printes.espaco();
                } else {
                    Printes.espaco();
                }
            }
        } else {
            Printes.espaco();
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (!Objects.equals(estado, "1")) {

            boolean certo = true;

            for (char c : estado.toCharArray()) {
                if (Character.isDigit(c)) {
                    certo = false;
                    Printes.espaco();
                    System.out.println("--> DIGITE O NOME DO SEU ESTADO APENAS COM LETRAS.");
                }
            }

            String novo;

            if (certo) {
                novo = estado;
                if (!Objects.equals(novo, this.estado)) {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String sqlInsert = "UPDATE UserCISAM SET Estado = ? WHERE CPF = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, estado);  // Aqui a query fica formada para a busca
                        stmt.setString(2, this.cpf);
                        int rs = stmt.executeUpdate(); // Aqui a query é executada


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                    this.estado = estado;
                    Printes.espaco();
                } else {
                    Printes.espaco();
                }
            }

        } else {
            Printes.espaco();
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (!Objects.equals(cidade, "1")) {

            boolean certo = true;

            for (char c : cidade.toCharArray()) {
                if (Character.isDigit(c)) {
                    certo = false;
                    Printes.espaco();
                    System.out.println("--> DIGITE O NOME DA SUA CIDADE APENAS COM LETRAS.");
                }
            }

            String novo;

            if (certo) {
                novo = cidade;
                if (!Objects.equals(novo, this.cidade)) {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String sqlInsert = "UPDATE UserCISAM SET Cidade = ? WHERE CPF = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, cidade);  // Aqui a query fica formada para a busca
                        stmt.setString(2, this.cpf);
                        int rs = stmt.executeUpdate(); // Aqui a query é executada


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                    this.cidade = cidade;
                    Printes.espaco();
                } else {
                    Printes.espaco();
                }
            }


        } else {
            Printes.espaco();
        }
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (!Objects.equals(cep, "1")) {

            boolean certo = true;

            for (char c : cep.toCharArray()) {
                if (!Character.isDigit(c)) {
                    certo = false;
                    Printes.espaco();
                    System.out.println("--> DIGITE O CEP COM 8 CARACTERES SENDO ELES APENAS NÚMEROS.");
                }
            }

            if (cep.length() != 8) {
                certo = false;
                Printes.espaco();
                System.out.println("--> DIGITE O CEP COM 8 CARACTERES SENDO ELES APENAS NÚMEROS.");
            }

            String novo;

            if (certo) {
                novo = cep;
                if (!Objects.equals(novo, this.cep)) {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String sqlInsert = "UPDATE UserCISAM SET CEP = ? WHERE CPF = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, cep);  // Aqui a query fica formada para a busca
                        stmt.setString(2, this.cpf);
                        int rs = stmt.executeUpdate(); // Aqui a query é executada


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                    this.cep = cep;
                    Printes.espaco();
                } else {
                    Printes.espaco();
                }
            }


        } else {
            Printes.espaco();
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (!Objects.equals(bairro, "1")) {

            boolean certo = true;

            for (char c : bairro.toCharArray()) {
                if (Character.isDigit(c)) {
                    certo = false;
                    Printes.espaco();
                    System.out.println("--> DIGITE O NOME DO SEU BAIRRO APENAS COM LETRAS.");
                }
            }

            String novo;

            if (certo) {
                novo = bairro;
                if (!Objects.equals(novo, this.bairro)) {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String sqlInsert = "UPDATE UserCISAM SET Bairro = ? WHERE CPF = ?";

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, bairro);  // Aqui a query fica formada para a busca
                        stmt.setString(2, this.cpf);
                        int rs = stmt.executeUpdate(); // Aqui a query é executada


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        db.closeConnection();
                    }
                    this.bairro = bairro;
                    Printes.espaco();
                } else {
                    Printes.espaco();
                }
            }


        } else {
            Printes.espaco();
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if (!Objects.equals(rua, "1")) {

            if (!Objects.equals(rua, this.rua)) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "UPDATE UserCISAM SET Rua = ? WHERE CPF = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, rua);  // Aqui a query fica formada para a busca
                    stmt.setString(2, this.cpf);
                    int rs = stmt.executeUpdate(); // Aqui a query é executada


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }
                this.rua = rua;
                Printes.espaco();
            } else {
                Printes.espaco();
            }
        } else {
            Printes.espaco();
        }
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        if (!Objects.equals(complemento, "1")) {

            if (!Objects.equals(complemento, this.complemento)) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "UPDATE UserCISAM SET Complemento = ? WHERE CPF = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, complemento);  // Aqui a query fica formada para a busca
                    stmt.setString(2, this.cpf);
                    int rs = stmt.executeUpdate(); // Aqui a query é executada


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }
                this.complemento = complemento;
                Printes.espaco();
            } else {
                Printes.espaco();
            }
        } else {
            Printes.espaco();
        }
    }

    public char getTipodaConta() {
        return tipodaConta;
    }

    public void setTipodaConta(char tipodaConta) {
        this.tipodaConta = tipodaConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (!Objects.equals(senha, "1")) {
            String novo = Logar.checarSenha(senha, this.senha);

            if (!Objects.equals(novo, this.senha) && !Objects.equals(novo, "")) {
                ConnectDB db = new ConnectDB();
                Connection conn = db.getConnection();

                String sqlInsert = "UPDATE UserCISAM SET Senha = ? WHERE CPF = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                    stmt.setString(1, senha);  // Aqui a query fica formada para a busca
                    stmt.setString(2, this.cpf);
                    int rs = stmt.executeUpdate(); // Aqui a query é executada


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    db.closeConnection();
                }
                this.senha = senha;
                Printes.espaco();
            }
        } else {
            Printes.espaco();
        }
    }

    public final boolean logOut() {
        return false;
    }

    public static void mostrarListaEspecialidades() {

        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Victor\\IdeaProjects\\s-cisam\\src\\especialidades.txt"))){

            String linha;
            while((linha = reader.readLine()) != null) {
                System.out.printf("•%s\n", linha.toUpperCase());
            }
        } catch(FileNotFoundException e){
            System.out.println("--> ARQUIVO NÃO ENCONTRADO.");
        } catch(IOException e){
            System.out.println("--> ALGO DEU ERRADO.");
        }
    }

    public void serializarUser(UserCISAM user) {
        try {
            FileOutputStream fileOut = new FileOutputStream(String.format("serial%s.ser", idUser));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
            Printes.espaco();
            System.out.println("--> SERIALIZAÇÃO COMPLETA.");
        } catch (IOException e) {
            Printes.espaco();
            System.out.println("--> NÃO FOI POSSÍVEL SERIALIZAR.");
            e.printStackTrace();
        }
    }

    public void desSerial(UserCISAM user) {
        try {
            FileInputStream fileIn = new FileInputStream(String.format("serial%s.ser", idUser));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            UserCISAM usuario2;
            usuario2 = (UserCISAM) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("--> INFORMAÇÕES DO USER SERIALIZADO.");
            System.out.println("•Nome: " + usuario2.getNome());
            System.out.println("•CPF: " + usuario2.getCpf());
            System.out.println("•ID: " + usuario2.getIdUser());
            System.out.println("•Nascimento: " + usuario2.getDataDeNascimento());
        } catch (IOException e) {
            Printes.espaco();
            System.out.println("--> NÃO FOI POSSÍVEL MOSTRAR A SERIALIZAÇÃO.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
