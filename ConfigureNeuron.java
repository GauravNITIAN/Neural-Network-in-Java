/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neural_network;

/**
 *
 * @author GAURAV KUMAR
 */
public class ConfigureNeuron {
    
    static double learning_rate=0.5;
    
    public double[] ComputeSigmoid(double input[])
     {
      for(int i=0;i<input.length;i++)
      {
      double exponent=Math.exp(-input[i]);
       input[i]=1/(1+exponent);
      
     }
      return input;
     }
    
  public double computeNextHiddenInput(double input[],double weight[],double bias)
  {
     // no of weight is equal to no_of_input*weight;
      double value=0.0;
     
      for(int i=0;i<input.length;i++)
      {
          value=value+input[i]*weight[i];
      }
        value=value+bias;   
        return value;
  } 
   
  
  public double[] computeHiddenOutput(double input[],double weight[][],double bias,int no_of_neurons)
  {
      double output[]=new double[no_of_neurons];
      for(int j=0;j<no_of_neurons;j++ )
      {
      double value=0.0;
      for(int i=0;i<weight.length;i++)
      {
          value=value+input[i]*weight[i][j];
      }
      output[j]=bias+value;
      }
      return output;
  }
  
  
 public double[] Errorcalculate(double target[],double actual_output[])
     {
     double[] error =new double[target.length];   
     for(int i=0;i<target.length;i++)
     {
     error[i]=Math.pow((target[i]-actual_output[i]),2);
     error[i]=error[i]/2;
    
     }
     
      return error;
     }
  
}
