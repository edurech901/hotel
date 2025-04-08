import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Reserva {
  String nome_hospede;
  LocalDate checkin, checkout;
  List<Quarto> quartos;

  Reserva(String nome_hospede, LocalDate checkin, LocalDate checkout, List<Quarto> quartos) {
    this.nome_hospede = nome_hospede;
    this.checkin = checkin;
    this.checkout = checkout;
    this.quartos = quartos;
  }

  public String getNome_hospede() {
    return nome_hospede;
  }

  public void setNome_hospede(String nome_hospede) {
    this.nome_hospede = nome_hospede;
  }

  public LocalDate getCheckout() {
    return checkout;
  }

  public void setCheckout(LocalDate checkout) {
    this.checkout = checkout;
  }

  public LocalDate getCheckin() {
    return checkin;
  }

  public void setCheckin(LocalDate checkin) {
    this.checkin = checkin;
  }

  public List<Quarto> getQuartos() {
    return quartos;
  }

  public void setQuartos(List<Quarto> quartos) {
    this.quartos = quartos;
  }

  public void printar_reserva() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.println("\nNome do hospede: " + nome_hospede);
    System.out.println("Periodo: ");
    /*System.out.println(checkin.getDate() + "/" + checkin.getMonth()+1 + "/" + checkin.getYear() + " - "
        + checkout.getDate() + "/" + checkout.getMonth()+1 + "/" + checkout.getYear());*/
    System.out.println(checkin.format(formatter) + " - "
        + checkout.format(formatter));

    System.out.println("Quartos reservados:");
    for (Quarto a : this.quartos) {
      System.out.println("  Numero do quarto: " + a.getNum_quarto());
      System.out.println("  Tipo do quarto: ");
      switch (a.getTipo_quarto()) {
        case 1 -> System.out.println("  Solteiro - R$200,00");
        case 2 -> System.out.println("  Casal - R$350,00");
        case 3 -> System.out.println("  Casal + Solteiro - R$500,00");
      }
    System.out.println("-----------------------");
    }
  }
}
