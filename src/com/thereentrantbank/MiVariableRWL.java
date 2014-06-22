package com.thereentrantbank;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MiVariableRWL {
	static int contador;
	ReentrantReadWriteLock mirwlock;
	
	public MiVariableRWL(int c)
	{
		this.contador=c;
		mirwlock=new ReentrantReadWriteLock(true);
	}

	public int verSaldo(String nombredelthread)
	{
		int variable = 0;
		String nombre=nombredelthread;
		this.mirwlock.readLock();
		System.out.println(nombre+" ha bloqueado el readlock...operacion ver saldo :"+ contador);
		try
		{
			variable= contador;
		}
		catch (Exception e)
		{
			//capturamos los errores aqui
			System.out.println("Error al getVariable");
		}
		finally
		{
			System.out.println(nombre+" desbloquea el readlock...");
			if (mirwlock.getReadHoldCount()>0) this.mirwlock.readLock().unlock();
		}
		return variable;
	}
	public void ingresarDinero(String nombredelthread,int c)
	{
		String nombre=nombredelthread;
		this.mirwlock.writeLock().lock();

		try
		{
			contador=contador +c;
			System.out.println(nombre+ " ha bloqueado el writelock operacion ingresar dinero :"+ contador);
		}
		catch (Exception e)
		{
			//capturamos los errores aqui
			System.out.println("Error al getVariable");
		}
		finally
		{
			System.out.println(nombre+ " ha desbloqueado el writelock...");
			if(this.mirwlock.isWriteLockedByCurrentThread()) this.mirwlock.writeLock().unlock();
		}

	}
	public void retirarDinero(String nombredelthread,int c)
	{
		this.mirwlock.writeLock().lock();
		String nombre=nombredelthread;
		try
		{
			if(contador>=c)
			{
				contador=contador - c;
				System.out.println(nombre+ " ha  bloqueado el writelock..operacion retirar dinero :"+ contador);
			}
			else{System.out.println("No tienes suficiente saldo");}

		}
		catch (Exception e)
		{
			//capturamos los errores aqui
			System.out.println("Error al getVariable");
		}

		finally
		{
			System.out.println(nombre+ " ha  desbloqueado writelock...");
			if(this.mirwlock.isWriteLockedByCurrentThread())  this.mirwlock.writeLock().unlock();
		}

	}
}
