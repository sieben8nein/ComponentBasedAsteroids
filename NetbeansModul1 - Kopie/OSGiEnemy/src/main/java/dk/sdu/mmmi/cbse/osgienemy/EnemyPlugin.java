package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.osgicommon.data.Entity;
import dk.sdu.mmmi.cbse.osgicommon.data.GameData;
import dk.sdu.mmmi.cbse.osgicommon.data.World;
import dk.sdu.mmmi.cbse.osgicommon.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.osgicommon.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.osgicommon.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.osgicommon.services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {@ServiceProvider(service = IGamePluginService.class) })
public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin(){

    }
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    public Entity createEnemy(GameData gameData){
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 100;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 3;
        float y = gameData.getDisplayHeight() / 3;
        float radians = 3.1415f;

        Entity enemy = new Enemy();
        enemy.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemy.add(new PositionPart(x, y, radians));
        enemy.add(new LifePart(100, 1000));
        enemy.setRadius(8);

        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
