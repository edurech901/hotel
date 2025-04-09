import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
  String nome_hospede;
  LocalDate checkin, checkout;
  Quarto reservado;

  Reserva(String nome_hospede, LocalDate checkin, LocalDate checkout, Quarto reservado) {
    this.nome_hospede = nome_hospede;
    this.checkin = checkin;
    this.checkout = checkout;
    this.reservado = reservado;
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

  public Quarto getQuarto() {
    return reservado;
  }

  public void setQuarto(Quarto quarto) {
    this.reservado = quarto;
  }

  public void printar_reserva() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.println("\nNome do hospede: " + nome_hospede);
    System.out.println("Periodo: ");
    System.out.println(checkin.format(formatter) + " - "
        + checkout.format(formatter));

    System.out.println("Quartos reservados:");
    reservado.printar_quarto();
    }
  
}
