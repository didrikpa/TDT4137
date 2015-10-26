    package fuzzy_reasoning;

    /**
     * Created by didrikpa on 22.10.15.
     */

    import java.util.ArrayList;
    public class MamdaniFuzzyReasoning {

        ArrayList<Double> distanceValues;
        ArrayList<Double> deltaValues;
        //Rules.
        ArrayList<String> rules = new ArrayList<>();
        public String rule1 = "IF distance is Small AND delta is Growing THEN action is None";
        public String rule2 = "IF distance is Small AND delta is Stable THEN action is SlowDown";
        public String rule3 = "IF distance is Perfect AND delta is Growing THEN action is SpeedUp";
        public String rule4 = "IF distance is VeryBig AND (delta is NOT Growing OR delta is NOT GrowingFast) THEN action is FloorIt";
        public String rule5 = "IF distance is VerySmall THEN action is BrakeHard";

        public MamdaniFuzzyReasoning(){
            this.deltaValues = new ArrayList<>();
            this.distanceValues = new ArrayList<>();
            rules.add(rule1);
            rules.add(rule2);
            rules.add(rule3);
            rules.add(rule4);
            rules.add(rule5);
        }
        //Given method for fuzzy sets.
        public static double triangle(double position, double x0, double x1, double x2, double clip){
            double value = 0.0;
            if (position >= x0 && position <=x1){
                value = ((position-x0)/(x1-x0));
            }
            else if (position >=x1 && position<=x2){
                value = (x2-position)/(x1-x0);
            }
            if (value>clip){
                value=clip;
            }
            return (double)Math.round((value)*10)/10;
        }
        //Given method for fuzzy sets.
        public static double grade(double position, double x0, double x1, double clip){
            double value;
            if (position >=x1){
                value = 1.0;
            }
            else if (position <=x0){
                value = 0.0;
            }
            else{
                value = (position -x0)/(x1-x0);
            }
            if (value>clip){
                value=clip;
            }
            return (double)Math.round((value)*10)/10;
        }
        //Given method for fuzzy sets.
        public static double reverse_grade(double position, double x0, double x1, double clip){
            double value;
            if (position <=x0){
                value = 1.0;
            }
            else if (position >=x1){
                value = 0.0;
            }
            else {
                value = (x1-position)/(x1-x0);
            }
            if (value>clip){
                value=clip;
            }
            return (double)Math.round((value)*10)/10;
        }

        //Step 1 Fuzzification: Add the fuzzy value for each fuzzy-set to a list (One for delta an one for distance).
        public void distanceFuzz(double position){
            distanceValues.add(reverse_grade(position, 0.25, 1.5, 1));
            distanceValues.add(triangle(position, 1.75, 3.25, 4.75, 1));
            distanceValues.add(triangle(position, 3.75, 5.25, 6.75, 1));
            distanceValues.add(triangle(position, 5.75, 7.25, 8.75, 1));
            distanceValues.add(grade(position, 7.75, 9.25, 1));
        }

        //Step 1 Fuzzification: Add the fuzzy value for each fuzzy-set to a list (One for delta an one for distance).
        public void deltaFuzz(double position){
            deltaValues.add(reverse_grade(position, -4.75, -3.75, 1));
            deltaValues.add(triangle(position, -3.25, -1.75, -0.25, 1));
            deltaValues.add(triangle(position, -1.25, 0.25,1.75, 1 ));
            deltaValues.add(triangle(position, 0.75, 2.25, 3.75, 1));
            deltaValues.add(grade(position, 2.75, 4.25, 1));
        }
        // Part of step 4 - defuzzification. Adds the fuzzy actionvalue to a list.
        public ArrayList<Double> actionFuzz(double position){
            ArrayList<Double> actionValues = new ArrayList<>();
            actionValues.add(reverse_grade(position,-10, -8, 1));
            actionValues.add(triangle(position, -7, -4, -1, 1));
            actionValues.add(triangle(position,-3, 0, 3, 1));
            actionValues.add(triangle(position,1, 4, 7, 1));
            actionValues.add(grade(position, 5, 8, 1));
            return actionValues;
        }
        //Fuzzy OR - Used in Step 2 - rule evaluation
        public double fuzzyOR(double value1, double value2){
            return Math.max(value1, value2);
        }
        //Fuzzy AND - Used in step 2 - rule evaluation
        public double fuzzyNOT(double value){
            return 1-value;
        }
        //Fuzzy NOT - used in step 2 - rule evaluation
        public double fuzzyAND(double value1, double value2){
            return Math.min(value1, value2);
        }

        //Step 2 Evaluate Rules - Get the fuzzy value based on the distance given in the rule.
        public double evaluateDistance(String distance){
            if (distance.equals("VerySmall")){
                return distanceValues.get(0);
            }
            else if (distance.equals("Small")){
                return distanceValues.get(1);
            }
            else if (distance.equals("Perfect")){
                return distanceValues.get(2);
            }
            else if (distance.equals("Big")){
                return distanceValues.get(3);
            }
            else if (distance.equals("VeryBig")){
                return distanceValues.get(4);
            }
            return 100.0;
        }
        //Step 2 Evaluate Rules - gets the fuzzy value based on the delta given in the rule.
        public double evaluateDelta(String delta){
            if (delta.equals("ShrinkingFast")){
                return deltaValues.get(0);
            }
            else if (delta.equals("Shrinking")){
                return deltaValues.get(1);
            }
            else if (delta.equals("Stable")){
                return deltaValues.get(2);
            }
            else if (delta.equals("Growing")){
                return deltaValues.get(3);
            }
            else if (delta.equals("GrowingFast")){
                return deltaValues.get(4);
            }
            return 100.0;
        }

        //Step 2 Rule evaluation - This function evaluates the rule given as input.
        public double evaluateExpression(String expression){
            ArrayList<Double> values = new ArrayList<>(); // The list of fuzzy values.
            expression = expression.replaceAll("[()]", ""); // Takes away the parentheses
            String[] tempAnd = expression.split("AND|OR"); // Splits the rule on AND and OR.
            String[] express = expression.split(" "); // Makes another list by splitting on " ".
            ArrayList<String> express2 = new ArrayList<>(); // Makes an ArrayList based on the expression list.
            for (int i = 0; i < express.length; i++) {
                express2.add(express[i]);
            }
            if (tempAnd.length > 1){ //Checks if the expression contains ANDs or ORs.
                for (int i = 0; i < tempAnd.length; i++) {
                    values.add(evaluateExpression(tempAnd[i]));// If it contains ANDs or ORs, it calls it self on the expressions made by splitting on AND and OR.
                }
            }
            else { //If the expression does not contain AND or OR, the expression is evaluated by getting/setting the delta and/or distance values.
                if (express2.contains("distance")){ //Sets the distance value if it exists in the rule.
                    if (!express2.get(express2.indexOf("distance")+2).equals("NOT")){
                        return evaluateDistance(express2.get(express2.indexOf("distance") + 2));
                    }
                    else {
                        return fuzzyNOT(evaluateDistance(express2.get(express2.indexOf("distance")+3))); // checks if the expression contains NOT.
                    }
                }
                else if (express2.contains("delta")){ // sets the delta value if it exists in the rule.
                    if (!express2.get(express2.indexOf("delta")+2).equals("NOT")){
                        return evaluateDelta(express2.get(express2.indexOf("delta") + 2));
                    }
                    else {
                        return fuzzyNOT(evaluateDelta(express2.get(express2.indexOf("delta")+3))); // checks if the expression contains NOT
                    }
                }
            }
            ArrayList<String> logOp = new ArrayList<>(); // A list containing the logical operators in backwards order
            for (int i = 0; i < express2.size(); i++) { // Adds the logical operators.
                if (express2.get(i).equals("AND") || express2.get(i).equals("OR")){
                    logOp.add(express2.get(i));
                }
            }
            for (int i = logOp.size()-1; i >=0; i--) { // evaluates the fuzzy values in pairs with either OR or AND.
                if (logOp.get(i).equals("OR")){
                    int a = values.size()-1;
                    int b = values.size()-2;
                    values.add(fuzzyOR(values.get(a), values.get(b))); //adds the new value to the value list.
                    values.remove(a);//DEletes the two values that was used in the OR-evaluation
                    values.remove(b);
                }
                else if (logOp.get(i).equals("AND")){
                    int a = values.size()-1;
                    int b = values.size()-2;
                    values.add(fuzzyAND(values.get(a), values.get(b)));//adds the new value to the value list
                    values.remove(a); // deletes the two values that was used in the AND-evaluation
                    values.remove(b);
                }
            }
            return values.get(0); //returns the remaining value when all logical expression have been evaluated.
        }

        public String getAction(String expression){ // returns the action based on the rule.
            String[] express = expression.split(" ");
            ArrayList<String> express2 = new ArrayList<>();
            for (int i = 0; i < express.length; i++) {
                express2.add(express[i]);
            }
            if (express2.contains("action")){
                return express2.get(express2.indexOf("action")+2);
            }
            return "No action";

        }

        public ArrayList<Double> getActionSet(String action){ // returns the fuzzy set for the action.
            ArrayList<Double> actionSet = new ArrayList<>();
            if (action.equals("BrakeHard")){
                actionSet.add(-10.0);
                actionSet.add(-8.0);
                actionSet.add(-5.0);
            }
            else if (action.equals("SlowDown")){
                actionSet.add(-7.0);
                actionSet.add(-4.0);
                actionSet.add(-1.0);
            }
            else if (action.equals("None")){
                actionSet.add(-3.0);
                actionSet.add(0.0);
                actionSet.add(3.0);
            }
            else if (action.equals("SpeedUp")){
                actionSet.add(1.0);
                actionSet.add(4.0);
                actionSet.add(7.0);
            }
            else if (action.equals("FloorIt")){
                actionSet.add(5.0);
                actionSet.add(8.0);
                actionSet.add(10.0);
            }
            return actionSet;
        }
        //Step 2 - Clipping - Finds the slope of the line-equation
        public double findSlope(double x0, double y0, double x1, double y1){
            return (y1-y0)/(x1-x0);
        }
        //Step 2 - Clipping -  finds the b-value of the line-equation
        public double findBValue(double x0, double y0, double slope){
            return y0-(x0*slope);
        }

        //Step 2 - Clipping - Gets the x-value of the crossing between the fuzzy action-value and a line in the action fuzzy set.
        public double getXValue(double actionValue, double x0, double x1, double y0, double y1, String action){
            double slope = findSlope(x0,y0,x1,y1 );
            double b = findBValue(x0, y0, slope);
            if (slope == 0){
                if (action.equals("BrakeHard")){
                    return x0;
                }
                else if (action.equals("FloorIt")){
                    return x1;
                }
            }
            return (actionValue-b)/slope;
        }

        //Step 2 - Clipping and Step 3 - Aggregation of the rule outputs. This function makes one fuzzy set for the action value.
        public ArrayList<Double> getSetDistance(double x0, double x1, double x2, String action, double actionValue){
            ArrayList<Double> distanceSet = new ArrayList<>();
            if (action.equals("BrakeHard")){
                distanceSet.add((double) Math.round(getXValue(actionValue, x0, x1, 1, 1, action)*1)/1);
                distanceSet.add((double) Math.round(getXValue(actionValue, x1, x2, 1, 0, action)*1)/1);
            }
            else if (action.equals("FloorIt")){
                distanceSet.add((double) Math.round(getXValue(actionValue, x0, x1, 0, 1, action) * 1 / 1));
                distanceSet.add((double) Math.round(getXValue(actionValue, x1, x2, 1, 1, action) * 1) / 1);
            }
            else if (action.equals("SlowDown") || action.equals("None") || action.equals("SpeedUp")){
                distanceSet.add((double) Math.round(getXValue(actionValue, x0, x1, 0, 1, action)*1)/1);
                distanceSet.add((double) Math.round(getXValue(actionValue, x1, x2, 1, 0, action)*1)/1);
            }
            return distanceSet;
        }

        //Step 4 - Defuzzification - Calculates the COG-value.
        public double defuzzification(ArrayList<ArrayList<Double>> setValues, ArrayList<Double> actionValues){
            double cogOver = 0;
            double cogUnder = 0;
            for (int i = 0; i < setValues.size(); i++) {
                double tempOver = 0;
                double tempUnder = 0;
                if (actionValues.get(i) != 0.0){
                    for (double j = setValues.get(i).get(0); j <= setValues.get(i).get(1); j++) {
                        tempOver+=j;
                        tempUnder+=1;
                    }
                    tempOver = tempOver*actionValues.get(i);
                    cogOver+=tempOver;
                    cogUnder+=tempUnder*actionValues.get(i);
                }

            }
            return cogOver/cogUnder;
        }

        //The algorithm.
        public String mamdaniFuzzy(double distance, double delta, ArrayList<String> rules){
            ArrayList<String> actions = new ArrayList<>(); // The different actions.
            ArrayList<Double> actionValues = new ArrayList<>(); // the value of the different actions
            ArrayList<ArrayList<Double>> actionSets = new ArrayList<>(); // The action fuzzy set
            ArrayList<ArrayList<Double>> setDistances = new ArrayList<>(); // the action fuzzy set with the action value from the rule.
            ArrayList<Double> actionCOGs;
            double cogValue = 0;

            //Step 1: Fuzzification.
            distanceFuzz(distance);
            deltaFuzz(delta);
            //Step 2: Evaluate rules.
            for (int i = 0; i < rules.size(); i++) {
                actionValues.add(evaluateExpression(rules.get(i)));
                actions.add(getAction(rules.get(i)));
                actionSets.add(getActionSet(actions.get(i)));
            }
            //Step 2: Clipping and Step 3: Aggregation.
            for (int i = 0; i  <actionSets.size(); i++) {
                setDistances.add(getSetDistance(actionSets.get(i).get(0), actionSets.get(i).get(1), actionSets.get(i).get(2), actions.get(i), actionValues.get(i)));
            }

            //Step 4: Defuzzification.
            cogValue = defuzzification(setDistances, actionValues);
            actionCOGs = actionFuzz(cogValue);
            int max = -1;
            double maxx = 0;
            for (int i = 0; i < actionCOGs.size(); i++) {
                if (actionCOGs.get(i) > maxx){
                    maxx = actionCOGs.get(i);
                    max = i;
                }
            }
            if (max == 0){
                System.out.println("BrakeHard");
                return "BrakeHard";
            }
            else if (max == 1){
                System.out.println("SlowDown");
                return "SlowDown";
            }
            else if (max == 2){
                System.out.println("None");
                return "None";
            }
            else if (max == 3){
                System.out.println("SpeedUp");
                return "SpeedUp";
            }
            else if (max == 4){
                System.out.println("FloorIt");
                return "FloorIt";
            }
            else{
                System.out.println("No action");
            }
            return "No action";
        }

        public static void main(String[] args) {
            MamdaniFuzzyReasoning mfr = new MamdaniFuzzyReasoning();
            mfr.mamdaniFuzzy(3.85, 1.35, mfr.rules);
        }
    }
