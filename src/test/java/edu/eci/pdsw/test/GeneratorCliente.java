package edu.eci.pdsw.test;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import org.quicktheories.generators.Generate;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.ArrayList;

import edu.eci.pdsw.samples.entities.*;


public class GeneratorCliente {
	
	public static Gen<Cliente> clientes() {
		return strings().basicLatinAlphabet().ofLengthBetween(10, 20)
				.zip(documentos(), telefonos(), direcciones(), correos(),
						(nombre, documento, telefono, direccion, email) -> 
							new Cliente(nombre, documento, telefono, direccion, email));
	}

	private static Gen<Long> documentos() {
	    return longs().all();
	}
	
	private static Gen<String> telefonos(){
		return strings().numericBetween(10000, 100000000);
	}
	
	private static Gen<String> direcciones(){
		return strings().basicLatinAlphabet().ofLengthBetween(10, 20);
	}
	
	private static Gen<String> correos(){
		return strings().basicLatinAlphabet().ofLengthBetween(5, 15).map(x ->  {return x+"@JohnYElVelan.com";});
	}
	
}


/*
private String nombre;
private long documento;
private String telefono;
private String direccion;
private String email;
private boolean vetado;
private ArrayList<ItemRentado> rentados;
*/