package ru.ktn.a4gf;

import android.support.v4.math.MathUtils;
import com.google.common.base.*;
import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.util.Vector;

import ru.ktn.a4gf.Solver;

public class Solver3 {
    public StringBuilder strb;

    public Solver3() {
        strb = new StringBuilder(10000);
    }

    public String toString() {
        return strb.toString();
    }


    public int DetermTests(int m, int type){
        switch (type){
            case (0):
                ///region тест Лукаса
                boolean endFlag;
                for(int b = 2; b <= m - 1; ++b){
                    endFlag = true;
                    if(Solver.to_pow_mod(b, (m - 1), m) != 1){
                        return 0;
                    }

                    Vector<Integer> primes = Solver.to_primes(m - 1);
                    for(int i = 0; i < primes.size(); ++i){
                        if(Solver.to_pow_mod(b, (m - 1) / primes.get(i), m) == 1){
                            endFlag = false;
                            break;
                        }
                    }

                    if(endFlag == true){
                        return 1;
                    }
                }
                return 0;
                ///endregion
            case (1):
                return  BigIntegerMath.factorial(m - 1).add(BigInteger.valueOf(1)).mod(BigInteger.valueOf(m)).intValue();
        }

        return 0;
    }
    public int DetermTests_view(int m, int type){
        switch (type){
            case (0):
                ///region тест Лукаса
                boolean endFlag;
                for(int b = 2; b <= m - 1; ++b){
                    endFlag = true;
                    if(Solver.to_pow_mod(b, (m - 1), m) != 1){
                        if(b > 2){
                            strb.append("b = [2, " + (b - 1) + "] <= Характеристика чисел != m - 1\n");
                        }
                        strb.append("b = " + b + " <= В степени m - 1 != 1\n");
                        return 0;
                    }

                    Vector<Integer> primes = Solver.to_primes(m - 1);
                    for(int i = 0; i < primes.size(); ++i){
                        if(Solver.to_pow_mod(b, (m - 1) / primes.get(i), m) == 1){
                            endFlag = false;
                            break;
                        }
                    }

                    if(endFlag == true){
                        if(b > 2){
                            strb.append("b = [2, " + (b - 1) + "] <= Характеристика чисел != m - 1\n");
                        }
                        strb.append("b = " + b + " <= Характеристика = m - 1\n");
                        return 1;
                    }
                }
                strb.append("b = [2, " + (m - 1) + "] <= Характеристика чисел != m - 1\n");
                return 0;
            ///endregion
            case (1):
                BigInteger res = BigIntegerMath.factorial(m - 1).add(BigInteger.valueOf(1));
                strb.append("(m - 1)! + 1 = " + (m - 1) + "! + 1 = " + res + "\n\n");
                res = res.mod(BigInteger.valueOf(m));
                if(res.intValue() == 0){
                    strb.append((m - 1) + "! + 1 (mod " + m + ") = 0\n\n");
                }
                else{
                    strb.append((m - 1) + "! + 1 (mod " + m + ") = " + res.intValue() + "\n\n");
                }

                return  res.intValue();
        }

        return 0;
    }


    public int ProbablyTests(int m, int count, int type){
        switch (type){
            case (0):
                ///region Тест Ферма
                for(int i = 0; i < ((count > m - 1) ? (m - 2) : count); ++i){
                    if(Solver.to_pow_mod(i + 2, m - 1, m) != 1){
                        return 0;
                    }
                }
                break;
                ///endregion
            case (1):
                ///region Тест Соловея-Штрассена
                for(int i = 0; i < ((count > m - 1) ? m - 2 : count); ++i){
                    if(Solver.to_pow_mod(i + 2, (m - 1) / 2, m) != Solver.to_mod(Solver.symbol_J(i + 2, m), m)){
                        return 0;
                    }
                }
                break;
                ///endregion
            case(2):
                ///region Тест Миллера-Рабина
                int s = 0, d = m - 1;
                while(d % 2 == 0){
                    d /= 2;
                    s++;
                }

                for(int i = 0; i < ((count > m - 1) ? m - 2 : count); ++i){
                    if(Solver.to_pow_mod(i + 2, d, m) == 1){
                        continue;
                    }
                    for(int g = 0; g < s; ++g){
                        if(Solver.to_pow_mod(i + 2, d * Solver.to_pow(2, g), m) != m - 1){
                            return 0;
                        }
                    }
                }
                ///endregion
        }
        return 1;
    }
}
