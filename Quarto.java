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

  public boolean isDisponibilidade() {
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
    System.out.println("Tipo do quarto: ");
    switch (tipo_quarto) {
      case 1 -> System.out.println("Solteiro - R$200,00");
      case 2 -> System.out.println("Casal - R$350,00");
      case 3 -> System.out.println("Casal e Solteiro - R$500,00");
    }
    if(disponibilidade) System.out.println("Ocupado");
    else System.out.println("Disponivel");
  }

  public void mudar_livre(){
    this.disponibilidade = false;
  }

  public void mudar_ocupado(){
    this.disponibilidade = true;
  }

}