/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neural_network;

    
/**
 *
 * @author GAURAV KUMAR
 */
public class AutoEncoder {
    
    static int count;
    static int no_of_hidden_neorons=2;
    public AutoEncoder(int no_of_times) {
        count=no_of_times;
    }
    
    
    public double[][] Encode(double input[],double weight[][])
    {
        // Intialization
        ArrayInput arrayinput=new ArrayInput();
        double[] output=new double[input.length];
        double hidden[][]=arrayinput.FitHidden(weight.length, input.length); 
        for(int i=0;i<output.length;i++)
        {
            output[i]=input[i];
        }
        
         ConfigureNeuron.learning_rate=0.5;
         ConfigureNeuron configure=new ConfigureNeuron();
         
         
          while(count>0)
         {
           /*
            * configure.computeHiddenOutput(inputs, hidden1, 0.35,2)
            *   argumets 1) inputs
            *           2) weight for the hidden layer
            *           3) Bias for that hidden layer
            *           4) No of neurons
            */  
         double hiddenout1[]=configure.computeHiddenOutput(input, weight, 0.35,no_of_hidden_neorons);
         double[] hiddenoutSigmoid1=configure.ComputeSigmoid(hiddenout1);
         
         double hiddenout2[]=configure.computeHiddenOutput(hiddenout1, hidden, 0.60,output.length);
         double[] hiddenoutSigmoid2=configure.ComputeSigmoid(hiddenout2);
         
        
              
         double error[] = configure.Errorcalculate(output, hiddenout2);
         for(int i=0;i<error.length;i++)
         {
            // System.out.println(error[i]+"    "+hiddenout2[i]);
         }
         // BACKPROPAGATION STARTS HERE
         
         BackPropagations propagation=new BackPropagations();
         
         hidden=propagation.OutErrorPropagate(hiddenoutSigmoid2, hiddenoutSigmoid1, output, hidden);
         
         for(int i=0;i<propagation.errors.length;i++)
         {
          // System.out.println(propagation.errors[i]) ;
         }
          weight=propagation.HiddenErrorPropagate(hiddenoutSigmoid1, input, propagation.errors, weight);
         
        // System.out.println(hidden1[1][1]);
        
         
         
      //   double hiddenout2[]=configure.computeHiddenOutput(hiddenout1, hidden1, 0.35,2);
     count--;    
    }
        return weight;
         
         
         
    }        
         
         
        
        
    }

