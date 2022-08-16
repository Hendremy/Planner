package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import java.time.LocalDate;

/**
 * D�finit l'objet de transfert de donn�es d'une t�che.
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
     * Initialise les donn�es d'une t�che
     * @param name le nom
     * @param description la description
     * @param technician le code du chef d'�quipe assign�
     * @param expStart la date de d�but attendue
     * @param expEnd la date de fin attendue
     * @param actStart la date de d�but r�elle
     * @param actEnd la date de fin r�elle
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
     * Retourne la date de d�but attendue.
     * @return la date de d�but attendue
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
     * Retourne la date de d�but r�elle.
     * @return la date de d�but r�elle
     */
    public LocalDate getActStart() {
        return actStart;
    }

    /**
     * Retourne la date de fin r�elle.
     * @return la date de fin r�elle
     */
    public LocalDate getActEnd() {
        return actEnd;
    }
}
