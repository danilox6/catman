package it.unisannio.catman.common.client;

import com.google.gwt.core.shared.GWT;

/**
 * Serve solo per fare in modo che il refactoring sia più veloce quando verrà implementata la gestione degli errori (tipo 404)
 */
public class ErrorHandler {
	public static void handle(String errorMessage){
		GWT.log(errorMessage);
	}
	
	public static void handle(){
		handle("Errore 404");
	}
}
