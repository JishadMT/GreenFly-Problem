/*
Greenfly can reproduce asexually. After one week of life a lone female can produce eight
offspring a day. Starting at the beginning of day 1 with a single mature female, how many
greenfly could there be by the end of day 28? You may assume that:
 
There are no deaths
All ospring are females

Note that at the end of day 1 there will be 9 greenfly (original + 8 ospring). At the end
of day 7 there will be 57 greenfly (original + 8 x 7 ooffspring). At the end of day 8 there will
be 129 greenfly (original + 8 x 8 offspring + 64 ospring from the daughters produced on
day 1).
*/

import java.util.*;
import java.io.*;

class GreenflyProblem{
    int matureFlies;
    HashMap<Integer,Integer> immatureFlies;
    int totalFlies,maturityPeriod,reproductionRate;
    GreenflyProblem(int initialFlies){
        this.immatureFlies = new HashMap<Integer,Integer>();
        this.totalFlies=initialFlies;
        this.matureFlies=initialFlies;
        this.maturityPeriod=1;
        this.reproductionRate=1;
    }
    void setMaturityPeriod(int days){
        this.maturityPeriod=days;
        for(int i=1;i<=this.maturityPeriod;++i){
            immatureFlies.put(i,0);
        }
    }
    void setReproductionRate(int reproductionRate){
        this.reproductionRate = reproductionRate;
    }
    int getTotalFlies(){
        return this.totalFlies;
    }
    void updateForNextDay(){
        this.matureFlies += immatureFlies.get(maturityPeriod);
        for(int i=maturityPeriod; i>1 ; --i){
            this.immatureFlies.put(i,this.immatureFlies.get(i-1));
        }
        this.immatureFlies.put(1,this.matureFlies*this.reproductionRate);
        totalFlies += this.immatureFlies.get(1);
    }
    public static void main(String as[]) throws IOException{
        System.out.println("How many mature flies do we have on Day-1 ? : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        GreenflyProblem greenFly = new GreenflyProblem(Integer.parseInt(br.readLine()));

        System.out.println("How many days a greenfly take to start reproducing ? : ");
        greenFly.setMaturityPeriod(Integer.parseInt(br.readLine()));
        System.out.println("How many children are given birth at a time ? : ");
        greenFly.setReproductionRate(Integer.parseInt(br.readLine()));
        System.out.println("How many days's observation is required ? : ");
        int numberOfDays = Integer.parseInt(br.readLine());

        for(int i=1;i<=numberOfDays;++i){
            greenFly.updateForNextDay();
            System.out.println("Total number of greenflies after "+i+" days is : "+greenFly.getTotalFlies());
        }
    }
}
