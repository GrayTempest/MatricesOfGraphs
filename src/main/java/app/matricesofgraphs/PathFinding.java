package app.matricesofgraphs;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinding {
    private int[][] am;

    public void setAm(int[][] am) {
        this.am = am;
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
            for (int i = am.length-1; i > 0; i--) {
                if (am[i][j] != 0){
                    if ((lambda[j] == lambda[i] + am[i][j]) && pf == false && pe == false) {
                        path.add(i);
                        pf = true;
                        if (i == 1){
                            pe = true;
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
    }


}
