package it.unisannio.catman.inbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;

public class Inbox implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Window.alert("Inbox module loaded");
		/* TODO
		 * Un nuovo modulo (sezione dell'app) che si carica dovrebbe mandare un messaggio
		 * sull'EventBus al modulo principale (Main) per manifestare la propria presenza ed
		 * essere incluso nel menù di navigazione.
		 * 
		 * Il resto del codice del modulo dovrebbe essere caricato via GWT.runAsync() (split-point)
		 * da Main.
		 */
	}	
}
