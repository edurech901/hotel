import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {
  public static List<Quarto> quartos() {
    List<Quarto> quartos = new ArrayList<>();

    quartos.add(new Quarto(101, 1));
    quartos.add(new Quarto(102, 2));
    quartos.add(new Quarto(201, 1));
    quartos.add(new Quarto(202, 3));
    quartos.add(new Quarto(301, 2));
    quartos.add(new Quarto(302, 3));
    return quartos;
  }

  public static int escolher_num_quarto(List<Quarto> quartos_livres, int andar) {
    int maior_quarto = andar * 100;
    for (Quarto a : quartos_livres) {
      if (a.getNum_quarto() / 100 == andar) {
        maior_quarto = a.getNum_quarto();
      }
    }
    return maior_quarto + 1;
  }

  public static LocalDate formata_data(String dataStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try {
      LocalDate data = LocalDate.parse(dataStr, formatter);
      return data;
    } catch (Exception e) {
      System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
    }
    return null;
  }

  public static boolean conflito_datas(LocalDate entrada, LocalDate saida, LocalDate checkin, LocalDate checkout) {
    return (entrada.isBefore(checkout) && saida.isAfter(checkin));
  }

  public static List<Quarto> quartos_livres(List<Quarto> quartos, List<Reserva> reservas, LocalDate entrada,
      LocalDate saida) {
    List<Quarto> quartos_livres = new ArrayList<>();
    for (Quarto a : quartos) {
      boolean disponivel = true;
      for (Reserva b : reservas) {
        if (b.getQuarto().getNum_quarto() == a.getNum_quarto()) {
          if (conflito_datas(entrada, saida, b.getCheckin(), b.getCheckout())) {
            disponivel = false;
          }
        }
      }
      if (disponivel) {
        quartos_livres.add(a);
      }
    }
    return quartos_livres;
  }

  public static List<Integer> tipos_quartos_livres(List<Quarto> quartos_livres) {
    int solteiro = 0, casal = 0, casal_solteiro = 0;
    List<Integer> tipos = new ArrayList<>();
    for (Quarto a : quartos_livres) {
      switch (a.getTipo_quarto()) {
        case 1 -> solteiro += 1;
        case 2 -> casal += 1;
        case 3 -> casal_solteiro += 1;
      }
    }
    if (solteiro > 0) {
      System.out.println("1 - Solteiro - Diaria: R$200,00");
      tipos.add(1);
    }
    if (casal > 0) {
      System.out.println("2 - Casal - Diaria: R$350,00");
      tipos.add(2);
    }
    if (casal_solteiro > 0) {
      System.out.println("3 - Casal e Solteiro - Diaria: R$500,00");
      tipos.add(3);
    }
    return tipos;
  }

  public static Quarto seletor_quartos_livres(List<Quarto> quartos_livres, int x) {
    for (Quarto a : quartos_livres) {
      if (a.getTipo_quarto() == x) {
        return a;
      }
    }
    return null;
  }

  public static boolean saida_menor_entrada(LocalDate saida, LocalDate entrada) {
    return saida.isBefore(entrada) /* || saida.isEqual(entrada) */ ; // foi usado apenas a data de saida menor (e não
                                                                     // menor ou igual) a data de entrada para poder
                                                                     // testar a função de checkout
  }

  public static Reserva buscar_reserva_hoje(List<Reserva> reservas, String nome_hospede) {
    for (Reserva a : reservas) {
      if (a.getHospede().getNome().contains(nome_hospede) && a.getCheckin().isEqual(LocalDate.now())) {
        return a;
      }
    }
    return null;
  }

  public static boolean validarCPF(String cpf) {
    return cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
  }

  public static boolean validarTelefone(String tel) {
    return tel.matches("^\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}$");
  }

  public static boolean validarEmail(String email) {
    return email.matches("^[\\w.-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
  }

  public static void main(String[] args) {

    List<Quarto> quartos = quartos();
    List<Reserva> reservas = new ArrayList<>();

    while (true) {
      @SuppressWarnings("resource")
      Scanner scan = new Scanner(System.in);
      int op;
      System.out.println("\n-------- MENU ---------");
      System.out.println("1  -- Cadastrar quarto");
      System.out.println("2  -- Lista de quartos");
      System.out.println("3  -- Fazer reserva");
      System.out.println("4  -- Lista de reservas");
      System.out.println("5  -- Fazer check-in");
      System.out.println("6  -- Fazer check-out");
      System.out.println("0  -- Sair\n");
      System.out.println("Escolha a opção:");
      try {
        op = scan.nextInt();
      } catch (Exception e) {
        System.err.println("Opção inválida!");
        continue;
      }
      switch (op) {
        default ->
          System.err.println("Opção inválida!");
        case 0 -> {
          scan.close();
          return;
        }
        case 1 -> {
          int andar_quarto, num_quarto, tipo_quarto;
          System.out.println("Digite o andar do quarto:");

          try {
            andar_quarto = scan.nextInt();
          } catch (Exception e) {
            System.err.println("Andar inválido!");
            continue;
          }
          num_quarto = escolher_num_quarto(quartos, andar_quarto);
          System.out.println("Escolha o tipo do quarto:");
          try {
            System.out.println("1 - Solteiro - Diaria: R$200,00");
            System.out.println("2 - Casal - Diaria: R$350,00");
            System.out.println("3 - Casal e Solteiro - Diaria: R$500,00");
            tipo_quarto = scan.nextInt();
          } catch (Exception e) {
            System.err.println("Tipo de quarto inválido!");
            continue;
          }
          if (tipo_quarto < 1 || tipo_quarto > 3) {
            System.out.println("Código de tipo de quarto invalido");
            break;
          }
          quartos.add(new Quarto(num_quarto, tipo_quarto));
          System.out.println("Quarto cadastrado com sucesso");
        }
        case 2 -> {
          for (Quarto quarto : quartos) {
            quarto.printar_quarto();
          }
        }
        case 3 -> {
          int tipo_quarto;
          List<Integer> tipos;
          String nome_hospede, dataStr;
          LocalDate checkin, checkout;
          System.out.println("Digite o nome da reserva: ");
          List<Quarto> quartos_livres;
          scan.nextLine(); // usado para resolver o problema da quebra de linha
          nome_hospede = scan.nextLine();
          Hospede hospede = new Hospede(nome_hospede);
          while (true) {
            System.out.println("Digite a data do check-in: ");
            dataStr = scan.next();
            checkin = formata_data(dataStr);
            if (checkin == null)
              continue;
            if (checkin.isBefore(LocalDate.now())) {
              System.out.println("Check-in não pode ser em data passada.");
              continue;
            }
            break;
          }
          while (true) {
            System.out.println("Digite a data do check-out: ");
            dataStr = scan.next();
            checkout = formata_data(dataStr);
            if (checkout == null)
              continue;
            if (saida_menor_entrada(checkout, checkin)) {
              System.out.println("Data de checkout não pode ser menor que a data de entrada");
              continue;
            }
            break;
          }
          while (true) {
            quartos_livres = quartos_livres(quartos, reservas, checkin, checkout);
            if (quartos_livres.isEmpty()) {
              System.out.println("Nenhum quarto livre nesse periodo.");
              break;
            }
            tipos = tipos_quartos_livres(quartos_livres);
            try {
              tipo_quarto = Integer.parseInt(scan.next());
            } catch (NumberFormatException e) {
              System.out.println("Entrada inválida.");
              continue;
            }
            if (!tipos.contains(tipo_quarto)) {
              System.out.println("Opção invalida");
              continue;
            }
            reservas
                .add(new Reserva(hospede, checkin, checkout, seletor_quartos_livres(quartos_livres, tipo_quarto)));
            System.out.println("Reserva feita com sucesso");
            break;
          }
        }
        case 4 -> {
          for (Reserva a : reservas) {
            a.printar_reserva();
            System.out.println();
          }
        }
        case 5 -> {
          String nome_hospede, cpf, tel, email;
          System.out.println("Digite o nome da reserva: ");
          nome_hospede = scan.next();
          Reserva reserva = buscar_reserva_hoje(reservas, nome_hospede);
          if (reserva == null) {
            System.out.println("Reserva não encontrada.");
            break;
          }
          Hospede hospede = reserva.getHospede(); // pega o real
          if (!hospede.getCadastrado()) {
            System.out.println("Finalize o cadastro:");
            while (true) {
              System.out.print("CPF (formato 000.000.000-00): ");
              cpf = scan.next();
              if (validarCPF(cpf))
                break;
              System.out.println("CPF inválido!");
            }

            // Telefone
            while (true) {
              System.out.print("Telefone (formato (99)99999-9999): ");
              tel = scan.next();
              if (validarTelefone(tel))
                break;
              System.out.println("Telefone inválido!");
            }

            // Email
            while (true) {
              System.out.print("Email: ");
              email = scan.next();
              if (validarEmail(email))
                break;
              System.out.println("Email inválido!");
            }

            hospede.finalizar_cadastro(cpf, tel, email);
            System.out.println("Cadastro finalizado!");
          } else {
            System.out.println("Hospede ja cadastrado");
            hospede.printar_hospede();
          }
          System.out.println("Deseja realizar o check-in? (S/N)");
          String check = scan.next();
          switch (check.toUpperCase()) {
            case "S" -> {
              reserva.setStatus(1);
              System.out.println("Check-in realizado com sucesso");
              System.out.println("Quarto: " + reserva.getQuarto().getNum_quarto());
            }
            case "N" -> {
              break;
            }
            default -> System.out.println("Opção inválida");
          }
        }
        case 6 -> {
          String nome_hospede;
          System.out.println("Digite o nome da reserva: ");
          nome_hospede = scan.next();
          Reserva reserva = buscar_reserva_hoje(reservas, nome_hospede);
          if (reserva == null) {
            System.out.println("Reserva não encontrada.");
            break;
          }
          Hospede hospede = reserva.getHospede(); // pega o real
          if (!hospede.getCadastrado()) {
            System.out.println("Finalize o cadastro:");
            System.out.print("CPF: ");
            String cpf = scan.next();
            System.out.print("Telefone: ");
            String tel = scan.next();
            System.out.print("Email: ");
            String email = scan.next();

            hospede.finalizar_cadastro(cpf, tel, email);
            System.out.println("Cadastro finalizado!");
          } else {
            System.out.println("Hospede ja cadastrado");
            hospede.printar_hospede();
          }
          System.out.println("Deseja realizar o check-out? (S/N)");
          String check = scan.next();
          switch (check.toUpperCase()) {
            case "S" -> {
              reserva.setStatus(1);
              System.out.println("Check-out realizado com sucesso");
              System.out.println("Quarto: " + reserva.getQuarto().getNum_quarto());
            }
            case "N" -> {
              break;
            }
            default -> System.out.println("Opção inválida");
          }
        }
      }
    }
  }
}