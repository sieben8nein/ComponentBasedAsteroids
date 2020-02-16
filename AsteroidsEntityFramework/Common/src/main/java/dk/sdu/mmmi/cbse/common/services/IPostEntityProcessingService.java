package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

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
