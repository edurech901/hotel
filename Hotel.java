import java.util.ArrayList;
import java.util.Date;
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

  @SuppressWarnings("deprecation")
  public static void main(String[] args) {

    List<Quarto> quartos = quartos();
    List<Reserva> reservas = new ArrayList<>();

    while (true) {
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
      op = scan.nextInt();

      switch (op) {
        case 0 -> {
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
          String nome_hospede;
          int dia, mes, ano;
          Date checkin, checkout;
          List<Quarto> quartos_reservados = new ArrayList<>();
          System.out.println("Digite o nome da reserva: ");
          nome_hospede = scan.next();
          System.out.println("Digite o dia do check-in: ");
          dia = scan.nextInt();
          System.out.println("Digite o mes do check-in: ");
          mes = scan.nextInt();
          System.out.println("Digite o ano do check-in: ");
          ano = scan.nextInt();
          checkin = new Date(ano, mes, dia);
          System.out.println("Digite o dia do check-out: ");
          dia = scan.nextInt();
          System.out.println("Digite o mes do check-out: ");
          mes = scan.nextInt();
          System.out.println("Digite o ano do check-out: ");
          ano = scan.nextInt();
          checkout = new Date(ano, mes, dia);
          while (true) {
            int num_quarto;
            System.out.println("Digite o numero do quarto:");
            System.out.println("Digite '0' para sair:");
            num_quarto = scan.nextInt();
            if (num_quarto == 0 && !quartos_reservados.isEmpty())
              break;
            else if (num_quarto == 0 && quartos_reservados.isEmpty()) 
              System.out.println("Ainda não foram adicionados quartos a reserva");
            else if (encontrar_num_quarto(quartos, num_quarto)) {
              for (Quarto a : quartos) {
                if (a.getNum_quarto() == num_quarto) {
                  System.out.println("Quarto " + num_quarto + " reservado");
                  quartos_reservados.add(a);
                }
              }
            }
            else if (!encontrar_num_quarto(quartos, num_quarto)) 
              System.out.println("O numero do quarto eh invalido");
          }
          reservas.add(new Reserva(nome_hospede, checkin, checkout, quartos_reservados));
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
              List<Quarto> quartos_reserva = a.getQuartos();
              for (Quarto x : quartos_reserva) {
                for (Quarto y : quartos) {
                  if (x.getNum_quarto() == y.num_quarto) {
                    y.mudar_ocupado();
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}