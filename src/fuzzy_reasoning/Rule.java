package fuzzy_reasoning;

import java.util.ArrayList;

/**
 * Created by didrikpa on 23.10.15.
 */
public class Rule {/*
    String rule;
    public ArrayList<Double> distanceFuzzification(double position){
        ArrayList<Double> distance = new ArrayList<>();
        if (position>=0.25 && position<1.75){
            distance.add(reverse_grade(position, 0.25, 1.5, 1.75, 1));
            distance.add(1.0);
            System.out.println("VerySmall");
        }
        else if (position >=1.75 &&position<2.75){
            distance.add(reverse_grade(position, 0.25, 1.5, 1.75, 1));
            distance.add(1.0);
            distance.add(triangle(position, 1.75, 3.25, 4.75, 1));
            distance.add(2.0);
            System.out.println("VerySmall and Small");
        }
        else if (position >=2.75 &&position<3.75){
            distance.add(triangle(position, 1.75, 3.25, 4.75, 1));
            distance.add(2.0);
            System.out.println("Small");
        }
        else if (position >=3.75 &&position<4.75){
            distance.add(triangle(position, 1.75, 3.25, 4.75, 1));
            distance.add(2.0);
            distance.add(triangle(position, 3.75, 5.25, 6.75, 1));
            distance.add(3.0);
            System.out.println("Small and Perfect");
        }
        else if (position >=4.75 &&position<5.75){
            distance.add(triangle(position, 3.75, 5.25, 6.75, 1));
            distance.add(3.0);
            System.out.println("Perfect");
        }
        else if (position >=5.75 &&position<6.75){
            distance.add(triangle(position, 3.75, 5.25, 6.75, 1));
            distance.add(3.0);
            distance.add(triangle(position, 5.75, 7.25, 8.75, 1));
            distance.add(4.0);
            System.out.println("Perfect and Big");
        }
        else if (position >=6.75 &&position<7.75){
            distance.add(triangle(position, 5.75, 7.25, 8.75, 1));
            distance.add(4.0);
            System.out.println("Big");
        }
        else if (position >=7.75 &&position<8.75){
            distance.add(triangle(position, 5.75, 7.25, 8.75, 1));
            distance.add(4.0);
            distance.add(grade(position, 7.75, 9.25, 10.25, 1));
            distance.add(5.0);
            System.out.println("Big and VeryBig");
        }
        else if (position >=8.75 &&position<9.75){
            distance.add(grade(position, 7.75, 9.25, 10.25, 1));
            distance.add(5.0);
            System.out.println("VeryBig");
        }
        return distance;
    }

    public ArrayList<Double> deltaFuzzification(double position){
        ArrayList<Double> delta = new ArrayList<>();
        if (position>=-4.75 && position<-3.25){
            delta.add(reverse_grade(position, -4.75, -3.75,-2.25, 1 ));
            delta.add(1.0);
            System.out.println("ShrinkingFast");
        }
        else if (position >=-3.25 && position<-2.25){
            delta.add(reverse_grade(position, -4.75, -3.75,-2.25, 1 ));
            delta.add(1.0);
            delta.add(triangle(position, -3.25, -1.75, -0.25, 1));
            delta.add(2.0);
            System.out.println("ShrinkingFast and Shrinking");
        }
        else if (position >=-2.25 && position<-1.25){
            delta.add(triangle(position, -3.25, -1.75,-0.25, 1 ));
            delta.add(2.0);
            System.out.println("Shrinking");
        }
        else if (position >=-1.25 && position<-0.25){
            delta.add(triangle(position, -3.25, -1.75,-0.25, 1 ));
            delta.add(2.0);
            delta.add(triangle(position, -1.25, 0.25,1.75, 1 ));
            delta.add(3.0);
            System.out.println("Shrinking and Stable");
        }
        else if (position >=-0.25 && position<0.75){
            delta.add(triangle(position, -1.25, 0.25,1.75, 1 ));
            delta.add(3.0);
            System.out.println("Stable");
        }
        else if (position >=0.75 && position<1.75){
            delta.add(triangle(position, -1.25, 0.25,1.75, 1 ));
            delta.add(3.0);
            delta.add(triangle(position, 0.75, 2.25,3.75, 1 ));
            delta.add(4.0);
            System.out.println("Stable and Growing");
        }
        else if (position >=1.75 && position<2.75){
            delta.add(triangle(position, 0.75, 2.25,3.75, 1 ));
            delta.add(4.0);
            System.out.println("Growing");
        }
        else if (position >=2.75 && position<3.75){
            delta.add(triangle(position, 0.75, 2.25,3.75, 1 ));
            delta.add(4.0);
            delta.add(grade(position, 2.75, 4.25, 5.25, 1));
            delta.add(5.0);
            System.out.println("Growing and GrowingFast");
        }
        else if (position >=3.75 && position<4.75){
            delta.add(grade(position, 2.75, 4.25,5.25, 1 ));
            delta.add(5.0);
            System.out.println("GrowingFast");
        }
        return delta;
    }

    public ArrayList<Double> evaluateExpression(){
        return null;
    }

    public void ruleEvaluation(String rule, ArrayList<Double> distances, ArrayList<Double> deltas){
        int lenDist = distances.size();
        int lenDelt = deltas.size();
        String[] ruleWords = ruleWords = rule.split(" ");
        for (int i = 0; i < ruleWords.length; i++) {

        }
        int or = 0;
        int and = 0;
        int not = 0;
        for (int i = 0; i <ruleWords.length; i++) {
            if (ruleWords[i].equals("AND")){
                and+=1;
            }
            else if (ruleWords[i].equals("OR")){
                or+=1;
            }
            else if (ruleWords[i].equals("NOT")){
                not+=1;
            }
        }

    }

    public Rule(String rule){
        this.rule = rule;
    }
*/
}
