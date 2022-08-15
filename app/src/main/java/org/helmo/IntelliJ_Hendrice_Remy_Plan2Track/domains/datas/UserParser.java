package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.util.Collection;

public interface UserParser {
    Collection<Technician> parseArray(String usersString);
}
