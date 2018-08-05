package ru.ktn.a4gf;

import android.util.Pair;

import java.util.Vector;

import static java.lang.Math.sqrt;

public class Solver {

    public StringBuilder strb;

    public Solver() {
        strb = new StringBuilder(10000);
    }

    @Override
    public String toString() {
        return strb.toString();
    }

    public static int is_pow_of_prime(int n) {
        int t = prime(n);
        while (n % t == 0) {
            n /= t;
        }
        if (n == 1) {
            return 1;
        }
        return 0;
    }

    public void correct_space(int n, int space) {
        if (n < 0) {
            space--;
        }
        space--;
        while (n > 9) {
            n /= 10;
            space--;
        }

        while (space > 0) {
            strb.append(" ");
            space--;
        }
    }
    public void correct_space(long n, long space) {
        if (n < 0) {
            space--;
        }
        space--;
        while (n > 9) {
            n /= 10;
            space--;
        }

        while (space > 0) {
            strb.append(" ");
            space--;
        }
    }
    public void correct_space2(int count, int space){
        if(count >= space){
            strb.append(" ");
        }
        else {
            for(int i = 0; i < (space - count); ++i){
                strb.append(" ");
            }
        }
    }
    public void correct_space_br(int n, int space) {
        if (n < 0) {
            space--;
        }
        space--;
        while (n > 9) {
            n /= 10;
            space--;
        }

        while (space > 0) {
            strb.append("&nbsp;");
            space--;
        }
    }
    public void correct_space_br(long n, long space) {
        if (n < 0) {
            space--;
        }
        space--;
        while (n > 9) {
            n /= 10;
            space--;
        }

        while (space > 0) {
            strb.append("&nbsp;");
            space--;
        }
    }
    public void correct_space2_br(int count, int space){
        if(count >= space){
            strb.append("&nbsp;");
        }
        else {
            for(int i = 0; i < (space - count); ++i){
                strb.append("&nbsp;");
            }
        }
    }

    public static int nod(int a, int b) {
        int c = b;

        while (a % b != 0) {
            c = a % b;
            a = b;
            b = c;
        }

        return c;
    }
    public static long nod(long a, long b) {
        long c = b;

        while (a % b != 0) {
            c = a % b;
            a = b;
            b = c;
        }

        return c;
    }

    public static int prime(int n) {
        if (n % 2 == 0 && n != 2) {
            return 2;
        }
        for (int i = 3; i <= sqrt((double)n) + 1; i += 2) {
            if (n % i == 0) {
                return i;
            }
        }
        return 0;
    }

    public static Vector<Integer> to_primes(int n){
        Vector<Integer> v = new Vector<>();
        int t;
        boolean f = false;

        while((t = prime(n)) != 0){
            n /= t;

            for(int i = 0; i < v.size(); ++i){
                if(nod(t, v.get(i).intValue()) != 1){
                    Integer a = new Integer(v.get(i).intValue() * t);
                    v.set(i, a);
                    f = true;
                    break;
                }
            }

            if(f == true){
                f = false;
                continue;
            }

            Integer a = new Integer(t);
            v.add(a);
        }

        for(int i = 0; i < v.size(); ++i){
            if(nod(n, v.get(i).intValue()) != 1){
                Integer a;
                if(t != 0){
                    a = new Integer(v.get(i).intValue() * t);
                }
                else{
                    a = new Integer(v.get(i).intValue() * n);
                }

                v.set(i, a);

                return v;
            }
        }

        Integer a = new Integer(n);
        v.add(a);

        return v;
    }

    public static int to_mod(int n, int mod) {
        while (n < 0) n += 10 * mod;
        n %= mod;
        return n;
    }
    public static long to_mod(long n, long mod) {
        while (n < 0) n += 10 * mod;
        n %= mod;
        return n;
    }

    public static int to_pow(int n, int pow) {
        if (pow == 0) {
            return 1;
        }
        else if(pow == 1){
            return n;
        }

        if (n == -1) {
            if (pow % 2 == 0) return 1;
            return -1;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            int res = n;
            while (--pow > 0) res *= n;
            return res;
        }
    }
    public static int to_pow_mod(int n, int pow, int mod) {
        if (pow == 0) {
            return 1;
        }

        n %= mod;

        if (n == -1) {
            if (pow % 2 == 0) return 1;
            return -1;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            int res = n;
            if(pow > mod - 1)
                pow %= mod - 1;
            while (--pow > 0) {
                res *= n;
                res %= mod;
            }
            return res;
        }
    }
    public static long to_pow_mod(long n, long pow, long mod) {
        if (pow == 0) {
            return 1;
        }

        n %= mod;

        if (n == -1) {
            if (pow % 2 == 0) return 1;
            return -1;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            long res = n;
            pow %= mod - 1;
            while (--pow > 0) {
                res *= n;
                res %= mod;
            }
            return res;
        }
    }
    public static int to_pow_minus(int n, int pow, int mod) {
        int pow2 = to_pow_mod(n, pow, mod);
        int t = 1;
        while (t % pow2 != 0) t += mod;
        return t / pow2;
    }
// Внеснеы изменения - может перестать работать)

    public static int devide_mod(int n_up, int n_down, int mod){
        n_up = to_mod(n_up, mod);
        n_down = to_mod(n_down, mod);

        if(n_down == 0){
            return -1;
        }

        while(n_up % n_down != 0) n_up += mod;

        return n_up / n_down;
    }
    public static long devide_mod(long n_up, long n_down, long mod){
        n_up = to_mod(n_up, mod);
        n_down = to_mod(n_down, mod);

        if(n_down == 0){
            return -1;
        }

        while(n_up % n_down != 0) n_up += mod;

        return n_up / n_down;
    }


    public static int system_China(Vector<Pair<Integer, Integer>> v){

        for(int i = 0; i < v.size(); ++i){
            for(int g = i + 1; g < v.size(); ++g){
                if(nod(v.get(i).second.intValue(), v.get(g).second.intValue()) != 1){
                    return 0;
                }
            }
        }

        int M = 1;
        for(int i = 0; i < v.size(); ++i){
            M *= v.get(i).second.intValue();
        }

        int N[] = new int[v.size()];
        int m[] = new int[v.size()];
        int C = 0;
        for(int i = 0; i < v.size(); ++i){
            m[i] = M / v.get(i).second.intValue();
            N[i] = evklid(m[i], 1, v.get(i).second.intValue());
            C += v.get(i).first.intValue() * m[i] * N[i];
            C %= M;
        }

        return C;
    }
    public int system_China_view(Vector<Pair<Integer, Integer>> v){

        for(int i = 0; i < v.size(); ++i){
            for(int g = i + 1; g < v.size(); ++g){
                if(nod(v.get(i).second.intValue(), v.get(g).second.intValue()) != 1){
                    strb.append("\n(m" + (i + 1) + ", m" + (g + 1) + ") != 1");
                    return 0;
                }
            }
        }

        long M = 1;
        for(int i = 0; i < v.size(); ++i){
            M *= v.get(i).second.intValue();
        }

        strb.append("\nM = " + M + "\n");

        int N[] = new int[v.size()];
        int m[] = new int[v.size()];
        int C = 0;
        for(int i = 0; i < v.size(); ++i){
            m[i] = (int)(M / (long)v.get(i).second.intValue());
            N[i] = evklid(m[i], 1, v.get(i).second.intValue());
            C += v.get(i).first.intValue() * m[i] * N[i];
            C %= M;

            strb.append("\nM" + (i + 1) + " = " + m[i]);
            strb.append("\nN" + (i + 1) + " = " + m[i]);
            strb.append("\nC" + (i + 1) + " * M"  + (i + 1) + " * N"  + (i + 1) + " = " + v.get(i).first.intValue() * m[i] * N[i] + "\n");
        }

        return C;
    }

    public static int evklid(int a, int b, int p_){
        a = to_mod(a, p_);
        b = to_mod(b, p_);

        int c = p_, x0 = 1, x1 = 0, x2, d, p = p_, a_ = a;

        while (a % p != 0) {
            c = a % p;
            d = a / p;
            a = p;
            p = c;


            x2 = x0 - x1 * d;
            x0 = x1;
            x1 = x2;

        }

        x1 *= b;
        x1 = to_mod(x1, p_);

        if(to_mod(a_ * x1, p_) == b){
            return x1;
        }

        return -1;
    }
    public int evklid_view(int a, int b, int p_){
        a = to_mod(a, p_);
        b = to_mod(b, p_);

        int c = p_, x0 = 1, x1 = 0, x2, d, p = p_, a_ = a;
        int i = -2;
        while (a % p != 0) {
            i += 1;

            c = a % p;
            d = a / p;
            strb.append("\n" + a + " = " + p + " * " + d + " + " + c);
            a = p;
            p = c;

            strb.append("\n    x[" + i + "] = " + x0);
            strb.append("\n    x[" + (i + 1) + "] = " + x1);

            x2 = x0 - x1 * d;
            x0 = x1;
            x1 = x2;

            strb.append("\n    x[" + (i + 2) + "] = " + x2);
        }

        x1 *= b;
        x1 = to_mod(x1, p_);

        if(to_mod(a_ * x1, p_) == b){
            return x1;
        }

        return -1;
    }

    public static int symbol_L(int n, int mod) {
        n = to_mod(n, mod);
        if(n == 0 || mod <= 2 || (prime(mod) != 0)){
            return 0;
        }

        int res = 1, t;
        while (true) {
            if (n < 0) {
                if ((mod - 1) % 4 != 0) res *= -1;

                n *= -1;
                n = to_mod(n, mod);
            }
            else if (n == 1) {
                return res;
            }
            else if (n == 2) {
                if(nod(n, mod) != 1){
                    return 0;
                }

                if ((mod * mod - 1) % 16 == 0) return res;

                res *= -1;
                return res;
            }
            else if ((t = prime(n)) != 0) {
                n /= t;
                res *= symbol_L(t, mod);
            }

            else if(nod(n, mod) == 1) {
                if (((n - 1) * (mod - 1)) % 8 != 0) res *= -1;

                t = n;
                n = mod;
                mod = t;

                n = to_mod(n, mod);
            }
            else {
                return 0;
            }
        }
    }
    public static int symbol_J(int n, int mod) {
        n = to_mod(n, mod);
        if(n == 0 || (mod % 2 == 0)){
            return 0;
        }

        int res = 1, t, c;
        while (true) {
            if (n < 0) {
                if ((mod - 1) % 4 != 0) res *= -1;

                n *= -1;
                n = to_mod(n, mod);
            }
            else if (n == 1) {
                return res;
            }
            else if (n == 2) {
                if ((mod * mod - 1) % 16 == 0) return res;

                res *= -1;
                return res;
            }
            else if (n % 2 == 0) {
                c = 0;
                while (n % 2 == 0) {
                    c++;
                    n /= 2;
                }

                if (c % 2 == 1) res *= symbol_J(2, mod);
            }
            else if(nod(n, mod) == 1) {
                if (((n - 1) * (mod - 1)) % 8 != 0) res *= -1;

                t = n;
                n = mod;
                mod = t;

                n = to_mod(n, mod);
            }
            else {
                return 0;
            }
        }
    }

    public static int linear(int a, int b, int mod) {
        a = to_mod(a, mod);
        b = to_mod(b, mod);

        int i = mod;
        while (b % a != 0 && i-- >= 0) {
            b += mod;
        }

        if (i >= 0) {
            return b / a;
        }
        return -1;
    }

    public static int quadrant(int a, int mod) {
        a %= mod;
        //Был символ Лежандра - переехал

        int t;
        if ((t = prime(mod)) != 0) {
            if (is_pow_of_prime(mod) == 1) {
                    if (symbol_L(a, t) != 1) {
                        return 0;
                    }

                    int x1 = quadrant(a, t);
                    if(x1 == 0 && a != 0){
                        return 0;
                    }
                    int k1 = evklid(2 * x1, (x1 * x1 - a) / -t, t);
                    int res = x1 + t * k1;
                    res = to_mod(res, mod);

                    if (t * t == mod) {
                        return res;
                    }

                    int x2 = res;
                    int k2 = evklid(2 * x2, (x2 * x2 - a) / (-t * t), t);
                    res = res + t * t * k2;
                    res = to_mod(res, mod);

                    if (t * t * t == mod) {
                        return res;
                    }

                    return 0;
            }

            Vector<Integer> v_prime = to_primes(mod);
            Vector<Pair<Integer, Integer>> vec = new Vector<>(v_prime.size());

            for(int i = 0; i < v_prime.size(); ++i){
                vec.add(Pair.create(Integer.valueOf(quadrant(a, v_prime.get(i).intValue())), v_prime.get(i)));

                if(vec.get(i).first == 0){
                    return 0;
                }
            }

            return system_China(vec);
        }
        else {
            if (symbol_L(a, mod) != 1) {
                return 0;
            }

            if ((mod - 1) % 8 == 0) {
                int h = mod - 1, k = 0, N = 0, N1, N2 = 1, a1, a2, j = 0;
                while (h % 2 == 0) {
                    k++;
                    h /= 2;
                }
                while (symbol_L(++N, mod) == 1) ;


                N1 = to_pow_mod(N, h, mod);

                a1 = to_pow_mod(a, (h + 1) / 2, mod);

                a2 = to_pow_minus(a, 1, mod);

                int b, c, d, res;
                for (int i = 0; i <= k - 2; ++i) {
                    b = a1 * N2;
                    b = to_mod(b, mod);

                    c = a2 * b * b;
                    c = to_mod(c, mod);

                    d = to_pow_mod(c, to_pow(2, (k - 2 - i)), mod);

                    if (d == 1) {
                        j = 0;
                    } else {
                        j = 1;
                    }

                    N2 = N2 * to_pow_mod(N1, to_pow(2, i) * j, mod);
                    N2 = to_mod(N2, mod);
                }
                res = a1 * N2;
                res = to_mod(res, mod);
                return res;
            }

            if ((mod - 3) % 4 == 0) {
                int m = (mod - 3) / 4;
                return to_pow_mod(a, m + 1, mod);
            }

            if ((mod - 5) % 8 == 0) {
                int m = (mod - 5) / 8, res;
                if (to_pow_mod(a, 2 * m + 1, mod) == 1) {
                    return to_pow_mod(a, m + 1, mod);
                }
                res = to_pow_mod(a, m + 1, mod) * to_pow_mod(2, 2 * m + 1, mod);
                res = to_mod(res, mod);
                return res;
            }

            if(mod == 2){
                if(a == 1){
                    return 1;
                }
                return 0;
            }
        }
        return 0;
    }
    public int quadrant_view(int a, int mod) {
        a %= mod;
        //Был символ Лежандра - переехал

        int t;
        if ((t = prime(mod)) != 0) {
            strb.append("<br>p - not prime<br>");

            //region Модуль - степень простого числа
            if (is_pow_of_prime(mod) == 1) {
                if (symbol_L(a, t) != 1) {
                    strb.append("<br>L (" + a + ", :" + t + ") != 1   =>   No answer<br>");
                    return 0;
                }

                int x1 = quadrant(a, t);
                if(x1 == 0 && a != 0){
                    strb.append("<br>No Solve<br>");
                    return 0;
                }
                strb.append("<br>x1<sup><small>2</sup></small> = " + a + " (mod " + t + ")");
                strb.append("<br>x1 = " + x1);
                int k1 = evklid(2 * x1, (x1 * x1 - a) / -t, t);
                strb.append("<br><br>2*k1*x1 = -(x1<sup><small>2</sup></small> - a)/p (mod p)");
                strb.append("<br>k1 = " + k1 + "(Решаем Евклидом)<br>");
                int res = x1 + t * k1;
                res = to_mod(res, mod);

                if (t * t == mod) {
                    strb.append("\nx = x1 + k1*p");
                    return res;
                }

                strb.append("<br>x2 = x1 + k1*p = " + res + "<br>");
                int x2 = res;
                int k2 = evklid(2 * x2, (x2 * x2 - a) / (-t * t), t);
                strb.append("<br>2*k2*x2 = -(x2<sup><small>2</sup></small> - a)/p<sup><small>2</sup></small> (mod p)");
                strb.append("<br>k2 = " + k2 + "(Решаем Евклидом)<br>");
                res = res + t * t * k2;
                res = to_mod(res, mod);

                if (t * t * t == mod) {
                    strb.append("<br>x = x2 + k2*p<sup><small>2</sup></small>");
                    return res;
                }

                strb.append("<br>the degree is too high<br>");
                return 0;
            }
            //endregion

            Vector<Integer> v_prime = to_primes(mod);
            Vector<Pair<Integer, Integer>> vec = new Vector<>(v_prime.size());

            for(int i = 0; i < v_prime.size(); ++i){
                strb.append("----------------" + (i + 1) + "-----------------");
                strb.append("<br>x<sup><small>2</sup></small>" + " ≡ " + a + " (mod " + v_prime.get(i) + ")<br>");
                vec.add(Pair.create(Integer.valueOf(quadrant_view(a, v_prime.get(i).intValue())), v_prime.get(i)));
                if(vec.get(i).first == 0){
                    return 0;
                }
                strb.append("<br>x" + (i + 1) + " ≡ " + vec.get(i).first + " (mod " + vec.get(i).second + ")<br>");
                strb.append("----------------"  + "-----------------<br>");
            }

            return system_China_view(vec);
        }
        else {
            strb.append("<br>p - prime<br>");

            if (symbol_L(a, mod) != 1) {
                strb.append("<br>L (" + a + "," + mod + ") != 1   =>   No answer<br>");
                return 0;
            }

            //region Простой модуль
            if ((mod - 1) % 8 == 0) {
                strb.append("<br>p = 2<sup><small>k</sup></small> * h + 1, k >= 3<br>");
                int h = mod - 1, k = 0, N = 0, N1, N2 = 1, a1, a2, j = 0;
                while (h % 2 == 0) {
                    k++;
                    h /= 2;
                }
                while (symbol_L(++N, mod) == 1) ;

                N1 = to_pow_mod(N, h, mod);

                a1 = to_pow_mod(a, (h + 1) / 2, mod);

                a2 = to_pow_minus(a, 1, mod);


                strb.append("<br>k = " + k + "<br>h = " + h + "<br>N = " + N + " (L(N, p) = -1)<br>N1 = N<sup><small>h</sup></small> = " + N1 + "<br>N2 = " + N2 + "<br>a1 = a<sup><small>((h + 1)/2)</sup></small> = " + a1 + "<br>a2 = a<sup><small>-1</sup></small> = " + a2 + "<br><br>");
                int[] b_m = new int[k - 1];
                int[] c_m = new int[k - 1];
                int[] d_m = new int[k - 1];
                int[] j_m = new int[k - 1];
                int[] N2_m = new int[k - 1];
                ////
                int b, c, d, res;
                for (int i = 0; i <= k - 2; ++i) {
                    b = a1 * N2;
                    b = to_mod(b, mod);

                    c = a2 * b * b;
                    c = to_mod(c, mod);

                    d = to_pow_mod(c, to_pow(2, (k - 2 - i)), mod);

                    if (d == 1) {
                        j = 0;
                    } else {
                        j = 1;
                    }

                    N2 = N2 * to_pow_mod(N1, to_pow(2, i) * j, mod);
                    N2 = to_mod(N2, mod);

                    b_m[i] = b;
                    c_m[i] = c;
                    if (d == 1) {
                        d_m[i] = 1;
                    } else {
                        d_m[i] = -1;
                    }
                    j_m[i] = j;
                    N2_m[i] = N2;
                }
                strb.append("<br>b = a1*N2<br>c = a2*b<sup><small>2</sup></small><br>d = c<sup><small>2^(k-2-i)</sup></small><br>if(d == 1) j = 0 else j = 1<br>N2 = N2*N1<sup><small>(j*2^i)</sup></small><br><br>");

                strb.append("i");
                correct_space2_br(1, 4);

                strb.append("b");
                correct_space2_br(1, 6);

                strb.append("c");
                correct_space2_br(1, 6);

                strb.append("d");
                correct_space2_br(1, 6);

                strb.append("j");
                correct_space2_br(1, 4);

                strb.append("N2<br>");

                strb.append("-------------------------------------<br>                                                                                                  ");

                for (int i = 0; i < k - 1; ++i) {
                    strb.append(i);
                    correct_space_br(i, 4);

                    strb.append(b_m[i]);
                    correct_space_br(b_m[i], 6);

                    strb.append(c_m[i]);
                    correct_space_br(c_m[i], 6);

                    strb.append(d_m[i]);
                    correct_space_br(d_m[i], 6);

                    strb.append(j_m[i]);
                    correct_space_br(j_m[i], 4);

                    strb.append(N2_m[i] + "<br>");
                }

                res = a1 * N2;
                res = to_mod(res, mod);
                return res;
            }

            if ((mod - 3) % 4 == 0) {
                strb.append("<br>p = 4m + 3<br>");
                int m = (mod - 3) / 4;
                strb.append("m = " + m + "<br>");
                strb.append("x = a<sup><small>(m + 1)</sup></small><br>");
                return to_pow_mod(a, m + 1, mod);
            }

            if ((mod - 5) % 8 == 0) {
                strb.append("<br>p = 8m + 5<br>");
                int m = (mod - 5) / 8, res;
                strb.append("m = " + m + "<br>");
                if (to_pow_mod(a, 2 * m + 1, mod) == 1) {
                    strb.append("a<sup><small>(2m + 1)</sup></small> = 1 (mod p) =><br>");
                    strb.append("=> x = a<sup><small>(m + 1)</sup></small> (mod p)<br>");
                    return to_pow_mod(a, m + 1, mod);
                }
                strb.append("a<sup><small>(2m + 1)</sup></small> = -1 (mod p) =><br>");
                strb.append("=> x = a<sup><small>(m + 1)</sup></small> * 2<sup><small>(2m + 1)</sup></small> (mod p)<br>");
                res = to_pow_mod(a, m + 1, mod) * to_pow_mod(2, 2 * m + 1, mod);
                res = to_mod(res, mod);
                return res;
            }
            //endregion

            if(mod == 2){
                if(a == 1){
                    return 1;
                }
                return 0;
            }
        }
        strb.append("<br>Local breakthrough chaos<br>");
        return 0;
    }

    public static int log_equation(int a, int b, int p, int type) {
        switch (type) {
            case (0):
                //region case0
                int H, c;
                H = (int) sqrt((double) p) + 1;
                c = to_pow_mod(a, H, p);

                int[] u = new int[H + 1];
                int[] v = new int[H + 1];

                v[0] = b;
                for (int i = 1; i <= H; ++i) {
                    u[i] = to_pow_mod(c, i, p);
                    v[i] = b * to_pow_mod(a, i, p);
                    v[i] = to_mod(v[i], p);
                }

                for (int i = 1; i <= H; ++i) {
                    for (int g = 0; g <= H; ++g) {
                        if (u[i] == v[g]) {
                            return (H * i - g) % (p - 1);
                        }

                    }
                }
                //endregion
                break;
            case(1):
                //region case1
                Vector<Integer> vec = to_primes(p - 1);
                int t;
                int x_res[] = new int[vec.size()];
                int r_table[];
                int q;
                for(int i = 0; i < vec.size(); ++i){
                    t = prime(vec.get(i).intValue());

                    if(t == 0){
                        r_table = new int[vec.get(i).intValue()];
                        q = vec.get(i).intValue();
                    }
                    else{
                        r_table = new int[t];
                        q = t;
                    }

                    r_table[0] = 1;
                    for(int g = 1; g < r_table.length; ++g){
                        r_table[g] = to_pow_mod(a, g * ((p - 1) / q), p);
                    }

                    //region Какая степень числа
                    int count = 1, t2 = q;
                    while(t2 != vec.get(i).intValue()){
                        t2 *= q;
                        count++;
                    }
                    //endregion

                    int b1 = b, b_r;
                    int x_temp[] = new int[count];
                    for(int g = 0; g < count; ++g){
                        b_r = to_pow_mod(b1, (p - 1) / (to_pow(q, g + 1)), p);
                        for(int k = 0; k < r_table.length; ++k){
                            if(b_r == r_table[k]){
                                x_temp[g] = k;
                            }
                        }

                        b1 *= to_pow_minus(a, x_temp[g] * to_pow(q, g), p);
                    }


                    for(int g = 0; g < count; ++g){
                        x_res[i] += x_temp[g] * to_pow(q, g);
                    }
                    x_res[i] %= vec.get(i).intValue();
                }

                Vector<Pair<Integer, Integer>> result = new Vector<>(vec.size());
                for(int i = 0; i < vec.size(); ++i){
                    result.add(Pair.create(Integer.valueOf(x_res[i]), vec.get(i)));
                }

                return system_China(result);
            //endregion
            case(2):
                //region case2
                int Z_mas[] = new int[1000];
                int U_mas[] = new int[1000];
                int V_mas[] = new int[1000];
                int count = 0;

                Z_mas[0] = 1;
                U_mas[0] = 0;
                V_mas[0] = 0;
                while(count < 1000){
                    if(Z_mas[count] < p / 3){
                        U_mas[count + 1] = U_mas[count] + 1;
                        V_mas[count + 1] = V_mas[count];
                    }
                    else if(Z_mas[count] < (2 * p) / 3){
                        U_mas[count + 1] = 2 * U_mas[count];
                        V_mas[count + 1] = 2 * V_mas[count];
                    }
                    else{
                        V_mas[count + 1] = V_mas[count] + 1;
                        U_mas[count + 1] = U_mas[count];
                    }

                    V_mas[count + 1] %= p - 1;
                    U_mas[count + 1] %= p - 1;

                    Z_mas[count + 1] = (to_pow_mod(b, U_mas[count + 1], p) * to_pow_mod(a, V_mas[count + 1], p)) % p;
                    count++;

                    for(int i = 0; i < count; ++i){
                        if(Z_mas[count] == Z_mas[i]){
                            int U_del = U_mas[count] - U_mas[i];
                            int V_del = V_mas[count] - V_mas[i];

                            if(nod(U_del, p - 1) != 1){
                                break;
                            }

                            return devide_mod(V_del, U_del, p - 1);
                        }
                    }
                }
                //endregion
                break;
        }
        return -1;
    }
    public int log_equation_view(int a, int b, int p, int type) {
        switch (type) {
            case (0):
                //region case0
                int H, c;
                H = (int)sqrt((double)p) + 1;
                c = to_pow_mod(a, H, p);

                strb.append("<br>H = " + H + "<br>");
                strb.append("c = a<sup><small>H</sup></small> = " + c + "<br>");

                int[] u = new int[H + 1];
                int[] v = new int[H + 1];

                v[0] = b;
                for (int i = 1; i <= H; ++i) {
                    u[i] = to_pow_mod(c, i, p);
                    v[i] = b * to_pow_mod(a, i, p);
                    v[i] = to_mod(v[i], p);
                }

                for (int i = 1; i <= H; ++i) {
                    strb.append("<br>c<sup><small>" + i + "</sup></small> = " + u[i]);
                }
                strb.append("<br>");
                for (int i = 0; i <= H; ++i) {
                    strb.append("<br>b * a<sup><small>" + i + "</sup></small> = " + v[i]);
                }
                strb.append("<br>");
                for (int i = 1; i <= H; ++i) {
                    for (int g = 0; g <= H; ++g) {
                        if (u[i] == v[g]) {
                            return (H * i - g) % (p - 1);
                        }

                    }
                }
                //endregion
                break;
            case(1):
                //region case1
                Vector<Integer> vec = to_primes(p - 1);
                int t;
                int x_res[] = new int[vec.size()];
                int r_table[];
                int q;
                for(int i = 0; i < vec.size(); ++i){
                    t = prime(vec.get(i).intValue());

                    if(t == 0){
                        r_table = new int[vec.get(i).intValue()];
                        q = vec.get(i).intValue();
                    }
                    else{
                        r_table = new int[t];
                        q = t;
                    }

                    r_table[0] = 1;
                    for(int g = 1; g < r_table.length; ++g){
                        r_table[g] = to_pow_mod(a, g * ((p - 1) / q), p);
                    }

                    //region Какая степень числа
                    int count = 1, t2 = q;
                    while(t2 != vec.get(i).intValue()){
                        t2 *= q;
                        count++;
                    }
                    //endregion

                    strb.append("<br>-------X" + (i + 1) + "--------");
                    strb.append("<br>q" + (i + 1) + " = " + q);
                    strb.append("    α" + (i + 1) + " = " + count + "<br><br>");
                    for(int g = 0; g < r_table.length; ++g){
                        strb.append("r" + q + "," + g + " = " + r_table[g] + "<br>");
                    }


                    int b1 = b, b_r;
                    int x_temp[] = new int[count];
                    for(int g = 0; g < count; ++g){
                        b_r = to_pow_mod(b1, (p - 1) / (to_pow(q, g + 1)), p);

                        strb.append("<br>(b * a<sup><small>(-x0...)</sup></small>)<sup><small>" + ((p - 1) / (to_pow(q, g + 1))) + "</sup></small>  =  " + b_r);

                        for(int k = 0; k < r_table.length; ++k){
                            if(b_r == r_table[k]){
                                x_temp[g] = k;
                                strb.append("  =  r" + q + "," + k + "  =>  x" + g + " = " + k);
                            }
                        }

                        b1 *= to_pow_minus(a, x_temp[g] * to_pow(q, g), p);
                    }


                    for(int g = 0; g < count; ++g){
                        x_res[i] += x_temp[g] * to_pow(q, g);
                    }
                    x_res[i] %= vec.get(i).intValue();
                    strb.append("<br><br>X" + (i + 1) + " = " + x_res[i]);
                }

                Vector<Pair<Integer, Integer>> result = new Vector<>(vec.size());
                for(int i = 0; i < vec.size(); ++i){
                    result.add(Pair.create(Integer.valueOf(x_res[i]), vec.get(i)));
                }
                strb.append("<br><br>");
                strb.append("---Китайская теорема об остатках---<br>");
                for(int i = 0; i < vec.size(); ++i) {
                    strb.append("<br>x ≡ " + "X" + (i + 1) + " mod " + vec.get(i).intValue());
                }
                strb.append("<br>");

                return system_China_view(result);
                //endregion
            case(2):
                //region case2
                long Z_mas[] = new long[1005];
                long U_mas[] = new long[1005];
                long V_mas[] = new long[1005];
                int count = 0;

                Z_mas[0] = 1;
                U_mas[0] = 0;
                V_mas[0] = 0;

                strb.append("<br>           ");
                strb.append("U");
                correct_space_br(1, 7);
                strb.append("V");
                correct_space_br(1, 7);
                strb.append("Z");
                correct_space_br(1, 7);
                strb.append("<br>---------------------------");

                strb.append("<br>" + count);
                correct_space_br(count, 7);

                strb.append(U_mas[count]);
                correct_space_br(U_mas[count], 7);

                strb.append(V_mas[count]);
                correct_space_br(V_mas[count], 7);

                strb.append(Z_mas[count]);
                correct_space_br(Z_mas[count], 7);

                while(count <= 100){
                    if(Z_mas[count] <= p / 3){
                        U_mas[count + 1] = U_mas[count] + 1;
                        V_mas[count + 1] = V_mas[count];
                    }
                    else if(Z_mas[count] <= (2 * p) / 3){
                        U_mas[count + 1] = 2 * U_mas[count];
                        V_mas[count + 1] = 2 * V_mas[count];
                    }
                    else{
                        V_mas[count + 1] = V_mas[count] + 1;
                        U_mas[count + 1] = U_mas[count];
                    }

                    V_mas[count + 1] %= p - 1;
                    U_mas[count + 1] %= p - 1;

                    Z_mas[count + 1] = (to_pow_mod(b, U_mas[count + 1], p) * to_pow_mod(a, V_mas[count + 1], p)) % p;
                    count++;

                    strb.append("<br>" + count);
                    correct_space_br(count, 7);

                    strb.append(U_mas[count]);
                    correct_space_br(U_mas[count], 7);

                    strb.append(V_mas[count]);
                    correct_space_br(V_mas[count], 7);

                    strb.append(Z_mas[count]);
                    correct_space_br(Z_mas[count], 7);

                    for(int i = 0; i < count; ++i){
                        if(Z_mas[count] == Z_mas[i]){
                            long U_del = U_mas[count] - U_mas[i];
                            long V_del = V_mas[count] - V_mas[i];

                            if(nod(U_del, p - 1) != 1){
                                break;
                            }

                            strb.append("<br><br>N" + i + " -> Z = " + Z_mas[i]);
                            strb.append("<br>N" + count + " -> Z = " + Z_mas[count]);
                            strb.append("<br>ΔU = " + U_del + " -> (" + U_del + ", " + (p - 1) + ") = 1<br>");

                            long res = devide_mod(V_del, U_del, p - 1);

                            if(to_pow_mod(a, res, p) == to_mod(b, p)) {
                                return (int)res;
                            }

                            strb.append("No<br>");
                        }
                    }
                }
                //endregion
                break;
        }
        return -1;
    }
}
