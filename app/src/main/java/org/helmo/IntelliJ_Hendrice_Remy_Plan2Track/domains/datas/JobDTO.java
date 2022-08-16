package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import java.time.LocalDate;

/**
 * Définit l'objet de transfert de données d'une tâche.
 */
public class JobDTO {
    private final String name;
    private final String description;
    private final String technician;
    private final LocalDate expStart;
    private final LocalDate expEnd;
    private final LocalDate actStart;
    private final LocalDate actEnd;

    /**
     * Initialise les données d'une tâche
     * @param name le nom
     * @param description la description
     * @param technician le code du chef d'équipe assigné
     * @param expStart la date de début attendue
     * @param expEnd la date de fin attendue
     * @param actStart la date de début réelle
     * @param actEnd la date de fin réelle
     */
    public JobDTO(String name, String description, String technician,
                  LocalDate expStart, LocalDate expEnd,
                  LocalDate actStart, LocalDate actEnd){
        this.name = name;
        this.description = description;
        this.technician = technician;
        this.expStart = expStart;
        this.expEnd = expEnd;
        this.actStart = actStart;
        this.actEnd = actEnd;
    }

    /**
     * Retourne le nom.
     * @return le nom
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la date de début attendue.
     * @return la date de début attendue
     */
    public LocalDate getExpStart() {
        return expStart;
    }

    /**
     * Retourne la date de fin attendue.
     * @return la date de fin attendue
     */
    public LocalDate getExpEnd() {
        return expEnd;
    }

    /**
     * Retourne la date de début réelle.
     * @return la date de début réelle
     */
    public LocalDate getActStart() {
        return actStart;
    }

    /**
     * Retourne la date de fin réelle.
     * @return la date de fin réelle
     */
    public LocalDate getActEnd() {
        return actEnd;
    }
}
