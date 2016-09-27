/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neural_network;

/**
 *
 * @author GAURAV KUMAR
 */
 public class ArrayInput {
  double input[];
  double hidden[][]; 
  double output[]; 
   public double[] FitInput(double inputs[])
     {
       input=new double[inputs.length];
       for(int i=0;i<input.length;i++)
       {
          input[i]=inputs[i]; 
       }
       return input;
     }
   public double[][] FitHidden(int input,int no_of_neuron)
   {
      hidden=new double[input][no_of_neuron];
      for(int j=0;j<input;j++)
      for(int i=0;i<no_of_neuron;i++)
      {
         hidden[j][i] =Math.random();
      }
      return hidden;
   }
   public double[] FitOutput(double outputs[])
   {
      output=new double[outputs.length];
       for(int i=0;i<input.length;i++)
       {
          output[i]=outputs[i]; 
       }
       return output;
   }
  
}
