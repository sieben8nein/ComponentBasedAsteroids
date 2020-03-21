package dk.sdu.mmmi.cbse.osgicommon.services;

import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.data.World;

/**
 *
 */
public interface IGamePluginService {
    /**
     * Precondition: The world has been initialized, and the game has been started
     * post:condition
     * @param gameData containing data about the current game setup, including framesize etc
     * @param world The world, containing and managing all entities
     */
    void start(GameData gameData, World world);
    void stop(GameData gameData, World world);
}
