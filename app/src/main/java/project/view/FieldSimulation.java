package project.view;


import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import project.logic.Field2D;
import project.logic.Particle;
import project.logic.Vector2D;
import project.utils.ResizableCanvas;

public class FieldSimulation {
    private Field2D field;
    private Canvas canvas;
    private GraphicsContext ctx;
    private double firstWidth, firstHeight;

    private Particle selectedParticle;
    double k;
    public FieldSimulation(Field2D f, Canvas c){
        field = f;
        canvas = c;
        ctx = c.getGraphicsContext2D();
        firstWidth=c.getWidth();
        firstHeight=c.getHeight();
        
    }


    public Vector2D convertFieldCoordsToScreen(Vector2D mapCoords){
        return Vector2D.fromCoords(
            (mapCoords.getX()+field.getWidth()/2)/field.getWidth()*canvas.getWidth(), 
            (mapCoords.getY()+field.getHeight()/2)/field.getHeight()*canvas.getHeight());
    }

    public Vector2D convertScreenCoordsToField(Vector2D screenCoords){
        return Vector2D.fromCoords(
            screenCoords.getX()/canvas.getWidth()*field.getWidth()-field.getWidth()/2, 
            screenCoords.getY()/canvas.getHeight()*field.getHeight()-field.getHeight()/2
        );
    }

    Vector2D convertGridCoordsToField(int x, int y){
        return Vector2D.fromCoords(
            (x-Math.round(field.getGridWidth()/2))*field.getWidth()/field.getGridWidth(),
            (y-Math.round(field.getGridHeight()/2))*field.getHeight()/field.getGridHeight()
        );
        
    }

    public void drawArrow(Vector2D coords, double length, double angle){
        
        // Draw a Text
        
        ctx.setFill(Color.BLUE);
        double x = coords.getX();
        double y = coords.getY();
    
        /*ctx.setLineDashes(0);
        ctx.setLineWidth(1);
        Transform transform = Transform.translate(x, y);
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
        ctx.setTransform(new Affine(transform));
        ctx.strokeLine(0, 0, length, 0);
        double arrSize = length*0.1;
        ctx.fillPolygon(new double[]{length, length- 2* arrSize, length - 2* arrSize}, new double[]{0, -arrSize, arrSize},
      3);

        ctx.restore();*/
        //length*=k;
        /*ctx.setLineDashes(0);
        ctx.setLineWidth(1);
        Transform transform = Transform.translate(x, y);
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
        ctx.setTransform(new Affine(transform));
        double arrSize = length*0.3;
        ctx.fillPolygon(new double[]{length, length- 3* arrSize, length - 3* arrSize}, new double[]{0, -arrSize, arrSize},3);


        ctx.setTransform(new Affine(Transform.translate(0, 0).createConcatenation(Transform.rotate(0,0,0))));
        ctx.setFill(Color.RED);
        ctx.fillOval(x, y, 3, 3);
        ctx.strokeText("Hello Canvas", 150, 100);
    */
        length*=k;
        double offs = 15 * Math.PI / 180.0; 
        angle += Math.PI;
        double xs[] = new double[]{
            x + (length/2 * Math.cos(angle+offs)), x-(length/2 * Math.cos(angle)),
                x + (length/2 * Math.cos(angle-offs))
        };
        double ys[] = new double[]{
            y + (length/2 * Math.sin(angle+offs)), y - (length/2 * Math.sin(angle)),
                y + (length/2 * Math.sin(angle-offs))
        };
        ctx.fillPolygon(xs, ys, 3);
        /*/
        ctx.fillPolygon(new double[]{
            coords.getX(),
            coords.getX()+(length * Math.tan(angle)),
            coords.getX()-(length * Math.tan(angle))
        }, new double[]{
            coords.getX(),
            coords.getY()-length,
            coords.getY()-length
        }, 3); 
        */
        

    }
    /*/
    static public void drawArrow(Graphics g, int x0, int y0, int x1,
            int y1, int headLength, int headAngle) {
        double offs = headAngle * Math.PI / 180.0;
        double angle = Math.atan2(y0 - y1, x0 - x1);
        int[] xs = { x1 + (int) (headLength * Math.cos(angle + offs)), x1,
                x1 + (int) (headLength * Math.cos(angle - offs)) };
        int[] ys = { y1 + (int) (headLength * Math.sin(angle + offs)), y1,
                y1 + (int) (headLength * Math.sin(angle - offs)) };
        g.drawLine(x0, y0, x1, y1);
        g.drawPolyline(xs, ys, 3);
    }*/

    final double e0 = 8.854187e-12;
    public Vector2D E(Vector2D pos){
        Vector2D res = Vector2D.nullVector();
        for (Particle p: field.getParticles()){
            
            Vector2D r = pos.sub(p.getPos());
            double rLen = r.len();
           //if(p.getQ()<0) return Vector2D.nullVector();
            Vector2D ur = r.div(rLen);
            res = res.add(ur.mul(p.getQ()/(rLen*rLen)));
        }
        return res.div(4*Math.PI*e0);
    }
    public void update(){

        for(int y=0; y<field.getGridHeight(); y++){
            for(int x=0; x<field.getGridWidth(); x++){
                Vector2D pos = convertGridCoordsToField(x, y);
    
                Vector2D vec = E(pos);//Vector2D.fromAngle(Math.toRadians(Math.random() * 360), 0.1);
                field.set(x, y, vec);
                //System.out.println(vec.toString());
            }
        }
    }
    public void render(){
        k = Math.sqrt((canvas.getWidth()/firstWidth)*(canvas.getHeight()/firstHeight));
        Platform.runLater(()->{
            ctx.setFill(Color.WHITE);
            ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            ctx.setStroke(Color.BLACK);
            ctx.strokeLine(canvas.getWidth()/2, 0, canvas.getWidth()/2, canvas.getHeight());
            ctx.strokeLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2);

            for(int y=0; y<field.getGridHeight(); y++){
                for(int x=0; x<field.getGridWidth(); x++){
                    Vector2D vec = field.get(x, y);
                    Vector2D renderCoords = convertFieldCoordsToScreen(convertGridCoordsToField(x, y));
                    drawArrow(renderCoords, 10, vec.getAngle());
                    //ctx.setFill(Color.RED);
                    //ctx.fillOval(renderCoords.getX()-1.5, renderCoords.getY()-1.5, 3, 3);
                }
            }

            for (Particle p: field.getParticles()){
                double r=10;
                if(p.getQ()>0) ctx.setFill(Color.RED);
                else ctx.setFill(Color.BLUE);

                Vector2D renderCoords = convertFieldCoordsToScreen(p.getPos());
                ctx.fillOval((renderCoords.getX()-r*k), (renderCoords.getY()-r*k), 2*r*k, 2*r*k);
            }
            if(field.getParticles().size()==0) selectedParticle=null;
            if(selectedParticle!=null){
                double r=20;

                Vector2D renderCoords = convertFieldCoordsToScreen(selectedParticle.getPos());
                ctx.setStroke(Color.GREEN);
                ctx.strokeOval((renderCoords.getX()-r*k), (renderCoords.getY()-r*k), 2*r*k, 2*r*k);
                
            }

            
        });

        
    }

    public void selectParticle(Particle p){
        selectedParticle=p;
    }
    public Particle getSelectedParticle(){
        return selectedParticle;
    }

}
