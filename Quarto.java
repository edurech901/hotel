public class Quarto {
  int num_quarto;
  int tipo_quarto;
  double preco;
  boolean disponibilidade;
  

  public Quarto(int num_quarto, int tipo_quarto) {
    this.num_quarto = num_quarto;
    this.tipo_quarto = tipo_quarto;
    switch (tipo_quarto) {
      case 1 -> this.preco = 200;
      case 2 -> this.preco = 350;
      case 3 -> this.preco = 500;
    }
    this.disponibilidade = false; // false = livre
  }

  public int getNum_quarto() {
    return num_quarto;
  }

  public void setNum_quarto(int num_quarto) {
    this.num_quarto = num_quarto;
  }

  public int getTipo_quarto() {
    return tipo_quarto;
  }

  public void setTipo_quarto(int tipo_quarto) {
    this.tipo_quarto = tipo_quarto;
  }

  public boolean getDisponibilidade() {
    return disponibilidade;
  }

  public void setDisponibilidade(boolean disponibilidade) {
    this.disponibilidade = disponibilidade;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public void printar_quarto() {
    System.out.println("\nNumero do quarto: " + num_quarto);
    switch (tipo_quarto) {
      case 1 -> System.out.println("Tipo do quarto: Solteiro\nDiaria: R$200,00");
      case 2 -> System.out.println("Tipo do quarto: Casal\nDiaria: R$350,00");
      case 3 -> System.out.println("Tipo do quarto: Casal e Solteiro\nDiaria: R$500,00");
    }
  }

  public void mostrar_disponibilidade() {
    if (disponibilidade)
      System.out.println("Quarto ocupado.");
    else
      System.out.println("Quarto livre.");
  }

  public void mudar_livre(){
    this.disponibilidade = false;
  }

  public void mudar_ocupado(){
    this.disponibilidade = true;
  }

}