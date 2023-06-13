import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.io.CharArrayWriter;

public class FinalImage2 {
    @Test
    public void ourPicture(){
//        Scene scene = new Scene.SceneBuilder("Test scene")//
//                .setBackground(new Color(174,234,255)).build();
        Scene scene = new Scene.SceneBuilder("Test scene")//
                .setBackground(new Color( 191, 41, 99)).build();


        Camera camera = new Camera(new Point(10000, -30000, 10000), new Vector(-1,3,-1).normalize(), new Vector(-1,3,10)) //
                .setVPDistance(10000).setVPSize(150, 150); //

//        Camera camera = new Camera(new Point(-1000, 2000, 500), new Vector(1000,-2000,-500).normalize(), new Vector(1000,1,2)) //
//                .setVPDistance(600).setVPSize(150, 150); //

//        //Scene scene = new Scene.SceneBuilder("Test scene").build();//
//        Camera camera = new Camera(new Point(-1000, 1500, 0), new Vector(1000,-1500,-500).normalize(), new Vector(1,0,2)) //
//                .setVPDistance(600).setVPSize(150, 150); //
//
//        Camera camera = new Camera(new Point(10000, -30000, 10000), new Vector(-1,3, 10), new Vector(-1, 3,-1)) //
//                .setVPSize(150, 150).setVPDistance(10000);

        //sun
        Material sunM = new Material().setKD(0.2).setKS(0.2).setNShininess(200).setKT(0.6);
        scene.geometries.add(new Sphere(new Point(-100, 140, 100), 40d).setEmission(new Color(YELLOW)).setMaterial(sunM));

        //mountains
        Material mountainM = new Material().setKD(0.6).setKS(0.4).setNShininess(200);
        Material snowM = new Material().setKD(0.3).setKS(0.6).setNShininess(200).setShininesR(0.4);

        //triangle 1
        scene.geometries.add(new Triangle(new Point(-120,10,0), new Point(-200, -45, 0), //
                new Point(-200, 0, 80)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-120,10,0), new Point(-200, 45, 0), //
                new Point(-200, 0, 80)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-260,10,0), new Point(-200, -45, 0), //
                new Point(-200, 0, 80)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-260,10,0), new Point(-200, 45, 0), //
                new Point(-200, 0, 80)).setEmission(new Color(117,68,35)).setMaterial(mountainM));

        //triangle 2
        scene.geometries.add(new Triangle(new Point(-75,0,100), new Point(-75, -40, 0), //
                new Point(-150, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-75,0,100), new Point(-75, -40, 0), //
                new Point(0, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-75,0,100), new Point(-75, 30,0), //
                new Point(-150, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-75,0,100), new Point(-75, 30, 0), //
                new Point(0, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));

        //triangle 3
        scene.geometries.add(new Triangle(new Point(0,0,90), new Point(0, -40, 0), //
                new Point(-50, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,0,90), new Point(0, -40, 0), //
                new Point(50, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,0,90), new Point(0, 30,0), //
                new Point(-50, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,0,90), new Point(0, 30, 0), //
                new Point(50, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));

        // triangle 4
        scene.geometries.add(new Triangle(new Point(75,0,100), new Point(75, -40, 0), //
                new Point(150, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(75,0,100), new Point(75, -40, 0), //
                new Point(0, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(75,0,100), new Point(75, 30,0), //
                new Point(150, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(75,0,100), new Point(75, 30, 0), //
                new Point(0, 0, 0)).setEmission(new Color(117,68,35)).setMaterial(mountainM));

        //triangle 5
        scene.geometries.add(new Triangle(new Point(120,10,0), new Point(200, 45, 0), //
                new Point(200, 0, 80)).setEmission(new  Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(120,10,0), new Point(200, -45, 0), //
                new Point(200, 0, 80)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(260,10,0), new Point(200, -45, 0), //
                new Point(200, 0, 80)).setEmission(new Color(117,68,35)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(260,10,0), new Point(200, 40, 0), //
                new Point(200, 0, 80)).setEmission(new Color(90,39,41)).setMaterial(mountainM));

        //triangle 6 
        scene.geometries.add(new Triangle(new Point(-150,80,160), new Point(-160, 130, 0), //
                new Point(-250, 70, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-150,80,160), new Point(-160, 130, 0), //
                new Point(-30, 60, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-150,80,160), new Point(-130, 20, 0), //
                new Point(-250, 70, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-150,80,160), new Point(-130, 20, 0), //
                new Point(-30, 60, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        //snow 6
        scene.geometries.add(new Triangle(new Point(-108,73.12,105), new Point(-153.75,99,100), //
                new Point(-150,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(-108,73.12,105), new Point(-142.5,57,100), //
                new Point(-150,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(-185,76.56,105), new Point(-153.75,99,100), //
                new Point(-150,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(-185,76.56,105), new Point(-142.5,57,100), //
                new Point(-150,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));

        //triangle 7
        scene.geometries.add(new Triangle(new Point(-50,50,120), new Point(-50, 70, 0), //
                new Point(-120, 40, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-50,50,120), new Point(-50, 70, 0), //
                new Point(0, 40, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-50,50,120), new Point(-40, 20,0), //
                new Point(-120, 40, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(-50,50,120), new Point(-40, 20, 0), //
                new Point(0, 40, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        //snow 7
        scene.geometries.add(new Triangle(new Point(-39,47.92,95), new Point(-47.5,42,90), //
                new Point(-50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(-39,47.92,95), new Point(-50,55.5,90), //
                new Point(-50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(-65,47.92,95), new Point(-47.5,42,90), //
                new Point(-50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(-65,47,95), new Point(-50,55.5,90), //
                new Point(-50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));

        //triangle 8 
        scene.geometries.add(new Triangle(new Point(50,50,120), new Point(40, 10, 0), //
                new Point(100, 50, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(50,50,120), new Point(40, 10, 0), //
                new Point(0, 50, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(50,50,120), new Point(40, 70,0), //
                new Point(100, 50, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(50,50,120), new Point(40, 70, 0), //
                new Point(0, 50, 0)).setEmission(new Color(101,67,33)).setMaterial(mountainM));
        //snow 8
        scene.geometries.add(new Triangle(new Point(61,50,95), new Point(47.5,55.5,90), //
                new Point(50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(61,50,95), new Point(47.5,39.5,90), //
                new Point(50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(39,50,95), new Point(47.5,55.5,90), //
                new Point(50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(39,50,95), new Point(47.5,39.5,90), //
                new Point(50,50,120)).setEmission(new Color(250,245,245)).setMaterial(snowM));

        //triangle 9 
        scene.geometries.add(new Triangle(new Point(150,80,160), new Point(160, 130, 0), //
                new Point(250, 70, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(150,80,160), new Point(160, 130, 0), //
                new Point(30, 60, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(150,80,160), new Point(130, 20, 0), //
                new Point(250, 70, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(150,80,160), new Point(130, 20, 0), //
                new Point(30, 60, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        //snow 9
        scene.geometries.add(new Triangle(new Point(185,76.56,105), new Point(153.75,99,100), //
                new Point(150, 80, 160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(185,76.56,105), new Point(142.5,57,100), //
                new Point(150, 80, 160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(108,73.12,105), new Point(153.75,99,100), //
                new Point(150, 80, 160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(108,73.12,105), new Point(142.5,57,100), //
                new Point(150, 80, 160)).setEmission(new Color(250,245,245)).setMaterial(snowM));

        //center triangle 10 
        scene.geometries.add(new Triangle(new Point(0,80,160), new Point(0, 100, 0), //
                new Point(90, 80, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,80,160), new Point(0, 100, 0), //
                new Point(-90, 80, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,80,160), new Point(0, 50,0), //
                new Point(90, 80, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,80,160), new Point(0, 50, 0), //
                new Point(-90, 80, 0)).setEmission(new Color(92,72,39)).setMaterial(mountainM));
        //snow 
        scene.geometries.add(new Triangle(new Point(-29,80,110), new Point(0,88,100), //
                new Point(0,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(-29,80,110), new Point(0,68,100), //
                new Point(0,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(29,80,110), new Point(0,88,100), //
                new Point(0,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));
        scene.geometries.add(new Triangle(new Point(29,80,110), new Point(0,68,100), //
                new Point(0,80,160)).setEmission(new Color(250,245,245)).setMaterial(snowM));

        //triangle 11 
        scene.geometries.add(new Triangle(new Point(0,150,100), new Point(0, 200, 0), //
                new Point(160, 130, 0)).setEmission(new Color(76,65,40)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,150,100), new Point(0, 200, 0), //
                new Point(-160, 130, 0)).setEmission(new Color(76,65,40)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,150,100), new Point(0, 100, 0), //
                new Point(160, 130, 0)).setEmission(new Color(76,65,40)).setMaterial(mountainM));
        scene.geometries.add(new Triangle(new Point(0,150,100), new Point(0, 100, 0), //
                new Point(-160, 130, 0)).setEmission(new Color(76,65,40)).setMaterial(mountainM));

        //earth- triangle
        scene.geometries.add(new Triangle(new Point(0,140,0), new Point(-600, -45, 0), //
                new Point(600, -45, 0)).setEmission(new Color(92,73,57)));

        //sea- triangle
        Material seaM = new Material().setKD(0.2).setKS(0.9).setNShininess(3000).setShininesR(0.6);
        scene.geometries.add(new Triangle(new Point(10000,0,0), new Point(-10000, 0, 0), //
                new Point(0, -10000, 0)).setEmission(new Color(0,25,50)).setMaterial(seaM));

        //sky- triangle
        scene.geometries.add(new Triangle(new Point(10000,10000,0), new Point(-10000, 10000, 0), //
                new Point(0, 10000, 100000)).setEmission(new Color(191, 41, 99)));

        //clouds
        Material cloudM = new Material().setKD(0.5).setKS(0.6).setNShininess(3000);

        //cloud 1
        scene.geometries.add(new Sphere(new Point(165, 165, 115), 30d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(140, 165, 105), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(190, 165, 105), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        //cloud 2
        scene.geometries.add(new Sphere(new Point(-215, 225, 135), 30d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(-190, 225, 125), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(-240, 225, 125), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        //cloud 3
        scene.geometries.add(new Sphere(new Point(35, 165, 165), 30d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(10, 165, 155), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(60, 165, 155), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        //cloud 3
        scene.geometries.add(new Sphere(new Point(-245, 5, 135), 30d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(-220, 5, 125), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));
        scene.geometries.add(new Sphere(new Point(-270, 5, 125), 20d).setEmission(new Color(248,245,245)).setMaterial(cloudM));

        //birds
        Material birdM = new Material().setKD(0.2).setKS(0.6).setNShininess(300);

        //bird 1
        scene.geometries.add(new Triangle(new Point(-66,-199,39), new Point(-66,-184,46), //
                new Point(-61,-184,43)).setEmission(new Color(0,0,0)).setMaterial(birdM));
        scene.geometries.add(new Triangle(new Point(-66,-199,39), new Point(-83,-198,43), //
                new Point(-76,-209,43)).setEmission(new Color(0,0,0)).setMaterial(birdM));
        scene.geometries.add(new Triangle(new Point(-40,-173,39), new Point(-66,-184,46), //
                new Point(-61,-184,43)).setEmission(new Color(0,0,0)).setMaterial(birdM));
        scene.geometries.add(new Triangle(new Point(-83,-220,37), new Point(-83,-198,43), //
                new Point(-76,-209,43)).setEmission(new Color(0,0,0)).setMaterial(birdM));
        //bird 2
        scene.geometries.add(new Triangle(new Point(162,59,133), new Point(168,63,139), //
                new Point(169,55,138)).setEmission(new Color(0,0,0)).setMaterial(birdM));
        scene.geometries.add(new Triangle(new Point(162,59,133), new Point(154,65,140), //
                new Point(155,57,140)).setEmission(new Color(0,0,0)).setMaterial(birdM));
        scene.geometries.add(new Triangle(new Point(177,59,130), new Point(168,63,139), //
                new Point(169,55,138)).setEmission(new Color(0,0,0)).setMaterial(birdM));
        scene.geometries.add(new Triangle(new Point(147,60,136), new Point(154,65,140), //
                new Point(155,57,140)).setEmission(new Color(0,0,0)).setMaterial(birdM));

        //bushes
        Material bushM = new Material().setKD(0.2).setKS(0.1).setNShininess(300);
        scene.geometries.add(new Sphere(new Point(225, -25, -5),22d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(172, -20, -5),15d).setEmission(new Color(108,187,60)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(130, -15, -5), 24d).setEmission(new Color(37,65,23)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(160, -20, -5), 10d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(100, -20, -5), 23d).setEmission(new Color(108,187,60)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(30, -19, -5), 14d).setEmission(new Color(56,124,68)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-24, -25, -5), 17d).setEmission(new Color(133,187,101)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-50, -20, -5), 15d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-100, -16, -5), 23d).setEmission(new Color(108,187,60)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-130, -25, -5), 18d).setEmission(new Color(37,65,23)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-165, -20, -5), 23d).setEmission(new Color(56,124,68)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-257, -22, -5), 19d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-233, -24, -5), 20d).setEmission(new Color(108,187,60)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-246, -25, -5),22d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-220, -20, -5),15d).setEmission(new Color(108,187,60)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-182, -15, -5), 21d).setEmission(new Color(37,65,23)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-147, -20, -5), 10d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-123, -20, -5), 23d).setEmission(new Color(108,187,60)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-76, -19, -5), 14d).setEmission(new Color(56,124,68)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(-31, -25, -5), 17d).setEmission(new Color(133,187,101)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(11, -20, -5), 15d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(49, -16, -5), 16d).setEmission(new Color(108,187,60)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(85, -25, -5), 18d).setEmission(new Color(37,65,23)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(116, -20, -5), 13d).setEmission(new Color(56,124,68)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(153, -22, -5), 19d).setEmission(new Color(114,140,0)).setMaterial(bushM));
        scene.geometries.add(new Sphere(new Point(240, -24, -5), 20d).setEmission(new Color(108,187,60)).setMaterial(bushM));

        scene.lights.add(
                new PointLight(new Color(yellow), new Point(90, 150, -300)) //
                        .setKc(1)
        );

        scene.lights.add(
                new SpotLight(new Color(255,204,0), new Point(-100, 140, 100), new Vector(15, 12, -19)) //
                        .setKL(0.00001).setKQ(0.000005)
        );

        //light source
        scene.lights.add(new DirectionalLight(new Color(200,200,0), new Vector(0,0,-1)));
        scene.lights.add(new SpotLight(new Color(123,104,238),new Point(10,-30,40), new Vector(-1, 3,7)));
        // Sun light source
        scene.lights.add(new PointLight(new Color(YELLOW),new Point(-100, 140, 100)).setKc(1));

        ImageWriter imageWriter = new ImageWriter("Mountains2", 400, 400);
        camera.setImageWriter(imageWriter) //
//                .setImprovments(true, true)
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }
}