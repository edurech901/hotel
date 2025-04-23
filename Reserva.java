import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
  private Hospede hospede;
  private LocalDate checkin, checkout;
  private Quarto reservado;
  private double total;
  private int status; // 0 = aguardando check-in, 1 = check-in, 2 = check-out, 3 = cancelada, 4 = atrasadas

  Reserva(Hospede hospede, LocalDate checkin, LocalDate checkout, Quarto reservado) {
    this.hospede = hospede;
    this.checkin = checkin;
    this.checkout = checkout;
    this.reservado = reservado;
    this.status = 0;
    if (checkout.isEqual(checkin))
      this.total = reservado.getPreco();
    else
      this.total = reservado.getPreco() * (checkout.toEpochDay() - checkin.toEpochDay());
  }

    public Reserva() {
    }

  public Hospede getHospede() {
    return hospede;
  }

  public void setHospede(Hospede hospede) {
    this.hospede = hospede;
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
    System.out.println("-------- RESERVA --------");
    System.out.println("Nome do hospede: " + hospede.getNome());
    System.out.println("Periodo: ");
    System.out.println(checkin.format(formatter) + " - "
        + checkout.format(formatter));
    reservado.printar_quarto();
    System.out.println("Total: R$" + total);
      switch (status) {
          case 0 -> System.out.println("Status: Aguardando check-in.");
          case 1 -> System.out.println("Status: Check-in realizado");
          case 2 -> System.out.println("Status: Check-out realizado.");
          case 3 -> System.out.println("Status: Cancelada.");
          case 4 -> System.out.println("Status: Atrasada.");
          default -> {
          }
      }
  }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
