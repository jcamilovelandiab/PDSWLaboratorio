package edu.eci.pdsw.test;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import org.quicktheories.generators.Generate;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.ArrayList;

import edu.eci.pdsw.samples.entities.*;


public class GeneratorTipoItem {
	
	public static Gen<TipoItem> tiposItems() {
		return ids().zip(descripciones(),
						(id,descripcion) -> 
							new TipoItem(id, descripcion));
	}

	private static Gen<Integer> ids() {
	    return integers().allPositive();
	}
	
	private static Gen<String> descripciones(){
		return strings().basicLatinAlphabet().ofLengthBetween(10, 70);
	}

}
