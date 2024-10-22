import java.util.Date;
import java.util.List;

public class Reserva {
  String nome_hospede;
  Date checkin, checkout;
  List<Quarto> quartos;

  Reserva(String nome_hospede, Date checkin, Date checkout, List<Quarto> quartos){
    this.nome_hospede = nome_hospede;
    this.checkin = checkin;
    this.checkout = checkout;
    this.quartos = quartos;
  }

}
