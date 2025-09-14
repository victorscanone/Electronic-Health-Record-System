import others.Printes;
import users.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Main implements Serializable {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String option; // opção da vez
        String passageiro = "";
        String idBeiraLeito = "";
        String qtdMedicos = "";
        String[] cpfSenha = {"", ""};
        String[] criarConta = {"", "00000000000", "", "", ""};
        String[] adicionarDado = {"", "", "", ""};
        char logged = 'n'; // "n" Logged out; "p" Paciente; "m" Médico; "g" Gestor
        boolean verLista = false;
        boolean serializou = false;
        UserCISAM usuario = null;

        Printes.espaco();



        while (true) {
            outer:
            if (logged == 'n') {
                /*
         ,--.
       ,--.'|                               ,--,
   ,--,:  : |                             ,--.'|                                         ,---,
,`--.'`|  ' :              ,---.          |  | :     ,---.                             ,---.'|   ,---.
|   :  :  | |             '   ,'\         :  : '    '   ,'\   ,----._,.                |   | :  '   ,'\
:   |   \ | :  ,--.--.   /   /   |        |  ' |   /   /   | /   /  ' /   ,--.--.      |   | | /   /   |
|   : '  '; | /       \ .   ; ,. :        '  | |  .   ; ,. :|   :     |  /       \   ,--.__| |.   ; ,. :
'   ' ;.    ;.--.  .-. |'   | |: :        |  | :  '   | |: :|   | .\  . .--.  .-. | /   ,'   |'   | |: :
|   | | \   | \__\/: . .'   | .; :        '  : |__'   | .; :.   ; ';  |  \__\/: . ..   '  /  |'   | .; :
'   : |  ; .' ," .--.; ||   :    |        |  | '.'|   :    |'   .   . |  ," .--.; |'   ; |:  ||   :    |
|   | '`--'  /  /  ,.  | \   \  /         ;  :    ;\   \  /  `---`-'| | /  /  ,.  ||   | '/  ' \   \  /
'   : |     ;  :   .'   \ `----'          |  ,   /  `----'   .'__/\_: |;  :   .'   \   :    :|  `----'
;   |.'     |  ,     .-./                  ---`-'            |   :    :|  ,     .-./\   \  /
'---'        `--`---'                                         \   \  /  `--`---'     `----'
                                                               `--`-'
*/
                Printes.menuPrincipal();
                System.out.println("•[1] Login");
                System.out.println("•[2] Criar conta S-CISAM");
                System.out.println("•[3] Sair");
                Printes.linha();
                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                option = input.next();




                if (Objects.equals(option, "1")) { //•[1] Login
                    Printes.espaco();
                    while (true) {
                        Printes.menuLogin();
                        System.out.println("•[1] Digite seu CPF: " + cpfSenha[0]);
                        System.out.println("•[2] Digite sua senha: " + cpfSenha[1]);
                        System.out.println("\n•[3] Logar");
                        System.out.println("•[4] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) {
                            System.out.print("•[1] <<< DIGITE APENAS OS NÚMEROS DO CPF, OU 1 PARA CANCELAR: ");
                            cpfSenha[0] = Logar.checarCPF(input.next(), cpfSenha[0]);
                        } else if (Objects.equals(option, "2")) {
                            System.out.print("•[2] <<< DIGITE SUA SENHA, OU 1 PARA CANCELAR: ");
                            cpfSenha[1] = Logar.checarSenha(input.next(), cpfSenha[1]);
                        } else if (Objects.equals(option, "3")) {

                            option = Logar.logar(cpfSenha[0], cpfSenha[1]);

                            if (Objects.equals(option, "p")) {
                                usuario = new PacienteCISAM(cpfSenha[0]);
                                Printes.espaco();
                                cpfSenha[0] = "";
                                cpfSenha[1] = "";
                                logged = 'p';
                                break outer;
                            } else if (Objects.equals(option, "m")) {
                                usuario = new MedicoCISAM(cpfSenha[0]);
                                Printes.espaco();
                                cpfSenha[0] = "";
                                cpfSenha[1] = "";
                                logged = 'm';
                                break outer;
                            } else if (Objects.equals(option, "g")) {
                                usuario = new GestorCISAM(cpfSenha[0]);
                                Printes.espaco();
                                cpfSenha[0] = "";
                                cpfSenha[1] = "";
                                logged = 'g';
                                break outer;
                            } else {
                                Printes.espaco();
                                System.out.println("--> SENHA OU CPF INCORRETOS.");
                            }

                        } else if (Objects.equals(option, "4")) {
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }







                } else if (Objects.equals(option, "2")) { //•[n.2] Criar conta S-CISAM
                    Printes.espaco();
                    while (true) {
                        Printes.menuCriarConta();
                        System.out.println("•[1] Digite seu nome completo: " + criarConta[0].toLowerCase());
                        System.out.println("•[2] Digite seu CPF: " + criarConta[1]);
                        System.out.println("•[3] Digite seu E-mail: " + criarConta[2]);
                        System.out.println("•[4] Digite uma senha: " + criarConta[3]);
                        System.out.println("•[5] Confirme a senha: " + criarConta[4]);
                        System.out.println("\n•[6] Criar conta");
                        System.out.println("•[7] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();
                        input.nextLine();

                        if (Objects.equals(option, "1")) {
                            System.out.print("•[1] <<< DIGITE SEU NOME, OU 1 PARA CANCELAR: ");
                            passageiro = input.nextLine();
                            criarConta[0] = Logar.checarNome(passageiro.toLowerCase(), criarConta[0].toLowerCase());
                        } else if (Objects.equals(option, "2")) {
                            System.out.print("•[2] <<< DIGITE APENAS OS NÚMEROS DO CPF, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[1] = Logar.checarCPF(passageiro, criarConta[1]);
                        } else if (Objects.equals(option, "3")) {
                            System.out.print("•[3] <<< DIGITE SEU E-MAIL, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[2] = Logar.checarEmail(passageiro.toLowerCase(), criarConta[2].toLowerCase());
                        } else if (Objects.equals(option, "4")) {
                            System.out.print("•[4] <<< DIGITE SUA SENHA, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[3] = Logar.checarSenha(passageiro, criarConta[3]);
                        } else if (Objects.equals(option, "5")) {
                            System.out.print("•[5] <<< CONFIRME SUA SENHA, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[4] = Logar.checarSenha2(criarConta[3], passageiro, criarConta[4]);
                        } else if (Objects.equals(option, "6")) { // [n.2.6] Criar conta
                            if (Objects.equals(Logar.criarConta(criarConta[0], criarConta[1], criarConta[2], criarConta[3], criarConta[4]), criarConta[1])) {
                                logged = 'p';
                                usuario = new PacienteCISAM(criarConta[1]);
                                Printes.espaco();
                                System.out.println("--> CONTA CRIADA COM SUCESSO.");
                                break;
                            }
                        } else if (Objects.equals(option, "7")) { // •[n.2.7] Voltar
                            Printes.espaco();
                            criarConta[0] = "";
                            criarConta[1] = "00000000000";
                            criarConta[2] = "";
                            criarConta[3] = "";
                            criarConta[4] = "";
                            break outer;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }







                } else if (Objects.equals(option, "3")) { //•[3] Sair
                    break;
                } else {
                    Printes.espaco();
                    System.out.println("--> OPÇÃO INVÁLIDA.");
                }


            } else if (logged == 'p' && (usuario instanceof PacienteCISAM)) {
                /*
,-.----.
\    /  \                                                        ___
|   :    \                       ,--,                          ,--.'|_
|   |  .\ :                    ,--.'|                  ,---,   |  | :,'
.   :  |: |                    |  |,               ,-+-. /  |  :  : ' :
|   |   \ : ,--.--.     ,---.  `--'_       ,---.  ,--.'|'   |.;__,'  /     ,---.
|   : .   //       \   /     \ ,' ,'|     /     \|   |  ,"' ||  |   |     /     \
;   | |`-'.--.  .-. | /    / ' '  | |    /    /  |   | /  | |:__,'| :    /    /  |
|   | ;    \__\/: . ..    ' /  |  | :   .    ' / |   | |  | |  '  : |__ .    ' / |
:   ' |    ," .--.; |'   ; :__ '  : |__ '   ;   /|   | |  |/   |  | '.'|'   ;   /|
:   : :   /  /  ,.  |'   | '.'||  | '.'|'   |  / |   | |--'    ;  :    ;'   |  / |
|   | :  ;  :   .'   \   :    :;  :    ;|   :    |   |/        |  ,   / |   :    |
`---'.|  |  ,     .-./\   \  / |  ,   /  \   \  /'---'          ---`-'   \   \  /
  `---`   `--`---'     `----'   ---`-'    `----'                          `----'
*/
                Printes.menuPrincipal();
                System.out.println("--> Tipo da conta: paciente CISAM");
                System.out.println("--> Seu nome: " + usuario.getNome());
                System.out.println("--> Seu identificador único: " + usuario.getIdUser());
                Printes.linha();
                System.out.println("•[1] Log out de " + usuario.getNome());
                System.out.println("•[2] Seu prontuário eletrônico");
                System.out.println("•[3] Sua conta CISAM");
                System.out.println("•[4] Especialidades do CISAM");
                System.out.println("•[5] Serialização do usuário");
                System.out.println("•[6] Sair");
                Printes.linha();
                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                option = input.next();

                if (Objects.equals(option, "1")) { // •[p.1] Log out de
                    usuario = null;
                    logged = 'n';
                    Printes.espaco();
                    serializou = false;
                    break outer;
                } else if (Objects.equals(option, "2")) { // •[p.2] Seu PEP
                    Printes.espaco();
                    while (true) {
                        Printes.meuPEP();
                        System.out.println("•[1] Seus dados pessoais");
                        System.out.println("•[2] Seus meios de contato");
                        System.out.println("•[3] Seu endereço");
                        System.out.println("•[4] Dados de saúde");
                        System.out.println("•[5] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) { // •[p.2.1] Seus dados pessoais
                            Printes.espaco();
                            while (true) {
                                Printes.meuPEP();
                                System.out.println("•[1] Nome: " + usuario.getNome());
                                System.out.println("•[2] dataEhora.Data de nascimento: " + usuario.getDataDeNascimento());
                                System.out.println("•[3] CPF: " + usuario.getCpf());
                                System.out.println("•[4] Sexo: " + usuario.getSexoBiologico());
                                System.out.println("•[5] Nome da mãe: " + usuario.getNomeDaMae());
                                System.out.println("\n•[6] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[p.2.1.1] Nome:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU NOME, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setNome(option);
                                } else if (Objects.equals(option, "2")) { // •[p.2.1.2] dataEhora.Data de nascimento:
                                    System.out.println("COLOQUE APENAS OS NÚMEROS NA SEQUÊNCIA DIA 00, MÊS 00 E ANO 0000, OU DIGITE 1 PARA CANCELAR.");
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR SUA DATA DE NASCIMENTO: ");
                                    option = input.next();
                                    usuario.setDataDeNascimento(option);
                                } else if (Objects.equals(option, "3")) { // •[p.2.1.3] CPF:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR SEU CPF, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setCpf(option);
                                } else if (Objects.equals(option, "4")) { // •[p.2.1.4] Sexo biológico:
                                    System.out.print("•[4] <<< DIGITE PARA EDITAR SEU SEXO: 'H' ou 'M', OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setSexoBiologico(option.toLowerCase());
                                } else if (Objects.equals(option, "5")) { // •[p.2.1.5] Nome da mãe:
                                    System.out.print("•[5] <<< DIGITE PARA EDITAR O NOME DA SUA MÃE, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setNomeDaMae(option.toLowerCase());
                                } else if (Objects.equals(option, "6")) { // •[p.2.1.6] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "2")) { // •[p.2.2] Seus meios de contato
                            Printes.espaco();
                            while (true) {
                                Printes.meuPEP();
                                System.out.println("•[1] Telefone fixo: " + usuario.getTelefoneFixo());
                                System.out.println("•[2] Telefone móvel: " + usuario.getTelefoneCelular());
                                System.out.println("•[3] E-mail: " + usuario.getEmail());
                                System.out.println("\n•[4] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[p.2.2.1] Telefone fixo:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU TELEFONE FIXO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setTelefoneFixo(option);
                                } else if (Objects.equals(option, "2")) { // •[p.2.2.2] Telefone móvel:
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR SEU TELEFONE CELULAR, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setTelefoneCelular(option);
                                } else if (Objects.equals(option, "3")) { // •[p.2.2.3] E-mail:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR SEU E-MAIL, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setEmail(option.toLowerCase());
                                } else if (Objects.equals(option, "4")) { // •[p.2.2.4] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "3")) { // •[p.2.3] Seu endereço
                            Printes.espaco();
                            while (true) {
                                Printes.meuPEP();
                                System.out.println("•[1] País: " + usuario.getPais());
                                System.out.println("•[2] Estado: " + usuario.getEstado());
                                System.out.println("•[3] Cidade: " + usuario.getCidade());
                                System.out.println("•[4] CEP: " + usuario.getCep());
                                System.out.println("•[5] Bairro: " + usuario.getBairro());
                                System.out.println("•[6] Rua: " + usuario.getRua());
                                System.out.println("•[7] Complemento: " + usuario.getComplemento());
                                System.out.println("\n•[8] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[p.2.3.1] País:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU PAÍS, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setPais(option.toLowerCase());
                                } else if (Objects.equals(option, "2")) { // •[p.2.3.2] Estado:
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR O ESTADO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setEstado(option.toLowerCase());
                                } else if (Objects.equals(option, "3")) { // •[p.2.3.3] Cidade:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR A CIDADE, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setCidade(option.toLowerCase());
                                } else if (Objects.equals(option, "4")) { // •[p.2.3.4] CEP:
                                    System.out.print("•[4] <<< DIGITE PARA EDITAR O CEP, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setCep(option);
                                } else if (Objects.equals(option, "5")) { // •[p.2.3.5] Bairro:
                                    System.out.print("•[5] <<< DIGITE PARA EDITAR O BAIRRO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setBairro(option.toLowerCase());
                                } else if (Objects.equals(option, "6")) { // •[p.2.3.6] Rua:
                                    System.out.print("•[6] <<< DIGITE PARA EDITAR A RUA, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setRua(option.toLowerCase());
                                } else if (Objects.equals(option, "7")) { // •[p.2.3.7] Complemento:
                                    System.out.print("•[7] <<< DIGITE PARA EDITAR O COMPLEMENTO DA RUA, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setComplemento(option.toLowerCase());
                                } else if (Objects.equals(option, "8")) { // •[p.2.3.8] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "4")) { // •[p.2.4] Dados de saúde
                            Printes.espaco();
                            while (true) {

                                Printes.meuPEP();
                                if (verLista) {
                                    ((PacienteCISAM) usuario).listaDadosSaude(usuario.getIdUser());
                                    Printes.linha();
                                    System.out.println("•[1] Deixar de ver lista (alergias; medicações; cirurgias; doenças)");
                                } else {
                                    System.out.println("•[1] Ver lista (alergias; medicações; cirurgias; doenças)");
                                }

                                System.out.println("•[2] Adicionar item à lista");
                                System.out.println("•[3] Remover item da lista");
                                System.out.println("\n•[4] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[p.2.4.1] Ver lista
                                    if (verLista) {
                                        verLista = false;
                                    } else {
                                        verLista = true;
                                    }
                                    Printes.espaco();
                                } else if (Objects.equals(option, "2")) { // •[p.2.4.2] Adicionar item à lista
                                    if (verLista) {
                                        while (true) { //adicionarDado = {INICIO, FIM, DADO, DESCRIÇÃO}
                                            for (int i = 0; i <= 3; i++) {
                                                adicionarDado[i] = "";
                                            }
                                            //System.out.println("•COLOQUE NESSA SEQUÊNCIA: 1° DADO DE SAÚDE; 2° DATA DE INICIO; 3° DATA DE FIM (OPCIONAL); 4° DESCRIÇÃO (OPCIONAL).");
                                            System.out.println("•DIGITE 1 A QUALQUER MOMENTO PARA CANCELAR A ADIÇÃO.");
                                            System.out.println("•COLOQUE A DATA COM DIA 00, MÊS 00 E ANO 0000, POR EXEMPLO: 01012001. CASO COLOCADO ERRADO A DATA FICARÁ ZERADA.");
                                            System.out.print("•[2] <<< DIGITE O TIPO DO DADO (ALERGIA, MEDICAÇÃO, CIRURGIA, DOENÇA) QUE VOCÊ QUER COLOCAR: ");
                                            option = input.next();

                                            if (Objects.equals(option, "1")) {
                                                Printes.espaco();
                                                break;
                                            }
                                            adicionarDado[2] = option;

                                            System.out.print("•[2] <<< DIGITE A DATA DE INICIO DO SEU DADO DE SAÚDE: ");
                                            option = input.next();

                                            if (Objects.equals(option, "1")) {
                                                Printes.espaco();
                                                break;
                                            }
                                            adicionarDado[0] = option;

                                            System.out.print("•[2] <<< DIGITE A DATA DE FIM DO SEU DADO DE SAÚDE, OU DIGITE 2 CASO NÃO TENHA TERMINADO OU VOCÊ NÃO QUEIRA INSERIR NADA: ");
                                            option = input.next();
                                            input.nextLine();

                                            if (Objects.equals(option, "1")) {
                                                Printes.espaco();
                                                break;
                                            } else if (Objects.equals(option, "2")) {
                                                adicionarDado[1] = "";
                                            } else {
                                                adicionarDado[1] = option;
                                            }

                                            System.out.print("•[2] <<< DIGITE A DESCRIÇÃO DO SEU DADO DE SAÚDE, OU DIGITE 2 CASO NÃO TENHA TERMINADO OU VOCÊ NÃO QUEIRA INSERIR NADA: ");
                                            option = input.nextLine();

                                            if (Objects.equals(option, "1")) {
                                                Printes.espaco();
                                                break;
                                            } else if (Objects.equals(option, "2")) {
                                                adicionarDado[3] = "";
                                            } else {
                                                adicionarDado[3] = option;
                                            }

                                            ((PacienteCISAM) usuario).adicionarItemALista(usuario.getIdUser(), adicionarDado[0], adicionarDado[1], adicionarDado[2], adicionarDado[3], ((PacienteCISAM) usuario).getTamanhoLista());
                                            break;
                                        }
                                    } else {
                                        Printes.espaco();
                                        System.out.println("--> PRIMEIRO ABRA A LISTA.");
                                    }
                                } else if (Objects.equals(option, "3")) { // •[p.2.4.3] Remover item da lista
                                    if (verLista) {
                                        System.out.print("•[3] <<< DIGITE UMA LINHA PARA SER REMOVIDA, COMO A l001: ");
                                        option = input.next();
                                        ((PacienteCISAM) usuario).removerItemDaLista(option, usuario.getIdUser());
                                    } else {
                                        Printes.espaco();
                                        System.out.println("--> PRIMEIRO ABRA A LISTA.");
                                    }
                                } else if (Objects.equals(option, "4")) { // •[p.2.4.4] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "5")) { // •[p.2.5] Voltar
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }


                } else if (Objects.equals(option, "3")) { // •[p.3] Sua conta CISAM
                    Printes.espaco();
                    while (true) {
                        Printes.suaContaCisam();
                        System.out.println("--> Tipo da conta: paciente CISAM");
                        System.out.println("--> Seu nome: " + usuario.getNome());
                        System.out.println("--> Sua senha: " + usuario.getSenha());
                        System.out.println("--> Seu identificador único: " + usuario.getIdUser());
                        Printes.linha();
                        System.out.println("•[1] Mudar senha");
                        System.out.println("•[2] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) { // •[p.3.1] Mudar senha
                            System.out.print("•[1] <<< DIGITE UMA NOVA SENHA, OU DIGITE 1 PARA CANCELAR: ");
                            option = input.next();
                            usuario.setSenha(option);
                        } else if (Objects.equals(option, "2")) { // •[p.3.2] Voltar
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "4")) { // •[p.4] Especialidades do CISAM
                    Printes.espaco();
                    while (true) {
                        Printes.especialidadesCisam();
                        UserCISAM.mostrarListaEspecialidades();
                        Printes.linha();
                        System.out.println("•[1] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) {
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "5")) { // •[p.5] Serialização do usuário
                    Printes.espaco();
                    while (true) {
                        Printes.menuSerializacao();
                        if (serializou) {
                            usuario.desSerial(usuario);
                            Printes.linha();
                        }
                        System.out.println("[1] Serializar");
                        System.out.println("[2] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1") && !serializou) {  // •[p.5.1] Serializar
                            usuario.serializarUser(usuario);
                            serializou = true;
                        } else if (Objects.equals(option, "2")) {  // •[p.5.2] Voltar
                            Printes.espaco();
                            break;
                        } else if (Objects.equals(option, "1") && serializou) {
                            Printes.espaco();
                            System.out.println("--> VOCÊ JÁ SERIALIZOU.");
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }

                } else if (Objects.equals(option, "6")) { // •[p.6] Sair
                    if (serializou) {
                        break;
                    } else {
                        Printes.espaco();
                        System.out.println("--> SALVE SEU USUÁRIO PRIMEIRO.");
                    }
                } else {
                    Printes.espaco();
                    System.out.println("--> OPÇÃO INVÁLIDA.");
                }



            } else if (logged == 'm' && (usuario instanceof MedicoCISAM)) {
/*
          ____
        ,'  , `.
     ,-+-,.' _ |               ,---,  ,--,
  ,-+-. ;   , ||             ,---.'|,--.'|               ,---.
 ,--.'|'   |  ;|             |   | :|  |,               '   ,'\
|   |  ,', |  ':  ,---.      |   | |`--'_       ,---.  /   /   |
|   | /  | |  || /     \   ,--.__| |,' ,'|     /     \.   ; ,. :
'   | :  | :  |,/    /  | /   ,'   |'  | |    /    / ''   | |: :
;   . |  ; |--'.    ' / |.   '  /  ||  | :   .    ' / '   | .; :
|   : |  | ,   '   ;   /|'   ; |:  |'  : |__ '   ; :__|   :    |
|   : '  |/    '   |  / ||   | '/  '|  | '.'|'   | '.'|\   \  /
;   | |`-'     |   :    ||   :    :|;  :    ;|   :    : `----'
|   ;/          \   \  /  \   \  /  |  ,   /  \   \  /
'---'            `----'    `----'    ---`-'    `----'
                                                                 */
                Printes.menuPrincipal();
                System.out.println("--> Tipo da conta: médico CISAM");
                System.out.println("--> Seu nome: " + usuario.getNome());
                System.out.println("--> Seu identificador único: " + usuario.getIdUser());
                Printes.linha();
                System.out.println("•[1] Log out de " + usuario.getNome());
                System.out.println("•[2] Seus dados");
                System.out.println("•[3] Procurar usuário");
                System.out.println("•[4] Sua conta CISAM");
                System.out.println("•[5] Especialidades do CISAM");
                System.out.println("•[6] Modo beira-leito");
                System.out.println("•[7] Sair");
                Printes.linha();
                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                option = input.next();

                if (Objects.equals(option, "1")) { // •[m.1] Log out de
                    usuario = null;
                    logged = 'n';
                    Printes.espaco();
                    break outer;
                } else if (Objects.equals(option, "2")) { // •[m.2] Seus dados
                    Printes.espaco();
                    while (true) {
                        Printes.seusDados();
                        System.out.println("•[1] Seus dados pessoais");
                        System.out.println("•[2] Seus meios de contato");
                        System.out.println("•[3] Seu endereço");
                        System.out.println("•[4] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) { // •[m.2.1] Seus dados pessoais
                            Printes.espaco();
                            while (true) {
                                Printes.seusDados();
                                System.out.println("•[1] Nome: " + usuario.getNome());
                                System.out.println("•[2] dataEhora.Data de nascimento: " + usuario.getDataDeNascimento());
                                System.out.println("•[3] CPF: " + usuario.getCpf());
                                System.out.println("•[4] Sexo: " + usuario.getSexoBiologico());
                                System.out.println("•[5] Nome da mãe: " + usuario.getNomeDaMae());
                                System.out.println("\n•[6] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[g.2.1.1] Nome:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU NOME, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setNome(option.toLowerCase());
                                } else if (Objects.equals(option, "2")) { // •[g.2.1.2] dataEhora.Data de nascimento:
                                    System.out.println("COLOQUE APENAS OS NÚMEROS NA SEQUÊNCIA DIA 00, MÊS 00 E ANO 0000, OU DIGITE 1 PARA CANCELAR.");
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR SUA DATA DE NASCIMENTO: ");
                                    option = input.next();
                                    usuario.setDataDeNascimento(option);
                                } else if (Objects.equals(option, "3")) { // •[g.2.1.3] CPF:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR SEU CPF, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setCpf(option);
                                } else if (Objects.equals(option, "4")) { // •[g.2.1.4] Sexo biológico:
                                    System.out.print("•[4] <<< DIGITE PARA EDITAR SEU SEXO: 'H' ou 'M', OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setSexoBiologico(option.toLowerCase());
                                } else if (Objects.equals(option, "5")) { // •[g.2.1.5] Nome da mãe:
                                    System.out.print("•[5] <<< DIGITE PARA EDITAR O NOME DA SUA MÃE, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setNomeDaMae(option.toLowerCase());
                                } else if (Objects.equals(option, "6")) { // •[g.2.1.6] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "2")) { // •[m.2.2] Seus meios de contato
                            Printes.espaco();
                            while (true) {
                                Printes.seusDados();
                                System.out.println("•[1] Telefone fixo: " + usuario.getTelefoneFixo());
                                System.out.println("•[2] Telefone móvel: " + usuario.getTelefoneCelular());
                                System.out.println("•[3] E-mail: " + usuario.getEmail());
                                System.out.println("\n•[4] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[g.2.2.1] Telefone fixo:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU TELEFONE FIXO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setTelefoneFixo(option);
                                } else if (Objects.equals(option, "2")) { // •[g.2.2.2] Telefone móvel:
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR SEU TELEFONE CELULAR, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setTelefoneCelular(option);
                                } else if (Objects.equals(option, "3")) { // •[g.2.2.3] E-mail:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR SEU E-MAIL, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setEmail(option.toLowerCase());
                                } else if (Objects.equals(option, "4")) { // •[g.2.2.4] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "3")) { // •[m.2.3] Seu endereço
                            Printes.espaco();
                            while (true) {
                                Printes.meuPEP();
                                System.out.println("•[1] País: " + usuario.getPais());
                                System.out.println("•[2] Estado: " + usuario.getEstado());
                                System.out.println("•[3] Cidade: " + usuario.getCidade());
                                System.out.println("•[4] CEP: " + usuario.getCep());
                                System.out.println("•[5] Bairro: " + usuario.getBairro());
                                System.out.println("•[6] Rua: " + usuario.getRua());
                                System.out.println("•[7] Complemento: " + usuario.getComplemento());
                                System.out.println("\n•[8] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[g.2.3.1] País:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU PAÍS, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setPais(option.toLowerCase());
                                } else if (Objects.equals(option, "2")) { // •[g.2.3.2] Estado:
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR O ESTADO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setEstado(option.toLowerCase());
                                } else if (Objects.equals(option, "3")) { // •[g.2.3.3] Cidade:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR A CIDADE, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setCidade(option.toLowerCase());
                                } else if (Objects.equals(option, "4")) { // •[g.2.3.4] CEP:
                                    System.out.print("•[4] <<< DIGITE PARA EDITAR O CEP, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setCep(option);
                                } else if (Objects.equals(option, "5")) { // •[g.2.3.5] Bairro:
                                    System.out.print("•[5] <<< DIGITE PARA EDITAR O BAIRRO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setBairro(option.toLowerCase());
                                } else if (Objects.equals(option, "6")) { // •[g.2.3.6] Rua:
                                    System.out.print("•[6] <<< DIGITE PARA EDITAR A RUA, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setRua(option.toLowerCase());
                                } else if (Objects.equals(option, "7")) { // •[g.2.3.7] Complemento:
                                    System.out.print("•[7] <<< DIGITE PARA EDITAR O COMPLEMENTO DA RUA, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setComplemento(option.toLowerCase());
                                } else if (Objects.equals(option, "8")) { // •[g.2.3.8] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "4")) { // •[m.2.4] Voltar
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "3")) { // •[m.3] Procurar usuário
                    Printes.espaco();
                    passageiro = "";
                    while (true) {
                        Printes.procurarUsuario();
                        if (!Objects.equals(passageiro, "") && !Objects.equals(passageiro, "1")) {
                            System.out.printf("\n•ID SENDO BUSCADO: %s\n", passageiro);
                            ((MedicoCISAM) usuario).procurarUsuario(passageiro);
                            Printes.linha();
                        }
                        System.out.println("•[1] Inserir ID para busca");
                        System.out.println("•[2] Gerar arquivo .txt com os dados");
                        System.out.println("•[3] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();
                        input.nextLine();

                        if (Objects.equals(option, "1")) {
                            System.out.print("•[1] <<< DIGITE O ID (000000) PARA BUSCA, OU DIGITE 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            Printes.espaco();
                        } else if (Objects.equals(option, "2")) {
                            System.out.print("•[2] <<< ESCREVA CASO QUEIRA ADICIONAR UMA DESCRIÇÃO NO ARQUIVO, DIGITE 1 PARA NÃO COLOCAR DESCRIÇÃO E DIGITE 2 PARA CANCELAR: ");
                            option = input.nextLine();
                            if (!Objects.equals(option, "2")) {
                                ((MedicoCISAM) usuario).gerarTxt(passageiro, option); // id, descrição
                            } else {
                                Printes.espaco();
                            }
                        } else if (Objects.equals(option, "3")) {
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "4")) { // •[m.4] Sua conta CISAM
                    Printes.espaco();
                    while (true) {
                        Printes.suaContaCisam();
                        System.out.println("--> Tipo da conta: médico CISAM");
                        System.out.println("--> Seu nome: " + usuario.getNome());
                        System.out.println("--> Sua senha: " + usuario.getSenha());
                        System.out.println("--> Seu identificador único: " + usuario.getIdUser());
                        Printes.linha();
                        System.out.println("•[1] Mudar senha");
                        System.out.println("•[2] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) { // •[p.3.1] Mudar senha
                            System.out.print("•[1] <<< DIGITE UMA NOVA SENHA, OU DIGITE 1 PARA CANCELAR: ");
                            option = input.next();
                            usuario.setSenha(option);
                        } else if (Objects.equals(option, "2")) { // •[p.3.2] Voltar
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "5")) { // •[m.5] Especialidades do CISAM
                    Printes.espaco();
                    while (true) {
                        Printes.especialidadesCisam();
                        UserCISAM.mostrarListaEspecialidades();
                        Printes.linha();
                        System.out.println("•[1] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) {
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "6")) { // •[m.6] Modo beira-leito
                    Printes.espaco();
                    while (true) {
                        Printes.menuBeiraLeito();
                        System.out.println("•[1] Insira o ID do paciente");
                        System.out.println("•[2] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) { // •[m.6.1] Insira o ID do paciente
                            System.out.print("•[1] <<< INSIRA O ID DO PACIENTE, OU DIGITE 1 PARA CANCELAR: ");
                            idBeiraLeito = input.next();
                            if (!Objects.equals(idBeiraLeito, "1")) {
                                if (MedicoCISAM.checarIdPaciente(idBeiraLeito)) {
                                    passageiro = "";
                                    Printes.espaco();
                                    while (true) {
                                        Printes.menuBeiraLeito();
                                        MedicoCISAM.modoBeiraLeito2(idBeiraLeito);
                                        System.out.println("\n•Descrição do médico: " + passageiro);
                                        Printes.linha();
                                        System.out.println("•[1] Colocar descrição");
                                        System.out.println("•[2] Voltar");
                                        Printes.linha();
                                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                        option = input.next();
                                        input.nextLine();

                                        if (Objects.equals(option, "1")) {
                                            System.out.print("•[1] <<< DIGITE UMA DESCRIÇÃO, OU DIGITE 1 PARA CANCELAR, OU 2 PARA APAGAR A DESCRIÇÃO: ");
                                            option = input.nextLine();
                                            if (Objects.equals(option, "1")) {
                                                Printes.espaco();
                                            } else if (Objects.equals(option, "2")) {
                                                passageiro = "";
                                                Printes.espaco();
                                            } else {
                                                passageiro = option;
                                                Printes.espaco();
                                            }
                                        } else if (Objects.equals(option, "2")) {
                                            Printes.espaco();
                                            break;
                                        } else {
                                            Printes.espaco();
                                            System.out.println("--> OPÇÃO INVÁLIDA.");
                                        }

                                    }
                                }
                            } else {
                                Printes.espaco();
                            }

                        } else if (Objects.equals(option, "2")) { // •[m.6.2] Voltar
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "7")) { // •[m.7] Sair
                    break;
                } else {
                    Printes.espaco();
                    System.out.println("--> OPÇÃO INVÁLIDA.");
                }


            } else if (logged == 'g' && (usuario instanceof GestorCISAM)) {
/*

  ,----..                           ___
 /   /   \                        ,--.'|_
|   :     :                       |  | :,'   ,---.    __  ,-.
.   |  ;. /             .--.--.   :  : ' :  '   ,'\ ,' ,'/ /|
.   ; /--`     ,---.   /  /    '.;__,'  /  /   /   |'  | |' |
;   | ;  __   /     \ |  :  /`./|  |   |  .   ; ,. :|  |   ,'
|   : |.' .' /    /  ||  :  ;_  :__,'| :  '   | |: :'  :  /
.   | '_.' :.    ' / | \  \    `. '  : |__'   | .; :|  | '
'   ; : \  |'   ;   /|  `----.   \|  | '.'|   :    |;  : |
'   | '/  .''   |  / | /  /`--'  /;  :    ;\   \  / |  , ;
|   :    /  |   :    |'--'.     / |  ,   /  `----'   ---'
 \   \ .'    \   \  /   `--'---'   ---`-'
  `---`       `----'
                                                              */
                Printes.menuPrincipal();
                System.out.println("--> Tipo da conta: gestor CISAM");
                System.out.println("--> Seu nome: " + usuario.getNome());
                System.out.println("--> Seu identificador único: " + usuario.getIdUser());
                Printes.linha();
                System.out.println("•[1] Log out de " + usuario.getNome());
                System.out.println("•[2] Seus dados");
                System.out.println("•[3] Procurar usuário");
                System.out.println("•[4] Sua conta CISAM");
                System.out.println("•[5] Especialidades do CISAM");
                System.out.println("•[6] Cadastrar novo médico");
                System.out.println("•[7] Serializações feitas");
                System.out.println("•[8] Sair");
                Printes.linha();
                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                option = input.next();

                if (Objects.equals(option, "1")) { // •[g.1] Log out de
                    usuario = null;
                    logged = 'n';
                    Printes.espaco();
                    break outer;
                } else if (Objects.equals(option, "2")) { // •[g.2] Seus dados
                    Printes.espaco();
                    while (true) {
                        Printes.seusDados();
                        System.out.println("•[1] Seus dados pessoais");
                        System.out.println("•[2] Seus meios de contato");
                        System.out.println("•[3] Seu endereço");
                        System.out.println("•[4] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) { // •[g.2.1] Seus dados pessoais
                            Printes.espaco();
                            while (true) {
                                Printes.seusDados();
                                System.out.println("•[1] Nome: " + usuario.getNome());
                                System.out.println("•[2] Data de nascimento: " + usuario.getDataDeNascimento());
                                System.out.println("•[3] CPF: " + usuario.getCpf());
                                System.out.println("•[4] Sexo: " + usuario.getSexoBiologico());
                                System.out.println("•[5] Nome da mãe: " + usuario.getNomeDaMae());
                                System.out.println("\n•[6] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[g.2.1.1] Nome:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU NOME, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setNome(option.toLowerCase());
                                } else if (Objects.equals(option, "2")) { // •[g.2.1.2] dataEhora.Data de nascimento:
                                    System.out.println("COLOQUE APENAS OS NÚMEROS NA SEQUÊNCIA DIA 00, MÊS 00 E ANO 0000, OU DIGITE 1 PARA CANCELAR.");
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR SUA DATA DE NASCIMENTO: ");
                                    option = input.next();
                                    usuario.setDataDeNascimento(option);
                                } else if (Objects.equals(option, "3")) { // •[g.2.1.3] CPF:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR SEU CPF, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setCpf(option);
                                } else if (Objects.equals(option, "4")) { // •[g.2.1.4] Sexo biológico:
                                    System.out.print("•[4] <<< DIGITE PARA EDITAR SEU SEXO: 'H' ou 'M', OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setSexoBiologico(option.toLowerCase());
                                } else if (Objects.equals(option, "5")) { // •[g.2.1.5] Nome da mãe:
                                    System.out.print("•[5] <<< DIGITE PARA EDITAR O NOME DA SUA MÃE, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setNomeDaMae(option.toLowerCase());
                                } else if (Objects.equals(option, "6")) { // •[g.2.1.6] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "2")) { // •[g.2.2] Seus meios de contato
                            Printes.espaco();
                            while (true) {
                                Printes.seusDados();
                                System.out.println("•[1] Telefone fixo: " + usuario.getTelefoneFixo());
                                System.out.println("•[2] Telefone móvel: " + usuario.getTelefoneCelular());
                                System.out.println("•[3] E-mail: " + usuario.getEmail());
                                System.out.println("\n•[4] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[g.2.2.1] Telefone fixo:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU TELEFONE FIXO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setTelefoneFixo(option);
                                } else if (Objects.equals(option, "2")) { // •[g.2.2.2] Telefone móvel:
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR SEU TELEFONE CELULAR, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setTelefoneCelular(option);
                                } else if (Objects.equals(option, "3")) { // •[g.2.2.3] E-mail:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR SEU E-MAIL, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setEmail(option.toLowerCase());
                                } else if (Objects.equals(option, "4")) { // •[g.2.2.4] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "3")) { // •[g.2.3] Seu endereço
                            Printes.espaco();
                            while (true) {
                                Printes.meuPEP();
                                System.out.println("•[1] País: " + usuario.getPais());
                                System.out.println("•[2] Estado: " + usuario.getEstado());
                                System.out.println("•[3] Cidade: " + usuario.getCidade());
                                System.out.println("•[4] CEP: " + usuario.getCep());
                                System.out.println("•[5] Bairro: " + usuario.getBairro());
                                System.out.println("•[6] Rua: " + usuario.getRua());
                                System.out.println("•[7] Complemento: " + usuario.getComplemento());
                                System.out.println("\n•[8] Voltar");
                                Printes.linha();
                                System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                                option = input.next();
                                input.nextLine();

                                if (Objects.equals(option, "1")) { // •[g.2.3.1] País:
                                    System.out.print("•[1] <<< DIGITE PARA EDITAR SEU PAÍS, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setPais(option.toLowerCase());
                                } else if (Objects.equals(option, "2")) { // •[g.2.3.2] Estado:
                                    System.out.print("•[2] <<< DIGITE PARA EDITAR O ESTADO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setEstado(option.toLowerCase());
                                } else if (Objects.equals(option, "3")) { // •[g.2.3.3] Cidade:
                                    System.out.print("•[3] <<< DIGITE PARA EDITAR A CIDADE, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setCidade(option.toLowerCase());
                                } else if (Objects.equals(option, "4")) { // •[g.2.3.4] CEP:
                                    System.out.print("•[4] <<< DIGITE PARA EDITAR O CEP, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.next();
                                    usuario.setCep(option);
                                } else if (Objects.equals(option, "5")) { // •[g.2.3.5] Bairro:
                                    System.out.print("•[5] <<< DIGITE PARA EDITAR O BAIRRO, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setBairro(option.toLowerCase());
                                } else if (Objects.equals(option, "6")) { // •[g.2.3.6] Rua:
                                    System.out.print("•[6] <<< DIGITE PARA EDITAR A RUA, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setRua(option.toLowerCase());
                                } else if (Objects.equals(option, "7")) { // •[g.2.3.7] Complemento:
                                    System.out.print("•[7] <<< DIGITE PARA EDITAR O COMPLEMENTO DA RUA, OU DIGITE 1 PARA CANCELAR: ");
                                    option = input.nextLine();
                                    usuario.setComplemento(option.toLowerCase());
                                } else if (Objects.equals(option, "8")) { // •[g.2.3.8] Voltar
                                    Printes.espaco();
                                    break;
                                } else {
                                    Printes.espaco();
                                    System.out.println("--> OPÇÃO INVÁLIDA.");
                                }
                            }
                        } else if (Objects.equals(option, "4")) { // •[g.2.4] Voltar
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "3")) { // •[g.3] Procurar usuário
                    Printes.espaco();
                    passageiro = "";
                    while (true) {
                        Printes.procurarUsuario();
                        if (!Objects.equals(passageiro, "") && !Objects.equals(passageiro, "1")) {
                            System.out.printf("\n•ID SENDO BUSCADO: %s\n", passageiro);
                            ((GestorCISAM) usuario).procurarUsuario(passageiro);
                            Printes.linha();
                        }
                        System.out.println("•[1] Inserir ID para busca");
                        System.out.println("•[2] Gerar arquivo .txt com os dados");
                        System.out.println("•[3] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();
                        input.nextLine();

                        if (Objects.equals(option, "1")) {
                            System.out.print("•[1] <<< DIGITE O ID (000000) PARA BUSCA, OU DIGITE 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            Printes.espaco();
                        } else if (Objects.equals(option, "2")) {
                            System.out.print("•[2] <<< ESCREVA CASO QUEIRA ADICIONAR UMA DESCRIÇÃO NO ARQUIVO, DIGITE 1 PARA NÃO COLOCAR DESCRIÇÃO E DIGITE 2 PARA CANCELAR: ");
                            option = input.nextLine();
                            if (!Objects.equals(option, "2")) {
                                ((GestorCISAM) usuario).gerarTxt(passageiro, option); // id, descrição
                            } else {
                                Printes.espaco();
                            }
                        } else if (Objects.equals(option, "3")) {
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "4")) { // •[g.4] Sua conta CISAM
                    Printes.espaco();
                    while (true) {
                        Printes.suaContaCisam();
                        System.out.println("--> Tipo da conta: gestor CISAM");
                        System.out.println("--> Seu nome: " + usuario.getNome());
                        System.out.println("--> Sua senha: " + usuario.getSenha());
                        System.out.println("--> Seu identificador único: " + usuario.getIdUser());
                        Printes.linha();
                        System.out.println("•[1] Mudar senha");
                        System.out.println("•[2] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) { // •[p.3.1] Mudar senha
                            System.out.print("•[1] <<< DIGITE UMA NOVA SENHA, OU DIGITE 1 PARA CANCELAR: ");
                            option = input.next();
                            usuario.setSenha(option);
                        } else if (Objects.equals(option, "2")) { // •[p.3.2] Voltar
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "5")) { // •[g.5] Especialidades do CISAM
                    Printes.espaco();
                    while (true) {
                        Printes.especialidadesCisam();
                        UserCISAM.mostrarListaEspecialidades();
                        Printes.linha();
                        System.out.println("•[1] Adicionar especialidade");
                        System.out.println("•[2] Tirar especialidade");
                        System.out.println("•[3] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();
                        input.nextLine();

                        if (Objects.equals(option, "1")) {
                            System.out.print("•[1] <<< DIGITE A ESPECIALIDADE QUE VOCÊ QUER ADICIONAR, OU DIGITE 1 PARA CANCELAR A ADIÇÃO: ");
                            option = input.nextLine();
                            if (!Objects.equals(option, "1")) {
                                System.out.print("•[1] <<< DIGITE A QUANTIDADE NÚMERICA DE MÉDICOS QUE ESSA ESPECIALIDADE POSSUI, OU DIGITE 0 PARA NÃO ADICIONAR, OU DIGITE 1 PARA CANCELAR A ADIÇÃO: ");
                                qtdMedicos = input.nextLine();

                                if (!Objects.equals(option, "1") && !Objects.equals(qtdMedicos, "1")) {
                                    if (Objects.equals(qtdMedicos, "0")) {
                                        GestorCISAM.adicionarEspecialidade(option.toUpperCase());
                                    } else {
                                        GestorCISAM.adicionarEspecialidade(option.toUpperCase(), qtdMedicos);
                                    }

                                } else {
                                    Printes.espaco();
                                }
                            } else {
                                Printes.espaco();
                            }
                        } else if (Objects.equals(option, "2")) {
                            System.out.print("•[2] <<< DIGITE A ESPECIALIDADE QUE VOCÊ QUER TIRAR, OU DIGITE 1 PARA CANCELAR A RETIRADA: ");
                            option = input.nextLine();
                            GestorCISAM.excluirEspecialidade(option.toUpperCase());
                        } else if (Objects.equals(option, "3")) {
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                } else if (Objects.equals(option, "6")) { // •[g.6] Cadastrar novo médico
                    Printes.espaco();
                    criarConta[0] = "";
                    criarConta[1] = "";
                    criarConta[2] = "";
                    criarConta[3] = "";
                    criarConta[4] = "";
                    while (true) {
                        Printes.cadastrarMedico();
                        System.out.println("•[1] Digite o nome completo: " + criarConta[0].toLowerCase());
                        System.out.println("•[2] Digite o CPF: " + criarConta[1]);
                        System.out.println("•[3] Digite o E-mail: " + criarConta[2]);
                        System.out.println("•[4] Digite uma senha: " + criarConta[3]);
                        System.out.println("•[5] Confirme a senha: " + criarConta[4]);
                        System.out.println("\n•[6] Criar conta");
                        System.out.println("•[7] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();
                        input.nextLine();

                        if (Objects.equals(option, "1")) { // •[g.6.1] Digite o nome completo:
                            System.out.print("•[1] <<< DIGITE SEU NOME, OU 1 PARA CANCELAR: ");
                            passageiro = input.nextLine();
                            criarConta[0] = Logar.checarNome(passageiro.toLowerCase(), criarConta[0].toLowerCase());
                        } else if (Objects.equals(option, "2")) { // •[g.6.2] Digite o CPF:
                            System.out.print("•[2] <<< DIGITE APENAS OS NÚMEROS DO CPF, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[1] = Logar.checarCPF(passageiro, criarConta[1]);
                        } else if (Objects.equals(option, "3")) { // •[g.6.3] Digite o E-mail:
                            System.out.print("•[3] <<< DIGITE SEU E-MAIL, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[2] = Logar.checarEmail(passageiro.toLowerCase(), criarConta[2].toLowerCase());
                        } else if (Objects.equals(option, "4")) { // •[g.6.4] Digite uma senha:
                            System.out.print("•[4] <<< DIGITE SUA SENHA, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[3] = Logar.checarSenha(passageiro, criarConta[3]);
                        } else if (Objects.equals(option, "5")) { // •[g.6.5] Confirme a senha:
                            System.out.print("•[5] <<< CONFIRME SUA SENHA, OU 1 PARA CANCELAR: ");
                            passageiro = input.next();
                            input.nextLine();
                            criarConta[4] = Logar.checarSenha2(criarConta[3], passageiro, criarConta[4]);
                        } else if (Objects.equals(option, "6")) { // •[g.6.6] Criar conta
                            if (Objects.equals(GestorCISAM.cadastrarmedico(criarConta[0], criarConta[1], criarConta[2], criarConta[3], criarConta[4]), criarConta[1])) {
                                Printes.espaco();
                                System.out.println("--> CONTA CRIADA COM SUCESSO.");
                                break;
                            }
                        } else if (Objects.equals(option, "7")) { // •[g.6.7] Voltar
                            Printes.espaco();
                            criarConta[0] = "";
                            criarConta[1] = "00000000000";
                            criarConta[2] = "";
                            criarConta[3] = "";
                            criarConta[4] = "";
                            break outer;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }


                    }

                } else if (Objects.equals(option, "7")) { // •[g.7] Serializações feitas
                    Printes.espaco();
                    while (true) {
                        Printes.menuSerialFeito();
                        GestorCISAM.serializacoesFeitas();
                        System.out.println("•[1] Remover usuário");
                        System.out.println("•[2] Voltar");
                        Printes.linha();
                        System.out.print("•[?] <<< DIGITE UMA OPÇÃO: ");
                        option = input.next();

                        if (Objects.equals(option, "1")) {
                            System.out.print("•[1] <<< DIGITE O ID CONTIDO NO ARQUIVO PARA EXCLUIR, OU DIGITE 1 PARA CANCELAR: ");
                            option = input.next();
                            if (!Objects.equals(option, "1")) {
                                GestorCISAM.excluirSerial(option);
                            } else {
                                Printes.espaco();
                            }
                        } else if (Objects.equals(option, "2")) {
                            Printes.espaco();
                            break;
                        } else {
                            Printes.espaco();
                            System.out.println("--> OPÇÃO INVÁLIDA.");
                        }
                    }
                    // serializacoesFeitas
                } else if (Objects.equals(option, "8")) { // •[g.8] Sair
                    break;
                } else {
                    Printes.espaco();
                    System.out.println("--> OPÇÃO INVÁLIDA.");
                }
            }
        }
    }
}