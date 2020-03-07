import java.util.Scanner;
public class NewtonRaphson{
   static double coef[] = {-5,-3,4};
   static double coef_extra[] = {-2,3,-2,3};
   static double dx = 0.00001;
   
   
   public static void main(String args[]){
      Scanner in = new Scanner(System.in);
      /*System.out.print("Ingresalos coeficientes: ");
      int opc = in.nextInt();
      double a[] = in_extra(opc);
      for(double c: a){
         System.out.println(c);
      }*/
      
      double r = cal_exp(2,2,1);
      System.out.println(r);
      double d = derivada(2,2,1);
      System.out.println(d);
      double n = newton_raphson(2,2,1);
      System.out.println(n);
      
      error(2,2,1,2);
   }
   
   
   static double[] in_polinomio(int grado){
      Scanner in = new Scanner(System.in);
      coef = new double[grado + 1];
      for(int i = grado; i >= 0; i--){
         if(i > 1)
            System.out.print("ax^" + i + ", a = ");
         else
            if(i == 1)
               System.out.print("ax, a = ");
            else
               System.out.print("a = ");
         coef[i] = in.nextDouble();
      }
      return coef;
   }
   
   static double[] in_extra(int opc){
      Scanner in = new Scanner(System.in);
      coef_extra = new double[4];
      switch(opc){
         case 1: 
            System.out.print("asen(bx), a = ");
            coef_extra[0] = in.nextDouble();
            if(coef_extra[0] != 0){
               System.out.print(coef_extra[0] + "sen(bx), b = ");
               coef_extra[1] = in.nextDouble();
            }
            break;
         case 2: 
            System.out.print("acos(bx), a = ");
            coef_extra[0] = in.nextDouble();
            if(coef_extra[0] != 0){
               System.out.print(coef_extra[0] + "cos(bx), b = ");
               coef_extra[1] = in.nextDouble();
            }
            break;
      }
      System.out.print("ae^(bx), a = ");
      coef_extra[2] = in.nextDouble();
      if(coef_extra[2] != 0){
         System.out.print(coef_extra[2] + "e^2(bx), b = ");
         coef_extra[3] = in.nextDouble();
      }
      return coef_extra;
   }
   
   static double cal_exp(double x, int grado, int opc){
      double resultado = 0;
      if(grado != 0){
         for(int i = grado; i > 1; i--)
            resultado += coef[i] * Math.pow(x, i);
         resultado += coef[1] * x + coef[0];
      } else
         resultado += coef[0];
         
      switch(opc){
         case 1:
            if(coef_extra[0] != 0)
               resultado += coef_extra[0] * Math.sin(coef_extra[1] * x);
            break;
         case 2:
            if(coef_extra[0] != 0)
               resultado += coef_extra[0] * Math.cos(coef_extra[1] * x);
            break;
      }
      if(coef_extra[2] != 0)
         resultado += coef_extra[2] * Math.exp(coef_extra[3] * x);
      return resultado;
   }
   
   static double derivada(double x, int grado, int opc){
      double resultado = (cal_exp(x + dx, grado, opc) - cal_exp(x, grado, opc)) / dx;
      return resultado;
   }  
   
   static double newton_raphson(double x, int grado, int opc){
      double resultado = x - (cal_exp(x, grado, opc) / derivada(x, grado, opc));
      return resultado;
   }
   
   static void error(double x, int grado, int opc, int opc_err){
      Scanner in = new Scanner(System.in);
      double e_a = 0, e_s = 1;
      int it = 0, i = 0;
      double x_Ant = x, x_Actual;
      
      switch(opc_err){
         case 1: 
            System.out.print("Ingresa el Ea: ");
            e_s = in.nextDouble();
            break;
         case 2:
            System.out.print("Ingresa las cifras significativas: ");
            double cifras = in.nextDouble();
            e_s = 0.5 * Math.pow(10, 2 - cifras);
            break;
         case 3:
            System.out.print("Ingresa el numero maximo de iteraciones: ");
            it = in.nextInt();
            break;
      }
       System.out.printf("| %10s| %22s | %22s | %22s |\n", "x", "f(x)", "f'(x)", "Ea");
       System.out.printf("----------------------------------------------------------------------------------------\n");           
      do{
         x_Actual = newton_raphson(x_Ant, grado, opc);
         if(opc_err != 3)
            e_a = Math.abs((x_Actual - x_Ant) / x_Actual);
         System.out.printf("| %10.6f| %22.6f | %22.6f | %22.6f |\n", x_Ant, cal_exp(x_Ant, grado, opc), derivada(x_Ant, grado, opc), e_a);
         x_Ant = x_Actual;
         i++;
      }while(!(e_a < e_s) || i < it);
      if(opc_err == 2){
      System.out.println("Raiz = " + x_Ant);
      }
            //IMPRIMIR
      else
         System.out.println("Raiz = " + x_Ant);
   }
   
   static double cifras_sig(double r, int cifras){
   return 0;
   }
}