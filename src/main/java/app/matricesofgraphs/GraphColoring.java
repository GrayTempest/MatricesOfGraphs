package app.matricesofgraphs;

import java.util.ArrayList;

public class GraphColoring {
    private int[][] am;

    private String pathV;

    public void setAm(int[][] am) {
        this.am = am;
    }

    public String getPathV() {
        return pathV;
    }

    private void toSwap(int first, int second, ArrayList<int[]> a){ //метод меняет местами пару чисел массива
        //long dummy = a[first];      //во временную переменную помещаем первый элемент
        //a[first] = a[second];       //на место первого ставим второй элемент
        //a[second] = dummy;          //вместо второго элемента пишем первый из временной памяти

        int[] dummy = a.get(first);
        a.set(first, a.get(second));
        a.set(second, dummy);
    }

    public void bubbleSorter(ArrayList<int[]> a){     //МЕТОД ПУЗЫРЬКОВОЙ СОРТИРОВКИ
        for (int out = a.size() - 1; out >= 1; out--){  //Внешний цикл
            for (int in = 0; in < out; in++){       //Внутренний цикл
                if(a.get(in)[1] > a.get(in+1)[1])               //Если порядок элементов нарушен
                    toSwap(in, in + 1, a);             //вызвать метод, меняющий местами
            }
        }
    }

    public void coloring(){
        ArrayList<int[]> xd = new ArrayList<>();

        ArrayList<int[]> pa = new ArrayList<>();

        //colors variables
        int pp = 0;
        int ii = 0;

        int count = 0;

        for (int i = 0; i < am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                if (am[i][j]!=0){
                   count++;
                }
            }
            xd.add(new int[] {i, count});
        }

        bubbleSorter(xd);


    }
}
