package app.matricesofgraphs;

public class MatricesConversion {
    private int[][] im;
    private int[][] am;

    public void setAm(int[][] am) {
        this.am = am;
    }

    public void setIm(int[][] im) {
        this.im = im;
    }

    public int[][] getAm() {
        return am;
    }

    public int[][] getIm() {
        return im;
    }

    public int[][] convertIMtoAM(){
        int rows = im.length;
        int columns = im[0].length;

        int[][] am = new int[rows][rows];

        //Set 2 variables to store
        int inception = -1;
        int end = -1;
        //Флаг для рёбер
        boolean f = false;
        //Fill AM
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (inception == -1 && im[i][j] == 1){
                    inception = i;
                } else if (im[i][j] == -1){
                    end = i;
                } else if (inception != -1 && im[i][j] == 1){
                    end = i;
                    f = true;
                }
            }
            if (end == -1){
                am[inception][inception] = 1;
            } else if (f == true){
                am[inception][end] = 1;
                am[end][inception] = 1;
            } else {
                am[inception][end] = 1;
            }
            inception = -1;
            end = -1;
            f = false;
        }
        System.out.println("sss");
        return am;
    }

}
