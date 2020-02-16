package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class WeaponPart implements EntityPart {
    private World world;
    private boolean shooting;
    public WeaponPart(World world){
        this.world = world;
    }
    @Override
    public void process(GameData gameData, Entity entity) {
        if(shooting){

        }
    }

    private void shoot(float x, float y, float dir){

    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
