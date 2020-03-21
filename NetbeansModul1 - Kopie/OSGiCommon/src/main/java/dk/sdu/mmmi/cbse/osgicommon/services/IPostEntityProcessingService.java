package dk.sdu.mmmi.cbse.osgicommon.services;

import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        /**
         * For at collision fx først bliver beregnet efter at alle entities er opdateret.
         * @param gameData
         * @param world
         */
        void process(GameData gameData, World world);
}
