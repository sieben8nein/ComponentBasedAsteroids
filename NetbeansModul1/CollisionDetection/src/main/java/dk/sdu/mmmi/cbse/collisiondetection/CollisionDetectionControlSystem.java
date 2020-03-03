package dk.sdu.mmmi.cbse.collisiondetection;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IPostEntityProcessingService.class)})
public class CollisionDetectionControlSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity ent1 : world.getEntities()) {
            for (Entity ent2 : world.getEntities()) {
                if(!ent1.getID().substring(ent1.getID().length()-3).equals(ent2.getID().substring(ent2.getID().length()-3))) {
                    if (getDistance(ent2, ent1) < ent1.getRadius() + ent2.getRadius()) {

                        LifePart ent1Life = ent1.getPart(LifePart.class);
                        LifePart ent2Life = ent2.getPart(LifePart.class);

                        if(ent1Life.getLife() <= 0){
                            world.removeEntity(ent1);
                        } else{
                            ent1Life.setIsHit(true);
                        }
                        if(ent2Life.getLife() <= 0){
                            world.removeEntity(ent2);
                        } else {
                            ent2Life.setIsHit(true);
                        }
                    }
                }
            }
        }
    }

    public float getDistance(Entity entity1, Entity entity2){
        float x1 = ((PositionPart) entity1.getPart(PositionPart.class)).getX();
        float y1 = ((PositionPart) entity1.getPart(PositionPart.class)).getY();
        float x2 = ((PositionPart) entity2.getPart(PositionPart.class)).getX();
        float y2 = ((PositionPart) entity2.getPart(PositionPart.class)).getY();
        return (float) Math.sqrt((Math.pow(x2-x1, 2) + (Math.pow(y2-y1, 2))));
    }
}
