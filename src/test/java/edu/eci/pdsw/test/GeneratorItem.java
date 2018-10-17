package edu.eci.pdsw.test;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import org.quicktheories.generators.Generate;
import org.quicktheories.generators.LocalDatesDSL;

import static org.quicktheories.generators.SourceDSL.*;
import static org.quicktheories.generators.LocalDatesDSL.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.test.*;

public class GeneratorItem {
	/*
	public Item(TipoItem tipo, int id, String nombre, String descripcion, 
		    Date fechaLanzamiento, long tarifaxDia, String formatoRenta, String genero) */
	public static Gen<Item> Items() {
		return GeneratorTipoItem.TiposItems()
				.zip(ids(), nombres(), tarifaGenerator(),generosGen(),
						(tipo, id, nombre,tarifas,generos) -> 
						new Item(tipo,id, nombre,tarifas,generos));
	}
      
        

	private static Gen<Integer> ids() {
	    return integers().allPositive();
	}
	
	private static Gen<String> nombres(){
		return strings().basicLatinAlphabet().ofLengthBetween(3, 15);
	}
	
	private static Gen<String> descripciones(){
		return strings().basicLatinAlphabet().ofLengthBetween(20, 60);
	}
	

	private static Gen<Date> fechas(){
		return dates().withMilliseconds(213987831);
	}
	
	
	private static Gen<Long> tarifaGenerator(){
		return longs().all();
	}
	
	private static Gen<String> formatoRentaGen(){
		return strings().basicLatinAlphabet().ofLengthBetween(13,16);
	}

	private static Gen<String> generosGen(){
		return strings().basicLatinAlphabet().ofLengthBetween(5, 30);
	}
	
}

