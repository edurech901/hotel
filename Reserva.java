import java.util.Date;
import java.util.List;

public class Reserva {
  String nome_hospede;
  Date checkin, checkout;
  List<Quarto> quartos;

  Reserva(String nome_hospede, Date checkin, Date checkout, List<Quarto> quartos) {
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

  public Date getCheckout() {
    return checkout;
  }

  public void setCheckout(Date checkout) {
    this.checkout = checkout;
  }

  public Date getCheckin() {
    return checkin;
  }

  public void setCheckin(Date checkin) {
    this.checkin = checkin;
  }

  public List<Quarto> getQuartos() {
    return quartos;
  }

  public void setQuartos(List<Quarto> quartos) {
    this.quartos = quartos;
  }

  @SuppressWarnings("deprecation")
  public void printar_reserva() {
    System.out.println("\nNome do hospede: " + nome_hospede);
    System.out.println("Periodo: ");
    System.out.println(checkin.getDate() + "/" + checkin.getMonth() + "/" + checkin.getYear() + " - "
        + checkout.getDate() + "/" + checkout.getMonth() + "/" + checkout.getYear());
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
