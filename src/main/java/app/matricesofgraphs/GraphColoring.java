package app.matricesofgraphs;

import java.util.ArrayList;

public class GraphColoring {
    private int[][] am;

    private String webViewString;

    public void setAm(int[][] am) {
        this.am = am;
    }

    public String getWebViewString() {
        return webViewString;
    }

    //Метод меняющий местами целочилсенные массивы
    private void toSwap(int first, int second, ArrayList<int[]> a){ //метод меняет местами пару массивов в списке
        int[] dummy = a.get(first); //во временную переменную помещаем первый массив
        a.set(first, a.get(second)); //на место первого массива ставим второй массив
        a.set(second, dummy); //вместо второго массива пишем первый из временной переменной
    }

    //Метод пузырьковой сортировки
    public void bubbleSorter(ArrayList<int[]> a){ //Метод сортировки списка из целочисленных массивов (по 2-ому элементу массива)
        for (int i = a.size() - 1; i >= 1; i--){  //Внешний цикл
            for (int j = 0; j < i; j++){          //Внутренний цикл
                if(a.get(j)[1] > a.get(j+1)[1])   //Если порядок элементов нарушен
                    toSwap(j, j + 1, a);   //то вызваем метод, меняющий местами элементы
            }
        }
    }

    //Метод определяющий есть ли вершина, которая окрашена в текущий цвет, смежная текущей вершине с номером n
    public boolean smezhnost (ArrayList<Integer> xP, int n){
        ArrayList<Integer> sm = new ArrayList<>();
        //Заполнение списка вершин смежных текущей вершине n
        for (int j = 0; j < am.length; j++) {
            if(am[n][j]!=0){
                sm.add(j);
            }
        }

        boolean result = false;

        //Проверка того что есть ли среди смежных вершин вершина окрашенная в текцщий цвет
        for (int i = 0; i < xP.size(); i++) { //Определеям это по тому встречаются ли элементы из списка окрашенных вершин в списке смежных вершин
            result = sm.contains(xP.get(i));
        }
        return result;
    }

    public void coloring(){
        //Array of degree and numbers of nodes array
        //Массив вершин графа с их степенями и цветами
        ArrayList<int[]> xd = new ArrayList<>();

        //colors variables
        //Переменная отвечающая за номер текущего цвета
        int pp = 1;
        //Генерируем первый цвет
        RandomHexColor randomHexColor = new RandomHexColor();
        String currentHexColorString = randomHexColor.getRandomHexColorString();
        //Обрезаем строку и преобразуем в число (Строка в 16-ой системе)
        int currentColor = Integer.parseInt(currentHexColorString.substring(1), 16);

        //Computing degree and Sorting vertices (nodes) by it
        //Вычисление степеней вершин и их сортировка по ним
        int count = 0; //degree number

        for (int i = 0; i < am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                if (am[i][j]!=0){
                   count++;
                }
            }
            xd.add(new int[] {i, count, 0, 0}); //Помещаем в массив массив из номера вершины, её степени, номера цвета, hex значение цвета
            count=0;
        }

        //Sorting by degree
        //Сортировка по степеням
        bubbleSorter(xd);

        //List of nodes colored in current color
        //Список узлов (вершин) окрашенных в текцщий цвет
        ArrayList<Integer> xP = new ArrayList<>();

        //Окраска вершин
        while (xd.stream().anyMatch(s -> s[2] == 0)){ //Пока есть не окрашенные вершины продолжаем
            for (int n = xd.size()-1; n >= 0; n--) { //Цикл по всем вершинам в массиве вершин (в обратном порядке)
                if (!smezhnost(xP, xd.get(n)[0])) { //Если есть хоть одна вершина смежная текцщей и окрашенная в текущий цвет, то пропускаем эту вершину
                    //Если нет, то красим текущую вершину в текущий цвет
                    if (xd.get(n)[2]==0) { //Если текущая вершина не окрашена, то красим
                        xd.set(n, new int[]{xd.get(n)[0],xd.get(n)[1], pp, currentColor }); //Записываем текущий цвет этой вершине в массиве вершин
                        //Помещаем в массив массив из номера вершины, её степени, номера цвета, hex значение цвета

                        xP.add(xd.get(n)[0]); //Записываем вершину в список окрашенных в текущий цвет вершин
                    }
                }
            }
            pp++; //После прохождения всех вершин меняем текущий цвет на следующий
            //Генерируем новый цвет
            currentHexColorString = randomHexColor.getRandomHexColorString();
            currentColor = Integer.parseInt(currentHexColorString.substring(1), 16);
            //
            xP.clear(); //Очищаем список окрашенных в текущий цвет вершин
        }

        //Get string for WebView
        webViewString = "<h1>&#947(G)="+(pp-1)+"<br>";

        for (int i = xd.size()-1; i >= 0; i--) {
            webViewString +="col(x<sub>"+(xd.get(i)[0]+1)+"</sub>)=<span style=";
            webViewString += "\"color: #"+Integer.toHexString(xd.get(i)[3])+"\">";
            webViewString +=(xd.get(i)[2])+"</span>, D="+(xd.get(i)[1])+"<br>";
        }

        webViewString +="</h1>";
    }
}
