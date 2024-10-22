import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

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

  public static boolean encontrar_num_quarto(List<Quarto> quartos, int num_quarto){
    for(Quarto a : quartos){
      if(num_quarto == a.getNum_quarto()) return true;
    }
    return false;
  }

  public static void main(String[] args) {

    List<Quarto> quartos = quartos();

    while (true) {
      Scanner scan = new Scanner(System.in);
      int op;
      System.out.println("\n-------- MENU ---------");
      System.out.println("1  -- Cadastrar quarto");
      System.out.println("2  -- lista de quartos");
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
          if (tipo_quarto<1 || tipo_quarto > 3) {
            System.out.println("Código de tipo de quarto invalido");
            break;
          }
          quartos.add(new Quarto(num_quarto, tipo_quarto));
          System.err.println("Quarto cadastrado com sucesso");
        }
        case 2 -> {
          for(Quarto quarto: quartos){
            quarto.printar_quarto();
          }
        }
      }
    }
  }
}