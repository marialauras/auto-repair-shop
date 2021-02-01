package classes;
public class Main {

	public static void main(String[] args) {
		List <Funcionario> func = new List<Funcionario>();
		Funcionario maria = new Vendedor("maria", "mar");
		func.add(maria);
		Funcionario joao = new Mecanico("carlos", "car");
		func.add(joao);
		Funcionario marcio = new Administrador("laura", "lau");
		func.add(marcio);

		Login login = new Login(func);
		login.login();
		
	}

}