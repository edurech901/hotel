public class Hospede {
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private boolean cadastrado; //false = incompleto - true = completo

    public Hospede(String nome) {
        this.nome = nome;
        cadastrado = false;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getCadastrado() {
        return cadastrado;
    }

    public void setCadastrado(boolean cadastrado) {
        this.cadastrado = cadastrado;
    }
    public void finalizar_cadastro(String cpf, String telefone, String email) {
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        cadastrado = true;
    }

    public void printar_hospede(){
        System.out.println("-------- HOSPEDE --------");
        System.out.println("Nome do hospede: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
    }
}

