package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.collisiondetectionsystem.CollisionDetectionControlSystem;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.interfaces.Drawable;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;

import java.util.ArrayList;
import java.util.List;

public class Game
        implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private List<IPostEntityProcessingService> postEntityProcessors = new ArrayList<>();
    private List<IGamePluginService> entityPlugins = new ArrayList<>();
    private World world = new World();

    @Override
    public void create() {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        createPlayer(entityPlugins, entityProcessors);
        createEnemy(entityPlugins, entityProcessors);
        createAsteroids(entityPlugins, entityProcessors);
        createCollisionDetection(postEntityProcessors);
        startPlugins(entityPlugins, gameData, world);

    }

    private void createCollisionDetection(List postEntityProcessors){
        IPostEntityProcessingService collisionDetectionProcess = new CollisionDetectionControlSystem();
        postEntityProcessors.add(collisionDetectionProcess);
    }

    private void startPlugins(List<IGamePluginService> entityPlugins, GameData gameData, World world){
        for (IGamePluginService iGamePlugin : entityPlugins) {
            iGamePlugin.start(gameData, world);
        }
    }

    private void createPlayer(List entityPlugins, List entityProcessors){
        IGamePluginService playerPlugin = new PlayerPlugin();
        IEntityProcessingService playerProcess = new PlayerControlSystem();
        entityPlugins.add(playerPlugin);
        entityProcessors.add(playerProcess);
    }

    private void createEnemy(List entityPlugins, List entityProcessors){
        IGamePluginService enemyPlugin = new EnemyPlugin();
        IEntityProcessingService enemyProcess = new EnemyControlSystem();
        entityPlugins.add(enemyPlugin);
        entityProcessors.add(enemyProcess);
    }

    private void createAsteroids(List entityPlugins, List entityProcessors){
        for (int i = 0; i < 10; i++) {
            IGamePluginService asteroidPlugin = new AsteroidPlugin();
            IEntityProcessingService asteroidProcess = new AsteroidControlSystem();
            entityPlugins.add(asteroidPlugin);
            entityProcessors.add(asteroidProcess);
        }
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        update();
        draw();
        gameData.getKeys().update();
    }

    public void addEntity(IGamePluginService pluginService, IEntityProcessingService entityProcessingService){
        entityPlugins.add(pluginService);
        entityProcessors.add(entityProcessingService);
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : entityProcessors) {
            entityProcessorService.process(gameData, world);
        }

        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessors) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Drawable entity : world.getEntities()) {
            entity.draw(sr);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
