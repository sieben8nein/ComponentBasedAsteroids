package dk.sdu.mmmi.cbse.enemysystem;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {
    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);

        sr.begin(ShapeRenderer.ShapeType.Line);

        float[] shapex = this.getShapeX();
        float[] shapey = this.getShapeY();

        for (int i = 0, j = shapex.length - 1;
             i < shapex.length;
             j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
        }

        sr.end();
    }
}
