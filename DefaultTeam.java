package algorithms;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

/***************************************************************
 * TME 1: calcul de diamètre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de  *
 *     points donné en entrée.                                 *
 *   - Couvrir l'ensemble de poitns donné en entrée par un     *
 *     cercle de rayon minimum.                                *
 *                                                             *
 * class Circle:                                               *
 *   - Circle(Point c, int r) constructs a new circle          *
 *     centered at c with radius r.                            *
 *   - Point getCenter() returns the center point.             *
 *   - int getRadius() returns the circle radius.              *
 *                                                             *
 * class Line:                                                 *
 *   - Line(Point p, Point q) constructs a new line            *
 *     starting at p ending at q.                              *
 *   - Point getP() returns one of the two end points.         *
 *   - Point getQ() returns the other end point.               *
 ***************************************************************/
import supportGUI.Circle;
import supportGUI.Line;


public class DefaultTeam {

  // calculDiametre: ArrayList<Point> --> Line
  //   renvoie une paire de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }
    Line line=null;
    double diametre =0;

    for(int i=0; i< points.size(); i++){
      for(int j=0;j<points.size();j++){
        Line lineTemp= new Line(points.get(i),points.get(j));
        double temp =Math.sqrt(Math.pow(lineTemp.getP().getX() - lineTemp.getQ().getX(), (double)2.0F) +
                               Math.pow(lineTemp.getP().getY() - lineTemp.getQ().getY(), (double)2.0F));


        if(diametre<temp) {
          diametre = temp;
          line = lineTemp;

        }
      }
    }
  return line;
  }





  //pour tous les points, calculer le diametre le plus grand et dessiner e cerce qui enC
  // calculCercleMin: ArrayList<Point> --> Circle
  //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> points) {
    return b_minidisk(points,new ArrayList<Point>());
  }
    /**if (points.isEmpty()) {
      return null;
    }
    Line line= calculDiametre(points);
    /**rayon du cercle**/
   /** int  baricentre= (int)Math.round(Math.sqrt(Math.pow(line.getP().getX() - line.getQ().getX(), 2) +
                                  Math.pow(line.getP().getY() - line.getQ().getY(), 2)) /2);

    /** coord du centre du cercle**/
   /** int x_cord= (int)Math.round((line.getP().getX()+line.getQ().getX())/2);
    int y_cord= (int)Math.round((line.getP().getY()+line.getQ().getY())/2);
    Point center= new Point(x_cord,y_cord);
    int radius=baricentre;

    /**verifier que le cercle recouvre bien tout les points**/
   /** for(int i=0;i<points.size();i++){
      //delta est la distance entre un point du plan et le centre de cercle actuel
      int delta= (int)Math.round(Math.sqrt(Math.pow(points.get(i).getX() - x_cord,2) +
              Math.pow(points.get(i).getY() - y_cord,2)));

      if(delta>radius){
        radius=delta;
        break;
      }
    }
    /**tous les points sont dans le cercle donc le renvoyer**/
  /**  if(radius==baricentre) return new Circle(center,radius);

    /**calculer tous les cercles circonscrit**/
    /**for(int u=0; u<points.size();u++){//point un
      for(int d=0;d<points.size();d++){//point deux
        for(int t=0;t<points.size();t++){//point trois
          double ux=points.get(u).getX();
          double uy=points.get(u).getY();
          double dx=points.get(d).getX();
          double dy=points.get(d).getY();
          double tx=points.get(t).getX();
          double ty=points.get(t).getY();

          /** Calcule des coordonnées du cenre du cercle circonscrit**/
         /** double detreminant= 2*(ux*(dy-ty)+ ux*(ty-uy) + tx*(uy-dy));

          int x_cicrumcenter= (int)Math.round( (
                                              (Math.pow(ux,2) + Math.pow(uy,2))*
                                                      (dy-ty)
                                                      +
                                                      (Math.pow(dx,2)+Math.pow(dy,2))*
                                                              (ty-uy)
                                                      +
                                                      (Math.pow(tx,2) + Math.pow(ty,2))*
                                                              (uy-dy)
                                                       )
                                                        /detreminant);
          int y_cicrumcenter= (int)Math.round( (
                                              (Math.pow(ux,2) +Math.pow(uy,2))*
                                                      (tx-dx)
                                                      +
                                                      (Math.pow(dx,2) +Math.pow(dy,2))*
                                                              (Math.pow(ux,2)-Math.pow(tx,2))
                                                      +
                                                      (Math.pow(tx,2)+Math.pow(ty,2))*
                                                              (dx-ux)
                                              )
                                                /detreminant);

          center=new Point(x_cicrumcenter,y_cicrumcenter);

            /** rayon du cercle circonscrit actuel**/

           /** int rayonTemp=(int)Math.round(Math.sqrt(
                                                  Math.pow(ux - x_cicrumcenter, 2)
                                                          +
                                                          Math.pow(uy - y_cicrumcenter, 2)));

            radius=rayonTemp;
            /** tester si tous les points entre dans le cercle circonscrit**/
          /**  for(int p=0; p<points.size();p++){

              int delta= (int)Math.round(Math.sqrt(
                              Math.pow(points.get(p).getX() - x_cicrumcenter, 2)
                              +
                              Math.pow(points.get(p).getY() - y_cicrumcenter, 2)
                              )
                              );

              if(delta>rayonTemp){
                radius=delta;
                break;
              }

            }
             if(radius==rayonTemp) return  new Circle(center,radius);

        }
      }
    }


    return new Circle(center,radius);

  }**/
          private Circle tme1exercice5(ArrayList<Point> points,ArrayList<Point> boundary){
            /**calculer le cercle min de points**/
            if(points.isEmpty()) return addBoundary(null,boundary);
            ArrayList<Point> rest = (ArrayList<Point>)points.clone();
            Point dummy=rest.get(0);
            Point p=dummy;
            for (Point s: rest) if (dummy.distance(s)>dummy.distance(p)) p=s;
            Point q=p;
            for (Point s: rest) if (p.distance(s)>p.distance(q)) q=s;
            double cX=.5*(p.x+q.x);
            double cY=.5*(p.y+q.y);
            double cRadius=.5*p.distance(q);
            rest.remove(p);
            rest.remove(q);
            while (!rest.isEmpty()){
              Point s=rest.remove(0);
              double distanceFromCToS=Math.sqrt((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY));
              if (distanceFromCToS<=cRadius) continue;
              double cPrimeRadius=.5*(cRadius+distanceFromCToS);
              double alpha=cPrimeRadius/(double)(distanceFromCToS);
              double beta=(distanceFromCToS-cPrimeRadius)/(double)(distanceFromCToS);
              double cPrimeX=alpha*cX+beta*s.x;
              double cPrimeY=alpha*cY+beta*s.y;
              cRadius=cPrimeRadius;
              cX=cPrimeX;
              cY=cPrimeY;
            }
           Circle finaleCercle= new Circle(new Point((int)cX,(int)cY),(int)cRadius);
            /** ajouter les points de boundary**/
            if(boundary.isEmpty() )return finaleCercle;
           return addBoundary(finaleCercle,boundary);
          }


  private Circle addBoundary(Circle cercle,ArrayList<Point> boundary){
            if(cercle==null) return tme1exercice5(boundary, new ArrayList<>());
            Circle newcercle=null;
            for (Point p : boundary) {
              double distanceFromCenter = Math.sqrt((p.getX()-cercle.getCenter().getX())*
                                              (p.getX()-cercle.getCenter().getX())
                                              +
                                              (p.getY()-cercle.getCenter().getY())*
                                                      (p.getY()-cercle.getCenter().getY())); ;
              if (distanceFromCenter > cercle.getRadius()) {

                int Maj_rayon = (int) (distanceFromCenter);


                double alphaX = p.x - cercle.getCenter().getX();
                double alphaY = p.y - cercle.getCenter().getY();

                double facteur = Maj_rayon / distanceFromCenter;
                int cx = (int) (cercle.getCenter().getX() + alphaX * facteur);
                int cy = (int) (cercle.getCenter().getY() + alphaY * facteur);
                newcercle= new Circle (new Point(cx,cy),Maj_rayon);

        }


      }
    return newcercle;
    }





          private Circle b_minidisk(ArrayList<Point> points, ArrayList<Point> boundary){
            Circle cercle;
            if (points.isEmpty()) {
              System.out.println("cas de base");
              cercle = tme1exercice5( new ArrayList<Point>(),boundary);
            }else{
              System.out.println("hey");
              Point p_random= points.remove((int)Math.random()* points.size());
              cercle= b_minidisk(points,boundary);
              double center_to_p= Math.sqrt((p_random.getX()-cercle.getCenter().getX())*
                                            (p_random.getX()-cercle.getCenter().getX())
                                            +
                                              (p_random.getY()-cercle.getCenter().getY())*(p_random.getY()-cercle.getCenter().getY()));
              if(cercle.getRadius()<center_to_p){
                ArrayList<Point> Union_boundary= new ArrayList<Point>();
                Union_boundary.addAll(boundary);
                Union_boundary.add(p_random);
                cercle=b_minidisk(points,Union_boundary);

              }
            }
            return cercle;
          }
}
