package dk.sdu.mmmi.cbse.osgicommon.services;

import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.data.World;

public interface IEntityProcessingService {
    /**
     *
     * @param gameData Used to provide the concerned entities with required gamedata, fx Key updates for the player
     * @param world Used to give access to the world and its entities for later processing/updating
     */
    void process(GameData gameData, World world);
}
