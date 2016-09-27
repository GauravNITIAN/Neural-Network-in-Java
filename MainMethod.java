/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neural_network;

/**
 *
 * @author GAURAV KUMAR
 */
public class MainMethod {
 
    public void Write()
    {
        
    }
    
    
    public static void main(String args[])
    {
         ArrayInput input=new ArrayInput();
        
       // Intitialization of hidden layer and input and output
         double inputs[]=input.FitInput(new double[]{0.05,0.10});
         /*
          double hidden1[][]=input.FitHidden(2, 2);
          *  argument 1) no of Inputs to neuron
          *           2) no of neuron at that layer
          */
         double hidden1[][]=input.FitHidden(2, 2);    
         double hidden2[][]=input.FitHidden(2, 2);  
         double hidden3[][]=input.FitHidden(2, 2);  
         double outputs[]=input.FitOutput(new double[]{0.01,0.99});
         hidden1[0][0]=0.15;
         hidden1[0][1]=0.25;
         hidden1[1][0]=0.20;
         hidden1[1][1]=0.30;
         
         hidden2[0][0]=0.40;
         hidden2[0][1]=0.50;
         hidden2[1][0]=0.45;
         hidden2[1][1]=0.55;
            
          double hidd[][]=input.FitHidden(2, 2);  
         AutoEncoder auto =new AutoEncoder(90000);
         hidden1=auto.Encode(inputs, hidden1);
         
         ConfigureNeuron.learning_rate=0.5;
         
         ConfigureNeuron configure=new ConfigureNeuron();
         int count=0;
                   
         while(count<100000)
         {
           /*
            * configure.computeHiddenOutput(inputs, hidden1, 0.35,2)
            *   argumets 1) inputs to layer
            *           2) weight for the hidden layer
            *           3) Bias for that hidden layer
            *           4) No of neurons
            */  
         double hiddenout1[]=configure.computeHiddenOutput(inputs, hidden1, 0.35,2);
         double[] hiddenoutSigmoid1=configure.ComputeSigmoid(hiddenout1);
         
         double hiddenout2[]=configure.computeHiddenOutput(hiddenout1, hidden2, 0.60,2);
         double[] hiddenoutSigmoid2=configure.ComputeSigmoid(hiddenout2);
         
         double hiddenout3[]=configure.computeHiddenOutput(hiddenout2, hidden3, 0.35,2);
         double[] hiddenoutSigmoid3=configure.ComputeSigmoid(hiddenout3);
              
         double error[] = configure.Errorcalculate(outputs, hiddenout3);
         for(int i=0;i<error.length;i++)
         {
             System.out.println(error[i]+"    "+hiddenout3[i]);
         }
         // BACKPROPAGATION STARTS HERE
         
         BackPropagations propagation=new BackPropagations();
         
         hidden3=propagation.OutErrorPropagate(hiddenoutSigmoid3, hiddenoutSigmoid2, outputs, hidden3);
         
         for(int i=0;i<propagation.errors.length;i++)
         {
          // System.out.println(propagation.errors[i]) ;
         }
          hidden2=propagation.HiddenErrorPropagate(hiddenoutSigmoid2, inputs, propagation.errors, hidden2);
         hidden1=propagation.HiddenErrorPropagate(hiddenoutSigmoid1, inputs, propagation.errors, hidden1);
        // System.out.println(hidden1[1][1]);
        
         
         
      //   double hiddenout2[]=configure.computeHiddenOutput(hiddenout1, hidden1, 0.35,2);
     count++;    
    }
         
         
         
    }        
}
