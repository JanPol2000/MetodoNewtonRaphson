import java.util.Scanner;
public class NewtonRaphson{
   static double coef[];
   static double coef_extra[];
   
   
   public static void main(String args[]){
      Scanner in = new Scanner(System.in);
      /*System.out.print("Ingresalos coeficientes: ");
      int opc = in.nextInt();
      double a[] = in_extra(opc);
      for(double c: a){
         System.out.println(c);
      }*/
      
      double r = cal_exp(2,4,2);
      System.out.println(r);
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
}