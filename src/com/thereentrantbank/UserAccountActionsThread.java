package com.thereentrantbank;

import java.util.Random;

public class UserAccountActionsThread extends Thread {

	int tiempo_espera=1000;
	MiVariableRWL micuenta2;
	String nombreUsuario;
	
	public UserAccountActionsThread(String nombreUsuario,MiVariableRWL m)
	{
		this.micuenta2=m;
		this.nombreUsuario=nombreUsuario;
	}

	public void run()
	{
		//vamos a hacer 20 operaciones aleatorias...versaldo,ingresar,retirar...
		for(int i=0;i<10;i++)
		{
			switch(randomnumber())
			{
			case 0:
				//ver saldo
				//System.out.println("El Usuario: "+this.nombreUsuario +" va a ver el saldo,intentando bloquear variable...");
				micuenta2.verSaldo(nombreUsuario);
				try{
					Thread.sleep(tiempo_espera);
				}catch(Exception e){};
				//System.out.println("El Usuario: "+this.nombreUsuario +" ya ha visto el saldo,variable desbloqueada...");
				break;
			case 1:
				//ingresar
				//System.out.println("El Usuario: "+this.nombreUsuario +" va a ingresar dinero,intentando bloquear variable...");
				micuenta2.ingresarDinero(nombreUsuario,5);
				try{
					Thread.sleep(tiempo_espera);
				}catch(Exception e){};
				//System.out.println("El Usuario: "+this.nombreUsuario +" ha finalizado de ingresar,variable desbloqueada...");
				break;
			case 2:
				//retirar
				//System.out.println("El Usuario: "+this.nombreUsuario +" va a retirar dinero,intentando bloquear variable...");
				micuenta2.retirarDinero(nombreUsuario,2);

				try{
					Thread.sleep(tiempo_espera);
				}catch(Exception e){};
				//System.out.println("El Usuario: "+this.nombreUsuario +" ha terminado de retirar dinero,variable desbloqueada...");
				break;
			}

		}
		System.out.println("Usuario: "+this.nombreUsuario+" ha finalizado la prueba.");
	}
	private int randomnumber()
	{
		int numero=0;
		Random r = new Random();
		numero = r.nextInt(3);
		return numero;
	}

}