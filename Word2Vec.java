/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neural_network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
/**
 *
 * @author GAURAV KUMAR
 */
public class Word2Vec {
 
     String[] inputVocab;
      double[] input;
      double[] output;
    public void MakeInputVocab(String file,int no_of_vectors) throws FileNotFoundException, IOException
    {
         int count=0;
         String line;
        
        BufferedReader hidden=new BufferedReader(new FileReader(new File(file)));
       
           
            while((line=hidden.readLine())!=null)
            {
                count++;
            }
        
        hidden.close();
        
         BufferedReader read=new BufferedReader(new FileReader(new File(file)));
        
            inputVocab=new String[count];
            input=new double[count];
            output=new double[count];
            for(int init=0;init<input.length;init++)
            {
                input[init]=0;
                output[init]=0;
            }
            int i=0;
            while((line=read.readLine())!=null)
            {
               inputVocab[i++]=line; 
            }
    read.close();
    }
    
    
  public void flush()
  {
      for(int init=0;init<input.length;init++)
            {
                input[init]=0;
                output[init]=0;
            }
  }
    
    
  public void MakeIndex(String file,String outfile) throws FileNotFoundException, IOException
  {
      BufferedReader hidden=new BufferedReader(new FileReader(new File(file)));
       File fn=new File(outfile);
         fn.createNewFile();
           BufferedWriter writer=new BufferedWriter(new FileWriter(fn,true));
         String line;
         while((line=hidden.readLine())!=null)
         {
             String[] str=line.split("\t");
             if(!str[0].isEmpty())
             {
              writer.write(str[0]);
             for(int i=1;i<str.length;i++)
             {
                 
                 if(str[i].contains("null"))
                 {
                     writer.write("\t");
                    writer.write(Integer.toString(-1)); 
                    
                    
                 }
                 else
                 {
                  
          String temp;
      
            for (int k = 0; k < input.length; k++) 
            {
                if (str[i].compareTo(inputVocab[k])==0) 
                {
                    //System.out.println(k + " -----------"+ str[i]+" -------"+ inputVocab[k]);
                      writer.write("\t");
                      writer.write(Integer.toString(k).trim());
                }
            }
        }
                  
                    
                 }
             }
             writer.newLine();
         }
         }
  
  public void StartWordToVec(String file,int no_of_neurons) throws FileNotFoundException, IOException
  {
       BufferedReader hidden=new BufferedReader(new FileReader(new File(file)));
       File vector=new File("vector.txt");
       vector.createNewFile();
       BufferedWriter out=new BufferedWriter(new FileWriter(vector,true));
       String line;
       int id=0;
       while((line=hidden.readLine())!=null)
       {
           String[] str=line.split("\t");
           
           flush();
           
           output[id++]=1;
           for(int i=1;i<str.length;i++)
           {
               int index=Integer.parseInt(str[i]);
               try
               {
               if(index>=0)
               {
                  input[index]=1; 
                  System.out.println(input[index]);
               }
               }
               catch(NumberFormatException e)
               {
                   continue;
               }
           }
            // Configure Neuron
      ArrayInput in=new ArrayInput();
       double hidden1[][]=in.FitHidden(input.length, no_of_neurons);  
       double hidden2[][]=in.FitHidden(no_of_neurons, input.length); 
       ConfigureNeuron.learning_rate=0.5;
         
         ConfigureNeuron configure=new ConfigureNeuron();
         int count=0;
                   double[] hiddenoutSigmoid2 = null;
                   double[] hiddenoutSigmoid1=null;
                    double hiddenout1[]=null;
                    double hiddenout2[] = null;
         while(count<10000)
         {
              
               hiddenout1=configure.computeHiddenOutput(input, hidden1, 0.35,no_of_neurons);
               hiddenoutSigmoid1=configure.ComputeSigmoid(hiddenout1);
              
               hiddenout2=configure.computeHiddenOutput(hiddenout1, hidden2, 0.60,input.length);
           hiddenoutSigmoid2=configure.ComputeSigmoid(hiddenout2);
         
         BackPropagations propagation=new BackPropagations();
         hidden2=propagation.OutErrorPropagate(hiddenoutSigmoid2, hiddenoutSigmoid2, output, hidden2);
         hidden1=propagation.HiddenErrorPropagate(hiddenoutSigmoid1, input, propagation.errors, hidden1);
         
             count++; 
         }
        System.out.println("--------");
        out.write(inputVocab[id-1]);
        out.write("[");
        
       for(int t=0;t<hiddenoutSigmoid1.length;t++)
       {
          out.write(Double.toString(hiddenoutSigmoid1[t])+",");
           //System.out.println();
       }
        out.write("[");
        out.newLine();
        System.out.println("--------");
        
        double error[] = configure.Errorcalculate(output, hiddenout2);
         for(int i=0;i<error.length;i++)
         {
             System.out.println(error[i]);
         }
       }
       out.close();
    
       
  }
  
  
  public static void main(String args[]) throws FileNotFoundException, IOException
{
    Word2Vec vec=new Word2Vec();
    vec.MakeInputVocab("temp.txt", 10);
   vec.MakeIndex("break.txt", "out.txt");
   vec.StartWordToVec("out.txt", 100);
    /*vocab.BreakWords("Review.txt");
    vocab.FindVocabs("temp.txt");
    */
    
  //  vocab.Sentence_Breaker("Review.txt", "break.txt", 2);
}

}
