package app.matricesofgraphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class MinSpanTree {
    private int[][] am;

    private String pathV;

    public void setAm(int[][] am) {
        this.am = am;
    }

    public String getPathV() {
        return pathV;
    }

    public void spanTree(){
        int l = 0;

        //int[][] ap = new int[am.length-2][2];
        ArrayList<int[]> ap = new ArrayList<>();

        boolean[] xp = new boolean[am.length];

        ArrayList<Integer> _xp = new ArrayList<Integer>();

        int[] b = new int[am.length];

        //
        ArrayList<int[]> c = new ArrayList<>();

        int lc = Integer.MAX_VALUE;

        int[] minC = new int[3];
        //

        xp[0] = true;

        b[0] = 1;

        for (int n = 1; n < am.length; n++) {
            //Fill _xp array -- non true _xp
            for (int i = 0; i < xp.length; i++) {
                if (xp[i]==true){
                    for (int j = 0; j < am.length; j++) {
                        if (am[i][j]!=0 /*&& i!=j*/){
                            _xp.add(j);
                        }
                    }
                }
            }
            //Cut _xp array -- true _xp (if node in xp then cut this node in _xp)
            for (int i = 0; i < xp.length; i++) {
                if (xp[i]==true){
                    int finalI = i;
                    _xp.removeIf(d -> (d == finalI));
                }
            }

            //Remove duplicates from _xp
            //Create linked hash set
            Set<Integer> set = new LinkedHashSet<Integer>();
            //Fill set
            set.addAll(_xp);
            //Delete all array
            _xp.clear();
            //Add to array (with no duplicates)
            _xp.addAll(set);
            ///////////


            ///Get all paths into arraylist
            for (int i = 0; i < xp.length; i++) {
                if (xp[i]==true){
                    for (int j = 0; j < am.length/*_xp.size()*/; j++) {
                        int finalJ = j; //who ?
                        if (am[i][j]!=0 && _xp.stream().anyMatch(k -> k==finalJ)){
                            c.add(new int[] {am[i][j], i, j});
                        }
                    }
                }

            }

            //
            for (int i = 0; i < c.size(); i++) {
                if ((c.get(i))[0] < lc){
                    lc = (c.get(i))[0];
                    //minC = c.get(i); //COPY OF ARRAY //WARNING
                    for (int j = 0; j < 3; j++) {
                        minC[j] = (c.get(i))[j];
                    }
                }

            }

            l+= lc;

            ap.add(new int[] {minC[1], minC[2]});

            b[minC[2]] = 1;
            xp[minC[2]] = true;
            lc = Integer.MAX_VALUE;
            _xp.clear();
            c.clear();
        }

        System.out.println("example");

        ///////
        //Get string for WebView
        pathV = "<h1>A<sub>p</sub>={";

        pathV+="(x<sub>"+((ap.get(0))[0]+1)+"</sub>, x<sub>"+((ap.get(0))[1]+1)+"</sub>)";

        for (int i = 1; i < ap.size(); i++) {
            pathV+="; (x<sub>"+((ap.get(i))[0]+1)+"</sub>, x<sub>"+((ap.get(i))[1]+1)+"</sub>)";
        }
        pathV+="}<br>";
        
        pathV+= "X<sub>p</sub>={";

        pathV+="x<sub>1</sub>";
        for (int i = 1; i < xp.length; i++) {
            pathV+= ", x<sub>"+(i+1)+"</sub>";
        }

        pathV+="}<br>L="+l;

        pathV+="</h1>";

    }
}
