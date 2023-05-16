package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The Scene class represents a scene in a computer graphics environment.
 * It contains information about the name, background color, ambient light,
 * and geometries present in the scene.
 *
 * The Scene class provides methods to set and retrieve the ambient light of the scene.
 * It also contains an inner class SceneBuilder, which is used to construct Scene objects
 * using a builder pattern.
 *
 * The Scene class is immutable once constructed.
 *
 * @author Naomi Reitzer and Leah Golovenziz
 */
public class Scene {

    public final String name;
    public final Color background;
    public AmbientLight ambientLight;
    public final Geometries geometries;

    /**
     * Constructs a Scene object using a SceneBuilder.
     * The builder is used to set the name, background color, ambient light, and geometries of the scene.
     *
     * @param builder The SceneBuilder used to construct the Scene object
     */
    private Scene(SceneBuilder builder){
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light to be set for the scene
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * The SceneBuilder class is an inner class of Scene.
     * It is used to construct Scene objects using a builder pattern.
     */
    public static class SceneBuilder {

        private final String name;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = AmbientLight.NONE;
        private Geometries geometries = new Geometries();

        /**
         * Constructs a SceneBuilder object with the given scene name.
         *
         * @param name The name of the scene
         */
        public SceneBuilder(String name){
            this.name = name;
        }

        /**
         * Sets the background color of the scene.
         *
         * @param background The background color to be set for the scene
         * @return The SceneBuilder object
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        /**
         * Sets the ambient light of the scene.
         *
         * @param ambientLight The ambient light to be set for the scene
         * @return The SceneBuilder object
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * Sets the geometries of the scene.
         *
         * @param geometries The geometries to be set for the scene
         * @return The SceneBuilder object
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * Builds and returns a Scene object using the values set in the SceneBuilder.
         *
         * @return The constructed Scene object
         */
        public Scene build(){
            Scene scene = new Scene(this);
            return scene;
        }
    }
}

