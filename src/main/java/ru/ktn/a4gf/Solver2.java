package ru.ktn.a4gf;


import android.util.Pair;
import ru.ktn.a4gf.Solver.*;

public class Solver2 {

    public StringBuilder strb;

    public Solver2() {
        strb = new StringBuilder(10000);
    }

    @Override
    public String toString() {
        return strb.toString();
    }

    public void correct_space(int n, int space) {
        if (n < 0) {
            space--;
        }
        space++;
        while (n > 9) {
            n /= 10;
            space--;
        }

        while (space >= 0) {
            strb.append(" ");
            space--;
        }
    }
    public void correct_space(long n, long space) {
        if (n < 0) {
            space--;
        }
        space++;
        while (n > 9) {
            n /= 10;
            space--;
        }

        while (space >= 0) {
            strb.append(" ");
            space--;
        }
    }

    public boolean check_t(Pair<Integer, Integer> t, int a, int b, int p){
        if(p != 2 && p != 3){
            int y = t.second.intValue() * t.second.intValue();
            y = Solver.to_mod(y, p);

            int x = t.first.intValue() * t.first.intValue() * t.first.intValue() + a * t.first.intValue() + b;
            x = Solver.to_mod(x, p);

            if(x == y){
                return true;
            }
        }

        return false;
    }

    public Pair<Integer, Integer> double_t(Pair<Integer, Integer> t, int a, int b, int p){
        if(check_t(t, a, b, p) == false){
            return Pair.create(Integer.valueOf(-1), Integer.valueOf(-1));
        }


        int x1 = t.first.intValue();
        int y1 = t.second.intValue();

        int K = Solver.devide_mod((3 * x1 * x1 + a), (2 * y1), p);

        int x2 = K * K - 2 * x1;
        x2 = Solver.to_mod(x2, p);

        int y2 = K * (x1 - x2) - y1;
        y2 = Solver.to_mod(y2, p);

        return Pair.create(Integer.valueOf(x2), Integer.valueOf(y2));
    }

    public Pair<Integer, Integer> plus_t(Pair<Integer, Integer> t1, Pair<Integer, Integer> t2, int a, int b, int p){
        if(check_t(t1, a, b, p) == false){
            return Pair.create(Integer.valueOf(-1), Integer.valueOf(-1));
        }
        if(check_t(t2, a, b, p) == false){
            return Pair.create(Integer.valueOf(-1), Integer.valueOf(-1));
        }

        if(t1.first == t2.first && t1.second == t2.second){
            return double_t(t1, a, b, p);
        }


        int x1 = t1.first.intValue();
        int x2 = t2.first.intValue();
        int y1 = t1.second.intValue();
        int y2 = t2.second.intValue();

        int K = Solver.devide_mod((y2 - y1), (x2 - x1), p);
        if(K == -1){
            return Pair.create(Integer.valueOf(-1), Integer.valueOf(-1));
        }

        int x3 = K * K - x1 - x2;
        x3 = Solver.to_mod(x3, p);

        int y3 = K * (x1 - x3) - y1;
        y3 = Solver.to_mod(y3, p);

        return Pair.create(Integer.valueOf(x3), Integer.valueOf(y3));
    }

    public Pair<Integer, Integer> multiple_t(Pair<Integer, Integer> t, int val, int a, int b, int p){
        if(check_t(t, a, b, p) == false){
            return Pair.create(Integer.valueOf(-1), Integer.valueOf(-1));
        }

        int count = 1, c2, temp = 1;
        while (temp <= val){
            temp *= 2;
            count++;
        }
        temp /= 2;
        count--;

        Pair<Integer, Integer> mas[] = new Pair[count];
        Pair<Integer, Integer> res;

        mas[0] = t;
        for(int i = 1; i < count; ++i){
            mas[i] = plus_t(mas[i - 1], mas[i - 1], a, b, p);
            if(mas[i].first == -1){
                return Pair.create(Integer.valueOf(-1), Integer.valueOf(-1));
            }
        }

        res = mas[count - 1];
        val -= temp;

        while (val > 0){
            c2 = 1;
            temp = 1;
            while (c2 <= val){
                c2++;
                temp *= 2;
            }
            temp /= 2;
            c2--;

            res = plus_t(res, mas[c2 - 1], a, b, p);
            val -= temp;
        }

        return res;
    }

    public void find_all_t(int a, int b, int p){
        int[] x_mas = new int[p];
        int[] y_mas = new int[p - 1];

        for(int x = 0; x < p; ++x){
            x_mas[x] = Solver.to_mod((x * x * x + a * x + b), p);
        }
        strb.append("\nx  |  x^3 + " + a + "x + " + b);
        for(int i = 0; i < p; ++i){
            strb.append("\n" + i + "  |  " + x_mas[i]);
        }


        for(int y = 1; y < p; ++y){
            y_mas[y - 1] = Solver.to_mod(y * y, p);
        }
        strb.append("\n\ny  |  y^2");
        for(int i = 0; i < p - 1; ++i){
            strb.append("\n" + (i + 1) + "  |  " + y_mas[i]);
        }
        strb.append("\n\nСами точки:\n");
        boolean flag = false;
        for(int i = 0; i < p; ++i){
            for(int g = 0; g < p - 1; ++g){
                if(x_mas[i] == y_mas[g]){
                    strb.append("(" + i + ", " + (g + 1) + ")  ");
                    flag = true;
                }
            }
            if(flag) strb.append("\n");
            flag = false;
        }

    }

    public int order_t(Pair<Integer, Integer> t, int a, int b, int p){
        if(check_t(t, a, b, p) == false){
            return -1;
        }

        int N = p + 1 + 2 * (int)Math.sqrt(p);
        int m = (int)Math.sqrt(N) + 1;
        //Здесь может быть косяк

        Pair<Integer, Integer> mas[] = new Pair[m + 1];

        mas[0] = t;
        for(int i = 1; i < m; ++i){
            mas[i] = plus_t(mas[i - 1], mas[0], a, b, p);
            if(mas[i].first == -1){
                return -1;
            }
        }

        Pair<Integer, Integer> alfa = new Pair<>(mas[m - 1].first, p - mas[m - 1].second);
        Pair<Integer, Integer> sigma = alfa;

        int i = 0;
        while(i < 100){
            i++;

            for(int j = 0; j < m; ++j){
                if(sigma.first == mas[j].first && sigma.second == mas[j].second){
                    return m * i + j + 1;
                }
            }

            sigma = plus_t(sigma, alfa, a, b, p);
        }

        return -1;
    }
    public int order_t_view(Pair<Integer, Integer> t, int a, int b, int p){
        if(check_t(t, a, b, p) == false){
            strb.append("\nТочка не кривая\n");
            return -1;
        }

        int N = p + 1 + 2 * (int)Math.sqrt(p);
        int m = (int)Math.sqrt(N) + 1;
        //Здесь может быть косяк

        strb.append("\nN = " + N);
        strb.append("\nm = " + m);

        Pair<Integer, Integer> mas[] = new Pair[m + 1];

        mas[0] = t;
        for(int i = 1; i < m; ++i){
            mas[i] = plus_t(mas[i - 1], mas[0], a, b, p);
            if(mas[i].first == -1){
                return -1;
            }
        }

        strb.append("\n\nj");
        correct_space(1, 7);
        for(int i = 0; i < m; ++i){
            strb.append(i + 1);
            correct_space(i + 1, 7);
        }

        strb.append("\n\njP");
        correct_space(20000, 7);
        for(int i = 0; i < m; ++i){
            strb.append("(" + mas[i].first + " " + mas[i].second + ")");
            correct_space(6000000, 7);
        }

        Pair<Integer, Integer> alfa = new Pair<>(mas[m - 1].first, p - mas[m - 1].second);
        Pair<Integer, Integer> sigma = alfa;

        strb.append("\n\nα = " + alfa + "\n");

        int i = 0;
        while(i < 100){
            i++;

            strb.append("\ni = " + i);
            strb.append("\nγ = " + sigma + "\n");
            for(int j = 0; j < m; ++j){
                if(sigma.first == mas[j].first && sigma.second == mas[j].second){
                    return m * i + j + 1;
                }
            }

            sigma = plus_t(sigma, alfa, a, b, p);
        }

        return -1;
    }
}
