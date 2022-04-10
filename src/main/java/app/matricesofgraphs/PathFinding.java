package app.matricesofgraphs;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinding {
    private int[][] am;

    private String pathV;

    public void setAm(int[][] am) {
        this.am = am;
    }

    public String getPathV() {
        return pathV;
    }

    public void pathFind(){
        int[] lambda = new int[am.length];
        lambda[0] = 0;
        int count = 1;
        for (int j = 1; j < am.length; j++) {
            ArrayList<Integer> aL = new ArrayList<Integer>();
            for (int i = 0; i < am.length; i++) {
                if(am[i][j] != 0){
                    aL.add(am[i][j]+lambda[i]);
                } else {
                    //aL.add(am[i][j]);
                }
            }
            //lambda[count] = Math.min(lambda[]) //TODO found min element of arrayList (aL)
            lambda[count] = Collections.min(aL);
            count++;
        }
        System.out.println(lambda[count-1]); //last element of lambda


        //Finding path itself
        //ArrayList for path points
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(am.length);
        //Path point set flag
        boolean pf = false;
        //End path flag
        boolean pe = false;
        //Loop
        for (int j = am.length-1; j > 0; j--) {
            for (int i = am.length-1; i >= 0; i--) {
                if (am[i][j] != 0){
                    if ((lambda[j] == lambda[i] + am[i][j]) && pf == false && pe == false) {
                        path.add(i+1); //numeration fix
                        pf = true;
                        if (i + 1 == 2){ //If we get 2 then we end loop (because after 2 comes 1)
                            pe = true;
                            path.add(1); //and adding to arraylist "1" as endpoint
                        }
                    }
                }
            }
            pf = false;
        }

        //Print arraylist
        for (int i = path.size(); i > 0; i--) {
            System.out.println(path.get(i-1));
        }

        //Get string for WebView
        pathV = "<h1>&#955(x<sub>";

        pathV+=am.length + "</sub>)="+lambda[am.length-1]+"<br>";

        pathV+="&#956(x<sub>1</sub>, x<sub>";

        pathV+=am.length;

        pathV+="</sub>)={x<sub>1</sub>";
        for (int i = path.size() - 2; i >= 0; i--) {
            pathV+=",x<sub>"+path.get(i)+"</sub>";
        }

        pathV+="}</h1>";
    }


}
