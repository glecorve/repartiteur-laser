package controle;

/**
 * Interface definissant les fonctionnalites attendues d'un controleur pour le projet de repartiteur laser
 * @author Gwenole Lecorve
 */
public interface ControleurInterface {
	
	/**
	 * Receptionne du demande d'entree en section critique de la part du processus metier
	 */
	void demanderSectionCritique();
	
	/**
	 * Signale l'autorisation d'entrer en section critique aupres du processus metier
	 */
	void signalerAutorisation();
	
	/**
	 * Receptionne la notification du processus metier a sa sortie de la section critique
	 */
	void quitterSectionCritique();
	
	/**
	 * Enregistre l'URL d'un controleur distant
	 * @param url l'URL a memoriser
	 */
	void enregistrerControleur(String url);
	
	/**
	 * Oublie l'URL d'un controleur distant
	 * @param url l'URL a oublier
	 */
	void oublierControleur(String url);
	
}
