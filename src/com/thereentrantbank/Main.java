package com.thereentrantbank;

public class Main {

	static MiVariableRWL micuenta2;
	private static int saldo_inicial=100;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//micuenta=new MiVariable(saldo_inicial);
		micuenta2=new MiVariableRWL(saldo_inicial);
		
		//creamos 3 usuarios thread...
		UserAccountActionsThread pepe=new UserAccountActionsThread("Pepe",micuenta2);
		UserAccountActionsThread juan=new UserAccountActionsThread("Juan",micuenta2);
		UserAccountActionsThread antonio=new UserAccountActionsThread("Antonio",micuenta2);

		//arrancamos los tres threads...
		System.out.println(" MAIN: Usuarios creados...");
		pepe.start();
		System.out.println(" MAIN: pepe arrancado...");
		juan.start();
		System.out.println(" MAIN: juan arrancado...");
		antonio.start();
		System.out.println(" MAIN: antonio arrancado...");
	}

}