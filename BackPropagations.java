/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neural_network;

/**
 *
 * @author GAURAV KUMAR
 */
public class BackPropagations {
    
    double errors[];
  public double[][] OutErrorPropagate(double withSigmoid[],double withoutSigmoid[],double target[],double weight[][])
  {
      errors=new double[withoutSigmoid.length];
    //  System.out.println(" aaaaaaa"+withoutSigmoid.length);
      for(int i=0;i<weight.length;i++)
      {
          double error=0;
          for(int j=0;j<withSigmoid.length;j++)
          {
            double  value=(withSigmoid[j]-target[j])*withSigmoid[j]*(1-withSigmoid[j])*withoutSigmoid[i];
            
           
            error=error+(withSigmoid[j]-target[j])*(1-withSigmoid[j])*withSigmoid[j]*weight[i][j];
            
          
            weight[i][j]=weight[i][j] - ConfigureNeuron.learning_rate*value;
            
           
            
          }
         errors[i]=error;
           // System.out.println(errors[i]+" aaaaa"+i+"   aaaaaa"+ i); 
      }
      return weight;
  }
 
  
  
  
  public double[][] HiddenErrorPropagate(double withSigmoid[],double withoutSigmoid[],double Errors[],double weight[][])
  {
      errors=new double[withoutSigmoid.length];
      for(int i=0;i<weight.length;i++)
      {
          double error=0;
          for(int j=0;j<withSigmoid.length;j++)
          {
            double  value=withSigmoid[j]*(1-withSigmoid[j])*withoutSigmoid[i]*Errors[j];
            
            error=error+Errors[j]*weight[i][j];
            
            weight[i][j]=weight[i][j] - ConfigureNeuron.learning_rate*value;
            error=error+value;
            
            
          }
          errors[i]=error;
      }
      return weight;
  }
  
}
