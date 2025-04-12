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

  public static boolean encontrar_num_quarto(List<Quarto> quartos, int num_quarto) {
    for (Quarto a : quartos) {
      if (num_quarto == a.getNum_quarto())
        return true;
    }
    return false;
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
    return !(saida.isBefore(checkin) || entrada.isAfter(checkout));
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
          int num_quarto, tipo_quarto;
          System.out.println("Digite o numero do quarto:");
          num_quarto = scan.nextInt();
          if (encontrar_num_quarto(quartos, num_quarto)) {
            System.out.println("Numero de quarto ja existe");
            break;
          }
          System.out.println("Digite o codigo do tipo do quarto:");
          tipo_quarto = scan.nextInt();
          if (tipo_quarto < 1 || tipo_quarto > 3) {
            System.out.println("Código de tipo de quarto invalido");
            break;
          }
          quartos.add(new Quarto(num_quarto, tipo_quarto));
          System.err.println("Quarto cadastrado com sucesso");
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
          nome_hospede = scan.next();
          while (true) {
            System.out.println("Digite a data do check-in: ");
            dataStr = scan.next();
            checkin = formata_data(dataStr);
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
            tipo_quarto = scan.nextInt();
            if (!tipos.contains(tipo_quarto)) {
              System.out.println("Opção invalida");
              continue;
            }
            reservas
                .add(new Reserva(nome_hospede, checkin, checkout, seletor_quartos_livres(quartos_livres, tipo_quarto)));
            break;
          }
        }
        case 4 -> {
          for (Reserva a : reservas)
            a.printar_reserva();
        }
        case 5 -> {
          String nome_hospede;
          System.out.println("Digite o nome da reserva: ");
          nome_hospede = scan.next();
          for (Reserva a : reservas) {
            if (a.getNome_hospede().equals(nome_hospede)) {
              System.out.println("Reserva:");
              a.printar_reserva();
              System.out.println("Check-in feito com sucesso");
              for (Quarto x : quartos) {
                if (x.getNum_quarto() == a.getQuarto().getNum_quarto()) {
                  x.mudar_ocupado();
                }
              }
            }
          }
        }
        case 6 -> {
          String nome_hospede;
          System.out.println("Digite o nome da reserva: ");
          nome_hospede = scan.next();
          for (Reserva a : reservas) {
            if (a.getNome_hospede().equals(nome_hospede)) {
              System.out.println("Reserva:");
              a.printar_reserva();
              System.out.println("Check-out feito com sucesso");
              for (Quarto x : quartos) {
                if (x.getNum_quarto() == a.getQuarto().getNum_quarto()) {
                  x.mudar_livre();
                }
              }
            }
          }
        }
      }
    }
  }
}