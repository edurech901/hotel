import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
  private String nome_hospede;
  private LocalDate checkin, checkout;
  private Quarto reservado;
  private double total;

  Reserva(String nome_hospede, LocalDate checkin, LocalDate checkout, Quarto reservado) {
    this.nome_hospede = nome_hospede;
    this.checkin = checkin;
    this.checkout = checkout;
    this.reservado = reservado;
    if (checkout.isEqual(checkin))
      this.total = reservado.getPreco();
    else
      this.total = reservado.getPreco() * (checkout.toEpochDay() - checkin.toEpochDay());
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

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void printar_reserva() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.println("\nNome do hospede: " + nome_hospede);
    System.out.println("Periodo: ");
    System.out.println(checkin.format(formatter) + " - "
        + checkout.format(formatter));

    System.out.println("Quarto reservado:");
    reservado.printar_quarto();
    System.out.println("Total: R$" + total);
  }

}
