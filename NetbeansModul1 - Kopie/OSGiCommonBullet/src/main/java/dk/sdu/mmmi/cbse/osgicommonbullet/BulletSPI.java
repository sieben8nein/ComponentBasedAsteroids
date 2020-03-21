package dk.sdu.mmmi.cbse.osgicommonbullet;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;

public interface BulletSPI {
    public Entity createBullet(Entity shooter, GameData gameData);
}
